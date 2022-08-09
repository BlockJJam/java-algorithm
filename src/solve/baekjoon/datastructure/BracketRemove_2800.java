package solve.baekjoon.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BracketRemove_2800 {
    static String operStr = "";
    static Set<String> set = new HashSet<>();
    static PriorityQueue<String> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        operStr = rd.next();

        removeBracket(-1, false, operStr);
        List<String> test = new ArrayList<>(set);
        Collections.sort(test);
        for(String str: test){
            System.out.println(str);
        }
    }

    static void removeBracket(int idx, boolean removing, String target){
        // removing이 true면 괄호찾기
        boolean removed = false;
        if(removing){
            for(int i =idx; i< target.length(); i++){
                // '(' 지우기
                if(target.charAt(i) == '('){
                    target = removeAndReturn(target, i);
                    removed = true;
                    break;
                }
            }

            if(removed){
                int findCnt = 0;
                for (int i = idx; i < target.length(); i++) {
                    if (target.charAt(i) == '(') ++findCnt;
                    if (target.charAt(i) == ')') {
                        if (findCnt == 0) {
                            target = removeAndReturn(target, i);
                            break;
                        }
                        findCnt--;
                    }
                }
            }
        }
        idx = removing ? idx: idx+1;

        if(idx >= target.length() - 1){
            if(target.equals(operStr) || !removing) return;
            set.add(target);
            return;
        }


        for(int i=idx; i< target.length(); i++){
            if(target.charAt(i) == '(') {
                idx = i;
                break;
            }
            if(i == target.length()-1)
                idx = i;
        }

        removeBracket(idx, true, target);
        removeBracket(idx, false, target);
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
