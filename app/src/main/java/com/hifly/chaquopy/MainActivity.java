package com.hifly.chaquopy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {
            @Override
            public void run() {
                Python python = Python.getInstance();
                PyObject pythonModule = python.getModule("ws_client");
                PyObject pyObject = pythonModule.callAttr("startServer");
            }
        }.start();
//        Log.d("chaquopy", pyObject.toString());
    }
}