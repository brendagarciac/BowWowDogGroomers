package com.example.bowwowdoggroomers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    CheckBox bathCheckbox, massageCheckbox, nailsCheckbox;
    TextView displayTextView;
    EditText weightEditText;
    NumberFormat fmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weightEditText = findViewById(R.id.weight_editText);
        displayTextView = findViewById(R.id.display_textView);
        bathCheckbox = findViewById(R.id.bath_checkBox);
        massageCheckbox = findViewById(R.id.massage_checkBox);
        nailsCheckbox = findViewById(R.id.nails_checkBox);
        fmt = NumberFormat.getCurrencyInstance(); //format the currency according to whatever language is on the phone
        displayTextView.setText("$0.00");
    }

    //reset button is clicked, time to put everything back to default!
    public void handleReset(View v){
        resetUI();
    }

    //checkmarks are clicked
    public void handleChecked(View v){
        updateUI();
    }

    //the order button is clicked => display message of success and reset values back to default
    public void handleOrder(View v){
        if(weightEditText.getText().toString().isEmpty()){ //cannot place order if the user does not enter weight!
            Toast.makeText(this, "Invalid action! Enter weight please :(", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Order placed! Thank you :)", Toast.LENGTH_SHORT).show();
        }
        resetUI();
    }

    //the calculate button is clicked => calculate the cost of grooming the dog and display it.
    public void handleCalculate(View v) {
        updateUI();
    }

    //handle reset
    public void resetUI(){
        weightEditText.setText(""); //whenever empty hint will show up
        displayTextView.setText("$0.00");
        bathCheckbox.setChecked(false); //make the checkbox unchecked
        massageCheckbox.setChecked(false);
        nailsCheckbox.setChecked(false);
    }

    //handle the updating of the dog grooming price
    public void updateUI(){
        Double weight, price;
        String result = "";

        try{
            weight = Double.parseDouble(weightEditText.getText().toString());
            if(weight < 30){
                price = 35.00;
            }
            else if(weight >= 30 && weight < 50){
                price = 45.00;
            }
            else{
                price = 55.00;
            }

            //add to the price if a checkbox for a service is checked
            if(bathCheckbox.isChecked()){
                price += 10.00;
            }

            if(nailsCheckbox.isChecked()){
                price += 5.00;
            }

            if(massageCheckbox.isChecked()){
                price += 20.00;
            }

            result = fmt.format(price);
        }catch(NumberFormatException ex){
            result = "Error";
        }finally {
            displayTextView.setText(result);
        }
    }
}