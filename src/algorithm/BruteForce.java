package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ## 정의
 * - 가능한 모든 경우의 수를 다 체크해 나아가면서 정답을 찾는 방식
 * - 가장 이해하기 쉬운 방식은, 비밀번호 4자리를 맞추기 위해서 0000 ~ 9999까지 모두 대입해보는 것이다
 * - 알고리즘의 기본적인 규칙에서 “완전탐색”보다는 “다른 알고리즘”에 더 손이 가는 이유
 *     1. 사용된 알고리즘이 적절한가? (문제를 풀 수 있는가?)
 *     2. 효율적으로 동작 가능한가?
 *     → 1번은 여러 알고리즘이 후보로 확실히 만족한다, 2번은 해당 문제를 풀 수 있는 여러 알고리즘이 있다고 해보자 후보군( 완전탐색, 이분탐색, 동적계획법.. ) 과연 이 중에 무엇을 고를까?
 *     : DP가 O(N)이 걸리는 연속합의 최대를 구하는 문제가 있다면, 완전탐색은 O(N$^2$)이 걸린다 → 이유가 충분하다..
 *
 * ## 활용방법
 * - 언제 사용하면 될까?
 *     1. 해결하고자 하는 문제의 가능한 경우의 수를 대략적으로 계산할 때
 *     2. 가능한 모든 방법을 고려해야 할 때
 *     3. 실제 답을 구할 수 있을 때
 * - 완전 탐색의 모든 방법을 고려하는 경우(위 2번)
 *     1. Brute Force기법 - 반복/조건문을 동원
 *     2. 순열 - n개의 원소 중에 r개의 원소를 중복 허용없이 나열
 *     3. 재귀 호출
 *     4. 비트 마스크 - 2진수 표현방식을 활용
 *     5. BFS, DFS
 */
public class BruteForce {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3}; //순열을 만들 배열
        int n = arr.length; //배열의 길이
        int[] output = new int[n]; //순열 출력을 위한 배열
        boolean[] visited = new boolean[n]; //중복해서 뽑지 않기 위해 방문했는지를 체크하는 배열

        System.out.println("-------- 1. Swap ---------");
        per1(arr, 0, n, 3);

        System.out.println("\n-------- 2. DFS ---------");
        per2(arr, output, visited, 0, n, 3); //r = 3, 3개를 뽑을 것
    }

    // 방법 1. Swap method 이용, 순서 없이 n개 중에 r개를 뽑는 경우
    static void per1(int[] arr, int depth, int n, int r){
            if(depth == r){
                print(arr, r);
                return;
            }

            for(int i=depth; i < n; i++){
                swap(arr, depth, i);
                per1(arr, depth +1, n, r);
                swap(arr, depth, i);
            }
    }

    // 배열 안의 서로 다른 위치의 원소를 서로 바꾸는 함수
    static void swap(int[] arr, int depth, int i){
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }

    // 2. DFS를 이용한 순열함수 - 순서를 지키면서 n개 중에 r개를 뽑는 경우
    static void per2(int[] arr, int[]output, boolean[] visited, int depth, int n, int r){
        if(depth == r){
            print(output, r);
            return;
        }

        for(int i=0; i<n; i++){
            if(visited[i] != true){
                visited[i] = true;
                output[depth] = arr[i];
                per2(arr, output, visited, depth +1, n, r);
                visited[i] = false;
            }
        }
    }

    static void print(int[] arr, int r){
        for(int i=0; i< r; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}

