package com.quranapp.islamic.utils.thread.tasks;

import android.os.Build;
import androidx.annotation.NonNull;

import java.net.HttpURLConnection;

public abstract class BaseRunnableTask implements TaskRunnable {
    private boolean mDone;

    @Override
    public void preExecute() {
    }

    @Override
    public void onProgress(long currentLength, long totalLength) {
    }

    @Override
    public void onFailed(@NonNull Exception e) {
        e.printStackTrace();
    }

    @Override
    public void postExecute() {
    }

    @Override
    public boolean isDone() {
        return mDone;
    }

    @Override
    public void setDone(boolean done) {
        mDone = true;
    }

    protected long getContentLength(HttpURLConnection conn) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return conn.getContentLengthLong();
        } else {
            return conn.getContentLength();
        }
    }
}