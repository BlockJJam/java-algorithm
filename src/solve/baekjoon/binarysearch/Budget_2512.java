package solve.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Budget_2512 {
    static int N;
    static int M;
    static int[] P;

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        // 예산 요청 개수와 각 요청금액을 입력받는다.
        N = rd.nextInt();
        P = new int[N];
        for(int i=0; i<N; i++){
            P[i] = rd.nextInt();
        }

        // 전체 예산 금액을 입력받는다.
        M = rd.nextInt();
        Arrays.sort(P);

        // 상한선의 최댓값은 입력받은 값 중에 제일 큰 값
        int r = P[N-1];


        System.out.println(findMaxLimit(0, r));
    }

    // 이분탐색을 통해 상한선 최댓값을 구해보자
    public static int findMaxLimit(int l, int r){
        // mid != 상한선 값으로 안 떨어질 때는, 작아야 할 값이 커버리면, 둘 중 작은 값을 반환 == r
        if(l > r) return r;


        int mid =   (l + r)/2;
        int sum = sumByMaxLimit(mid);
//        System.out.println("mid = " + mid);
//        System.out.println("sum = " + sum);

        // 상한선금액으로 총예산과 각 예산을 더한 값을 비교했을 때, 두 값이 같으면 바로 값 리턴, 총예산이 더 크면 상한선을 높임, 총예산이 더 작으면 상한선 내림
        if(sum == M) return mid;
        if(sum < M) return findMaxLimit(mid+1, r);
        else return findMaxLimit(l, mid-1);
    }

    public static int sumByMaxLimit(int max){
        // P배열은 오름차순, max보다 작은 값은 그냥 더하다가, max보다 크거나 같은 값이 나오면 남은 예산들 * max를 한번에 더해버리고 끝냄
        int retSum = 0;
        for(int i=0; i<N; i++){
            if(P[i] >= max){
                int rest = max * (N-i);
                i = N;
                retSum += rest;
            }else{
                retSum += P[i];
            }
        }

        // P배열 예산들과 상한선을 이용해서 모두 더한 값 반환
        return retSum;
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
