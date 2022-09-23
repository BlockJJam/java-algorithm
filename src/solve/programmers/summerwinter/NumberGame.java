package solve.programmers.summerwinter;

import java.util.Arrays;
import java.util.Comparator;

public class NumberGame {
    public static int solution(int[] A, int[] B) {
        int answer = -1;
        answer = 0;
        int arrLen = A.length;
        // B의 가장 큰놈 순서대로, A팀의 가장 큰놈을 상대해야 한다. int[] -> Integer
        Integer[] ABoxed  = Arrays.stream(A).boxed().toArray(Integer[]::new);
        Integer[] BBoxed = Arrays.stream(B).boxed().toArray(Integer[]::new);

        Arrays.sort(ABoxed, Comparator.reverseOrder());
        Arrays.sort(BBoxed, Comparator.reverseOrder());

        for(int n: ABoxed)
            System.out.print(n + " ");
        System.out.println();

        for(int n: BBoxed)
            System.out.print(n + " ");
        System.out.println();

        int pivot =0;
        for(int i=0; i<arrLen; i++){
            if(ABoxed[i] < BBoxed[pivot]){
                pivot ++;
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 1, 3, 7}, new int[]{2, 1, 6, 8}));
    }
}
