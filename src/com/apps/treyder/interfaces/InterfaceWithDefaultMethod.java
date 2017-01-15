package com.apps.treyder.interfaces;

/**
 * Created by adminuser on 1/15/17.
 */
public interface InterfaceWithDefaultMethod {
    void someFunc();

    default void someOtherFunc() {
        System.out.println("InterfaceWithDefaultMethod::default");
    }
}
