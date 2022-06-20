package solve.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BardYongJae_19948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputOrigin = br.readLine();
        StringBuilder sb = new StringBuilder();
        char prev = '!';
        for(int i=0; i < inputOrigin.length(); i++){
            char target = inputOrigin.charAt(i);
            if(target != prev){
                sb.append(target);
            }
            prev = target;
        }
        String input = sb.toString();

        input = input.toLowerCase();

        StringTokenizer st = new StringTokenizer(input);
        List<Character> topics = new ArrayList<>();


        while(true){
            try{
                String text = st.nextToken();
                topics.add(text.charAt(0));
            }catch(Exception e){
                break;
            }
        }


        Integer space = Integer.parseInt(br.readLine());
        Map<Integer, Integer> remainedAlphaMap = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<26; i++){
            remainedAlphaMap.put(i, Integer.parseInt(st.nextToken()));
        }
        remainedAlphaMap.put(26, space);

        for(int i=0; i< input.length(); i++){
            int target = input.charAt(i) - 'a';
            int key = 0;
            if(target < 0){
                key = 26;
            }else{
                key = target;
            }
            int replacedCnt = remainedAlphaMap.get(key) - 1;
            if(replacedCnt < 0){
                System.out.println(-1);
                return;
            }
            remainedAlphaMap.replace(key, replacedCnt);
        }

        StringBuilder answer = new StringBuilder();
        for(char c : topics){
            answer.append(c);
            int target = c -'a';

            int replacedCnt = remainedAlphaMap.get(target) -1;
            if(replacedCnt < 0){
                System.out.println(-1);
                return;
            }
            remainedAlphaMap.replace(target, replacedCnt);
        }
        System.out.println(answer.toString().toUpperCase());
    }
}
