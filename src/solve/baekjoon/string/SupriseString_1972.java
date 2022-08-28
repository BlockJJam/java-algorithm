package solve.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SupriseString_1972 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        /**
         * text: 입력받는 문자열
         * N : text 길이
         * sb: 문자열을 term 간격에 X-쌍을 만드는 builder
         * loopCnt: Set의 size와 만들어낸 총 쌍의 개수를 통해 중복되는 word가 있는지 총 쌍의 개수를 저장
         * surprised: 그래서 유일한지
         * term: X-쌍의 X의 term을 저장
         * ts: TreeSet을 통해 중복되는 단어는 거르기 위해 사용하는 Set자료구조
         */
        String text = rd.next();
        int N = text.length();
        StringBuilder sb;
        int loopCnt;
        boolean surprised;
        int term;
        TreeSet<String> ts;

        // 입력받은 문자열이 *이면 종료한다.
        while(!text.equals("*")){
            // term은 항상 1로 바로 옆 문자부터 체크한다.
            term = 1;
            surprised = true;
            // term은 문자열 끝의 문자까지 가능
            while(term <= N-1){
//                System.out.println(term + ", N: "+ N);
                ts = new TreeSet();
                loopCnt = 0;

                // 첫번째 문자 + term뒤의 문자 조합을 ts에 넣고, loopCnt는 한번 처리당 하나씩 증가한다.
                for(int i=0; i< N - term; i++){
                    sb = new StringBuilder();
                    sb.append(text.charAt(i));
                    sb.append(text.charAt(i+term));

                    String word = sb.toString();
                    ts.add(word);
                    loopCnt++;
                }

                // ts에서 걸러진 쌍이 있다면, 유일하지 않다는 증거로, 놀랍지 않다는 처리를 해주고, 반복문을 나가자
                if(loopCnt != ts.size()){
                    surprised = false;
                    break;
                }

                term ++;
            }
            if(surprised)
                System.out.println(text + " is surprising.");
            else
                System.out.println(text + " is NOT surprising.");

            text = rd.next();
            N = text.length();
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
