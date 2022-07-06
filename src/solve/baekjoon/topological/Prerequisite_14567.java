package solve.baekjoon.topological;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prerequisite_14567 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int N = rd.nextInt();
        int M = rd.nextInt();
        Graph graph = new Graph(N);

        for(int i=0; i<M; i++){
            int A = rd.nextInt()-1;
            int B = rd.nextInt()-1;
            graph.addEdge(A, B);
        }

        int[] result = new int[N];

        boolean isCycled = graph.topologicalSort(result);
        if(!isCycled){
            for(int target : result){
                System.out.print((target)+ " ");
            }
        }
    }

    static class Graph{
        private int V, E;
        private ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        private int[] indegree;

        Graph(int V){
            this.V = V;
            this.indegree = new int[V];
            this.E = 0;
            for(int i=0; i< V; i++){
                this.adj.add(new ArrayList<>());
            }
        }

        public void addEdge(int src, int dest){
            if( src > this.V || dest > this.V){
                throw new NullPointerException();
            }

            this.adj.get(src).add(dest);
            this.indegree[dest]++;
            this.E++;
        }

        public boolean topologicalSort(int[] result){
            Queue<Integer> q = new LinkedList<>();
            for(int i=0; i<V; i++){
                if(indegree[i] == 0){
                    q.offer(i);
                }
            }

            int qSize = q.size();
            int semester = 1;
            int idx = 0;
            while(!q.isEmpty()){
                while(qSize-- != 0){
                    int target = q.poll();
                    result[target] = semester;

                    for(int vertex : this.adj.get(target)){
                        --indegree[vertex];
                        if(indegree[vertex] == 0){
                            q.offer(vertex);
                        }
                    }
                    idx++;
                }
                qSize = q.size();
                semester++;
            }

            if(this.V != idx){
                return true;
            }
            return false;
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
