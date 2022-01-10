package data_structure;

import java.util.*;

public class Maps {
    public static void main(String[] args) {
        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, String> treeMap = new TreeMap<>();
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        Map<Integer, String> weakHashMap = new WeakHashMap<>(); // HashMap과의 차이점이 강한 참조냐 약한 참조냐 밖에 없기 때문에 활용 X(가비지 컬랙터에 의해 제거대상이 되는지 여부)

        /** HashMap **/
        hashMap.put(0,"jjm");
        hashMap.put(2, "kks");
        hashMap.put(-1, "bdh");

        System.out.println(hashMap.get(0));
        System.out.println(hashMap.get(1)); // 없는 key의 경우 -> null을 리턴
        System.out.println("if no get value return default(='give up'): "+ hashMap.getOrDefault(1,"give up"));

        if(hashMap.containsKey(0))
            System.out.println("key가 있음");
        else
            System.out.println("key없음");

        if(hashMap.containsValue("jjm2"))
            System.out.println("value가 있음");
        else
            System.out.println("value 없음");

        for(Map.Entry<Integer, String> entry: hashMap.entrySet())
            System.out.printf("[key]: %d, [value]: %s \n",entry.getKey(), entry.getValue());

        for(String value: hashMap.values())
            System.out.println("value: "+value);

        for(Integer key: hashMap.keySet())
            System.out.println("key: "+ key);

        hashMap.remove(0);
        System.out.println("hashMap = " + hashMap);
        if(hashMap.remove(0,"jjm"))
            System.out.println("remove key 0");
        else
            System.out.println("no remove");

        // HashMap의 putAll은 사용하지 말 것 -> merge 사용
        Map<Integer, String> hashMap2 = new HashMap<>();
        hashMap2.put(1000,"apple");
        hashMap2.put(2000, "banana");
        hashMap2.forEach((key, value)-> hashMap.merge(key, value,(v1,v2)-> v2));
        //hashMap2.forEach((key, value)-> hashMap.merge(key, value,(v1,v2)-> v1 + v2)); 다양한 병합 방법을 구현 가능하기 때문에 forEach + merge 사용
        System.out.println("hashMap = " + hashMap);

        if(hashMap.replace(-1,"bdh", "bdh real")) // replace(key, newValue)는 바뀌면 newValue 리턴, 안바뀌면 null 리턴
            System.out.println("bdh -> bdh real");
        else
            System.out.println("no contains key: -1, value: bd");

        hashMap.replaceAll((key, value)-> { // BiFunction<String, Integer, Integer>의 apply적용을 람다로
            if(key > 100) {
                value = value + "!!";
                return value; // 바뀐 value를 return하여 해당 key의 value를 replace
            }
            return value;
        });
        System.out.println("hashMap = " + hashMap);

        // HashMap의 compute는 해당 key에 대한 value를 어떻게 처리(계산)할 지 정의한다 -> 키에 대한 값이 없으면, null이 반환( 하지만 value + String을 하면 값이 생성됨 ㅋㅋ)
        // IfAbsent는 key에 대한 value가 없어야지만, Function 람다가 실행 -> map에 {key,value}를 생성해준다
        // IfPresent는 key가 존재해야만 람다가 발동된다
        System.out.println(hashMap.compute(0, (key,value) -> value));
        hashMap.computeIfAbsent(0, key -> "good");
        hashMap.computeIfAbsent(1000, key -> "apple?");
        hashMap.computeIfPresent(100000, (key,value)-> value+"!!!!");
        hashMap.computeIfPresent(1000, (key,value)-> value+"!!!!");


        System.out.println("hashMap = " + hashMap);

        /** TreeMap **/
        // TreeMap은 Tree 구조이기 때문에, 저장시에 자동 정렬된다 -> TreeSet은 값자체로 정렬되지만, TreeMap은 키를 통해 오름차순으로 저장된다
        // 정렬된 상태를 유지해야 하거나, 범위 검색, 정렬된 데이터를 조회해야 할 때 유용하다( 하지만 이러한 이유 외에는 HashMap이 성능우위)

        // TreeMap 제공 기능이 HashMap이랑 거의 같아서 HashMap에서 정렬이 필요한 경우에 TreeMap을 활용해보자
        treeMap = new TreeMap<>(hashMap);
        System.out.println("treeMap = " + treeMap);

        // value를 오름차순으로 정렬하는 방법(람다 활용법) -> Map.Entry활용
        List<Map.Entry<Integer, String>> entryList = new LinkedList<>(hashMap.entrySet());
        Collections.sort(entryList, (o1,o2)-> hashMap.get(o1.getKey()).compareTo(hashMap.get(o2.getKey())));
        System.out.println("entryList = " + entryList);

        /** LinkedHashMap **/
        // HashMap의 확장 버전으로, 키-값 구조에 전체크기는 알지 못하지만, 순서를 알아야 하는 경우에 HashMap보다는 LinkedHashMap을 사용하자
        // 검색을 활용할 수 있으면서 순서대로 조회할 필요도 있을 때 좋다, 하지만 자료의 크기가 너무 방대하면 메모리 성능이 급격히 올라간다
        // LinkedHashMap과 HashMap 성능 이슈가 크지 않은 것으로 보아, LinkedHashMap도 고려해볼만 할 것 같
        linkedHashMap.put(0, "first");
        linkedHashMap.put(100, "second");
        linkedHashMap.put(-1, "third");
        linkedHashMap.put(3, "fourth");

        for( Integer key: linkedHashMap.keySet())
            System.out.println("key = " + key);
    }
}
