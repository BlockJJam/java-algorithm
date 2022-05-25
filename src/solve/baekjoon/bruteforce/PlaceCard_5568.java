package solve.baekjoon.bruteforce;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class PlaceCard_5568 {
    static int N;
    static int K;
    static int[] cards;
    static int[] output;
    static boolean[] visited;
    static Set<String> results;
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        cards = new int[N];
        visited = new boolean[N];
        output = new int[K];
        results = new HashSet<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            cards[i] = Integer.parseInt(st.nextToken());
        }

        makeNumber(0,K);

        bw.write(results.size() + "\n");
        bw.flush();
        bw.close();

    }


    static void makeNumber(int depth, int r){

        if(depth == r){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<r; i++){
                sb.append(output[i]);
            }
            String result = sb.toString();
            results.add(result);
            return;
        }

        for(int i=0; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                output[depth] = cards[i];
                makeNumber(depth+1, r);
                visited[i] = false;
            }
        }
    }
}
