package com.example.braintrainer;

public class ReturnTwoValues<T, U> {

    private T valueOne;
    private U valueTwo;

    public ReturnTwoValues(T valueOne, U valueTwo) {
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    public T getValueOne() {
        return valueOne;
    }

    public U getValueTwo() {
        return valueTwo;
    }
}
