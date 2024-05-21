package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText dispatchET;
    EditText arg0ET;
    EditText arg1ET;
    Button encipherButton;
    TextView encodedDispatchTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dispatchET = findViewById(R.id.dispatchID);
        arg0ET = findViewById(R.id.arg0ID);
        arg1ET = findViewById(R.id.arg1ID);
        encipherButton = findViewById(R.id.encipherButtonID);
        encodedDispatchTV = findViewById(R.id.encodedDispatchID);
    }

    public void handleClick(View view){
        boolean errorEncountered = false;
        //Set dispatch
        final String dispatch = dispatchET.getText().toString();
        if(dispatch.length() == 0){
            encodedDispatchTV.setText("");//empty dispatch");
            dispatchET.setError("Invalid Dispatch");
            errorEncountered = true;
        }
        boolean containsLetter = false;
        for(int i = 0; i < dispatch.length(); i++){
            char c = dispatch.charAt(i);
            if(Character.isAlphabetic(c)){
                containsLetter = true;
                break;
            }
        }
        if(!containsLetter){
            encodedDispatchTV.setText("");//dispatch not contains letter");
            dispatchET.setError("Invalid Dispatch");
            errorEncountered = true;
        }

        //Set arg0
        final String arg0S = arg0ET.getText().toString();
        if(arg0S.length() == 0){
            encodedDispatchTV.setText("");//empty arg0");
            arg0ET.setError("Invalid Argument 0");
            errorEncountered = true;
        }
        int arg0 = 0;
        final Set<Integer> arg0Values = getArg0Values();
        try{
            arg0 = Integer.parseInt(arg0S);
            if(!arg0Values.contains(arg0)){
                throw new UnsupportedOperationException();
            }
        }catch (NumberFormatException nfe){
            encodedDispatchTV.setText("");//NumberFormatException arg0");
            arg0ET.setError("Invalid Argument 0");
            errorEncountered = true;
        }catch (UnsupportedOperationException uoe){
            encodedDispatchTV.setText("");//UnsupportedOperationException arg0");
            arg0ET.setError("Invalid Argument 0");
            errorEncountered = true;
        }

        //Set arg1
        final String arg1S = arg1ET.getText().toString();
        if(arg1S.length() == 0){
            encodedDispatchTV.setText("");//empty arg1");
            arg1ET.setError("Invalid Argument 1");
            errorEncountered = true;
        }
        int arg1 = 0;
        try{
            arg1 = Integer.parseInt(arg1S);
            if(arg1 < 1 || arg1 > 25){
                throw new UnsupportedOperationException();
            }
        }catch (NumberFormatException nfe){
            encodedDispatchTV.setText("");//NumberFormatException arg1");
            arg1ET.setError("Invalid Argument 1");
            errorEncountered = true;
        }catch (UnsupportedOperationException uoe){
            encodedDispatchTV.setText("");//UnsupportedOperationException arg1");
            arg1ET.setError("Invalid Argument 1");
            errorEncountered = true;
        }
        if(errorEncountered){
            return;
        }

        final Map<Character, Integer> letterToNumberMap = getLetterToNumberMap();
        final Map<Integer, Character> numberToLetterMap = getNumberToLetterMap(letterToNumberMap);

        //Begin encryption
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < dispatch.length(); i++){
            final char c = dispatch.charAt(i);
            if(Character.isAlphabetic(c)){
                int x = letterToNumberMap.get(Character.toLowerCase(c));
                int encryptedNumber = getEncryptedNumber(arg0, arg1, x);
                char encryptedLetter = numberToLetterMap.get(encryptedNumber);
                if(Character.isUpperCase(c)){
                    output.append(encryptedLetter);
                }else{
                    output.append(Character.toUpperCase(encryptedLetter));
                }
            }else{
                output.append(c);
            }
        }
        encodedDispatchTV.setText(output.toString());
    }

    private Set<Integer> getArg0Values(){
        final Set<Integer> arg0Values = new HashSet<>();
        arg0Values.add(1);
        arg0Values.add(3);
        arg0Values.add(5);
        arg0Values.add(7);
        arg0Values.add(9);
        arg0Values.add(11);
        arg0Values.add(15);
        arg0Values.add(17);
        arg0Values.add(19);
        arg0Values.add(21);
        arg0Values.add(23);
        arg0Values.add(25);
        return arg0Values;
    }

    private Map<Character, Integer> getLetterToNumberMap(){
        final Map<Character, Integer> map = new HashMap<>();
        map.put('a',0);
        map.put('b',1);
        map.put('c',2);
        map.put('d',3);
        map.put('e',4);
        map.put('f',5);
        map.put('g',6);
        map.put('h',7);
        map.put('i',8);
        map.put('j',9);
        map.put('k',10);
        map.put('l',11);
        map.put('m',12);
        map.put('n',13);
        map.put('o',14);
        map.put('p',15);
        map.put('q',16);
        map.put('r',17);
        map.put('s',18);
        map.put('t',19);
        map.put('u',20);
        map.put('v',21);
        map.put('w',22);
        map.put('x',23);
        map.put('y',24);
        map.put('z',25);
        return map;
    }

    private Map<Integer, Character> getNumberToLetterMap(Map<Character, Integer> inMap) {
        final Map<Integer, Character> map = new HashMap<>();
        for(Map.Entry<Character, Integer> entry:  inMap.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }
        return map;
    }

    private int getEncryptedNumber(int a, int b, int x){
        return ((a * x) + b) % 26;
    }
}