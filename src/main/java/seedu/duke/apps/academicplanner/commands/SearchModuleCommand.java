package seedu.duke.apps.academicplanner.commands;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.FullModule;
import java.util.ArrayList;

public class SearchModuleCommand extends Command {

    private final ModuleLoader allModules;
    private String keyword;
    private final ArrayList<String> matchList = new ArrayList<>();

    public SearchModuleCommand(ModuleLoader allModules, String keyword) {
       this.allModules = allModules;
       this.keyword = keyword;
    }

    @Override
    public void execute() {

        FullModule[] modules = allModules.getModuleFullDetails();

        for (FullModule item: modules) {
            if (item.getModuleCode().contains(keyword)) {
                matchList.add(item.getModuleCode());
            }
            if (matchList.size() == 10) break;
        }

        System.out.println("Note: Only up to the first 10 results are displayed.");
        String grammar = matchList.size() == 1 ? "module." : "modules.";
        System.out.println("Found " + matchList.size() + " matching " + grammar);
        for (String matches: matchList) {
            System.out.println(matches);
        }

    }

}
