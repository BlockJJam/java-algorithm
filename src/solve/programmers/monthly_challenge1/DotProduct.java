package solve.programmers.monthly_challenge1;

public class DotProduct {
    public int solution(int[] a, int[] b) {
        int answer = 1234567890;

        for(int i=0; i<a.length; i++){
            answer = i == 0? a[0]*b[0] : answer + a[i]*b[i];
        }

        return answer;
    }
}
