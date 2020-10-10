package seedu.duke.apps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.objects.FullModule;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ModuleInitializerTest {
    private ModuleInitializer allModules;

    @BeforeEach
    public void setup() {
        allModules = new ModuleInitializer();
    }

    @Test
    void getModuleMap_loadAllModuleData_success() {
        Map<String, Integer> moduleMap = allModules.getModuleMap();

        assertEquals(moduleMap.get("CS1010"), 1786);
    }

    @Test
    void getModuleFullDetails_loadAllModuleData_success() {
        FullModule[] modules = allModules.getModuleFullDetails();

        assertEquals(allModules.getIsLoadingSuccessful(), true);

        assertEquals(modules.length, 12436);
        assertEquals(modules[1786].toString(), "{\"additionalProperties\":{}," +
                "\"attributes\":[true,false,false,false,false,false,false,false,false]," +
                "\"corequisite\":\"\",\"department\":\"Computer Science\",\"faculty\":\"Computing\"," +
                "\"moduleCode\":\"CS1010\",\"moduleCredit\":4," +
                "\"preclusion\":\"CS1010E, CS1010J, CS1010S, CS1010X, CS1010XCP, CS1101S\"," +
                "\"prerequisite\":\"\",\"semester\":[1,2],\"title\":\"Programming Methodology\"}");
    }

    @Test
    void getModuleFullDetails_loadAllModuleData_fail() {
        allModules = new ModuleInitializer(false);

        assertEquals(allModules.getIsLoadingSuccessful(), false);
    }
}