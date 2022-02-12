package algorithm.graph;

import java.util.ArrayList;

public class AdjacentList {
    public static void main(String[] args) {
        int initSize = 5;
        AdjacentList adList = new AdjacentList(initSize);
        adList.put(1, 2, 1);
        adList.put(1, 4, 1);
        adList.put(2, 3, 1);
        adList.put(2, 5, 1);
        adList.put(4, 5, 1);

        adList.printGraph(1);
    }

    // vertex와 가중치가 담긴 Node 클래스
    private static class Node{
        private int vertex;
        private int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int getVertex() {
            return vertex;
        }

        public int getWeight() {
            return weight;
        }
    }

    // 인접리스트 구현
    private ArrayList<ArrayList<Node>> adList;
    private int size;

    public AdjacentList(int initSize){
        this.adList = new ArrayList<>();
        this.size = initSize;
        for(int i=0; i<initSize+1; i++){
            this.adList.add(new ArrayList<Node>());
        }
    }

    // 가중치 그래프를 기반으로 만들지만, 없다면 가중치 1을 주는 것도 방식
    // 양방향(무방향도 마찬가지) 그래프인 경우 활용가능한 방법
    public void put(int vertex_x, int vertex_y, int weight){
        this.adList.get(vertex_x).add(new Node(vertex_y, weight));
        this.adList.get(vertex_y).add(new Node(vertex_x, weight));
    }

    // 단방향 그래프인 경우 활용가능한 방법
    public void putSingleDirect(int vertex_start, int vertex_end, int weight){
        this.adList.get(vertex_start).add(new Node(vertex_end, weight));
    }

    // 전체 그래프 가져오기
    public ArrayList<ArrayList<Node>> getGraph(){
        return this.adList;
    }

    // 특정 Vertex의 list 정보 가져오기
    public ArrayList<Node> getVertex(int idx){
        return this.adList.get(idx);
    }

    // 특정 vertex X와 vertex Y의 관계 반환
    public int getWeight(int vertex_x, int vertex_y){
        return this.adList.get(vertex_x).get(vertex_y).getWeight();
    }

    // vertex가 0번 혹은 1번부터 시작할 수 있으니, startidx를 arg로 받는다
    public void printGraph(int startIdx){
        StringBuilder sb = new StringBuilder();
        for(int i=startIdx; i < this.size+1; i++){
            sb.append("정점 ").append(i).append("의 인접 정점 리스트");
            for( int j=0; j < this.adList.get(i).size(); j++){
                sb.append(" -(weight:").append(this.adList.get(i).get(j).getWeight()).append(")> ").append(this.adList.get(i).get(j).getVertex());
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
