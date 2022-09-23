package algorithm.all_module;

/**
 * 이전에 구한 값을 저장해서 활용하는 방법 - (메모이제이션)
 * - 왜 저장하는가? 반복적으로 메서드를 불러오는 횟수를 줄이기 가능
 * - 한번 연산한 결과를 또 연산할 필요 없음
 * - == 기억하며 풀기
 * - knapsack 문제 -> 점화식을 밑에서 확인
 *              - 0   if(j <= 0 || i< 0)
 *   m[i][j] = |  max(m[i-1][j], m[i-1][j-weights[i]] + value[i])   if(j>=weights[i])
 *             -  m[i-1][j]   if(j < weighths[i])
 */
public class DP {

}
