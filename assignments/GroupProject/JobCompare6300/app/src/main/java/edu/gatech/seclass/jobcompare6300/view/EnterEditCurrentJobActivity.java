package edu.gatech.seclass.jobcompare6300.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.controller.DBController;

public class EnterEditCurrentJobActivity extends AppCompatActivity {

    final DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_edit_current_job);
    }

    public void save(View view){
        finish();
    }

    public void cancel(View view){
        finish();
    }
}