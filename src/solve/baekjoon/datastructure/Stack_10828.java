package solve.baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class Stack_10828 {
    static int N;
    static StackUtil stackUtil;
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        stackUtil = new StackUtil();

        for(int i =0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("push")){
                stackUtil.call(command, Integer.parseInt(st.nextToken()) );
            }else{
                int result = stackUtil.call(command);
                bw.write(result+"\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static class StackUtil{
        // 문제용 배열 생성
        private Deque<Integer> stack = new ArrayDeque<>();

        public void call(String command, int x){
            if(command.equals("push"))
                this.push(x);
        }

        public int call(String command){
            if(command.equals("pop")) {
                return this.pop();
            } else if (command.equals("size")) {
                return this.size();
            } else if (command.equals("empty")) {
                return this.empty();
            } else if (command.equals("top")) {
                return this.top();
            } else{
                return -1000;
            }
        }

        // 5가지 기능 메서드
        // push
        public void push(int x){
            stack.push(x);
        }

        // pop
        public Integer pop(){
            return stack.isEmpty()? -1: stack.pop();
        }

        // size
        public Integer size(){
            return stack.size();
        }

        // empty
        public Integer empty(){
            return stack.isEmpty() ? 1 : 0;
        }

        // top
        public Integer top(){
            return stack.isEmpty()? -1 :stack.peek();
        }


    }
}
