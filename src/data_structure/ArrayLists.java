package data_structure;

import java.util.*;

public class ArrayLists {
    public static void main(String[] args) {

        /** ArrayList **/
        List<String> listA = new ArrayList<>();
        listA.add("A");
        listA.add("B");

        List<String> listB = Arrays.asList("1","2");
        //listA.addAll(listB);

        // list 특정 value로 가득 채우기
        listB = Collections.nCopies(listB.size(),"1");

        System.out.println(listB);
        System.out.println("listA contains 'C' = " + listA.contains("C"));
        System.out.println("listA contains 'C' = " + listA.isEmpty());

        System.out.println("listA contains listB = " + listA.containsAll(listB));
        System.out.println("listA's lastindex = " + listA.lastIndexOf("C"));
        System.out.println("listA's lastindex = " + listA.lastIndexOf("B"));
        listA.add("C");
        listA.add("D");
        System.out.println("listA removes = "+listA.remove(3));
        System.out.println("listA remove 'B' isSuccessed = "+ listA.remove("AB"));
        listA.replaceAll(e -> e.toLowerCase());
        System.out.println("listA = " + listA);
        listA.retainAll(List.of("a","b","c"));
        System.out.println("retainAlled listA = " + listA);

        /** LinkedList **/
        LinkedList<String> linkedList = new LinkedList();
        linkedList.add("Jin");
        linkedList.add("Min");
        linkedList.add("Sae");
        
        linkedList.addFirst("Lue");
        Iterator<String> iter = linkedList.iterator();
        Iterator<String> iterDesc = linkedList.descendingIterator();
        while(iter.hasNext())
            System.out.print(" " + iter.next());
        System.out.println();

        // 왜 같은 기능의 함수를 추가했을까? 2가지 이유가 있다고 본다
        // -> 다른 Collection에서 같은 기능에 서로 다른 예외처리가 필요한 경우가 존재, ArrayList의 add는 MAX_ARRAY_SIZE 영향을 받지만, LinkedList는 동적 배열이라 메모리 영향만 받는다
        // -> 기존 함수들은 실패시 Exception처리, 같은기능의 새로운 함수는 실패시 spection value(특정값)을 리턴

        Boolean offered = linkedList.offer("removed"); // == add(Throws exception)
        String polled = linkedList.poll(); // == remove(Throws exception) remove head
        String peeked = linkedList.peek(); // == element(Throws exception) the head element, no remove

        System.out.println("removed first find and remove? "+linkedList.removeFirstOccurrence("removed")); // 해당 원소를 찾아 제일 첫번째 원소를 지운다
        System.out.println("removed first find and remove? "+linkedList.removeFirstOccurrence("removed")); // 해당 원소를 찾아 제일 첫번째 원소를 지운다
        linkedList.addLast("Jin");
        System.out.println("Jin last find and remove?"+ linkedList.removeLastOccurrence("Jin"));
        System.out.println("Jinjja last find and remove?"+ linkedList.removeLastOccurrence("Jinjja"));
        linkedList.set(1,"int");
        System.out.println("linkedList = " + linkedList);

        /** ArrayList 와 LinkedList
         *  - ArrayList는 메모리에 순차적으로 적재되기 때문에, 요소를 접근하는 목적이나, 수정하는 용도로 좋으나 중간 요소를 삭제하거나 추가할 때는 연산에 대한 비용이 높으므로 적절하지 않음
         *  - LinkedList는 메모리에 랜덤하게 적재되지만 다음 순서의 next라는 메모리 위치를 갖기 때문에, 중간 요소를 삭제하거나 추가할 때 사용하기 적절하나, 순서대로 접근하거나 수정만 하기에는 비용이 많이 든다
         */


    }
}
