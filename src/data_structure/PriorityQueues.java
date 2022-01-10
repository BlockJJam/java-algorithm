package data_structure;

import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueues {
    public static void main(String[] args) {
        /** Priority Queue 활용 **/
        // 높은 우선 순위 요소를 먼저 꺼내서 처리하는 구조 -> 값에 대한 비교 기준이 있어야 된다
        // 힙 구성으로 "이진 트리"구조로 이루어짐

        // Integer & String의 우선순위 높은 순과 낮은 순의 선언부터
        PriorityQueue<Integer> pqIntDesc = new PriorityQueue<>(); // Desc
        PriorityQueue<Integer> pqIntAsc = new PriorityQueue<>(Collections.reverseOrder()); // Asc
        PriorityQueue<String> pqStrDesc =  new PriorityQueue<>();
        PriorityQueue<String> pqStrAsc = new PriorityQueue<>(Collections.reverseOrder());

        // add element
        pqIntDesc.add(0);
        pqIntDesc.add(1);
        pqIntDesc.offer(3);
        pqIntDesc.offer(2);

        // get element
        Integer value =pqIntDesc.peek(); // 제일 첫번째 값 참조만(제거 x)
        System.out.println("value = " + value);

        while(!pqIntDesc.isEmpty()){
            //value = pqIntDesc.poll(); // 제일 첫번째 값을 꺼내고 제거
            System.out.println("value = " + value);
            break;
        }

        System.out.println(pqIntDesc.remove(0));
        System.out.println("pqIntDesc = " + pqIntDesc);

    }
}
