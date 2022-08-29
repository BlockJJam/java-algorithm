package solve.baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 도시를 건설하려는데, 쌍이 겹치는 도로가 꽤 있어서, 양정점당 하나의 도로 즉 mst를 구해라
// M 범위가 5*10^5 이거나 N* (N+1) / 2 중에 작은 것의 이하로, 간선이 많을 것으로 판단되어 Prim을 사용
public class CityBuild_21924 {
    static int V;
    static int E;
    static long totalWeight; // 입력받은 모든 weight
    static ArrayList<Node>[] adj; // 해당 정점과 이어져 있는 정점간의 거리 배열
    static boolean[] visited; // 방문 여부

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        // 일단 양방향으로 필요한 값들 모두 입력받기
        V = rd.nextInt();
        E = rd.nextInt();
        totalWeight = 0;
        adj = new ArrayList[V+1];
        for(int i=1; i<V+1; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++){
            int a = rd.nextInt();
            int b = rd.nextInt();
            int c = rd.nextInt();

            adj[a].add(new Node(b,c));
            adj[b].add(new Node(a, c));
            totalWeight += c;
        }

        // prim 알고리즘을 통해 해당 adj배열에서 mst 값을 찾는다.
        long mstWeight = primPriorityQueue();
        for(int i=1; i<V+1; i++){
            // 한 도시라도 방문한적이 없는 도시가 있다면 -1 출력
            if(!visited[i]){
                System.out.println(-1);
                return;
            }
        }

        System.out.println(totalWeight-mstWeight);
    }

    public static long primPriorityQueue(){
        visited = new boolean[V+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작 정점은 아무것이나 상관없지만, 일단 도시 1번으로 시작
        pq.add(new Node(1, 0));

        long res = 0;
        int cnt = 0;

        while(!pq.isEmpty()){
            Node edge = pq.poll();
            // 방문한 적이 없는 도시가 상대편이면
            if(!visited[edge.to]){
                // 해당 간선의 weight을 더하고 방문처리
                res += edge.weight;
//                System.out.println("res = " + res);
                visited[edge.to] = true;

                // 모든 정점 방문을 완료했다면 mst값을 반환
                if(++cnt == V){
                    return res;
                }

                // 꺼낸 node의 상대편 정점의 인접배열 중에 방문한 적 없는 node를 pq에 넣는다
                // ( 탐색 범위가 넓어지면, 같은 정점이지만 weight이 작아져서 들어간 경우가 있다)
                // ( 그렇다면, pq의 앞쪽에 위치하기 때문에 꺼내는 순서가 바뀔 수 있다 )
                for(int i=0; i<adj[edge.to].size(); i++){
                    Node next = adj[edge.to].get(i);
                    if(!visited[next.to]){
                        pq.add(next);
                    }
                }
            }
        }

        return res;
    }

    static class Node implements Comparable<Node>{
        int to;
        int weight;

        Node(int to, int weight){
            super();
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
