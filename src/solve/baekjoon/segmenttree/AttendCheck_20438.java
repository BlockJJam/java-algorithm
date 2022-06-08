package solve.baekjoon.segmenttree;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AttendCheck_20438 {

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int N = rd.nextInt();
        int K = rd.nextInt();
        int Q = rd.nextInt();
        int M = rd.nextInt();

        int[] sleptArr = new int[N+3];
        for(int i=0; i<K; i++){
            int slept = rd.nextInt();
            sleptArr[slept] = 1;
        }

        int[] attends = new int[N+3];
        for(int i=0; i<Q; i++){
            int attendNum = rd.nextInt();
            if(sleptArr[attendNum] == 1) continue;
            for(int j=attendNum; j<N+3; j +=attendNum){
                if(sleptArr[j] == 1) continue;
//                System.out.println(j);
                attends[j] = 1;
            }
        }


        AttendSegmentTree ast = new AttendSegmentTree(N, 3, N+2);

        ast.init(attends, 1, 3, N+2);
        for(int i=0; i<M; i++){
            int S = rd.nextInt();
            int E = rd.nextInt();
            long sum = ast.sum(S, E);
            System.out.println(sum);
        }


//        printGraph(attends);
    }

    static void printGraph(int[] graph){
        for(int target : graph){
            System.out.print(target + " ");
        }
        System.out.println();
    }

    static class AttendSegmentTree{
        long[] tree;
        int startIdx;
        int endIdx;
        int NODE_START = 1;

        AttendSegmentTree(int n, int start, int end){
            double height = Math.ceil(Math.log(n)/Math.log(2)) + 1;
            long nodeCnt = Math.round(Math.pow(2, height));
            tree = new long[Math.toIntExact(nodeCnt)];
            this.startIdx = start;
            this.endIdx = end;
        }

        long init(int[] arr, int node, int start, int end) {
            // leaf node
            if (start == end) {
                return tree[node] = arr[start] == 1? 0: 1;
            }else{
                return tree[node] = init(arr, node * 2, start, (start + end) / 2)
                        + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
            }
        }

        long sum(int node, int start, int end, int left, int right){ // left, right : 사용자 입력 법위
            if(end < left || right < start){
                return 0;
            }else if(left <= start  && end <= right){
                return tree[node];
            }else{
                return sum(node * 2, start, (start + end) / 2, left, right) + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            }
        }

        long sum(int left, int right){
            return sum(NODE_START, startIdx, endIdx, left, right);
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
