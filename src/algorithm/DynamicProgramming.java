package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * ## 정의
 * - 간략하게는, 이전에 구한 값(prev)을 다시 가져와서 활용하는 방법
 * - 왜? 반복적으로 메소드를 불러오는 횟수를 줄일 수 있다, 해당 연산을 과거에 한 적이 있으면 중복 연산을 할 필요가 없지 않은가? → 피보나치 문제
 *
 * ## 예시
 * - 피보나치
 * - 거스름돈 문제
 * - knapsack 문제
 */
public class DynamicProgramming {
    static int[] change;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("거스름돈을 입력: ");
        StringTokenizer st = new StringTokenizer(reader.readLine());

        change = new int[101];
        int i = Integer.parseInt(st.nextToken());

        if(i <= 0 && i > 100)
            return;

        calc_change();
        System.out.println("지불해야 하는 거스름돈 개수: "+ change[i]);
    }

    static void calc_change(){
        change[0] = 0;
        for(int i =1; i<101; i++){
            change[i] = min_change(i) + 1;
        }
    }

    static int min_change(int i){
        int min = change[i-1];
        if(i >= 3){
            if(min > change[i-3]) min = change[i - 3];
        }
        if(i >= 4){
            if(min > change[i-4]) min = change[i-4];
        }
        return min;
    }
}
