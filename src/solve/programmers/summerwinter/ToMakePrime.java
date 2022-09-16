package solve.programmers.summerwinter;

public class ToMakePrime {
    int n;
    int[] numArr;
    boolean[] prime;
    int ans =0;

    public int solution(int[] nums) {
        int answer = -1;

        numArr = nums;
        n = nums.length;
        prime = new boolean[3001];

        eratosMakePrime(3000);

        int sum = 0;
        for(int i=0; i< nums.length -2; i++){
            for(int j = i+1; j< nums.length -1; j++){
                for(int k = j+1; k < nums.length; k++){
                    sum = nums[i] + nums[j] + nums[k];
                    if(!prime[sum]){
                        ans++;
                    }
                }
            }
        }

        answer = ans;
        return answer;
    }

    public void eratosMakePrime(int n){
        prime[0] = prime[1] = true;

        for (int i = 2; i <= n; i++) {
            if(!prime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    if(!prime[j])
                        prime[j] = true;
                }
            }
        }
    }



    public static void main(String[] args) {

    }
}
