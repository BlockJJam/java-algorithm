package data_structure.custom;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie
 * TrieNode는 [자식노드맵]과 [현재 노드가 마지막 글자인지 여부] 에 대한 정보를 가진다
 * 언제 사용할까 : 여러 단어 사전입력이 들어왔을 때, 찾고자 하는 단어를 검색하여 해당 단어가 Trie에 있는지 알고 싶을 때 사용한다
 * 동작 방식    : 1) 단어들을 일단 채운다 -> TrieNode에 해당 키를 같은 노드가 있으면, 자식노드를 get하고
 *                  , 없을 때는 Node를 생성한뒤 get한다
 *                  , 문자의 끝 Node에 [마지막 글자 여부]를 체크한다
 *            : 2) 단어를 포함하고 있는지 체크한다 -> 입력받은 word를 [root노드]부터 첫번째 글자에 해당하는 Node가 있는지 타고 내려간다
 *                  , 내려가면서 [다음 글자의 노드]가 없으면 return false 해야한다
 *                  , 마지막 글자 Node에서 [마지막 글자 여부]를 return 한다
 *            : 3) 단어를 삭제할 수 있어야 한다(callback) -> 조건이 있다, [자식 노드]를 가지고 있지 않아야 한다, [삭제를 시작하는 노드]는 마지막 글자 여부가 true여야 한다
 *                  , 콜백 방식이기 때문에, 첫번째 글자부터 찾는 Node가 없으면 바로 Exception 처리한다
 *                  , 자식 Node로 가면서(index늘리면서 delete메서드 콜) index == word의 길이 만큼 내려간다
 *                  , index == word의 길이만큼 내려가면 (삭제 단어가 아니면 Error) 마지막 여부를 true -> false로 변경후, 자식 노드들이 비어있는지 보고 안비어있으면 삭제를 이어갈 수 없다(그대로 빠져나옴)
 *                  , 자식노드가 비어있으면 삭제를 하면서 delete를 빠져나오고 이후는 delete()를 빠져나온 이후의 코드들이 실행된다 -> // callback check <-라고 표시된 부분
 *                  , delete() 이후 코드는 해당 Node의 마지막 여부가 false여야 하고, 해당 Node의 자식 노드들 자체가 없어야만 자식 노드를 지우기가 가능하고 이에 해당 안되면 그대로 빠져나온다
 *
 *              ! 3)의 변경사항: 입력한 단어에 대한 Node가 없는 경우( 자식 노드가 없거나, 마지막 여부가 false)일 때, Error -> delete 메서드의 반환 타입을 boolean으로 변경해본다
 */
public class Trie {
    private TrieNode rootNode;
    Trie(){
        rootNode = new TrieNode();
    }

    // 동작 방식 1)
    void insert(String word){
        TrieNode currNode = this.rootNode;

        for(int i=0; i< word.length(); i++){
            currNode = currNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        currNode.setIsLastChar(true);
    }

    // 동작 방식 2)
    boolean contains(String word){
        TrieNode currNode = this.rootNode;

        for(int i=0; i< word.length(); i++){
            char charInWord = word.charAt(i);
            TrieNode nodeAboutWord = currNode.getChildNodes().get(charInWord);

            if(nodeAboutWord == null){
                return false;
            }

            currNode = nodeAboutWord;
        }

        return currNode.isLastChar();
    }

    // 동작 방식 3)
    boolean delete(String word){
        return delete(this.rootNode, word, 0);
    }

    private boolean delete(TrieNode currNode, String word, int index){
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
            childNode.setIsLastChar(false);

            // 해당 자식 노드가 가진 자식 노드들이 없으면, 지금 노드에서 해당 자식 노드 자체를 없앤다
            if(childNode.getChildNodes().isEmpty()){
                currNode.getChildNodes().remove(charInWord);
            }
            return true;
        }else{
            boolean isDeleted = delete(childNode, word, index); // call back!

            if(!childNode.isLastChar() && childNode.getChildNodes().isEmpty()){
                currNode.getChildNodes().remove(charInWord);
            }

            return isDeleted;
        }
    }
}


class TrieNode{
    // child node
    private Map<Character,TrieNode> childNodes = new HashMap<>();
    private boolean isLastChar;

    Map<Character, TrieNode> getChildNodes(){
        return this.childNodes;
    }

    boolean isLastChar(){
        return this.isLastChar;
    }

    void setIsLastChar(boolean isLastChar) {
        this.isLastChar = isLastChar;
    }
}