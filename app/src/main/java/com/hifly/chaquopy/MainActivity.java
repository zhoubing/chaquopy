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

        Python python = Python.getInstance();
        PyObject pythonModule = python.getModule("ftp_server");
        PyObject pyObject = pythonModule.callAttr("startServer");
//        Log.d("chaquopy", pyObject.toString());
    }
}