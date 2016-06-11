package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ben_a_000 on 2016-05-13.
 */
public class CalculateAverageConsumptionActivity extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_average_consumption);


        Button calculate = (Button) findViewById(R.id.buttonCalculate_average_consumption);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText distanceInput = (EditText) findViewById(R.id.distance_input2);
                EditText fuelAmountInput = (EditText) findViewById(R.id.fuelPrice_input2);
                TextView yourCarConsumptionValue = (TextView) findViewById(R.id.your_car_consumption_value);

                if (distanceInput.getText().toString().trim().length() > 0 && fuelAmountInput.getText().toString().trim().length() > 0) {
                    double distanceInput_double = Double.parseDouble(distanceInput.getText().toString());
                    double fuelAmountInput_double = Double.parseDouble(fuelAmountInput.getText().toString());
                    double averageConsumption_double = ((fuelAmountInput_double * 100) / distanceInput_double);
                    String yourConsumption = String.format("%.2f", averageConsumption_double);
                    yourCarConsumptionValue.setText(yourConsumption+ "L /100km");
                }
            }
        });
    }


    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
