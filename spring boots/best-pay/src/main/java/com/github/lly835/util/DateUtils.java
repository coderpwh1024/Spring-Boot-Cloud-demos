package com.github.lly835.util;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {


    public static String getDate() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());

        System.out.println(dateName);
        return dateName;
    }

    /***
     *  Date 时间转化为字符串
     * @param currentTime
     * @return
     */
    public static String getStringDate(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /***
     *  字符串转date
     * @param dateStr
     * @return
     */
    public static Date getDateString(String dateStr) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /***
     *    在指定时间上添加月份（每月30天）
     * @param num
     * @param currdate
     * @return
     */
    public static String getDateAdd(int num, Date currdate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 现在时间
            Calendar ca = Calendar.getInstance();
            ca.setTime(currdate);

            // num为增加的天数，可以改变的
//            ca.add(Calendar.DATE, num);
            ca.add(Calendar.DAY_OF_YEAR, num);
            currdate = ca.getTime();
            // 添加后的日期
            String enddate = format.format(currdate);
            return enddate;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return "";

    }


    @Test
    public void test() throws ParseException {

        String str = "2021-01-27 15:19:56";
//        add(5,str);
        System.out.println(getDateAdd(3, new Date()));
//        System.out.println(getDateAdd(40,DateUtils.getDateString(str)));

    }


    public static String add(int num, String newDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currdate = format.parse(newDate);
        System.out.println("现在的日期是：" + newDate);
        Calendar ca = Calendar.getInstance();
//        ca.setTime(currdate);

        // num为增加的天数，可以改变的
        ca.add(Calendar.DAY_OF_YEAR, 61);
        currdate = ca.getTime();
        String enddate = format.format(currdate);
        System.out.println("增加天数以后的日期：" + enddate);
        return enddate;

    }


}
