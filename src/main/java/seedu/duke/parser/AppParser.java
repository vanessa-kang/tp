package seedu.duke.parser;

import seedu.duke.apps.AcademicPlanner;
import seedu.duke.apps.App;
import seedu.duke.apps.ModuleInitializer;
import seedu.duke.exceptions.AppParserException;
import seedu.duke.objects.Person;

public class AppParser {
    public static App parse(String userInput, ModuleInitializer allModules, Person currentPerson)
            throws AppParserException {
        userInput = userInput.trim().toLowerCase();

        if (userInput.equals("acadplan")) {
            return new AcademicPlanner(allModules, currentPerson);
        } else if (userInput.equals("exit")) {
            return new App(true);
        } else {
            throw new AppParserException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

//        String[] inputs = command.split(" ");
//        inputs[0] = inputs[0].toLowerCase();

//        if (inputs[0].equals("list")) {
//            return new ListCommand();
//        } else if (inputs[0].equals("help")) {
//            return new HelpCommand();
//        } else if (inputs[0].equals("done")) {
//            return new DoneCommand(Integer.parseInt(inputs[1]));
//        } else if (inputs[0].equals("delete")) {
//            return new DeleteCommand(command);
//        } else if (inputs[0].equals("bye") || inputs[0].equals("exit")) {
//            return new Command(true);
//        } else if (inputs[0].equals("todo") || inputs[0].equals("deadline") || inputs[0].equals("event")) {
//            return new AddCommand(inputs[0], command);
//        } else if (inputs[0].equals("print")) {
//            try {
//                return new PrintCommand(command);
//            } catch (DukeException error) {
//                throw new DukeException(error.getErrorMessage());
//            }
//        } else if (inputs[0].equals("find")) {
//            try {
//                return new FindCommand(inputs[1]);
//            } catch (IndexOutOfBoundsException error) {
//                throw new DukeException("The keyword cannot be empty!");
//            }
//        } else {
//            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
