package solve.programmers.monthly_challenge1;

import java.util.Arrays;
import java.util.HashSet;

public class PopBalloon {
    // 다른 사람 풀이 - 내 풀이보다 6배 정도 빠르다.
    public static int solution(int[] a) {

        // 양쪽 방향에서 양옆의 풍선 중에 터트릴 수 있는 가장 작은 값을 HashSet에 넣는다.
        // HashSet에 넣는 위치에서는 한쪽 방향으로는 최솟값임을 증명가능하기 때문에 해당 로직이 가능
        int answer = 0;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        HashSet<Integer> hs = new HashSet<>();
        // int[][] dp=new int[a.length][2];
        for(int i=0;i<a.length;i++){
            min1=Math.min(min1,a[i]);
            min2=Math.min(min2,a[a.length-1-i]);
            hs.add(min1);
            hs.add(min2);
            // dp[i][0]=min1;
            // dp[a.length-1-i][1]=min2;

        }
        // for(int i=0;i<dp.length;i++){
        //     System.out.println(dp[i][0]+" "+dp[i][1]);
        // }
        return hs.size();
    }


    // 내 풀이
//    public static int solution(int[] a) {
//        int answer = 0;
//
//        // 최대 1번만 작은 번호를 터트릴 수 있다
//        // 최후의 1인이 가능한 원소 개수
//        // 인접한 두 풍선을 고른 뒤, 더 큰 번호의 풍선을 터트리 되, 최대 1번 작은 번호를 터트린다를 아래 의미로 판단해봤다
//        // 해당 번호 위치에서 왼쪽과 오른쪽을 나누었을 때, 양쪽 모두 자신보다 작은 번호가 제일 작은 번호라면 {해당번호}만 남겨질 수 없다.
//        // 그 이유는 양쪽 중에 제일 작은 번호 나머지 풍선을 다 터트리고 남을 것인데, {해당 번호}는 양쪽 중에 최대 1번만 작은 번호를 터트릴 수 있기 때문
//
//
//        // 0~N까지 해당 위치의 왼쪽 가장 작은수, 오른쪽 가장 작은 수를 기억하자
//        SegmentTree st = new SegmentTree(a.length, 0, a.length-1);
//        st.init(a, 1, 0, a.length- 1);
//        for(int i=1; i<a.length-1; i++){
//
//            int leftMin = st.getMin(0, i-1);
//            int rightMin = st.getMin(i+1,  a.length-1);
//            // a[i] 왼쪽에 가장 작은 값과 오른쪽 가장 작은 값 중에 2개 이상 a[i]보다 작은 값이 있지 않으면 그 값은 최후의 1인이 될 수 있다.
//            if(a[i] < leftMin || a[i] < rightMin){
//                answer++;
//            }
////            System.out.println("leftMin = " + leftMin + " rightMin = " + rightMin);
//        }
//        answer += 2;
//
//        return answer;
//    }
//
//    /**
//     * SegmentTree는 범위 내에서 가장 작은 값을 뽑아내기 위해 사용
//     */
//    static class SegmentTree{
//        private int[] tree;
//        private int startIdx;
//        private int endIdx;
//        private int NODE_START = 1;
//
//        SegmentTree(int n, int start, int end){
//            tree = new int[n*4];
//            this.startIdx = start;
//            this.endIdx = end;
//        }
//
//        int init(int[] arr, int node, int start, int end){
//            if(start == end){
//                return tree[node] = arr[start];
//            }else {
//                int mid = (start + end)/2;
//                return tree[node] = Math.min(
//                        init(arr, node*2, start, mid),
//                        init(arr, node *2 +1, mid +1, end));
//            }
//        }
//
//        int getMin(int left, int right){
//            return getMin(NODE_START, startIdx, endIdx, left, right);
//        }
//
//        int getMin(int node, int start, int end, int left, int right){
//            if(end < left || right < start){
//                return Integer.MAX_VALUE;
//            }else if(left <= start && end <= right){
//                return tree[node];
//            }else{
//                int mid = (start + end) /2;
//                return Math.min(getMin(node*2, start, mid, left, right),
//                        getMin(node*2+1, mid+1, end, left, right));
//            }
//        }
//    }


    public static void main(String[] args) {
        solution(new int[]{-16,27,65,-2,58,-92,-71,-68,-61,-33});
    }
}
