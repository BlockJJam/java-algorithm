package solve.baekjoon.disjointset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FriendsCost_16562 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int M = rd.nextInt();
        int K = rd.nextInt();

        boolean[] visited = new boolean[N+1];
        int[] friendsCosts = new int[N+1];
        for(int i=0; i<N; i++){
            friendsCosts[i + 1] = rd.nextInt();
        }
        DS ds = new DS(N, friendsCosts);

        for(int i=0; i<M; i++){
            int a = rd.nextInt();
            int b = rd.nextInt();
            ds.union(a, b);
        }

        int result = 0;
        for(int i=1; i<N+1; i++){
            int relation = ds.find(i);

            if(!visited[relation]){
                result += friendsCosts[relation];
                K -= friendsCosts[relation];
                visited[relation] = true;
            }
        }

        if(K < 0){
            System.out.println("Oh no");
        }else{
            System.out.println(result);
        }
    }

    static class DS{
        int[] parent;
        int[] rank;
        int[] friendCosts;


        DS(int N, int[] friendCosts){
            this.parent = new int[N+1];
            this.rank = new int[N+1];
            this.friendCosts = Arrays.copyOf(friendCosts, friendCosts.length);

            makeSet(N);
        }

        int find(int e){
            if(e == parent[e]){
                return e;
            }
            return parent[e] = find(parent[e]);
        }

        void union(int e1, int e2){
            e1 = find(e1);
            e2 = find(e2);

            if(e1 == e2) return;

            if(friendCosts[e1] == friendCosts[e2]){

                if(rank[e1] > rank[e2])  parent[e2] = e1;
                else if(rank[e1] < rank[e2]) parent[e1] = e2;
                else{
                    parent[e2] = e1;
                    rank[e1]++;
                }

            }else if(friendCosts[e1] < friendCosts[e2]){
                parent[e2] = e1;
            }else{
                parent[e1] = e2;
            }
        }

        int getParent(int e){
            return this.parent[e];
        }

        void makeSet(int N){
            for(int i=0; i<N+1; i++){
                parent[i] = i;
                rank[i] = 1;
            }
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
