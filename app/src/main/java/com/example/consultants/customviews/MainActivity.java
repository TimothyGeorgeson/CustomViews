package com.example.consultants.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.consultants.customviews.customviews.CustomButton;
import com.example.consultants.customviews.customviews.CustomCircle;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements CustomButton.onClickListener{
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private CustomCircle customCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomButton customButton = findViewById(R.id.btnCustom);
        customButton.setCustomClickListener(this);

        //custom circle
        customCircle = findViewById(R.id.customCircle);
    }

    @Override
    public void customOnClick(String customValue) {
        Log.d(TAG, "customOnClick: " + customValue);
        Toast.makeText(this, customValue, Toast.LENGTH_SHORT).show();
    }

    public void onChangeRadius(View view) {

        int randomRadius = (new Random().nextInt(10) + 5) * 10;
        customCircle.setRadius(randomRadius);
    }
}
