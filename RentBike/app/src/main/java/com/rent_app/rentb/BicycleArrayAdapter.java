package com.rent_app.rentb;

import android.content.Context;
import android.graphics.Bitmap;
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
//byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
public class BicycleArrayAdapter extends ArrayAdapter<Bicycle> {
    private final List<Bicycle> bicycles;

    public BicycleArrayAdapter(Context context, int resource, List<Bicycle> bicycles) {
        super(context, resource, bicycles);
        this.bicycles = bicycles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_list_of_bikes, null);

        //Gets info for title
        TextView textView = (TextView) row.findViewById(R.id.label);
        textView.setText(bicycles.get(position).getName());

        //Gets info for subtitle
        TextView subs = (TextView) row.findViewById(R.id.sublabel);
        subs.setText(bicycles.get(position).getSubtitle());




            ImageView imageView = (ImageView) row.findViewById(R.id.icon);
            Bitmap filename = bicycles.get(position).getFilename();
            imageView.setImageBitmap(filename);
//            InputStream inputStream = getContext().getAssets().open(filename);
//            Drawable drawable = Drawable.createFromStream(inputStream, null);
//            imageView.setImageDrawable(drawable);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return row;
    }
}
