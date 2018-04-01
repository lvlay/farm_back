package com.taoroot;


/**
 * @author: taoroot
 * @date: 2018/3/31
 * @description:
 */
public class test {

    public static void main(String[] args) {
        for (int i = 1; i <= 12; i++) {
            count(i);
        }
    }

    public static void count(int i) {
        int [] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        i += 12;
        for (int j = 1; j < 7; j++) {
            System.out.print(arr[(i - j) % 12]);
            System.out.print(" ");
        }
        System.out.println();

    }
}
