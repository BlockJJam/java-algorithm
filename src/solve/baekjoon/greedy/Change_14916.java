package solve.baekjoon.greedy;

import java.io.*;
import java.util.*;

public class Change_14916 {
    static int N;
    static int result;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        result = -1;

        Coin coin = new Coin();
        coin.findReturn(N, 0);

        bw.write(result+"\n");
        bw.flush();
        bw.close();

    }

    static class Coin{

        public void findReturn(int remain, int cntOfTwo){

            if(remain == 1){
                result = -1;
                return;
            }
            if(remain % 5 == 0){
                // remain이 5의 배수이면 2원의 개수 + 5원으로 나머지를 채운 코인 개수를 result 반환
                result =cntOfTwo;
                result += remain / 5;
            }else if((remain -2)% 5 ==0 ){
                // remain에 2를 뺏을 때 5로 나눈 나머지가 0이면 result에 5를 나눈 값 + 2의 개수를 result에 담기
                result = cntOfTwo +1;
                result += remain / 5;
            }else{
                // 위 2가지 상황이 모두 아니면 cntOfTwo를 하나씩 늘리고, remain에서는 2씩 차감하여 재귀 호출
                findReturn(remain -2, cntOfTwo +1);
            }
        }
    }
}
