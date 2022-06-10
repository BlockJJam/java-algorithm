package solve.baekjoon.segmenttree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PianoGymnastic_21318 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int N = rd.nextInt();
        int[] dp = new int[N+1];
        FenwickTree ft = new FenwickTree(N);

        int prev= -1;
        for(int i=1; i<N+1; i++){
            int target = rd.nextInt();
            if(prev == -1){
                prev = target;
                continue;
            }
            if(target < prev){
                ft.add(i-1, 1);
            }
            prev = target;
        }

        int Q = rd.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<Q; i++){
            int s = rd.nextInt();
            int e = rd.nextInt()-1;
            dp[s-1] = dp[s-1] != 0? dp[s-1] : ft.getSum(s-1);
            dp[e] = dp[e] != 0? dp[e] : ft.getSum(e);
            sb.append((dp[e] - dp[s-1])+"\n");
        }
        System.out.println(sb.toString());

    }

    static class FenwickTree{
        int[] tree;
        int n;

        FenwickTree(int n){
            this.tree = new int[n+1];
            this.n = n;
        }

        public void add(int pos, int val){
            while(pos <= n){
                tree[pos] += val;
                pos += (pos & -pos);
            }
        }

        public int getSum(int pos){
            int result = 0;
            while(pos > 0){
                result += tree[pos];
                pos &= (pos - 1);
            }
            return result;
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
