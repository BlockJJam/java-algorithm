package solve.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GroupWordChecker_1316 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int N = rd.nextInt();
        boolean[] visited = new boolean[27]; // a~z +1

        int result = 0;
        for(int i=0; i<N; i++){
            String str = rd.next();
            char[] data = str.toCharArray();
            char target = '{'; // ascii에서 "z" 다음

            for(int j = 0; j<data.length; j++){
                if(data[j] == target){
                    result = j == data.length-1? result +1 : result;
                }else{
                    int idx = data[j] -'a';
                    if(!visited[idx]){
                        target = data[j];
                        visited[idx] = true;
                        result = j == data.length-1? result +1 : result;
                    }else{
                        break;
                    }
                }
            }
            Arrays.fill(visited, false);
        }
        System.out.println(result);
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
