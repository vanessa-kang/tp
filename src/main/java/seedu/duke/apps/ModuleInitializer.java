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
    private static final int TOTAL_NUMBER_OF_MODULES = 12436;

    private boolean isLoadingSuccessful = false;
    private Map<String, Integer> moduleMap = new HashMap<>();
    private FullModule[] moduleFullDetails;

    /**
     * Default constructor for to load all modules from JSON file.
     */
    public ModuleInitializer() {
        Gson gson = new Gson();

        InputStream in = ModuleInitializer.class.getResourceAsStream("/ModuleData.json");
        Reader jsonReader = new InputStreamReader(in);
        moduleFullDetails = gson.fromJson(jsonReader, FullModule[].class);

        for (int i = 0; i < moduleFullDetails.length; i++) {
            moduleMap.put(moduleFullDetails[i].getModuleCode(), i);
        }

        if (moduleFullDetails.length == TOTAL_NUMBER_OF_MODULES) {
            isLoadingSuccessful = true;
        }
    }

    /**
     * Constructor for checking condition where loading all module from JSON file fails.
     *
     * @param isProperCreation boolean indicating non-proper creation of module initializer (Default: false)
     */
    public ModuleInitializer(boolean isProperCreation) {
        this.isLoadingSuccessful = isProperCreation;
    }

    /**
     * Getter for whether loading modules from JSON is successful.
     *
     * @return boolean
     */
    public boolean getIsLoadingSuccessful() {
        return this.isLoadingSuccessful;
    }

    /**
     * Getter for module map.
     *
     * @return Map<String, Integer>
     */
    public Map<String, Integer> getModuleMap() {
        return this.moduleMap;
    }

    /**
     * Getter for full module details.
     *
     * @return An Array of FullModule
     */
    public FullModule[] getModuleFullDetails() {
        return this.moduleFullDetails;
    }
}
