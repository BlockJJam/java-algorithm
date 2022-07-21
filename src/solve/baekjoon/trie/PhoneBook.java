package solve.baekjoon.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PhoneBook {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int T = rd.nextInt();
        while(T-- != 0){
            int N = rd.nextInt();
            Trie trie = new Trie();
            List<String> compareStrs = new ArrayList<>();

            for(int i=0; i<N; i++){
                String inputStr = rd.next();
                trie.insert(inputStr);
                compareStrs.add(inputStr);
            }

            boolean isConsistecy = true;
            for(String target: compareStrs){
                if(!trie.isConsistency(target)){
                    isConsistecy = false;
                    break;
                }
            }
            System.out.println(isConsistecy? "YES" : "NO");
        }
    }

    static class Trie{
        private TrieNode rootNode;

        Trie(){
            this.rootNode = new TrieNode();
        }

        void insert(String word){
            TrieNode currNode = this.rootNode;

            for(int i=0; i<word.length(); i++){
                currNode = currNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
            }
            currNode.setLastChar(true);
        }

        boolean isConsistency(String word){
            TrieNode currNode = this.rootNode;

            for(int i=0; i<word.length(); i++){
                char charInWord = word.charAt(i);
                TrieNode nodeAboutWord = currNode.getChildNodes().get(charInWord);

                if(nodeAboutWord == null) break;
                if(i != (word.length() -1) && nodeAboutWord.isLastChar()){
                    return false;
                }
                currNode = nodeAboutWord;
            }
            return true;
        }

        boolean delete(String word){
            return delete(this.rootNode, word, 0);
        }

        boolean delete(TrieNode currNode, String word, int index){
            char charInWord = word.charAt(index);

            if(!currNode.getChildNodes().containsKey(charInWord)){
                return false;
            }

            TrieNode childNode = currNode.getChildNodes().get(charInWord);
            ++index;

            if(index == word.length()){
                if(!childNode.isLastChar()){
                    return false;
                }

                if(childNode.getChildNodes().isEmpty()){
                    currNode.getChildNodes().remove(charInWord);
                }
                return true;
            }else{
                boolean isDeleted = delete(childNode, word, index);

                if(!childNode.isLastChar() && childNode.getChildNodes().isEmpty()){
                    currNode.getChildNodes().remove(charInWord);
                }

                return isDeleted;
            }
        }
    }

    static class TrieNode{
        private Map<Character, TrieNode> childNodes = new HashMap<>();
        private boolean isLastChar = false;

        Map<Character, TrieNode> getChildNodes(){
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
