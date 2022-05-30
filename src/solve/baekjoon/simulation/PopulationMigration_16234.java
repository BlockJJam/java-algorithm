package solve.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PopulationMigration_16234 {
    public static void main(String[] args) throws Exception {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Migration migration = new Migration(graph, L, R, N);
        int result = 0;
        while(migration.findOpenBoundary()){
            ++result;
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    static class Migration{
        int L;
        int R;
        int N;
        int[][] graph;
        boolean[][] visited;
        boolean[][] unioned;
        int unionCnt;
        int unionAvg;
        boolean migrated;
        Queue<Point> updatePointQueue;

        public Migration(int[][] graph, int L, int R, int N){
            this.graph = graph;
            this.L = L;
            this.R = R;
            this.N = N;
            this.visited = new boolean[N][N];
            this.unioned = new boolean[N][N];
            this.unionCnt = 0;
            this.unionAvg = 0;
            migrated = false;
            updatePointQueue = new LinkedList<>();
        }

        int[] adjX = {0, 1, 0, -1};
        int[] adjY = {-1, 0, 1, 0};
        boolean findOpenBoundary(){
            Queue<Point> q = new LinkedList<>();
            migrated = false;

            for(int i=0; i< N; i++){
                for(int j=0; j<N; j++){
                    if(!unioned[i][j]){
                        q.offer(new Point(j, i));
                        unioned[i][j] = true;
                        visited[i][j] = true;
                        bfs(q);
                        execUnion();
                    }
                }
            }

            for(int i=0; i<N; i++){
                Arrays.fill(unioned[i], false);
            }
            if(migrated)
                return true;
            return false;
        }

        void execUnion(){
            if(unionCnt != 1) migrated = true;

            unionAvg = unionAvg / unionCnt;

            while(!updatePointQueue.isEmpty()){
                Point p = updatePointQueue.poll();
                int x = p.x;
                int y = p.y;
                graph[y][x] = unionAvg;
                visited[y][x] = false;
            }

            unionAvg = 0;
            unionCnt = 0;
        }


        void bfs(Queue<Point> q){
            while(!q.isEmpty()){
                Point p = q.poll();
                int x = p.x; int y = p.y;
                unionAvg += graph[y][x];
                unionCnt +=1;
                updatePointQueue.offer(p);

                for(int i=0; i<4; i++){
                    int nx = x + adjX[i];
                    int ny = y + adjY[i];
                    if(isRangedXY(nx, ny)){
                        if(!visited[ny][nx] && !unioned[ny][nx] && isRangedDiff(graph[y][x], graph[ny][nx])){
                            visited[ny][nx] = true;
                            unioned[ny][nx] = true;
                            Point newPoint = new Point(nx, ny);

                            updatePointQueue.offer(newPoint);
                            q.offer(newPoint);
                        }
                    }
                }
            }
        }

        boolean isRangedDiff(int a, int b){
            int diff = Math.abs(a -b);
            if(L <= diff && diff <= R)
                return true;
            return false;
        }

        boolean isRangedXY(int x, int y){
            return x >= 0 && x < N && y >= 0 && y < N;
        }
    }

    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
