package com.quietboy.easydialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.quietboy.easydialog.widget.AlertDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AlertDialog.Builder(this).setTitle("dfgdfg")
                .setMessage("dgsfdg").show();

    }
}
