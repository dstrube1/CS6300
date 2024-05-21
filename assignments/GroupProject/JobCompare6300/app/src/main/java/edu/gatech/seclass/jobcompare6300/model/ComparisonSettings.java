package edu.gatech.seclass.jobcompare6300.model;

public class ComparisonSettings {
    private int yearlySalaryWeight = 1;
    private int yearlyBonusWeight = 1;
    private int weeklyTeleworkDaysWeight = 1;
    private int leaveTimeWeight = 1;
    private int gymMembershipAllowanceWeight = 1;

    public void setYearlySalaryWeight(int input) {
        yearlySalaryWeight = input;
    }

    public void setYearlyBonusWeight(int input) {
        yearlyBonusWeight = input;
    }

    public void setWeeklyTeleworkDaysWeight(int input) {
        weeklyTeleworkDaysWeight = input;
    }

    public void setLeaveTimeWeight(int input) {
        leaveTimeWeight = input;
    }

    public void setGymMembershipAllowanceWeight(int input) {
        gymMembershipAllowanceWeight = input;
    }

    public int getYearlySalaryWeight(int input) {
        return yearlySalaryWeight;
    }

    public int getYearlyBonusWeight(int input) {
        return yearlyBonusWeight;
    }

    public int getWeeklyTeleworkDaysWeight(int input) {
        return weeklyTeleworkDaysWeight;
    }

    public int getLeaveTimeWeight(int input) {
        return leaveTimeWeight;
    }

    public int getGymMembershipAllowanceWeight(int input) {
        return gymMembershipAllowanceWeight;
    }
}
