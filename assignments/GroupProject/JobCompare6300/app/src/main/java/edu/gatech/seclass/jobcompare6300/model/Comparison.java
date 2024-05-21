package edu.gatech.seclass.jobcompare6300.model;

public class Comparison {

    private Job job0;
    private Job job1;
    public int compareJobs() {
        if (job0.getJobScore() == job1.getJobScore())
            return 0;
        else if (job0.getJobScore() > job1.getJobScore())
            return 1;
        else
            return -1;
    }

    public void populateTable(){}
}
