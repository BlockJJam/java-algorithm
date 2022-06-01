package solve.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LanCutting_1654 {
    static int K;
    static int N;
    static long result;

    public static void main(String[] args) throws Exception{
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        List<Long> lans = new ArrayList<>();
        long maxLength = 0;
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            long lan = Long.parseLong(st.nextToken());
            lans.add(lan);
            maxLength = Math.max(maxLength, lan);
        }

        binarySearch(1L, maxLength, lans);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    // 만들 수 있는 개수를 N개에 맞추되 길이가 최대인 것으로
    public static void binarySearch(long low, long high, List<Long> lans){
        if(low > high){
            result = high;
            return;
        }

        long mid = (low + high) / 2;
        int cuttedCnt = 0;
        for(long lan : lans){
            cuttedCnt += lan/mid;
        }

        if(cuttedCnt < N){
            binarySearch(low, mid-1, lans);
        }else{
            binarySearch(mid+1, high, lans);
        }
    }
}
