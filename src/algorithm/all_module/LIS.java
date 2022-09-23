package algorithm.all_module;

public class LIS {
    static int[] LisDp; // index는 부분 최장 증가 수열의 길이, value는 해당 부분 수열 중에 마지막 원소들 중 최솟값
    static int N;
    static int[] arr;

    public static void solution(){
        LisDp = new int[N+1];
        LisDp[0] = arr[0];

        int idx = 0;
        for(int i=1; i<N;i++){
            if(LisDp[idx] < arr[i]){
                LisDp[++idx] = arr[i];
            }else{
                int ii = lowerBound(idx, arr[i]); // dp의 idx 미만 원소 위치에, dp 값보다 제일 가까운 큰 값 위치를
                arr[ii] = arr[i];
            }
        }
    }

    public static int lowerBound(int r, int key){
        int l = 0;

        while(l < r){
            int m = (l + r) / 2;
            if(LisDp[m] <key){
                l = m + 1;
            }else{
                r = m;
            }
        }

        return r;
    }
}
