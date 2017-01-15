package com.apps.treyder.interfaces;

/**
 * Created by adminuser on 1/15/17.
 */
public class ImplementingInterfaceWithDefaultMethod implements InterfaceWithDefaultMethod {
    @Override
    public void someFunc() {
        System.out.println("implemented only one method");
    }
}
