package solve.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class MagicSharkAndRain_21610 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        MagicShark magicShark = new MagicShark(N, graph);

        int d, s;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            d= Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            if(i == 0){
                magicShark.firstCloud(d,s);
            }else{
                magicShark.makeCloud(d,s);
            }
            magicShark.rainToBucket();
        };
        // 마지막 단계가 구름 생성까지라서 한번 더 구름 생성
        magicShark.makeCloud(0,0);

        bw.write(magicShark.getAllWater()+ "\n");
        bw.flush();
        bw.close();
        // 모든 구름이 di 방향으로 si칸 이동한다.
        // 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
        // 구름이 모두 사라진다.
        // 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
        // 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
    }

    static class MagicShark{
        int[][] graph;
        boolean[][] madeClouds;
        Queue<Point> clouds;
        int N;

        public MagicShark(int N, int[][] graph){
            this.N = N;
            this.graph = graph;
            madeClouds = new boolean[N+1][N+1];
            clouds = new LinkedList<>();
        }

        public void firstCloud(int d, int s){
            clouds.offer(Point.getNextPoint(1, N, d, s, N));
            clouds.offer(Point.getNextPoint(2, N, d, s, N));
            clouds.offer(Point.getNextPoint(1, N-1, d, s, N));
            clouds.offer(Point.getNextPoint(2, N-1, d, s, N));
        }
        // cloud 생성
        public void makeCloud(int d, int s){
            for(int i=1; i<= N; i++){
                for(int j=1; j<=N; j++){
                    if(graph[i][j] >= 2 && !madeClouds[i][j]){
                        graph[i][j] -= 2;
                        Point insertPoint = new Point(j, i);
                        insertPoint = Point.getNextPoint(insertPoint.x, insertPoint.y, d, s, N);
                        clouds.offer(insertPoint);
                    }
                }
            }
//            System.out.println("after make cloud");
//            printGraph();
            cleanCloud();
        }

        // cloud 물뿌리기
        public void rainToBucket(){
            List<Point> collectors = new ArrayList<>();
            while(!clouds.isEmpty()){
                Point p = clouds.poll();
                graph[p.y][p.x] += 1;
                madeClouds[p.y][p.x] = true;

                collectors.add(p);
            }
//            System.out.println("after rain");
//            printGraph();
            collectCrossPoint(collectors);
        }

        public boolean isRanged(int x, int y){
            return x >= 1 && x <= N && y >= 1 && y <= N;
        }
        // cloud 자리의 대각선 물 수집
        public void collectCrossPoint(List<Point> collectors){
            for(Point collector : collectors){
                List<Point> crossArr = Point.getCrossPoint(collector.x, collector.y);
                for(Point cross : crossArr){
                    if(isRanged(cross.x, cross.y) && graph[cross.y][cross.x] != 0){
                        graph[collector.y][collector.x] += 1;
                    }
                }
            }
//            System.out.println("collect cross");
//            printGraph();
        }

        public void cleanCloud(){
            for(boolean[] madeCloud: madeClouds){
                Arrays.fill(madeCloud, false);
            }
        }

        public void printGraph(){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    System.out.print(graph[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        public int getAllWater(){
            int result =0;

            for(int[] targets : graph){
                for(int target: targets){
                    result += target;
                }
            }

            return result;
        }
    }

    static class Point{
        int x;
        int y;

        static int[] adjX = {-100, -1,-1,0,1,1,1,0,-1}; // 북 -> 북서
        static int[] adjY = {-100, 0,-1,-1,-1,0,1,1,1}; // 북 -> 북서

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public static Point getNextPoint(int x, int y, int d, int s, int N ){
            int nx = (x+ adjX[d] * s);
            if(nx <= 0){
                while(nx <= 0) nx = nx + N;
            }
            nx = nx % N ;
            nx = nx == 0? N: nx;

            int ny = (y + adjY[d] * s);
            if(ny <= 0){
                while(ny <= 0) ny = ny + N;
            }
            ny = ny % N;
            ny = ny == 0? N: ny;

            return new Point(nx, ny);
        }

        public static  List<Point> getCrossPoint(int x, int y){
            List<Point> returns = new ArrayList<>();

            returns.add(new Point(x-1,y-1));
            returns.add(new Point(x-1, y+1));
            returns.add(new Point(x+1, y-1));
            returns.add(new Point(x+1, y+1));

            return returns;
        }
    }
}
