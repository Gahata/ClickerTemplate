package com.example.pjezi.clickertemplate;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.pjezi.clickertemplate.MainActivity.perSecondValue;

public class AboutActivity extends AppCompatActivity {

    //developer card declarations
    ImageView twitterImage;
    ImageView googleplusImage;
    ImageView playstoreImage;
    TextView twitterText;
    TextView googleplusText;
    TextView playstoreText;

    //translate card declarations
    ImageView translateImage;
    TextView translateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //toolbar code with enabling arrow to previous screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //developer card initializations
        twitterImage = (ImageView) findViewById(R.id.twitter_image);
        googleplusImage = (ImageView) findViewById(R.id.googleplus_image);
        playstoreImage = (ImageView) findViewById(R.id.playstore_image);

        twitterText = (TextView) findViewById(R.id.twitter_textview);
        googleplusText = (TextView) findViewById(R.id.googleplus_textview);
        playstoreText = (TextView) findViewById(R.id.playstore_textview);

        //developer card onclick listeners, one for every image and textview
        twitterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Twitter button successful",Toast.LENGTH_SHORT).show();
            }
        });

        googleplusImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Google+ button successful",Toast.LENGTH_SHORT).show();
            }
        });

        playstoreImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Play Store button successful",Toast.LENGTH_SHORT).show();
            }
        });

        twitterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Twitter button successful",Toast.LENGTH_SHORT).show();
            }
        });

        googleplusText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Google+ button successful",Toast.LENGTH_SHORT).show();
            }
        });

        playstoreText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Play Store button successful",Toast.LENGTH_SHORT).show();
            }
        });

        //translate card initializations
        translateImage = (ImageView) findViewById(R.id.translate_image);
        translateText = (TextView) findViewById(R.id.translate_textview);

        //translate card onlick listeners, one for image and one for textview
        translateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Translate button successful",Toast.LENGTH_SHORT).show();
            }
        });

        translateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Translate button successful",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}