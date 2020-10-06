# User Guide for PlanNus

## Introduction

PlaNUS is a greenfield project which aims to solve the gap in undergraduate academic planning in NUS. 

## Table of contents

* [Quick Start](#quick-start)
* [Features](#features)
    * [Academic Calendar Planner](#academic-calendar-planner)
        * [Add Module](#adding-a-module-to-your-academic-calendar-add)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `PlanNUS` from [here](https://github.com/AY2021S1-CS2113T-F12-1/tp/releases "PlanNUS releases").
1. Run the `PlanNus.jar` file.

## Features 

1. Academic Calendar Planner
    1. `Add` Module to Calendar
    1. `Edit` Module in Calendar
    1. `Remove` Module from Calendar
    1. `Print` Calendar
1. Academic Calculator
    1. `Get` CAP
    1. `Target` CAP

## Academic Calendar Planner

This is the part of PlanNUS which handles the crafting of your personalised Academic Calendar for your 4 or 5 years of studies at NUS!

### Adding a module to your academic calendar: `add`

Adds a new module to your academic calendar

Format: `add <MODULE_CODE>`

* The `<MODULE_CODE>` must be a module that is offered by NUS. For more info on which modules are offered by NUS, visit [here](https://nusmods.com/modules?sem[0]=1&sem[1]=2&sem[2]=3&sem[3]=4 "NUSMODS").
* The `<MODULE_CODE>` must be not already be in your Planner. (No duplicate modules allowed)

If successful, the Academic Planner will prompt you of the semester you plan to take `<MODULE_CODE>`. A valid semester must be entered.

Format: `<SEMESTER>`

* The `<SEMESTER>` must be an integer from 1 to 10.

If successful, the Academic Planner will prompt you of the grade you achieved for `<MODULE_CODE>`. A valid grade must be entered.

Format: `<GRADE>`

* The `<GRADE>` must be a valid grade given by NUS or NT if `<MODULE_CODE>` has not been taken. 

**Example of proper usage:**

Example 1 : Module has already been taken and have been graded by NUS
```
add CS1231
Semester you plan to take CS1231?
1
Grade received for CS1231?
B+
CS1231 added into Semester 1.
```

Example 2 : Module has not been taken yet or not yet graded 
```
add CS2113
Semester you plan to take CS2113?
3
Grade received for CS2113?
NT
CS2113 added into Semester 3.
```

**Examples of messages when an incorrect input is given**

Example 1 : `<MODULE_CODE>` is not offered by NUS.

```
INSERT ERROR
```

Example 2 : `<MODULE_CODE>` is already in your Academic Planner.

```
INSERT ERROR
```

Example 3 : `<SEMESTER>` is out of range.

```
INSERT ERROR
```

Example 4 : `<GRADE>` is not a valid grade.

```
INSERT ERROR
```

Refer to [FAQ](#faq) for more information.

## FAQ

Question Type|Question|Answer
:---:|---|---
General | How do I check which modules are offered by NUS? | For more info on which modules are offered by NUS, visit [here](https://nusmods.com/modules?sem[0]=1&sem[1]=2&sem[2]=3&sem[3]=4 "NUSMODS").
General | What is a valid semester index? | For the purpose of this planning software, we have come to a decision to limit the maximum number of semesters an undergraduate can take to 10, which is 5 academic years. The notation they are represented by is by an integer of value 1 to 10. For a fresh undergraduate, they will begin at semester index of 1. For a year 3 student who is currently in semester 1, the semester index will be 5.
General | What is a valid grade? | **Letter Grades**: A+, A, B+, B, B-, C+, C, D+, D, F , **Special Grades**: CS, CU, S, U, W, IC, IP, AUD, WU, EXE, **If you have yet to have a grade for the module**: NT

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add module to Academic Calendar Planner : `add <MODULE_CODE>`
