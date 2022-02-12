package algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        Vertex v1 = new Vertex('A');
        Vertex v2 = new Vertex('B');
        Vertex v3 = new Vertex('C');
        Vertex v4 = new Vertex('D');
        Vertex v5 = new Vertex('E');
        Vertex v6 = new Vertex('F');
        Vertex v7 = new Vertex('G');
        Vertex v8 = new Vertex('H');

        Graph graph = new Graph(8);
        graph.addEdge(v1, v2);
        graph.addEdge(v1, v3);
        graph.addEdge(v2, v4);
        graph.addEdge(v2, v5);
        graph.addEdge(v1, v2);
        graph.addEdge(v3, v6);
        graph.addEdge(v3, v7);
        graph.addEdge(v5, v6);
        graph.addEdge(v5, v8);
        graph.addEdge(v6, v7);

        graph.bfs(v1);

        // Disconnected 된(끊겨져 있는 정점이 있을 때) Graph일 경우 아래와 같이 방문 상태가 아닌 모든 정점에서 부터 시작하면 된다.
        // 사실, 아래와 같이 별도의 Class로 Vertex를 정의했고 그 안에서 인접 정점을 지정하였기 때문에 아래와 같이 배열을 생성함
        // 실제로 인접 리스트 방식으로 생성하면 단순히 배열을 순환하면 된다.
        // 참조 : https://www.geeksforgeeks.org/bfs-disconnected-graph/?ref=rp

        /*
        Vertex[] vertexes = {v1, v2, v3, v4, v5, v6, v7, v8};
        for(Vertex v : vertexes){
            if(!v.visited) {
                graph.bfs(v);
            }
        }
         */

    }
    static class Vertex{
        char data; // 현 정점 데이터
        boolean visited = false; // 현 정점을 방문한 적이 있는지
        LinkedList<Vertex> adList = new LinkedList<>();// 현재 정점의 인접 리스트

        public Vertex(char data){
            this.data = data;
        }
    }

    static class Graph{
        private int v;
        public Graph(int v){
            this.v = v;
        }

        // source -> dest 정점을 이어주는 메서드
        // 상호 연결
        public void addEdge(Vertex s, Vertex d){
            s.adList.add(d);
            d.adList.add(s);
        }

        // BFS 수행 메서드
        public void bfs(Vertex s){
            Queue<Vertex> queue = new LinkedList<>();
            s.visited = true;
            queue.offer(s);

            StringBuilder sb = new StringBuilder();

            // 큐에서 더이상 꺼낼 데이터가 없을 때까지 반복
            while(!queue.isEmpty()){
                Vertex currV = queue.poll();
                sb.append(currV.data).append(" ");

                for(Vertex v: currV.adList){
                    if(!v.visited){
                        queue.offer(v);
                        v.visited = true;
                    }
                }
            }
            System.out.println(sb);
        }
    }
}
