package solve.baekjoon.dp.LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LIS2_12015 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int[] arr = new int[N];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arr[i] = rd.nextInt();
        }

        for(int num : arr){
            if(list.size() == 0 || list.get(list.size() -1) < num){
                list.add(num);
            }else{
                int l = 0;
                int r = list.size() - 1;
                while( l < r){
                    int mid = (l + r) / 2;
                    if(list.get(mid) < num){
                        l = mid +1;
                    }else{
                        r = mid;
                    }
                }
                list.set(r, num);
            }
        }
        System.out.println(list.size());

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
