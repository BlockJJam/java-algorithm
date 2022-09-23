package algorithm.all_module;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 최소 스패닝 트리 활용법 중 Prim과 함께 최소비용의 부분 집합으로 구성되는 트리
 * 정점 선택기반 트리 방식으로, 간선이 많을 때 사용하면 좋음
 * 최소 스패닝 트리 문제 유형
 * - 정점(제국, 도시, 네트워크)끼리 연결 하되 최소 비용으로 연결하는 경우
 * - 그래프에서 해당 위치까지의 최소 이동 거리
 */
public class Prim {
    static int V, E;
    static ArrayList<Node>[] adj;

    static class Node implements Comparable<Node>{
        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other){
            return Integer.compare(this.weight, other.weight);
        }
    }

    static long primPriortiyQueue(){
        boolean[] visited=  new boolean[V+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(1,0));
        long ret = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            Node edge = pq.poll();
            if(!visited[edge.to]){
                ret += edge.weight;
                visited[edge.to] = true;

                if(++cnt == V){
                    return ret;
                }

                for(int i=0; i < adj[edge.to].size(); i++){
                    Node next = adj[edge.to].get(i);
                    if(!visited[next.to])
                        pq.add(next);
                }
            }
        }
        return ret;
    }
}
