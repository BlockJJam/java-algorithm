package solve.baekjoon.twopointer;

import java.io.*;
import java.util.StringTokenizer;

public class Blog_21921 {
    static int N;
    static int X;
    static int[] custArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        custArr = new int[N];

        int loop = X;
        int maxCust = 0;
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            int customer = Integer.parseInt(st.nextToken());
            custArr[i] = customer;
            if(loop != 0){
                maxCust += customer;
                --loop;
            }
        }

        int start = 0;
        int sumCustomer = maxCust;
        int sameCnt = 1;
        for(int i = X; i<N; i++){
            sumCustomer -= custArr[start];
            ++start;
            sumCustomer += custArr[i];

            if(sumCustomer > maxCust){
                maxCust = sumCustomer;
                sameCnt =1;
            }else if(sumCustomer == maxCust){
                ++sameCnt;
            }
        }

        if(maxCust == 0){
            bw.write("SAD\n");
        }else{
            bw.write(maxCust +"\n"+sameCnt +"\n");
        }

        bw.flush();
        bw.close();
    }
}
