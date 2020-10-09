package seedu.duke.commons;

import seedu.duke.apps.ModuleInitializer;
import seedu.duke.exceptions.AcademicPlannerParserException;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

public class AcademicPlannerCommon {

    private static final int FROM_ADD = 1;
    private static final int FROM_EDIT = 2;
    private static final int FROM_REMOVE = 3;

    private ModuleInitializer allModules = new ModuleInitializer();

    public AcademicPlannerCommon() {

    }

    public static String getModuleCodeFromInput(String userInput) {
        int moduleCodeStartIndex = userInput.indexOf("/m") + 1;
        int moduleCodeEndIndex = userInput.indexOf("/") - 1;
        return userInput.substring(moduleCodeStartIndex,moduleCodeEndIndex);
    }

    public static int getSemesterFromInput(String userInput) throws AcademicPlannerParserException {
        int semesterStartIndex = userInput.indexOf("/s") + 1;
        int semesterEndIndex = userInput.indexOf("/") - 1;
        try {
            int inputSemester = Integer.parseInt(userInput.substring(semesterStartIndex,semesterEndIndex));
            return inputSemester;
        } catch (Exception e) {
            throw new AcademicPlannerParserException("Input semester is not an integer!");
        }
    }

    public static String getGradeFromInput(String userInput) {
        int gradeStartIndex = userInput.indexOf("/g");
        return userInput.substring(gradeStartIndex);
    }

    public int getModuleCreditForModule(String moduleCode) {
        int mapIndex = allModules.getModuleMap().get(moduleCode);
        return allModules.getModuleFullDetails()[mapIndex].getModuleCredit();
    }

    public void addModuleToUser(String moduleCode, int semesterValue, String gradeValue, int moduleCredit,
                                Person currentPerson, Ui ui) {
        PartialModule newModuleToAdd = new PartialModule(moduleCode, semesterValue, gradeValue, moduleCredit);
        ArrayList<PartialModule>  modulesList = currentPerson.getModulesList();
        HashMap<String, PartialModule> modulesAddedMap = currentPerson.getModulesAddedMap();
        modulesList.add(newModuleToAdd);
        modulesAddedMap.put(moduleCode, newModuleToAdd);
        updateCap(FROM_ADD, newModuleToAdd, currentPerson);
        System.out.println(newModuleToAdd.getModuleCode()
                + " added into Semester " + semesterValue + ".");
    }

    public void updateCap(int type, PartialModule currentModule,Person currentPerson, double... caps)  {

        ArrayList<PartialModule>  modulesList = currentPerson.getModulesList();
        HashMap<String, PartialModule> modulesAddedMap = currentPerson.getModulesAddedMap();

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
}
