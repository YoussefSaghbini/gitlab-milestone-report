import org.gitlab.api.*
import org.gitlab.api.models.*

import java.util.stream.Collectors

@Grab('org.gitlab:java-gitlab-api:1.2.7')
class MilestoneReport {

	static void main(String... args) {
		if (args.length != 4) throw new IllegalArgumentException("Exact four arguments are needed: [gitlab/url] [access token] [projectId] [milestoneId]")
		def markdown = new Markdown(new Transformer(new Gitlab(args)))
		println markdown.report()
	}


	static class Transformer {

		private Gitlab gitlab

		def milestoneUrl = gitlab.milestoneUrl()
		def milestoneId = gitlab.stone
		def issueUrl = gitlab.issuesUrl()

		Transformer(Gitlab gitlab) {
			this.gitlab = gitlab
		}

		def groupIssuesByLabels() {
			def labels = gitlab.labels()
			def issues = gitlab.issues()
			labels.collectEntries { label ->
				[label.name, issues.stream().filter { it.labels.contains(label.name) }.collect()]
			} as Map<String, List<GitlabIssue>>
		}

	}

	static class Markdown {

		private Transformer transformer

		Markdown(Transformer transformer) {
			this.transformer = transformer
		}

		def report() {
			section() + "\n\n" + body() + "\n\n" + footer()
		}

		def body() {
			String.join("\n", transformer.groupIssuesByLabels().entrySet().stream()
					.filter { !it.value.isEmpty() }
					.map { subSection(it.key, it.value) }
					.collect())
		}

		def subSection(String label, List<GitlabIssue> issues) {
			"##### $label\n\n${String.join("\n", issues.collect { issueToText(it) })}\n"
		}

		def issueToText(GitlabIssue issue) {
			"- $issue.title - [#$issue.iid]($transformer.issueUrl$issue.iid)"
		}

		def section() {
			"#### $transformer.milestoneId"
		}

		def footer() {
			"See all issues at: [$transformer.milestoneId](${transformer.milestoneUrl})"
		}
	}


	static class Gitlab {

		String gitlabUrl
		String projectId
		String stone

		GitlabAPI api
		GitlabMilestone milestone
		GitlabProject project

		Gitlab(String... args) {

			gitlabUrl = args[0]
			def token = args[1]
			projectId = args[2]
			stone = args[3]

			api = GitlabAPI.connect(gitlabUrl, token)
			milestone = api.getMilestones(projectId)
					.find { it.title == stone }
			project = new GitlabProject()
			project.setId(milestone.projectId)
		}

		def labels() {
			api.getLabels(project)
		}

		def issues() {
			api.getIssues(project).stream()
					.filter { it.milestone != null }
					.filter { it.milestone.title == milestone.title }
					.collect(Collectors.toList()) as List<GitlabIssue>
		}

		def milestoneUrl() {
			gitlabUrl + projectId + GitlabMilestone.URL + "/" + milestone.iid
		}

		def issuesUrl() {
			gitlabUrl + projectId + GitlabIssue.URL + "/"
		}
	}

}