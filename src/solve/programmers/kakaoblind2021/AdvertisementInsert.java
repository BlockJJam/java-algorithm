package solve.programmers.kakaoblind2021;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdvertisementInsert {
    public static String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int intPlay = timeStringToInt(play_time);
        int intAdv = timeStringToInt(adv_time);

        long[] watchPlayingTimes = new long[intPlay + 2];

        for (int i = 0; i < logs.length; i++) {
            String[] splited = logs[i].split("-");
            int st = timeStringToInt(splited[0]) + 1;
            int et = timeStringToInt(splited[1]) + 1;
            watchPlayingTimes[st] ++;
            watchPlayingTimes[et] --;
        }

        for(int i = 1; i< watchPlayingTimes.length; i++) {
            watchPlayingTimes[i] += watchPlayingTimes[i - 1];
        }

        for(int i = 1; i< watchPlayingTimes.length; i++) {
            watchPlayingTimes[i] += watchPlayingTimes[i - 1];
        }

        long totalTime = 0;
        int startTime = 0;
        for (int time = 0; time + intAdv < watchPlayingTimes.length; time++) {
            long playTime = watchPlayingTimes[time + intAdv] - watchPlayingTimes[time];
            if(totalTime < playTime){
                totalTime = playTime;
                startTime = time;
            }
        }

        answer = intToTimeString(startTime);

        System.out.println("answer = " + answer);
        return answer;
    }

    public static int timeStringToInt(String timeStr) {
        // XX:XX:XX -> integer
        String[] splited = timeStr.split(":");
        int second = 3600;
        int total = 0;
        for(int i=0; i<splited.length; i++){
            total += Integer.parseInt(splited[i]) * second;
            second /= 60;
        }

        return total;
    }


    public static String intToTimeString(int timeNum) {
        // XX:XX:XX <- integer
        int second = 3600;
        StringBuilder sb = new StringBuilder();

        while(second > 0){
            int time = timeNum / second;
            if(time < 10){
                sb.append('0');
                sb.append(time);
            }else{
                sb.append(time);
            }
            timeNum %= second;
            second /= 60;
            sb.append(":");
        }

        String ret = sb.toString();
        return ret.substring(0, ret.length() - 1);
    }

    public static void main(String[] args) throws ParseException {
        int x = timeStringToInt("12:12:12");
        System.out.println(x);
        System.out.println("longToTimeString(x) = " + intToTimeString(x));

        solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});
        solution("99:59:59", "25:00:00", new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"});
        solution("50:00:00", "50:00:00", new String[]{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"});
    }
}
