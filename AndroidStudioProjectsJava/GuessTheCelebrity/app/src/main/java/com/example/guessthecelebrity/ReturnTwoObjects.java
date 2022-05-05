package com.example.guessthecelebrity;

import java.util.List;

public class ReturnTwoObjects<U, V> {

    private U object1;
    private V object2;

    public ReturnTwoObjects(U object1, V object2) {
        this.object1 = object1;
        this.object2 = object2;
    }

    public U getObject1() {
        return object1;
    }

    public V getObject2() {
        return object2;
    }
}
