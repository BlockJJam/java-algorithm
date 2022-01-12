package data_structure;

import java.util.Deque;
import java.util.LinkedList;

public class Deques {
    public static void main(String[] args) {
        /** Deque 활용(Deque, Vector, List활용에 대해 **/
        // Deque에 대한 설명
        // - double-end queue로 간단히는 앞뒤로 In&Out이 가능하다
        // - Stack처럼도, Queue처럼도 사용이 가능
        // - 구현체: ArrayDeque, LinkedBlockingDeque, ConcurrentLinkedDeque, LinkedList
        // - 구현 메서드: addFirst(), offerFirst(), addLast(), add(), offerLast()(용량여부에 따른 boolean처리, removeFirst(), pollFirst()(비면 null), removeLast(), pollLast(), remove(), poll()
        // - (이어서) : getFirst(), peekFirst(), getLast(), peekLast(), peek(), removeFirst(Last)Occurrence( obj ), element()(=removeFirst()), addAll(Collections)
        // - (이어사) : push()(=addFirst()), contain(), size(), iterator(), descendingIterator()(뒤순서부터 순차적인 iterator)

        /** vector perform **/
        // 접근: O(N)
        // 끝에 삽입/제거: O(n)-아모타이즈드
        // 순서 상관없이 원소 순환: O(logN)
        // 단점: 컨테이너 끝이 아닌 곳에서 삽입/제거 시 성능 저하
        // 주의: 동적으로 컨테이너 크기 확장/축소는 편하나, 확장시에 reallocation은 비용이 크다, 메모리 확보가 중요

        /** deque **/
        // 개별 원소들을 position index 접근 가능: O(N)
        // 앞에서의 삽입/삭제도 빠름
        // 마찬가지로 어느 순서로도 순회 가능
        // 단점: (list에 비해) 중간 위치에서의 삽입/삭제 성능이 떨어짐
        // 단점: 연속 메모리 공간이 아니라는 점에서, 원소들간의 포인터 연산이 불가능

        /** list **/
        // doubly linked list(앞뒤로 연결되어있다는 의미겠지)
        // 어느 위치에서도 삽입/제거 가능: O(N)
        // 원소 간의 순서 이동이 자유로움: O(N)
        // 제일 강점: 어느 위치에서도 원소의 삽입/제거가 빠름
        // 단점: position index로 직접 접근이 불가능 -> 특정 접근에도 선형 탐색이 필요
        // 단점: 원소 순회 자체가 선형복잡도
        // 단점: 기본적인 비용이 지출됨 -> 노드간의 연결정보를 위해 추가적인 메모리 사용
        Deque<String> deque = new LinkedList<>();
    }

}
