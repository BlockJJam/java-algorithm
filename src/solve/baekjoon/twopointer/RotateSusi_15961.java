package solve.baekjoon.twopointer;

import java.io.*;
import java.util.StringTokenizer;

public class RotateSusi_15961 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, D, K, C;
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[] belt= new int[N];
        int[] kinds = new int[D+1];
        int maxKind = 0;
        int currKind = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            belt[i] = Integer.parseInt(st.nextToken());
            if(i < K){
                ++kinds[belt[i]];
                maxKind = kinds[belt[i]] == 1? maxKind+1: maxKind;
            }
        }

        // 현재 초밥 개수 동기화
        currKind = maxKind;
        if(kinds[C] == 0){
            // 처음 연속으로 먹은 초밥 종류에 쿠폰이 있는지 없는지로 maxKind 수정
            maxKind++;
        }

        int start = 0;
        for(int i=K; i<N+K; i++){
            currKind = --kinds[belt[start]] == 0? currKind -1: currKind;
            start++;
            currKind = ++kinds[belt[i%N]] == 1? currKind +1 : currKind;
            // 비교값을 옮기면서 쿠폰까지 고려한 maxKind 계산
            maxKind = Math.max(maxKind, kinds[C] == 0? currKind+1: currKind);
        }

        bw.write(maxKind+"\n");
        bw.flush();
        bw.close();
    }
}
