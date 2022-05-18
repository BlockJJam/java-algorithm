package solve.baekjoon.math;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrimeAndPelindrome_1747 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int NN = 2000000;
        boolean[] isPrime = new boolean[NN+1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;

        for(int i=2; i <=NN; i++){
            if(isPrime[i]){
                for(int j=i*i; j<=NN && j > 2; j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        Pelindrome pelindrome = new Pelindrome();
        long result = 0;
        for(int i=N; i<=NN; i++){
            if(isPrime[i]){
                String target = String.valueOf(i);
                boolean passed= false;
                if(target.length()%2 == 0){
                    passed = pelindrome.isPelindromeByEven(target);
                }else{
                    passed = pelindrome.isPelindromeByOdd(target);
                }
                if(passed){
                    result = i;
                    break;
                }
            }
        }

        bw.write(result +"\n");
        bw.flush();
        bw.close();
    }

    static class Pelindrome{
        private int left;
        private int right;

        public boolean isPelindromeByOdd(String target){
            left = target.length()/2 ;
            right = target.length()/2 ;
            for(int i=0; i<=left; i++){
                if(target.charAt(left - i) != target.charAt(right + i)){
                    return false;
                }
            }
            return true;
        }

        public boolean isPelindromeByEven(String target){
            left = target.length()/2 -1;
            right = target.length()/2 ;


            for(int i=0; i<=left; i++){
                if(target.charAt(left - i) != target.charAt(right + i)){
                    return false;
                }
            }
            return true;
        }
    }
}
