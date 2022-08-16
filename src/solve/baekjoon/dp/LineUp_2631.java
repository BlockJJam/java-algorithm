package solve.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LineUp_2631 {
    static int N;
    static int[] numArr;
    static boolean[] visited;
    static List<Integer> students = new ArrayList<>();
    static int result;

    public static void main(String[] args) {
        FastReader rd = new FastReader();

        N = rd.nextInt();
        numArr = new int[N];
        visited = new boolean[N];
        result = -1;
//        for(int i=0; i<N; i++){
//            numArr[i] = i+1;
//            students.add(rd.nextInt());
//        }
        for(int i=N-1; i>=0; i--){
            numArr[i] = i+1;
            students.add(i+1);
        }

        for(int i=1; i< N+1; i++){
            if(result == -1){
                findMinChangeCount(0, i);
            }
        }

        System.out.println(result);

    }

    static void findMinChangeCount(int start, int round){
        if(round == 0){
            // 해당 조합에서 순서변경 후, 순서가 올바르게 만들어지는지 확인
            List<Integer> copiedStudents = new ArrayList<>();
            copiedStudents.addAll(students);
//            Collections.copy(copiedStudents, students);
            int total = 0;
            for(int i=0; i<visited.length; i++){
                if(visited[i]) {
                    // 방문한 자리의 숫자의 자리를 꺼내서 순서대로 맞는 자리를 찾도록 하는 함수 호출
//                    System.out.print(i + " ");
                    findCollectLocation(copiedStudents, i+1);
                    ++total;
                }
            }
//            System.out.println();
//            System.out.println(copiedStudents);
            int prev = 0;
            boolean found = true;
            for(int target : copiedStudents){
                if(prev > target){
                    found = false;
                    break;
                }
                prev = target;
            }
            if(found){
                result = total;
                return;
            }
//            for(int i=0; i < copiedStudents.size(); i++){
//                int target = copiedStudents.get(i);
//                if(target != i+1) break;
//                if(i == copiedStudents.size() -1) {
//                    result = total;
//                }
//            }
        }

        for(int i = start; i< N; i++){
            if(!visited[i]){
                visited[i] = true;
                findMinChangeCount(i+1, round -1);
                visited[i] = false;
            }
        }
    }

    static void findCollectLocation(List<Integer> list, int target){
        int targetIndex = list.indexOf(target);
        list.remove(targetIndex);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
