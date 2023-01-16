package com.hifly.chaquopy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import java.lang.annotation.Annotation;
import java.util.function.Function;

@RequiresApi(api = Build.VERSION_CODES.N)
class B implements Function<PyObject, Person> {

    @Override
    public Person apply(PyObject s) {
        System.out.println(s.asMap());
        Person p = new Person();
        p.setName("Java Namenm");
        return p;
    }
}


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
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            A a = new A();
//            pythonModule.put("java_callback", a);
//        }
//        PyObject pyObject1 = pythonModule.callAttr("hello_world", "King of !!!");
//        System.out.println(pyObject1.toString());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Function<Object[], Person> callback = (Function<Object[], Person>) o -> {
                System.out.println(this);
                Person p = new Person();
                p.setName("Java Name");
                return p;
            };
            System.out.println(callback);
            pythonModule.put("pass_object_callback1", callback);
            Function<PyObject, Person> callback1 = new Function<PyObject, Person>() {
                @Override
                public Person apply(PyObject pyObject) {
                    System.out.println(this);
                    System.out.println(pyObject.asMap());
                    Person p = new Person();
                    p.setName("Java Name");
                    return p;
                }
            };
            System.out.println(callback1);
            pythonModule.put("pass_object_callback", callback1);
//            C b = new C();
//            pythonModule.put("pass_object_callback", b);
        }
        PyObject pyObject2 = pythonModule.callAttr("pass_object");
        System.out.println(pyObject2.asMap());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    class C implements Function<PyObject, Person> {

        @Override
        public Person apply(PyObject s) {
            System.out.println(s.asMap());
            Person p = new Person();
            p.setName("Java Namenm");
            return p;
        }
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
