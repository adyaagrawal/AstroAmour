package com.example.astroamour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ItemDetails extends AppCompatActivity {
    String img_url,img_title,img_date,img_des,img_copy;
    ImageView img;
    TextView date,title,des,copy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        if(getIntent().hasExtra("image_url")&& getIntent().hasExtra("image_title")
            && getIntent().hasExtra("image_date")
        && getIntent().hasExtra("image_des")&& getIntent().hasExtra("image_copy")){
            img_url=getIntent().getStringExtra("image_url");
            img_title=getIntent().getStringExtra("image_title");
            img_date=getIntent().getStringExtra("image_date");
            img_des=getIntent().getStringExtra("image_des");
            img_copy=getIntent().getStringExtra("image_copy");
        }
        img=findViewById(R.id.imagepic);
        date=findViewById(R.id.imgdate);
        title=findViewById(R.id.ititle);
        des=findViewById(R.id.iexplanation);
        copy=findViewById(R.id.icopyright);

        date.setText(img_date);
        title.setText(img_title);
        des.setText(img_des);
        copy.setText("Copyright: "+img_copy);
        Picasso.with(getBaseContext()).load(img_url).into(img);


    }
}