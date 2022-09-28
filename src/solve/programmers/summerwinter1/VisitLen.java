package solve.programmers.summerwinter1;

public class VisitLen {
    static boolean[][][] visited;

    static class Point{
        int r, c;

        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static Point selectDirection(char d){
        switch(d){
            case 'L':
                return new Point(0, -1);
            case 'R':
                return new Point(0, 1);
            case 'U':
                return new Point(-1, 0);
            case 'D':
                return new Point(1, 0);
            default:
                return null;
        }
    }

    public static int indegreeDir(char d){
        switch(d){
            case 'L':
                return 0;
            case 'R':
                return 1;
            case 'U':
                return 2;
            case 'D':
                return 3;
            default:
                return -1;
        }
    }

    public static int outDir(char d){
        switch(d){
            case'L':
                return 1;
            case'R':
                return 0;
            case'U':
                return 3;
            case'D':
                return 2;
            default:
                return -1;
        }
    }

    static boolean innerGraph(int r, int c){
        return r >= 0 && r < 11 && c >= 0 && c < 11;
    }
    public static int solution(String dirs) {
        int answer = 0;
        int r = 5;
        int c = 5;
        visited = new boolean[11][11][4];

        for(int i=0; i<dirs.length(); i++){
            Point adjPoint = selectDirection(dirs.charAt(i));

            int nr = r + adjPoint.r;
            int nc = c + adjPoint.c;
            int indegree = indegreeDir(dirs.charAt(i));
            int out = outDir(dirs.charAt(i));

            if(innerGraph(nr, nc)){
                if(!visited[nr][nc][indegree] && !visited[r][c][out]){
                    visited[nr][nc][indegree] = true;
                    visited[r][c][out] = true;
                    answer++;
                }
                r = nr;
                c = nc;
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution("ULURRDLLURRDLLU"));

    }
}
