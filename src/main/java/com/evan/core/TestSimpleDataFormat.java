package com.evan.core;

import com.sun.deploy.util.ArrayUtil;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description
 * @ClassName TestSimpleDataFormat
 * @Author Evan
 * @date 2020.02.20 22:03
 */
public class TestSimpleDataFormat {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");


        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return sdf.parse("20200220");
            }
        };


        Callable<Date> task1 =()-> sdf.parse("20200220");


        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));

        }

        results.forEach(
                s->{
                    try {
                        System.out.println(s.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );

        pool.shutdown();
        }

    }
