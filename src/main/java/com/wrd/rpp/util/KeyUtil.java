package com.wrd.rpp.util;

import java.util.Random;

public class KeyUtil {
   /* 生成唯一主键*/
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        return System.currentTimeMillis()+ String.valueOf(random.nextInt(900000)+100000);
    }
}
