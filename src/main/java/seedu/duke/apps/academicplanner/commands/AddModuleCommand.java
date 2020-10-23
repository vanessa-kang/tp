package seedu.duke.apps.academicplanner.commands;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.academicplanner.commons.AddUtils;
import seedu.duke.apps.academicplanner.commons.ModuleValidator;
import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.global.Command;
import seedu.duke.global.LoggingTool;
import seedu.duke.global.objects.Person;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author jerroldlam
/**
 * Class representing an add module command from the academic planner.
 */
public class AddModuleCommand extends Command {
    private static final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    private static final String ERROR_INVALID_SEMESTER_INDEX = "INVALID SEMESTER INDEX";
    private static final String ERROR_INVALID_GRADE = "INVALID GRADE VALUE";
    private static final String ERROR_NOT_OFFERED = " IS NOT OFFERED BY NUS";
    private static final String ERROR_DUPLICATE_MOD = "You already have this mod on your calendar!";
    private static final String VALID_GRADES = "Valid grades are:\n"
            + "\tLetter Grades: A+, A, A-, B+, B, B-, C+, C, D+, D, F\n"
            + "\tSpecial Grades: CS, CU, S, U, W, IC, IP, AUD, WU, EXE\n"
            + "\tIf you have yet to have a grade for the module: NT";
    private static final String VALID_SEMESTERS = "\tValid semesters are integers from 1 to 10, inclusive";
    private static final String LOG_FILE_NAME = "AddModuleCommand.log";
    private static final String LOGGER_NAME = "AddModuleCommand";

    private static Logger logger;
    private static FileHandler fh;
    private AddUtils addUtils;
    private ModuleValidator moduleValidator;
    private Scanner in;
    private String moduleCode;

    /**
     * Default constructor for Add Module command.
     *
     * @param allModules all modules offered by NUS
     * @param currentPerson current user
     * @param in scanner
     * @param moduleCode module code
     */
    public AddModuleCommand(ModuleLoader allModules, Person currentPerson, Scanner in, String moduleCode) {
        this.addUtils = new AddUtils(allModules, currentPerson);
        this.moduleValidator = new ModuleValidator(allModules, currentPerson);
        this.in = in;
        this.moduleCode = moduleCode;
    }

    /**
     * Adds a module to the user's academic calendar if it exists in FullModule,
     * else does not add module into user's academic calendar.
     * Validates user's input semester and grade.
     * If either is invalid, does not add module into user's academic calendar.
     * Updates user's statistics as well.
     */
    @Override
    public void execute() throws AcademicException, IOException {
        fh = new FileHandler(LOG_FILE_NAME);
        logger = new LoggingTool(LOGGER_NAME,fh).initialize();
        logger.log(Level.INFO,"Executing add command.");

        validateModuleCode();

        promptUserToEnterSemester();
        String userInput = in.nextLine().trim();

        promptUserToEnterGrade();
        String gradeValue = in.nextLine().trim().toUpperCase();

        int semesterValue = validateInputs(userInput, gradeValue);
        int moduleCredit = addUtils.getModuleCreditForModule(moduleCode);
        
        addUtils.addModuleToUser(moduleCode, semesterValue, gradeValue, moduleCredit);

        assert semesterValue > 0;
        assert moduleCredit >= 0;

        logger.log(Level.INFO,"Finish executing add command.");
        fh.close();
    }

    /**
     * Throws AcademicException if the module code is not offered by NUS,
     * or module is already taken by user.
     *
     * @throws AcademicException thrown when invalid module code is requested to be added
     */
    private void validateModuleCode() throws AcademicException {
        if (!moduleValidator.isModOfferedByNus(moduleCode)) {
            logger.log(Level.WARNING,"Module entered not offered by NUS.");
            fh.close();
            throw new AcademicException(moduleCode + ERROR_NOT_OFFERED);
        }

        if (moduleValidator.isModTakenByUser(moduleCode)) {
            logger.log(Level.WARNING,"Module entered is duplicated.");
            fh.close();
            throw new AcademicException(ERROR_DUPLICATE_MOD);
        }
    }

    /**
     * Validates user inputs and returns semester value if inputs are valid,
     * else throws Academic Exception.
     *
     * @param userInput semester value
     * @param gradeValue grade value
     * @return semesterIndex
     * @throws AcademicException thrown when any input is invalid
     */
    private int validateInputs(String userInput, String gradeValue) throws AcademicException {
        int semesterValue;

        try {
            semesterValue = Integer.parseInt(userInput);
        } catch (Exception e) {
            logger.log(Level.WARNING,"Semester entered is not an integer.");
            fh.close();
            throw new AcademicException(ERROR_INVALID_COMMAND);
        }

        if (!ModuleValidator.isValidSemester(semesterValue)) {
            logger.log(Level.WARNING,"Semester entered is invalid.");
            fh.close();
            throw new AcademicException(ERROR_INVALID_SEMESTER_INDEX);
        }

        if (!moduleValidator.isValidGrade(gradeValue)) {
            logger.log(Level.WARNING,"Grade entered is invalid.");
            fh.close();
            throw new AcademicException(ERROR_INVALID_GRADE);
        }
        return semesterValue;
    }

    /**
     * Prints prompt and help messages for user to enter module's grade.
     */
    private void promptUserToEnterGrade() {
        System.out.println("Grade received for " + moduleCode.toUpperCase() + "?");
        System.out.println(VALID_GRADES);
    }

    /**
     * Prints prompt and help messages for user to enter module's semester taken.
     */
    private void promptUserToEnterSemester() {
        System.out.println("Semester you plan to take " + moduleCode.toUpperCase() + "?");
        System.out.println(VALID_SEMESTERS);
    }
}
