package seedu.duke.apps.academicplanner.commands;
import seedu.duke.apps.academicplanner.commons.ModuleValidator;
import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.FullModule;

import java.util.logging.Level;

public class ModuleDetailsCommand extends Command {

    private static final String ERROR_NOT_OFFERED = " IS NOT OFFERED BY NUS";

    private final ModuleLoader allModules;
    private String moduleCode;
    private ModuleValidator moduleValidator;

    public ModuleDetailsCommand(ModuleLoader allModules, String moduleCode) {
        this.allModules = allModules;
        this.moduleCode = moduleCode;
        this.moduleValidator = new ModuleValidator(allModules);
    }

    @Override
    public void execute() throws AcademicException {

        validateModuleCode();

        int mapIndex = allModules.getModuleMap().get(moduleCode);
        FullModule module = allModules.getModuleFullDetails()[mapIndex];
        String semOfferedStr = module.getSemester().toString();

        System.out.println(module.getModuleCode() + " " + module.getTitle());
        System.out.println("\t" + "Department: " + module.getDepartment());
        System.out.println("\t" + "Faculty: " + module.getFaculty());
        System.out.println("\t" + "Credits: " + module.getModuleCredit() + "MCs");
        System.out.println("\t" + "Semesters offered: " + semOfferedStr.substring(1,semOfferedStr.length()-1));
        System.out.println("\t" + "Prerequisites: " + module.getPrerequisite().replaceAll("\n","\n\t               "));
        System.out.println("\t" + "Corequisites: " + module.getCorequisite().replaceAll("\n","\n\t             "));
        System.out.println("\t" + "Preclusions: " + module.getPreclusion().replaceAll("\n","\n\t             "));

    }

    private void validateModuleCode() throws AcademicException {
        if (!moduleValidator.isModOfferedByNus(moduleCode)) {
            throw new AcademicException(moduleCode + ERROR_NOT_OFFERED);
        }
    }

}
