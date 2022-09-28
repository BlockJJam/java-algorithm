package solve.programmers.monthly_challenge1;

public class BinaryConvertion {
    public static int[] solution(String s) {
        int[] answer = {};

        int diff = 0;
        int cnt = 0;
        int diffSum = 0;
        while(s.length() != 1){
            // 0 제거 diff 횟수 증가
            diff = s.length();
            s = s.replace("0", "");

            // int 제거된 문자열길이
            diff -= s.length();
            s = Integer.toBinaryString(s.length());

            // 길이 -> 문자열
            cnt++;
            diffSum += diff;

        }

        answer = new int[2];
        answer[0] = cnt;
        answer[1] = diffSum;

        return answer;
    }

    public static void main(String[] args) {
        solution("110010101001");
    }
}
