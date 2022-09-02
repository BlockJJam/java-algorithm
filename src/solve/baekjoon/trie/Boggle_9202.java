package solve.baekjoon.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boggle_9202 {
    static int W, B;
    static Character[][] graph;
    static Trie trie;
    static boolean[][] visited = new boolean[4][4];
    static Map<String, Boolean> isUsed = new HashMap<>();
    static int point;
    static int findCnt;
    static String output;
    static PriorityQueue<String> pq;

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        W = rd.nextInt();
        graph = new Character[4][4];


        trie = new Trie();
        // 입력받는 사전은 바로 Trie 사전에 넣어준다.
        for(int i=0; i<W; i++){
            String word = rd.next();
            trie.insert(word);
            isUsed.put(word, false);
        }

        B = rd.nextInt();
        // 테스트 케이스
        for(int i=0; i<B; i++){
            // 각 테스트 케이스마다, 재료들을 초기화 해주자.
            point = 0;
            findCnt = 0;
            output = "";
            pq = new PriorityQueue<>();
            for(String key: isUsed.keySet()){
                isUsed.put(key, false);
            }
            for(int l=0; l<4; l++){
                Arrays.fill(visited[l], false);
            }

            // B번동안 graph 입력받아서, dfs 돌려보자
            for(int j= 0; j<4; j++){
                String input= rd.next();
                for(int k= 0; k<4; k++){
                    graph[j][k] = input.charAt(k);
                }
            }

//            printGraph();

            for(int j= 0; j<4; j++){
                for(int k= 0; k<4; k++){
                    // 각 그래프 위치마다 시작하는 dfs를 호출해주자.
                    // - 문제 풀이가 길어진 이유 1) 해당 graph[j][k]부터 탐색을 시작해야되는데 dfs 3번째 파라미터에 "빈문자열"을 넣어버렸다
                    visited[j][k] = true;
                    dfs(j, k, String.valueOf(graph[j][k]), 7);
                    visited[j][k] = false;
                }
            }
            output = pq.poll();

            System.out.print(point + " ");
            if(output != null){
                System.out.print(output + " ");
            }
            System.out.println(findCnt);

        }

    }

    static void printGraph(){
        for(Character[] g: graph){
            for(Character e: g){
                System.out.print(e + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 북 -> 동 -> 남 -> 서 -> 북 시계방향
    static int[] adjR = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] adjC = {0, 1, 1, 1, 0, -1, -1, -1};

    public static boolean innerGraph(int r, int c){
        return r >= 0 && r < 4 && c >= 0 && c < 4;
    }


    public static void dfs(int r, int c, String text, int remain){
        // 해당 단어가 사전에 있고, isUsed 맵에서 이번 테스트케이스에서 꺼냈던 적이 있는 단어인지 검사
        if(trie.findWord(text) && !isUsed.get(text)){
//            System.out.println("text = " + text);
            isUsed.put(text, true);
            point += getPointByTextSize(text.length());
            findCnt++;
            fillTextInQueue(text);
        }

        if(remain == 0){
            return;
        }

        StringBuilder sb;
        for(int i=0; i<8; i++){
            int nr = r + adjR[i];
            int nc = c+ adjC[i];

            sb = new StringBuilder(text);

            if(innerGraph(nr, nc) && !visited[nr][nc]){
                sb.append(graph[nr][nc]);
                String nextWord = sb.toString();
                if(trie.possibleToFind(nextWord)){
                    visited[nr][nc] = true;
                    dfs(nr, nc, nextWord, remain -1);
                    visited[nr][nc] = false;
                }
            }
        }
    }

    public static int getPointByTextSize(int textLen){
        // 각 문자열 길이에 맞는 점수를 반환해주자
        // - 문제 풀이가 길어진 이유 2) 1~2길이에 0점 반환인데, 0~1길이에 0점 반환으로 해버려서, 길이가 2일때 11을 반환해버렸다.
        return textLen <= 2? 0:
                textLen == 3 || textLen == 4? 1:
                textLen == 5? 2:
                textLen == 6? 3:
                textLen == 7? 5:
                textLen == 8? 11: 0;
    }

    private static void fillTextInQueue(String text) {
        // queue에 가장 긴 길이의 문자열을 사전순으로 담기 위한 로직
//        System.out.println(text);
        if(!pq.isEmpty()){
            if(text.length() == pq.peek().length()){
                pq.offer(text);
            }else if(text.length() > pq.peek().length()){
                pq = new PriorityQueue<>();
                pq.offer(text);
            }
        }else{
            pq.offer(text);
        }
    }

    static class Trie{
        private TrieNode rootNode;

        Trie(){
            this.rootNode = new TrieNode();
        }

        public void insert(String word){
            // 체크할 노드를 rootNode로 설정
            TrieNode currNode = this.rootNode;

            // computeIfAbsent로 있으면 그냥 있는 node로, 없으면 해당 Character:new TrieNode 원소를 추가
            for (int i = 0; i < word.length(); i++) {
                currNode = currNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
            }
            // 마지막 node가 마지막 스펠링임을 지정
            currNode.setLastChar(true);
        }

        public boolean findWord(String word){
            // 체크할 노드를 rootNode로 설정
            TrieNode currNode = rootNode;

            for(int i =0; i<word.length(); i++){
                // 해당 철자를 잠깐 빼보자
                Character c = word.charAt(i);

                // 해당 각 철자마다 node로 가지고 있는지 꺼내보자
                TrieNode nodeAboutWord = currNode.getChildNodes().get(c);

                // 없으면 사전에 포함하지 않은 단어
                if(nodeAboutWord == null)
                    return false;

                // 다음 철자를 검사하러 가보자
                currNode = nodeAboutWord;
            }

            // 마지막 node가 해당 사전에 있는 단어 끝이 맞냐?
            return currNode.isLastChar();

        }

        // 기존에 없던 메서드: 해당 단어가 더 탐색해도 되는지여부를 리턴한다.
        // - dfs에서 word 이후로 탐색하지 못하게 하려고 만든 함수
        public boolean possibleToFind(String word){
            // 체크할 노드를 rootNode로 설정
            TrieNode currNode = rootNode;

            for(int i =0; i<word.length(); i++){
                // 해당 철자를 잠깐 빼보자
                Character c = word.charAt(i);

                // 해당 각 철자마다 node로 가지고 있는지 꺼내보자
                TrieNode nodeAboutWord = currNode.getChildNodes().get(c);

                // 없으면 사전에 포함하지 않은 단어
                if(nodeAboutWord == null)
                    return false;

                // 다음 철자를 검사하러 가보자
                currNode = nodeAboutWord;
            }

            // 마지막 node까지 아직 진행해도 되는 것인지?
            return true;
        }
    }

    static class TrieNode{
        private Map<Character, TrieNode> childNodes = new HashMap<>();
        private boolean isLastChar;


        // getter setter로 해당 노드의 값을 조회 및 설정해주자
        public Map<Character, TrieNode> getChildNodes() {
            return childNodes;
        }

        public boolean isLastChar() {
            return isLastChar;
        }

        public void setLastChar(boolean isLastChar){
            this.isLastChar = isLastChar;
        }

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
