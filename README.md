# GitlabMilestoneReport

This repository holds one groovy script to generate markdown reports of milestones from gitlab.

### Usage
Scheme:
```groovy 
groovy milestone-report.groovy [gitlab/url] [access token] [projectId] [milestoneId]
```
In action:
```groovy
groovy milestone-report.groovy https://gitlab.com/ abcdefghij... arturbosch/detekt M6
```

### Example

Generated report for my project 'detekt' for milestone M6:

#### M6

##### Documentation

- Rework Parameters for CLI section in readme - [#80](https://gitlab.com/arturbosch/detekt/issues/80)
- Update command line options in readme - [#74](https://gitlab.com/arturbosch/detekt/issues/74)

##### Work in progress

- Progress logging - [#69](https://gitlab.com/arturbosch/detekt/issues/69)
- Track analysis progress via #ktFiles - [#68](https://gitlab.com/arturbosch/detekt/issues/68)

##### defect

- Measure run time of detekt even if 'SmellBorder' is met - [#81](https://gitlab.com/arturbosch/detekt/issues/81)
- KtTreeCompiler considers only "kt" as file ending, which may led to crashed on other file endings eg ".detekt" - [#78](https://gitlab.com/arturbosch/detekt/issues/78)

##### feature

- Allow to save findings into a text file with --output - [#77](https://gitlab.com/arturbosch/detekt/issues/77)
- Allow to define code smell baseline where only new smells are shown - [#76](https://gitlab.com/arturbosch/detekt/issues/76)
- Fail gradle task on found issues - [#75](https://gitlab.com/arturbosch/detekt/issues/75)
- Move formatting rule set to own project - [#73](https://gitlab.com/arturbosch/detekt/issues/73)
- Progress logging - [#69](https://gitlab.com/arturbosch/detekt/issues/69)
- Track analysis progress via #ktFiles - [#68](https://gitlab.com/arturbosch/detekt/issues/68)

##### improvement

- Move Optional- rules to formatting and support autocorrecting - [#72](https://gitlab.com/arturbosch/detekt/issues/72)

##### release

- Rework Parameters for CLI section in readme - [#80](https://gitlab.com/arturbosch/detekt/issues/80)
- Update command line options in readme - [#74](https://gitlab.com/arturbosch/detekt/issues/74)


See all issues at: [M6](https://gitlab.com/arturbosch/detekt/milestones/M6)