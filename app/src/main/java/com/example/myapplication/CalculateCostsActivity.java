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
import android.widget.Toast;

import java.util.Iterator;

/**
 * Created by ben_a_000 on 2016-05-08.
 */
public class CalculateCostsActivity extends ActionBarActivity {
    public static String AVG_CONSUMPTION = "99.99";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_costs);
        Bundle b = getIntent().getExtras();
        final double avg_consumption = b.getDouble("avg_consumption");

        TextView averageConsumption = (TextView) findViewById(R.id.averageConsumption);
        averageConsumption.setText(Double.toString(avg_consumption));

        //CZY MOZNA TO ZROBIC JAKOS LEPIEJ?
        //NP PRZEKAZAC PRZEZ INTENT NUMER AUTA, potem stwrzyc new provider?
        AVG_CONSUMPTION = Double.toString(avg_consumption);


        Button calculate = (Button) findViewById(R.id.buttonCalculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText distance = (EditText) findViewById(R.id.distance_input);
                EditText fuelPrice = (EditText) findViewById(R.id.fuelPrice_input);

                if (distance.getText().toString().trim().length() > 0 && fuelPrice.getText().toString().trim().length() > 0){
                    final double distance_int = Double.parseDouble(distance.getText().toString());
                    final double fuelPrice_double = Double.parseDouble(fuelPrice.getText().toString());

                    TextView your_costs_value = (TextView) findViewById(R.id.your_costs_value);
                    double costs = (distance_int/100) * fuelPrice_double * Double.parseDouble(AVG_CONSUMPTION);

                    String formatted = String.format("%.2f", costs);
                    your_costs_value.setText(formatted+" zł");
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
