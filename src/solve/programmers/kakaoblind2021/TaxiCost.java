package solve.programmers.kakaoblind2021;

import java.util.*;

public class TaxiCost {

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        V = n;
        S = s;
        A = a;
        B = b;

        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }
        parents = new int[V+1];

        for(int i=0 ;i< fares.length;i++){
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];

            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }

        int[] distFirst = dijkstra(S);
        int minDist = Integer.MAX_VALUE;
        for(int i=1; i < distFirst.length; i++){
            int dist = distFirst[i];
            int[] startDist = dijkstra(i);
            int aDist = startDist[A];
            int bDist = startDist[B];
            minDist = Math.min(minDist, dist + aDist + bDist);
            System.out.println("minDist = " + dist + " " + aDist + " " + bDist);
        }
//        qA = searchPath(A);
//        qB = searchPath(B);

        answer = minDist;
        System.out.println("answer = " + answer);

        return answer;
    }

    static int V, S, A, B;
    static ArrayList<ArrayList<Node>> graph;
    static int[] parents;
    static Queue<Integer> qA, qB;

    public static int[] dijkstra(int start){
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)-> Integer.compare(o1.cost, o2.cost));

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curr = pq.poll();

            if(dist[curr.next] >= curr.cost){
                ArrayList<Node> nextNodes = graph.get(curr.next);
                for(int i = 0; i< nextNodes.size(); i++){
                    Node next = nextNodes.get(i);

                    if(curr.cost + next.cost < dist[next.next]){
                        dist[next.next] = curr.cost + next.cost;
                        pq.add(new Node(next.next, dist[next.next]));
                        parents[next.next] = curr.next;
                    }
                }
            }
        }

        return dist;
    }

    public static Queue<Integer> searchPath(int dest){
        Queue<Integer> q = new LinkedList<>();
        int curr = dest;
        System.out.println("curr = " + curr);
        while(curr != S){
            q.offer(curr);
            curr = parents[curr];
            System.out.println("curr = " + curr);
        }

        q.offer(curr);

        System.out.println("end");
        return q;
    }

    static class Node{
        int next;
        int cost;

        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        solution(6,4,6,2, new int[][]{
                new int[]{4,1,10},
                new int[]{3,5,24},
                new int[]{5,6,2},
                new int[]{3,1,41},
                new int[]{5,1,24},
                new int[]{4,6,50},
                new int[]{2,3,22},
                new int[]{2,4,66},
                new int[]{1,6,25}
        });
        System.out.println();
        solution(7,3,4,1, new int[][]{
                new int[]{5,7,9},
                new int[]{4,6,4},
                new int[]{3,6,1},
                new int[]{3,2,3},
                new int[]{5,1,24},
                new int[]{2,1,6}
        });
        System.out.println();
        solution(6,4,5,6, new int[][]{
                new int[]{2,6,6},
                new int[]{6,3,7},
                new int[]{4,6,7},
                new int[]{6,5,11},
                new int[]{2,5,12},
                new int[]{5,3,20},
                new int[]{2,4,8},
                new int[]{4,3,9},
        });
    }
}
