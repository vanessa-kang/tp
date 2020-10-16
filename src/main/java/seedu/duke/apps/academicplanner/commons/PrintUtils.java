package seedu.duke.apps.academicplanner.commons;

import seedu.duke.objects.PartialModule;
import java.util.ArrayList;

/**
 * Class representing the common print methods.
 */
public class PrintUtils {
    private static final String INDENT = "     ";
    private static final String ERROR_NO_MODULES = "You have no modules added in the semester you requested!";

    public PrintUtils() {
        //Constructor is intentionally left blank
    }

    /**
     * Prints out the sorted list of modules.
     *
     * @param sortedList list to be printed
     * @param selectedSemester semester chosen.
     */
    public void printBySemester(ArrayList<PartialModule> sortedList, int selectedSemester) {
        if (sortedList.size() < 1) {
            System.out.println(ERROR_NO_MODULES);
        }

        System.out.println(INDENT + "SEMESTER " + selectedSemester);
        for (PartialModule item : sortedList) {
            printCalendarLine(item);
        }
    }

    /**
     * Prints a single entry of item.
     *
     * @param item module details to be printed
     */
    private void printCalendarLine(PartialModule item) {
        int spacing = 8 + (8 - item.getModuleCode().length());
        System.out.println(item.getModuleCode()
                + printSpace(spacing)
                + item.getGrade());
    }

    /**
     * Prints out contents of the Array list of partial modules in an intuitive format.
     *
     * @param sortedBySem arraylist of modules
     */
    public void printFullCalendar(ArrayList<PartialModule> sortedBySem) {
        int newSem;
        int currentSem = 0;
        for (PartialModule item : sortedBySem) {
            newSem = item.getSemesterIndex();
            if (newSem != currentSem) {
                currentSem = newSem;
                System.out.println(INDENT + "SEMESTER " + currentSem);
            }
            printCalendarLine(item);
        }
    }

    /**
     * Prints num spaces for indentation.
     * @param num int of spaces to print
     * @return string of spaces
     */
    private String printSpace(int num) {
        String space = "";
        for (int i = 0; i < num; i++) {
            space += " ";
        }
        return space;
    }
}
