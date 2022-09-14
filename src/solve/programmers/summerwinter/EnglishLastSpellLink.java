package solve.programmers.summerwinter;


import java.util.HashMap;
import java.util.Map;

public class EnglishLastSpellLink {
    Map<String, Boolean> wordMap = new HashMap<>();

    public int[] solution(int n, String[] words){
        int[] answer = new int[2];

        int target = 1;
        int totalLoop = 1;
        boolean hasDropped = false;
        char lastWord = words[0].charAt(0);

        for( String word : words){
            if(lastWord != word.charAt(0)){
                hasDropped = true;
                break;
            }

            lastWord = word.charAt(word.length() -1);
            if(wordMap.get(word) == null){

                wordMap.put(word, true);
                ++target;
                if(target == n + 1){
                    totalLoop++;
                    target = 1;
                }
            }else{
                hasDropped = true;
                break;
            }
        }

        if(hasDropped){
            answer[0] = target;
            answer[1] = totalLoop;
        }

        return answer;
    }
}
