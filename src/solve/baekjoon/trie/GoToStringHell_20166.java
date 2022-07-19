package solve.baekjoon.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GoToStringHell_20166 {
    static char[][] graph;
    static Trie trie;
    static int N;
    static int M;
    static int maxLen = 0;

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        M = rd.nextInt();
        int K = rd.nextInt();
        graph = new char[N + 1][M + 1];
        trie = new Trie();

        for(int i=1; i<N+1; i++){
            String input = rd.next();
            for(int j=1; j<M+1; j++){
                graph[i][j] = input.charAt(j-1);
            }
        }

        List<String> godList = new ArrayList<>();
        for(int i=0; i<K; i++){
            String input = rd.nextLine();
            maxLen = Math.max(maxLen, input.length());
            godList.add(input);
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                dfs(j, i, String.valueOf(graph[i][j]));
            }
        }

        for(int i=0; i<K; i++){
            int cnt = trie.contains(godList.get(i));
            System.out.println(cnt);
        }
    }

    static int[] adjX = {0,1,1,1,0,-1,-1,-1};
    static int[] adjY = {-1,-1,0,1,1,1,0,-1};
    static StringBuilder sb;
    static int cnt = 0;
    static void dfs(int x, int y, String word){
        if(word.length() == maxLen){
            trie.insert(word);
            return;
        }


        for(int i=0; i<8; i++){
            int nx = x + adjX[i];
            nx = nx == 0? M : nx > M ? 1 : nx;
            int ny = y + adjY[i];
            ny = ny == 0? N : ny > N ? 1 : ny;

            sb = new StringBuilder(word);
            sb.append(graph[ny][nx]);
            dfs(nx, ny, sb.toString());
        }
    }


    static class Trie{
        private TrieNode rootNode;

        public Trie(){
            this.rootNode = new TrieNode();
        }

        void insert(String word){
            TrieNode currNode = this.rootNode;

            for (int i = 0; i < word.length(); i++) {
                currNode = currNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
            }
            currNode.setLastChar(true);
            currNode.increaseCnt();
        }

        int contains(String word){
            TrieNode currNode = this.rootNode;

            for (int i = 0; i < word.length(); i++) {
                char charInWord = word.charAt(i);
                TrieNode nodeAboutWord = currNode.getChildNodes().get(charInWord);
                if(nodeAboutWord == null){
                    return 0;
                }
                currNode = nodeAboutWord;
            }
            return currNode.getStrCnt();
        }
    }

    static class TrieNode{
        boolean isLastChar;
        Map<Character, TrieNode> childNodes = new HashMap<>();
        int strCnt = 0;

        Map<Character, TrieNode> getChildNodes(){
            return this.childNodes;
        }

        boolean isLastChar(){
            return this.isLastChar;
        }

        void setLastChar(boolean isLastChar){
            this.isLastChar = isLastChar;
        }

        void increaseCnt(){
            this.strCnt++;
        }

        int getStrCnt(){
            return this.strCnt;
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
