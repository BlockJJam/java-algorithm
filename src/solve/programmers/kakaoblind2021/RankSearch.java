package solve.programmers.kakaoblind2021;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RankSearch {

    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];


        // info에서 후보군을 " "으로 나누어, Map에 String, List<Integer>로 저장
        for(int i = 0; i< info.length; i++){
            String[] splited = info[i].split(" ");
            makeSentence(splited, "", 0);
        }

        for(String str : map.keySet()){
            Collections.sort(map.get(str));
        }

        for(int i =0; i<query.length; i++){
            query[i] = query[i].replaceAll(" and ", "");
            String[] q = query[i].split(" ");
//            System.out.println("q[0] = " + q[0] + " q[1] = " + q[1]);

            answer[i] = map.containsKey(q[0]) ? binarySearch(q[0], Integer.parseInt(q[1])) : 0;
//            System.out.println("answer = " + answer[i]);
        }

        return answer;
    }

    static int binarySearch(String key, int score){
        ArrayList<Integer> list = map.get(key);

        int l = 0, r = list.size() -1;

        int mid;
        while(l < r){
            mid = (l + r) / 2;
            if(list.get(mid) < score){
                l = mid +1;
            }else{
                r = mid;
            }
        }
        return list.size() - l;
    }

    static void makeSentence(String[] p, String str, int cnt){
        if(cnt == 4){
            if(!map.containsKey(str)){
                ArrayList<Integer> list = new ArrayList<>();
                map.put(str, list);
            }
//            System.out.println("str = " + str+ " "+ p[4]);
            map.get(str).add(Integer.parseInt(p[4]));
            return;
        }

        makeSentence(p, str+"-", cnt+1);
        makeSentence(p, str+p[cnt], cnt+1);

    }

    public static void main(String[] args) {
        solution(new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
    }
}
