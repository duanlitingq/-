package com.yunchao.hsh.utils;

import java.util.Random;

public class IDUtils {
    
    /*
     商品ID生成/订单生成
     */
    public static long genItemId() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end = random.nextInt(99);
        String str = millis + String.format("%2d", end);
        long id = new Long(str);
        return id;
    }
}
