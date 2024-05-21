package edu.gatech.seclass.jobcompare6300.model;

public class User {
    private static User instance;
    private static ComparisonSettings _comparisonSettings;

    private User(){
        _comparisonSettings = new ComparisonSettings();
    }

    public static User getInstance(){
        if (instance == null){
            instance = new User();
        }
        return instance;
    }

    public static ComparisonSettings getComparisonSettings(){
        return _comparisonSettings;
    }

    public static void setComparisonSettings(ComparisonSettings comparisonSettings){
        _comparisonSettings = comparisonSettings;
    }

}
