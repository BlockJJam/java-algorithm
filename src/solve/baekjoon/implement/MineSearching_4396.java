package solve.baekjoon.implement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MineSearching_4396 {
    static int N;
    static boolean boomed;
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        boomed = false;
        char[][] graph = new char[N][N];
        char[][] playerInfo = new char[N][N];
        List<Point> boomPoints = new ArrayList<>();

        // fill graph, boomPoints
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String token = st.nextToken();
            for(int j=0; j<N; j++){
                graph[i][j] = token.charAt(j);
                if(graph[i][j] == '*'){
                    boomPoints.add(new Point(j,i));
                }
            }
        }

        // check Mine and fill playerInfo
        char target;
        char source;
        String token;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            token = st.nextToken();
            for(int j=0; j<N; j++){
                target = token.charAt(j);
                source = graph[i][j];

                if(target == '.'){
                    playerInfo[i][j] = '.';
                }else{
                    if(source != '*'){
                        int aroundBombCnt = checkMine(j, i, graph);
                        playerInfo[i][j] = Character.forDigit(aroundBombCnt,10);
                    }else{
                        boomed = true;
                    }
                }
            }
        }

        if(boomed){
            for(Point point: boomPoints){
                int x = point.x;
                int y = point.y;
                playerInfo[y][x] = '*';
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                bw.write(playerInfo[i][j]);
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int[] adjX = {0,1,1,1,0,-1,-1,-1}; // 상 -> 시계방향
    static int[] adjY = {-1,-1,0,1,1,1,0,-1};

    static boolean isRanged(int x, int y){
        return x >= 0 && x <N && y >= 0 && y <N;
    }

    static int checkMine(int x, int y, char[][] graph){
        int bombCnt=0;
        int nx;
        int ny;
        for(int i=0; i<8; i++){
            nx = x + adjX[i];
            ny = y + adjY[i];

            if(isRanged(nx, ny)){
                if(graph[ny][nx] == '*') bombCnt++;
            }
        }
        return bombCnt;
    }
}
