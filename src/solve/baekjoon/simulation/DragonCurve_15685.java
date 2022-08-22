package solve.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DragonCurve_15685 {
    static int N;
    static int[][] graph = new int[101][101];

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        N = rd.nextInt();
        DragonCurve dragonCurve;

        for(int i=0; i<N; i++){
            int x = rd.nextInt(); // col
            int y = rd.nextInt(); // row
            dragonCurve = new DragonCurve(y, x);
            int d = rd.nextInt();
            int g = rd.nextInt();

            List<Integer> inputList = new ArrayList<>();
            inputList.add(d);
            dragonCurve.draw(y,x, inputList, g);
        }

        for(int i = 0; i< 101; i++){
            for(int j= 0; j< 101; j++){
                System.out.print(graph[i][j]);
            }
            System.out.println();
        }
    }

    static int[] adjR = {0,-1,0,1};
    static int[] adjC = {1, 0, -1, 0};

    static class DragonCurve{
        public DragonCurve(int r, int c){
            graph[r][c] = 1;
        }

        public void draw(int row, int col, List<Integer> dirList, int gen){
            for(int dir : dirList){
                row = row+ adjR[dir];
                col= col+ adjC[dir];
                graph[row][col] = 1;
            }

            fillDirectForNextGen(dirList);
            if(gen != 0){
                draw(row, col, dirList, gen - 1);
            }
        }

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

        private void fillDirectForNextGen(List<Integer> dirList){
            LinkedList<Integer> appendList = new LinkedList<>();

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
                int turned = turnClockDirection(reversedDir);


                appendList.add(turnClockDirection(reversedDir));
//                System.out.println(i + " " +  reversedDir);
                dirList.set(i, reversedDir);
            }
            dirList.addAll(appendList);
            System.out.println(dirList);
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
