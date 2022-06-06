package solve.baekjoon.divideconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ThueMorseString_18222 {

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        long K = rd.nextLong();

        ThueMorse tm = new ThueMorse();
        tm.findCharToK(K,0);


        Character a = '0';
        while(tm.result-- > 0){
            a = a == '0'? '1':'0';
        }
        System.out.println(a);

    }

    static class ThueMorse{
        int result;
        public ThueMorse(){
            this.result = 0;
        }

        void findCharToK(long K, int cnt){
            if(K == 1){
                result = cnt;
                return;
            }

            // 맨처음 0까지를 찾는다
            long loop = 0;
            long KPow =0;
            long prevPow = 0;
            while(loop >=0){
                KPow = (long)Math.pow(2,loop);
                if(KPow >= K)
                    break;
                prevPow = KPow;
                loop++;
            }
            findCharToK(K - prevPow, cnt+1);

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
