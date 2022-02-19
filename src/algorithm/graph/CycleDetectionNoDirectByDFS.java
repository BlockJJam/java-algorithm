package algorithm.graph;

import algorithm.search.BinarySearch;

import java.util.LinkedList;

public class CycleDetectionNoDirectByDFS {
    public static void main(String[] args){
        Graph g = new Graph(5);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(2, 4);

        if(g.isCycle()){
            System.out.println("Cycle Detected! - The List are below!");
            for(int i=0; i < g.v; i++){
                if(g.cycle[i]) System.out.print(i + ", ");
            }
        } else {
            System.out.println("No Cycle!");
        }
    }

    static class Graph{
        private int v;
        private LinkedList<Integer>[] adj;
        boolean[] cycle; // 순환에 해당하는 정점 리스트를 저장( 순환 시 true )

        public Graph(int v) {
            this.v = v;
            this.adj = new LinkedList[this.v];
            cycle = new boolean[this.v];

            for(int i=0; i < this.v; i++){
                this.adj[i] = new LinkedList<>();
            }
        }

        // 간선 추가 - 무방향 그래프 이므로 양쪽으로 연결
        public void addEdge(int s, int d){
            this.adj[s].add(d); // S -> D 로 연결
            this.adj[d].add(s); // D -> S 로 연결
        }

        public boolean isCycle(){
            boolean[] visited = new boolean[this.v];

            for (int i = 0; i < this.v; i++) {
                // 이미 방문한 정점은 다시 탐색하지 않아도 된다, 이미 탐색으로 Cycle여부까지 확인했기 때문
                // 아래 주석처리는 단순 Cycle여부만 체크
                // if(visited[i]) continue;

                // 최초 방문 정점은 parent를 -1로 지정
                // start는 어떤 정점에서 시작했는지를 나타낼 것이다 -> i로 시작
                if (isCycleUtil(i, visited, -1, i)) {
                    return true;
                }

                // 새로운 정점에서 시작할 때를 대비해서, visited를 초기화 해주자
                // 단순 Cycle 체크를 할 때 위의 주석처리된 코드를 풀고, 밑 코드를 주석할 것
                visited = new boolean[this.v];
            }
            return false;
        }

        public boolean isCycleUtil(int i, boolean[] visited, int parent, int start){
            visited[i] = true;

            for(int v : adj[i]){
                // 아직 방문하지 않은 정점일 때, 재귀로 파고들자
                if(!visited[v]){
                    if(isCycleUtil(v, visited, i, start)){
                        cycle[v] = true;
                        return true;
                    }
                // v가 방문한 적은 있고, 다음 인접 정점의 parent가 아니라면 중간에 다른 정점을 방문한 뒤 도착한 것을 의미
                // 그런데 v == start가 되면 최초 시작한 지점으로 돌아왔다는 의미 -> 이 체크가 없다면 어떤 부분에서 Cycle인지 확인할 때 오류가 발생할 수 있다
                // start 지점으로 돌아왔다!는 의미는 "현재까지 탐색된 내역"이 Cycle을 이룬다는 의미
                // 만약, 중간 부분만 Cycle 이었다면, Cycle이 아닌 정점까지 Cycle의 일부로 판단할 수 있기 때문에 "start == v"가 있는 것
                }else if(v != parent && v == start){
                    cycle[v] = true;
                    return true;
                }
            }

            return true;
        }
    }
}
