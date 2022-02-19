package algorithm.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TopologicalSortByStack {
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

        // 모든 위상정렬이 가능한 경우의 수를 채우자
        graph.allTopologicalSort();

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

            for(int i=0; i< V; i++){
                this.adj.add(new ArrayList<>());
            }
        }

        // Graph의 정점간 연결을 수행
        public void addEdge(int source, int target){
            if(source > this.V || target > this.V){
                throw new NullPointerException();
            }

            this.adj.get(source).add(target);
            this.E++;
            this.indegree[target]++;
        }

        // 위상 정렬 수행 메서드 - stack
        public boolean topologicalSort(int[] result) {
            boolean[] visited = new boolean[this.V];
            boolean[] beingVisited = new boolean[this.V];

            Stack<Integer> topolStack = new Stack<>();

            boolean sortResult = false;

            for(int i=0; i< this.V; i++){
                if(!visited[i]){
                    sortResult = recursion(i, topolStack, visited, beingVisited);
                }

                if(!sortResult){
                    break;
                }
            }

            int idx = 0;
            if(sortResult){
                while(!topolStack.isEmpty()){
                    result[idx++] = topolStack.pop();
                }
            }

            return sortResult;
        }

        // stack에 넣기 위해 자신이 가리키는 이전의 모든 정점을 우선적으로 stack에 넣는다
        private boolean recursion(int v, Stack<Integer> topolStack, boolean[] visited, boolean[] beingVisited) {
            beingVisited[v] = true;
            ArrayList<Integer> arrayList = this.adj.get(v);

            // 초기에는 true(=no cycle)로 설정
            boolean isNotCycle = true;

            for(int target: arrayList){
                // 이미 재귀 중에 쌓여 방문중(완료 아님)인 상태면 Cycle이 있음을 나타냄
                if(beingVisited[target]){
                    return false;
                }else if( !visited[target]){
                    // 이전에 이미 방문하지 않았을 때, 재귀로 target을 stack에 넣을지 고려하고 cycle여부를 판단한다
                    isNotCycle = recursion(target, topolStack, visited, beingVisited);
                }

                // isNotCycle이 true면 상관없지만, false면 cycle이 있으므로 중단
                if(!isNotCycle){
                    return false;
                }
            }

            topolStack.push(v);
            visited[v] = true;
            // stack에 넣었기 때문에 방문 중인 상태를 해제
            beingVisited[v] = false;

            return true;
        }

        // Backtracking으로 위상정렬이 되는 모든 경우의 수를 구해보기
        public int[] allTopologicalSort(){
            // 현재 정점크기의 bool 배열추가
            boolean[] visited = new boolean[this.V];

            Stack<Integer> topolStack = new Stack<>();

            recursion(topolStack, visited);

            int[] sortResult = new int[this.V];
            int idx = 0;

            while(!topolStack.isEmpty()){
                sortResult[idx++] = topolStack.pop();
            }
            return sortResult;
        }

        // 모든 경우의 수를 찾는 위상정렬의 recursion 함수
        public void recursion(Stack topolStack, boolean[] visited){
            // 위상 정렬 결과가 나왔는지 확인 flag
            boolean beingTopolSort = false;

            for(int i=0; i< this.V; i++){
                if(!visited[i] && indegree[i] == 0){

                    // 결과에 포함시키기 위한 Stack에 추가
                    visited[i] = true;
                    topolStack.push(i);

                    for(int adjacent : this.adj.get(i)){
                        this.indegree[adjacent]--;
                    }

                    // 재귀를 통해 다음 내차수가 0인 경우가 있는지 모두 순회
                    recursion(topolStack, visited);

                    visited[i] = false;
                    topolStack.pop();
                    for(int adjacent : this.adj.get(i)) {
                        this.indegree[adjacent]++;
                    }

                    beingTopolSort = true;
                }
            }

            // beingTopolSort = false면 모든 위상정렬 찾는 순회가 종료된 상태로 결과를 얻을 수 있음
            if(!beingTopolSort){
                System.out.print("all-topological-sort's element: ");
                topolStack.forEach(i -> System.out.print(i + " "));
                System.out.println();
            }
        }
    }
}
