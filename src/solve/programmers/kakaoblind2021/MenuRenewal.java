package solve.programmers.kakaoblind2021;

import java.util.*;

public class MenuRenewal {

    static boolean[] visited;
    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        List<String> courseList = new ArrayList<>();
        for(int i=0; i<course.length; i++){
            HashMap<String, Integer> map = new HashMap<>(); // course 별 갯수를 저장한다.
            // Course에 대한 주문 갯수를 desc로 저장하는 우선순위큐를 사용
            PriorityQueue<Course> pq = new PriorityQueue<Course>((o1, o2)-> Integer.compare(o2.orderCnt, o1.orderCnt));
            int len = course[i]; // course에 담을 음식 갯수

            for(int j = 0; j< orders.length; j++){
                if(orders[j].length() >= len){
                    visited = new boolean[orders[j].length()];

                    // order 음식에서 순열을 뽑아내, course로 가능한 경우를 map에 담아놓는다.
                    dfs(len,"", orders[j], map, 0);
                }
            }

            for(Map.Entry<String, Integer> entry: map.entrySet()){
                // map에서 음식 종류와 order한 횟수를 pq에 담아 놓는다.
                Course c = new Course(entry.getKey(), entry.getValue());
                pq.offer(c);
            }

            if(pq.size() == 0 ) // pq가 비어있으면, course로 쓸만한 애가 없음
                break;

            int maxCnt = pq.peek().orderCnt; // pq에서 가장 주문횟수가 많은 경우를 기준으로 삼는다.
            while(!pq.isEmpty()){
                Course c= pq.poll();
                String currMenu = c.menu;
                int currCnt = c.orderCnt;
//                System.out.println("currMenu = " + currMenu + " currCnt: " + currCnt);
                if(maxCnt > currCnt || maxCnt == 1){ // 주문횟수가 1이면, course불가능하고, maxCnt보다 작은 횟수의 코스요리 후보는 버린다.
                    break;
                }
                courseList.add(currMenu);
            }
        }

        answer = new String[courseList.size()];
        for(int i = 0; i<courseList.size(); i++){
            answer[i] = courseList.get(i);
        }

        Arrays.sort(answer);

        return answer;
    }


    static void dfs(int r, String str, String order, HashMap<String, Integer> map, int start){
        if(r == str.length()){
//            System.out.println("str = " + str);
            // course길이가 되었을 때, 해당 str이 map에 있는 지 확인해서, map에 해당 order개수를 채운다.
            str = str.chars()
                    .sorted()
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            if(map.get(str) == null){
                map.put(str, 1);
            }else{
                int next = map.get(str) + 1;
//                System.out.println("next = " + next);
                map.put(str, next);
            }
            return;
        }

        // 순열을 이용하여, 모든 course 경우의 수를 만들어본다.
        for(int i=start; i<order.length(); i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(r, str+order.charAt(i), order, map, i + 1);
                visited[i] = false;
            }
        }
    }

    static class Course{
        String menu;
        int orderCnt;

        public Course(String menu, int orderCnt) {
            this.menu = menu;
            this.orderCnt = orderCnt;
        }
    }

    public static void main(String[] args) {
        solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4});
    }
}
