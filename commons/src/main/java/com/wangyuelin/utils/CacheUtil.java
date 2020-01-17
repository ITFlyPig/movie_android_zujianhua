package com.wangyuelin.utils;

import android.text.TextUtils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import io.github.prototypez.appjoint.commons.AppBase;

public class CacheUtil {


    /**
     * 将对象持久化到文件
     *
     * @param t
     * @param fileName
     * @param <T>
     */
    public static <T> void saveObj(T t, String fileName) {
        if (TextUtils.isEmpty(fileName) || t == null) {
            return;
        }
        String path = AppBase.INSTANCE.getFilesDir() + File.separator + fileName;
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.writeObject(t);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将近期的数据持久化到磁盘
     *
     * @param t
     * @param <T>
     */
    public static <T> void saveRecentData(T t) {
        saveObj(t, "recent_data");

    }

    public static <T> T getRecentData() {
        return getObj("recent_data");
    }

    /**
     * 将持久化的对象读取
     * @param fileName
     * @param <T>
     * @return
     */
    public static <T> T getObj(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }
        String path = AppBase.INSTANCE.getFilesDir() + File.separator + fileName;
        File targetFile = new File(path);
        if (!targetFile.exists()) {
            return null;
        }
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new FileInputStream(targetFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (is == null) {
            return null;
        }
        try {
            T t = (T) is.readObject();
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
