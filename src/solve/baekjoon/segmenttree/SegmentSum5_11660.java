package solve.baekjoon.segmenttree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class SegmentSum5_11660 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int N = rd.nextInt();
        int M = rd.nextInt();

        int[][] graph  = new int[N+1][N+1];

        FenwickTree[] ft = new FenwickTree[N+1];
        for(int i=1; i<N+1; i++){
            ft[i] = new FenwickTree(N);
        }
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                 ft[i].add(j, rd.nextInt());
            }
        }
//        printGraph(graph);
        StringBuilder sb = new StringBuilder();
        long[][] dp = new long[N+1][N+1];
        long sum = 0;
        for(int i=0; i<M; i++){
            sum = 0;
            int x1 = rd.nextInt();
            int y1 = rd.nextInt();
            int x2 = rd.nextInt();
            int y2 = rd.nextInt();
            for (int j = x1; j <= x2; j++) {
                dp[j][y1-1] = dp[j][y1-1] != 0? dp[j][y1-1]: ft[j].getSum(y1-1);
                dp[j][y2] = dp[j][y2] != 0? dp[j][y2] : ft[j].getSum(y2);
                sum += dp[j][y2] - dp[j][y1-1];
            }
            sb.append(sum + "\n");
        }

        System.out.println(sb.toString());
    }

    static class FenwickTree{
        long[] tree;
        int n;

        FenwickTree(int n){
            tree = new long[n+1];
            this.n = n;
        }

        public void add(int pos, int val){
            while(pos <= n){
                tree[pos] += val;
                pos += (pos & -pos);
            }
        }

        public long getSum(int pos){
            long result = 0;
            while(pos > 0){
                result += tree[pos];
                pos &= (pos-1);
            }
            return result;
        }
    }

    static void printGraph(int[][] graph){
        for(int[] g: graph){
            for(int target: g){
                System.out.print(target + " ");
            }
            System.out.println();
        }
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
