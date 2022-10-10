package solve.programmers.kakaoblind2021;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 순위 탐색
 * - info의 경우, 각 항목 조건 4가지를 " "로 구분했기 때문에, 점수를 담는 리스트를 값으로 Map에 저장
 * - 단, 모든 조건에 -인 경우를 생각해서, key value를 저장한다.
 * - 모든 map의 값인 리스트를 sort한다.
 * - query에서 " and "를 모두 제거하고, map에 저장된 해당 키의 리스트에서 query의 점수보다 높은 점수를 갖는 원소갯수를 구한다.
 * - 위에서 찾는 과정을 binary search로 탐색한다.
 */
public class RankSearch {

    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];


        // info에서 후보군을 " "으로 나누어, Map에 String, List<Integer>로 저장
        for(int i = 0; i< info.length; i++){
            String[] splited = info[i].split(" ");
            makeSentence(splited, "", 0);
        }

        // map에 저장된 info 후보군의 점수 리스트들을 모두 sorting한다.
        for(String str : map.keySet()){
            Collections.sort(map.get(str));
        }

        // query의 조건 키값과 점수를 나누어, map에서 해당 조건 키값의 점수 리스트를 불러와 이진 탐색으로 점수 이상의 지원자를 찾는다.
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
