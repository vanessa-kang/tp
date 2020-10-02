package seedu.duke.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
    //General Variables
    private String personName;

    //Variables needed for CAP Calculation
    private double currentTotalMCxGrade;
    private int currentMC;
    private int currentMCAfterSU;

    //Variables needed for Academic Planner
    private ArrayList<Module> modulesList;
    private HashMap<String,Module> modulesAddedMap;

    public Person(String personName) {
        this.currentTotalMCxGrade = 0.0;
        this.currentMC = 0;
        this.currentMCAfterSU = 0;
        this.personName = personName;
        this.modulesList = new ArrayList<>();
        this.modulesAddedMap = new HashMap<>();
    }

    public String getPersonName() {
        return personName;
    }

    public double getCurrentTotalMCxGrade() {
        return currentTotalMCxGrade;
    }

    public void setCurrentTotalMCxGrade(double currentTotalMCxGrade) {
        this.currentTotalMCxGrade = currentTotalMCxGrade;
    }

    public int getCurrentMC() {
        return currentMC;
    }

    public void setCurrentMC(int currentMC) {
        this.currentMC = currentMC;
    }

    public int getCurrentMCAfterSU() {
        return currentMCAfterSU;
    }

    public void setCurrentMCAfterSU(int currentMCAfterSU) {
        this.currentMCAfterSU = currentMCAfterSU;
    }

    public ArrayList<Module> getModulesList() {
        return modulesList;
    }

    public void setModulesList(ArrayList<Module> modulesList) {
        this.modulesList = modulesList;
    }

    public HashMap<String, Module> getModulesAddedMap() {
        return modulesAddedMap;
    }

    public void setModulesAddedMap(HashMap<String, Module> modulesAddedMap) {
        this.modulesAddedMap = modulesAddedMap;
    }
}
