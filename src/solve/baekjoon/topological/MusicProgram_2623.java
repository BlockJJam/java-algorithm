package solve.baekjoon.topological;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MusicProgram_2623 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt(), M = rd.nextInt();

        Graph graph = new Graph(N);
        int src, dest;
        for(int i=0; i<M; i++){
            int loop = rd.nextInt();
            src = -1;
            dest = 0;
            for (int j = 0; j < loop; j++) {
                if(src == -1){
                    src = rd.nextInt() - 1;
                }else{
                    dest = rd.nextInt() - 1;
                    graph.addEdge(src, dest);
                    src = dest;
                }
            }
        }

        boolean isCompleted = false;
        isCompleted = graph.topologicalSort();

        if(isCompleted){
            int[] result = graph.getResult();
            for (int e : result) {
                System.out.println(e);
            }
        }else{
            System.out.println(0);
        }

    }

    static class Graph{
        private int v, e;
        private int[] indegrees;
        private ArrayList<ArrayList<Integer>> adj;
        private int loopCnt;
        private int[] result;

        public Graph(int v){
            this.v = v;
            this.indegrees = new int[v];
            this.adj = new ArrayList<>();
            for(int i=0; i < v; i++){
                adj.add(new ArrayList<>());
            }
            this.loopCnt = 0;
            this.result = new int[v];
        }

        public void addEdge(int src, int dest){
            this.adj.get(src).add(dest);
            this.indegrees[dest]++;
        }

        public boolean topologicalSort(){
            Queue<Integer> q = new LinkedList<>();

            for(int i=0; i<v; i++){
                if(indegrees[i] == 0){
                    q.offer(i);
                }
            }

            int loopCnt = 0;
            while(!q.isEmpty()){
                int currVertex = q.poll();
                result[loopCnt++] = currVertex + 1;

                for(int nextV : this.adj.get(currVertex)){
                    --indegrees[nextV];
                    if(indegrees[nextV] == 0)
                        q.offer(nextV);
                }
            }

            if(loopCnt != this.v)
                return false;
            return true;
        }

        public int[] getResult(){
            return this.result;
        }

        public int[] getIndegrees(){
            return this.indegrees;
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
