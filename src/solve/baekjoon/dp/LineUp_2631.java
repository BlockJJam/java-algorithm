package solve.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * LIS && DP
 */
public class LineUp_2631 {


    public static void main(String[] args) {
        FastReader rd = new FastReader();

        // O(N*logN)
//        int n = rd.nextInt();
//        int[] arr = new int[n];
//        List<Integer> list = new ArrayList<>();
//
//        for(int i=0; i<n; i++){
//            arr[i] = rd.nextInt();
//        }
//
//        for(int num : arr){
//            if(list.size() == 0 || list.get(list.size() -1 ) < num){
//                list.add(num);
//            }else{
//                int i = 0;
//                int j  = list.size() -1;
//
//                while(i < j){
//                    int mid = (i + j)/2;
//
//                    if(list.get(mid) < num){
//                        i = mid + 1;
//                    }else{
//                        j = mid;
//                    }
//                }
//                list.set(j, num);
//            }
//        }
//        System.out.println(n - list.size());

        // O(N^2)
        int n = rd.nextInt();
        int[] dp = new int[n];
        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = rd.nextInt();
        }

        dp[0] = 1;
        for(int i= 1; i< n; i++){
            dp[i] = 1;
            for(int j= 0; j < i; j++){
                if(arr[j] < arr[i] && dp[j] +1 > dp[i]){
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int ans = 0;
        for( int i=1; i<n; i++){
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(n - ans);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
