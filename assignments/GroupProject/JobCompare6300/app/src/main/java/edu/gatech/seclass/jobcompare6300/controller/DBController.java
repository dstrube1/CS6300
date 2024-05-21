package edu.gatech.seclass.jobcompare6300.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBController extends SQLiteOpenHelper {
    //For logging
    private static final String TAG = DBController.class.getName();
    private String message;
    private final Context context;

    //table names
    private static final String TABLE_NAME_COMPARISON_SETTINGS = "ComparisonSettings";
    private static final String TABLE_NAME_JOBS = "Jobs";
    private static final List<String> tablesNames = new ArrayList<>();
    private static final List<String> comparisonSettingsColumns = new ArrayList<>();
    private static final List<String> jobsColumns = new ArrayList<>();
    private static final String COMPARISON_SETTINGS_KEY = "comparisonSettingsId";
    private static final String JOBS_KEY = "jobId";

    /**
     * Constructor
     *
     * @param context context
     */
    public DBController(Context context) {
        super(context, "jobcompare6300.db", null, 1);

        this.context = context;

        tablesNames.add(TABLE_NAME_COMPARISON_SETTINGS);
        tablesNames.add(TABLE_NAME_JOBS);

        comparisonSettingsColumns.add(COMPARISON_SETTINGS_KEY);
        comparisonSettingsColumns.add("yearlySalaryWeight");
        comparisonSettingsColumns.add("yearlyBonusWeight");
        comparisonSettingsColumns.add("weeklyTeleworkDaysWeight");
        comparisonSettingsColumns.add("leaveTimeWeight");
        comparisonSettingsColumns.add("gymMembershipAllowanceWeight");

        jobsColumns.add(JOBS_KEY);
        jobsColumns.add("title");
        jobsColumns.add("company");
        jobsColumns.add("location");
        jobsColumns.add("costOfLiving");
        jobsColumns.add("yearlySalary");
        jobsColumns.add("yearlyBonus");
        jobsColumns.add("weeklyTeleworkDays");
        jobsColumns.add("leaveTime");
        jobsColumns.add("gymMembershipAllowance");
        jobsColumns.add("isCurrent");

        Log.i(TAG, "DBController constructed");
    }

    /**
     * Create the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating DBController....");
        String query;
        //ComparisonSettings
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_COMPARISON_SETTINGS
                + " ( comparisonSettingsId INTEGER PRIMARY KEY, "
                + "yearlySalaryWeight INTEGER, yearlyBonusWeight INTEGER, "
                + "weeklyTeleworkDaysWeight INTEGER, leaveTimeWeight INTEGER, "
                + "gymMembershipAllowanceWeight INTEGER)";
        db.execSQL(query);

        //Jobs
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_JOBS
                + " ( jobId INTEGER PRIMARY KEY, title TEXT,  "
                + "company TEXT, location TEXT, costOfLiving INTEGER, yearlySalary INTEGER, "
                + "yearlyBonus INTEGER, weeklyTeleworkDays INTEGER, leaveTime INTEGER, "
                + "gymMembershipAllowance INTEGER, isCurrent BOOLEAN)";
        db.execSQL(query);
        //In SQLite v2, there is a datatype BOOLEAN:
        //https://sqlite.org/datatypes.html
        //In v3:
        //https://sqlite.org/datatype3.html
        //"SQLite does not have a separate Boolean storage class.
        // Instead, Boolean values are stored as integers 0 (false) and 1 (true)."

        message = "DBController Created";
        Log.i(TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Upgrade the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "DBController Upgrading....");
        String query;
        for (final String tableName : tablesNames) {
            query = "DROP TABLE IF EXISTS " + tableName;
            db.execSQL(query);
        }
        onCreate(db);

        message = "DBController Upgraded";
        Log.i(TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Insert row
     *
     * @param queryValues query values
     */
    public void insertComparisonSetting(final Map<String, String> queryValues) {
        final List<Map<String, String>> list = getAllComparisonSettings();
        if(!list.isEmpty()){
            message = "ERROR: Trying to insert >1 ComparisonSetting";
            Log.e(TAG, message);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            return;
        }
        final SQLiteDatabase database = this.getWritableDatabase();
        final ContentValues values = new ContentValues();
        for (final String column : comparisonSettingsColumns) {
            values.put(column, queryValues.get(column));
        }

        final long insertRowId = database.insert(TABLE_NAME_COMPARISON_SETTINGS, null, values);

        if (insertRowId != -1) {
            message = "Successfully inserted ComparisonSetting row....";
            Log.i(TAG, message);
        } else {
            message = "ERROR: ComparisonSetting insertRowId  == -1";
            Log.e(TAG, message);
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        database.close();
    }

    /**
     * Insert row
     *
     * @param queryValues query values
     */
    public void insertJob(final Map<String, String> queryValues) {
        final SQLiteDatabase database = this.getWritableDatabase();
        final ContentValues values = new ContentValues();
        for (final String column : jobsColumns) {
            values.put(column, queryValues.get(column));
        }

        final long insertRowId = database.insert(TABLE_NAME_JOBS, null, values);

        if (insertRowId != -1) {
            message = "Successfully inserted Job row....";
            Log.i(TAG, message);
        } else {
            message = "ERROR: Job insertRowId  == -1";
            Log.e(TAG, message);
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        database.close();
    }

    /**
     * Update row
     *
     * @param queryValues query values
     * @return the number of rows affected
     */
    public int updateComparisonSetting(final Map<String, String> queryValues) {
        final SQLiteDatabase database = this.getWritableDatabase();
        final ContentValues values = new ContentValues();

        final List<Map<String, String>> list = getAllComparisonSettings();
        if(list.size() == 0){
            message = "WARNING: Trying to update ComparisonSetting where 0 exist";
            Log.w(TAG, message);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            insertComparisonSetting(queryValues);
            return 1;
        }
        if(list.size() > 1){
            message = "WARNING: Trying to update ComparisonSetting where more than 1 exists";
            Log.w(TAG, message);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//            return;
        }

        for (final String column : comparisonSettingsColumns) {
            if (!column.equals(COMPARISON_SETTINGS_KEY)) {
                values.put(column, queryValues.get(column));
            }
        }
        final int result = database.update(TABLE_NAME_COMPARISON_SETTINGS, values,
                COMPARISON_SETTINGS_KEY + " = ?", new String[]{"1"});
        database.close();
        message = "Updated ComparisonSetting";
        Log.i(TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        return result;
    }

    /**
     * Update row
     *
     * @param queryValues query values
     * @return the number of rows affected
     */
    public int updateJob(final Map<String, String> queryValues, int id) {

        Log.i(TAG, "Updating job " + id);

        final SQLiteDatabase database = this.getWritableDatabase();
        final ContentValues values = new ContentValues();

        final List<Map<String, String>> list = getAllJobs();
        if(list.size() == 0){
            message = "WARNING: Trying to update Job where 0 exist";
            Log.w(TAG, message);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            insertJob(queryValues);
            return 1;
        }

        for (final String column : jobsColumns) {
            if (!column.equals(JOBS_KEY)) {
                values.put(column, queryValues.get(column));
            }
        }

        final int result = database.update(TABLE_NAME_JOBS, values,
                JOBS_KEY + " = ?", new String[]{"" + id});
        database.close();
        message = "Updated job " + id;
        Log.i(TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        return result;
    }

    /**
     * Select * from ComparisonSettings
     *
     * @return list of ComparisonSettings
     */
    public List<Map<String, String>> getAllComparisonSettings() {
        Log.i(TAG, "Getting list of comparison settings");
        final List<Map<String, String>> list = new ArrayList<>();
        final String selectQuery = "SELECT  * FROM " + TABLE_NAME_COMPARISON_SETTINGS;
        final SQLiteDatabase database = this.getWritableDatabase();
        final Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                final Map<String, String> map = new HashMap<>();
                for (int i = 0; i < comparisonSettingsColumns.size(); i++) {
                    map.put(comparisonSettingsColumns.get(i), cursor.getString(i));
                }
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        message = "Got all ComparisonSettings";
        Log.i(TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        return list;
    }

    /**
     * Select * from Jobs
     *
     * @return list of Jobs
     */
    public List<Map<String, String>> getAllJobs() {
        Log.i(TAG, "Getting list of jobs");
        final List<Map<String, String>> list = new ArrayList<>();
        final String selectQuery = "SELECT  * FROM " + TABLE_NAME_JOBS;
        final SQLiteDatabase database = this.getWritableDatabase();
        final Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                final Map<String, String> map = new HashMap<>();
                for (int i = 0; i < jobsColumns.size(); i++) {
                    map.put(jobsColumns.get(i), cursor.getString(i));
                }
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        message = "Got all " + list.size() + "Jobs";
        Log.i(TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        return list;
    }

    /**
     * Select * from Jobs where jobs=id
     *
     * @param id id
     * @return job info
     */
    public Map<String, String> getJob(String id) {
        Log.i(TAG, "Getting job " + id);
        final Map<String, String> map = new HashMap<>();
        final SQLiteDatabase database = this.getReadableDatabase();
        final String selectQuery = "SELECT * FROM " + TABLE_NAME_JOBS + " where " + JOBS_KEY + "='" + id + "'";
        final Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                for (int i = 1; i < jobsColumns.size(); i++) {
                    map.put(jobsColumns.get(i), cursor.getString(i));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        message = "Got job " + id;
        Log.i(TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        return map;
    }

    //VVV
    //WARNING: These are dangerous! Only call these if you're *VERY* sure this is what you want.
    //VVV
    public void deleteAllJobs(){
        Log.i(TAG, "deleteAllJobs");
        final SQLiteDatabase database = this.getWritableDatabase();
        final String deleteQuery = "DELETE FROM " + TABLE_NAME_JOBS;
        database.execSQL(deleteQuery);
        database.close();
        message = "Deleted all jobs: " + getAllJobs().size();
        Log.i(TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void deleteAllComparisonSettings(){
        Log.i(TAG, "deleteAllComparisonSettings");
        final SQLiteDatabase database = this.getWritableDatabase();
        final String deleteQuery = "DELETE FROM " + TABLE_NAME_COMPARISON_SETTINGS;
        database.execSQL(deleteQuery);
        database.close();
        message = "Deleted all ComparisonSettings: " + getAllComparisonSettings().size();
        Log.i(TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
