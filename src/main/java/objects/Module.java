package objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Module implements Comparable<Module>{
    private String moduleCode;
    private int semesterIndex; // 1 to 8
    private String grade;
    private int moduleCredit;
    private double CAP;

    public Module (String moduleCode, int semesterIndex, String grade, int moduleCredit) {
        setModuleCode(moduleCode);
        setSemesterIndex(semesterIndex);
        setGrade(grade);
        setModuleCredit(moduleCredit);
        setCAP(grade);
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public Integer getModuleCredit() {
        return moduleCredit;
    }

    public String getGrade() {
        return grade;
    }

    public int getSemesterIndex() {
        return semesterIndex;
    }

    public void setModuleCredit(int moduleCredit) {
        this.moduleCredit = moduleCredit;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setSemesterIndex(int semesterIndex) {
        this.semesterIndex = semesterIndex;
    }

    public void setGrade(String grade) {
        this.grade = grade;
        setCAP(grade);
    }

    public void setCAP(String grade) {
        switch (grade.toUpperCase()) {
        case "A+":
        case "A":
            this.CAP = 5.00;
            break;
        case "A-":
            this.CAP = 4.50;
            break;
        case "B+":
            this.CAP = 4.00;
            break;
        case "B":
            this.CAP = 3.50;
            break;
        case "B-":
            this.CAP = 3.00;
            break;
        case "C+":
            this.CAP = 2.50;
            break;
        case "C":
            this.CAP = 2.00;
            break;
        case "D+":
            this.CAP = 1.50;
            break;
        case "D":
            this.CAP = 1.00;
            break;
        case "F":
            this.CAP = 0.00;
            break;
        case "CS":           //Completed Satisfactorily
            //Fallthrough
        case "CU":           //Completed Unsatisfactorily
            //Fallthrough
        case "S":            //Satisfactory
            //Fallthrough
        case "U":            //Unsatisfactory
            //Fallthrough
        case "W":            //Withdrawn
            //Fallthrough
        case "IC":           //Incomplete
            //Fallthrough
        case "IP":           //In progress
            //Fallthrough
        case "AUD":          //Audit
            //Fallthrough
        case "WU":           //Withdrawn from University
            //Fallthrough
        case "EXE":         //Exempted
            //Fallthrough
        case "NT":           //Not taken
            this.CAP = -1.00;
            break;
        default:
            this.CAP = 0.00;
            break;
        }
    }

    public double getCAP() {
        return CAP;
    }

    @Override
    public int compareTo(Module module) {
        return (Double.compare(this.getCAP(), module.getCAP()));
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
