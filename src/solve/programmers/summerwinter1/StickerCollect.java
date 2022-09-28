package solve.programmers.summerwinter1;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class StickerCollect {

    public static int solution(int sticker[]) {
        int answer = 0;
        int N = sticker.length;
        // 최댓값 배열을 만든다. maxArr
        int[] maxArr = new int[N];

        if(N == 1){
            answer = sticker[0];
            return answer;
        }

        // maxArr의 0,1번째 값은 sticker의 0,1번째 중에 최댓값이 된다.
        // maxArr의 2번째 값부터는 maxArr[i] = Max(maxArr[i-2]+ sticker[i], maxArr[i-1]) 을 연산한다.
        // maxArr을 2번 검사해야되는데, 그 이유는 원형이기 때문에, 0번째에서 시작과 1번째에서 시작을 검사해야한다.
        // 0번째 시작은 maxArr[N-2]값이 최댓값이 된다. maxArr[N-1]은 첫번째 스티커의 영향을 받기 때문. 물론, 2번째 스티커가 사용될 수 있지만, 이를 위해 밑에 연산이 존재
        // 그래서 1번째 시작으로 maxArr[N-1]값을 최댓값으로, 이전 최댓값과 비교한다.
        int max = 0;
        for(int i=0; i<2; i++){
            for(int j = i; j<N -(1-i);j++){
                if( j == i) {
                    maxArr[j] = sticker[j];
                }else if(j == i+1) {
                    maxArr[j] = Math.max(sticker[j - 1], sticker[j]);
                }else{
                    maxArr[j] = Math.max(maxArr[j-1], maxArr[j-2] + sticker[j]);
                }
            }

            max = i == 0? maxArr[N-2]: Math.max(max, maxArr[N-1]);
            Arrays.fill(maxArr, 0);
        }

        return answer = max;
    }

    // 최대값 배열과 점화식을 만들어서 풀이하자
    // 최대값 배열: array
    // 점화식: array[i] = Max(array[i-1], array[i-2] + sticker[i])

    public static void main(String[] args) {
        int[] test = new int[100_000];
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for(int i = 0; i< test.length; i++){
            test[i] = random.nextInt(10);
        }
//        int ret = solution(new int[]{1, 3, 2, 5, 4});
//        int ret = solution(new int[]{14, 6, 5, 11, 3, 9, 2, 10});
        int ret = solution(test);
        System.out.println(ret);
    }
}
