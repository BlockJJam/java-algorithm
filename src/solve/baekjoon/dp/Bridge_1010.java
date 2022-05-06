package solve.baekjoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Bridge_1010 {
    static int T;
    static int N;
    static int M;
    static long[][] dp;
    static long result;
    static Bridge bridge;
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        bridge = new Bridge();

        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dp = new long[N][M];

            result = bridge.selectRight(0,0);

            bw.write(result+ "\n");
            bw.flush();

        }
        bw.close();
    }

    static class Bridge{
        public long selectRight(int nStart, int mSelected){
            if(nStart == N-1){
                return M - mSelected;
            }

            long ret = 0;
            for(int i=mSelected; i<M-N+nStart+1; i++){
                if(dp[nStart][i] == 0){
                    dp[nStart][i]= selectRight(nStart +1, i+1);
                }
                ret += dp[nStart][i];
            }
            return ret;
        }
    }
}
