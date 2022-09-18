package solve.programmers.summerwinter;

import java.util.Arrays;

public class SkillTree {
    public static int solution(String skill, String[] skill_trees) {
        int answer = -1;
        answer = 0;

        boolean[] noLearned = new boolean[26]; // 선행 스킬을 아직 안 배웠나? true: 안 배웠다, false: 배울 필요 없다.
        int[] parents = new int[26]; // skill의 각 위치에서 바로 앞의 문자에 대한 리스트

        // -1인 원소는 skill과 관련이 없고, 이 외에는 skill의 앞 문자를 담아준다
        Arrays.fill(parents, -1);
        for (int i = 0; i < skill.length(); i++) {
            int target = skill.charAt(i) -'A';
            if(i == 0){
                // 선행스킬 첫번째임을 나타내기 위한 값으로 100을 선택
                parents[target] = 100;
                noLearned[target] = true;
            }else{
                parents[target] = skill.charAt(i-1) - 'A';
                noLearned[target] =true;
            }
        }

        for(int i=0; i<skill_trees.length; i++){
            boolean[] noLearnedCopy = noLearned.clone();
            String customedSkill = skill_trees[i];

            // skill_trees를 순회하면서 선행스킬을 확인한다.
            for(int j=0; j<customedSkill.length();j++){
                int target = customedSkill.charAt(j) - 'A';
                int parent = parents[target];

                if(parent == 100) // 선행스킬 첫번째 문자라면 그냥 선행스킬 배움만 처리
                    noLearnedCopy[target] = false;
                else if(parent != -1){
                    // 선행스킬 여부를 알아야 한다면, 부모 선행스킬을 배웠는지 판단 후에 해당 스킬을 배워도 된다는 표시를 한다.
                    if(noLearnedCopy[parent]){
                        break;
                    }
                    noLearnedCopy[target] = false;
                }

                if(j == customedSkill.length() -1){
                    // 만약 마지막 커스텀 스킬까지 처리했다면 정답은 하나씩 늘린다.
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"});
    }
}
