package com.spacester.mobilicis.device;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.spacester.mobilicis.R;

public class ModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        findViewById(R.id.back).setOnClickListener(view -> onBackPressed());

        DeviceInfo deviceInfo = new DeviceInfo(ModelActivity.this);
        String manufacturer = deviceInfo.getManufacturer();
        String model = deviceInfo.getModel();
        String ram = deviceInfo.getRam();
        String osVersion = Build.VERSION.RELEASE;

        TextView mManufacturer = findViewById(R.id.manufacturer);
        TextView mModel = findViewById(R.id.model);
        TextView mRam = findViewById(R.id.ram);
        TextView verison = findViewById(R.id.verison);



        mManufacturer.setText("Manufacturer : "+ manufacturer);

        mModel.setText("Model : "+ model);

        mRam.setText("Ram : "+ ram);

        verison.setText("OS version : "+ osVersion);


    }
}