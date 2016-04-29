package com.rent_app.rentb;

import android.graphics.Bitmap;

/**
 * Created by ccrodrig on 2/1/2016.
 */
public class Bicycle {

        private String name;
        private Bitmap filename;
        private String subtitle;
        private String key;//added

        public Bicycle(String name, Bitmap filename, String subtitle, String key) {
            this.name = name;
            this.filename = filename;
            this.subtitle = subtitle;
            this.key = key;
        }

        public String getName() {return name;}

        public Bitmap getFilename() {
            return filename;
        }

        public String getSubtitle(){
            return subtitle;
        }

        public String getKey(){return key;}


}
