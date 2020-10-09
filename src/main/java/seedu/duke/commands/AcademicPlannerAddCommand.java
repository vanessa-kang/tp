package seedu.duke.commands;

import seedu.duke.apps.ModuleInitializer;
import seedu.duke.commons.AcademicPlannerCommon;
import seedu.duke.exceptions.AcademicPlannerParserException;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;
import static seedu.duke.commons.AcademicPlannerCommon.getModuleCodeFromInput;
import static seedu.duke.commons.AcademicPlannerCommon.getSemesterFromInput;
import static seedu.duke.commons.AcademicPlannerCommon.getGradeFromInput;


public class AcademicPlannerAddCommand extends AcademicPlannerCommand {

    private String moduleCode;
    private int semester;
    private String grade;
    private Person user;
    private Ui ui = new Ui();
    private AcademicPlannerCommon apc = new AcademicPlannerCommon();

    //userInput format = "add m/cs1010 s/2 g/A+"
    public AcademicPlannerAddCommand(String userInput, ModuleInitializer allmodules,
                                     Person currentPerson, Ui ui) throws AcademicPlannerParserException {
        setModuleCode(getModuleCodeFromInput(userInput));
        setSemester(getSemesterFromInput(userInput));
        setGrade(getGradeFromInput(userInput));
        setUser(currentPerson);
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public void run() {
        int moduleCredit = apc.getModuleCreditForModule(moduleCode);
        apc.addModuleToUser(moduleCode,semester,grade,moduleCredit,getUser(),ui);
    }
}
