package solve.baekjoon.graph;

import java.io.*;
import java.util.*;

public class EffectiveHacking_1325 {

    static int N;
    static int M;
    static PriorityQueue<Pair> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();

        Graph graph = new Graph(N);

        int s, d;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken()) - 1; // vertex N을 0번부터 넣었기 때문
            s = Integer.parseInt(st.nextToken()) - 1;

            graph.addEdge(graph.getVertex(s), graph.getVertex(d));
        }

        for(int i=0; i<N; i++){
            Vertex target = graph.getVertex(i);
            graph.bfs(target);
            graph.cleanVisited();

        }

        int maxHacked = -1;
        while(!pq.isEmpty()){
            Pair candidate = pq.poll();
            if(maxHacked == -1)
                maxHacked = candidate.hackCnt;

            if(candidate.hackCnt != maxHacked)
                break;

            bw.write((candidate.v+1) +" ");
        }

        bw.flush();
        bw.close();
    }

    static class Vertex{
        int data;
        boolean visited;
        boolean noSearch;
        List<Vertex> adList = new LinkedList<>();

        public Vertex(int data){
            this.data = data;
            this.visited = false;
            this.noSearch = false;
        }
    }

    static class Graph{
        private List<Vertex> vertexList = new LinkedList<>();
        public Graph(int v){
            for(int i=0; i<v; i++){
                vertexList.add(new Vertex(i));
            }
        }

        public Vertex getVertex(int v){
            return vertexList.get(v);
        }

        public void addEdge(Vertex s, Vertex d){
            s.adList.add(d);
        }

        public void bfs(Vertex v){
            Queue<Vertex> queue = new LinkedList<>();
            Queue<Vertex> sameHackPowerVertexQueue = new LinkedList<>();
            v.visited = true;
            v.noSearch =true;
            queue.offer(v);
            sameHackPowerVertexQueue.offer(v);

            int result = 0;
            while(!queue.isEmpty()){
                Vertex target = queue.poll();
                if(target.noSearch){
                    sameHackPowerVertexQueue.add(target);
                }
                for(Vertex adjV : target.adList){
                    if( !adjV.visited){
                        adjV.visited = true;
                        adjV.noSearch = true;
                        queue.offer(adjV);
                        result++;
                    }
                }
            }
            while(!sameHackPowerVertexQueue.isEmpty()){
                Vertex sameHackVertex = sameHackPowerVertexQueue.poll();
                pq.add(new Pair(result, sameHackVertex.data));
            }
            pq.add(new Pair(result, v.data));
        }

        public void cleanVisited(){
            for(Vertex v : vertexList){
                v.visited = false;
            }
        }
    }

    static class Pair implements Comparable<Pair>{
        int hackCnt;
        int v;

        public Pair(int hackCnt, int v){
            this.hackCnt = hackCnt;
            this.v = v;
        }

        @Override
        public int compareTo(Pair pair) {
            return pair.hackCnt != hackCnt ? pair.hackCnt - hackCnt : v - pair.v;
        }
    }
}
