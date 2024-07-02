package com.IXACBANK.TERMINAL;

import java.time.LocalDate;
import java.util.*;
public class Time extends Exceptions{
    public static String returnDate(){
        LocalDate localdate = LocalDate.now();
        return String.valueOf(localdate);
    }
    public static String returnDateAndTime(){
        Calendar calender = new GregorianCalendar();
        return String.valueOf(calender.getTime());
    }
}