package solve.baekjoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class StoneGame_9655 {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[Math.max(N, 4)];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 0;
        dp[3] = 1;

        for(int i= 4; i<N; i++){
            if(dp[i-1] != 0 || dp[i-3] != 0){
                dp[i]= 0;
            }else{
                dp[i]= 1;
            }
        }

        bw.write(dp[N-1] == 0? "SK": "CY");


        bw.flush();
        bw.close();

    }
}
