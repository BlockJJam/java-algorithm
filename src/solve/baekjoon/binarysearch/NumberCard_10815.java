package solve.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberCard_10815 {
    static int N;
    static int M;
    static int target;
    static int[] cards;
    public static void main(String[] args) throws Exception {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        cards = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            target = Integer.parseInt(st.nextToken());
            if(binarySearch(0, N-1)){
                bw.write("1 ");
            }else{
                bw.write("0 ");
            }
        }

        bw.flush();
        bw.close();
    }

    static boolean binarySearch(int left, int right){
        if(left > right)
            return false;

        int mid = (left + right) / 2;

        if(cards[mid] == target) return true;

        if(target < cards[mid] ){
            return binarySearch(left, mid -1);
        }else{
            return binarySearch(mid+1, right);
        }
    }
}
