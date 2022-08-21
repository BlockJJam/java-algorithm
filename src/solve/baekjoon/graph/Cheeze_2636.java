package solve.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Cheeze_2636 {
    static int time;
    static int cheezeCnt, totalCnt;
    static int R, C;
    static int[][] graph;
    static boolean[][] visited;
    static int startRow, startCol;

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        R = rd.nextInt();
        C = rd.nextInt();
        totalCnt = 0;
        graph = new int[R][C];
        visited = new boolean[R][C];

        // 전체 cheeze 그래프를 입력받자
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++){
                graph[i][j] = rd.nextInt();
                if(graph[i][j] == 1){
                    totalCnt++;
                }
            }
        }

        boolean hasCheeze = true;
        startRow = 0;
        startCol = 0;
        visited[startRow][startCol] = true;
        cheezeCnt = 0;
        time = 0;
        while(totalCnt != 0){
            // 전체 치즈 갯수가 0이 될 때까지, 치즈를 녹이는 작업을 한다.
            // 한번 치즈를 녹이는 작업 중에 마지막 치즈 작업에서 녹은 치즈 개수를 담기 위해 cheezCnt를 초기화 해놓는다.
            cheezeCnt = 0;

            // 치즈 녹이는 작업의 시작 지점을 넣어서, 공기중에 치즈를 녹이는 작업을 시작
            meltCheeze(startRow, startCol);

            // 녹은 치즈 위치와 방문처리, 그리고 다음 녹이는 시작 row를 업데이트 해주도록 한다.
            updateGraph();

            // 총 몇번 녹이는 작업이 들어갔는지 구한다.
            time++;
        }
        System.out.println(time);
        System.out.println(cheezeCnt);
    }

    static int[] adjR = {-1, 0 ,1, 0};
    static int[] adjC = {0, 1, 0, -1};
    private static boolean innerGraph(int r, int c){
        return r >= 0 && r < R && c >= 0  && c < C;
    }

    // dfs 방식으로 시작 지점부터 치즈를 녹이는 작업을 수행한다.
    public static void meltCheeze(int r, int c){
        for (int i = 0; i < 4; i++) {
            int nr = r+ adjR[i];
            int nc = c+ adjC[i];

            // graph 밖에 범위는 탐색못함
            if(!innerGraph(nr, nc))
                continue;

            // 만약 방문한 적 없고, 해당 자리가 치즈가 없는 공간이면 다음 탐색 위치로 낙점
            if(!visited[nr][nc] && graph[nr][nc] == 0){
                visited[nr][nc] = true;
                meltCheeze(nr, nc);
            }
            // 방문한 빈 공간 위치의 인접한 지점에 치즈가 있으면 녹는 대상으로 선정, 녹는 치즈 개수와 전체 치즈 개수에도 반영한다.
            if(graph[nr][nc] == 1){
                graph[nr][nc] = -1;
                cheezeCnt++;
                totalCnt--;
            }
        }
    }

    // 녹이는 작업을 통해 얻어진 값들로 실제 Graph를 업데이트 하는 함수
    public static void updateGraph(){
        boolean noCheezeRow = true;
        for(int i=0; i<R; i++){
            for(int j= 0; j<C; j++){
                // startRow부터는 다시 탐색해야 됨으로, 방문처리 그래프를 리셋해준다.
                if(startRow <= i ) visited[i][j] = false;
                // 녹이는 대상의 치즈는 빈공간으로 업데이트
                if(graph[i][j] == -1) graph[i][j] = 0;
                // startRow를 찾기 위해, 만약 해당 row에 치즈가 없으면 다음 탐색 범위를 좁힐 수 있도록 startRow를 업데이트
                if(graph[i][j] == 1) noCheezeRow = false;
            }
            startRow = noCheezeRow? i: startRow;
        }
    }

    public static void printGraph(){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(graph[i][j]);
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
