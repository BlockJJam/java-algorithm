package solve.baekjoon.topological;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ACMCraft_1005 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int T = rd.nextInt();
        while(T-- != 0){
            int N = rd.nextInt();
            int K = rd.nextInt();
            Graph graph = new Graph(N);
            for(int i=0; i<N; i++){
                int d = rd.nextInt();
                graph.setTimes(i, d);
            }

            for(int i=0; i<K; i++){
                int a = rd.nextInt()-1;
                int b = rd.nextInt()-1;

                graph.addEdge(a, b);
                graph.getDs().union(a, b);
            }

            int w = rd.nextInt()-1;
            int result = 0;
            boolean[] visited = new boolean[N];

            // 그래프가 모두 연결되어 있지 않을 수 있기 때문에, DisjointSet을 사용해서, root가 서로 다른 경우에 위상정렬 탐색을 진행했다
            for(int i=0; i < N; i++){
                int root = graph.getDs().find(i); // 각 Vertex의 root vertex가 중복되지 않도록 root를 방문 처리했다
                if(!visited[root]){
                    visited[root] = true;
                    result = graph.topologicalSort(root, w); // W를 찾지 찾을 때까지 위상정렬한다
                    if(result != -1)
                        break;
                }
                graph.reset();
            }
            System.out.println(result);
        }
    }

    static class Graph{
        private int V, E;
        private int[] indegree;
        private int[] times;
        private ArrayList<ArrayList<Integer>> adj;
        private int sumTime;
        private DisjointSet ds;

        Graph(int V){
            this.V = V;
            this.E = 0;
            this.indegree = new int[V];
            this.times = new int[V];
            this.adj = new ArrayList<>();
            this.ds = new DisjointSet(V);
            sumTime = 0;

            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void setTimes(int idx, int time){
            this.times[idx] = time;
        }

        void addEdge(int src, int dest){
            this.adj.get(src).add(dest);
            this.indegree[dest]++;
        }

        int topologicalSort(int root, int w){
            // Build(Custom으로 만든 클래스) 객체는 Vertex와 Vertex까지의 건설시간(total)을 갖는다
            Queue<Build> q = new LinkedList<>();
            int[] maxTimes = new int[V];

            for(int i=0; i<V; i++){
                if(indegree[i] == 0 && ds.find(i) == root){
                    q.offer(new Build(i, times[i]));
                }
            }

            int minTime = 0;
            boolean flag = false; // 사실 빨리 풀고 싶어서 flag를 하나 두었다.
            int qSize;
            while(!q.isEmpty()){
                Build build = q.poll();
                int targetV = build.v;
                int buildTotal = build.total;

                // 원하는 목표 건물(w)를 찾았으면, 건설하는데 최소시간을 구한다
                if(targetV == w){
                    flag = true;
                    minTime = Math.max(buildTotal, minTime);
                }

                for(int adjV : this.adj.get(targetV)){
                    // maxTimes는 인접 건물이 지어지기 위해서 이전에 완공되어야 할 건물들이 모두 완공되는 시점을 담는다.
                    maxTimes[adjV] = Math.max(maxTimes[adjV], buildTotal + times[adjV]);
                    // 모두 완공되었을 때, queue에 다음 건설을 진행할 건물을 넣는다
                    if(--indegree[adjV] == 0) q.offer(new Build(adjV, maxTimes[adjV]));
                }
            }

            // 빨리 풀기 위한 조잡한 flag ㅋㅋㅋ
            if(!flag){
                return -1;
            }else{
                return minTime;
            }
        }

        static class Build{
            int v;
            int total;

            Build(int v, int total){
                this.v = v;
                this.total = total;
            }
        }

        DisjointSet getDs(){
            return ds;
        }

        void reset(){
            this.sumTime = 0;
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

    static class DisjointSet {
        int parent[];
        int rank[];

        public DisjointSet(int n){
            this.parent= new int[n+1];
            this.rank = new int[n+1];

            for(int i=1; i < n+1; i++){
                this.parent[i] = i;
            }
            Arrays.fill(rank, 1);
        }

        public int find(int e){
            if(this.parent[e] == e) return e;
            return this.parent[e] = find(this.parent[e]);
        }

        public void union(int e1, int e2){
            e1 = find(e1);
            e2 = find(e2);

            if(e1 == e2) return;

            if(e1 < e2) {
                this.parent[e2] = e1;
            }else{
                this.parent[e1] = e2;
            }
        }

        public void unionByRank(int e1, int e2){
            e1 = find(e1);
            e2 = find(e2);

            if(e1 == e2) return;

            if(rank[e1] > rank[e2]){
                this.parent[e2] = e1;
            }else {
                this.parent[e1] = e2;
            }

            if(rank[e1] == rank[e2]) ++rank[e2];
        }
    }
}
