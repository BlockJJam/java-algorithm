package solve.baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class NetworkConnection_1922 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int N = rd.nextInt();
        int M = rd.nextInt();
        Prim prim = new Prim(N);

        for( int i = 0; i<M; i++){
            int a = rd.nextInt();
            int b = rd.nextInt();
            int weight = rd.nextInt();

            prim.addEdge(a, b, weight);
            prim.addEdge(b, a, weight);
        }
        prim.saveMinTotalWeightByPrim();

        System.out.println(prim.getResult());


    }

    static class Node{
        int to;
        int weight;

        public Node(int to, int weight){
            this.to  = to;
            this.weight = weight;
        }
    }

    static class Prim{
        private boolean[] visited;
        private ArrayList<Node>[] adjList;
        private int result;
        private PriorityQueue<Node> pq;
        private int V;

        public Prim(int N){
            this.adjList = new ArrayList[N+1];
            for(int i=1; i< adjList.length; i++){
                this.adjList[i] = new ArrayList<>();
            }
            this.visited = new boolean[N+1];
            this.result = 0;
            this.pq = new PriorityQueue<>((o1,o2)-> Integer.compare(o1.weight, o2.weight));
            this.V = N;
        }

        void addEdge(int src, int dest, int cost){
            this.adjList[src].add(new Node(dest, cost));
        }

        void saveMinTotalWeightByPrim(){
            pq.add(new Node(1,0));

            int cnt = 0;
            while(!pq.isEmpty()){
                Node edge = pq.poll();
//                System.out.println("curr: "+ edge.to +  " " + edge.weight);

                if(!visited[edge.to]){
                    result += edge.weight;
                    visited[edge.to] = true;
                    if(++cnt == V)
                        return;

                    for(int i=0; i<adjList[edge.to].size(); i++){
                        Node nextNode = adjList[edge.to].get(i);
                        if(!visited[nextNode.to]){
//                            System.out.println("next: " +nextNode.to+ " " +nextNode.weight);
                            pq.add(nextNode);
                        }
                    }
                }
            }
        }

        int getResult(){
            return this.result;
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
