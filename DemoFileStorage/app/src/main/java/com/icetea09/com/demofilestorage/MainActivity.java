package com.icetea09.com.demofilestorage;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String WRITE_INTERNAL_DATA = "Why is the alphabet arranged the way it is? " +
            "How many words are there in the English language? Get the answers to some intriguing " +
            "language questions.";
    private static final String WRITE_EXTERNAL_DATA = "How do we decide whether a new word should " +
            "be included in an Oxford dictionary?\n\n Discover how we monitor and research language " +
            "in order to decide which words to include in our dictionaries.";
    private static final String FILE_NAME = "demo_file_storage.data";

    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_write_internal).setOnClickListener(this);
        findViewById(R.id.btn_read_internal).setOnClickListener(this);
        findViewById(R.id.btn_write_external).setOnClickListener(this);
        findViewById(R.id.btn_read_external).setOnClickListener(this);
        mTvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btn_write_internal:
                    FileStorageHelper.writeToInternalStorage(this, FILE_NAME, WRITE_INTERNAL_DATA.getBytes());
                    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_read_internal:
                    mTvResult.setText(new String(FileStorageHelper.readFromInternalStorage(this, FILE_NAME)));
                    break;
                case R.id.btn_write_external:
                    if (FileStorageHelper.isExternalStorageWritable()) {
                        FileStorageHelper.writeToExternalPrivateFile(this, null, FILE_NAME, WRITE_EXTERNAL_DATA.getBytes());
                        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "External storage is not available!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_read_external:
                    if (FileStorageHelper.isExternalStorageReadable()) {
                        mTvResult.setText(new String(FileStorageHelper.readFromExternalPrivateFile(this, null, FILE_NAME)));
                    } else {
                        Toast.makeText(this, "External storage is not available!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
