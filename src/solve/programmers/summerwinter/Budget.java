package solve.programmers.summerwinter;

import java.util.Arrays;

public class Budget {
    public int solution(int[] d, int budget){
        int answer =0;
        Arrays.sort(d);

        int sum = 0;
        for(int i=0; i<d.length; i++){
            sum += d[i];

            if(sum <= budget){
                answer++;
            }else{
                break;
            }
        }
        return answer;
    }
}
