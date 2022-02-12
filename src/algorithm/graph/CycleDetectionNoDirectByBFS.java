package algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

public class CycleDetectionNoDirectByBFS {
    public static void main(String[] args){
        Graph g = new Graph(5);

        // 위 그림의 index는 -1씩 해서 적용
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 4);

        if(g.isCycle()){
            System.out.println("Cycle Detected!");
        } else {
            System.out.println("No Cycle!");
        }
    }

    static class Graph{
        int v;
        LinkedList<Integer>[] adj;
        boolean[] visited; // 각 정점 방문 여부
        boolean[] cycle; // 각 정점이 순환 내에 있는지 저장

        public Graph(int v){
            this.v = v;
            this.adj = new LinkedList[v];
            this.visited = new boolean[v];
            this.cycle = new boolean[v];
            for(int i=0; i< this.v; i++){
                this.adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s, int d){
            this.adj[s].add(d);
            this.adj[d].add(s);
        }

        // 각 정점에서 Cycle 탐지를 수행
        public boolean isCycle(){
            // disconnected 부분을 체크하기 위해 모든 정점에서 탐색 수행
            for (int i = 0; i < this.v; i++) {
                if(!this.visited[i] && isCycleUtil(i)){
                    return true;
                }
            }
            return false;
        }

        // 실제 순환을 하는지 여부를 판단하는 함수
        public boolean isCycleUtil(int s){
            int[] parent = new int[this.v];

            Queue<Integer> q = new LinkedList<>();
            visited[s] = true;
            q.offer(s);

            while(!q.isEmpty()){
                int u = q.poll();

                // 인접 접점을 모두 탐색
                for(int v : adj[u]){
                    // 아직 방문되지 않은 정점이면 탐색 수행
                    if(!visited[v]){
                        visited[v] = true;
                        q.add(v);
                        parent[v] = u;
                    }else if(parent[u] != v){
                        // 이미 방문한 인접 정점 v가 현재 정점 u의 부모가 아니라면 Cycle이 있다는 것
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
