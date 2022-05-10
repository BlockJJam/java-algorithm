package solve.baekjoon.graph;


import java.io.*;
import java.util.*;

public class EffectiveHacking_1325 {

    static int N, M ,ans;
    static ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[10001];
    static boolean[] visited = new boolean[10001];
    static int[] answer = new int[10001];


    public static void main(String[] args) throws Exception {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=1; i<=N; i++) adj[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj[x].add(y);
        }

        ans = 0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++) visited[j] = false;
            dfs(i);
        }

        for(int i=1; i<=N; i++){
            ans = Math.max(ans, answer[i]);
        }

        for(int i=1; i<=N; i++){
            if(ans == answer[i]){
                System.out.print(i +" ");
            }
        }

    }

    public static void dfs(int num){
        visited[num] = true;

        for(int next: adj[num]){
            if(!visited[next]){
                answer[next]++;
                dfs(next);
            }
        }
    }

}
