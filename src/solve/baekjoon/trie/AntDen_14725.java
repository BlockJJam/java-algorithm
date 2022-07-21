package solve.baekjoon.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AntDen_14725 {
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int N = rd.nextInt();
        Trie trie = new Trie();

        for(int i=0; i<N; i++){
            List<String> strList = new ArrayList<>();
            int K = rd.nextInt();

            for(int j=0; j<K; j++){
                String inputStr = rd.next();
                strList.add(inputStr);
            }

            trie.insert(strList);
        }

        trie.printFood();
    }

    static class Trie{
        private TrieNode rootNode;

        Trie(){
            this.rootNode = new TrieNode();
        }

        void insert(List<String> strList){
            TrieNode currNode = this.rootNode;

            for(String target : strList){
                currNode = currNode.getChildNodes().computeIfAbsent(target, str -> new TrieNode());
            }
            currNode.setLastChar(true);
        }
        void printFood(){
            printFood("", rootNode);
        }

        void printFood(String prefix, TrieNode currNode){
            if(!currNode.getChildNodes().isEmpty()){
                for(String key: currNode.getChildNodes().keySet()){
                    System.out.println(prefix + key);

                    TrieNode childNode = currNode.getChildNodes().get(key);

                    if(!childNode.getChildNodes().isEmpty()){
                        printFood(prefix + "--", childNode);
                    }
                }
            }
        }
    }

    static class TrieNode{
        private Map<String, TrieNode> childNodes = new TreeMap<>();
        private boolean isLastChar;

        Map<String, TrieNode> getChildNodes(){
            return this.childNodes;
        }

        void setLastChar(boolean isLastChar){
            this.isLastChar = isLastChar;
        }

        boolean isLastChar(){
            return this.isLastChar;
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
