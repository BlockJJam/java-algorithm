package solve.baekjoon.divideconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pooling222_17829 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int N = rd.nextInt();
        int cnt = 0;

        Pooling222 pooling = new Pooling222();
        pooling.setMap(0, 1);
        while(++cnt > 0) {
            int breakPoint = (int) Math.pow(2, cnt);
            pooling.setMap(cnt, breakPoint);

            if (breakPoint == N)
                break;
        }

        int[][] graph = pooling.graphMap.get(cnt);
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                graph[i][j] = rd.nextInt();
            }
        }

        pooling.pooling(0,N-1,0,N-1, 0, N);

        System.out.println(pooling.graphMap.get(0)[0][0]);


    }

    public static void printGraph(int[][] graph){
        for(int[] g : graph){
            for(int target: g){
                System.out.print(target+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static class Pooling222{
        Map<Integer, int[][]> graphMap;

        Pooling222(){
            graphMap = new HashMap<>();
        }

        public int pooling(int sx, int ex, int sy, int ey, int depth, int d){
            int[][] graph = graphMap.get(depth);
            if(sx == ex){
                return graph[sy][sx];
            }

            int midX = (sx + ex)/2;
            int midY = (sy + ey)/2;

            return conquer(pooling(sx, midX, sy, midY, depth + 1, d/2),
                    pooling(sx, midX, midY + 1, ey, depth + 1, d/2),
                    pooling(midX + 1, ex, sy, midY, depth + 1, d/2),
                    pooling(midX + 1, ex, midY + 1, ey, depth + 1, d/2), depth, sx/d, sy/d);
        }

        public int conquer( int d1, int d2, int d3, int d4, int depth, int x, int y){
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            pq.offer(d1);
            pq.offer(d2);
            pq.offer(d3);
            pq.offer(d4);
            pq.poll();
            int big2 = pq.poll();
            int[][] graph = graphMap.get(depth);

            graph[y][x] = big2;

            return graph[y][x];
        }

        public void setMap(int N, int size){
            graphMap.put(N, new int[size][size]);
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
