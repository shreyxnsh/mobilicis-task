package com.spacester.mobilicis.device;

import androidx.appcompat.app.AppCompatActivity;

import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

import com.spacester.mobilicis.R;

public class BatteryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        findViewById(R.id.back).setOnClickListener(view -> onBackPressed());

        BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);

        int batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        TextView battery = findViewById(R.id.batteryLevel);
        battery.setText("Battery Level : "+ batteryLevel+"%");

        int batteryStatus = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS);
        String status = "";
        switch (batteryStatus) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                status = "Charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                status = "Discharging";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                status = "Not Charging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                status = "Full";
                break;
        }

        TextView stat = findViewById(R.id.status);
        stat.setText("Battery status : "+ status);


        long timeToFullMillis = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
        long minutes = timeToFullMillis / 60000;

        TextView tm = findViewById(R.id.timeToFullMillis);
        tm.setText("Time to full charge : "+ minutes+" hour");

        String health = "";

        if(BatteryManager.BATTERY_HEALTH_GOOD == 2){
            health = "Good";
        }else if(BatteryManager.BATTERY_HEALTH_OVERHEAT == 3){
            health = "Overheat";
        }else if(BatteryManager.BATTERY_HEALTH_DEAD == 4){
            health = "Dead";
        }else if(BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE == 5){
            health = "Over Voltage";
        }else if(BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE == 6){
            health = "Unspecified Failure";
        }

        TextView healths = findViewById(R.id.health);
        healths.setText("Battery health : "+ health);


        int capacity = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        TextView capacitys = findViewById(R.id.capacity);
        capacitys.setText("Battery capacity : "+ capacity);

        long remainingEnergy = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER);

        TextView remainingEnergys = findViewById(R.id.remainingEnergy);
        remainingEnergys.setText("Remaining Energy : "+ remainingEnergy);

        int currentFlow = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);

        TextView currentFlows = findViewById(R.id.currentFlow);
        currentFlows.setText("Current Flow : "+ currentFlow);

        int currentAverage = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);

        TextView currentAverages = findViewById(R.id.currentAverage);
        currentAverages.setText("Current Average : "+ currentAverage);

        long chargeCounter = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);

        TextView chargeCounters = findViewById(R.id.chargeCounter);
        chargeCounters.setText("Current Counters : "+ chargeCounter);



    }
}