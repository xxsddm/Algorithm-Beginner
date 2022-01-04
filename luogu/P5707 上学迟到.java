// https://www.luogu.com.cn/problem/P5707

import java.util.Scanner;

class Main {
    public String calculate(int s, int v) {
        int time = s / v + (s % v == 0 ? 0 : 1);
        int start = 7 * 60 + 50 - time;     // 转换为以分钟为单位的时间
        start += start < 0 ? 24 * 60 : 0;
        int hour = start / 60, minute = start % 60;
        StringBuilder sb = new StringBuilder();
        if (hour < 10) {
            sb.append(0);
        }
        sb.append(hour);
        sb.append(':');
        if (minute < 10) {
            sb.append('0');
        }
        sb.append(minute);
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int s = input.nextInt(), v = input.nextInt();
        System.out.println(new Main().calculate(s, v));
    }
}
