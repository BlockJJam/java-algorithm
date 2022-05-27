package solve.baekjoon.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

public class CoinGame_9079 {
    static int T;
    static int N;
    static int result;

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=3;
        char[][] graph = new char[N][N];
        T = Integer.parseInt(st.nextToken());

        for(int k=0; k<T; k++){

            result = 9;
            UsedLocation usedLocation = new UsedLocation();
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    graph[i][j] = st.nextToken().charAt(0);
                }
            }
            makeSameCoin(graph, usedLocation, Direction.NONE, -1, 0);
            if(result != 9) bw.write(result+"\n");
            else bw.write("-1 \n");
        }


        bw.flush();
        bw.close();
    }

    public static void makeSameCoin(char[][] graph, UsedLocation used, Direction direct, int idx, int next) throws CloneNotSupportedException {
        if(result <= next) {
            return;
        }

        // graph를 copyGraph로 복사 및 type idx로 뒤집기
        char[][] placedCoin = placeCoin(graph, direct, idx);

        // 모든 자리가 한쪽면인지 검사 및 끝 조건인지 검사
        int H=0, T=0;
        for(char[] graphRow : placedCoin){
            for(char target: graphRow){
                if(target == 'H') H++;
                else T++;
            }
        }
        if(H == 9 || T == 9 || next == 9){
            result = Math.min(next, result);
            return;
        }

        // type과 idx로 used 체크
        checkUsed(used, direct,idx);

        // 8개의 뒤집는 경우로 makeSamecoin 전달, 단 used가 true인 자리는 뒤집는 요청 금지
        char[][] copyCoin;
        for(int i=0; i<3; i++) {
            if(!used.rowUsed[i]) {
                copyCoin = new char[N][N];
                for(int j=0; j<N; j++)  System.arraycopy(placedCoin[j],0, copyCoin[j],0, N);
                makeSameCoin(copyCoin, new UsedLocation(used), Direction.ROW, i, next + 1);
            }
        }
        for(int i=0; i<3; i++) {
            if(!used.colUsed[i]) {
                copyCoin = new char[N][N];
                for(int j=0; j<N; j++)  System.arraycopy(placedCoin[j],0, copyCoin[j],0, N);
                makeSameCoin(copyCoin, new UsedLocation(used), Direction.COL, i, next + 1);
            }
        }
        for(int i=0; i<2; i++) {
            if(!used.crossUsed[i]) {
                copyCoin = new char[N][N];
                for(int j=0; j<N; j++)  System.arraycopy(placedCoin[j],0, copyCoin[j],0, N);
                makeSameCoin(copyCoin, new UsedLocation(used), Direction.CROSS, i, next + 1);
            }
        }
    }

    static char[][] placeCoin(char[][] graph, Direction direct, int idx){
        char[][] copyGraph = new char[N][N];
        for(int i=0; i<N; i++){
            System.arraycopy(graph[i],0, copyGraph[i],0, N);
        }
        switch(direct){
            case ROW:
                for(int i=0; i<3; i++)  copyGraph[idx][i] = graph[idx][i] == 'H' ? 'T' : 'H';
                break;
            case COL:
                for(int i=0; i<3; i++)  copyGraph[i][idx] = graph[i][idx] == 'H' ? 'T' : 'H';
                break;
            case CROSS:
                if(idx == 0)
                    for(int i=0; i<3; i++)  copyGraph[i][i] = graph[i][i] == 'H' ? 'T' : 'H';
                else
                    for(int i=0; i<3; i++) copyGraph[i][2-i] = graph[i][2-i] == 'H' ? 'T': 'H';
                break;
            default:
                break;
        }

        return copyGraph;
    }

    static void checkUsed(UsedLocation used, Direction direction, int idx){
        switch(direction){
            case ROW:
                used.rowUsed[idx] = true;
                break;
            case COL:
                used.colUsed[idx] = true;
                break;
            case CROSS:
                used.crossUsed[idx] = true;
                break;
            default:
                break;
        }
    }

    static class UsedLocation implements Cloneable{
        boolean[] rowUsed;
        boolean[] colUsed;
        boolean[] crossUsed;

        public UsedLocation(){
            rowUsed = new boolean[3];
            colUsed = new boolean[3];
            crossUsed = new boolean[2];
        }

        public UsedLocation(UsedLocation used){
            rowUsed = used.rowUsed.clone();
            colUsed = used.colUsed.clone();
            crossUsed = used.crossUsed.clone();
        }

        @Override
        protected UsedLocation clone() throws CloneNotSupportedException {
            return (UsedLocation) super.clone();
        }
    }

    static enum Direction{
        ROW,
        COL,
        CROSS,
        NONE
    }



}
