package solve.baekjoon.divideconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ColorPaperMake_2630 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int[][] graph = new int[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                graph[i][j] = rd.nextInt();
            }
        }
//        printGraph(graph);

        PaperBuilder pb = new PaperBuilder(graph);

        Paper p = pb.dividePaper(0, N - 1, 0, N - 1);
        System.out.println(p.white);
        System.out.println(p.blue);
    }

    public static void printGraph(int[][] graph){
        for(int[] g : graph){
            for(int target: g){
                System.out.print(target+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static class PaperBuilder{
        int[][] graph;

        public PaperBuilder(int[][] graph){
            this.graph = graph;
        }

        public Paper dividePaper(int sx, int ex, int sy, int ey){
            if(sx == ex){
                if(graph[sx][sy] == 1){
                    return new Paper(1,0,true);
                }else{
                    return new Paper(0, 1, true);
                }
            }

            int midX = (sx + ex)/2;
            int midY = (sy + ey)/2;

            return conquerColor(
                    dividePaper(sx, midX, sy, midY),
                    dividePaper(sx,midX , midY+1, ey),
                    dividePaper(midX+1, ex, sy, midY),
                    dividePaper(midX+1, ex, midY+1, ey)
            );
        }

        public Paper conquerColor(Paper p1, Paper p2, Paper p3, Paper p4){
            int white = p1.white + p2.white + p3.white + p4.white;
            int blue = p1.blue + p2.blue + p3.blue + p4.blue;
            boolean same = p1.combined && p2.combined && p3.combined && p4.combined;

            if(white == 4 && same){
                return new Paper(0,1,true);
            } else if (blue == 4 && same) {
                return new Paper(1,0, true);
            }

            return new Paper(blue, white, false);
        }
    }

    static class Paper{
        int blue;
        int white;
        boolean combined;

        public Paper(int blue, int white, boolean combined){
            this.blue = blue;
            this.white = white;
            this.combined = combined;
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
