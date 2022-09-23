package algorithm.search;

/**
 * KMP 언제쓸까?
 * - 전체 문자열 중에 특정 문자열을 찾을 때, KMP가 없다면 -> 이중 for문을 통해 문자열을 검색할 것이다
 * - [전체 문자열에서 불일치가 발생하기 직전까지 같았던 부분]을 다시 비교하지 말고 패턴 매칭을 통해 진행하자
 *
 */
public class KMPStringSearch {
    public static void main(String[] args) {
        String text = "ababacabacaaba";
        String pattern = "abacaaba";
        KMPLegacy(text, pattern);
        KMP(text, pattern);
    }

    // 예시: "ababacabacaaba" 에서 "abacaaba"를 찾기
    static void KMPLegacy(String text, String pattern){
        int[] table = makeTable(pattern);

        int n1 = text.length(), n2 = pattern.length();
        int begin = 0, matched = 0;

        while(begin <= n1-n2){
            // 만약 text 해당 글자가 pattern의 해당 글자와 같다면? 단 matched가 n2를 넘어간 숫자면 안된다
            if (matched < n2 && text.charAt(begin + matched) == pattern.charAt(matched)) {
                ++matched;
                // 결과적으로 pattern 글자가 모두 일치했다면 답에 추가한다
                if (matched == n2) {
                    System.out.println( (begin+1) + "번째에서 찾았습니다 ~" + (begin+matched));
                }
            }else{
                // 예외: matched가 0인 경우 다음 칸으로
                if(matched == 0){
                    ++begin;
                }else{
                    begin += matched - table[matched-1];
                    // begin을 옮겼다고 해서 처음부터 비교하지 않고, table[matched-1]만큼은 일치하기 때문에 그 뒤부터 탐색하도록 matched를 만들어준다
                    matched = table[matched -1];
                }
            }
        }
    }

    // 전통적인 방법은 부보인 구현으로 좀 까다롭다, 더욱 간결하게 만들어 응용하기 더욱 편하도록 만들어본다
    static void KMP(String text, String pattern){
        int[] table = makeTable(pattern);
        char[] texts = text.toCharArray();
        char[] patterns = pattern.toCharArray();

        int idx=0;
        for (int i = 0; i < texts.length; i++) {
            while(idx > 0 && texts[i]  != patterns[idx]){
                idx = table[idx-1];
            }

            if(texts[i] == patterns[idx]){
                if(idx == patterns.length -1){
                    System.out.printf("%d번째에서 찾았습니다 ~%d", i-idx+1, (i+1));
                    idx = table[idx];
                }else{
                    idx++;
                }
            }
        }
    }

    static int[] makeTable(String pattern){
        int n = pattern.length();
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
            // 일치하는 문자가 발생했을 때(idx > 0), 연속적으로 더 일치하지 않으면 idx = table[idx-1]로 돌려준다
            while (idx > 0 && pattern.charAt(idx) != pattern.charAt(i)) {
                idx = table[idx-1];
            }

            if (pattern.charAt(idx) == pattern.charAt(i)) {
                idx += 1;
                table[i] = idx;
            }
        }

        return table;
    }
}
