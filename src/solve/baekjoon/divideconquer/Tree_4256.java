package solve.baekjoon.divideconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tree_4256 {
    static int N;
    static int[] pre;
    static int[] in;
    static boolean[] visited;
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int T = rd.nextInt();

        while(T-- != 0){
            N = rd.nextInt();
            pre = new int[N];
            in = new int[N];
            visited = new boolean[N];

            for(int i=0; i<N; i++){
                pre[i] = rd.nextInt();
            }

            for(int i=0; i<N; i++){
                in[i] = rd.nextInt();
            }

            startFindPost();
            System.out.println();
        }
    }


    static int pi;
    static void startFindPost(){
        pi = 0;
        int ii = 0;
        int data = pre[pi];
        for(int i=0; i<N; i++){
            if(pre[pi] == in[i]){
                ii = i;
                break;
            }
        }
        if(N != 1){
            if( ii-1 >= 0) {
                ++pi;
                findPost(0, ii - 1);
            }
            if( ii+1 <= N -1) {
                pi++;
                findPost(ii + 1, N - 1);
            }
        }
        System.out.print(data + " ");
    }

    static void findPost(int startIi, int endIi){
//        if(visited[pi]) return;

//        System.out.println("pi = " + pi + " pre: "+ pre[pi] + " start: "+ startIi + " endIi: "+ endIi);

        int ii=0;
        int data = pre[pi];


        for(int i= startIi; i<=endIi; i++){
            if(pre[pi] == in[i]){
//                System.out.println("i = " + i);
                ii = i;
                break;
            }
        }

        if(ii -1 >= startIi){
            ++pi;
            findPost(startIi, ii-1);
        }
        if(ii + 1 <= endIi){
            ++pi;
            findPost(ii + 1, endIi);
        }
        System.out.print(data+ " ");
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
