package solve.programmers.summerwinter1;

public class CookiePurchase {
    public static int solution(int[] cookie) {
        int answer = -1;

        int max = 0;
        for (int i = 0; i < cookie.length-1; i++) {
            // 모든 cookie배열 위치에 m을 적용한다.
            int prev = i;
            int next = i+1;

            int lSum = cookie[prev];
            int rSum = cookie[next];
            while(true){
                // lSum과 rSum을 비교한다.
                if(lSum == rSum){
                    // lSum과 rSum이 같으면, max중에 더 큰 값을 지정하고 prev-- , next++ 를 체크한다.
                    max = Math.max(lSum, max);
                    if(--prev == -1 || ++next == cookie.length){
                        break;
                    }

                    lSum += cookie[prev];
                    rSum += cookie[next];
                }else if(lSum < rSum){
                    // lSum이 더 작으면, prev--를 체크하고, lSum을 업데이트한다.
                    if(--prev == -1){
                        break;
                    }
                    lSum += cookie[prev];
                }else{
                    // rSum이 더 작으면, next++을 체크하고, rSum을 업데이트한다.
                    if(++next == cookie.length){
                        break;
                    }
                    rSum += cookie[next];
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
//        int ret = solution(new int[]{1,1,2,3});
        int ret = solution(new int[]{1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5,});
//        int ret = solution(new int[]{1,2,4,5});

        System.out.println("ret = " + ret);
    }
}
