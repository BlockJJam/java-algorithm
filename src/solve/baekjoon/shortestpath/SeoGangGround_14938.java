package solve.baekjoon.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최단 경로 찾기 형 문제로, 모든 시작지점에서 다른 지점과의 최단 경로를 미리 구해놓는 것이 핵심
// - 플로이드 워셜 사용
public class SeoGangGround_14938 {
    static int N, M, R;
    static int[] items;
    // 거리 2차원 배열을 사용하는데, Integer.MAX_VALUE로 초기화 하는 것은 그리 좋지 않은 방법같다.
    // 두 지점간의 거리를 더해야하는데, MAX끼리 더하면 당연히 Integer 범위를 초과하기 때문 <- 이 때문에 제한 조건을 걸었고, 그래서 별로임
    static int[][] dist;

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        N = rd.nextInt();
        M = rd.nextInt();
        R = rd.nextInt();
        // 각 지역의 아이템 개수 목록
        items = new int[N + 1];
        // 각 지역끼리의 거리 배열
        dist = new int[N + 1][N+1];

        int a,b;
        for(int i=0; i<N; i++){
            items[i + 1] = rd.nextInt();
        }

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if(i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < R; i++) {
            a = rd.nextInt();
            b = rd.nextInt();
            int d = rd.nextInt();
            // 겹치는 경로가 나올 수 있다. 그 중에 짧은 길이만 이용
            dist[a][b] = Math.min(d, dist[a][b]);
            dist[b][a] = Math.min(d, dist[a][b]);
        }

        // 입력받은 거리 배열에서, 각 지역간의 최단 경로 배열로 바꿔주기
        fillAllDist();

        int maxItemCnt = 0;
        // 각 시작지점 중에 가장 많은 아이템을 수거했을 때, 그 아이템 개수를 찾는다.
        for (int i = 1; i < N + 1; i++) {
            maxItemCnt = Math.max(findItemMaxCount(i), maxItemCnt);
        }

        System.out.println(maxItemCnt);

    }

    static void fillAllDist(){
        // i -> j까지의 최단 경로를 찾기 위해, 모든 경유 지점을 찾아 최단 경로를 dist[i][j]에 담는다.
        for(int k = 1; k < N+1; k++){
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if(dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE)
                        dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }
    }

    // start 지점에서 자신 포함 모든 지점간의 경로가 M보다 짧으면 탐색할 수 있는 아이템 개수에 추가하여, 수거가능한 모든 아이템 개수를 반환
    static int findItemMaxCount(int start){
        int retSum = 0;

        for(int i = 1; i<N+1; i++){
            if(dist[start][i] <= M){
                retSum += items[i];
            }
        }
        return retSum;
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
