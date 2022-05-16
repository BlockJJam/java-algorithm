package solve.baekjoon.math;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class GCDLCM_2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        bw.write(gcd(N,M)+"\n");
        bw.write(lcm(N, M) + "\n");
        bw.flush();
        bw.close();

    }

    public static int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b){
        return (a * b) / gcd(a,b);
    }
}
