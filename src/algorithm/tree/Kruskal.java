package algorithm.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 예시로, BOJ_1197 풀이로 알아보자
 */
public class Kruskal {
    // 정점의 부모를 채우기 위한 parent배열
    static int V, E, parents[];
    static Edge[] edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E];
        parents = new int[V+1];

        for( int i=0; i< E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(from, to, weight);
        }

        System.out.println(kruskal());
    }

    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight){
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    /**
     * 코드를 보면 알겠지만, Disjoint-Set을 활용하여, 가장 작은 가중치간선부터, MST를 찾는다
     * @return
     */
    private static int kruskal(){
        int res = 0, cnt = 0;

        // 간선 가중치 오름차순(Edge.compare())
        Arrays.sort(edgeList);
        // parent 정점 초기화
        make();

        // sorting된 edgelist를 이어본다
        for( Edge edge : edgeList){
            // 싸이클이 형성되지 않았는지 확인하고,
            if(union(edge.from, edge.to)){
                res += edge.weight;
                if(++cnt == V-1){
                    return res;
                }
            }
        }

        return res;
    }

    private static boolean union(int from, int to){
        int fromRoot = find(from);
        int toRoot = find(to);

        if(fromRoot == toRoot) {
            return false;
        }

        parents[toRoot] = fromRoot;
        return true;
    }

    private static int find(int v){
        if(v == parents[v]){
            return v;
        }
        return parents[v] = find(parents[v]);
    }

    private static void make(){
        for(int i=0; i < V+1; i++){
            parents[i] = i;
        }
    }
}
