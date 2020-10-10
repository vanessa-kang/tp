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
    }

    public Map<String, Integer> getModuleMap() {
        return this.moduleMap;
    }

    public FullModule[] getModuleFullDetails() {
        return this.moduleFullDetails;
    }
}
