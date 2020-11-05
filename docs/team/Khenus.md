# Khenus Tan - Project Portfolio Page

## Overview

PlanNUS is a greenfield, CLI-based project which aims to solve the gap in undergraduate academic planning in NUS. With the Academic Calendar Planner, PlanNUS will be able to assist undergraduate students in forecasting their academic journey for their 4 or 5 years in NUS. With the CAP Calculator , PlanNUS will be able to tell you your current CAP and forecast future grades needed to achieve your target CAP.

### Summary of Contributions

* Code contributed: [Reposense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=khenus&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other "Link to contributed code")

* **New Feature:** Added the ability to navigate between different apps within PlanNUS.
    * What it does: Allow users to select between Academic Planner or CAP Calulator at any point while using PlanNus.
    * Justification: This feature allow users to fully utilise all other features offered by PlanNUS with great ease. It also serve as an entry point for the user when using PlanNUS.
    * Highlights: This feature handles the flow of data within PlanNUS to provide users with the ability to toggle between apps seemlessly. It also ensures proper allocation and usage of memory space to prevent unexpected crashes due to memory leak.
    

* **New Functionality:** Created a JavaScript program to clean up all module informatiom retrieve from NUSMODS API.
    * What it does: Removal of extra information and reformating needed information to allow for ease of conversion from Javascript Object Notation (JSON) to Plain Old Java Object (POJO).
    * Justification: The creation of this program is to assist in removing irrelavant data and reformatting nested objects from all 12436 modules within the JSON file retrieved from NUSMODS API to improve both spatial and temporal efficiencies during the eventual conversion of JSON into POJO in PlanNUS.
    * Highlights: This program allows for the formatting of specific output fields, removal of irrelavant information from the final list, counting of total number of modules offered, finding the length of the longest module name, the minimum MC across all modules offered and searching of a specific module within the final list.

* **New Functionality:** Creation of `ModuleLoader` class
    * What it does: The `ModuleLoader` class allows for the creation of a Plain Old Java Object (POJO) and HashMap from the formatted Javascript Object Notatiom (JSON) file containing information for all modules offered in NUS using GSON library from Google.
    * Justification: This class creates an ArrayList for fellow developers to access module information when needed. 
    * Highlights: 

* **New Functionality:** 
    * What it does:
    * Justification: 
    * Highlights:

* Formatting JSON object data retrieved from NUSMODs with JavaScript
* Loading all module data into PlanNUS
* Handles all file structure in PlanNUS
* Creation and maintenance of updating CAP after modifying each module
