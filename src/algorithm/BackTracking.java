package algorithm;

/**
 * # 특징
 * - 재귀적으로 문제를 하나씩 풀어나간다
 * - 현재 재귀 상태에서 제한 조건에 위배되는지 판단
 *     - 위배되는 경우는 해당 상태를 제외시킨다 → 제외 시킨다는 것에도 집중해보자
 *     - 불필요한 비용을 낭비하지 않는것 → 가지치기
 * - 물론, DP(동적계획), greedy가 더 빠른 해결가능한 경우가 많음
 *     - 하지만, 백트래킹만으로 해결가능한 부분도 존재
 */
public class BackTracking {
    static int N = 4;
    static int[][] board = new int[4][4];

    public static void main(String[] args) {
        if(putQueen(0) == false){
            System.out.println("solution does not exist!");
        }else{
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    System.out.print(board[i][j]+ " ");
                }
                System.out.println();
            }
        }
    }

    // 재귀 + 반복을 통해 문제를 해결하는 메서드(각 컬럼에 대한 체크)
    // 성능: for문 1번으로 O(N)
    private static boolean putQueen(int col){
        // N보다 col이 높거나 같으면 모든 열에 배치가 마무리된 상태
        if(col >= N) return true;

        // 현재 열에서 각 행을 하나씩 체크
        for(int i=0; i<N; i++){

            // i번째 행에 퀸을 위치 시킬 수 있는지 파악
            if(ableLocateQueen(i,col) == true){
                board[i][col] = 1;

                // 위치 시켰다면, 이후 col에 대해서도 연속적으로 가능한지 확인
                if(putQueen(col+1) == true){
                    return true;
                }
                board[i][col] = 0;
            }
        }
        // true를 지금까지 반환 못했다는 것은 퀸을 퀸을 놓는 경우를 다 지나온 경우이므로, false 리턴
        return false;
    }

    private static boolean ableLocateQueen(int row, int col){
        int i,j;

        // 현재 행의 모든 열에 퀸이 있는지 체크
        for(i =0; i<col; i++){
            if(board[row][i] == 1){
                return false;
            }
        }

        // 현 위치에서 (지나온)좌상단 대각선 위치에 퀸이 있는지 체크
        for(i =row, j= col; i>= 0 && j >=0; i--, j--){
            if(board[i][j] == 1) {
                return false;
            }
        }

        // 위치가 가능한 것은 2개의 검사를 모두 아무 일 없이 통과했을 때
        return true;
    }
}
