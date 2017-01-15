package com.apps.treyder.interfaces;

import com.apps.treyder.interfaces.ImplementingInterfaceWithDefaultMethod;
import com.apps.treyder.interfaces.ImplementingInterfaceWithStaticMethod;
import com.apps.treyder.interfaces.InterfaceWitStaticMethod;
import com.apps.treyder.interfaces.InterfaceWithDefaultMethod;

public class InterfacesMain {

    public static void main(String[] args) {

        InterfaceWithDefaultMethod interfaceWithDefaultMethod = new ImplementingInterfaceWithDefaultMethod();
        interfaceWithDefaultMethod.someFunc();
        interfaceWithDefaultMethod.someOtherFunc();


        InterfaceWitStaticMethod interfaceWitStaticMethod = new ImplementingInterfaceWithStaticMethod();
        interfaceWitStaticMethod.someFunc();
        InterfaceWitStaticMethod.someStaticFunc();
    }
}
