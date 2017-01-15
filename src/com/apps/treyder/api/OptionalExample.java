package com.apps.treyder.api;

import java.util.Optional;

/**
 * Created by adminuser on 1/15/17.
 */
public class OptionalExample {

    private static class SomeClass {
        public void someFunc() {
            System.out.println("some func");
        }
    }

    Optional<SomeClass> someEmptyClass = Optional.empty();

    Optional<SomeClass> someNonEmptyClass = Optional.of(new SomeClass());

    Optional<String> someString = Optional.of("some string");

    public void run() {

        someString.ifPresent(System.out::println);

        someEmptyClass.ifPresent(SomeClass::someFunc);

        someNonEmptyClass.ifPresent(SomeClass::someFunc);
    }

    public static void main(String [] args) {

        OptionalExample optionalExample = new OptionalExample();
        optionalExample.run();
    }

}
