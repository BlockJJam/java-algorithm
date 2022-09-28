package solve.programmers.summerwinter1;

public class ToBuildStation {

    public static int solution(int n, int[] stations, int w) {
        int answer = 0;

        int operand = 2 * w + 1;

        int left = stations[0] - w -1;
        if( left > 0){
            // 0번째 왼쪽
            answer += (left + operand - 1)/ operand; // operand -1: 나머지값 Math.ceil 대신
        }

        int sLen = stations.length;
        int right = n - stations[sLen -1] - w;
        if(right > 0){
            // 맨마지막 오른쪽
            answer += (right + operand - 1) / operand;
        }

        for(int i=1; i<stations.length; i++){
            // 맨왼쪽 ~ 맨오른쪽 사이,
            int gap = stations[i] - stations[i -1] - 2 * w-1; // (station[i] - 1)다음 기지국 전 위치(다음 기지국 포함 X) - station[i-1]이전 기지국 - (2 * w)전파 범위
//            System.out.println("station1: "+ stations[i-1] + " station2: "+ stations[i] + " gap: "+ gap );
            answer += (gap + operand - 1) / operand;
        }

        return answer;
    }


    public static void main(String[] args) {
        int[] test = new int[10000];
        for(int i=0; i<10000; i++){
            test[i] = (i+1)*15000;
        }
//        int ret = solution(200_000_000, test, 1000);
        int ret = solution(16, new int[]{2, 7}, 2);
        System.out.println(ret);
    }
}
