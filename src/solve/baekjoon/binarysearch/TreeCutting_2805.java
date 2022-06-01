package solve.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeCutting_2805 {
    static int N;
    static int M;
    static long totalHeight;
    static int result;

    public static void main(String[] args) throws Exception{
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> trees = new ArrayList<>();
        int maxHeight = 0;
        for(int i=0; i<N; i++){
            int height = Integer.parseInt(st.nextToken());
            trees.add(height);
            maxHeight = Math.max(height, maxHeight);
        }

        binarySearch(0, maxHeight, trees);

        bw.write(result+"\n");
        bw.flush();
        bw.close();
    }

    static void binarySearch(int low, int high, List<Integer> trees){
        if(low > high) {
            result = high;
            return;
        }

        // 자를 높이
        int mid = (low + high) /2;
        totalHeight = 0L;

        int cuttedHeight = 0;
        for(int tree: trees){
            cuttedHeight = tree - mid;
            if(cuttedHeight > 0){
                totalHeight += cuttedHeight;
            }
        }

        if(totalHeight == M){
            result = mid;
            return;
        }

        if(totalHeight <M){
            binarySearch(low, mid - 1, trees);
        }else{
            binarySearch(mid + 1, high, trees);
        }
    }
}
