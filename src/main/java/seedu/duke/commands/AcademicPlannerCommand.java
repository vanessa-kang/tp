package seedu.duke.commands;

public class AcademicPlannerCommand {
    boolean isExit = false;

    public AcademicPlannerCommand() {

    }

    public AcademicPlannerCommand(boolean isExitCommand) {
        this.isExit = isExitCommand;
    }

    public boolean isExit() {
        return isExit;
    }

    public void run() {

    }
}
