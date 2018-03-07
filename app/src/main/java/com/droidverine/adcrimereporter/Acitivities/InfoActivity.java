package com.droidverine.adcrimereporter.Acitivities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.droidverine.adcrimereporter.R;

public class InfoActivity extends AppCompatActivity {
TextView otherinfo,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        Intent intent=getIntent();
        final ImageView imageView=(ImageView)findViewById(R.id.victimimgfull);
        collapsingToolbarLayout.setTitle(intent.getStringExtra("name"));
        Glide.with(getApplicationContext()).load(intent.getStringExtra("url"))
                .listener(new RequestListener<Drawable>()
                {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource)
                    {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource)
                    {
                        Log.d("Glide", "resource ready");
                        imageView.setBackgroundResource(0);
                        return false;
                    }

                })
                .into(imageView);
        // toolbar.setTitle(intent.getStringExtra("name"));
        otherinfo=(TextView)findViewById(R.id.otherinfotxt);
        location=(TextView)findViewById(R.id.locationinfo);
        location.setText(intent.getStringExtra("location"));
        otherinfo.setText(intent.getStringExtra("otherinfo"));


    }
}
