package solve.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoNTiling_11726 {
    static int N;
    static int[][] dp;

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        N = rd.nextInt();

        dp = new int[N][2];
        for(int i=0; i<N; i++){
            Arrays.fill(dp[i], -1);
        }
        int total = (findTileWays(0, 0, N) + findTileWays(0,1, N)) % 10_007;

        System.out.println(total);
    }

    static int findTileWays(int tile, int dir, int remain){
        if(dp[tile][dir] != -1){
            return dp[tile][dir];
        }

        if(dir == 1 && remain <= 1){
            return 0;
        }

        if((dir == 0 && remain == 1) || (dir == 1 && remain == 2)){
            return 1;
        }

        int nextRemain = dir == 0? remain -1: remain -2;
        int nextTile = dir == 0? tile +1 : tile +2;

        return dp[tile][dir] = (findTileWays(nextTile, 0, nextRemain) + findTileWays(nextTile, 1, nextRemain))%10007;
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
