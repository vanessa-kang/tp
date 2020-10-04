package seedu.duke;

import seedu.duke.moduledata.ModuleDatum;
import seedu.duke.moduledata.ModuleInitializer;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ModuleInitializer allModules =  new ModuleInitializer();
        Integer idx = allModules.getModuleMap().get("CS1010");
        ModuleDatum cs1010Data = allModules.getModuleFullDetails()[idx];

        System.out.println(cs1010Data.toString());
    }
}
