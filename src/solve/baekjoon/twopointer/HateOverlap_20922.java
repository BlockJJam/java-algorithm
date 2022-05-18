package solve.baekjoon.twopointer;

import java.io.*;
import java.util.StringTokenizer;

public class HateOverlap_20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] sameArr = new int[100001];
        int[] numArr = new int[N];

        int maxCnt=0;
        int start =0;
        st = new StringTokenizer(br.readLine());
        int loopCnt =0;
        for(int i=0 ; i<N; i++){
            int num = Integer.parseInt(st.nextToken());
            numArr[i] = num;
            ++sameArr[num];
            ++loopCnt;
            if(sameArr[num] > K){
                maxCnt = Math.max(loopCnt-1, maxCnt);
                int j=start;
                while(sameArr[num] > K){
                    --sameArr[numArr[j]];
                    --loopCnt;
                    ++j;
                }
                start = j;
            }
        }
        maxCnt = Math.max(loopCnt, maxCnt);

        bw.write(maxCnt +"\n");
        bw.flush();
        bw.close();
    }
}
