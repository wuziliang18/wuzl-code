package com.family.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;



public class NumberUtil {

    private NumberUtil(){
        throw new UnsupportedOperationException("do not create NumberUtil object.");
    }

    /**
     * 按照格式将number格式化成相应字符串 如：保留两位小数（“#.00”）
     * 
     * @param number
     * @param pattern
     * @return
     */
    public static String format(Double number, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(number);
    }

    /**
     * 除法，number1为被除数，number2为除数，scale为保留小数位数
     * 
     * @param number1
     * @param number2
     * @param scale
     * @return
     */
    public static Double div(Double number1, Double number2, int scale) {
        BigDecimal b1 = new BigDecimal(number1);
        BigDecimal b2 = new BigDecimal(number2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 除法，number1为被除数，number2为除数，scale为保留小数位数
     * 
     * @param number1
     * @param number2
     * @param scale
     * @return
     */
    public static Double div(Long number1, Long number2, int scale) {
        BigDecimal b1 = new BigDecimal(number1);
        BigDecimal b2 = new BigDecimal(number2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 格式化钱，保留两位小数
     * 
     * @param money 钱的数量，单位已经是元了
     * @return
     */
    public static String formatMoney(double money) {
        return formatMoney(money, false);
    }

    /**
     * 格式化钱，保留两位小数
     * 
     * @param money 钱的数量，单位已经是元了
     * @param thousandsOfPoints 是否需要千分符 true:需要千分符 false:不需要千分符
     * @return
     */
    public static String formatMoney(double money, boolean thousandsOfPoints) {
        if (thousandsOfPoints) {// 如果需要千分符
            return format(money, "#,##0.00");
        } else {// 如果不需要千分符
            return format(money, "0.00");
        }
    }

    /**
     * 判断数值是否介于两个数区间之内，主要用于productId的合理性判断
     * 
     * @param num
     * @return
     */
    public static boolean compareNumber(Long num) {
        int length = num.toString().length();
        if (length > 9 && length < 14) {
            return true;
        }
        return false;
    }

}
