package solve.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BomberMan_16918 {
    static char[][] graph;
    static int R,C,N;

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        R = rd.nextInt();
        C = rd.nextInt();
        N = rd.nextInt();
        graph = new char[R][C];

        // 전체 그래프를 입력받자
        for (int i = 0; i < R; i++) {
            String target = rd.next();
            for (int j = 0; j < C; j++) {
                graph[i][j] = target.charAt(j);
            }
        }

        // 시간이 흐를동안, 폭탄을 심고 터뜨리기 기능이 있는 BomberMachine
        BombMachine bombMachine = new BombMachine();
        bombMachine.playTimer();

        printGraph();

    }

    public static void printGraph(){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(graph[i][j]);
            }
            System.out.println();
        }

    }

    static int[] adjc = {0, 1, 0, -1};
    static int[] adjr = {-1, 0, 1, 0};
    static class BombMachine{
        private Queue<Point> queue = new LinkedList<>();

        public void playTimer(){
            // 1초 이후 부터, 폭탄 제어가 시작된다.
            int i = 1;

            // 2초부터 그래플에 변동이 생긴다
            while(++i != N+1){
                if( i % 2 == 0){
                    // 짝수 시간에는 폭탄 채우기를 진행한다.
                    for(int j=0; j<R; j++){
                        for(int k=0; k<C; k++){
                            if(graph[j][k] == 'O'){
                                layBomb(j, k);
                            }else{
                                graph[j][k] = 'O';
                            }
                        }
                    }
                }else{
                    // 홀수 시간에는 3초후에 터지는 폭탄 터뜨리기 기능이 동작한다.
                    boom();
                }
            }
        }

        private boolean innerGraph(int r, int c){
            // 인접한 북-동-남-서의 폭탄 위치가 그래프 범위 안인지 확인한다.
            return r >= 0 && r < R && c >= 0 && c < C;
        }
        private void boom(){
            while(!queue.isEmpty()){
                // 폭탄위치가 담겨있는 queue에서 Point를 하나씩 꺼내어, 해당 위치 + 북동남서 의 위치를 터뜨린다 Boom!
                Point p = queue.poll();
                int r = p.r;
                int c = p.c;

                graph[r][c] = '.';
                for (int i = 0; i < 4; i++) {
                    int nr = p.r + adjr[i];
                    int nc = p.c + adjc[i];

                    if(innerGraph(nr, nc)){
                        graph[nr][nc] = '.';
                    }
                }
            }
        }

        private void layBomb(int r, int c){
            // 'O'의 위치에 설치할 폭탄 위치를 queue에 넣어놓는다.
            queue.offer(new Point(r, c));
        }
    }

    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
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
