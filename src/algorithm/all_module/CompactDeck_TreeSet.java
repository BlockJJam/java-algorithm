package algorithm.all_module;

// TreeSet은 중복을 지우고, 오름차순으로 정렬되어 있기 때문에, 입력받은 start와 end값을 중복없이 각 원소의 해당 index가 압축덱이 되도록 이용한다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 *             ------
 *       ------
 *    -------------------
 * |--|--|----|-----|---|--|
 * 0  2  4    6     10  16 ...20000000
 *     --
 *   --
 * ---------
 * |-|-|-|-|
 * 0 1 2 3 4
 */
public class CompactDeck_TreeSet{
    static int N;
    static int[] sarr;
    static int[] earr;
    static TreeSet<Integer> tree = new TreeSet<>();

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        sarr = new int[N];
        earr = new int[N];

        for (int i = 0; i < N; i++) {
            int s = rd.nextInt();
            int e = rd.nextInt();

            sarr[i] = s;
            earr[i] = e;

            tree.add(s);
            tree.add(e);
        }

        ArrayList<Integer> idxList = new ArrayList<>();
        int[] sum = new int[idxList.size()];

        for (int i = 0; i < N; i++) {
            int start  = Collections.binarySearch(idxList, sarr[i]);
            int end = Collections.binarySearch(idxList, earr[i]);

            for(int time = start; time < end; time++){
                sum[time]++;
            }
        }

        int max = 0;
        int startIdx = -1;
        int endIdx = -1;

        for(int i = 0; i < sum.length; i++){
            if(sum[i] > max){
                startIdx = i;
                endIdx = i;
                max = sum[i];
            }

            if(sum[i] == max && (i-1) == endIdx){
                endIdx = i;
            }
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
