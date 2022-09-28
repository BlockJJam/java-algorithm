package solve.programmers.summerwinter1;

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

    // n이 가능한 범위까지 소수 여부를 배열에 체크해줄 것이다.
    public void eratosMakePrime(int n){
        // 0,1은 소수가 아니다
        prime[0] = prime[1] = true;

        // 2부터 해당 자리의 제곱과 그 배수를 n범위까지 소수불가 처리를 해준다
        // 즉, 2, 3, 5 ... 등은 소수지만, 그 제곱부터는 소수가 될 수 없음을 미리 체크해준 것
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
