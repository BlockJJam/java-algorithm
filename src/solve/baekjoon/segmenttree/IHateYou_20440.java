package solve.baekjoon.segmenttree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// TreeSet은 중복을 지우고, 오름차순으로 정렬되어 있기 때문에, 입력받은 start와 end값을 중복없이 각 원소의 해당 index가 압축덱이 되도록 이용한다
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
public class IHateYou_20440 {
    // sarr과 earr은 각각, 해당 모기의 timeline과 이를 idxList에서 꺼내기 위한 용도로 사용한다.
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

        // 압축덱이 완성되었으면, ArrayList에서 사용할 것이다.
        ArrayList<Integer> idxList = new ArrayList<>(tree);
        int[] sum = new int[idxList.size()];

        for(int i=0; i<N; i++){
            int start = Collections.binarySearch(idxList, sarr[i]);
            int end = Collections.binarySearch(idxList, earr[i]);
            // s, e의 index를 ArrayList에서 찾아, 압축덱에서 해당 범위의 모기 개수를 +1해준다.
            for(int time = start; time < end; time++){
                sum[time]++;
            }
        }

        int max = 0;
        int startIdx = -1;
        int endIdx = -1;

        // 압축덱 범위가 곧, 전체 모기 timeline의 범위가 된다.
        for(int i=0; i<sum.length; i++){
            // 새로운 max값이 나오면, max 최신화 해주고, startIdx와 endIdx를 초기화
            if(sum[i] > max){
                startIdx = i;
                endIdx = i;
                max = sum[i];
            }
            if(sum[i] == max && (i-1) == endIdx){
                // max의 유지시간만큼 endIdx를 최신화해준다.
                endIdx = i;
            }
        }

        System.out.print(max + "\n" + idxList.get(startIdx) + " " + idxList.get(endIdx+1));
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
