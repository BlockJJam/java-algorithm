package solve.baekjoon.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HideAndSeek3_13549 {

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int K = rd.nextInt();

        int[] dist = new int[200001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Point> q = new LinkedList<>();

        q.offer(new Point(N, 0));
        boolean breakPoint = false;
        while(!q.isEmpty()){
            if(breakPoint) break;

            Point p = q.poll();
            int currX = p.x;
            int currStep = p.step;

            int prevX, postX, nextStep = currStep+1;
            int loopX = currX;
            while( loopX >= 0 && loopX <= 200000){
                if(dist[loopX] > currStep){
                    dist[loopX] = currStep;

                    if(loopX == K){
                        breakPoint = true;
                        break;
                    }

                    prevX = loopX -1;
                    postX = loopX +1;
                    q.offer(new Point(prevX, nextStep));
                    q.offer(new Point(postX, nextStep));
                }
                if(loopX == 0) break;
                loopX *= 2;
            }
        }

        System.out.println(dist[K]);

    }

    static class Point{
        int x;
        int step;

        Point(int x, int step){
            this.x= x;
            this.step = step;
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
