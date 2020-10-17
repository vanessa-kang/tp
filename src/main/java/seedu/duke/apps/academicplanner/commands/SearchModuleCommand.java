package seedu.duke.apps.academicplanner.commands;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.academicplanner.commons.PrintUtils;
import seedu.duke.global.Command;
import seedu.duke.global.objects.FullModule;
import java.util.ArrayList;

public class SearchModuleCommand extends Command {

    private final ModuleLoader allModules;
    private String keyword;
    private final ArrayList<String> matchList = new ArrayList<>();
    private final int maxResults = 10;
    private PrintUtils printUtils = new PrintUtils();

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
            if (matchList.size() == maxResults) {
                break;
            }
        }

        printUtils.printMatchModules(matchList, maxResults);

    }

}
