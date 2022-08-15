package solve.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GasStation_13305 {
    static long total;
    static final long COST_MAX = Integer.MAX_VALUE;
    static int N;
    static long[] roads;
    static long[] costs;

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        roads = new long[N-1];
        costs = new long[N];
        for(int i=0; i<N-1; i++){
            int road = rd.nextInt();
            roads[i] = road;
        }

        for(int i=0; i<N; i++){
            int cost = rd.nextInt();
            costs[i] = cost;
        }

        total = 0;
        findMinCost(0, COST_MAX);

    }

    static void findMinCost(int city, long prevOilCost){
        if(city == costs.length - 1){
            System.out.println(total);
            return;
        }

        long roadLen = roads[city];
        long oilCost = Math.min(prevOilCost, costs[city]);

        total += roadLen * oilCost;

        findMinCost(city + 1, oilCost);
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
