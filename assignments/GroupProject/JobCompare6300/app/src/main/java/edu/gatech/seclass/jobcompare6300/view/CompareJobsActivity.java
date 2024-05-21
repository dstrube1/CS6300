package edu.gatech.seclass.jobcompare6300.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.R;

public class CompareJobsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_jobs);

        // Create a list data which will be displayed in inner ListView.
        Bundle bundle = getIntent().getBundleExtra("bundle");
        List<String> listData = bundle.getStringArrayList("list");

        // Create the ArrayAdapter use the item row layout and the list data.
        ArrayAdapter<String> listDataAdapter = new ArrayAdapter<>(this,
                R.layout.activity_compare_jobs, R.id.listRowTextView, listData);
        // Set this adapter to inner ListView object.
        this.setListAdapter(listDataAdapter);
    }
}