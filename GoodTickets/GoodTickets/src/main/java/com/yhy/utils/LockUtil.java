package com.yhy.utils;

import java.util.concurrent.ConcurrentHashMap;

public class LockUtil {

    private final static ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();

    private final static Object VALUE = new Object();


    public static boolean lock(String lock) {
        return locks.putIfAbsent(lock, VALUE) == null;
    }
    public static void unlock(String lock) {
        locks.remove(lock);
    }

}
