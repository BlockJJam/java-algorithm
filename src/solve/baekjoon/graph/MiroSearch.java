package solve.baekjoon.graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MiroSearch {
    static int N,M;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(N+1,M+1);

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            for( int j=1; j<=M; j++){
                int value = temp.charAt(j-1) - '0';
                graph.board[i][j]= value;
            }
        }

        graph.bfs();
        int result = graph.getResult();
        bw.write(result +"\n");
        bw.flush();
        bw.close();
    }

    static class Graph{
        int[][] board;
        boolean[][] visited;
        int result;
        Queue<Point> q ;

        int[] nextX = {0,1,0,-1};
        int[] nextY = {-1,0,1,0};

        public Graph(int n, int m){
            this.board  = new int[n][m];
            this.visited = new boolean[n][m];
            this.q = new LinkedList<>();
            this.result = Integer.MAX_VALUE;
        }

        public void bfs(){
            this.visited[1][1] = true;
            q.offer(new Point(1,1, 1));

            while(!q.isEmpty()){
                Point p = q.poll();
                int prevCnt = p.cnt;

                for(int i=0; i<4; i++){
                    int nX = p.x + nextX[i];
                    int nY = p.y + nextY[i];
                    if(nY == N && nX == M){
                        this.result = Math.min(this.result, prevCnt + 1);
                        return;
                    }
                    if(isInBoard(nX, nY)){
                        if(!visited[nY][nX] && board[nY][nX] == 1){
                            visited[nY][nX] = true;
                            q.offer(new Point(nX,nY, prevCnt +1));
                        }
                    }
                }
            }
        }

        public boolean isInBoard(int x, int y){
            return 0 < x && 0 < y && M >= x && N >= y;
        }

        public int getResult(){
            return result;
        }


    }

    static class Point{
        int x,y, cnt;
        public Point(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
