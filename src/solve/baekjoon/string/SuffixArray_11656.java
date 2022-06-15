package solve.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SuffixArray_11656 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        String text = rd.next();

        List<Integer> suffixes = makeSuffixArray(text);
        for(int idx: suffixes){
            System.out.println(text.substring(idx));
        }
    }

    public static List<Integer> makeSuffixArray(String text){
        int pivot = 1;
        int n = text.length();

        List<Integer> suffixes = new ArrayList<>();
        int[] dividedGroup = new int[n];

        for(int i=0; i<n; i++){
            suffixes.add(i);
            dividedGroup[i] = text.charAt(i) -'a';
        }

        while (pivot < n) {
            int[] copiedDividedGroup = dividedGroup;
            int compPivot = pivot;

            Collections.sort(suffixes, (idx1, idx2) -> {
                if (copiedDividedGroup[idx1] != copiedDividedGroup[idx2]) {
                    return copiedDividedGroup[idx1] - copiedDividedGroup[idx2];
                }

                int left = idx1 + compPivot >= n ? -1 : idx1 + compPivot;
                int right = idx2 + compPivot >= n ? -1 : idx2 + compPivot;

                return left - right;
            });

            int[] newGroup = new int[n];
            newGroup[suffixes.get(0)] = 0;

            for (int i = 1; i < n; i++) {
                if (dividedGroup[suffixes.get(i - 1)] < dividedGroup[suffixes.get(i)]) {
                    newGroup[suffixes.get(i)] = newGroup[suffixes.get(i - 1)] + 1;
                } else {
                    newGroup[suffixes.get(i)] = newGroup[suffixes.get(i - 1)];
                }
            }

            dividedGroup = newGroup;
            pivot *= 2;
        }

        return suffixes;
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
