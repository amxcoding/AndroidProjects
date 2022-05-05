package com.example.braintrainer;

import android.annotation.SuppressLint;

import java.util.Random;

public class Questions {
    private final Random random;
    private ReturnTwoValues<Integer, String> returnTwoValues;

    private static final int EASY = 50;
    private static final int MEDIUM = 20;
    private static final int HARD = 30;

    public Questions() {
        random = new Random();
    }

    public ReturnTwoValues<Integer, String> getReturnTwoValues() {
        return returnTwoValues;
    }

    public void question(int difficulty) {
        int value1, value2, value3;

        if (difficulty == 0) {
            value1 = random.nextInt(EASY) + 1; // random.nextInt(max - min + 1) + min
            value2 = random.nextInt(EASY) + 1;
            resultOperationEasy(value1, value2);
            resultOperationEasy(value1, value2);

        } else if (difficulty == 1) {
            value1 = random.nextInt(MEDIUM) + 1;
            value2 = random.nextInt(MEDIUM) + 1;
            value3 = random.nextInt(MEDIUM) + 1;
            resultOperationMediumOrHard(value1, value2, value3);
            resultOperationMediumOrHard(value1, value2, value3);

        } else if (difficulty == 2) {
            value1 = random.nextInt(HARD) + 1;
            value2 = random.nextInt(HARD) + 1;
            value3 = random.nextInt(HARD) + 1;
            resultOperationMediumOrHard(value1, value2, value3);
            resultOperationMediumOrHard(value1, value2, value3);
        }
    }

    @SuppressLint("DefaultLocale")
    ReturnTwoValues<Integer, String> resultOperationEasy(int value1, int value2) {
        String question;
        int result;
        int operation = random.nextInt(3) + 1;

        if (operation == 1) {
            result = value1 + value2;
            question = String.format("%d + %d = ", value1, value2);

        } else if (operation == 2) {
            result = value1 - value2;
            question = String.format("%d - %d = ", value1, value2);

        } else {
            result = value1 * value2;
            question = String.format("%d * %d = ", value1, value2);
        }

        returnTwoValues = new ReturnTwoValues<>(result, question);

        return  returnTwoValues;
    }

    @SuppressLint("DefaultLocale")
    ReturnTwoValues<Integer, String> resultOperationMediumOrHard(int value1, int value2, int value3) {
        int operation1 = random.nextInt(3) + 1;
        int operation2 = random.nextInt(3) + 1;
        String question;
        int result;

        if (operation1 == 1) {
            if (operation2 == 1) {
                question = String.format("%d + %d + %d = ?", value1, value2, value3);
                result = value1 + value2 + value3;
            } else if (operation2 == 2) {
                question = String.format("%d + %d - %d = ?", value1, value2, value3);
                result = value1 + value2 - value3;
            } else {
                question = String.format("%d + (%d x %d) = ?", value1, value2, value3);
                result = value1 + (value2 * value3);
            }
        } else if (operation1 == 2) {
            if (operation2 == 1) {
                question = String.format("%d - %d + %d = ?", value1, value2, value3);
                result = value1 - value2 + value3;
            } else if (operation2 == 2) {
                question = String.format("%d - %d - %d = ?", value1, value2, value3);
                result = value1 - value2 - value3;
            } else {
                question = String.format("%d - (%d x %d) = ?", value1, value2, value3);
                result = value1 - (value2 * value3);
            }
        } else {
            if (operation2 == 1) {
                question = String.format("(%d x %d) + %d = ?", value1, value2, value3);
                result = (value1 * value2) + value3;
            } else if (operation2 == 2) {
                question = String.format("(%d x %d) - %d = ?", value1, value2, value3);
                result = (value1 * value2) - value3;
            } else {
                question = String.format("%d x %d x %d = ?", value1, value2, value3);
                result = value1 * value2 * value3;
            }
        }

        returnTwoValues = new ReturnTwoValues<>(result, question);

        return  returnTwoValues;
    }








}
