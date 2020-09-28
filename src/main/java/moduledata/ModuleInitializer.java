package moduledata;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ModuleInitializer {
    private Map<String, Integer> moduleMap = new HashMap<>();
    private ModuleDatum[] moduleFullDetails;

    public ModuleInitializer() {
        Gson gson = new Gson();

        InputStream in = ModuleInitializer.class.getResourceAsStream("/moduleData.json");
        Reader jsonReader = new InputStreamReader(in);
        moduleFullDetails = gson.fromJson(jsonReader, ModuleDatum[].class);

        for (int i = 0; i < moduleFullDetails.length; i++) {
            moduleMap.put(moduleFullDetails[i].getModuleCode(), i);
        }
    }

    public Map<String, Integer> getModuleMap() {
        return this.moduleMap;
    }
    public ModuleDatum[] getModuleFullDetails() {
        return this.moduleFullDetails;
    }
}
