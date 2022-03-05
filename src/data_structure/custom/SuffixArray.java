package data_structure.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * # 단순 구현으로 접미사 배열 만들기
 *   - 시간 복잡도: String length = N, O(N^2 * logN)
 *
 * # 멘버-마이어스 알고리즘으로 접미사 배열만들기
 *   - 시간 복잡도: String length = N, O(N * logN * logN)
 */
public class SuffixArray {
    public static void main(String[] args) {
        String text = "banana";
        ArrayList<Integer> suffixes = makeSuffixArray(text);
        printSuffixes(text, suffixes);

        ArrayList<Integer> suffixesByMM = makeSuffixArrayByManberMyer(text);
        printSuffixes(text, suffixesByMM);
    }


    // 단순 구현으로 접미사 배열을 만들어보자
    public static ArrayList<Integer> makeSuffixArray(String text){
        int len = text.length();

        // 접미사 내역을 저장하는 배열(suffixes)을 생성, 길이는 현재 text의 길이만큼만 하면 모두 저장이 가능
        // index 값만 원소로 저장해도 접미사 시작위치를 나타내기 때문에 상관없고, 오히려 메모리 낭비를 막는다
        // (String 값을 모두 저장하면, 메모리 낭비가 심함 -> O(N^2))
        ArrayList<Integer> suffixes = new ArrayList<>();
        for(int i=0; i<len; i++){
            suffixes.add(i);
        }

        // index만 String 값 기반으로 정렬하여 사용할 것이다
        // Collections.sort를 사용 O(N*logN) 의 시간 복잡도를 갖는 정렬 방식 이용
        // Comparator를 구현 -> index값에 해당하는 substring된 문자열을 비교
        Collections.sort(suffixes, (o1, o2) ->{
            String a = text.substring(o1);
            String b = text.substring(o2);
            return a.compareTo(b);
        });
        return suffixes;
    }

    // 멘버-마이어스 알고리즘 활용 접미사 배열
    public static ArrayList<Integer> makeSuffixArrayByManberMyer(String text){
        int pivot = 1; // 0, 1, 3 ,7 번째 index 역할, pivot *= 2

        int n = text.length();

        // 위와 마찬가지로 접미사 index를 저장할 배열을 만든다
        ArrayList<Integer> suffixes = new ArrayList<>();

        // dividedGroup 은 각 문자열을 잘라낸 후 해당 문자열이 속할 group을 지정하기 위해 사용
        // 결국 1개씩 각 몬든 그룹에 속하게 된다 -> dividedGroup length = n
        int[] dividedGroup = new int[n];

        for(int i=0; i < n; i++){
            // suffixes를 초기화
            suffixes.add(i);

            // dividedGroup도 각 접미사의 첫번째 글자를 기준으로 설정
            // 모두 소문자일 경우만이라고 가정하자
            dividedGroup[i] = text.charAt(i) - 'a';
        }

        // 비교할 위치를 지정하는 pivot의 값이 전체 문자열의 길이를 넘지 못하는 선에서 진행
        while(pivot < n){
            // Comparator에서 참조하기 위해 복사해놓자
            int[] compDividedGroup = dividedGroup;
            int compPivot = pivot;

            // index만 String값 기반으로 정렬하여 사용한다
            // Comparator Functional interface를 통해 index값에 해당하는 substring된 문자를 비교
            // 시간복잡도 : O(n * log n)
            Collections.sort(suffixes, (idx1, idx2) ->{
                // idx1의 접미사와 idx2의 접미사의 그룹이 다르다면
                // 그룹번호가 더 큰 것이 더 뒤의 문자가 됨, 따라서 크기를 비교하여 반환
                if( compDividedGroup[idx1] != compDividedGroup[idx2]){
                    return compDividedGroup[idx1] - compDividedGroup[idx2];
                }

                // 만약 두 index의 접미사 그룹이 같다면?
                int left = idx1 + compPivot >= n ? -1 : idx1 + compPivot;
                int right = idx2 + compPivot >= n? -1 : idx2 + compPivot;
                return left - right;
            });

            // 정렬을 진행한 뒤, 현재 정렬된 상태의 내역들을 각 그룹으로 묶어줄 값을 저장
            int[] newGroup = new int[n];

            // ArrayList의 제일 첫번째에 있다는 것은 현재 비교된 문자를 기준으로 가장 앞에 있다는 의미
            newGroup[suffixes.get(0)] = 0;

            for (int i = 1; i < n; i++) {
                // i-1번째 접미사가 i번째의 접미사보다 더 그룹 번호가 크다? i-1번째 그룹의 +1의 그룹으로 더 큰 번호가 하나 뒤에 오도록 한다
                if(dividedGroup[suffixes.get(i-1)] < dividedGroup[suffixes.get(i)]){
                    newGroup[suffixes.get(i)] = newGroup[suffixes.get(i-1)] +1;
                }else{
                    // 그렇지 않으면(크거나, 같으면) 같은 그룹에 속하도록 한다
                    newGroup[suffixes.get(i)] = newGroup[suffixes.get(i-1)];
                }
            }
            dividedGroup = newGroup;
            pivot *= 2;
        }

        return suffixes;
   }

    public static void printSuffixes(String text, ArrayList<Integer> suffixes){
        for(int i: suffixes){
            System.out.print(i +", ");
        }
        System.out.println();

        for(int i: suffixes){
            System.out.print(text.substring(i) +", ");
        }
        System.out.println();
    }

}
