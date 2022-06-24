package algorithm.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
    static int V, E, start;
    static ArrayList<ArrayList<Node>> graph;

    static class Node{
        int next, cost;

        Node(int next, int cost){
            this.next = next;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException{
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for(int i=0; i<V+1; i++){
            graph.add(new ArrayList<Node>());
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(next, cost));
        }

        // 다익스트라 알고리즘 초기화
        int[] dist = new int[V+1];
        for(int i=0; i<V+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        // 주의점1. 다익스트라 알고리즘의 최소비용 기준으로 추출할 수 있도록 비교해주자( 최대 비용은 최악의 경우 지수시간 필요 )
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
        // 시작 -> 시작 노드가 가장 최소 비용(순환이 없으니까)
        q.offer(new Node(start, 0));

        // 다익스트라 알고리즘 또한 시작 노드 비용을 0으로 할당
        dist[start] = 0;
        while(!q.isEmpty()){
            Node curNode = q.poll();

            // 목표 정점이 정해져 있는 문제 -> 해당 목표 노드까지 최솟값 갱신이 완료되었다는 로직이 밑에 주석 코드
//            if(curNode.next == end){
//                System.out.println(dist[curNode.next]);
//                return;
//            }

            // 꺼낸 노드 = 현재 최소 비용을 갖는 노드로, 해당 노드 비용이 현재 dist배열에 기록된 내용보다 크다면 고려할 필요 없으므로 스킵
            // 주의점2. 중복노드 방지1
            if(dist[curNode.next] >= curNode.cost){
                // 선택된 주변 노드를 고려
                for(int i=0; i<graph.get(curNode.next).size(); i++){
                    Node nextNode = graph.get(curNode.next).get(i);
                    // 만약 주변 노드까지의 현재 dist값(비용)과 현재 선택된 노드로부터 주변 노드로 가는 비용을 비교하고, 더 작은 값을 선택
                    // 주의점3. 중복노드 방지2
                    if(dist[nextNode.next] > curNode.cost + nextNode.cost){
                        dist[nextNode.next] = curNode.cost + nextNode.cost;
                        // 갱신이 되었기 때문에 큐에 넣는다.
                        q.offer(new Node(nextNode.next, dist[nextNode.next]));
                    }
                }
            }
        }
        System.out.println(Arrays.toString(dist));
    }
}
