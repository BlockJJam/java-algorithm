package solve.programmers.summerwinter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Delivery {

//    public static int solution(int N, int[][] road, int K) {
//        int answer = 0;
//
//        int[][] graph = new int[N+1][N+1];
//        for(int i=0; i<=N; i++){
//            Arrays.fill(graph[i], 2_000_000);
//        }
//
//        for(int i=0; i<= N; i++){
//            graph[i][i] = 0;
//        }
//        for(int[] roadInfo : road){
//            for(int i=0; i<roadInfo.length; i++){
//                int a = roadInfo[0];
//                int b = roadInfo[1];
//                int c = roadInfo[2];
//
//                graph[a][b] = Math.min(graph[a][b], c);
//                graph[b][a] = Math.min(graph[b][a], c);
//            }
//        }
//
//        for(int k = 1; k <= N; k++){
//            for(int i = 1; i <= N; i++){
//                for(int j = 1; j <= N; j++){
//                    graph[i][j] = Math.min(graph[i][j] , graph[i][k] + graph[k][j]);
//                }
//            }
//        }
//
//
//        for(int i = 1; i<=N; i++){
//            // System.out.println("graph = " + graph[1][i]);
//            if(graph[1][i] <= K){
//                answer++;
//            }
//        }
//
//        return answer;
//    }


    // 도로를 graph를 만들어 담는다
    // 플로이드 워셸 알고리즘을 활용해서 시작지점에서 모든 지점까지의 최단 거리를 구한다
    // graph[시작지점][다른 지점] 의 거리가 K 미만인 개수를 모두 구한다

    // 다익스트라 버전

    public static int solution(int N, int[][] road, int K) {
        int answer = 0;

        Graph graph = new Graph(N, road.length, road);
        int[] dist = graph.dijkstra(1);

        for(int i=0; i<dist.length; i++){
            if(dist[i] <= K){
                answer++;
            }
        }


        return answer;
    }

    static class Node{
        int dest;
        int cost;

        Node(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }
    }

    static class Graph{
        int V, E;
        ArrayList<ArrayList<Node>> graph;
        public Graph(int V, int E, int[][] roads){
            this.V = V;
            this.E = E;
            this.graph = new ArrayList<>();

            for( int i=0; i<V+1; i++){
                this.graph.add(new ArrayList<>());
            }
            initGraph(roads);
        }

        private void initGraph(int[][] roads){
            for(int[] road : roads){
                int a = road[0];
                int b = road[1];
                int c = road[2];

                graph.get(a).add(new Node(b, c));
                graph.get(b).add(new Node(a, c));
            }
        }

        public int[] dijkstra(int start){
            int[] retDist = new int[V+1];

            // 시작점부터의 거리를 담을 retDist를 초기화
            Arrays.fill(retDist, Integer.MAX_VALUE);
            retDist[start] = 0;

            // cost가 가장 적은 순으로 node를 담는다.
            PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)-> Integer.compare(o1.cost, o2.cost));
            pq.offer(new Node(start, 0));

            while(!pq.isEmpty()){
                Node node = pq.poll();

                // 꺼낸 node의 인접 node간의 거리를 비교한다
                if(retDist[node.dest] >= node.cost){
                    for(Node nextNode : graph.get(node.dest)){
                        if(retDist[nextNode.dest] > node.cost + nextNode.cost){
                            retDist[nextNode.dest] = node.cost + nextNode.cost;
                            pq.offer(new Node(nextNode.dest, retDist[nextNode.dest]));
                        }
                    }
                }
            }

            return retDist;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(6, new int[][]{ {1,2,1},{1,3,2}, {2,3,2}, {3,4,3}, {3,5,2}, {3,5,3}, {5, 6, 1} }, 4));
    }
}
