package com.vlad.firstcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String newStr){
        String old = display.getText().toString();
        int cursorPos = display.getSelectionStart();

        String left = old.substring(0,cursorPos);
        String right = old.substring(cursorPos);

        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(newStr);

        } else {

            display.setText(String.format("%s%s%s",left,newStr,right));
        }

        display.setSelection(cursorPos + 1);
    }

    public void zeroBTN (View view){
        updateText("0");
    }

    public void oneBTN (View view){
        updateText("1");
    }

    public void twoBTN (View view){
        updateText("2");
    }

    public void threeBTN (View view){
        updateText("3");
    }

    public void fourBTN (View view){
        updateText("4");
    }

    public void fiveBTN (View view){
        updateText("5");
    }

    public void sixBTN (View view){
        updateText("6");
    }

    public void sevenBTN (View view){
        updateText("7");
    }

    public void eightBTN (View view){
        updateText("8");
    }

    public void nineBTN (View view){
        updateText("9");
    }

    public void multiplyBTN (View view){
        updateText("×");
    }

    public void divideBTN (View view){
        updateText("÷");
    }

    public void subtractBTN (View view){
        updateText("-");
    }

    public void addBTN (View view){
        updateText("+");
    }

    public void clearBTN (View view){
        display.setText("");
    }

    public void parBTN (View view){
        int curPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLength = display.getText().length();

        for (int i = 0; i < curPos; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")){
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")){
                closedPar += 1;
            }
        }

        if(openPar == closedPar || display.getText().toString().substring(textLength - 1, textLength).equals("(")){
            updateText("(");
        }
        else if(closedPar < openPar && !display.getText().toString().substring(textLength - 1, textLength).equals("(")){
            updateText(")");
        }
        display.setSelection(curPos + 1);
    }

    public void expBTN (View view){
        updateText("^");
    }

    public void plusMinusBTN(View view){
        updateText("-");
    }

    public void decimalBTN(View view){
        updateText(".");
    }

    public void equalsBTN (View view){
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×","*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void backspaceBTN(View view){
        int curPos = display.getSelectionStart();
        int textLength = display.getText().length();

        if(curPos != 0 && textLength != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(curPos - 1, curPos, "");

            display.setText(selection);
            display.setSelection(curPos - 1);
        }
    }

    public void plusBTN (View view){
        updateText("+");
    }

    public void minusBTN (View view){
        updateText("-");
    }
}