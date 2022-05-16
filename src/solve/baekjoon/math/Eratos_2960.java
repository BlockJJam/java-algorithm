package solve.baekjoon.math;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Eratos_2960 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true);
        int delcnt = 0;
        int result = 0;
        for(int i=2; i<=N; i++){
            if(isPrime[i]){
                ++delcnt;
                if(delcnt == K) {
                    bw.write(i + "\n");
                    bw.flush();
                    bw.close();
                    return;
                }
                for(int j = i*i; j<=N; j+=i){
                    if(!isPrime[j])
                        continue;

                    isPrime[j] = false;
                    ++delcnt;
                    if(delcnt == K){
                        bw.write(j + "\n");
                        bw.flush();
                        bw.close();
                        return;
                    }
                }
            }
        }
    }
}
