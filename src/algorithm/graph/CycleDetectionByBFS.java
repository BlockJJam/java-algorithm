package algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

public class CycleDetectionByBFS {
    public static void main(String[] args) {
        Graph g = new Graph(5);

        g.addEdge(1, 2);
        g.addEdge(2, 1);
        g.addEdge(2, 4);
        g.addEdge(3, 0);
        g.addEdge(3, 2);
        g.addEdge(4, 3);

        if(g.isCycle()){
            System.out.println("Cycle Detected!");
        } else {
            System.out.println("No Cycle!");
        }
    }

    static class Graph{
        int v; // 정점의 개수
        LinkedList<Integer>[] adj; // 인접 리스트 방식 이용( 정점 클래스는 없고
        int[] inDegree;

        public Graph(int v){
            this.v = v;

            this.adj = new LinkedList[v];
            for(int i=0; i<this.v; i++){
                adj[i] = new LinkedList<>();
            }

            this.inDegree = new int[this.v];
        }

        // 간선 추가
        public void addEdge(int s, int d){
            this.adj[s].add(d);
            this.inDegree[d]++; // 목적지점으로 들어오는 내차수 1개씩
        }

        // Cycle 존재 여부
        public boolean isCycle(){
            Queue<Integer> q = new LinkedList<>();
            for(int i=0; i< this.v; i++){
                if(this.inDegree[i] == 0){
                    q.add(i);
                }
            }
            int clear_visited = 0; // 방문 완료된 정점 개수
            while(!q.isEmpty()){
                int current = q.poll();
                for(int dest: adj[current]){
                    if(--this.inDegree[dest] == 0){
                        q.add(dest);
                    }
                }
                clear_visited++;
            }

            if(clear_visited != this.v){
                return true;
            }
            return false;
        }
    }
}
