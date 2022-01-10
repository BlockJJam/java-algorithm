package data_structure;

import java.util.*;

public class Sets {
    public static void main(String[] args){
        /** Set **/
        // 당연한 사실, 데이터(객체포함)를 중복해서 저장할 수 없다
        // 저장된 데이터는 index로 관리하지 않기 때문에 저장순서를 보장하지 않음
        // HashSet, TreeSet, LinkedHashSet
        // HashSet: 입력 순서를 보장하지도, 정렬을 하지도 않기 때문에 빠르게 데이터를 삽입하고, 조회가능하다
        // TreeSet: 입력 순서를 보장하지는 않지만, 기본적으로는 오름차순으로 데이터를 삽입하여 정렬시킨다, order방식을 변경하여 선언할 수 있다. 데이터를 순회하는 Set을 사용할 때 유리
        // LinkedHashSet: 정렬을 하지 않지만, 입력 순서를 보장하므로(Linked 계열) 데이터를 입력한 순서대로 조회하는 Set을 사용하고 싶을 때 유리
        //   -> HashSet이 TreeSet이나 LinkedHashSet보다 삽입 속도는 빠르지만, 활용용도에 따라 알고리즘 성능을 높이는 Set 종류를 선택해야 한다
        Set<String> setA = new HashSet<>();
        setA.add("steam");
        setA.add("sting");
        setA.add("new");
        setA.add("sting");
        System.out.println("setA = " + setA);

        System.out.println(setA.contains(Arrays.asList("sting","Abcd")));
        if(setA.remove("Sting"))
            System.out.println("Sting이 지워졌습니다");
        else
            System.out.println("Sting이 지워지지 않았습니다");

        setA.addAll(Arrays.asList("joo","jae","min","jae"));
        System.out.println("setA = " + setA);

        Iterator<String> iterator = setA.iterator();
        while(iterator.hasNext()){
            System.out.println("iterator = " + iterator.next());
        }
        System.out.println("------------------------------------");
        // 이외 메서드들은 기본적으로 ArrayList에서 활용해봤으므로, 넘어감

        /** TreeSet **/
        // element가 정렬된 형태로 되어있는지, Stream을 써서 출력해보자
        Set<String> setTree = new TreeSet<>(setA);
        System.out.println("\n[TreeSet]");
        setTree.stream().forEach(System.out::println);

        /** LinkedHashSet **/
        // 입력한 순서대로 조회가 되는지 테스트 해보자
        Set<String> setLinked = new LinkedHashSet<>(Arrays.asList("String","Apple","string","Zero","Lover","Zero","apple","lover","zero"));
        System.out.println("\n[LinkedHashSet]");
        setLinked.stream().forEach(System.out::println);
        
        /** 커스텀 클래스 객체 테스트 **/
        // no equals and hashCode
        System.out.println("------------------------------------");
        Set<MembersNoEqualsAndHashCode> membersNoEqualsAndHashCodeSet = new HashSet<>();
        membersNoEqualsAndHashCodeSet.add(new MembersNoEqualsAndHashCode(1,"jaemin"));
        membersNoEqualsAndHashCodeSet.add(new MembersNoEqualsAndHashCode(1,"jaemin"));
        System.out.println("membersNoEqualsAndHashCodeSet = " + membersNoEqualsAndHashCodeSet);

        // contains equals and hashCode
        Set<MembersContainsEqualsAndHashCode> membersContainsEqualsAndHashCodesSet = new HashSet<>();
        membersContainsEqualsAndHashCodesSet.add(new MembersContainsEqualsAndHashCode(1,"minjae"));
        membersContainsEqualsAndHashCodesSet.add(new MembersContainsEqualsAndHashCode(1,"minjae"));
        System.out.println("membersContainsEqualsAndHashCodesSet = " + membersContainsEqualsAndHashCodesSet);
    }
    
    public static class MembersNoEqualsAndHashCode {
        private int id;
        private String name;

        public MembersNoEqualsAndHashCode(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "MembersNoEqualsAndHashCode{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        // 과연 equals와 hashCode 메서드 없어도 Set중복에 걸릴까?
    }

    public static class MembersContainsEqualsAndHashCode {
        private int id;
        private String name;

        public MembersContainsEqualsAndHashCode(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "MembersContainsEqualsAndHashCode{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MembersContainsEqualsAndHashCode that = (MembersContainsEqualsAndHashCode) o;
            return id == that.id && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}
