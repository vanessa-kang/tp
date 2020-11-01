# Jerrold Lam - Project Portfolio Page

## Overview

PlanNUS is a greenfield, CLI-based project which aims to solve the gap in undergraduate academic planning in NUS.
With the Academic Calendar Planner, PlanNUS will be able to assist undergraduate students in forecasting their academic journey for their 4 or 5 years in NUS.
With the CAP Calculator , PlanNUS will be able to tell you your current CAP and forecast future grades needed to achieve your target CAP.

### Summary of Contributions

* [Code Contributed](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jerroldlam "Link to contributed code")
* **New Feature:** Added the ability to add modules into their unique academic planner.
    * What it does: Allows the user to add a module which is offered by NUS into their academic calendar, one at a time.
    * Justification: This feature is one of the core features of PlanNUS and forms the basis of which PlanNUS is built upon.
    * Highlights: The implementation of this feature needed to be foolproof and complex to implement as it required multiple interactions with multiple classes in order to validate and properly store the required variables.
* **New Feature:** Added the ability to view modules from a specific semester of the user's academic calendar.
    * What it does: Allows the user to view all modules, and their grades from a specific semester that they input.
    * Justification: This feature improves PlanNUS significantly as the user need not be overloaded with the information of their whole academic calendar should they only want to view a semester's information.
    * Highlights: This enhancement affects the existing view command and needed to be neatly and coherently integrated with the current view command. This required in depth analysis of the possible integration solutions to make the more intuitive program flow for the ease of use for the user.
* **New Functionality:** Created the main object classes to be used in PlanNUS.
    * What it does: Through the `Person` and `PartialModule` object classes, PlanNUS is able to store required variables efficiently to be utilised throughout the program.
    * Justification: By encapsulating the variables and creating a facade for the private variables, it reduces the risk of a rogue function modifying the variables. This would make our program more robust and reduces the opportunity for bugs to appear.
    * Highlights: The implementation of these object classes need to be thought of at a high level as it forms the foundation of the functionality of PlanNUS. This meant that uses cases needed to be thought out to account for the various usage type. These classes also need to be easily modifiable as the classes can be easily augmented to encapsulate more data should the program require.
* **New Functionality:** Created the parser class to be used in the academic planner app.
    * What it does: Processes user input data and returns the correct command to be executed.
    * Justification: Raises the coding standard of the PlanNUS code base as more object-oriented-programming can be implemented.
    * Highlights: The parser needs to be able to handle all sorts of output and handle them gracefully without fail. Hence, much thought was put into designing the parser to account for the various possible different inputs.
    * Credits: Referenced from teammate Khenus's app parser.
* **New Functionality:** Added `ModuleValidator` class to easily validate inputs from user.
    * What it does: Easily validates critical information such as Module Codes, Semester indexes and Grade values.
    * Justification: This enhances the credibility of PlanNUS by rejecting random inputs, maintaining the data integrity of the information that will be stored in PlanNUS.
    * Highlights: As there are many parameters that need to be verified, careful implementation of the verification methods is conducted. This forms the core of PlanNUS' data integrity checks, as a mistake here would result in unintended values to be accepted as valid.
* **New Functionality:** Added `LoggingTool` class to easily initialise a `Logger` for use within PlanNUS.
    * What it does: Easy initialisation of `Logger` to assist in logging the runtime execution of PlanNUS.
    * Justification: Logging will be widely used in PlanNUS to assist in tracking its events, hence by creating this class, developers can easily initialise a standardised logger which will output `.log` files for the developer to understand what is going on in the program. 
    * Highlights: Following careful consideration of how logging will be used, the parameters are set up to allow different codes to easily log without requiring a huge chunk of code to initialise it.
* **Documentation**
    * User Guide
        * Added documentation for `AddModuleCommand`.
        * Added Overview and introduction to user guide.
        * Added appendix for relevant information regarding NUS modules and semester indexes.
    * Developer Guide
        * Added documentation for `AddModuleCommand` and its flow in the program.
        * Added details of the `Ui` class
* **Team-Based Tasks**
    * Maintained issue tracker on the team's repository.
    * Made necessary code quality enhancements.
    * Resolved coding standard violations.
    * Made numerous bug fixes for the code and found bugs to be resolved.
        
        
        
