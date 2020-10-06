package seedu.duke;

import seedu.duke.apps.App;
import seedu.duke.exceptions.AppParserException;
import seedu.duke.objects.FullModule;
import seedu.duke.apps.ModuleInitializer;
import seedu.duke.objects.Person;
import seedu.duke.parser.AppParser;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Duke {
    private Ui ui;
    private ModuleInitializer allModules;
    private Person currentPerson;

    public Duke() {
        ui = new Ui();
        allModules =  new ModuleInitializer();
        currentPerson = new Person("Bob");
    }

    public void run() {
        System.out.println("hello welcome to plannus");
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.getScanner().nextLine();
                App selectedApp = AppParser.parse(userInput, allModules, currentPerson, ui);
                selectedApp.run();
                isExit = selectedApp.isExit();
                System.out.println("hello welcome back to plannus");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("bye bye");
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
