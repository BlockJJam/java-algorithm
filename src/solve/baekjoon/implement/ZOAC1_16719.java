package solve.baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ZOAC1_16719 {
    static int N;
    static ArrayList<Character>[] charList; // 0 ~ text 길이까지의 ArrayList 배열을 만들어, 각 위치부터 text끝까지의 단어의 sorting된 Character 배열을 이용할 것이다.
    // 이하 charList를 "text 위치 별 문자리스트"로 부른다.
    static boolean[] visited; // 사전순으로 방문 처리된 여부를 나타낼 배열로, 콘솔에 찍을 때도 활용한다.
    static String text;

    public static void main(String[] args) {
        FastReader rd = new FastReader();
        // text 문장을 입력받는다
        text = rd.next();

        // text 전체길이는 두고두고 사용한다.
        N =text.length();
        charList = new ArrayList[N];
        visited = new boolean[N];
        for(int i=0; i<N; i++){
            charList[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++){
            // 각 위치에서 시작하는 text 위치 별 문자리스트에 Character를 추가한다.
            char target = text.charAt(i);
            for(int j=0; j<=i; j++){
                charList[j].add(target);
            }
        }

        // 0번째부터 N번째까지 각 index에서 시작하고 text끝 Charcter까지 담은 리스트를 사전순으로 sorting한다.
        for(int i=0; i<N; i++) {
            Collections.sort(charList[i]);
        }

        printOrderByDictionary(0);
    }

    static void printOrderByDictionary(int arrIdx){
        // 해당 메서드는 arrIdx는 text의 위치로, 해당 위치의 charList의 배열을 뒤져서,
        // 해당 문자 리스트안에 사전순으로 가져올 Character의 위치를 findIdxInText메서드로부터 받아
        // visited 방문처리하고, 콘솔에 방문처리된 문자열들을 찍는다 -> 답을 맞추기 위해
        // 만약 해당 문자 리스트 안에 원하는 Character 위치를 찾지못하면 그냥 넘어간다.
        for(int i=0; i<charList[arrIdx].size(); i++){
            int findIdx = findIdxInText(arrIdx, charList[arrIdx].get(i));
            if(findIdx != -1){
                visited[findIdx] = true;
                for(int j=0; j<visited.length; j++){
                    if(visited[j]) System.out.print(text.charAt(j));
                }
                System.out.println();
                printOrderByDictionary(findIdx);
            }
        }

    }

    static int findIdxInText(int start, char target){
        // text를 뒤질 위치가 start가 되고, start ~ text 끝 위치까지 탐색해서, 해당 target과 같은 character가 있는지,
        // 그 character가 방문한적은 없는지 여부를 확인하고 모두 통과하면 해당 위치인 i를 리턴한다.
        for(int i = start; i<N; i++){
            if(text.charAt(i) == target && !visited[i]){
                return i;
            }
        }
        return -1;
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
