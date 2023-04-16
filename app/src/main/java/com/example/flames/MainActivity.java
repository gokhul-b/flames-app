package com.example.flames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.security.PrivateKey;
import java.util.HashMap;

import com.google.android.material
        .snackbar
        .Snackbar;


public class MainActivity extends AppCompatActivity {

    private EditText yourname;
    private EditText crushname;
    private TextView ResulttextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        yourname = findViewById(R.id.yourname);
        crushname = findViewById(R.id.crushname);
        Button button = findViewById(R.id.button);
        ResulttextView = findViewById(R.id.ResulttextView);

        button.setOnClickListener(view -> {
            String name1 = yourname.getText().toString();
            String name2 = crushname.getText().toString();
            String result = flames(name1, name2);
            ResulttextView.setText(result);
//            Snackbar.make(findViewById(R.id.ResulttextView), result,
//                            Snackbar.LENGTH_SHORT)
//                    .show();



//            showToast(result);
        });

    }

    private void showToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }


    private String flames(String str1, String str2) {

        String name1 = str1.replaceAll("\\s", "");
        String name2 = str2.replaceAll("\\s", "");

        if (name1.equals("") || name2.equals("")) {
            return "Please enter the name";
        }

        HashMap<Character, Integer> charCountMap1 = new HashMap<>();
        HashMap<Character, Integer> charCountMap2 = new HashMap<>();

        char[] strArray1 = name1.toCharArray();
        for (char c : strArray1) {
            if (charCountMap1.containsKey(c)) {
                charCountMap1.put(c, charCountMap1.get(c) + 1);
            } else {
                charCountMap1.put(c, 1);
            }
        }

        char[] strArray2 = name2.toCharArray();
        for (char c : strArray2) {
            if (charCountMap2.containsKey(c)) {
                charCountMap2.put(c, charCountMap2.get(c) + 1);
            } else {
                charCountMap2.put(c, 1);
            }
        }

        int uniqueCount = 0;
        StringBuilder commonLetters = new StringBuilder();

        for (char i : name1.toCharArray()) {
            for (char j : name2.toCharArray()) {
                if (i == j && !commonLetters.toString().contains(Character.toString(i))) {
                    commonLetters.append(i);
                    uniqueCount += Math.abs(charCountMap1.get(i) - charCountMap2.get(j));
                }
            }
        }

        String combName = name1 + name2;

        for (char i : combName.toCharArray()) {
            if (!commonLetters.toString().contains(Character.toString(i))) {
                uniqueCount += 1;
            }
        }

        String flames = "FLAMES";
        int temp = 6;
        int pop;

        while (temp - 1 > 0) {
            pop = (uniqueCount % temp) - 1;
            System.out.println(flames);
            if (pop == -1) {
                flames = flames.substring(0, flames.length() - 1);
            } else {
                flames = flames.substring(pop + 1) + flames.substring(0, pop);
            }
            temp -= 1;
        }

        HashMap<Character, String> bond = new HashMap<>();
        bond.put('F', "Friends");
        bond.put('L', "Lovers");
        bond.put('A', "Affection");
        bond.put('M', "Marriage");
        bond.put('E', "Enemies");
        bond.put('S', "Sibling");

        char flamesChar = flames.charAt(0);

        return bond.get(flamesChar);
    }

}