package solve.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 파티 문제
 * - 다익스트라를 2번 이용하는 문제로 모든 시작지점 -> X위치까지의 최단거리 배열을 만든다.
 * - X -> 모든 시작지점 배열은 1개만 만들면 된다. dijkstra(X)객체 하나
 * - 모든 시작지점에서부터 위 2개의 배열을 이용해서, (s -> x) + (x -> s)까지의 최댓값을 구하면 된다.
 */
public class Party_1238 {

    static int N,M,X;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        N = rd.nextInt();
        M = rd.nextInt();
        X = rd.nextInt();

        graph = new ArrayList<>();
        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            int src = rd.nextInt();
            int dest = rd.nextInt();
            int time = rd.nextInt();
            graph.get(src).add(new Node(dest, time));
        }
        /***** 주입 작업 끝 *****/

        int[] distsFromX = dijkstra(X);

        int answer = 0;
        for(int i = 1; i<N + 1; i++){
            int[] distsToX = dijkstra(i);
            answer = Math.max(answer, distsToX[X] + distsFromX[i]);
        }

        System.out.println(answer);
    }

    static int[] dijkstra(int start){
        int[] dist = new int[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curr = pq.poll();

            if(dist[curr.next] >= curr.cost){
                for(Node next : graph.get(curr.next)){
                    if(curr.cost + next.cost < dist[next.next]){
                        dist[next.next] = curr.cost+ next.cost;
                        pq.add(new Node(next.next, dist[next.next]));
                    }
                }
            }
        }

        return dist;
    }

    static class Node{
        int next;
        int cost;

        public Node(int next, int cost) {
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
