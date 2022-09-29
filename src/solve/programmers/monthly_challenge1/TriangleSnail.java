package solve.programmers.monthly_challenge1;

public class TriangleSnail {
    public static int solution(int[] a) {
        int answer = 0;

        if(a.length == 1)
            return 0;

        int[] intersections = new int[a.length];
        boolean[] hasBacked = new boolean[a.length];

        for(int i=0; i<a.length; i++){
            if (i == 0) {
                // 시작 지점
                if(a[i+1] != a[i]){
                    // 다음 값과 현재 값이 다르면
                    intersections[a[i]] ++;
                    hasBacked[i]= true;
                }
            } else if (i == 1) {
                // 시작 지점 바로 앞
                if(a[i-1] != a[i]){
                    intersections[a[i]]++;
                }else{
                    if(i != a.length-1){
                        // 시작 지점 바로 앞이 끝지점이 아니면
                        if(a[i+1] != a[i]){
                            // 다음 값과 현재 값이 다를 때
                            intersections[a[i]]++;
                            hasBacked[i] =true;
                        }
                    }
                }
            } else if(i == a.length-1){
                // 끝지점
                if(a[i-1] != a[i]){
                    // 이전전 값과 현재값이 같은데 뒤를 포함하는 값이면 i를 건너뛰자
                    if(a[i-2] == a[i] && hasBacked[i-2]) continue;
                    intersections[a[i]]++;
                }
            } else{
                // 중간 지점
                if(a[i-1] != a[i] && a[i-2] != a[i]){
                    // 이전값과 이전전값과 현재값이 모두 다를 때
                    intersections[a[i]]++;
                }else if(a[i-1] != a[i] && a[i-2] == a[i] && !hasBacked[i-2]){
                    // 이전전값과 현재값이 같지만, 뒤를 포함하지 않는 이전전값일 때
                    intersections[a[i]]++;
                } else if(a[i+1] != a[i]){
                    // 위 모두 해당되지 않으면, 다음 값을 교집합 가능한 집합에 포함시키고 표시를 남겨두자
                    intersections[a[i]]++;
                    hasBacked[i] =true;
                }
            }
        }

        for(int i=0; i<a.length; i++){
            // System.out.println("intersections = " + intersections[i]);
            answer =  Math.max( answer, intersections[i] * 2);
        }

        return answer;
    }

    public static void main(String[] args) {
        solution(new int[]{0, 1});
    }
}
