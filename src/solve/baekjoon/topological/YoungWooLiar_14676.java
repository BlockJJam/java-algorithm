package solve.baekjoon.topological;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class YoungWooLiar_14676 {
    static int N, M, K;

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        M = rd.nextInt();
        K = rd.nextInt();

        SpaceWar spaceWar = new SpaceWar(N);
        // 인접 리스트에 src-dest를 추가하고, dest에 들어오는 간선 개수를 추가하자(indegree:진입차수)
        for(int i=0; i<M; i++){
            int src = rd.nextInt() - 1;
            int dest = rd.nextInt() - 1;

            spaceWar.addEdge(src, dest);
        }

        for(int i=0; i<K; i++){
            int cmd = rd.nextInt();
            boolean cmdResult = true;
            // cmd가 1이면, 건물을 짓는다. 만약 결과가 false면 치트키를 사용한 것
            if(cmd == 1){
                int target = rd.nextInt()-1;
                cmdResult = spaceWar.built(target);
            }else{ // cmd가 2면, 건물을 부순다. 만약 결과가 false면 치트키를 사용했다.
                int target = rd.nextInt()-1;
                cmdResult = spaceWar.destroy(target);
            }
            if(!cmdResult){
                System.out.println("Lier!");
                return;
            }
        }
        System.out.println("King-God-Emperor");
    }

    static class SpaceWar{
        int V, E;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int[] indegree;
        int[] buildings;

        SpaceWar(int V){
            this.V = V;
            this.indegree = new int[V];
            this.buildings = new int[V];
            for(int i=0; i<V; i++){
                this.adj.add(new ArrayList<>());
            }
        }

        // 처음 M개 입력받을 때만 해당
        public void addEdge(int src, int dest){
            this.adj.get(src).add(dest);
            this.indegree[dest]++;
        }

        public boolean built(int idx){
            // 건물을 지을 때, 선수관계를 통과 했다면, 건물을 지을 수 있다.
            if(indegree[idx] == 0){
                this.buildings[idx]++;

                // 처음 지어지는 것이면, 해당 idx 건물과 관계에 있는 건물에 대해, indegree를 낮추자
                if (this.buildings[idx] == 1) {
                    for(int dest : adj.get(idx)){
                        indegree[dest]--;
                    }
                }
                return true;
            }else{
                // 건물을 지을 때, 선수관계를 통과하지 못하면, 치트키를 사용했다는 증거
                return false;
            }
        }

        public boolean destroy(int idx){
            this.buildings[idx]--;
            // 건물을 파괴했더니, 해당 건물 개수가 0이 되었다면 선수관계를 원복한다.
            if(this.buildings[idx] == 0){
                for(int dest: adj.get(idx)){
                    indegree[dest]++;
                }
            }else if(this.buildings[idx] < 0){
                // 지어진 건물도 없는데 파괴하려 하면, 치트키를 사용한 것
                return false;
            }
            return true;
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
