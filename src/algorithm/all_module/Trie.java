package algorithm.all_module;

import java.util.HashMap;
import java.util.Map;

public class Trie {
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

    boolean contains(String word){
        TrieNode currNode = this.rootNode;

        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            TrieNode nodeAboutWord = currNode.getChildNodes().get(c);

            if(nodeAboutWord == null)
                return false;

            currNode = nodeAboutWord;
        }

        return currNode.isLastChar();
    }

    boolean delete(String word){
        return delete(this.rootNode, word, 0);
    }

    boolean delete(TrieNode currNode, String word, int index){
        char c = word.charAt(index);

        if (!currNode.getChildNodes().containsKey(c)) {
            return false;
        }

        TrieNode childNode = currNode.getChildNodes().get(c);
        ++index;

        if(index == word.length()){
            if(!childNode.isLastChar()){
                return false;
            }
            childNode.setLastChar(false);

            if(childNode.getChildNodes().isEmpty()){
                currNode.getChildNodes().remove(c);
            }
            return true;
        }else{
            boolean deleted = delete(childNode, word, index);

            if(!childNode.isLastChar && childNode.getChildNodes().isEmpty()){
                currNode.getChildNodes().remove(c);
            }

            return deleted;
        }
    }

    static class TrieNode{
        private Map<Character, TrieNode> childNodes = new HashMap<>();
        private boolean isLastChar;

        public Map<Character, TrieNode> getChildNodes() {
            return childNodes;
        }

        public boolean isLastChar() {
            return isLastChar;
        }

        public void setLastChar(boolean lastChar) {
            isLastChar = lastChar;
        }
    }
}
