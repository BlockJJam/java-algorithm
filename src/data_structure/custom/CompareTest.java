package data_structure.custom;

import java.util.*;

public class CompareTest {

    static class Player { //implements Comparable {
        private int ranking;
        private String name;
        private int age;

//        @Override
//        public int compareTo(Object o) {
//            Player other = (Player) o;
//            return Integer.compare(getRanking(), other.getRanking());
//        }

        // constructor, getters, setters
        public Player(int ranking, String name, int age) {
            this.ranking = ranking;
            this.name = name;
            this.age = age;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "ranking=" + ranking +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    static class PlayerAgeComparator implements Comparator<Player>{
        @Override
        public int compare(Player player1, Player player2) {
            return Integer.compare(player1.getAge(), player2.getAge());
        }
    }

    public static void main(String[] args) {
        List<Player> footballTeam = new ArrayList<>();
        Player player1 = new Player(59, "John", 20);
        Player player2 = new Player(67, "Roger", 22);
        Player player5 = new Player(72, "Roger", 22);
        Player player3 = new Player(69, "Roger", 22);
        Player player4 = new Player(45, "Steven", 24);
        footballTeam.add(player1);
        footballTeam.add(player2);
        footballTeam.add(player3);
        footballTeam.add(player4);
        footballTeam.add(player5);


        System.out.println("Before Sorting : " + footballTeam);
//        Collections.sort(footballTeam);
        PlayerAgeComparator playerAgeComparator = new PlayerAgeComparator();
        Collections.sort(footballTeam, playerAgeComparator);
        Collections.sort(footballTeam, (Player p1, Player p2) -> Integer.compare(p1.getRanking(), p2.getRanking()));

        // ArrayList의 정렬을 다중 조건으로 처리할 때!
        footballTeam.sort(Comparator.comparing(Player::getName, Comparator.naturalOrder())
                .thenComparing(Player::getRanking, Comparator.reverseOrder())
        );
        System.out.println("After Sorting : " + footballTeam);
    }
}
