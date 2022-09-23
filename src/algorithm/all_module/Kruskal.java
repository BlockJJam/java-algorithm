package algorithm.all_module;

import java.util.Arrays;

/**
 * 최소 스패닝 트리 활용법 중 Prim과 함께 최소비용의 부분 집합으로 구성되는 트리
 * 간선기반 탐용적인 방식으로, 간선이 적을 때 사용하면 좋음
 * 최소 스패닝 트리 문제 유형
 * - 정점(제국, 도시, 네트워크)끼리 연결 하되 최소 비용으로 연결하는 경우
 * - 그래프에서 해당 위치까지의 최소 이동 거리
 */
public class Kruskal {
    public static void main(String[] args) {

    }

    static int V, E, parents[];
    static Edge[] edgeList;

    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    private static int kruskal(){
        int ret = 0, cnt = 0;

        Arrays.sort(edgeList);
        makeParent();

        for(Edge edge : edgeList){
            if(union(edge.from, edge.to)){
                ret += edge.weight;
                if(++cnt == V -1){
                    return ret;
                }
            }
        }

        return ret;
    }

    private static boolean union(int from, int to){
        int fromRoot = find(from);
        int toRoot = find(to);

        if(fromRoot == toRoot){
            return false;
        }

        parents[toRoot] = fromRoot;
        return true;
    }

    private static int find(int v){
        if(v == parents[v])
            return v;

        return parents[v] = find(parents[v]);
    }

    private static void makeParent(){
        for(int i=0; i< V+1; i++){
            parents[i] = 1;
        }
    }
}
