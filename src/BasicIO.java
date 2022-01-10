import com.sun.source.tree.BinaryTree;

import java.io.*;
import java.util.StringTokenizer;

/** Java 기본적으로 활용하는(백준에서) 문제 풀이 형태
 * 1. Scanner보다 BufferedReader를 사용
 * 2. split보다 StringTokenizer
 * 3. 여러번 출력하는 경우는 StringBuilder를 사용해 한번에 출력하는 것이 성능에 좋다
 * 4. Array를 사용하기보다는 ArrayList가 더 최적화
 * 5. ArrayList 정령에는 Collections.sort()를 사용
 * 6. 배열 초기화는 Arrays.fill(배열, 초기화값)을 사용 -> ArrayList 초기화도 될까?
 * 7. 연속으로 있는 숫자를 입력받기 위해서는 2번을 사용하자
 *          1. String[] inputs = br.readline().split("");
 *          2. br.read() - '0';
 */
public class BasicIO {

    private static int N,M;
    private static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 특정 문자(delim) 분리: StringTokenizer(String str, String delim(="#"이런식으로) )
        // 특정 문자까지 토큰에 포함해서 분리: StringTokenizer(String str, String delim, boolean returnDelim(토큰으로 포함할지 여부))
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 연속적인 숫자를 입력받을 때
        //N = br.read() - '0';

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = st.nextToken();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(S+"\n");
        bw.write(N+"\n");
        bw.flush();
        bw.close(); // bw.flush(), bw.close()는 1세트
        /**
         * BufferedReader에 관해,
         * Scanner의 경우 입력받는 "Space" "Enter"를 모두 경계로 인식
         * BufferedReader는 "Enter"만 경계로 인식 + String 으로 고정되어 반환 가공이 필요!
         * 그럼에도, BufferedReader를 이용하는 경우가 있으니 -> 많은 양의 데이터를 입력받을 때, 일반적으로 BufferedReader를 사용하자
         */
    }
}
