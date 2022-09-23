package algorithm.perm_comb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS {

    static int N;
    static int[] arr;
    static int[] dp;
    static int INF = Integer.MAX_VALUE;

    public static void solution(){
        dp = new int[N+1];
        dp[0] = arr[0];

        int idx = 0;
        for(int i=1; i<N; i++){
            if(dp[idx] < arr[i]){
                dp[++idx] = arr[i];
            }else{
                int ii = lowerBound(idx, arr[i]);
//                System.out.println("ii = " + ii);
                dp[ii] = arr[i];
            }
        }

        for(int i=0; i<dp.length; i++){
            System.out.println("dp = " + dp[i]);
        }
    }

    public static int lowerBound(int r, int key){
        int l = 0;

        while(l < r){
            int mid = (l + r) / 2;
            if( dp[mid] < key){
                l = mid + 1;
            }else{
                r = mid;
            }
        }

        return r;
    }

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = rd.nextInt();
        }

        solution();
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
