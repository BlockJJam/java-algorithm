package algorithm.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopologicalSortByQueue {
    public static void main(String[] args) {
        int n = 6;

        // Cycle이 없는 경우
        Graph graph = new Graph();

        graph.createGraph(n);
        graph.addEdge(2,3);
        graph.addEdge(3,1);
        graph.addEdge(4,0);
        graph.addEdge(4,1);
        graph.addEdge(5,0);
        graph.addEdge(5,2);

        int[] result = new int[n];
        boolean sort_result = graph.topologicalSort(result);
        if(sort_result){
            for(int i: result){
                System.out.print(i+", ");
            }
            System.out.println();
        }else{
            System.out.println("Cycle이 발생했습니다!");
        }

        // Cycle이 있는 경우
        graph = new Graph();

        graph.createGraph(n);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(5, 0);
        graph.addEdge(5, 2);
        // 1이 5를 가리키는 상황을 추가!
        // 이 부분에 의해 (1 - 5 - 3)이 서로 Cycle을 이루게 됨!
        graph.addEdge(1, 5);

        result = new int[n];
        sort_result = graph.topologicalSort(result);
        if(sort_result){
            for(int i : result){
                System.out.print(i + ", ");
            }
            System.out.println();
        } else {
            System.out.println("Cycle이 발생했습니다!");
        }
    }

    static class Graph{
        private int V;
        private int E;
        private ArrayList<ArrayList<Integer>> adj;
        private int[] indegree;

        // 임의로 그래프 생성하는 method
        // 그래프 생성은 상황에 따라 유연하게 구현하면 됨
        public void createGraph(int v){
            this.V = v;
            this.adj = new ArrayList<>();
            this.indegree = new int[v];

            for(int i=0; i< v; i++){
                this.adj.add(new ArrayList<>());
            }
        }

        // Graph의 정점간 연결을 수행
        public void addEdge(int source, int target){
            if(source > this.V || target > this.V){
                throw new NullPointerException();
            }

            this.adj.get(source).add(target);
            this.indegree[target]++;
            this.E++;
        }

        // 위상 정렬 수행 메서드 - queue
        public boolean topologicalSort(int[] result){
            Queue<Integer> q = new LinkedList<>();

            // 내차수가 0인  것들을 모두 queue에 삽입
            for(int i=0; i< this.V; i++){
                if(this.indegree[i] == 0){
                    q.offer(i);
                }
            }

            int idx = 0;
            while(!q.isEmpty()){
                int currVertex = q.poll();
                result[idx++] = currVertex;

                for( int target : this.adj.get(currVertex)){
                    this.indegree[target]--;

                    if(this.indegree[target] == 0){
                        q.offer(target);
                    }
                }
            }

            // idx와 V의 비교하여 다르다면 Cycle이 있다는 것
            if( this.V != idx){
                return false;
            }
            return true;
        }
    }
}
