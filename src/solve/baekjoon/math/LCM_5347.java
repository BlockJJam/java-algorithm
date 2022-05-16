package solve.baekjoon.math;

import java.io.*;
import java.util.StringTokenizer;

public class LCM_5347 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while(--T >= 0){
            st = new StringTokenizer(br.readLine());

            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            bw.write(lcm(a,b)+"\n");
        }

        bw.flush();
        bw.close();
    }

    public static long gcd(long a, long b){
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    public static long lcm(long a, long b){
        return (a * b) / gcd(a,b);
    }
}
