package solve.baekjoon.greedy;

import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class Polyomino_1343 {
    static String board;
    static String result="";

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        board = st.nextToken();
        Polyomino polyomino = new Polyomino();

        int x = 0;
        boolean success = true;
        for(int i=0; i<board.length(); i++){
            if(!success) break;

            Character c = board.charAt(i);
            if(c.equals('.')){
                success = polyomino.makeBoard(x, false);
                x = 0;
            }else if(i == board.length()-1){
                ++x;
                success = polyomino.makeBoard(x, true);
            }else{
                ++x;
            }
        }

        if(success){
            bw.write(result +"\n");
        }else{
            bw.write("-1\n");
        }
        bw.flush();
        bw.close();
    }

    static class Polyomino{
        public boolean makeBoard(int x, boolean last){
            if(x % 2 == 1){
                return false;
            }
            int temp = x;
            Stream.generate(()->"AAAA").limit((x/4)).reduce((a,b)->a+b).ifPresent(aaaa-> result+= aaaa );
            temp -= (x/4)*4;
            Stream.generate(() -> "BB").limit((temp / 2)).reduce((a, b) -> a + b).ifPresent(aaaa-> result += aaaa);

            if(!last){
                result += '.';
            }
            return true;
        }
    }
}
