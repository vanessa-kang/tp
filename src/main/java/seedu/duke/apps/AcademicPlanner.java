package seedu.duke.apps;

import seedu.duke.exceptions.AcademicException;
import seedu.duke.moduledata.ModuleInitializer;
import seedu.duke.objects.Module;
import seedu.duke.objects.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AcademicPlanner {

    private static final int STARTING_SEMESTER_INDEX = 1;
    private static final int FINAL_SEMESTER_INDEX = 10;
    private static final int COMMAND_INDEX = 0;
    private static final int MODULE_CODE_INDEX = 1;
    private static final int FROM_ADD = 1;
    private static final int FROM_EDIT = 2;
    private static final int FROM_REMOVE = 3;
    private static final String COMMAND_REGEX = " ";
    private static final String EDIT_SEMESTER = "1";
    private static final String EDIT_GRADE = "2";
    private static final String PROMPT_NEW_SEMESTER_VALUE = "Enter the new semester value: ";
    private static final String PROMPT_NEW_GRADE = "Enter the new grade: ";
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to Academic Planner Main Menu.";
    private static final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    private static final String ERROR_INVALID_SEMESTER_INDEX = "INVALID SEMESTER INDEX";
    private static final String ERROR_INVALID_GRADE = "INVALID GRADE VALUE";
    private static final String ERROR_NOT_OFFERED = " IS NOT OFFERED BY NUS";
    private static final String ERROR_DUPLICATE_MOD = "You already have this mod on your calendar!";
    private static final String ERROR_NOT_ADDED = "You have not added this module into your list yet";
    private static final String ERROR_EDIT_OPTION = "Number entered does not correspond to any feature";
    private static final String WELCOME_MESSAGE = "\nWelcome to Academic Planner!";
    private static final String ADD_COMMAND = "ADD";
    private static final String EDIT_COMMAND = "EDIT";
    private static final String REMOVE_COMMAND = "REMOVE";
    private static final String VIEW_COMMAND = "VIEW";
    private static final String EXIT_COMMAND = "EXIT";
    private static final String EXIT_MESSAGE = "Thank you for using Academic Planner!";
    private static final String COMMANDS_LIST = "Available commands are: \n"
            + "  add <module code>\n"
            + "  edit <module code>\n"
            + "  remove <module code>\n"
            + "  view\n"
            + "  exit\n"
            + "Type a command to continue...";
    private static final String VALID_GRADES = "Valid grades are: \n"
            + "Letter Grades: A+, A, B+, B, B-, C+, C, D+, D, F\n"
            + "Special Grades: CS, CU, S, U, W, IC, IP, AUD, WU, EXE\n"
            + "If you have yet to have a grade for the module: NT";
    private static final String VALID_SEMESTERS = "Valid semesters are integers from 1 to 10, inclusive";
    public static final String PROMPT_EDIT_CHOICES = "Enter the number corresponding to the feature you wish to edit:\n"
            + "1) Semester\n"
            + "2) Grade";

    private final ModuleInitializer allModules;
    private final ArrayList<Module> modulesList;
    private final HashMap<String,Module> modulesAddedMap;
    private final Person currentPerson;

    public AcademicPlanner(ModuleInitializer allModules, Person currentPerson) {
        this.allModules = allModules;
        this.modulesList = currentPerson.getModulesList();
        this.modulesAddedMap = currentPerson.getModulesAddedMap();
        this.currentPerson = currentPerson;
    }

    private void printCommandsList() {
        System.out.println(COMMANDS_LIST);
    }

    public void planner() {
        System.out.println(WELCOME_MESSAGE);
        printCommandsList();
        Scanner scanner = new Scanner(System.in);
        String fullInput = scanner.nextLine().toUpperCase();
        String[] inputs = fullInput.split(" ");

        while (!inputs[COMMAND_INDEX].equals(EXIT_COMMAND)) {
            try {
                switch (inputs[COMMAND_INDEX]) {
                case ADD_COMMAND:
                    addModule(scanner, inputs[MODULE_CODE_INDEX]);
                    break;
                case EDIT_COMMAND:
                    editModule(scanner, inputs[MODULE_CODE_INDEX]);
                    break;
                case REMOVE_COMMAND:
                    removeModule(inputs[MODULE_CODE_INDEX]);
                    break;
                case VIEW_COMMAND:
                    // printCalendar();
                    break;
                default:
                    System.out.println(ERROR_INVALID_COMMAND);
                    System.out.println(EXITING_CURRENT_COMMAND);
                }
            } catch (AcademicException e) {
                System.out.println(ERROR_INVALID_COMMAND);
                System.out.println(EXITING_CURRENT_COMMAND);
            }
            printCommandsList();
            fullInput = scanner.nextLine().toUpperCase();
            inputs = fullInput.split(COMMAND_REGEX);
        }
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Adds a module to the user's academic calendar if it exists in ModuleDatum,
     * else does not add module into user's academic calendar.
     * Validates user's input semester and grade.
     * If either is invalid, does not add module into user's academic calendar.
     *
     * @param in scanner class
     * @param moduleCode string of module code
     */
    private void addModule(Scanner in, String moduleCode) throws AcademicException {
        if (!isModOfferedByNus(moduleCode)) {
            throw new AcademicException(moduleCode + ERROR_NOT_OFFERED);
        }

        if (isModTakenByUser(moduleCode)) {
            throw new AcademicException(ERROR_DUPLICATE_MOD);
        }

        System.out.println("Semester you plan to take " + moduleCode.toUpperCase() + "?");
        System.out.println(VALID_SEMESTERS);
        String userInput = in.nextLine();

        int semesterValue;
        try {
            semesterValue = Integer.parseInt(userInput);
        } catch (Exception e) {
            throw new AcademicException(ERROR_INVALID_COMMAND);
        }

        if (!isValidSemester(semesterValue)) {
            throw new AcademicException(ERROR_INVALID_SEMESTER_INDEX);
        }

        System.out.println("Grade received for " + moduleCode.toUpperCase() + "?");
        System.out.println(VALID_GRADES);
        String gradeValue = in.nextLine();

        if (!isValidGrade(gradeValue)) {
            throw new AcademicException(ERROR_INVALID_GRADE);
        }
        int moduleCredit = getModuleCreditForModule(moduleCode);
        addModuleToUser(moduleCode, semesterValue, gradeValue, moduleCredit);
    }

    /**
     * Creates a new instance of a module of the parameters and adds it into the hashmap and array list of user modules.
     *
     * @param moduleCode module to be added
     * @param semesterValue semester that module is taken in
     * @param gradeValue grade achieved for module
     * @param moduleCredit module's credit weightage
     */
    private void addModuleToUser(String moduleCode, int semesterValue, String gradeValue, int moduleCredit) {
        Module newModuleToAdd = new Module(moduleCode, semesterValue, gradeValue, moduleCredit);
        modulesList.add(newModuleToAdd);
        modulesAddedMap.put(moduleCode, newModuleToAdd);
        updateCap(FROM_ADD, newModuleToAdd);
        System.out.println(newModuleToAdd.getModuleCode()
                + " added into Semester " + semesterValue + ".");
    }

    /**
     * Update Cap after every change in current module list.
     *
     * @param type An int storing the type of function calling update cap
     * @param currentModule A Module object storing current module
     * @param caps An optional number of double storing old cap and new cap (If you send in old, must send in new also)
     */
    //TODO Look into throwing error and short circuiting this command
    private void updateCap(int type, Module currentModule, double... caps) {
        // Caps is an array, 0 being oldCap, 1 being newCap
        if (type == FROM_ADD) {
            //Incrementing total MC regardless of SU
            int moduleCredit = currentModule.getModuleCredit();
            currentPerson.setCurrentMc(currentPerson.getCurrentMc() + moduleCredit);

            //Incrementing total MC after SU only if module is not SU
            if (currentModule.getCap() != -1.00) {
                currentPerson.setCurrentMcAfterSU(currentPerson.getCurrentMcAfterSU() + moduleCredit);
                double newMCxGrade = currentModule.getCap() * moduleCredit;
                currentPerson.setCurrentTotalMcxGrade(currentPerson.getCurrentTotalMcxGrade() + newMCxGrade);
            }
        } else if (type == FROM_REMOVE) {
            //Decreasing total MC regardless of SU
            currentPerson.setCurrentMc(currentPerson.getCurrentMc()
                    - currentModule.getModuleCredit());

            //Decreasing total MC after SU only if module is not SU
            if (currentModule.getCap() != -1.00) {
                currentPerson.setCurrentMcAfterSU(currentPerson.getCurrentMcAfterSU()
                        - currentModule.getModuleCredit());
                double mcxGradeToMinus = currentModule.getCap() * currentModule.getModuleCredit();
                currentPerson.setCurrentTotalMcxGrade(currentPerson.getCurrentTotalMcxGrade()
                        - mcxGradeToMinus);
            }
        } else if (type == FROM_EDIT) {
            if (caps[0] == -1.00 && caps[1] != -1.00) {
                //Case where previously was SU but new is not SU
                currentPerson.setCurrentMcAfterSU(currentPerson.getCurrentMcAfterSU()
                        + currentModule.getModuleCredit());
                double newMCxGrade = caps[1] * currentModule.getModuleCredit();
                currentPerson.setCurrentTotalMcxGrade(currentPerson.getCurrentTotalMcxGrade()
                        + newMCxGrade);

            }  else if (caps[0] != -1.00 && caps[1] == -1.00) {
                //Case where previously was not SU but now is SU
                currentPerson.setCurrentMcAfterSU(currentPerson.getCurrentMcAfterSU()
                        - currentModule.getModuleCredit());
                double mcxGradeToMinus = caps[0] * currentModule.getModuleCredit();
                currentPerson.setCurrentTotalMcxGrade(currentPerson.getCurrentTotalMcxGrade()
                        - mcxGradeToMinus);

            } else if (caps[0] != caps[1]) {
                //Case where previously and new cap are not SU but not the same
                double oldMCxGrade = caps[0] * currentModule.getModuleCredit();
                double newMCxGrade = caps[1] * currentModule.getModuleCredit();
                double mcxGradeToSet = newMCxGrade - oldMCxGrade;
                currentPerson.setCurrentTotalMcxGrade(currentPerson.getCurrentTotalMcxGrade()
                        + mcxGradeToSet);
            }
        }
    }

    /**
     * Allows user to edit the module entry of his academic calendar.
     * Parameters allowed to change are semesterIndex or Grade.
     *
     * @param in scanner
     * @param moduleCode module code to edit
     */
    private void editModule(Scanner in, String moduleCode) throws AcademicException {
        try {
            if (isModTakenByUser(moduleCode)) {
                System.out.println(PROMPT_EDIT_CHOICES);
                String choice = in.nextLine();

                if (choice.equals(EDIT_SEMESTER)) {
                    editModuleSemester(in, moduleCode);
                } else if (choice.equals(EDIT_GRADE)) {
                    editModuleGrade(in, moduleCode);
                } else {
                    throw new AcademicException(ERROR_EDIT_OPTION);
                }
            } else {
                throw new AcademicException(ERROR_NOT_ADDED);
            }
        } catch (Exception e) {
            throw new AcademicException(ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Changes the current grade of module to the input of the user.
     * Module must exist in user's list and hashmap
     *
     * @param in scanner
     * @param moduleCode code of module to edit the grade
     * @throws AcademicException invalid grade
     */
    private void editModuleGrade(Scanner in, String moduleCode) throws AcademicException {
        System.out.println(PROMPT_NEW_GRADE);
        String gradeValue = in.nextLine().toUpperCase();

        if (!isValidGrade(gradeValue)) {
            throw new AcademicException(ERROR_INVALID_GRADE);
        }

        System.out.println(moduleCode);
        updateModuleGrade(moduleCode, gradeValue);
        System.out.println("Grade for " + moduleCode + " successfully updated!");
    }

    /**
     * Updates user's module with new grade and updates user's Cap.
     *
     * @param moduleCode module to edit
     * @param gradeValue grade to edit to
     */
    private void updateModuleGrade(String moduleCode, String gradeValue) {
        for (Module item : modulesList) {
            if (item.getModuleCode().equals(moduleCode)) {
                double oldCap = item.getCap();
                item.setGrade(gradeValue);
                double newCap = item.getCap();
                updateCap(FROM_EDIT, item, oldCap, newCap);
                break;
            }
        }
        modulesAddedMap.get(moduleCode).setGrade(gradeValue);
    }

    /**
     * Edits module semester taken when module is in user's list.
     * Module must exist in user's module list and hashmap.
     *
     * @param in scanner
     * @param moduleCode module to edit
     * @throws AcademicException invalid semester index
     */
    private void editModuleSemester(Scanner in, String moduleCode) throws AcademicException {
        System.out.println(PROMPT_NEW_SEMESTER_VALUE);
        String newValue = in.nextLine();

        if (!isValidSemester(Integer.parseInt(newValue))) {
            throw new AcademicException(ERROR_INVALID_SEMESTER_INDEX);
        }

        updateModuleSemester(moduleCode, newValue);
        modulesAddedMap.get(moduleCode).setSemesterIndex(Integer.parseInt(newValue));
        System.out.println("Semester for " + moduleCode + " successfully updated!");
    }

    /**
     * Finds the module and updates the semester taken.
     *
     * @param moduleCode module to edit
     * @param newValue new semester index
     */
    private void updateModuleSemester(String moduleCode, String newValue) {
        for (Module item : modulesList) {
            if (item.getModuleCode().equals(moduleCode)) {
                item.setSemesterIndex(Integer.parseInt(newValue));
                return;
            }
        }
    }

    /**
     * Removes moduleCode from user's academic calendar if it exists,
     * else prompts user of error.
     */
    private void removeModule(String moduleCode) throws AcademicException {
        try {
            if (isModTakenByUser(moduleCode)) {
                modulesAddedMap.remove(moduleCode);
                removeModuleFromUserModuleList(moduleCode);
            } else {
                throw new AcademicException(ERROR_NOT_ADDED);
            }
        } catch (Exception e) {
            System.out.println(ERROR_INVALID_COMMAND);
            System.out.println(EXITING_CURRENT_COMMAND);
        }
    }

    /**
     * Removes module from user's module list.
     * Module must exist in user's module list.
     *
     * @param moduleCode module to remove.
     */
    private void removeModuleFromUserModuleList(String moduleCode) {
        for (Module item : modulesList) {
            if (item.getModuleCode().equals(moduleCode)) {
                System.out.println(item.getModuleCode() + " has been removed from the list");
                updateCap(FROM_REMOVE, item);
                modulesList.remove(item);
                return;
            }
        }
    }

    /**
     * Returns true if module code is offered by NUS,
     * else returns false.
     * @param moduleCode input module code
     * @return boolean of module code in ModuleDatum
     */
    private boolean isModOfferedByNus(String moduleCode) {
        return (allModules.getModuleMap().get(moduleCode) > -1);
    }

    /**
     * Returns true if module is in the user's academic calendar,
     * else returns false.
     *
     * @param moduleCode moduleCode to check
     * @return boolean
     */
    private boolean isModTakenByUser(String moduleCode) {
        return (modulesAddedMap.containsKey(moduleCode));
    }

    /**
     * Returns true if semsesterIndex is a valid semesterIndex,
     * else returns false.
     *
     * @param semesterIndex semesterIndex to check
     * @return false
     */
    private boolean isValidSemester(int semesterIndex) {
        return (semesterIndex >= STARTING_SEMESTER_INDEX && semesterIndex <= FINAL_SEMESTER_INDEX);
    }

    /**
     * Returns true if grade is a Grade option offered by NUS,
     * else returns false.
     *
     * @param grade grade to check
     * @return boolean
     */
    private boolean isValidGrade(String grade) {
        switch (grade.toUpperCase()) {
        case "A+":
            //Fallthrough
        case "A":
            //Fallthrough
        case "A-":
            //Fallthrough
        case "B+":
            //Fallthrough
        case "B":
            //Fallthrough
        case "B-":
            //Fallthrough
        case "C+":
            //Fallthrough
        case "C":
            //Fallthrough
        case "D+":
            //Fallthrough
        case "D":
            //Fallthrough
        case "F":
            //Fallthrough
        case "CS":           //Completed Satisfactorily
            //Fallthrough
        case "CU":           //Completed Unsatisfactorily
            //Fallthrough
        case "S":            //Satisfactory
            //Fallthrough
        case "U":            //Unsatisfactory
            //Fallthrough
        case "W":            //Withdrawn
            //Fallthrough
        case "IC":           //Incomplete
            //Fallthrough
        case "IP":           //In progress
            //Fallthrough
        case "AUD":          //Audit
            //Fallthrough
        case "WU":           //Withdrawn from University
            //Fallthrough
        case "EXE" :         //Exempted
            //Fallthrough
        case "NT":           //Not taken
            return true;
        default:
            return false;
        }
    }

    /**
     * Function to return module credits for module code.
     *
     * @param moduleCode string of module code
     * @return int of module credit
     */
    private int getModuleCreditForModule(String moduleCode) {
        int mapIndex = allModules.getModuleMap().get(moduleCode);
        return allModules.getModuleFullDetails()[mapIndex].getModuleCredit();
    }
}
