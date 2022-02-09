package algorithm.search;

import java.util.Arrays;

public class ExponentialSearch {
    public static void main(String[] args){
        int[] arr = {10, 11, 13, 15, 17, 19, 20, 25, 26, 28, 30, 32, 35, 36, 40, 41, 42};

        int x = 40;
        int result = search(arr, x);
        System.out.println(result == -1 ? x + " does not exist" : "Found! Index is : " + result);

        x = 11;
        result = search(arr, x);
        System.out.println(result == -1 ? x + " does not exist" : "Found! Index is : " + result);

    }

    protected static int search(int[] arr, int x){
        if(arr[0] == x){
            return 0;
        }
        int i=1;
        while(i < arr.length && arr[i] <= x){
            i *= 2;
        }

        return Arrays.binarySearch(arr, i/2, Math.min(i, arr.length-1), x);
    }
}
