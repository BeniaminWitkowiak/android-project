package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ben_a_000 on 2016-05-23.
 */
public class AddNewCarActivity extends ActionBarActivity{
    ImageView photoFromCamera;
    static final int REQUEST_IMAGE_CAPTURE = 0;
    Bitmap imageBitmap;
    private String mImageFileLocation="";
    Bitmap photoCaptureBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_car);

       // Button zrobZdjecie = (Button) findViewById(R.id.buttonAddCarPhoto);
        photoFromCamera = (ImageView) findViewById(R.id.imageViewCarAdd);


        final EditText addAnotherCar_brand_input = (EditText) findViewById(R.id.addAnotherCar_brand_input);
        final EditText addAnotherCar_model_input = (EditText) findViewById(R.id.addAnotherCar_model_input);
        final EditText addAnotherCar_engineSize_input = (EditText) findViewById(R.id.addAnotherCar_engineSize_input);
        final EditText addAnotherCar_averageConsumption_input = (EditText) findViewById(R.id.addAnotherCar_averageConsumption_input);




        // CZYSZCZENIE TEKSTU PO KLIKNIECIU W EDITTEXT
        //*
        addAnotherCar_brand_input.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                addAnotherCar_brand_input.getText().clear();
            }
        });
        addAnotherCar_model_input.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                addAnotherCar_model_input.getText().clear();
            }
        });
        addAnotherCar_engineSize_input.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                addAnotherCar_engineSize_input.getText().clear();
            }
        });
        addAnotherCar_averageConsumption_input.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                addAnotherCar_averageConsumption_input.getText().clear();
            }
        });
        // ***
        // ***

        Button addNewCar = (Button) findViewById(R.id.buttonAddAnotherCar2);
        addNewCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addAnotherCar_brand_input.getText().toString().trim().length() > 0 &&
                        addAnotherCar_model_input.getText().toString().trim().length() > 0 &&
                        addAnotherCar_engineSize_input.getText().toString().trim().length() > 0 &&
                        addAnotherCar_averageConsumption_input.getText().toString().trim().length() > 0){


                    String addAnotherCar_brand = addAnotherCar_brand_input.getText().toString();
                    addAnotherCar_brand = addAnotherCar_brand.substring(0,1).toUpperCase() + addAnotherCar_brand.substring(1).toLowerCase();

                    String addAnotherCar_model = addAnotherCar_model_input.getText().toString();
                    addAnotherCar_model = addAnotherCar_model.substring(0,1).toUpperCase() + addAnotherCar_model.substring(1).toLowerCase();

                    String addAnotherCar_engineSize = addAnotherCar_engineSize_input.getText().toString();

                    String addAnotherCar_averageConsumption = addAnotherCar_averageConsumption_input.getText().toString();


                    Intent intent = new Intent(AddNewCarActivity.this, ListViewActivity.class);
                    intent.putExtra(ListViewActivity.BRAND, addAnotherCar_brand);
                    intent.putExtra(ListViewActivity.MODEL, addAnotherCar_model);
                    intent.putExtra(ListViewActivity.ENGINE_SIZE, addAnotherCar_engineSize);
                    intent.putExtra(ListViewActivity.AVERAGE_CONSUMPTION, addAnotherCar_averageConsumption);

                    //KONWERSJA ZJECIA KTORE OTRZYMALISMY JUZ Z APARATU ZBEY PRZESLAC JE DO LIST VIEW
                    //**
                    //    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    //imageBitmap.compress(Bitmap.CompressFormat.PNG,50, bs);
                      //  intent.putExtra("byteArray", bs.toByteArray());
                    //**

                    //DODAWANIE AUTA DO BAZY
                    Car carToSaveInDatabase = new Car(1,addAnotherCar_brand,addAnotherCar_model,Double.parseDouble(addAnotherCar_engineSize),Double.parseDouble(addAnotherCar_averageConsumption),photoCaptureBitmap);
                    saveCarInDatabase(carToSaveInDatabase, photoCaptureBitmap);



                    startActivity(intent);
                } else{
                    Toast.makeText(AddNewCarActivity.this, " Uzupełnij wszystkie dane! ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void TakePhoto(View view) {
        Intent callCameraApplicationIntent = new Intent();
        callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        File photoFile = null;
        try{
            photoFile = createImageFile();
            //photofile bedzie mialo adres gdzie zapisano plik

        } catch (IOException e){
            e.printStackTrace();
        }
        callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));//camera zapisze wyjście do adresu URI
        startActivityForResult(callCameraApplicationIntent, REQUEST_IMAGE_CAPTURE);
    }

    File createImageFile() throws IOException{
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMAGE_"+timestamp + "_";
        // ? ? ? File storageDirectory = "storage/sdcard1/DCIM/Camera";
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName,".jpg", storageDirectory);
        mImageFileLocation = image.getAbsolutePath(); //field to hold adress, zwroci bezwzgledna sciezke

        return image;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(AddNewCarActivity.this, "Picture taken successfully", Toast.LENGTH_SHORT).show();
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // PONIZEJ KOD DO WYSWIETLENIA ORYGINALNEGO ZDJECIA W PELNEJ ROZDZIECZOSCI
            photoCaptureBitmap = BitmapFactory.decodeFile(mImageFileLocation);
            photoFromCamera.setImageBitmap(photoCaptureBitmap);
        }
    }



    private void saveCarInDatabase(Car car, Bitmap photoCaptureBitmap) {
        Database carDb = new Database(this);
        carDb.addCar(car, photoCaptureBitmap);

        Toast.makeText(this, car.getBrand()+" "+car.getModel()+" saved in database! ", Toast.LENGTH_SHORT).show();
        finish();

        showInformationsFromDatabase();


    }

    public void showInformationsFromDatabase(){
        Database carDb = new Database(this);
        int numberOfCarsInDatabase = carDb.getCarsNumber();
        Toast.makeText(this, "NUMBER OF CARS IN DATABASE: "+numberOfCarsInDatabase, Toast.LENGTH_SHORT).show();
        --numberOfCarsInDatabase;
        //Car carFromDatabase = carDb.getCar(numberOfCarsInDatabase);
        //Toast.makeText(this, carFromDatabase.getBrand()+" FROM DATABASE", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, carFromDatabase.getModel()+" FROM DATABASE", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, carFromDatabase.getEngineSize()+" FROM DATABASE", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, carFromDatabase.getAverageConsumption()+" FROM DATABASE", Toast.LENGTH_SHORT).show();
        
    }
    
   



}
