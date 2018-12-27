package com.example.sinas.newcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnDot;
    private Button btnC;
    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;
    private Button btnEq;

    private TextView textView;

    private double num1;
    private double num2;
    private char operation;
    boolean newOp = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
        setListeners();

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("0");
            }
        });

        btnEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String statement = textView.getText().toString();
                String[] operations = {"+", "-", "/", "*"};

                    for(int j=0; j<operations.length; j++){
                        if(statement.contains(operations[j])){
                            int i = statement.indexOf(operations[j]);
                            operation = statement.charAt(i);
                            num1 = Double.parseDouble(statement.substring(0, i));
                            num2 = Double.parseDouble(statement.substring(i+1, statement.length()));

                            calculate();
                            newOp = true;
                            break;
                        }
                    }
            }
        });
    }

    private void setViews(){
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnDot = (Button) findViewById(R.id.btnDot);
        btnC = (Button) findViewById(R.id.btnC);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnEq = (Button) findViewById(R.id.btnEq);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void setListeners(){

        btn0.setOnClickListener(buttonListener);
        btn1.setOnClickListener(buttonListener);
        btn2.setOnClickListener(buttonListener);
        btn3.setOnClickListener(buttonListener);
        btn4.setOnClickListener(buttonListener);
        btn5.setOnClickListener(buttonListener);
        btn6.setOnClickListener(buttonListener);
        btn7.setOnClickListener(buttonListener);
        btn8.setOnClickListener(buttonListener);
        btn9.setOnClickListener(buttonListener);
        btnDot.setOnClickListener(buttonListener);
        btnAdd.setOnClickListener(buttonListener);
        btnSub.setOnClickListener(buttonListener);
        btnMul.setOnClickListener(buttonListener);
        btnDiv.setOnClickListener(buttonListener);
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            if((newOp) || textView.getText().equals("0")){
                if(!(b.getText().equals("+") || b.getText().equals("-") || b.getText().equals("*") || b.getText().equals("/"))){
                    textView.setText(b.getText());
                    newOp = false;
                }
                else{
                    textView.setText("0");
                }
            }
            else if((b.getText().equals("+") || b.getText().equals("-") || b.getText().equals("*") || b.getText().equals("/")) && (textView.getText().charAt(textView.length() - 1) == '+' || textView.getText().charAt(textView.length() - 1) == '-' || textView.getText().charAt(textView.length() - 1) == '*' || textView.getText().charAt(textView.length() - 1) == '/')) {
                        textView.setText(textView.getText().toString().replace(textView.getText().charAt(textView.getText().length() - 1), b.getText().charAt(0)));
            }
            else if((b.getText().equals("+") || b.getText().equals("-") || b.getText().equals("*") || b.getText().equals("/")) && (textView.getText().toString().contains("+") || textView.getText().toString().contains("-") || textView.getText().toString().contains("*") || textView.getText().toString().contains("/"))){
                textView.setText(textView.getText());
            }
            else{
                textView.setText(textView.getText().toString() + b.getText());
            }
        }
    };

    private void calculate(){
        if(operation == '+'){
            textView.setText(add(num1, num2));
        }
        if(operation == '-'){
            textView.setText(subtract(num1, num2));
        }
        if(operation == '*'){
            textView.setText(multiply(num1, num2));
        }
        if(operation == '/'){
            textView.setText(divide(num1, num2));
        }
    }

    private String add(double a, double b){
        return String.valueOf(a + b);
    }

    private String subtract(double a, double b){
        return String.valueOf(a - b);
    }
    private String multiply(double a, double b){
        return String.valueOf(a * b);
    }
    private String divide(double a, double b){
        if(b == 0){
            return "Error";
        }
        else{
            return String.valueOf(a / b);
        }
    }
}
