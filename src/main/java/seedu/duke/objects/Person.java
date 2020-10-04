package seedu.duke.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
    //General Variables
    private String personName;

    //Variables needed for CAP Calculation
    private double currentTotalMcxGrade;
    private int currentMc;
    private int currentMcAfterSU;

    //Variables needed for Academic Planner
    private ArrayList<Module> modulesList;
    private HashMap<String,Module> modulesAddedMap;

    public Person(String personName) {
        this.currentTotalMcxGrade = 0.0;
        this.currentMc = 0;
        this.currentMcAfterSU = 0;
        this.personName = personName;
        this.modulesList = new ArrayList<>();
        this.modulesAddedMap = new HashMap<>();
    }

    public String getPersonName() {
        return personName;
    }

    public double getCurrentTotalMcxGrade() {
        return currentTotalMcxGrade;
    }

    public void setCurrentTotalMcxGrade(double currentTotalMcxGrade) {
        this.currentTotalMcxGrade = currentTotalMcxGrade;
    }

    public int getCurrentMc() {
        return currentMc;
    }

    public void setCurrentMc(int currentMc) {
        this.currentMc = currentMc;
    }

    public int getCurrentMcAfterSU() {
        return currentMcAfterSU;
    }

    public void setCurrentMcAfterSU(int currentMcAfterSU) {
        this.currentMcAfterSU = currentMcAfterSU;
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
