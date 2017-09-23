package pl.macavia.howmuchfuel;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;

import static pl.macavia.howmuchfuel.R.id.fuelOnBoard;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the view to listen to
        findViewById(R.id.FuelRequired).setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(getApplicationContext(), "got the focus", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "lost the focus", Toast.LENGTH_SHORT).show();
            }
        });

        // find the view to listen to again
        findViewById(R.id.FuelRequired).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateFuelToAdd();
            }
        });

    }

    public void calculateFuelToAdd() {
        //get the fuel numbers from edit texts
        EditText amount_text = (EditText) findViewById(R.id.fuelOnBoard);
        EditText amount_text2 = (EditText) findViewById(R.id.FuelRequired);
        int fuelRequired = Integer.parseInt(amount_text2.getText().toString());
        int fuelOnBoard = Integer.parseInt(amount_text.getText().toString());

        //calculate the fuel
        int numberOfLiters = (int) Math.ceil((((fuelRequired - fuelOnBoard) * 0.4536) / 0.8) / 10) * 10;
        int numberOfKilograms = (int) Math.ceil((fuelRequired - fuelOnBoard) * 0.4536);
        int numberOfGallons = (int) Math.ceil((numberOfKilograms) / 3.785 / 0.8);

        //Access total fuel text view and create texts
        TextView quantityTextView = (TextView) findViewById(R.id.textView);
        quantityTextView.setText("Add:    \n\n" + numberOfLiters + " liters\n"
                + numberOfKilograms + " kilograms\n"
                + numberOfGallons + " US gallons\n"
                + (fuelRequired - fuelOnBoard) + " pounds");

        //Access textViews over the wings and create texts
        TextView quantityTextViewLeft = (TextView) findViewById(R.id.textViewLeftFuel);
        quantityTextViewLeft.setText(String.valueOf(numberOfLiters / 2) + " l");
        TextView quantityTextViewRight = (TextView) findViewById(R.id.textViewRightFuel);
        quantityTextViewRight.setText(String.valueOf(numberOfLiters / 2) + " l");

        //hide the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
