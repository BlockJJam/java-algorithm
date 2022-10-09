package solve.programmers.kakaoblind2021;

public class NewIdRecommand {

    public static String solution(String new_id) {
        String answer = "";

        // 1
        String s = new_id.toLowerCase();
        System.out.println(s);


        // 2
        String s2 = s.replaceAll("[^0-9a-z-_.]", "");
        System.out.println("s2 = " + s2);

        // 3
        String s3 = s2;
        while(s3.contains("..")){
            s3 = s3.replaceAll("\\.\\.", ".");
        }
        System.out.println("s3 = " + s3);

        // 4
        s3 = replaceFirstDot(s3);
        s3 = replaceLastDot(s3);

        s3 = s3.length() == 0? "a": s3;
        s3 = s3.length() >= 16? s3.substring(0, 15): s3;

        s3 = replaceFirstDot(s3);
        s3 = replaceLastDot(s3);

        StringBuilder sb = new StringBuilder(s3);
        while(sb.toString().length() <= 2){
            sb.append(sb.charAt(sb.length()-1));
        }

        return sb.toString();
    }

    private static String replaceLastDot(String s3) {
        return s3.replaceAll("\\.$", "");
    }

    private static String replaceFirstDot(String s3) {
        return s3.replaceAll("^\\.","");
    }

    public static void main(String[] args) {
        solution("...!@BaT#*..y.ab12cdefghijklm");

    }
}
