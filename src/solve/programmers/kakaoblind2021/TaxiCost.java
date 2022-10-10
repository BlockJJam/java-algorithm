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

        // 모든 그래프 값을 채워넣는다. 양방향 주의
        for(int i=0 ;i< fares.length;i++){
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];

            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }

        // 시작지점에서 모든 정점까지의 최단거리를 구해놓는다.
        int[] distFirst = dijkstra(S);

        int minDist = Integer.MAX_VALUE;
        for(int i=1; i < distFirst.length; i++){
            // 모든 정점이 중간지점이 되어, 시작지점 - 중간지점, 중간 지점 - A/B까지 거리의 최솟값을 구한다.
            int dist = distFirst[i];
            int[] startDist = dijkstra(i);
            int aDist = startDist[A];
            int bDist = startDist[B];
            minDist = Math.min(minDist, dist + aDist + bDist);
            System.out.println("minDist = " + dist + " " + aDist + " " + bDist);
        }

        answer = minDist;
        System.out.println("answer = " + answer);

        return answer;
    }

    static int V, S, A, B;  // 정점, 출발지, A지점, B지점 순
    static ArrayList<ArrayList<Node>> graph; // 모든 경로를 담는다.

    public static int[] dijkstra(int start){
        // 시작 지점을 입력받아, 해당 노드에서 인접한 정점의 이동거리를 최단거리로 업데이트 하고,
        // 인접 정점 중에 가장 비용이 적은 곳부터 순회한다.(pq이용)
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
