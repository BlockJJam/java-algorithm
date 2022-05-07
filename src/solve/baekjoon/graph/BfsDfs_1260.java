package solve.baekjoon.graph;

import java.io.*;
import java.util.*;

public class BfsDfs_1260 {

    public static void main(String[] args) throws IOException {
        int N, M, V;

        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(N+1);
        int x;
        int y;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            graph.setXY(x, y);
        }

        graph.bfs(V);
        graph.cleanGraph();
        graph.dfs(V);

        for(int e: graph.getDfs()){
            System.out.print(e + " ");
        }
        System.out.println();

        for(int e: graph.getBfs()){
            System.out.print(e+" ");
        }
        System.out.println();
    }

    static class Graph{
        private Queue<Integer> q = new LinkedList<>();
        private short[][] graph;
        private boolean[] visited;
        private List<Integer> resultBfs = new ArrayList<>();
        private List<Integer> resultDfs = new ArrayList<>();


        public Graph(int n){
            graph = new short[n][n];
            visited = new boolean[n];
        }

        public void bfs(int v){
            visited[v] = true;
            q.offer(v);

            int value;
            while(!q.isEmpty()){
                value= q.poll();
                resultBfs.add(value);

                for(int i=1; i<graph[value].length; i++){
                    if(!visited[i] && graph[value][i] == 1){
                        visited[i] = true;
                        q.offer(i);
                    }
                }
            }
        }

        public void dfs(int v){
            resultDfs.add(v);
            visited[v] = true;

            for(int i=1; i<graph[v].length; i++){
                if(!visited[i] && graph[v][i] == 1){
                    dfs(i);
                }
            }
        }

        public void setXY(int x, int y){
            graph[x][y] = 1;
            graph[y][x] = 1;
        }

        public List<Integer> getBfs(){
            return resultBfs;
        }

        public List<Integer> getDfs(){
            return resultDfs;
        }

        public void cleanGraph(){
            Arrays.fill(visited, false);
        }
    }
}
