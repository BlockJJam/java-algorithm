package solve.programmers.kakaoblind2021;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdvertisementInsert {
    public static String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int intPlayTime= timeStringToInt(play_time);
        int intAdvTime= timeStringToInt(adv_time);

        long[] watchPeoples = new long[intPlayTime + 2];
        for(int idx = 0; idx < logs.length; idx++){
            String[] splited = logs[idx].split("-");
            int st = timeStringToInt(splited[0])+1;
            int et = timeStringToInt(splited[1])+1;
            watchPeoples[st] ++;
            watchPeoples[et] --;
        }

        int startTime= 0;
        long totalTime = 0;

        for(int time = 1; time < watchPeoples.length; time++){
            // 해당 시간에 진행중인 동영상 개수를 먼저 채운다.
            watchPeoples[time] += watchPeoples[time-1];
        }
        // 0 0 0 0 1 1 2 2 2 1 1 0 0 1 1 0 0 1 2 2 3 3 2 2 1 1 0 0 //


        for(int time = 1; time < watchPeoples.length; time++) {
            // 진행중인 동영상들의 해당 시간까지 플레잉 된 누적 시간을 찍는다.
            watchPeoples[time] += watchPeoples[time - 1];
        }
        // 0 0 0 0 1 2 4 6 8 9 10 10 10 11 12 12 12 13 15 17 20 23 25 27 28 29 29 29 //

        for(int time = 0; time + intAdvTime < watchPeoples.length; time++){
            // "시작 시간 + 광고 끝나는 시간의 누적 플레잉 타임수"에서  "해당 시작 시간의 누적 플레잉 타임 수"를 빼준다. 즉 "해당 범위의 누적 플레잉 타임수"를 구해준다.
            long watchTime = watchPeoples[time + intAdvTime] - watchPeoples[time];
            if(totalTime < watchTime){
                totalTime = watchTime;
                startTime = time;
            }
        }

        answer = intToTimeString(startTime);
        System.out.println("answer = " + answer);
        return answer;
    }

    public static int timeStringToInt(String timeStr) {
        // XX:XX:XX -> integer
        String[] times = timeStr.split(":");
        int intTime = 0;
        int second = 3600;

        for(int i=0; i< times.length; i++){
            intTime += second * Long.parseLong(times[i]);
            second /= 60;
        }
        return intTime;
    }


    public static String intToTimeString(int timeNum) {
        StringBuilder sb = new StringBuilder();
        int second = 3600;

        while(second > 0){
            long time = timeNum / second;
            if(time < 10){
                sb.append('0');
                sb.append(Long.toString(time));
            }else{
                sb.append(Long.toString(time));
            }

            sb.append(":");
            timeNum %= second;
            second /= 60;
        }

        String retStr = sb.toString();
        return retStr.substring(0, retStr.length()-1);
    }

    public static void main(String[] args) throws ParseException {
        int x = timeStringToInt("12:12:12");
        System.out.println(x);
        System.out.println("longToTimeString(x) = " + intToTimeString(x));

        solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});
//        solution("99:59:59", "25:00:00", new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"});
//        solution("50:00:00", "50:00:00", new String[]{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"});
    }
}
