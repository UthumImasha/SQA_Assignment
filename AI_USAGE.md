# AI Usage Declaration

AI tooling was used during the development of this project, in the way modern development teams use coding assistants. This file declares where it helped and what I did myself, as required by the assignment brief.

## Tool

- Claude (Anthropic), used as a coding and writing assistant during development

## Where AI Assisted

- Scaffolding the Maven project and the Page Object Model structure
- Generating boilerplate and repetitive code, such as page object locator declarations and utility classes
- Drafting documentation in `docs/` based on my inputs, decisions and the live analysis of the site
- Debugging support: interpreting Selenium exceptions (for example distinguishing ElementNotInteractable from ElementClickIntercepted) and suggesting stable locator and wait strategies

## What I Did Myself

- Chose the target website and approved the automation scenario selection, priorities and justifications
- Reviewed the files in every commit, and made and pushed every commit personally
- Ran the test suite locally throughout development, including rerunning failures to separate demo-site flakiness from real defects
- Reviewed and adopted the design decisions recorded in the framework design document
- Prepared the report and recorded the reflection video

## Verification

Everything in this repository was reviewed, executed and verified on my machine.
