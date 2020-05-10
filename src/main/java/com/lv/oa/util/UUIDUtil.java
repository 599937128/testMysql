package com.lv.oa.util;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取uuid
     * @return
     */
    public static String getUuid()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
