package edu.gatech.seclass.jobcompare6300.controller;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.model.*;
import edu.gatech.seclass.jobcompare6300.view.AdjustComparisonSettingsActivity;
import edu.gatech.seclass.jobcompare6300.view.CompareJobsActivity;
import edu.gatech.seclass.jobcompare6300.view.EnterEditCurrentJobActivity;
import edu.gatech.seclass.jobcompare6300.view.EnterJobOfferActivity;

public class MainActivity extends AppCompatActivity {
    private final DBController controller = new DBController(this);
    private CurrentJob currentJob = null;
    private final List<Job> jobs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
    }

    public void enterCurrentJobDetails(View view) {
        //TODO add button to clear out db
        Intent intent = new Intent(this, EnterEditCurrentJobActivity.class);
        startActivity(intent);
    }

    public void editCurrentJobDetails(View view) {
        Intent intent = new Intent(this, EnterEditCurrentJobActivity.class);
        startActivity(intent);
    }

    public void enterJobOfferDetails(View view) {
        Intent intent = new Intent(this, EnterJobOfferActivity.class);
        startActivity(intent);
    }

    public void adjustComparisonSettings(View view){
        Intent intent = new Intent(this, AdjustComparisonSettingsActivity.class);
        startActivity(intent);
    }

    public void compareJobs(View view){
        Intent intent = new Intent(this, CompareJobsActivity.class);
        Bundle bundle = new Bundle();
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        bundle.putStringArrayList("list", list);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    public int countJobs() {
        return jobs.size();
    }

    public void rankSortJobs() {
    }

    public void compareToCurrentJob(JobOffer jobOffer){
        Intent intent = new Intent(this, CompareJobsActivity.class);
        Bundle bundle = new Bundle();
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        bundle.putStringArrayList("list", list);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    public void setCurrentJob(CurrentJob currentJob){
        this.currentJob = currentJob;
    }

    public void saveCurrentJob(View view){}

    public void cancelCurrentJob(View view){}
}