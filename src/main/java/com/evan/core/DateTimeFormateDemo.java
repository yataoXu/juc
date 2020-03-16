package com.evan.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description
 * @ClassName DateTimeFormate
 * @Author Evan
 * @date 2020.02.20 22:25
 */
public class DateTimeFormateDemo {

    public static void main(String[] args) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task1 =()-> LocalDate.parse("20200220",dtf);


        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task1));

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

    }
    }

