package solve.baekjoon.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FourSquares_17626 {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N+1];
        Arrays.fill(dp, 5);
        FourSquares fourSquares = new FourSquares();
        fourSquares.findPowSumCombi(0,0);

        bw.write(dp[N] + "\n");

        bw.flush();
        bw.close();
    }

    static class FourSquares{
        public void findPowSumCombi(int prevSum, int idx){
            if(idx+1 >4){
                return;
            }
            int addValue = 0;
            for(int i=(int)Math.sqrt(N); i >= 1; i--){
                addValue = (int) Math.pow(i,2);

                if(prevSum + addValue > N || dp[prevSum + addValue] <= idx +1 ) {
                    continue;
                } else{
                    dp[prevSum + addValue] = idx +1;
                    findPowSumCombi(prevSum + addValue, idx+1);
                }
            }
        }
    }
}
