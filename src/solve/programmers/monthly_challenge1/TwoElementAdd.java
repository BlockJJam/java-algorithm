package solve.programmers.monthly_challenge1;

import java.util.TreeSet;

public class TwoElementAdd {
    public int[] solution(int[] numbers) {
        int[] answer = {};

        TreeSet<Integer> ts = new TreeSet<>();
        for(int i=0; i<numbers.length; i++){
            for (int j = i + 1; j < numbers.length; j++) {
                ts.add(numbers[i] + numbers[j]);
            }
        }

        answer = new int[ts.size()];
        int i = 0;
        for(int num : ts){
            answer[i++] = num;
        }
        return answer;
    }
}
