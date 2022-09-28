package algorithm.all_module;

public class EratosMakePrime {
    static boolean[] prime;
    static int N;

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
}
