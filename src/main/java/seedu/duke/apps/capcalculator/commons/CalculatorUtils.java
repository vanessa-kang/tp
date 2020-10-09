package seedu.duke.apps.capcalculator.commons;

import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;

import java.text.DecimalFormat;

public class CalculatorUtils {
    private static final int FROM_ADD = 1;
    private static final int FROM_EDIT = 2;
    private static final int FROM_REMOVE = 3;
    private static final DecimalFormat formatFinalCap = new DecimalFormat("#.##");
    protected static final double MAXIMUM_CAP = 5.00;


    private final Person currentPerson;

    public CalculatorUtils(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    /**
     * Update Cap after every change in current module list.
     *
     * @param type An int storing the type of function calling update cap
     * @param currentModule A PartialModule object storing current module
     * @param caps An optional number of double storing old cap and new cap (If you send in old, must send in new also)
     */
    //TODO Look into throwing error and short circuiting this command
    public void updateCap(int type, PartialModule currentModule, double... caps) {
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
     * Returns CAP score as a string.
     *
     * @param academicPoint academic point to parse
     * @return string of academic point
     */
    public static String formatCapToString(double academicPoint) {
        if (isNaN(academicPoint)) {
            return "0";
        }
        return formatFinalCap.format(academicPoint);
    }

    /**
     * Returns true if CAP is NaN
     * else returns false.
     *
     * @param academicPoint academic point to check
     * @return boolean
     */
    public static boolean isNaN(double academicPoint) {
        return (academicPoint != academicPoint);
    }
}
