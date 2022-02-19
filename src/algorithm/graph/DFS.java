package algorithm.graph;

import java.util.LinkedList;

public class DFS {
    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0,1);
        g.addEdge(0,5);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(2,0);
        g.addEdge(3,4);
        g.addEdge(4,2);

        g.dfs();
    }

    static class Graph{
        private int v;
        private LinkedList<Integer>[] adj;
        public Graph(int v){
            this.v = v;
            this.adj= new LinkedList[v];

            for(int i=0; i < this.v; i++){
                this.adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s, int d){
            this.adj[s].add(d);
        }

        public void dfs(){
            boolean[] visited = new boolean[this.v];

            for(int i=0; i < this.v; i++){
                if(!visited[i]){
                    dfs_recursive(i, visited);
                }
            }
        }

        public void dfs_recursive(int v, boolean visited[]){
            visited[v] = true;

            System.out.println(v +" ");

            for(int next : adj[v]){
                if(!visited[next]){
                    dfs_recursive(next, visited);
                }
            }
        }
    }


}
