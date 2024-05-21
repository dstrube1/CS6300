package edu.gatech.seclass.jobcompare6300.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.controller.DBController;
import edu.gatech.seclass.jobcompare6300.model.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.model.User;

public class AdjustComparisonSettingsActivity extends AppCompatActivity {
    private static final String TAG = AdjustComparisonSettingsActivity.class.getName();
    String message;

    final DBController controller = new DBController(this);
//    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_comparison_settings);
    }

//    public void setUser(User user){
//        this.user = user;
//    }

    public void adjust(View view) {
//        if(user == null){
//            message = "Null user";
//            Log.e(TAG, message);
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//            return;
//        }
        ComparisonSettings comparisonSettings = User.getComparisonSettings();

        //set settings

        //update database

        User.setComparisonSettings(comparisonSettings);
        finish();
    }

    public void cancel(View view) {
        message = "Cancelling adjust comparison settings";
        Log.i(TAG, message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        finish();
    }
}