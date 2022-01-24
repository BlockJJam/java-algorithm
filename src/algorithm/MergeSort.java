package algorithm;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * # 정의
 * - 문제를 즉각 해결 가능해 질 때까지 재귀적으로 둘 이상의 하위 문제들로 나누고(Divide) 문제를 해결(Conquer)한 다음 그 결과를 이용하여 다시 합쳐진(Merge) 문제를 해결해 나아가, 전체 문제를 해결하는 방법
 * - 결국, “문제를 제일 작은 단위로 나누어 - 풀고 - 합쳐서 다시 풀기를 재귀적으로 해 나아가다보면 답이 나온다!” 는 개념
 *
 * # 핵심 진행방식
 * 1. 분할: 동일한 타입의 하위 문제로 큰 문제를 분할한다
 * 2. 정복: 재귀적으로 하위 문제를 해결한다
 * 3. 병합: 직전 분할된 문제 결과를 활용하여, 병합된 문제를 해결한다
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for(int i=0; i< arr.length; i++){
            arr[i] = (int)(Math.random()*100);
        }

        mergeSort(arr, 0, arr.length-1);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+",");
        }

        Arrays.stream(arr).forEach(e-> System.out.println(e));
    }

    private static void mergeSort(int[] arr, int start, int end){
        // 1개남으면 그대로 반환
        if(start == end)
            return;

        // 중앙 idx를 기준으로 1개남을 때까지 쪼개기
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid+1, end);

        int temp[] = new int[end -start +1];
        int idx = 0;
        int left = start;
        int right = mid+1;

        // 병합된 배열들 중에 left가 mid값을 벗어나지 않고 & right가 end값을 벗어나지 않는 범위에서
        // [left .. right ..] 값들을 비교해서 temp에 정렬된 값을 채워나간다 left, right중에 선택된 인덱스는 후위연산자로 루프마무리에 ++해준다
        while(left <= mid && right <= end){
            temp[idx++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }
        // left가 mid를 벗어나지 않았으면 남겨진 애들부터 temp를 채워준다
        while(left <= mid){
            temp[idx++] = arr[left++];
        }
        // right가 end를 벗어나지 않았으면 남겨진 애들부터 temp를 채워준다
        while(right <= end){
            temp[idx++] = arr[right++];
        }

        for(int i=start; i <= end; i++){
            arr[i] = temp[i-start];
        }
    }
}
