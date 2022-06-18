package solve.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SuffixArray_11656 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        String text = rd.next();

        List<Integer> suffixes = makeSuffixArray(text);
        for(int idx: suffixes){
            System.out.println(text.substring(idx));
        }
    }

    private static List<Integer> makeSuffixArray(String text){
        int n = text.length();
        int t = 1;

        List<Integer> perm = new ArrayList<>();

        int[] group = new int[n+1];
        for(int i=0 ; i<n; i++){
            perm.add(i);
            group[i] = text.charAt(i) - 'a';
        }

        group[n] = -1;
        CompUsing2T compUsing2T = new CompUsing2T(n, t, group);

        while(t < n){
            Collections.sort(perm, compUsing2T.comparator);

            t *= 2;
            if(t >= n) break;

            int[] newGroup = new int[n+1];

            newGroup[perm.get(0)] = 0;
            newGroup[n] = -1;

            for(int i =1; i<n; i++){
                if(compUsing2T.comparator.compare(perm.get(i-1), perm.get(i))<0){
                    newGroup[perm.get(i)] = newGroup[perm.get(i - 1)] + 1;
                }else{
                    newGroup[perm.get(i)] = newGroup[perm.get(i-1)];
                }

            }
            group = newGroup;
            compUsing2T.changeValues(t, group);
        }

        return perm;
    }

    static class CompUsing2T {
        private int n;
        private int t;
        private int[] group;

        public CompUsing2T(int n, int t, int[] group) {
            this.n = n;
            this.t = t;
            this.group = group;
        }

        public void changeValues(int t, int[] group){
            this.t = t;
            this.group = Arrays.copyOf(group, group.length);
        }

        private Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(group[o1] != group[o2]){
                    return group[o1] - group[o2];
                }

                int left = o1 +t, right = o2 +t;
                if(left > n)
                    left = n;
                if(right > n)
                    right = n;

                return group[left] - group[right];
            }
        };
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
