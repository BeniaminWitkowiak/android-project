package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ben_a_000 on 2016-05-08.
 */
public class CarsProvider {
    public ArrayList<Car> allCars = new ArrayList<>();
    private int carNumber = 8;
    public CarsProvider(Context context){
        allCars.clear();
        //allCars.add(new Car(1, "Renault", "Laguna", 2.0, 8.0, R.drawable.car1));
        //allCars.add(new Car(2, "Fiat", "Brava", 1.6, 8.5, R.drawable.car2));
        //allCars.add(new Car(3, "Opel", "Corsa", 1.0, 5.0, R.drawable.car3));
        //allCars.add(new Car(4, "Volkswagen", "Polo", 1.4, 5.9, 2009, 2014, R.drawable.polo));
        //allCars.add(new Car(5, "Ople", "Astra", 1.4, 6.9, 1991, 2002, R.drawable.astraf));
        //allCars.add(new Car(6, "BMW", "M5 e39", 4.9, 13.9, 1998, 2004, R.drawable.m5e39));
        //allCars.add(new Car(7, "Alfa", "Romeo", 3.2, 11.5, 2005, 2010, R.drawable.alfa159));
        //allCars.add(new Car(8, "Volvo", "XC 70", 3.0, 10.6, 2009, 2013, R.drawable.xc70));
        //allCars.add(new Car(9, "Opel", "Insignia", 2.8, 11.2, 2008, 2013, R.drawable.insignia));

    }

    public Car getCar(int position){
        return allCars.get(position);
    }

    public int getNumerOfCars(){
        return allCars.size();
    }

    public void addCarProvider(Car car){
        carNumber++;
        allCars.add(car);


    }
}
