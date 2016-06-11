package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ben_a_000 on 2016-05-11.
 */
public class ListViewAdapter extends BaseAdapter{

    //private CarsProvider provider;
    private Database provider;
    private Context context;

    public ListViewAdapter(Context context) {
        this.context = context;
        this.provider = new Database(context);
    }

    public ListViewAdapter(Database provider) {
        this.provider = provider;
    }

    public int getCount(){
        return this.provider.getCarsNumber();
    }

    public Car getItem(int position){
        return provider.getCar(position);
    }

    @Override
    public long getItemId(int position) {
        return this.provider.getCar(position).getCarID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View carView;

        if (convertView == null) {
            carView = LayoutInflater.from(context).inflate(R.layout.list_view_row, parent, false);
        } else {
            carView = convertView;
        }

        bindRecipeToView(getItem(position), carView, position);

        return carView;
    }

    private void bindRecipeToView(Car car, View carView, int position) {
        ImageView recipePhoto = (ImageView) carView.findViewById(R.id.car1);
        //recipePhoto.setImageResource(car.getPhotoNumber());
        recipePhoto.setImageBitmap(car.getPhotoNumber());

        TextView recipeLabel = (TextView) carView.findViewById(R.id.listview_engine_size_value);
        recipeLabel.setText(Double.toString(car.getEngineSize()));

        TextView recipeRating = (TextView) carView.findViewById(R.id.car_name);
        String recipeRatingText = car.getBrand()+ " " + car.getModel();
        recipeRating.setText(recipeRatingText);


        TextView avgConsumptionValue = (TextView) carView.findViewById(R.id.listview_avg_consumption_value);
        avgConsumptionValue.setText(Double.toString(car.getAverageConsumption()));
    }


    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }




}
