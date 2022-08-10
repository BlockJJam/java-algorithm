package solve.baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CutVertexAndCutEdge_14675 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        Node[] nodes = new Node[N+1];
        for(int i=1; i<N+1; i++){
            nodes[i] = new Node(0);
        }

        for(int i=0; i<N-1; i++){
            int a = rd.nextInt();
            int b = rd.nextInt();
            nodes[a].edgeCnt++;
            nodes[b].edgeCnt++;
        }

        int q = rd.nextInt();
        for(int i=0; i<q; i++){
            int t = rd.nextInt();
            int k = rd.nextInt();
            if(t == 2)
                System.out.println("yes");
            else{
                if(nodes[k].edgeCnt -1 == 0)
                    System.out.println("no");
                else
                    System.out.println("yes");
            }
        }
    }

    static class Node{
        int edgeCnt;

        public Node(int edgeCnt) {
            this.edgeCnt = edgeCnt;
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
