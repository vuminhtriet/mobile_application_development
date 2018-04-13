package com.example.user.latency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btnConvert;
    Button btnClear;
    EditText txtInput;
    EditText txtOutput;
    RadioButton btnUSD1;
    RadioButton btnUSD2;
    RadioButton btnVND1;
    RadioButton btnVND2;
    RadioButton btnEUR1;
    RadioButton btnEUR2;
    //USA money format (12 digits, 2 decimals)
    DecimalFormat usaDf = new DecimalFormat("###,###,###,###.##");
    DecimalFormat eurDf = new DecimalFormat("###,###,###,###.##");
    DecimalFormat vnDf = new DecimalFormat("###,###,###,###");
    // naive currency converter (USD to Euros & Colones)
    private final double EUR2USD = 1.35;
    private final double VND2USD = 0.000044;
    private final double USD2VND = 22727.27;
    private final double USD2EUR = 0.81;
    private final double VND2EUR = 0.000035;
    private final double EUR2VND = 28037.06;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
        setRadioBtn();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtOutput.setText("");
                txtInput.setText("");
            }
        });
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnVND1.isChecked())
                {
                    if(btnVND2.isChecked()){
                        try {
                            String input = txtInput.getText().toString();
                            double moneyIn = Double.parseDouble(input);
                            String moneyOut =
                                    String.valueOf(vnDf.format(moneyIn));
                            txtOutput.setText(moneyOut);
                        } catch (NumberFormatException e) {
                            // ignore errors
                        }
                    }
                    else if(btnUSD2.isChecked()){
                        try {
                            String input = txtInput.getText().toString();
                            double moneyIn = Double.parseDouble(input);
                            String moneyOut =
                                    String.valueOf(usaDf.format(moneyIn * VND2USD));
                            txtOutput.setText(moneyOut);
                        } catch (NumberFormatException e) {
                            // ignore errors
                        }
                    }
                    else if(btnEUR2.isChecked()){
                        try {
                            String input = txtInput.getText().toString();
                            double moneyIn = Double.parseDouble(input);
                            String moneyOut =
                                    String.valueOf(eurDf.format(moneyIn * VND2EUR));
                            txtOutput.setText(moneyOut);
                        } catch (NumberFormatException e) {
                            // ignore errors
                        }
                    }
                }
                if(btnUSD1.isChecked())
                {
                    if(btnVND2.isChecked()){
                        try {
                            String input = txtInput.getText().toString();
                            double moneyIn = Double.parseDouble(input);
                            String moneyOut =
                                    String.valueOf(vnDf.format(moneyIn * USD2VND));
                            txtOutput.setText(moneyOut);
                        } catch (NumberFormatException e) {
                            // ignore errors
                        }
                    }
                    else if(btnUSD2.isChecked()){
                        try {
                            String input = txtInput.getText().toString();
                            double moneyIn = Double.parseDouble(input);
                            String moneyOut =
                                    String.valueOf(usaDf.format(moneyIn));
                            txtOutput.setText(moneyOut);
                        } catch (NumberFormatException e) {
                            // ignore errors
                        }
                    }
                    else if(btnEUR2.isChecked()){
                        try {
                            String input = txtInput.getText().toString();
                            double moneyIn = Double.parseDouble(input);
                            String moneyOut =
                                    String.valueOf(eurDf.format(moneyIn * USD2EUR));
                            txtOutput.setText(moneyOut);
                        } catch (NumberFormatException e) {
                            // ignore errors
                        }
                    }
                }
                if(btnEUR1.isChecked())
                {
                    if(btnVND2.isChecked()){
                        try {
                            String input = txtInput.getText().toString();
                            double moneyIn = Double.parseDouble(input);
                            String moneyOut =
                                    String.valueOf(vnDf.format(moneyIn * EUR2VND));
                            txtOutput.setText(moneyOut);
                        } catch (NumberFormatException e) {
                            // ignore errors
                        }
                    }
                    else if(btnUSD2.isChecked()){
                        try {
                            String input = txtInput.getText().toString();
                            double moneyIn = Double.parseDouble(input);
                            String moneyOut =
                                    String.valueOf(usaDf.format(moneyIn * EUR2USD));
                            txtOutput.setText(moneyOut);
                        } catch (NumberFormatException e) {
                            // ignore errors
                        }
                    }
                    else if(btnEUR2.isChecked()){
                        try {
                            String input = txtInput.getText().toString();
                            double moneyIn = Double.parseDouble(input);
                            String moneyOut =
                                    String.valueOf(eurDf.format(moneyIn));
                            txtOutput.setText(moneyOut);
                        } catch (NumberFormatException e) {
                            // ignore errors
                        }
                    }
                }
            }
        });
    }

    public void setUp(){
        txtInput = (EditText)findViewById(R.id.editText);
        txtOutput = (EditText)findViewById(R.id.editText2);
        txtOutput.setInputType(EditorInfo.TYPE_NULL);
        btnVND1 = (RadioButton) findViewById(R.id.radioButton);
        btnVND2 = (RadioButton) findViewById(R.id.radioButton5);
        btnUSD1 = (RadioButton) findViewById(R.id.radioButton3);
        btnUSD2 = (RadioButton) findViewById(R.id.radioButton4);
        btnEUR1 = (RadioButton) findViewById(R.id.radioButton2);
        btnEUR2 = (RadioButton) findViewById(R.id.radioButton6);
        btnConvert = (Button) findViewById(R.id.button);
        btnClear = (Button) findViewById(R.id.button2);
        btnVND1.setChecked(true);
        btnVND2.setChecked(true);
    }
    public void setRadioBtn(){
        btnVND1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnVND1.isChecked()){
                    btnUSD1.setChecked(false);
                    btnEUR1.setChecked(false);
                }
            }
        });
        btnUSD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnUSD1.isChecked()){
                    btnVND1.setChecked(false);
                    btnEUR1.setChecked(false);
                }
            }
        });
        btnEUR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnEUR1.isChecked()){
                    btnUSD1.setChecked(false);
                    btnVND1.setChecked(false);
                }
            }
        });
        btnVND2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnVND2.isChecked()){
                    btnUSD2.setChecked(false);
                    btnEUR2.setChecked(false);
                }
            }
        });
        btnUSD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnUSD2.isChecked()){
                    btnVND2.setChecked(false);
                    btnEUR2.setChecked(false);
                }
            }
        });
        btnEUR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnEUR2.isChecked()){
                    btnUSD2.setChecked(false);
                    btnVND2.setChecked(false);
                }
            }
        });
    }
}
