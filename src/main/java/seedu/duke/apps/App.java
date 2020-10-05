package seedu.duke.apps;

public class App {
    boolean isExit = false;

    public App() {}

    public App(boolean isExitCommand) {
        this.isExit = isExitCommand;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void run() {}
}
