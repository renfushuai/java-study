package com.rfs.javastudy.modules.study.java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleDateTimeFormatTest {
    private static SimpleDateFormat sd=new SimpleDateFormat("YYYY-MM-dd");

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(()->{
            try {
                System.out.println(sd.parse("2018-05-01"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()->{
            try {
                System.out.println(sd.parse("2018-05-01"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()->{
            try {
                System.out.println(sd.parse("2018-05-01"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }
}
