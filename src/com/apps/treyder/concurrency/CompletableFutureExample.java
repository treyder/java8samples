package com.apps.treyder.concurrency;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by adminuser on 1/15/17.
 */
public class CompletableFutureExample {

    private void printCompleteRoute(List<String> route) {
        System.out.println("CompletableFutureExample::printCompleteRoute");

        for (String s : route) {
            System.out.println(s);
        }
    }

    private List<String> calculateMainRoute() {
        List<String> route = new LinkedList<>();
        route.add("GDN");
        route.add("KRT");
        route.add("KRK");
        return route;
    }

    private List<String> calculateAlternateRoute() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> route = new LinkedList<>();
        route.add("KRK");
        route.add("WAW");
        return route;
    }

    private List<String> composeRoute(List<String> list1, List<String> list2) {
        return Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
    }

    public void sendAsyncMessage() {

        CompletableFuture<List<String>> mainRoute = CompletableFuture.supplyAsync(this::calculateMainRoute);

        CompletableFuture<List<String>> alternateRoute = CompletableFuture.supplyAsync(this::calculateAlternateRoute);

        mainRoute.thenCombine(alternateRoute, this::composeRoute).thenAccept(this::printCompleteRoute);;
    }

    public static void main(String [] args) {
        CompletableFutureExample completableFutureExample = new CompletableFutureExample();
        completableFutureExample.sendAsyncMessage();
    }
}
