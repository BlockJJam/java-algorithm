package algorithm.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
sample input(첫 번째 숫자는 노드의 개수, 두 번째 숫자는 간선의 개수 이다).
5
8
0 1 5
0 4 1
0 2 7
0 3 2
1 2 3
1 3 6
2 3 10
3 4 4
 */
public class FloydWarshell {
    static int N, M;
    static int[][] dist;

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        // 초기화 작업
        N = rd.nextInt();
        M = rd.nextInt();
        dist = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i == j){
                    dist[i][j] = 0;
                }else{
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            int a = rd.nextInt();
            int b = rd.nextInt();
            int cost = rd.nextInt();
            dist[a][b] = Math.min(dist[a][b], cost);
            dist[b][a] = Math.min(dist[b][a], cost);
        }

        // floyd-warshell
        // 1 ~ N 까지 거쳐가는 모든 경우를 고려
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+ dist[k][j]);
                }
            }
        }

        // 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <N; j++){
                if(dist[i][j] == Integer.MAX_VALUE){
                    System.out.print("INF ");
                }else{
                    System.out.print(dist[i][j] + " ");
                }
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
