package com.codewhy.common.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 生成订单id 时间（精确到毫秒）+3位随机数+5位用户id
 */
@Slf4j
public class getOrderID {
    public static String getOrderNum(String userId) {
        //时间（精确到毫秒）
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String localDate = LocalDateTime.now().format(ofPattern);
        //3位随机数
        String randomNumeric = String.valueOf(new Random().nextInt(3));
        String sUserId = userId.toString();
        String orderNum = localDate + randomNumeric + sUserId;
        log.info("订单号:{}", orderNum);
        return orderNum;
    }
}