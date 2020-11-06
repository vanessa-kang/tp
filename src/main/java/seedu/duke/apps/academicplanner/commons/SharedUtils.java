package seedu.duke.apps.academicplanner.commons;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;
import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;

//@@author Khenus
public class SharedUtils {
    private static final int FROM_EDIT = 2;
    private static final int FROM_REMOVE = 3;

    public SharedUtils() {

    }

    public static ArrayList<PartialModule> getAllOccurrencesOfModule(Person currentPerson, String moduleCode) {
        ArrayList<PartialModule> modulesAddedList = currentPerson.getModulesList();
        ArrayList<Integer> occurrenceList = currentPerson.getModulesAddedMap().get(moduleCode);

        ArrayList<PartialModule> allOccurrencesOfModule = new ArrayList<>();

        for (int i = 0; i < occurrenceList.size(); i++) {
                allOccurrencesOfModule.add(modulesAddedList.get(occurrenceList.get(i)));
        }

        return allOccurrencesOfModule;
    }

    public static void printAllOccurrencesOfModule(ArrayList<PartialModule> allOccurrence) {
        System.out.println("\nPrevious attempt(s) for " + allOccurrence.get(0).getModuleCode() + ":");
        System.out.println("\t Semester    Grade");

        for (int i = 0; i < allOccurrence.size(); i++) {
            int currentSemester = allOccurrence.get(i).getSemesterIndex();
            String currentGrade = allOccurrence.get(i).getGrade();
            String toPrint = String.format("\t    %02d        %s  ", currentSemester, currentGrade);

            System.out.println(toPrint);
        }
    }

    public static void printAllOccurrencesOfModuleToEdit(Person currentPerson, String moduleCode) {
        ArrayList<PartialModule> allOccurrence = getAllOccurrencesOfModule(currentPerson, moduleCode);

        System.out.println("\nPrevious occurrence(s) for " + allOccurrence.get(0).getModuleCode() + ":");
        System.out.println("\tIndex   Semester    Grade");

        for (int i = 0; i < allOccurrence.size(); i++) {
            int currentSemester = allOccurrence.get(i).getSemesterIndex();
            String currentGrade = allOccurrence.get(i).getGrade();
            String toPrint = String.format("\t  %d.      %02d        %s  ",  i + 1, currentSemester, currentGrade);

            System.out.println(toPrint);
        }
    }

    public static int getEntryToBeEdited(Scanner in, String moduleCode, Person currentPerson, int from)
            throws AcademicException {
        ArrayList<Integer> moduleIndexList = currentPerson.getModulesAddedMap().get(moduleCode);

        int indexToUpdate = 0;

        if (moduleIndexList.size() > 1) {
            if (from == FROM_EDIT) {
                System.out.print("Please enter the index of entry to be edited:");
            } else {
                System.out.print("Please enter the index of entry to be removed:");
            }

            printAllOccurrencesOfModuleToEdit(currentPerson, moduleCode);

            String entryToEdit = in.nextLine().trim();

            try {
                indexToUpdate = parseInt(entryToEdit) - 1;
            } catch (Exception e) {
                throw new AcademicException("The value you entered is not an integer!");
            }

            if (!(indexToUpdate >= 0 && indexToUpdate < moduleIndexList.size())) {
                throw new AcademicException("The index you entered does not exist within the list!");
            }
        }

        return indexToUpdate;
    }
}
