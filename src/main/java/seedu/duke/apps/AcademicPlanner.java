package seedu.duke.apps;

import seedu.duke.moduledata.ModuleInitializer;
import seedu.duke.objects.Module;
import seedu.duke.objects.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AcademicPlanner {
    //CONSTANTS
    private final int STARTING_SEMESTER_INDEX = 1;
    private final int FINAL_SEMESTER_INDEX = 10;
    private final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    private final String ERROR_INVALID_SEMESTER_INDEX = "INVALID SEMESTER INDEX";
    private final String ERROR_INVALID_GRADE = "INVALID GRADE VALUE";
    private final String ERROR_NOT_OFFERED = " IS NOT OFFERED BY NUS";
    private final String ERROR_DUPLICATE_MOD = "You already have this mod on your calendar!";
    private final String ERROR_NOT_ADDED = "You have not added this module into your list yet";
    private final String ERROR_EDIT_OPTION = "Number entered does not correspond to any feature";
    private final String WELCOME_MESSAGE = "\nWelcome to Academic Planner!";
    private final String ADD_COMMAND = "ADD";
    private final String EDIT_COMMAND = "EDIT";
    private final String REMOVE_COMMAND = "REMOVE";
    private final String VIEW_COMMAND = "VIEW";
    private final String EXIT_COMMAND = "EXIT";
    private final String COMMANDS_LIST = "Available commands are: \n" +
            "  add <module code>\n" +
            "  edit <module code>\n" +
            "  remove <module code>\n" +
            "  view\n" +
            "  exit\n" +
            "Type a command to continue...";

    private ModuleInitializer allModules;
    private ArrayList<Module> modulesList;
    private HashMap<String,Module> modulesAddedMap;
    private Person currentPerson;

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

        while (!inputs[0].equals(EXIT_COMMAND)) {
            if (inputs[0].equals(ADD_COMMAND)) {
                addModule(scanner, inputs[1]);
            } else if (inputs[0].equals(EDIT_COMMAND)) {
                editModule(scanner, inputs[1]);
            } else if (inputs[0].equals(REMOVE_COMMAND)) {
                removeModule(inputs[1]);
            } else if (inputs[0].equals(VIEW_COMMAND)) {
//                printCalendar();
            } else {
                System.out.println(ERROR_INVALID_COMMAND);
            }
            printCommandsList();
            fullInput = scanner.nextLine().toUpperCase();
            inputs = fullInput.split(" ");
        }
        System.out.println("Thank you for using Academic Planner!");
    }

    /**
     * Adds a module to the user's academic calendar if it exists in ModuleDatum,
     * else does not add module into user's academic calendar.
     * Validates user's input semester and grade.
     * If either is invalid, does not add module into user's academic calendar.
     */
    private void addModule(Scanner in, String moduleCode) {
        try {
            if (checkIfModOfferedByNUS(moduleCode)) {
                if (checkIfModTaken(moduleCode)) {
                    System.out.println(ERROR_DUPLICATE_MOD);
                }

                System.out.println("Semester you plan to take " + moduleCode.toUpperCase() + "?");
                String userInput = in.nextLine();
                int semesterValue = Integer.parseInt(userInput);

                if (!checkValidSemester(semesterValue)) {
                    System.out.println(ERROR_INVALID_SEMESTER_INDEX);
                    return;
                }

                System.out.println("Grade received for " + moduleCode.toUpperCase() +"?");
                String gradeValue = in.nextLine();
                if (!checkValidGrade(gradeValue)) {
                    System.out.println(ERROR_INVALID_GRADE);
                    return;
                }

                int moduleCredit = getModuleCreditForModule(moduleCode);
                Module newModuleToAdd = new Module(moduleCode, semesterValue, gradeValue, moduleCredit);
                modulesList.add(newModuleToAdd);
                modulesAddedMap.put(moduleCode, newModuleToAdd);

                //Incrementing total MC regardless of SU
                currentPerson.setCurrentMC(currentPerson.getCurrentMC() + moduleCredit);

                //Incrementing total MC after SU only if module is not SU
                if (newModuleToAdd.getCAP() != -1.00) {
                    currentPerson.setCurrentMCAfterSU(currentPerson.getCurrentMCAfterSU() + moduleCredit);
                    double newMCxGrade = newModuleToAdd.getCAP() * moduleCredit;
                    currentPerson.setCurrentTotalMCxGrade(currentPerson.getCurrentTotalMCxGrade() + newMCxGrade);
                }
                System.out.println(newModuleToAdd.getModuleCode()
                        + " added into Semester " + semesterValue + ".");
            } else {
                System.out.println(moduleCode + ERROR_NOT_OFFERED);
            }
        } catch (Exception e) {
            System.out.println(ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Allows user to edit the module entry of his academic calendar.
     * Parameters allowed to change are semesterIndex or Grade.
     */
    private void editModule(Scanner in, String moduleCode) {
        try {
            if (checkIfModTaken(moduleCode)) {
                System.out.println("Enter the number corresponding to the feature you wish to edit:" +
                        "\n1) Semester\n2) Grade");
                String choice = in.nextLine();
                if (choice.equals("1")) {
                    System.out.println("Enter the new semester value: ");
                    String newValue = in.nextLine();
                    if (!checkValidSemester(Integer.parseInt(newValue))) {
                        System.out.println(ERROR_INVALID_SEMESTER_INDEX);
                        return;
                    }
                    for (Module item : modulesList) {
                        if (item.getModuleCode().equals(moduleCode)) {
                            item.setSemesterIndex(Integer.parseInt(newValue));
                            break;
                        }
                    }
                    modulesAddedMap.get(moduleCode).setSemesterIndex(Integer.parseInt(newValue));
                    System.out.println("Semester for " + moduleCode + " successfully updated!");
                } else if (choice.equals("2")) {
                    System.out.println("Enter the new grade: ");
                    String gradeValue = in.nextLine().toUpperCase();
                    if (!checkValidGrade(gradeValue)) {
                        System.out.println(ERROR_INVALID_GRADE);
                        return;
                    }

                    double oldCap;
                    double newCap;

                    System.out.println(moduleCode);

                    for (Module item : modulesList) {
                        if (item.getModuleCode().equals(moduleCode)) {
                            oldCap = item.getCAP();
                            item.setGrade(gradeValue);
                            newCap = item.getCAP();

                            if (oldCap == -1.00 && newCap != -1.00) {  //Case where previously was SU but new is not SU
                                currentPerson.setCurrentMCAfterSU(currentPerson.getCurrentMCAfterSU() + item.getModuleCredit());
                                double newMCxGrade = newCap * item.getModuleCredit();
                                currentPerson.setCurrentTotalMCxGrade(currentPerson.getCurrentTotalMCxGrade() + newMCxGrade);
                            }  else if (oldCap != -1.00 && newCap == -1.00) {   //Case where previously was not SU but now is SU
                                currentPerson.setCurrentMCAfterSU(currentPerson.getCurrentMCAfterSU() - item.getModuleCredit());
                                double MCxGradeToMinus = oldCap * item.getModuleCredit();
                                currentPerson.setCurrentTotalMCxGrade(currentPerson.getCurrentTotalMCxGrade() - MCxGradeToMinus);
                            } else if (oldCap != newCap) {  //Case where previously and new cap are not SU but not the same
                                double oldMCxGrade = oldCap * item.getModuleCredit();
                                double newMCxGrade = newCap * item.getModuleCredit();
                                double MCxGradeToSet = newMCxGrade - oldMCxGrade;
                                currentPerson.setCurrentTotalMCxGrade(currentPerson.getCurrentTotalMCxGrade() + MCxGradeToSet);
                            }
                            break;
                        }
                    }
                    modulesAddedMap.get(moduleCode).setGrade(gradeValue);


                    //Below output are for debugging purposes
                    System.out.println("Current total MC taken: " + currentPerson.getCurrentMC());
                    System.out.println("Current total MC x Grade: " + currentPerson.getCurrentTotalMCxGrade());
                    System.out.println("Current total MC after SU: " + currentPerson.getCurrentMCAfterSU());
                    System.out.println("Current CAP: " + currentPerson.getCurrentTotalMCxGrade() / (double) currentPerson.getCurrentMCAfterSU());


                    System.out.println("Grade for " + moduleCode + " successfully updated!");
                } else {
                    System.out.println(ERROR_EDIT_OPTION);
                }
            } else {
                System.out.println(ERROR_NOT_ADDED);
            }
        } catch (Exception e) {
            System.out.println(ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Removes moduleCode from user's academic calendar if it exists,
     * else prompts user of error.
     */
    private void removeModule(String moduleCode) {
        try {
            if (checkIfModTaken(moduleCode)) {
                modulesAddedMap.remove(moduleCode);
                for (Module item : modulesList) {
                    if (item.getModuleCode().equals(moduleCode)) {
                        System.out.println(item.getModuleCode() + " has been removed from the list");

                        //Decreasing total MC regardless of SU
                        currentPerson.setCurrentMC(currentPerson.getCurrentMC() - item.getModuleCredit());

                        //Decreasing total MC after SU only if module is not SU
                        if (item.getCAP() != -1.00) {
                            currentPerson.setCurrentMCAfterSU(currentPerson.getCurrentMCAfterSU() - item.getModuleCredit());
                            double MCxGradeToMinus = item.getCAP() * item.getModuleCredit();
                            currentPerson.setCurrentTotalMCxGrade(currentPerson.getCurrentTotalMCxGrade() - MCxGradeToMinus);
                        }

                        modulesList.remove(item);
                        break;
                    }
                }

                //Below output are for debugging purposes
                System.out.println("Current total MC taken: " + currentPerson.getCurrentMC());
                System.out.println("Current total MC x Grade: " + currentPerson.getCurrentTotalMCxGrade());
                System.out.println("Current total MC after SU: " + currentPerson.getCurrentMCAfterSU());
                System.out.println("Current CAP: " + currentPerson.getCurrentTotalMCxGrade() / (double) currentPerson.getCurrentMCAfterSU());
            } else {
                System.out.println(ERROR_NOT_ADDED);
            }
        } catch (Exception e) {
            System.out.println(ERROR_INVALID_COMMAND);
        }
    }
    
    /**
     * Returns true if module code is offered by NUS,
     * else returns false.
     * @param moduleCode input module code
     * @return boolean of module code in ModuleDatum
     */
    private boolean checkIfModOfferedByNUS(String moduleCode) {
        return (allModules.getModuleMap().get(moduleCode) > -1);
    }

    /**
     * Returns tru if module is in the user's academic calendar
     * else returns false.
     *
     * @param moduleCode moduleCode to check
     * @return boolean
     */
    private boolean checkIfModTaken(String moduleCode) {
        return (modulesAddedMap.containsKey(moduleCode));
    }

    /**
     * Returns true if semsesterIndex is a valid semesterIndex
     * else returns false
     *
     * @param semesterIndex semesterIndex to check
     * @return false
     */
    private boolean checkValidSemester (int semesterIndex) {
        return (semesterIndex >= STARTING_SEMESTER_INDEX && semesterIndex <= FINAL_SEMESTER_INDEX);
    }

    /**
     * Returns true if grade is a Grade option offered by NUS,
     * else returns false.
     *
     * @param grade grade to check
     * @return boolean
     */
    private boolean checkValidGrade(String grade) {
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
     * Function to return module credits for module code
     *
     * @param moduleCode string of module code
     * @return int of module credit
     */
    private int getModuleCreditForModule(String moduleCode) {
        int mapIndex = allModules.getModuleMap().get(moduleCode);
        return allModules.getModuleFullDetails()[mapIndex].getModuleCredit();
    }
}
