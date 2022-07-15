package solve.baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PlanetConnection_16398 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int[][] graph = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                graph[i][j] = rd.nextInt();
            }
        }


        Kruskal kruskal = new Kruskal(N);
        for (int i = 1; i < N + 1; i++) {
            for (int j = N; j > i; j--){
                kruskal.addEdge(i, j, graph[i][j]);
            }
        }

        long result = kruskal.makeFlowByKruskal();
        System.out.println(result);


    }

    static class Edge{
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static class Kruskal {
        private int V;
        private int[] parents;
        private ArrayList<Edge> edgeList;

        Kruskal(int N){
            this.V = N;
            this.parents = new int[N+1];
            makeParents();
            this.edgeList = new ArrayList<>();
        }

        long makeFlowByKruskal(){
            long res = 0;
            int cnt = 0;

            edgeList.sort((o1,o2)-> Long.compare(o1.weight, o2.weight));

            for(Edge edge: edgeList){
                if(union(edge.from, edge.to)){
                    res += edge.weight;

                    if(++cnt == this.V -1){
                        return res;
                    }
                }
            }

            return res;
        }

        void addEdge(int from, int to, int weight){
            this.edgeList.add(new Edge(from, to, weight));
        }

        int find(int v){
            if(v == parents[v]){
                return v;
            }
            return parents[v] = find(parents[v]);
        }

        boolean union(int from, int to){
            int fromRoot = find(from);
            int toRoot = find(to);

            if(fromRoot == toRoot){
                return false;
            }

            parents[toRoot] = fromRoot;
            return true;
        }


        void makeParents(){
            for(int i=0; i< this.V+1; i++){
                this.parents[i] = i;
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
