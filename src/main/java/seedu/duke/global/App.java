package seedu.duke.global;

/**
 * Class representing an application in PlanNUS.
 */
public class App {
    private boolean isExit = false;

    public App() {

    }

    public App(boolean isExitCommand) {
        this.isExit = isExitCommand;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void run() {
        //Method is intentionally left blank
    }

    public boolean getIsExit() {
        return this.isExit;
    }
}
