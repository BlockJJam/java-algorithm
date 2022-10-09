package solve.programmers.kakaoblind2021;

import java.util.*;

public class MenuRenewal {

    static boolean[] visited;
    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};


        List<String> courseList = new ArrayList<>();
        for(int i=0; i<course.length; i++){
            HashMap<String, Integer> map = new HashMap<>();
            PriorityQueue<Course> pq = new PriorityQueue<Course>((o1, o2)-> Integer.compare(o2.orderCnt, o1.orderCnt));

            int len = course[i];

            for(int j = 0; j< orders.length; j++){
                if(orders[j].length() >= len){
                    visited = new boolean[orders[j].length()];
                    dfs(len,"", orders[j], map, 0);
                }
            }

            for(Map.Entry<String, Integer> entry: map.entrySet()){
                Course c = new Course(entry.getKey(), entry.getValue());
                pq.offer(c);
            }

            if(pq.size() == 0 )
                break;

            int maxCnt = pq.peek().orderCnt;
            while(!pq.isEmpty()){
                Course c= pq.poll();
                String currMenu = c.menu;
                int currCnt = c.orderCnt;
//                System.out.println("currMenu = " + currMenu + " currCnt: " + currCnt);
                if(maxCnt > currCnt || maxCnt == 1){
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
