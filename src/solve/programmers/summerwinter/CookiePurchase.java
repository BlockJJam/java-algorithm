package solve.programmers.summerwinter;

public class CookiePurchase {
    public static int solution(int[] cookie) {
        int answer = -1;

        // 1개 일 때와 2개 일때 전처리
        if(cookie.length == 1){
            return -1;
        }else if(cookie.length == 2){
            int ans = cookie[0] == cookie[1] ? cookie[0] : -1;
            return ans;
        }

        // 이분법과 세그먼트 트리를 사용할 예정
        SegmentTree st = new SegmentTree(cookie.length);
        for(int i= 0; i<cookie.length; i++){
            st.updateTree(i+1, cookie[i]);
//            System.out.println("i = " + i);
        }

        int pivot = findPivot(1, cookie.length, st);

        int noDiff = isNoDiff(pivot, cookie.length, st);
        if(noDiff == 0){
            return st.getSum(pivot);
        }else if(noDiff == -1){
            pivot++;
        }
        // l과 r을 조절하지 않은 상태에서 m+1->r까지 합 - l->m합 > 0 중에 최소인 m을 pivot으로 구함

        int l = 1;
        for(int m = pivot; m < cookie.length; m++){
            for(int i = l; i < m; i++){
                // l 첫번째 바구니부터 제거해서, m까지 합이 m+1 - r까지 합보다 작아지면 break;
                int sumDiff = (st.getSum(cookie.length) - st.getSum(m)) - (st.getSum(m) - st.getSum(i));
//                System.out.println("sumDiff = " + sumDiff);
                if(sumDiff <= 0) {
//                    System.out.println("i = " + i + " m = " + m);
                    l = i;
                    break;
                }
            }

            // m -l 과 m+1 - r 을 비교 0이면 리턴, 아니면 continue;
            if((st.getSum(cookie.length) - st.getSum(m)) - (st.getSum(m) - st.getSum(l)) == 0){
                return st.getSum(cookie.length) - st.getSum(m);
            }
        }

        return 0;
    }

    static int isNoDiff(int m, int r, SegmentTree st){
        int left = st.getSum(m);
        int right = st.getSum(r) - st.getSum(m);

//        System.out.println("left = " + left + " right = " + right);

        // -1 이면 왼쪽이 더 적음, 0이면 과자 수 같음, 1이면 오른쪽이 더 적음
        return Integer.compare(left, right);

    }

    static int findPivot(int l, int r, SegmentTree st){
        // l ~ m 까지 합과 m+1 ~ r 까지 합의 차가 가장 작을 때의 pivot을 꺼낸다.
        int m = 0;
        while(l < r){
            m = (l+r)/2;
//            System.out.println("m = " + m);
            int lSum = st.getSum(m);
            int rSum = st.getSum(r) - st.getSum(m);
            if(lSum < rSum ){
                l = m+1;
            }else if(lSum > rSum){
                r = m;
            }else{
                break;
            }
        }

        return m;
    }

    static class SegmentTree{
        private int[] tree;

        SegmentTree(int n){
            tree = new int[n+1];
        }

        public void updateTree(int idx, int value){
            while(idx <= tree.length){
//                System.out.println("idx = " + idx);
                tree[idx] += value;
                idx += (idx & -idx);
            }
        }

        public int getSum(int idx){
            int sum = 0;
            while(idx > 0){
                sum += tree[idx];
                idx -= (idx & -idx);
            }

            return sum;
        }
    }

    public static void main(String[] args) {
        int ret = solution(new int[]{1,1,2,3});
//        int ret = solution(new int[]{1,2,4,5});

        System.out.println("ret = " + ret);
    }
}
