package solve.baekjoon.implement;

import java.io.*;
import java.util.StringTokenizer;

public class SharkElementarySchool_21608 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        SeatBuilder builder = new SeatBuilder(N);
        Student[] studentList = new Student[N*N];
        for(int i=0; i<N*N; i++){
            st = new StringTokenizer(br.readLine());
            Student s = new Student(Integer.parseInt(st.nextToken()), N);

            for(int j=0; j<4; j++){
                s.checkFavorite(Integer.parseInt(st.nextToken()));
            }
            studentList[i] = s;
        }

        for(int i=0; i<N*N; i++){
            builder.setSeat(studentList[i]);
        }

        int result = 0;
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++){
                result += builder.getFavoritePoint(j,i);
            }
        }

        bw.write(result +"\n");
        bw.flush();
        bw.close();
    }

    static class SeatBuilder{
        int [][] seat;
        Student[][] resultTable;
        int N;
        int[] adjX = {0, 1, 0, -1};
        int[] adjY = {-1, 0, 1, 0};

        public SeatBuilder(int n){
            seat = new int[n+1][n+1];
            resultTable = new Student[n+1][n+1];
            N = n;
        }


        public void setSeat(Student student){
            int fCnt=0, eCnt = 0;
            int targetF = 0, targetE =0, targetR = N*N+1, targetC = N*N+1;
            Point targetPoint = new Point();
            for(int i=1; i <=N; i++){
                for(int j=1; j <= N; j++){
                    if(seat[i][j] ==0){
                        for(int k =0; k <4; k++){
                            int nX = j + adjX[k];
                            int nY = i + adjY[k];
                            if(isRanged(nX,nY)){
                                fCnt = student.favorites[seat[nY][nX]] ? fCnt+1: fCnt;
                                eCnt = seat[nY][nX] == 0? eCnt +1: eCnt;

                            }
                        }
                        // 위 4가지 비교로 targetPoint를 만든다
                        if(targetF < fCnt){
                            targetF = fCnt;
                            targetE = eCnt;
                            targetR = i;
                            targetC = j;

                            targetPoint = new Point(j, i);
                        }else if(targetE < eCnt && targetF == fCnt){
                            targetE = eCnt;
                            targetR = i;
                            targetC = j;
                            targetPoint = new Point(j, i);
                        }else if(targetR > i && targetF == fCnt && targetE == eCnt){
                            targetR = i;
                            targetC = j;
                            targetPoint = new Point(j, i);
                        }else if(targetC > j && targetF == fCnt && targetE == eCnt && targetR == i ){
                            targetC = j;
                            targetPoint = new Point(j, i);
                        }
                        // 위 4가지 정보를 reset
                        fCnt = 0;
                        eCnt =0;
                    }
                }
            }
            int finalX = targetPoint.x;
            int finalY = targetPoint.y;
            seat[finalY][finalX] = student.id;
            resultTable[finalY][finalX] = student;
        }

        boolean isRanged(int x, int y){
            return x >=1 && x <= N && y >= 1 && y <= N;
        }

        int getFavoritePoint(int x, int y){
            int fCnt=0;
            for(int i=0; i<4; i++){
                int nX = x + adjX[i];
                int nY = y + adjY[i];
                Student target = resultTable[y][x];
                if (isRanged(nX, nY)) {
                    if(target.favorites[seat[nY][nX]]){
                        fCnt++;
                    }
                }
            }
            switch(fCnt){
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 10;
                case 3:
                    return 100;
                case 4:
                    return 1000;
                default:
                    return -1;
            }
        }
    }

    static class Point{
        int x, y;
        public Point(){
            this.x = 0;
            this.y = 0;
        }
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Student{
        int id;
        boolean[] favorites;

        public Student(int id, int n){
            this.id = id;
            favorites = new boolean[n*n+1];
        }

        public void checkFavorite(int friend){
            favorites[friend] = true;
        }
    }
}
