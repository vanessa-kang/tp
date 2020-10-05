package seedu.duke;

import seedu.duke.moduledata.ModuleDatum;
import seedu.duke.moduledata.ModuleInitializer;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the PlanNus application.
     */
    public static void main(String[] args) {
        ModuleInitializer allModules =  new ModuleInitializer();

        Scanner input = new Scanner(System.in);
        String modCode = input.nextLine();

        Integer idx = allModules.getModuleMap().get(modCode);
        ModuleDatum cs1010Data = allModules.getModuleFullDetails()[idx];


        System.out.println("Module loaded... Checking for CS1010 validity...");
        System.out.println(cs1010Data);
    }
}
