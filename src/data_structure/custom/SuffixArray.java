package data_structure.custom;

import java.util.*;

/**
 * # 단순 구현으로 접미사 배열 만들기
 *   - 시간 복잡도: String length = N, O(N^2 * logN)
 *
 * # 멘버-마이어스 알고리즘으로 접미사 배열만들기
 *   - 시간 복잡도: String length = N, O(N * logN * logN)
 */
public class SuffixArray {
    public static void main(String[] args) {
        String text = "baoekjoon";
        ArrayList<Integer> suffixes = makeSuffixArray(text);
        printSuffixes(text, suffixes);

        List<Integer> suffix = getSuffixArray(text);

        for (int i : suffix) {
            System.out.println(i + " - " + text.substring(i));
        }
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

    private static List<Integer> getSuffixArray(String text) {
        int n = text.length();
        int t = 1;

        // 접미사 배열이 될 반환 값 (이 동적 배열을 log(n)번 정렬)
        List<Integer> perm = new ArrayList<>();
        // group
        int[] group = new int[n + 1];
        for (int i = 0; i < n; i++) {
            perm.add(i);
            // text가 영어 소문자로만 이루어졌다고 가정
            group[i] = text.charAt(i) - 'a';
        }
        group[n] = -1;

        CompUsing2T compUsing2T = new CompUsing2T(n, t, group);
        while (t < n) {
            // group[]은 첫 t글자를 기준으로 계산해뒀다. 첫 2t글자를 기준으로 perm을 다시 정렬한다.
            Collections.sort(perm, compUsing2T.comparator);
            t *= 2;
            if (t >= n) break;

            int[] newGroup = new int[n + 1];
            newGroup[perm.get(0)] = 0;
            newGroup[n] = -1;

            for (int i = 1; i < n; i++) {
                if (compUsing2T.comparator.compare(perm.get(i - 1), perm.get(i)) < 0) {
                    newGroup[perm.get(i)] = newGroup[perm.get(i - 1)] + 1;
                } else {
                    newGroup[perm.get(i)] = newGroup[perm.get(i - 1)];
                }
            }
            group = newGroup;
            compUsing2T.changeValues(t, group);
        }
        return perm;
    }

    static class CompUsing2T {
        private int n;
        private int t;
        private int[] group;

        public CompUsing2T(int n, int t, int[] group) {
            this.n = n;
            this.t = t;
            this.group = Arrays.copyOf(group, group.length);
        }

        public void changeValues(int t, int[] group) {
            this.t = t;
            this.group = Arrays.copyOf(group, group.length);
        }

        private Comparator<Integer> comparator = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (group[o1] != group[o2]) {
                    return group[o1] - group[o2];
                }
                int left = o1 + t, right = o2 + t;
                if (o1 + t > n) {
                    left = n;
                }
                if (o2 + t > n) {
                    right = n;
                }
                return group[left] - group[right];
            }
        };
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
