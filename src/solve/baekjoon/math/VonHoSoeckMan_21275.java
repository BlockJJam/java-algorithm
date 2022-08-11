package solve.baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class VonHoSoeckMan_21275 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        String AX = rd.next();
        String BX = rd.next();

        Map<Character, Integer> notationMap = new HashMap<>();
        for(int i=97; i<(97+26); i++){
            char notion = (char)i;
            notationMap.put(notion, i-87);
        }

        // AX의 진법 최댓수 찾기
        int aMin = 0;
        char c;
        int num = 0;
        for(int i=0; i<AX.length(); i++){
            c = AX.charAt(i);
            num = Character.isDigit(c)?  c -'0': notationMap.get(c);
            aMin = Math.max(num, aMin);
        }

        // BX의 진법 최댓수 찾기
        int bMin = 0;
        for(int i=0; i<BX.length(); i++){
            c = BX.charAt(i);
            num = Character.isDigit(c)? c - '0' : notationMap.get(c);
            bMin = Math.max(num, bMin);
        }


        // AX로 X후보가 되는 Map 채우기
        long X = 0L;
        Map<Long, Integer> xMap = new HashMap<>();
        for(int i = aMin+1; i <= 36; i++){
            X = 0L;
            for(int j=0; j<AX.length(); j++){
                int loop = AX.length() - 1 - j;

                c = AX.charAt(j);
                num = Character.isDigit(c)?  c -'0': notationMap.get(c);

                X += num * (long)Math.pow(i, loop);
            }
//            System.out.println("A의 X: "+ X);

            if(X >= 0L){
                xMap.put(X, i);
            }
        }

        int xCnt = 0;
        int A = -1;
        int B = -1;
        long XResult = -1;
        for(int i = bMin+1; i<= 36; i++){
            X = 0L;
            for(int j = 0; j < BX.length(); j++){
                int loop = BX.length() - 1 - j;

                c = BX.charAt(j);
                num = Character.isDigit(c)? c - '0': notationMap.get(c);

                X += num * (long)Math.pow(i, loop);
            }
//            System.out.println("B의 X: " + X);

            if( X >= 0L && xMap.containsKey(X)){
                if(!xMap.get(X).equals(i)){
                    xCnt++;
                    A = xMap.get(X);
                    B = i;
                    XResult = X;
                }
            }
        }

        System.out.println(xCnt == 0? "Impossible": xCnt == 1? XResult +" "+ A + " " + B : "Multiple");




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
