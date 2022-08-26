package solve.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WeaponEngineer_18430 {
    static int NM;
    static int N;
    static int M;
    static int[][] graph;
    static int maxPower;
    static boolean[][] visited;

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        M = rd.nextInt();
        // NM의 용도는 graph의 전체 인덱싱을 한쪽 방향(가로)으로 순회하기 위함
        NM = N * M;
        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for(int j = 0; j< M; j++){
                graph[i][j] = rd.nextInt();
            }
        }

        // graph[0][0]부터 모든 부메랑의 경우를 찾는다
        measureMaxPower(0, 0);

        System.out.println(maxPower);
    }

    // 부메랑을 모두 찾았을 때, 올바르게 방문처리를 했는지, 방문한 위치가 어디인지 확인하기 위한 용도
    static void printVisitedGraph(){
        for(int i=0 ; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[] adjR = {-1, 0, 1, 0};
    static int[] adjC = {0, 1, 0, -1};

    // dfs방식으로 모든 부메랑의 경우를 탐색한다
    static void measureMaxPower(int idx, int power){
        // idx가 NM을 벗어나면 안된다
        if(idx >= NM ){
//            printVisitedGraph();
            return;
        }
        Point p = getPointByIdx(idx);
        int r = p.r;
        int c = p.c;
        // 부메랑이 될 수 있는 4방향과 부메랑을 안 만들고 지나가는 경우를 체크한다.
        for(int i=0; i<5; i++){
            // 부메랑을 안 만드는 경우
            if(i == 4){
                measureMaxPower(findNextIdx(idx), power);
                break;
            }

            // 부메랑을 만드는 경우, 4가지 부메랑 위치를 구한다.
            List<Point> nextPoints = getNextPoint(i, r, c);
            // 부메랑의 위치가 graph범위에 있고, 방문한 적이 없다면
            if(possibleMakeBumerang(nextPoints)){
                // 부메랑 위치 방문 처리
                turnVisit(nextPoints, true);
//                for(Point pp: nextPoints){
//                    System.out.println("pp.r = " + pp.r + " pp.c = "+pp.c);
//                }
                // 부메랑 파워 적립
                int nextPower = power + getNextPower(nextPoints) + graph[r][c];
                // 부메랑 파워의 최대를 기록
                maxPower = Math.max(nextPower, maxPower);

                // 다음 탐색 위치를 찾고, 기록한 파워로 다음 탐색을 이어간다
                measureMaxPower(findNextIdx(idx), nextPower);

                // dfs 탐색을 이어가기 위해, 탐색이 끝난 위치는 원복
                turnVisit(nextPoints, false);
            }
        }
    }

    // 다음 탐색 index를 한쪽방향으로 idx를 증가시키며 찾는다.
    static int findNextIdx(int idx){
        while(idx < NM -1){
            ++idx;
//            System.out.println("idx = " + idx);
            Point np = getPointByIdx(idx);
            if(!visited[np.r][np.c]){
                return idx;
            }
        }
        return NM;
    }

    // 부메랑의 power를 찾는다. 2배 파워는 여기서 더하지 않고 이 메서드를 호출한 곳에서 더한다.
    static int getNextPower(List<Point> points){
        int sum = 0;
        for(Point p : points){
            sum += graph[p.r][p.c];
        }
        return sum;
    }

    // 부메랑 위치 리스트에서 하나씩 방문 처리
    static void turnVisit(List<Point> points, boolean isVisited){
        for(Point p : points){
            visited[p.r][p.c] = isVisited;
        }
    }

    // 부메랑 위치리스트의 각 원소 위치가 부메랑 위치로 적절한지 확인한다.
    static boolean possibleMakeBumerang(List<Point> points){

        for (Point p : points) {
            if(!innerGraph(p.r, p.c))
                return false;

            if(visited[p.r][p.c])
                return false;
        }
        return true;

    }

    // 그래프 범위를 해당 위치가 벗어나는지 체크한다.
    static boolean innerGraph(int nr, int nc){
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }

    // 4가지 부메랑 만들기 방식 중 div를 통해 (r,c)를 중심으로 찾아준다.
    static List<Point> getNextPoint(int div, int r, int c){
        List<Point> ret = new ArrayList<>();
        Point n1, n2;
        ret.add(new Point(r, c));

        switch(div){
            case 0:
                n1 = new Point(r + adjR[0], c + adjC[0]);
                n2 = new Point(r + adjR[1], c + adjC[1]);
                ret.add(n1);
                ret.add(n2);
                break;
            case 1:
                n1 = new Point(r + adjR[1], c + adjC[1]);
                n2 = new Point(r + adjR[2], c + adjC[2]);
                ret.add(n1);
                ret.add(n2);
                break;
            case 2:
                n1 = new Point(r + adjR[2], c + adjC[2]);
                n2 = new Point(r + adjR[3], c + adjC[3]);
                ret.add(n1);
                ret.add(n2);
                break;
            case 3:
                n1 = new Point(r + adjR[3], c + adjC[3]);
                n2 = new Point(r + adjR[0], c + adjC[0]);
                ret.add(n1);
                ret.add(n2);
                break;
        }
        return ret;
    }

    // idx를 통해, M(가로 최대 수)로 나눈 값은 r, M으로 나눈 값의 나머지를 c로 위치를 반환한다.
    static Point getPointByIdx(int idx){
        int r = idx / M;
        int c = idx % M;
        return new Point(r, c);
    }

    // 위치 정보
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
