package solve.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 트리의 지름을 구하는 문제(이론)
 * - 트리의 지름은 결국 양 끝 정점에서 동일한 정점 경로를 가진다.
 * - 즉 각 정점에서 제일 멀리 있는 정점까지 가는 길에는 항상 일부가 겹친다.
 * - 반례로, 만약 중간 정점이 최장 정점이 되려면, 기존의 양끝 정점간의 거리보다 큰 값이 나와야 한다.
 * - 근데 결국 가장 먼 정점 또한 중간 정점이 아니게 된다.
 *
 * 트리의 지름을 구하는 순서
 * 1. dfs/bfs를 통해 정점 하나에서 가장 먼 정점을 구한다.( 임의의 정점)
 * 2. 구한 정점에서 한번 더 가장 먼 정점까지의 거리를 구한다.(그래야, 지름에서 해당 정점을 포함할 것임으로)
 */
public class TreeDiameter_1167 {
    static int V;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        V = rd.nextInt();
        graph = new ArrayList[V+1];

        for(int i=1; i<V+1; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            int src = rd.nextInt();
            while(true){
                int v = rd.nextInt();
                if(v== -1 )break;
                int cost = rd.nextInt();

                graph[src].add(new Node(v, cost));
                graph[v].add(new Node(src, cost));
            }
        }

        int maxDiameter = 0;
        int[] bfs1 = bfs(1);

        int maxTemp = 0;
        int start = 0;
        for(int i=1; i<bfs1.length; i++){
            if(maxTemp < bfs1[i]){
                start = i;
                maxTemp = bfs1[i];
            }
        }

        int[] bfs2 = bfs(start);
        Arrays.sort(bfs2);

        System.out.println(bfs2[bfs2.length-1]);
    }

    static int[] bfs(int start){
        boolean[] visited = new boolean[V+1];
        int[] dist = new int[V+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o2.cost, o1.cost));

        pq.offer(new Node(start, 0));
        visited[start] = true;

        int max = 0;
        while(!pq.isEmpty()){
            Node curr = pq.poll();
//            System.out.println("curr.cost = " + curr.next);
            for (Node next : graph[curr.next]) {
                if(!visited[next.next]){
                    visited[next.next]= true;
                    pq.offer(new Node(next.next, next.cost));
                    dist[next.next] = dist[curr.next] + next.cost;
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
