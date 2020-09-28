package apps;

import moduledata.ModuleInitializer;
import objects.Module;
import objects.Person;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.ArrayList;
import java.util.Comparator;
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
//                editModule(scanner, inputs[1]);
            } else if (inputs[0].equals(REMOVE_COMMAND)) {
//                removeModule(inputs[1]);
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
