package algorithm.all_module;

/**
 * KMP 언제쓸까?
 * - 전체 문자열 중에 특정 문자열을 찾을 때, KMP가 없다면 -> 이중 for문을 통해 문자열을 검색할 것이다
 * - [전체 문자열에서 불일치가 발생하기 직전까지 같았던 부분]을 다시 비교하지 말고 패턴 매칭을 통해 진행하자
 */
public class KMP {

    static void kmp(String text, String pattern){
        int[] table = makeTable(pattern);

        int n1 = text.length();
        int n2 = pattern.length();

        int begin = 0;
        int matched = 0;

        while(begin <= n1 - n2){
            if(matched < n2 && text.charAt(begin + matched) == pattern.charAt(matched)){
                ++ matched;

                if(matched == n2){
                    // begin + 1번째에서 찾았을 때의 로직
                }
            }else{
                if(matched == 0){
                    ++matched;
                }else{
                    begin += matched - table[matched-1];
                    matched = table[matched - 1];
                }
            }
        }
    }

    static int[] makeTable(String pattern){
        int n = pattern.length();
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
            while(idx > 0 && pattern.charAt(idx) != pattern.charAt(i)){
                idx = table[idx -1];
            }
            if(pattern.charAt(idx) == pattern.charAt(i)){
                table[i] = ++idx;
            }
        }

        return table;
    }
}
