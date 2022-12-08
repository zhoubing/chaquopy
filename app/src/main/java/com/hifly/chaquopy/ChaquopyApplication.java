package com.hifly.chaquopy;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.chaquo.python.android.PyApplication;

public class ChaquopyApplication extends PyApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }
}
