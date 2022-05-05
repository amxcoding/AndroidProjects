package com.example.numbershapes;

public class SquareNumber {

    public boolean checkIfSquare(int n) {
        /*
        check if sqrt(n) is an integer
         */

        double resultX = Math.sqrt(n);

        if (resultX == (int) resultX) {
            return true;
        } else {
            return false;
        }
    }


}
