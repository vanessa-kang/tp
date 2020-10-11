package seedu.duke.apps;

import com.google.gson.Gson;
import seedu.duke.objects.FullModule;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing function to load all modules offered by NUS.
 */
public class ModuleInitializer {
    private final static int TOTAL_NUMBER_OF_MODULES = 12436;

    private boolean isLoadingSuccessful;
    private Map<String, Integer> moduleMap = new HashMap<>();
    private FullModule[] moduleFullDetails;

    public ModuleInitializer() {
        Gson gson = new Gson();

        InputStream in = ModuleInitializer.class.getResourceAsStream("/ModuleData.json");
        Reader jsonReader = new InputStreamReader(in);
        moduleFullDetails = gson.fromJson(jsonReader, FullModule[].class);

        for (int i = 0; i < moduleFullDetails.length; i++) {
            moduleMap.put(moduleFullDetails[i].getModuleCode(), i);
        }

        if (moduleFullDetails.length != TOTAL_NUMBER_OF_MODULES) {
            isLoadingSuccessful = false;
        } else {
            isLoadingSuccessful = true;
        }
    }

    public ModuleInitializer(boolean isProperCreation) {
        this.isLoadingSuccessful = isProperCreation;
    }

    public boolean getIsLoadingSuccessful() {
        return this.isLoadingSuccessful;
    }

    public Map<String, Integer> getModuleMap() {
        return this.moduleMap;
    }

    public FullModule[] getModuleFullDetails() {
        return this.moduleFullDetails;
    }
}
