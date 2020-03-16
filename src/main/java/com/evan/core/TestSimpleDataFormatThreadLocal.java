package com.evan.core;

import javafx.scene.input.DataFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @Description
 * @ClassName TestSimpleDataFormatThreadLocal
 * @Author Evan
 * @date 2020.02.20 22:16
 */
public class TestSimpleDataFormatThreadLocal {

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<>();
        }
