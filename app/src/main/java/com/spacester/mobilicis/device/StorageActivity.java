package com.spacester.mobilicis.device;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.widget.TextView;

import com.spacester.mobilicis.R;

import java.io.File;

public class StorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        findViewById(R.id.back).setOnClickListener(view -> onBackPressed());

        File externalStorage = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(externalStorage.getPath());
        long blockSize = statFs.getBlockSizeLong();
        long totalBlocks = statFs.getBlockCountLong();
        long availableBlocks = statFs.getAvailableBlocksLong();

        double totalSize = (double) totalBlocks * blockSize / (1024 * 1024 * 1024);
        double availableSize = (double) availableBlocks * blockSize / (1024 * 1024 * 1024);
        double usedSize = totalSize - availableSize;

        TextView available = findViewById(R.id.available);
        available.setText("Available Size: " + availableSize + " GB");

        TextView total = findViewById(R.id.total);
        total.setText("Total Size: " + totalSize + " GB");

        TextView used = findViewById(R.id.used);
        used.setText("Used Size: " + usedSize + " GB");



    }
}