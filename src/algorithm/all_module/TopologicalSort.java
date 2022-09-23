package algorithm.all_module;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopologicalSort {

    static class Graph{
        private int V;
        private int E;
        private ArrayList<ArrayList<Integer>> adj;
        private int[] indegree;

        public void createGraph(int v){
            this.V = v;
            this.E = 0;
            this.adj = new ArrayList<>();
            this.indegree = new int[v];

            for (int i = 0; i < v; i++) {
                this.adj.add(new ArrayList<>());
            }
        }

        public void addEdge(int src, int dest){
            if(src > this.V || dest > this.V)
                throw new NullPointerException("addEdge 함수를 확인하세요");

            this.adj.get(src).add(dest);
            this.indegree[dest]++;
            this.E++;
        }

        public boolean topologicalSort(int[] result){
            Queue<Integer> q = new LinkedList<>();

            for (int i = 0; i < this.V; i++) {
                if(this.indegree[i] == 0){
                    q.offer(i);
                }
            }

            int idx = 0;
            while(!q.isEmpty()){
                int v = q.poll();
                result[idx++] = v;

                for(int dest: this.adj.get(v)){
                    indegree[dest]--;
                    if(indegree[dest] == 0){
                        q.offer(dest);
                    }
                }
            }

            if(this.V != idx){
                return false;
            }
            return true;
        }
    }
}
