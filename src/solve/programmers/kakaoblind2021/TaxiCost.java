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

        int[] dist = dijkstra();
        qA = searchPath(A);
        qB = searchPath(B);

        answer += dist[A];
        answer += dist[B];

        int samePoint = -1;
        while(!qA.isEmpty() && !qB.isEmpty()){
            int aPoint = qA.poll();
            int bPoint = qB.poll();
            if(aPoint != bPoint){
                break;
            }
            samePoint = aPoint;
        }

        if(samePoint != -1){
            System.out.println("!!!");
            answer -= dist[samePoint];
        }

        System.out.println("answer = " + answer);

        return answer;
    }

    static int V, S, A, B;
    static ArrayList<ArrayList<Node>> graph;
    static int[] parents;
    static Queue<Integer> qA, qB;

    public static int[] dijkstra(){
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)-> Integer.compare(o1.cost, o2.cost));

        pq.offer(new Node(S, 0));
        dist[S] = 0;

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
                new int[]{3,1,41},
                new int[]{5,1,24},
                new int[]{4,6,50},
                new int[]{2,3,22},
                new int[]{2,4,66},
                new int[]{1,6,25}
        });
    }
}
