package solve.baekjoon.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BracketRemove_2800 {
    static String operStr = "";
    static PriorityQueue<String> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        operStr = rd.next();

        int bracketCnt = 0;
        for(int i = 0; i< operStr.length(); i++){
            if(operStr.charAt(i) == '('){
                bracketCnt++;
            }
        }

        removeBracket(0, false, operStr, bracketCnt);
        while(!pq.isEmpty()){
            System.out.println(pq.poll());
        }
    }

    static void removeBracket(int idx, boolean removing, String target, int total){
//        System.out.println("idx: "+idx);


        // removing이 true면 괄호찾기
        if(removing){
            // '(' 지우기
            int findCnt = 0;
            int nextStart = 0;
            for(int i =0; i< target.length(); i++){
                // '(' 지우기
                if(target.charAt(i) == '(') ++findCnt;
                if(findCnt == idx){
                    target = removeAndReturn(target, i);
                    nextStart = i;
                    break;
                }
            }

            findCnt = 0;
            for(int i = nextStart; i<target.length(); i++){
                if(target.charAt(i) == '(') ++findCnt;
                if(target.charAt(i) == ')'){
                    if(findCnt == 0){
                        target = removeAndReturn(target, i);
                        break;
                    }
                    findCnt--;
                }
            }
            idx--;
            total--;
        }
        if(idx == total ){
            if(target.equals(operStr)) return;
            pq.offer(target);
//            System.out.println(target);
            return;
        }
        removeBracket(idx+1, false, target, total);
        removeBracket(idx+1, true, target, total);
    }

    private static String removeAndReturn(String target, int i) {
        StringBuilder sb = new StringBuilder(target);
        sb.deleteCharAt(i);
        target = sb.toString();
        return target;
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
