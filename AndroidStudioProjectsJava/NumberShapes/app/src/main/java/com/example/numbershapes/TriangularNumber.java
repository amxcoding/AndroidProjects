package com.example.numbershapes;

import android.widget.Toast;

public class TriangularNumber {

    public boolean checkIfTriangular(int n) {
        /*
        to solve: x^2 + x - 2n = 0
        --> x = (-1 + sqrt(1 + 4 * n)) / 2
        if x is an integer results is correct
         */

        double resultX = (-1 + Math.sqrt(1 + 8 * n)) / 2;

        if (resultX == (int) resultX) {
            return true;
        } else {
            return false;
        }
    }

}
