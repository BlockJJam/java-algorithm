package solve.baekjoon.disjointset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GoTrip_1969 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int M = rd.nextInt();
        int[][] graph = new int[N+1][N+1];
        DS ds = new DS(N);

        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                if(rd.nextInt() ==1){
                    ds.union(i, j);
                }
            }
        }

        int rootNum = -1;
        for(int i=0 ; i<M; i++){
            int nextRoot = ds.find(rd.nextInt());
            if (rootNum == -1){
                rootNum = nextRoot;
            }else{
                if(nextRoot != rootNum){
                    System.out.println("NO");
                    return;
                }
                rootNum = nextRoot;
            }
        }
        System.out.println("YES");
    }

    static class DS{
        int[] parent;
        int[] rank;

        DS(int N){
            this.parent = new int[N+1];
            this.rank = new int[N+1];

            for(int i=0; i<N+1; i++){
                parent[i]= i;
            }
            Arrays.fill(rank, 1);
        }

        int find(int e){
            if(e == parent[e])
                return e;
            return parent[e] = find(parent[e]);
        }

        void union(int e1, int e2){
            int e1Root = find(e1);
            int e2Root = find(e2);
            if(e1Root == e2Root) {
                return;
            }else if(e1Root > e2Root){
                int temp = e1Root;
                e1Root = e2Root;
                e2Root = temp;
            }

            if(rank[e1Root] >= rank[e2Root]){
                parent[e2Root] = e1Root;
            }else{
                parent[e1Root] = e2Root;
            }

            if(rank[e1Root] == rank[e2Root]){
                rank[e1Root]++;
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
