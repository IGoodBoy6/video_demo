package demo.read.video.web;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author gongchengqiang
 * @Create 2022-04-29-17:12
 */
public class Demo {


    private final static String prefix="htt";

    public static void main(String[] args) {
        System.out.println(transferLongToDate("yyyyMMdd", 1650886812));

        System.out.println("获取当前的月份1："+getCurrentMonth());
        System.out.println("获取当前的月份2："+new Date().getMonth());
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("获取系统当前时间："+System.currentTimeMillis());
        System.out.println(sdf.format(System.currentTimeMillis()));
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println("当前月份："+month);
        System.out.println("当前日历的月份："+calendar.get(Calendar.MONTH));

        System.out.println("日期："+timeStamp2Date("1654654366748", "yyyy-MM-dd"));
        long day = 1L;
        try {
             day = getDay("2020-04-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("时间戳："+day);

        System.out.println("当前日历的年份："+calendar.get(Calendar.YEAR));

        System.out.println("年份："+getYearByDate("1654486247383", "yyyy-MM-dd"));


    }

    public static String transferLongToDate(String dateFormat,long millSec){

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        Date date= new Date(millSec);

        return sdf.format(date);

    }


    public static int getCurrentMonth(){
        int month = new Date().getMonth()+1;
        return month;
    }



    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }


    //当月
    public static long getDay(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date parseTime = format.parse(date);
        return parseTime.getTime();
    }



    public static int getYearByDate(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return 0;
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(Long.valueOf(seconds+"000"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }









}
