package solve.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DragonCurve_15685 {
    static int N;
    static int[][] graph = new int[101][101];
    // 아래 min, max 값들은 graph에서 정사각형 탐색의 범위에 사용된다.
    static int maxRow = 0;
    static int maxCol = 0;
    static int minRow = 101;
    static int minCol = 101;

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        N = rd.nextInt();

        // graph에 dragon curve를 그리며, graph 탐색범위를 조절한다.
        DragonCurve dragonCurve;

        for(int i=0; i<N; i++){
            int x = rd.nextInt(); // col
            int y = rd.nextInt(); // row
            int d = rd.nextInt();
            int g = rd.nextInt();

            ArrayList<Integer> inputList = new ArrayList<>();
            inputList.add(d);
            dragonCurve = new DragonCurve(y, x, d); // Dragon Curve의 시작좌표와 방향을 입력한다.
            dragonCurve.draw(y,x, inputList, g);
        }

        System.out.println(findOneByOneSquarePoint());

    }

    // 전역변수 min/max row/col값들로 1*1 정사각형 개수를 찾는다.
    public static int findOneByOneSquarePoint(){
        int ret = 0;
        for(int i = minRow+1; i<=maxRow; i++){
            for(int j = minCol+1; j<=maxCol; j++){
                if(graph[i-1][j-1] ==1 && graph[i][j-1] ==1 && graph[i-1][j] ==1 && graph[i][j] ==1 )
                    ret++;
            }
        }
        return ret;
    }

    // 문제의 4가지 방향의 row/col 배열
    static int[] adjR = {0,-1,0,1};
    static int[] adjC = {1, 0, -1, 0};

    static class DragonCurve{

        public DragonCurve(int r, int c, int initDir){
            graph[r][c] = 1;
            // fullDirList는 dirList에서 지나간 흔적을 저장한 전체 경로 리스트다.
            fullDirList = new LinkedList<>();
            fullDirList.add(initDir);
        }

        public void draw(int row, int col, ArrayList<Integer> dirList, int gen){
            // 일단 dirList의 값들을 꺼내서 graph를 그린다.
            setMinMaxPoint(row, col);
            for(int dir : dirList){
                row = row + adjR[dir];
                col= col + adjC[dir];
                setMinMaxPoint(row, col);
                graph[row][col] = 1;
            }
            // 드래곤 커브 지나간 모든 경로를 담은 리스트로 변경한다.
            dirList = new ArrayList<>(fullDirList);

            // 다음 세대를 위한 경로를 만들러 간다.
            fillDirectForNextGen(dirList);

            if(gen != 0){
                draw(row, col, dirList, gen - 1);
            }
        }

        private void setMinMaxPoint(int row, int col){
            // 드래곤 커브로 경로가 채워질 때마다, 정사각형 탐색 범위를 업데이트 해준다.
            minRow = Math.min(minRow, row);
            minCol = Math.min(minCol, col);
            maxRow = Math.max(maxRow, row);
            maxCol = Math.max(maxCol, col);
        }

        // 해당 방향의 90도 시계방향으로 돌려주는 함수
        private int turnClockDirection(int dir){

            switch(dir){
                case 0:
                    return 3;
                case 1:
                    return 0;
                case 2:
                    return 1;
                case 3:
                    return 2;
            }
            return -1;
        }

        static LinkedList<Integer> fullDirList = new LinkedList<>();
        // 현재 드래곤커브가 지나간 경로를 활용해 다음 경로 리스트로 업데이트 해주고, 지나간 흔적을 담는 fullDirList에 업데이트 된 경로만 추가해준다.
        private void fillDirectForNextGen(List<Integer> dirList){

            // 현재 방향의 반대 방향으로 변경
            for(int i=0; i<dirList.size(); i++){
                int reversedDir = 0;
                switch(dirList.get(i)){
                    case 0:
                        reversedDir = 2;
                        break;
                    case 1:
                        reversedDir = 3;
                        break;
                    case 2:
                        reversedDir = 0;
                        break;
                    case 3:
                        reversedDir = 1;
                        break;
                }
                // 반대로 돌린 방향의 90도 방향 변경
                int turned = turnClockDirection(reversedDir);
                // 최종 변경된 새로운 경로로 dirList를 채운다.
                dirList.set(i, turned);
                // 최종 변경된 새로운 경로를 fullDirList에 채운다.
                fullDirList.addFirst(turned);
            }

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
