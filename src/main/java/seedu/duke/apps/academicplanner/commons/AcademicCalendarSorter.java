package seedu.duke.apps.academicplanner.commons;

import seedu.duke.objects.PartialModule;
import java.util.ArrayList;

/**
 * Class representing sorter for academic calendar.
 */
public class AcademicCalendarSorter {
    private ArrayList<PartialModule> userModuleList;

    public AcademicCalendarSorter(ArrayList<PartialModule> moduleList) {
        userModuleList = moduleList;
    }

    /**
     * Sorts module list by semester index and return module list of chosen semester.
     *
     * @param semesterIndex semesterIndex to search for
     * @return module list of the chosen semester
     */
    public ArrayList<PartialModule> sortBySemester(int semesterIndex) {

        if (userModuleList.size() < 1) {
            System.out.println("Your academic calendar is empty.");
        }

        ArrayList<PartialModule> sortedList = new ArrayList<>();

        for (PartialModule m : userModuleList) {
            if (m.getSemesterIndex() == semesterIndex) {
                sortedList.add(m);
            }
        }
        return sortedList;
    }
}
