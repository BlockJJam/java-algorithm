package solve.baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TownDividePlan_1647 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int M = rd.nextInt();
        Prim prim = new Prim(N);

        for(int i=0; i<M; i++){
            int a = rd.nextInt();
            int b = rd.nextInt();
            int weight = rd.nextInt();

            prim.addEdge(a,b,weight);
            prim.addEdge(b,a,weight);
        }

        prim.saveMinTotalWeightByPrim();

        System.out.println(prim.getResult() - prim.getMaxWeight());

    }

    static class Node{
        int to;
        int weight;

        public Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class Prim{
        private ArrayList<Node>[] adjList;
        private boolean[] visited;
        private int result;
        private int maxWeight;
        private PriorityQueue<Node> pq;
        private int V;

        public Prim(int N){
            this.adjList = new ArrayList[N+1];
            for(int i=1; i<N+1; i++){
                this.adjList[i] = new ArrayList<>();
            }
            this.visited = new boolean[N+1];
            this.result = 0;
            this.pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.weight, o2.weight));
            this.V = N;
            this.maxWeight = 0;
        }

        void addEdge(int src, int dest, int weight){
            this.adjList[src].add(new Node(dest, weight));
        }

        int getResult(){
            return this.result;
        }

        void saveMinTotalWeightByPrim(){
            pq.add(new Node(1,0));

            int cnt = 0;
            while(!pq.isEmpty()){
                Node currNode = pq.poll();

                if(!visited[currNode.to]){
                    result += currNode.weight;
                    maxWeight = Math.max(currNode.weight, maxWeight);
                    visited[currNode.to] = true;

                    if(++cnt == this.V){
                        return;
                    }

                    for(int i=0; i<adjList[currNode.to].size(); i++){
                        Node nextNode = adjList[currNode.to].get(i);
                        if(!visited[nextNode.to]){
                            pq.add(nextNode);
                        }
                    }
                }
            }
        }

        int getMaxWeight(){
            return this.maxWeight;
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
