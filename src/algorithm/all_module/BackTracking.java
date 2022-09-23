package algorithm.all_module;

public class BackTracking {
    /**
     * 해를 찾는 도중 해가 아니어서 막히면, 되돌아가서 해를 찾아가는 기법
     * - 가지치기를 얼마나 잘하느냐에 따라 효율 증가
     *
     *
     */

    static int N = 4;
    static int[][] board = new int[4][4];

    public static void main(String[] args) {
        if(!putQueen(0)){
            System.out.println("sol not exist!");
        }else{
            System.out.println("sol exist");
        }
    }

    private static boolean putQueen(int col){
        if(col == N) return true;

        for (int i = 0; i < N; i++) {
            if(ableLocationQueen(i, col)){
                board[i][col] = 1;

                if(putQueen(col+1)){
                    return true;
                }
                board[i][col] = 0;
            }
        }

        return false;
    }

    private static boolean ableLocationQueen(int row, int col){
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
