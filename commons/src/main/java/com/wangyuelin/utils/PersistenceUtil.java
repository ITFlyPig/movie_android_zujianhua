package com.wangyuelin.utils;

import android.content.Context;
import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Set;

/**
 * author : yuelinwang
 * time   : 2020-01-07 15:08
 * desc   : 数据持久化工具，中间层，屏蔽实现方式，（实现可以使SP、MMKV等）。可以保存基本数据和实现序列化接口的对象
 */
public class PersistenceUtil {

    /**
     * 初始化
     *
     * @param context
     */
    public static void initialize(Context context) {
        MMKV.initialize(context);
    }

    //////////////////保存数据start////////////////////////
    public static void save(String key, int value) {
        MMKV mk = MMKV.defaultMMKV();
        mk.encode(key, value);
    }

    public static void save(String key, int value, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        mk.encode(key, value);
    }

    public static void save(String key, float value) {
        MMKV mk = MMKV.defaultMMKV();
        mk.encode(key, value);
    }

    public static void save(String key, float value, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        mk.encode(key, value);
    }

    public static void save(String key, boolean value) {
        MMKV mk = MMKV.defaultMMKV();
        mk.encode(key, value);
    }

    public static void save(String key, boolean value, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        mk.encode(key, value);
    }

    public static void save(String key, long value) {
        MMKV mk = MMKV.defaultMMKV();
        mk.encode(key, value);
    }

    public static void save(String key, long value, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        mk.encode(key, value);
    }

    public static void save(String key, double value) {
        MMKV mk = MMKV.defaultMMKV();
        mk.encode(key, value);
    }

    public static void save(String key, double value, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        mk.encode(key, value);
    }

    public static void save(String key, String value) {
        MMKV mk = MMKV.defaultMMKV();
        mk.encode(key, value);
    }

    public static void save(String key, String value, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        mk.encode(key, value);
    }

    public static void save(String key, byte[] value) {
        MMKV mk = MMKV.defaultMMKV();
        mk.encode(key, value);
    }

    public static void save(String key, byte[] value, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        mk.encode(key, value);

    }

    public static void save(String key, Set<String> value) {
        MMKV mk = MMKV.defaultMMKV();
        mk.encode(key, value);
    }

    public static void save(String key, Set<String> value, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        mk.encode(key, value);
    }

    public static void save(String key, Parcelable value) {
        MMKV mk = MMKV.defaultMMKV();
        mk.encode(key, value);
    }

    public static void save(String key, Parcelable value, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        mk.encode(key, value);

    }

    public static void save(String key, Serializable value) {
        byte[] byteArray = toByteArray(value);
        if (byteArray == null) {
            return;
        }
        MMKV mk = MMKV.defaultMMKV();
        mk.encode(key, byteArray);
    }

    public static void save(String key, Serializable value, String bizName) {
        byte[] byteArray = toByteArray(value);
        if (byteArray == null) {
            return;
        }
        MMKV mk = MMKV.mmkvWithID(bizName);
        mk.encode(key, byteArray);

    }

    private static byte[] toByteArray(Serializable obj) {
        if (obj == null) {
            return null;
        }
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;

    }

    //////////////////保存数据end////////////////////////

    //////////////////获取数据start////////////////////////

    public static int getInt(String key) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeInt(key);
    }

    public static int getInt(String key, int defValue) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeInt(key, defValue);
    }

    public static int getInt(String key, int defValue, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeInt(key, defValue);
    }

    public static int getInt(String key, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeInt(key);
    }

    public static boolean getBool(String key) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeBool(key);
    }

    public static boolean getBool(String key, boolean defValue) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeBool(key, defValue);
    }

    public static boolean getBool(String key, boolean defValue, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeBool(key, defValue);
    }

    public static boolean getBool(String key, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeBool(key);
    }


    public static long getLong(String key) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeLong(key);
    }

    public static long getLong(String key, long defValue) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeLong(key, defValue);
    }

    public static long getLong(String key, long defValue, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeLong(key, defValue);
    }

    public static long getLong(String key, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeLong(key);
    }

    public static float getFloat(String key) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeFloat(key);
    }

    public static float getFloat(String key, float defValue) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeFloat(key, defValue);
    }

    public static float getFloat(String key, float defValue, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeFloat(key, defValue);
    }

    public static float getFloat(String key, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeFloat(key);
    }

    public static double getDouble(String key) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeDouble(key);
    }

    public static double getDouble(String key, double defValue) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeDouble(key, defValue);
    }

    public static double getDouble(String key, double defValue, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeDouble(key, defValue);
    }

    public static double getDouble(String key, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeDouble(key);
    }

    public static String getStr(String key) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeString(key);
    }

    public static String getStr(String key, String defValue) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeString(key, defValue);
    }

    public static String getStr(String key, String defValue, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeString(key, defValue);
    }

    public static byte[] getBytes(String key) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeBytes(key);
    }

    public static byte[] getBytes(String key, byte[] defValue) {
        MMKV mk = MMKV.defaultMMKV();
        return mk.decodeBytes(key, defValue);
    }

    public static byte[] getBytes(String key, byte[] defValue, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeBytes(key, defValue);
    }

    public static byte[] getBytes(String key, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        return mk.decodeBytes(key);
    }

    public static <T> T getObj(String key) {
        MMKV mk = MMKV.defaultMMKV();
        byte[] bytes = mk.decodeBytes(key);
        return toObject(bytes);
    }

    public static <T> T getObj(String key, Serializable defValue) {
        MMKV mk = MMKV.defaultMMKV();
        byte[] bytes = mk.decodeBytes(key);
        if (bytes == null && defValue != null) {
            bytes = toByteArray(defValue);
        }
        return toObject(bytes);
    }

    public static <T> T getObj(String key, Serializable defValue, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        byte[] bytes = mk.decodeBytes(key);
        if (bytes == null && defValue != null) {
            bytes = toByteArray(defValue);
        }
        return toObject(bytes);
    }

    public static <T> T getObj(String key, String bizName) {
        MMKV mk = MMKV.mmkvWithID(bizName);
        byte[] bytes = mk.decodeBytes(key);
        return toObject(bytes);
    }

    /**
     * 将字节数组转为对象
     * @param bytes
     * @param <T>
     * @return
     */
    private static <T> T toObject(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            return (T) obj;
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            ex.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
