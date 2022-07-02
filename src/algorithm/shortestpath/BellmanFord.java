package algorithm.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BellmanFord {
    // BOJ 11657b Timemachine으로 알아보자
    /**
     * 문제 설명: N개의 도시, A,B,C 버스
     * - A: 시작도시, B: 도착도시, C: 버스 이동시간
     * - C == 0 순간이동, < 0 타임 머신!
     * 1번 도시에서 출발
     */
    static class Bus{
        int u;
        int v;
        int cost;
        Bus(int u, int v, int cost){
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    static int n, m;
    static Bus[] e;
    static long[] dist;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        n = rd.nextInt();
        m = rd.nextInt();

        e = new Bus[m];

        // graph 채우기
        for( int i=0; i<m; i++){
            int u = rd.nextInt();
            int v = rd.nextInt();
            int cost = rd.nextInt();

            e[i] = new Bus(u, v, cost);
        }

        // 2. 최단 거리 테이블 초기화
        dist = new long[n+1];
        for(int i=1; i<n+1; i++){
            dist[i] = INF;
        }

        // bellmanford 실행( true: 음수 존재, false: 음수 존재 하지 않음)
        if(bellmanford(1)){
            System.out.println(-1);
        }else{
            // 1번 노드를 제외한 다른 모든 노드로 가기 위한 최단 거리 출력
            for(int i =2; i<n+1; i++){
                if(dist[i] == INF){
                    System.out.println("-1");
                }else{
                    System.out.println(dist[i]);
                }
            }
        }

    }

    static boolean bellmanford(int start){
        dist[start] = 0;

        // n번 반복
        for (int i = 0; i < n; i++) {
            // 매 반복마다 모든 간석을 체크
            for (int j = 0; j < m; j++) {
                int cur = e[j].u;
                int next = e[j].v;
                int cost = e[j].cost;

                if(dist[e[j].u] != INF) {
                    if(dist[next] > (dist[cur] + cost)){
                        dist[next] = dist[cur] + cost;

                        if (i == n - 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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
