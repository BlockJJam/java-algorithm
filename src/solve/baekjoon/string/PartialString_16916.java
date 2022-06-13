package solve.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PartialString_16916 {
    static int result = 0;

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        String S = rd.next();
        String P = rd.next();

        kmsSearch(S, P);

        System.out.println(result);

    }

    public static void kmsSearch(String text, String pattern){
        int[] table = makeTable(pattern);
        char[] texts = text.toCharArray();
        char[] patterns = pattern.toCharArray();

        int idx = 0;
        for(int i=0; i<texts.length; i++){
            while(idx > 0 && texts[i] != patterns[idx]){
                idx = table[idx-1];
            }

            if(texts[i] == patterns[idx]){
                if(idx == patterns.length -1){
                    result =1;
                    return;
                }else{
                    idx ++;
                }
            }
        }
    }

    public static int[] makeTable(String pattern){
        int n = pattern.length();
        int[] table = new int[n];
        int idx = 0;

        for (int i = 1; i < n; i++) {

            while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx))
                idx = table[idx - 1];

            if(pattern.charAt(i) == pattern.charAt(idx)){
                idx += 1;
                table[i] = idx;
            }
        }

        return table;
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
