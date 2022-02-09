package algorithm.search;

/**
 * ## 보간탐색 (쏠림탐색)
 * - 정의
 * : 이진 탐색처럼 범위의 중간을 자르는 방식과 유사하다
 * : 이진 탐색이 범위의 중간 위치를 통해 구간을 나눴다면, 보간탐색은 구간을 나누는 위치를 구하는 공식이 있다
 *
 *     > $pos = low + ((X - arr[low])*(high-low)) / (arr[high] - arr[low])$
 *
 *     - low: 현재 구간의 시작 위치
 *     - high: 현재 구간의 마지막 위치
 *     - X: 찾고자 하는 data
 *     >
 * - 이미 정렬된 데이터 범위 안에서 (X - arr[low]) 부분과 (arr[high] - arr[low]) 부분이 중요하다. 즉, 현재 구간 내 최댓값과 최솟값의 차이로 데이터 값 크기의 차이를 나누어준다
 * → 공식의 의미는 이렇게 인식하면 된다, 데이터가 크면 전체 분자의 크기가 커지게 되어 구간을 나누는 위치가 오른쪽으로 쏠리면서, 탐색하고자 하는 위치를 빠르게 찾을 수 있다( 반대도 이해했겠지? )
 * - 보간 탐색은 전체 데이터 범위에서 데이터가 균등하게 분포되어 있을 수록 탐색하고자 하는 위치와 범위를 빠르게 찾는다
 * - 시간복잡도는 다소 편차가 있을 수 있다 → 보통은 $O(log(log(n))$이지만, 최악은 $O(n)$이 될 수도 있다
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = {10, 11, 13, 15, 17, 19, 20, 25, 26, 28, 30, 32, 35, 36, 40, 41, 42};

        int x = 40;
        int result = search(arr, x, 0, arr.length-1);
        System.out.println(result == -1 ? x + " does not exist" : "Found! Index is : " + result);

        x = 11;
        result = search(arr, x, 0, arr.length-1);
        System.out.println(result == -1 ? x + " does not exist" : "Found! Index is : " + result);

    }

    protected static int search(int[] arr, int x, int low, int high){
        if (low > high || x < arr[low] || x > arr[high]) {
            return -1;
        }

        int pos = low + ((high - low) * (x - arr[low])) / (arr[high] - arr[low]);
        if( arr[pos] == x){
            return pos;
        }

        if(arr[pos] < x){
            return search(arr, x, pos + 1, high);
        }else{
            return search(arr, x, low, pos - 1);
        }
    }
}
