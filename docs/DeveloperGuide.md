# Developer Guide

{:toc}

## Setting up PlanNUS

First and foremost, the following steps are assuming that you already have a [GitHub](github.com) account set up beforehand. Once this has been done, proceed to __fork__ this [repo](https://github.com/AY2021S1-CS2113T-F12-1/tp), and __clone__ the fork into your computer using [Sourcetree](<sourcetreeapp.com>) or any other _Git GUI_.

The _IDE_ to be used should contain the latest version of _Java_ as this is the main programming language for this application. Thus you are highly recommended to use Intellij IDEA.

The following are remaining steps to be taken to finish the set up:

1. Make sure that the version is configured as __JDK 11__.
2. When prompted, __import__ the project as a __Gradle project__ (could take minutes to complete).
3. Enter commands to ensure that PlanNUS functions as expected. You may refer to the _User Guide_ for valid commands.



## Design

### Architecture

![Architecture](./images/DeveloperGuide/Architecture.png)

The ***Architecture Diagram*** given above explains the high-level design of PlanNUS. Below is a quick overview of each component.



### Overview

#### PlanNus

`PlanNus` class contains the `main` and `run` method, which is responsible for

  * At launch

       * Loading all modules for AY2020/21 into PlanNUS

       * Loading previous save file into PlanNUS if available

       * Creation of entry point to available apps in PlanNUS

  * While running

       * Continuously prompt user for app selection

  * At shut down

       * Saving of user data into save file
       * Clean up methods where necessary



#### Global, Ui, Parser, Storage, Apps

* The `global` package contains classes, exceptions and objects that are required across the whole app. 
* The `ui` package contains the class that is responsible for sharing one `scanner` class across the whole app to prevent multiple IO streams
* The `parser` package contains the class that handles user's app selection
* The `storage` package handles loading and saving of user's data to a save file.
* Packages for Available apps such as Academic Planner and CAP Calculator are stored within `apps` package



### Project Structure

Each package in the PlanNUS as given above follows the following file structure where applicable:

* A functional class that acts as the entry point to that module
* A parser class that parses user input into executable commands by PlanNUS

* `commands`: A package that handles all executable commands given by parser
* `commons`: A package with the utilities and shared classes across the parent package
* `exceptions`: A package to handle all exceptions thrown across the parent package



The interaction within each package should ideally be as shown below.

![Project structure](C:\Users\Orion\Desktop\Git\CS2113T\tp\docs\images\DeveloperGuide\Project structure.png)

*Note that while this is the ideal case, packages such as* `global`, `parser` *and* `ui` *might not strictly follow this structure due to these package serving a different function altogether (Refer to the sections below for more details.)*

### Lifecycle of PlanNUS

The *sequence diagram* below shows how different packages and classes interact with each other throughout the whole lifecycle of PlanNUS.

![Packages Interaction](C:\Users\Orion\Desktop\Git\CS2113T\tp\docs\images\DeveloperGuide\Packages Interaction.png)

### Details

#### Global Component

Classes used by multiple components are in the `src.main.java.global` package.

#### Storage Component

**API** : `src.main.java.seedu.duke.storage`

#### Parser Component


## Implementation

### Academic Calendar Planner : Add Module feature

#### Proposed implementation

{Exact diagram and corresponding descriptions to be added}

The proposed add module command is facilitated by `AcademicPlannerParser`. It allows users to add modules into their
 `Academic Planner` by instantiating a new `PartialModule` object and adding it into the `userModuleList` 
 and `userModuleMap`. Both the list and hashmap are the _java API_, which are used by importing them. The `Person` object
 is used to encapsulate both `userModuleList` and `userModuleMap`.
 
Additionally, add module command extends the `Command` class and overrides its `execute()` command. An external class,
 `ModuleValidator` is called upon to validate the various parameters that the user has entered, as to only allow
 valid modules to be added to the user.
 
Given below is an example usage scenario and how add module command behaves at each step.

{DIAGRAM FOR STEP 1: INITIAL STATE}

**Step 1** : The user calls the add module command from the `AcademicPlannerParser`, which will initialise a 
`AddModuleCommand`. `AddModuleCommand`'s constructor takes in parameters of `ModuleLoader`, `Person`,`Scanner`, 
and `String`. Below is a table of what each parameter corresponds to in the state diagram of the program.

|Parameter|Corresponds to|Referred to as
|:---:|:---:|:---:
|`ModuleLoader`| Class representing all modules offered by NUS | `allModules`
|`Person`| Class representing current user's information | `currentPerson`
|`Scanner`| Class representing java's default scanner class | `in`
|`String` | Class representing the module code to be added | `moduleCode`
 
 {DIAGRAM FOR STEP 2: WITH FH AND LOGGER}
 
**Step 2** : `execute()` is called from the instance of `AddModuleCommand`. It can throw `AcademicException` 
or `IOException`. `FileHandler` and `Logger` classes from the _java API_ are instantiated to handle logging for the 
remainder of the `execute()` method. 


**Step 3** : `in` then reads in the next two lines of input, which is the user's input for the desired semester for the 
`moduleCode` and `moduleCode`'s grades.

{DIAGRAM FOR STEP 3:  }

**Step 4** : 

#### Design consideration

{Exact diagram and corresponding descriptions to be added}

### CAP Calculator features (i.e. current and set target)

#### Proposed implementation

{Exact diagram and corresponding descriptions to be added}

#### Design consideration

{Exact diagram and corresponding descriptions to be added}



## Documentation, logging, testing, configuration, dev-ops

__Documentation guide__

__Testing guide__

__Logging guide__

__Configuration guide__

__DevOps guide__



## Appendix: Requirements

### Product scope

__Target user profile:__

* has adequate level of familiarization with CLI applications
* requires a clear outlook of academic journey with the modules offered by NUS
* wants to keep track of his or her results and set target grades for the upcoming semester(s)
* prefers using desktop or laptop instead of other electronic devices

__Value proposition:__
Provides NUS undergraduates with a platform to keep track of their academic progress and explore other possibilities with the plethora of modules available. 



### User stories

| Version | As a ...                                                  | I want to ...                                                | So that I can ...                                            |
| ------- | --------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| v1.0    | fresh undergraduate                                       | visualize the modules in the recommended schedule and course requirements | better plan out my academic journey for the next 4-5 years in NUS |
| v1.0    | undergraduate with at least 1 semester of study completed | calculate my CAP easily                                      | forecast my own expected graduation CAP and if they match my expected CAP |
| v1.0    | undergraduate with at least 1 semester of study completed | print out a personalized list of modules taken so far and grades obtained | track my academic progression in NUS                         |
| v2.0    | user                                                      | find a to-do item by name                                    | locate a to-do without having to go through the entire list  |



### Use cases

__Use case 1: Add a module__

__MSS__

1. User enters `acadplan` in the main menu of PlanNUS

2. User enters the necessary command and module code 

3. PlanNUS prompts user to enter the remaining information

4. PlanNUS adds the module

   Use case ends.

__Extensions__

* 2a. The command entered is invalid.

  * 2a1. PlanNUS shows an error message.

  Use case ends.

* 2b. Module code entered is invalid.

  * 2b1. PlanNUS shows an error message.

  Use case ends.

* 3a. Invalid semester or grade is entered.

  * 3a1. PlanNUS shows an error message.

    Use case ends resumes at step 3.



{More to be added}

### Non-Functional Requirements

{More to be added}

### Glossary

{More to be added}



## Appendix: Instructions for manual testing

{More to be added}