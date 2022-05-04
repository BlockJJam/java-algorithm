package solve.baekjoon.greedy;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Rope_2217 {
    static int N;
    static long result;
    static PriorityQueue<Long> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        result = 0L;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            pq.add(Long.parseLong(st.nextToken()));
        }

        int k =0;
        long w = 0;
        while(!pq.isEmpty()){
            k = pq.size();
            w = pq.poll();
            result = Math.max(k*w, result);
        }
        bw.write(result +"\n");
        bw.flush();
        bw.close();
    }
}
