package com.rent_app.rentb;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ccrodrig on 2/1/2016.
 */
public class BikeArrayAdaptor extends ArrayAdapter<Bike> {
    private final List<Bike> bikes;

    public BikeArrayAdaptor(Context context, int resource, List<Bike> bikes) {
        super(context, resource, bikes);
        this.bikes = bikes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.profile_custom_row, null);

        TextView textView = (TextView) row.findViewById(R.id.label);
        textView.setText(bikes.get(position).getName());

        try {
            ImageView imageView = (ImageView) row.findViewById(R.id.icon);
            String filename = bikes.get(position).getFilename();
            InputStream inputStream = getContext().getAssets().open(filename);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }

}
