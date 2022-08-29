package solve.baekjoon.disjointset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 처음엔, 그냥 입력받아서 방문처리 해버리고 방문처리된 녀석과 같이 파티에 있는 파티는 거짓말 못한다!로 끝내려 했으나
// 문제는 거짓말 못하는 파티에 있던 애가 다른 파티에 껴있으면 문제가 된다.
// 그래서 disjoinSet(DS)를 사용해서, 거짓말 못하는 모임에 같이 있던 파티원이 다른 파티에 있으면 안되도록 하였다.
public class Lie_1043 {
    static int N, M;
    static int T;
    static int P;

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        M = rd.nextInt();
        T = rd.nextInt();
        DS ds = new DS(N);
        for(int i=0; i<T; i++){
            int t = rd.nextInt();
            // ds에 진실을 아는 boolean배열이 있다. (방문처리방식)
            ds.setTrust(t);
        }

        ArrayList<Integer>[] parties = new ArrayList[M];
        for(int i=0; i<M; i++){
            parties[i] = new ArrayList<>();
        }

        // 일단, 파티원
        for(int i=0; i<M; i++){
            P = rd.nextInt();
            // 각 파티원끼리 묶음으로 동일한 부모 설정을 위해 이전 팀원 번호 알아야 한다.
            int prev = 0;
            for(int j=0; j<P; j++){
                int target = rd.nextInt();
                parties[i].add(target);

                if(j == 0)
                    prev = target;
                if(j > 0) {
                    // 2번째 파티원부터 prev 파티원과 union한다.
                    ds.union(prev, target);
                    prev = target;
                }
            }
        }

        int cnt = 0;
        boolean[] trust = ds.getTrust();
        for(ArrayList<Integer> party : parties){
            boolean isAbleToLie = true;
            for(int person: party){
                // 해당 파티의 파티원 중에 부모가 진실을 아는 자라면 해당 파티는 거짓말 못한다.
                if(trust[ds.find(person)]){
                    isAbleToLie =false;
                    break;
                }
            }
            cnt = isAbleToLie? cnt+1: cnt;
        }

        System.out.println(cnt);
    }

    static class DS{
        private int[] parent;
        private int[] rank;
        // 기존 disjoint-set에 추가된 배열로, 진실을 아는자들을 방문처리한다.
        private boolean[] trust;

        DS(int n){
            parent = new int[n+1];
            trust = new boolean[n+1];
            rank = new int[n+1];

            initArr();
        }

        private void initArr() {
            for (int i = 1; i < N+1; i++) {
                parent[i] = i;
            }
            Arrays.fill(rank, 1);
        }

        public int find(int n){
            if(this.parent[n] == n){
                return n;
            }

            return this.parent[n] = find(this.parent[n]);
        }

        public void union(int n, int v){
//            System.out.println("find전 n = " + n +", find전 v = "+v);
            n = find(n);
            v = find(v);
//            System.out.println("n = " + n);
//            System.out.println("v = " + v);
//            System.out.println();

            // 만약 둘다 진실을 아는자라면, 그냥 나가도 된다.
            if(n == v || (trust[n] && trust[v])){
                return;
            }
            // 만약 n만 진실을 아는자라면 부모는 n이된다. 반대도 마찬가지 v가 부모가 된다.
            if(trust[n]){
                parent[v] = n;
                return;
            }else if(trust[v]){
                parent[n] = v;
                return;
            }

            if(rank[n] == rank[v]){
                this.parent[v] = n;
                rank[n]++;
            }else if(rank[n] > rank[v]){
                this.parent[v] = n;
            }else{
                this.parent[n] = v;
            }
        }

        public void setTrust(int a){
            this.trust[a] = true;
        }

        public boolean[] getTrust(){
            return this.trust;
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
