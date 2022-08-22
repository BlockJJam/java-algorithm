package solve.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LeaveCompany_14501 {
    static int N;
    static int[] T;
    static int[] P;
    static int result = 0;
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        T = new int[N];
        P = new int[N];

        // T[]에는 기간이, P[] 에는 수입이 들어있다.
        for(int i=0; i<N; i++){
            T[i] = rd.nextInt();
            P[i] = rd.nextInt();
        }

        // 0번째 날부터 최대 이익을 낼 수 있는 plan을 세워보자
        makeRichPlan(0, 0);

        System.out.println(result);
    }

    // start기간부터 N까지 최대 이익을 내기 위해 재귀하면서 max earn을 구할 것이다.
    static void makeRichPlan(int start, int earn){
        // T를 훑으면서 나온 earn값 중에 최대를 찾는다.
        result = Math.max(earn, result);

        for(int i=start; i<N; i++){
            // 조건: T[i]에 나온 값 + i는 현재 날짜를 의미 -> 이것이 N을 넘으면 안된다.
            if(T[i] + i <= N){
                // 조건에만 맞는다면, 해당 기간 T[i]를 수행한 다음 로직으로 넘어갈 수 있도록, 재귀한다.
                makeRichPlan(i + T[i], earn + P[i]);
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
