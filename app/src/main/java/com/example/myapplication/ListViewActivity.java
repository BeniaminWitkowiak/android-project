package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ben_a_000 on 2016-05-11.
 */
public class ListViewActivity extends ActionBarActivity {
    public static final String BRAND = "DEFAULT_BRAND";
    public static final String MODEL = "DEFAULT_MODEL";
    public static final String ENGINE_SIZE = "DEFAULT_ENGINE_SIZE";
    public static final String AVERAGE_CONSUMPTION = "DEFAULT_AVERAGE_CONSUMPTION";
    final ListViewAdapter adapter = new ListViewAdapter(this);
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        initializeList();

        Button button = (Button) findViewById(R.id.buttonAddAnotherCar);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(ListViewActivity.this, "DODAWANIE AUTA", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListViewActivity.this, AddNewCarActivity.class);
                startActivity(intent);

            }
        });
    }


    private void initializeList() {

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Car car = adapter.getItem(position);
                double average_consumption_for_CalculateCostsActivity = car.getAverageConsumption();
                showCar(car, average_consumption_for_CalculateCostsActivity);
            }
        });
    }

    private void showCar(Car car, double average_consumption_for_CalculateCostsActivity) {
        Intent intent = new Intent(ListViewActivity.this, CalculateCostsActivity.class);
        Bundle b = new Bundle();
        b.putDouble("avg_consumption", average_consumption_for_CalculateCostsActivity);
        intent.putExtras(b);
        startActivity(intent);

    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        String brand = intent.getStringExtra(BRAND);
        String model = intent.getStringExtra(MODEL);
        double engineSize = Double.parseDouble(intent.getStringExtra(ENGINE_SIZE));
        double averageConsumption = Double.parseDouble(intent.getStringExtra(AVERAGE_CONSUMPTION));
        ImageView imageFromCamera = new ImageView(this);

        adapter.notifyDataSetChanged();


    }



}
