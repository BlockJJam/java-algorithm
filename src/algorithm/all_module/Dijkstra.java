package algorithm.all_module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {

    static class Node {
        int next;
        int cost;

        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    static int V, E;
    static ArrayList<ArrayList<Node>> graph;

    public static int[] dijkstra(int start){
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(((n1, n2) -> Integer.compare(n1.cost, n2.cost)));

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curr = pq.poll();


            if(dist[curr.next] >= curr.cost){
                for(int i=0; i < graph.get(curr.next).size(); i++){
                    Node next = graph.get(curr.next).get(i);

                    if(curr.cost + next.cost < dist[next.next]){
                        dist[next.next] = curr.cost + next.cost;
                        pq.add(new Node(next.next, dist[next.next]));
                    }
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<E; i++){
            int from = 0;
            int to = 0;
            int cost = 0;

            graph.get(from).add(new Node(to, cost));
        }

        dijkstra(0);
    }
}
