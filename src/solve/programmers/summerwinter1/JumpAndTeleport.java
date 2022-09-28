package solve.programmers.summerwinter1;

public class JumpAndTeleport {
    // 쓸데없이 이것 저것 붙인 나의 풀이..
//    static int answer; // 해당 위치까지의 전력 사용량
//    static int[] dp;
//    public static void main(String[] args) {
//        solution(6);
//        solution(5);
//        solution(5000);
////        solution(1_000_000_00);
//    }
//
//    public static int solution(int n) {
//        answer = Integer.MAX_VALUE;
//        int ans = 0;
//        dp = new int [n+1];
//        Arrays.fill(dp, Integer.MAX_VALUE);
//
//        jumpOrTeleport(n);
//        System.out.println(answer);
//        return ans;
//    }
//
//    public static void jumpOrTeleport(int n){
//        Queue<Move> q = new LinkedList<>();
//
//        q.offer(new Move(1, 1));
//
//        while(!q.isEmpty()){
//            Move m = q.poll();
//            int dest = m.dest;
//            int k = m.k;
//            System.out.println("dest = " + dest + ", k = " + k);
//            dp[dest] = Math.min(k, dp[dest]);
//
//            if(dest == n){
//                System.out.println("k = " + k);
//                answer = Math.min(k, answer);
//            }
//            for(int i=0; i<2; i++){
//                int nd;
//                int nk;
//                if(i == 0){
//
//                    nd = dest * 2;
//                    nk = k;
//                    while(nd <= n){
//                        if(nk < dp[nd] && nk < answer){
//                            dp[nd] = nk;
//                            q.offer(new Move(nd, nk));
//                        }
//                        nd *= 2;
//                    }
//                }else{
//                    nd = dest + 1;
//                    nk = k + 1;
//                    if(nd <= n){
//                        if(nk < dp[nd] && nk < answer) {
//                            dp[nd] = nk;
//                            q.offer(new Move(nd, nk));
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    static class Move{
//        int dest;
//        int k;
//
//        public Move(int dest, int k){
//            this.dest = dest;
//            this.k = k;
//        }
//    }
    public int solution(int n){
        int sub = 1;
        int ans = 0;

        while(n != 0){
            // 백트래킹을 해보면, 2로 나누었을 때, 1이 남으면 그 수를 더해주는 위치다 -> 1을 빼주어 2로 나누는 것을 이어가자
            if(n%2 == 1){
                n -= sub;
                ans += 1;
            }
            // 2로 나누면서 순간이동을 할 수 있는 위치에서는 지속해서 2로 나누도록 한다.
            n /= 2;
        }
        return ans;
    }
}
