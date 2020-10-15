package seedu.duke.storage;

import seedu.duke.apps.academicplanner.commons.AddUtils;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Storage {
    private ModuleLoader allModules;
    private AddUtils addUtils;

    private static final String FILE_NAME = "PlanNUS.txt";
    private static final String INDENT = "            ";
    private static final String NEWLINE = "\n";

    private static final int SEMESTER_WORD_POSITION = 1;
    private static final int SEMESTER_INDEX_POSITION = 2;
    private static final int MODULE_CODE_POSITION = 0;
    private static final int MODULE_GRADE_POSITION = 1;
    private static final int MODULE_CREDIT_POSITION = 2;


    public void loadTextFile(Person currentPerson) throws FileNotFoundException {
        File f = new File("PlanNUS.txt");
        Scanner in = new Scanner(f);
        addUtils = new AddUtils(allModules, currentPerson);
        int currSem = 0;

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] lineItems = line.split("\\s+");

            if (lineItems[SEMESTER_WORD_POSITION].equals("SEMESTER")) {
                currSem = Integer.parseInt(lineItems[SEMESTER_INDEX_POSITION]);
            } else {
                String currentModuleCode = lineItems[MODULE_CODE_POSITION];
                String currentModuleGrade = lineItems[MODULE_GRADE_POSITION];
                int currentModuleCredit = Integer.parseInt(lineItems[MODULE_CREDIT_POSITION]);

                addUtils.addModuleToUser(currentModuleCode, currSem, currentModuleGrade, currentModuleCredit);
            }
        }
    }

    public void saveTextFile(Person currentPerson) throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME);
        ArrayList<PartialModule> sortedBySem = currentPerson.getModulesList();
        sortedBySem.sort(Comparator.comparing(PartialModule::getSemesterIndex));

        int currSem = 0;
        int newSem;
        String textToSave = "";

        for (PartialModule item : sortedBySem) {
            newSem = item.getSemesterIndex();
            if (newSem != currSem) {
                currSem = newSem;
                textToSave += INDENT + "SEMESTER " + currSem + NEWLINE;
            }
            int spacingCodeAndGrade = 8 + (8 - item.getModuleCode().length());
            int spacingGradeAndCredit = 8 + (8 - item.getGrade().length());
            textToSave += item.getModuleCode() + writeSpaces(spacingCodeAndGrade)
                    + item.getGrade() + writeSpaces(spacingGradeAndCredit)
                    + item.getModuleCredit() + NEWLINE;
        }

        fw.write(textToSave);
        fw.close();
    }

    public String writeSpaces(int spacing) {
        String actualSpaces = "";
        while (spacing > 0) {
            actualSpaces += " ";
            spacing -= 1;
        }
        return actualSpaces;
    }
}
