package com.example.studentcecomponentmarksheet;

public class UserData {

    private String roll,name,classtest,sessional,sem,assignment,total;

    public UserData(String roll, String name, String classtest, String sessional, String sem, String assignment, String total) {
        this.roll = roll;
        this.name = name;
        this.classtest = classtest;
        this.sessional = sessional;
        this.sem = sem;
        this.assignment = assignment;
        this.total = total;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasstest() {
        return classtest;
    }

    public void setClasstest(String classtest) {
        this.classtest = classtest;
    }

    public String getSessional() {
        return sessional;
    }

    public void setSessional(String sessional) {
        this.sessional = sessional;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
