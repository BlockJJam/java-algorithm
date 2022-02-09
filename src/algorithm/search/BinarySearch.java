package algorithm.search;

import java.util.Arrays;

/**
 * ## 이진탐색
 *
 * - 정의
 * : 이 탐색은 반복적으로 데이터가 저장된 구간을 반으로 나누어 데이터가 저장된 위치를 찾아가는 방식이다
 */

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {-22, -15, -30, 7, 1, 20, 55, 35};

        int x = 7;
        Arrays.sort(arr);

        int result = iterativeSearch(arr, x);
        System.out.println(result == -1 ? x + "does not exist" : "Found! Index is "+ result);

        x = 20;
        result = recursiveSearch(arr, x, 0, arr.length-1);
        System.out.println(result == -1 ? x + "does not exist" : "Found! Index is "+ result);
    }

    // 반복문 사용
    protected static int iterativeSearch(int[] arr, int x){
        int start = 0;
        int end = arr.length -1;

        while(start <= end){
            int mid = (start + end)/2;
            if(arr[mid] == x){
                return mid;
            }else if(arr[mid] > x){
                end = mid -1;
            }else{
                start = mid +1;
            }
        }
        return -1;
    }

    // 재귀 사용
    protected static int recursiveSearch(int[] arr, int x, int start, int end){
        if(start > end) return -1;

        int mid = (start + end) / 2;

        if(arr[mid] == x){
            return mid;
        }else if(arr[mid] > x ){
            return recursiveSearch(arr, x, start, mid-1);
        }else{
            return recursiveSearch(arr, x, mid+1, end);
        }
    }
}

