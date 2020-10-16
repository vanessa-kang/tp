package seedu.duke.storage.commons;

import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;
import seedu.duke.storage.exceptions.SaveFileException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class SaveUtils {
    private static final String NEWLINE = "\n";
    private static final String SEPARATOR = "|";

    private Person currentPerson;
    private String fileName;

    /**
     * Default constructor for handling save
     *
     * @param currentPerson a Person object storing information for current person
     * @param fileName a String for name of save file
     */
    public SaveUtils(Person currentPerson, String fileName) {
        this.currentPerson = currentPerson;
        this.fileName = fileName;
    }

    /**
     * Function to save person object into save file
     *
     * @throws SaveFileException Exception thrown if there is an error accessing save file
     */
    public void save() throws SaveFileException {
        try {
            FileWriter fw = new FileWriter(fileName);
            ArrayList<PartialModule> sortedBySem = currentPerson.getModulesList();
            sortedBySem.sort(Comparator.comparing(PartialModule::getSemesterIndex));

            String textToSave = "";

            for (PartialModule item : sortedBySem) {
                textToSave += item.getSemesterIndex() + SEPARATOR
                        + item.getModuleCode() + SEPARATOR
                        + item.getGrade() + SEPARATOR
                        + item.getModuleCredit() + NEWLINE;
            }

            fw.write(textToSave);
            fw.close();
        } catch (IOException e) {
            throw new SaveFileException("Error accessing save file!");
        }
    }
}
