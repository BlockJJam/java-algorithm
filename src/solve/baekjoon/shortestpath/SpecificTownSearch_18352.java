package solve.baekjoon.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SpecificTownSearch_18352 {
    static int N, M, K, X;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        N = rd.nextInt(); // V개수
        M = rd.nextInt(); // E개수
        K = rd.nextInt(); // 원하는 최단 거리
        X = rd.nextInt(); // 출발 도시 정보

        graph = new ArrayList<>();
        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<Node>());
        }

        for(int i=0 ; i<M; i++){
            int start = rd.nextInt();
            int next = rd.nextInt();
            int cost = 1;

            graph.get(start).add(new Node(next, cost));
        }

        int[] dist = new int[N+1];
        for (int i = 0; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2)-> Integer.compare(o1.cost, o2.cost));

        q.offer(new Node(X, 0));
        dist[X] = 0;

        while(!q.isEmpty()){
            Node curNode = q.poll();

            if(dist[curNode.next] >= curNode.cost){
                for(int i=0; i<graph.get(curNode.next).size(); i++){
                    Node nextNode = graph.get(curNode.next).get(i);

                    if(dist[nextNode.next] > curNode.cost + nextNode.cost){
                        dist[nextNode.next]  = curNode.cost + nextNode.cost;
                        q.offer(new Node(nextNode.next, dist[nextNode.next]));
                    }
                }
            }
        }

        boolean hasK = false;
        for(int i=1; i<N+1; i++){
            if(dist[i] == K){
                System.out.println(i);
                hasK = true;
            }
        }
        if(!hasK) System.out.println(-1);
    }

    static class Node{
        int next, cost;

        Node(int next, int cost){
            this.next = next;
            this.cost = cost;
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
