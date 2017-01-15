package com.apps.treyder.api;

import java.time.LocalDateTime;

/**
 * Created by adminuser on 1/15/17.
 */
public class DateTimeExample {

    public static void main(String [] args) {

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        System.out.println("local date: " + localDateTime.toLocalDate());
        System.out.println("local time: " + localDateTime.toLocalTime());

        System.out.println("two weeks from now: " + localDateTime.plusWeeks(2));

        LocalDateTime fixedDateTime = LocalDateTime.of(2017, 1, 1, 0, 0);
        System.out.println(fixedDateTime);

        LocalDateTime parsedDateTime = LocalDateTime.parse("2015-02-24T15:20");
        System.out.println(parsedDateTime);




    }
}
