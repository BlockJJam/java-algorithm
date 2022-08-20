package solve.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoSolution_2470 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int[] arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i] = rd.nextInt();
        }
        Arrays.sort(arr);

        int start = 0;
        int end = arr.length - 1;

        int zeroNear = Integer.MAX_VALUE;
        int target = 0;
        Pair result = new Pair(arr[start], arr[end]);

        while(start != end){
            target = Math.abs(arr[start] + arr[end]);
            zeroNear = Math.min(zeroNear, target);
            if(target == zeroNear){
                result = new Pair(arr[start], arr[end]);
            }

            int a = Math.abs(arr[start+1] + arr[end]);
            int b = Math.abs(arr[start] + arr[end-1]);
            if(a < b){
                start = start +1;
            }else{
                end = end -1;
            }
        }


        System.out.println(result.left + " " + result.right);
    }

    static class Pair{
        int left;
        int right;

        Pair(int left, int right){
            this.left = left;
            this.right = right;
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
