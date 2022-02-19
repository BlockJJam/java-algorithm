package algorithm.graph;

import java.util.LinkedList;

// 방향성이 있을 때의 dfs를 활용한 순환 탐지
public class CycleDetectionByDFS {
    public static void main(String[] args){
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        if(g.isCycle()){
            System.out.println("Cycle Detected!");
        } else {
            System.out.println("No Cycle!");
        }
    }

    static class Graph{
        private int v;
        private LinkedList<Integer>[] adj;

        public Graph(int v){
            this.v = v;
            this.adj = new LinkedList[this.v];
            for(int i=0; i < this.v; i++){
                this.adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s, int d){
            this.adj[s].add(d);
        }

        // 순환의 여부만 알려준다, -> 즉 순환 체크로직까지 담겨있진 않다
        public boolean isCycle(){
            boolean[] visited = new boolean[this.v];

            // recStack에 있다 -> 방문 진행중이라는 의미( 방문 완료는 visited )
            // 즉, beingVisited 의미
            boolean[] recStack = new boolean[this.v];

            // 끊겨져 있는 부분까지 체크하기 위한 루프
            for (int i = 0; i < this.v; i++) {
                if(isCycleUtil(i, visited, recStack)){
                    return true;
                }
            }
            return false;
        }


        private boolean isCycleUtil(int i, boolean[] visited, boolean[] recStack){
            // 현 stack에 있다면, 방문 중이므로, Cycle이 있다
            if (recStack[i]) {
                return true;
            }

            // 방문이 끝난 경우는 그냥 돌아감
            if(visited[i]){
                return false;
            }

            visited[i] = true;
            recStack[i] = true;

            for( int v : this.adj[i]){
                if(isCycleUtil(i, visited, recStack)){
                    return true;
                }
            }
            recStack[i] = false;
            return false;
        }
    }
}
