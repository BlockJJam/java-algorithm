package algorithm.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 예시로, BOJ_1197 풀이로 알아보자
 */
public class Prim {

    static int V, E;
    // priority-queue용
    static ArrayList<Node>[] adj;
    // array
    static int adjMatrix[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adj = new ArrayList[V + 1];
        adjMatrix = new int[V+1][V+1];

        for (int i = 1; i < V + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = c;
            adjMatrix[b][a] = c;
            adj[a].add(new Node(b,c));
            adj[b].add(new Node(a,c));
        }

        System.out.println(primPriorityQueue());
        System.out.println(primMatrix());
    }

    static class Node implements Comparable<Node> {
        int to, weight;

        public Node(int to, int weight) {
            super();
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static long primPriorityQueue() {
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        long res = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Node edge = pq.poll();
            if (!visited[edge.to]) {
                res += edge.weight;
                visited[edge.to] = true;
                // 모든 노드를 방문 했을 때, return
                if (++cnt == V) {
                    return res;
                }

                for (int i = 0; i < adj[edge.to].size(); i++) {
                    Node next = adj[edge.to].get(i);
                    if (!visited[next.to]) {
                        pq.add(next);
                    }
                }
            }
        }

        return res;
    }

    static long primMatrix(){
        // 선택되었는지 아닌지 판단하기위한 boolean 배열
        boolean visited[] = new boolean[V+1];
        // 현재 선택된 정점들로부터 도달할 수 있는 최소 거리 저장 배열
        int distances[] = new int[V+1];

        // 아직 선택되지 않은 거리를 모두 최댓값으로 설정
        Arrays.fill(distances, Integer.MAX_VALUE);

        int result = 0;
        distances[1] = 0; // 처음 선택한 정점의 거리는 0부터 시작
        int cnt = 0;

        while(true){
            int minWeight = Integer.MAX_VALUE;
            int idx = 1;

            for (int i = 1; i < V+1; i++) {
                // 선택되지 않았고, 거리를 저장한 key보다 작은 경우만 갱신 -> 똑같아도 의미 없으니 작을 때만
                if (!visited[i] && distances[i] < minWeight) {
                    idx = i;
                    minWeight = distances[i];
                }
            }
            // 선택된 정점은 체크하지 못하게 막고
            visited[idx] = true;

            // return할 결과에 골라낸 정점의 가중치 추가
            result += minWeight;
            cnt++;

            // cnt와 v가 같아지면 모든 정점을 거침
            if(cnt == V) break;

            // 새로 추가한 정점으로부터 연결된 다른 정점의 간선 정보를 업데이트 해준다
            for (int i = 1; i < V+1; i++) {
                if(!visited[i] && adjMatrix[idx][i] > 0 && distances[i] > adjMatrix[idx][i]) {
                    distances[i] = adjMatrix[idx][i];
                }
            }
        }

        return result;
    }
}
