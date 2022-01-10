package data_structure;

import java.util.LinkedList;
import java.util.Queue;

public class Queues {
    public static void main(String[] args) {
        /** Queue **/
        // Priority Queue의 경우, insert 시에, 이진 트리 구조에 맞게 우선순위 기준 순으로 데이터를 정렬하여 저장하지만
        // Queue의 경우는 정렬하지 않고, 들어간 순서로 선입선출 방식이다
        /**    활용할 함수
         *     boolean add(E var1);
         *     boolean offer(E var1);
         *     E remove();
         *     E poll();
         *     E element();
         *     E peek();
         */
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        q.add(1);
        q.offer(3);
        q.offer(-1);
        q.offer(2);

        System.out.println("peeked value= " + q.peek().toString());
        System.out.println("element value = "+q.element().toString()); // 첫번째 값을 리턴(제거 x)

        System.out.println("removed value = " +q.remove().toString());
        while(!q.isEmpty()){
            System.out.println(" polled value = " + q.poll().toString());
        }

        q.clear();
    }
}
