package com.apps.treyder.interfaces;

/**
 * Created by adminuser on 1/15/17.
 */
public interface InterfaceWitStaticMethod {

    void someFunc();

    static void someStaticFunc() {
        System.out.println("Some static func");
    }
}
