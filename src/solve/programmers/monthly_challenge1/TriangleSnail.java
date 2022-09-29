package solve.programmers.monthly_challenge1;

public class TriangleSnail {
    public int[] solution(int n) {
        int[] answer = {};

        int[][] graph = new int[n][n];

        int r = -1;
        int c = 0;
        int num = 1;
        // 왼쪽 아래 대각선 - 오른쪽 - 왼쪽 위 대각서
        for(int i =0; i<n; i++){
            for(int j = i; j<n; j++){
                if(i % 3== 0){
                    r++;
                }else if(i % 3 == 1){
                    c++;
                }else{
                    r--;
                    c--;
                }
                graph[r][c] = num++;
            }
        }

        answer = new int[n*(n+1)/2];
        int idx = 0;
        for(int i=0; i<n; i++){
            for(int j = 0; j <=i; j++){
                answer[idx++] = graph[i][j];
            }
        }

        return answer;
    }
}
