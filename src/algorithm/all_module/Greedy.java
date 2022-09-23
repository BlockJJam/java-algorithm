package algorithm.all_module;


/**
 * 특징
 * - 매 선택 중에 내가 가장 원하는 가까운 답으로 향하겠다
 * - 백트래킹 하지말고.. 현재 조건에서 다른 선택의 경우는 검증할 필요없다!
 * - 단 언제나 전체에서 최적값을 구할 수 없기 때문에, 다음 2가지를 만족할 때 사용
 *      1. 탐욕 선택 속성: 이전의 선택이 이후에 영향을 주지 않는다
 *      2. 최적 부분 구조: 부분 문제 최적 결과가 전체흐름에 그대로 적용된다
 * - 안되는 예시
 *      - 도둑이 가방에 넣을 수 있는 물건 중 가장 비싼 물건을 넣어야 할 때
 *      - [35KG의 30KG = 3000원], [25KG = 2500원 + 10KG = 1500원]
 *      - 3000원 vs 4000원
 *      - Greedy라면, 3000원을 고른다
 */
public class Greedy {
}
