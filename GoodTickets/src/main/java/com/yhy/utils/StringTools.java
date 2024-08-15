package com.yhy.utils;

import cn.hutool.core.util.RandomUtil;

public class StringTools {


    //获取随机数字
    public static final String getRandomNumber(Integer length) {
        return RandomUtil.randomNumbers(length);
    }
    //生成随机字符串
    public static final String getRandomString(Integer length) {
        return RandomUtil.randomString(length);
    }
    //判断字符串是否为空
    public static boolean isEmpty(String str) {

        if (null == str || "".equals(str) || "null".equals(str) || "\u0000".equals(str)) {
            return true;
        } else if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }
}
