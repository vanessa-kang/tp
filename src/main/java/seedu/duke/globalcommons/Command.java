package seedu.duke.globalcommons;

public class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute() throws Exception {

    }

    public boolean getIsExit() {
        return this.isExit;
    }
}
