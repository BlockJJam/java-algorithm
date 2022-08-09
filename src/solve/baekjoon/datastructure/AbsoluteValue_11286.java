package solve.baekjoon.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class AbsoluteValue_11286 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)-> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);
            if(abs1 != abs2)
                return Integer.compare(abs1, abs2);
            return Integer.compare(o1, o2);
        });

        for(int i=0; i<N; i++){
            int target = rd.nextInt();
            if(target == 0){
                System.out.println(
                        pq.isEmpty()? 0: pq.poll()
                );
            }else{
                pq.offer(target);
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
