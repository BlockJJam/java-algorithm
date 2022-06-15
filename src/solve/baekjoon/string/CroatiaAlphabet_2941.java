package solve.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CroatiaAlphabet_2941 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        String text = rd.next();
        int N = text.length();

        Map<String, Boolean> pattern = new HashMap<>();
        fillPattern(pattern);

        int result = 0;
        int jump = 0;
        for (int i = 0; i < N; i+=jump) {

            jump = 0;
            for(int j=i+1; j<=i+3; j++){
                if(j== N+1) {
                    jump =1;
                    break;
                }

                ++jump;
                if(pattern.containsKey(text.substring(i,j))){
                    break;
                }else if(j == i+3){
                    jump = 1;
                }
            }
            result++;
        }
        System.out.println(result);
    }

    static void fillPattern(Map<String, Boolean> pattern){
        pattern.put("c=", true);
        pattern.put("c-", true);
        pattern.put("dz=", true);
        pattern.put("d-", true);
        pattern.put("lj", true);
        pattern.put("nj", true);
        pattern.put("s=", true);
        pattern.put("z=", true);
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
