package seedu.duke.global;

//@@author Khenus
/**
 * Class representing a command in PlanNUS.
 */
public class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute() throws Exception {
        //Method is intentionally left blank
    }

    public boolean getIsExit() {
        return this.isExit;
    }
}
