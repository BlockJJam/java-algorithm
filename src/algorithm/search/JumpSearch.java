package algorithm.search;

/**
 * - 정의
 *
 * : 정렬된 데이터를 가진 배열 or 리스트에서 “jump”를 수행할 size를 지정한 후에 탐색을 진행
 * → jump를 찾는 예시 중 하나 = `Math.floor( Math.sqrt(배열길이) )` = $내림(\sqrt배열길이)$
 * : 찾고자 하는 데이터인 “X”가 있다면, 0번째 index부터  jump 단위만큼 넘어가면서 해당 X가 포함된
 * : arr[prev] ≤ X ≤ arr[curr] 에 해당하는 prev, curr을 찾고 선형탐색으로 X를 찾는다
 * : 해당 탐색의 시간 복잡도: O(√n) ****← jump단위로 범위 찾기(${n\over m}$), 범위안에서 최악의 경우($\le \sqrt n$)
 */
public class JumpSearch {
    public static void main(String[] args){
        int[] arr = {-22, -15, 1, 7, 20, 35, 55};

        int x = -15;
        int result = search(arr, x);
        System.out.println(result == -1 ? x + " does not exist" : "Found! Index is : " + result);

        x = 20;
        result = search(arr, x);
        System.out.println(result == -1 ? x + " does not exist" : "Found! Index is : " + result);

    }

    public static int search(int[] arr, int x){
        int size = arr.length;

        // jump 사이즈 지정
        int jump = (int)Math.floor(Math.sqrt(size));

        int prevIdx = 0;
        int currIdx = Math.min(jump, size) -1; // 0부터 시작이기 때문에 -1

        // x범위를 나타내는 prev, curr idx찾기
        while(arr[currIdx] < x){
            prevIdx = currIdx;
            currIdx += jump;
            if(prevIdx >= size){
                return -1;
            }
        }

        // 찾은 prev, curr 안에서 선형탐색
        while(prevIdx <= currIdx){
            if(arr[prevIdx] == arr[currIdx]) {
                return prevIdx;
            }
            ++prevIdx;
        }
        return -1;
    }
}
