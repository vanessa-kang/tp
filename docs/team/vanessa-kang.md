## Vanessa Kang - Project Portfolio Page

## Project: PlanNUS

PlanNUS is a greenfield, CLI-based project which aims to solve the gap in undergraduate academic planning in NUS. PlanNUS aims to remove the need for Excel sheets and CAP calculator websites by bundling their functionalities, and more, into two key apps: the _Academic Planner_ and _CAP Calculator_.

The _Academic Planner_ assists students in planning out their academic calendar for the entire duration (4-5 years) of their NUS candidature. It provides students with a convenient interface to keep track of the modules that they have taken, or are planning to take, along with the associated semester and grade information. 

The _CAP Calculator_ then makes use of this existing data to calculate students' current CAP. This helps students to accurately monitor, and even forecast, their academic performance.

Given below are my contributions to the project.

<br>

- **Code contributed:** [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=vanessa&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=vanessa-kang&tabRepo=AY2021S1-CS2113T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
- **New Feature:** Added the ability to view all modules in the user's academic calendar.
  - What it does: Allows users to view all the modules - along with their associated grades, and sorted by semester - that are currently in their academic calendar.
  - Justification: This feature aids users greatly in the planning of their academic journey, as it allows them to have a clear overview of all the modules (and its associated information) that they have taken, or plan to take.
  - Highlights: While formatting the academic calendar for printing, we needed to consider how best to lay out the modules and its associated information in a straightforward manner, so that it would be a useful aid to module planning.
- **New Feature:** Added the ability to view the details of a specific module.
  - What it does: Allows users to view detailed information about a specific module, such as semesters offered, prerequisites, and preclusions.
  - Justification: Users who need to quickly look up crucial information about specific modules can do so within PlanNUS itself, eliminating the need to toggle between PlanNUS and an external search window.
  - Highlights: While formatting the module information for printing, two things needed to be taken into consideration: 1) what information students actually find useful, and 2) how to lay out the details in a clear and unambiguous manner. Otherwise, this feature would end up leaving students even more confused than before.
- **New Feature:** Added the ability to search for modules that contain a specific keyword.
  - What it does: Allows users to input a keyword, then searches the module database for modules that contain this keyword. Currently, it only searches based on module code, and displays only the first 10 results.
  - Justification: If users are unsure of the full code of a certain module, this feature would be a handy tool to jog their memory.
  - Highlights: 

- **Documentation:**
  - User Guide:
    - Added documentation for `details` and `search` ([#132](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/132))
    - Formatting and proofreading of User Guide ([#132](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/132), [#241](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/241), [#242](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/242))
  - Developer Guide:
    - Added documentation for `ModuleDetailsCommand` ([#173](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/173))
    - Added use cases for _Academic Planner_ features ([#236](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/236))
    - Added instructions for manual testing ([#245](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/245), [#289](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/289))
    - Formatting and proofreading of Developer Guide ([#242](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/242))
- **Team-based tasks:**
  - General bug fixes ([#238](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/238))
  - Alerted teammates to bugs that needed fixing (e.g. [#103](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/103), [#288](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/288))
- **Review contributions:**
  - PRs reviewed (with non-trivial review comments): 
    - iP: [#15](https://github.com/nus-cs2113-AY2021S1/ip/pull/15), [#154](https://github.com/nus-cs2113-AY2021S1/ip/pull/154)
    - tP: [#26](https://github.com/nus-cs2113-AY2021S1/tp/pull/26), [#68](https://github.com/nus-cs2113-AY2021S1/tp/pull/68), [PE dry run](https://github.com/vanessa-kang/ped/issues)





- **[Optional] Contributions to the User Guide (Extracts):**
- **[Optional] Contributions to the Developer Guide (Extracts):**



