package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoPointer {
    static int N;
    static long M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        M = Long.parseLong(st.nextToken());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }


        long sum =0;
        int start =0;
        int end = 0;
        long result =0;

        while(start < N){
            if (sum > M || end == N){
                sum -= arr[start];
                start++;
            }else{
                sum += arr[end];
                end ++;
            }

            if(sum == M)
                result++;
        }

        System.out.println(result);
    }
}
