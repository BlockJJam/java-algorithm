package solve.baekjoon.segmenttree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class SegmentSum5_11660 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int N = rd.nextInt();
        int M = rd.nextInt();

        int[][] graph  = new int[N+1][N+1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                graph[i][j] = rd.nextInt();
            }
        }
//        printGraph(graph);

        SegmentTree st = new SegmentTree(N, 1,N,1,N);
        st.init(graph, 1,1,N,1,N);

        long sum =0;
        for(int i=0;i<M;i++){
            sum = 0;
            int x1 = rd.nextInt();
            int y1 = rd.nextInt();
            int x2 = rd.nextInt();
            int y2 = rd.nextInt();
            sum = st.sum(x1,x2,y1,y2);
            System.out.println(sum);
        }

    }

    static class SegmentTree{
        long[] tree;
        int startXIdx;
        int endXIdx;
        int startYIdx;
        int endYIdx;
        int NODE_START=1;

        SegmentTree(int n, int startX, int endX, int startY, int endY){
            double height = ceil(log(n)/log(2))+1;
            long nodeCnt = round(pow(4, height));
            tree = new long[toIntExact(nodeCnt)];
            this.startXIdx = startX;
            this.endXIdx = endX;
            this.startYIdx = startY;
            this.endYIdx = endY;
        }

        long init(int[][] arr, int node, int x1, int x2, int y1, int y2){
            // leaf node
            if(x1 == x2 && y1 == y2){
                return tree[node] = arr[x1][y1];
            }else{
                return tree[node] = init(arr, node * 4, x1, (x1+x2) / 2, y1, (y1+y2)/2)
                        + init(arr, node * 4+1, (x1+x2)/2+1, x2, y1, (y1+y2)/2)
                        + init(arr, node * 4+2, x1, (x1+x2)/2, (y1+y2)/2+1, y2)
                        + init(arr, node * 4+3, (x1+x2)/2+1, x2, (y1+y2)/2+1, y2);
            }
        }

        long sum(int node, int startX, int endX, int startY, int endY, int leftX, int rightX, int leftY, int rightY ){
            if(endX < leftX || rightX < startX || endY < leftY || rightY < startY){
                return 0L;
            }else if(leftX <= startX && endX <= rightX && leftY <= startY && endY <= rightY){
                return tree[node];
            }else{
                return sum(node * 4, startX, (startX + endX) / 2, startY, (startY + endY)/2, leftX, rightX, leftY, rightY)
                        + sum(node * 4+1, (startX + endX) / 2+1, endX, startY, (startY + endY)/2, leftX, rightX, leftY, rightY)
                        + sum(node * 4+2, startX, (startX + endX) / 2, (startY + endY)/2+1, endY, leftX, rightX, leftY, rightY)
                        + sum(node * 4+3, (startX + endX) / 2+1,  endX, (startY + endY)/2+1, endY, leftX, rightX, leftY, rightY);
            }
        }

        long sum(int leftX, int rightX, int leftY, int rightY){
            return sum(NODE_START, startXIdx, endXIdx, startYIdx, endYIdx, leftX, rightX, leftY, rightY);
        }
    }

    static void printGraph(int[][] graph){
        for(int[] g: graph){
            for(int target: g){
                System.out.print(target + " ");
            }
            System.out.println();
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
