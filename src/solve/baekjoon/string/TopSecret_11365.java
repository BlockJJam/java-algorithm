package solve.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class TopSecret_11365 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Character> dq = new ArrayDeque<>();

        String input = br.readLine();
        StringBuilder sb = new StringBuilder();
        while(!input.equals("END")){
            char[] data = input.toCharArray();
            for(int i=0; i<data.length; i++){
                dq.add(data[i]);
            }

            while(!dq.isEmpty()){
                sb.append(dq.pollLast());
            }
            sb.append("\n");
            input = br.readLine();
        }
//        sb.append('\n');
        System.out.print(sb.toString());

    }
}
