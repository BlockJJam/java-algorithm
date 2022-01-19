package algorithm;

import java.util.ArrayList;
import java.util.Collections;


/**
 * ## 특징
 *
 * - 매 선택에서 내가 원하는 가장 가까운 답으로 향할 것
 * - 백트래킹 하지 말고(시간아깝게..) 현재 조건에서 다른 선택의 경우는 검증하지 않는다
 * - 단, 언제나 전체 경우에서 최적값(=정답)을 구할 수 없다
 *
 * - 다음 2가지를 만족해야 된다
 *     1. 탐욕 선택 속성: 이전의 선택이 이후에 영향을 주지 않는다
 *     2. 최적 부분 구조: 부분 문제 최적 결과과, 전체 흐름에 그대로 적용
 */
public class Greedy {

    public static void main(String[] args) {
        ArrayList<Action> list = new ArrayList<>();
        list.add(new Action("A",7, 8));
        list.add(new Action("A", 7, 8));
        list.add(new Action("B", 5, 7));
        list.add(new Action("C", 3, 6));
        list.add(new Action("D", 1, 2));
        list.add(new Action("E", 6, 9));
        list.add(new Action("F", 10, 11));
        Collections.sort(list);

        ArrayList<Action> ans = greedy(list);
        for(Action action: ans){
            System.out.print(action.name + " ");
        }
    }

    private static ArrayList<Action> greedy(ArrayList<Action> list) {
        int time = 0;
        ArrayList<Action> ans = new ArrayList<>();

        for (Action action : list) {
            if (time <= action.startTime) {
                time = action.endTime;
                ans.add(action);
            }
        }
        return ans;
    }
}

class Action implements Comparable<Action>{
    String name;
    int startTime;
    int endTime;

    public Action(String name, int startTime, int endTime){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Action action) {
        return this.endTime - action.endTime;
    }

    @Override
    public String toString(){
        return this.name;
    }
}