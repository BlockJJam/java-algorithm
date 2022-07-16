package solve.baekjoon.disjointset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakeRobot_18116 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int N= rd.nextInt();
        DS ds = new DS();
        for(int i=0; i<N;i++){
            char cmd = rd.next().charAt(0);
            if(cmd == 'I'){
                int a = rd.nextInt();
                int b = rd.nextInt();
                ds.union(a, b);
            }else if (cmd == 'Q'){
                int partCnt = ds.find(rd.nextInt()).cnt;
                System.out.println(partCnt);
            }
        }
    }

    static class Item{
        int id;
        int cnt;

        Item(int id, int cnt){
            this.id = id;
            this.cnt = cnt;
        }
    }
    static class DS{
        private Item[] parent;
        private int[] rank;
        private int PART_MAX= 1_000_000;

        DS(){
            this.parent = new Item[PART_MAX+1];
            this.rank = new int[PART_MAX+1];

            makeSet(PART_MAX);
        }

        void makeSet(int N){
            for(int i=1; i<N+1; i++){
                this.parent[i] = new Item(i,1);
                this.rank[i] = 1;
            }
        }

        Item find(int e){
            if(e == parent[e].id){
                return parent[e];
            }
            return parent[e] = find(parent[e].id);
        }

        void union(int e1, int e2){
            Item e1Root = find(e1);
            Item e2Root = find(e2);

            if( e1Root.id == e2Root.id) return;

            if(rank[e1Root.id] > rank[e2Root.id]){
                parent[e2Root.id] = e1Root;
                e1Root.cnt += e2Root.cnt;
            }else if(rank[e1Root.id] < rank[e2Root.id]){
                parent[e1Root.id] = e2Root;
                e2Root.cnt += e1Root.cnt;
            }else{
                parent[e2Root.id] = e1Root;
                e1Root.cnt += e2Root.cnt;
                rank[e1Root.id] ++;
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
