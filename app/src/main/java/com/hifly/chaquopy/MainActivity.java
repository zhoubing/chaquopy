package com.hifly.chaquopy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Python python = Python.getInstance();
        PyObject pythonModule = python.getModule("ws_client");

        new Thread() {
            @Override
            public void run() {
                PyObject pyObject = pythonModule.callAttr("startServer");
            }
        }.start();
//        Log.d("chaquopy", pyObject.toString());
        //register python callback
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            pythonModule.put("java_callback", (Function<String, String>) o -> {
                System.out.println(o);
                return "msg from Java!!!";
            });
        }
        PyObject pyObject1 = pythonModule.callAttr("hello_world", "King of !!!");
        System.out.println(pyObject1.toString());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            pythonModule.put("pass_object_callback", (Function<Object[], Person>) o -> {
//                Person p = o.toJava(Person.class);
//                System.out.println(p);
                for (Object o1 : o) {
                    System.out.println(o1);
                }
                Person p = new Person();
                p.setName("Java Name");
                return p;
            });
        }
        PyObject pyObject2 = pythonModule.callAttr("pass_object");
//        pyObject2.toJava()
//        System.out.println(pyObject2.toJava(Person.class));
    }
}

class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}