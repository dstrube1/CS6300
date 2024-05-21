package edu.gatech.seclass.jobcompare6300.model;

import edu.gatech.seclass.jobcompare6300.util.Money;

public class Job {
    protected int id;
    public String title = "";
    public String company = "";
    public String location = "";
    public int costOfLiving = 0;
    public Money yearlySalary;
    public Money yearlyBonus;
    public int weeklyTeleworkDays = 0;
    public int leaveTime = 0;
    public Money gymMembershipAllowance;
    public boolean isCurrent = false;

    public float getJobScore() {
        return 0.0f;
    }
}