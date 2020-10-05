package seedu.duke;

import seedu.duke.apps.App;
import seedu.duke.exceptions.AppParserException;
import seedu.duke.objects.FullModule;
import seedu.duke.apps.ModuleInitializer;
import seedu.duke.objects.Person;
import seedu.duke.parser.AppParser;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the PlanNus application.
     */

    private static ModuleInitializer allModules;
    private static Person currentPerson;

    public static void main(String[] args) {
        allModules =  new ModuleInitializer();
        currentPerson = new Person("Bob");

//        Scanner input = new Scanner(System.in);
//        String modCode = input.nextLine();
//
//        Integer idx = allModules.getModuleMap().get(modCode);
//        FullModule cs1010Data = allModules.getModuleFullDetails()[idx];
//
//        System.out.println("PartialModule loaded... Checking for CS1010 validity...");
//        System.out.println(cs1010Data);

        new Duke().run();
    }

//    private Storage storage;
//    private TaskList tasks;
//    private Ui ui;

    /**
     * Constructor for Duke to allow for initialising of save files
     *
     * @param filePath A String containing the path of the save file
     */
//    public Duke(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showError(e.getErrorMessage());
//            tasks = new TaskList();
//        }
//    }

    /**
     * A continuously running function to allow for user input and task operations
     */
    public void run() {
//        ui.showWelcome();
        System.out.println("hello welcome to plannus");
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            try {
//                String fullCommand = ui.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
                String userInput = scanner.nextLine();
                App selectedApp = AppParser.parse(userInput, allModules, currentPerson);
                selectedApp.run();
                isExit = selectedApp.isExit();
                System.out.println("hello welcome back to plannus");
//                storage.write(tasks);
            } catch (Exception e) {
//                ui.showError(e.getErrorMessage());
                System.out.println(e.getMessage());
            }
        }
        System.out.println("bye bye");
//        ui.showGoodbye();
    }

    /**
     * Main function of project Duke
     *
     * @param args A String Array containing arguments when running Duke
     */
//        public static void main(String[] args) {
//            new Duke("tasks.txt").run();
//        }
}
