package solve.baekjoon.divideconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tree_4256 {
    static int N;
    static int[] pre;
    static int[] in;
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int T = rd.nextInt();

        while(T-- != 0){
            N = rd.nextInt();
            // 전위 탐색 결과와 중위 탐색 결과 리스트를 입력받는다
            pre = new int[N];
            in = new int[N];

            for(int i=0; i<N; i++){
                pre[i] = rd.nextInt();
            }

            for(int i=0; i<N; i++){
                in[i] = rd.nextInt();
            }

            // 전위 탐색과 중위 탐색으로 후위 탐색 결과를 찍어보자
            startFindPost();
            System.out.println();
        }
    }

    // pi는 총 0~N번의 탐색을 이어가기 위한 값, 한번의 탐색에 하나씩 증가할 것이다
    static int pi;
    // 첫번째 값을 지정하여 탐색을 시작하기 위해 startFindPost 메서드를 따로 뺐다.
    // pre 배열의 시작원소가 root 노드 값이다.
    // 방식은 이러하다
    // - 1. pre배열은 전위 탐색결과이기 때문에, 가장 먼저 접근하는 node의 값부터 출력한다.
    // - 2. in배열은 중위 탐색결과이기 때문에, 왼 - 부모 - 오른 순으로 출력한다.
    // - 3. 위 1번, 2번을 이용하면, pre배열 순서대로 탐색한 원소를 in배열에서 그 원소를 찾아 index를 구한다.
    // - 4. 해당 index를 마지막에 출력하고, 그 전에 해당 index를 기준으로 왼쪽 오른쪽으로 배열을 나눠, 각 나뉘어진 배열 범위에서 3번을 실행한다.
    // - 5. leaf node에 도달하면, 탐색은 멈추고 해당 값을 print한다.
    // 이를 통해 분할과 정복을 이용하고, 탐색 후에 해당 node의 데이터를 print하면 후위탐색 결과를 확인할 수 있다.
    static void startFindPost(){
        pi = 0;
        int ii = 0;
        int data = pre[pi];
        for(int i=0; i<N; i++){
            if(pre[pi] == in[i]){
                ii = i;
                break;
            }
        }
        if(N != 1){
            if( ii-1 >= 0) {
                ++pi;
                findPost(0, ii - 1);
            }
            if( ii+1 <= N -1) {
                pi++;
                findPost(ii + 1, N - 1);
            }
        }
        System.out.print(data + " ");
    }

    static void findPost(int startIi, int endIi){
//        if(visited[pi]) return;

//        System.out.println("pi = " + pi + " pre: "+ pre[pi] + " start: "+ startIi + " endIi: "+ endIi);

        int ii=0;
        int data = pre[pi];


        for(int i= startIi; i<=endIi; i++){
            if(pre[pi] == in[i]){
//                System.out.println("i = " + i);
                ii = i;
                break;
            }
        }

        if(ii -1 >= startIi){
            ++pi;
            findPost(startIi, ii-1);
        }
        if(ii + 1 <= endIi){
            ++pi;
            findPost(ii + 1, endIi);
        }
        System.out.print(data+ " ");
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
