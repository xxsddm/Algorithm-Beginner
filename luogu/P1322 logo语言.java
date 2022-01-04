// https://www.luogu.com.cn/problem/P1322

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static int move(String str) {    // REPEAT, FD, BK均可取0
        LinkedList<Integer> stack = new LinkedList<>();
        int idx = 0, length = str.length(), multiplier = 1, loc = 0;
        boolean repeat = false, forward = false;
        while (idx < length) {
            int num = 0;
            boolean move = false;
            while (idx < length && str.charAt(idx) >= '0' && str.charAt(idx) <= '9') {
                move = true;
                num = num * 10 + str.charAt(idx) - '0';
                idx++;
            }
            if (move) {
                // 数字在REPEAT后
                if (repeat) {
                    stack.addLast(num);
                    multiplier *= num;
                    repeat = false;
                }
                // 数字在FD后
                else if (forward) {
                    loc += num * multiplier;
                    forward = false;
                }
                // 数字在BK后
                else {
                    loc -= num * multiplier;
                }
                continue;
            }

            char temp = str.charAt(idx);
            if (temp == 'R') {
                repeat = true;
                idx += 6;
            }
            else if (temp == 'F') {
                forward = true;
                idx += 2;
            }
            else if (temp == 'B') {
                idx += 2;
            }
            else if (temp == ']' && !stack.isEmpty()) {
                int last = stack.pollLast();
                if (last != 0) {
                    multiplier /= last;
                }
                else {
                    multiplier = 1;
                    for (int tempnum : stack) {
                        multiplier *= tempnum;
                    }
                }
            }
            idx++;
        }

        return Math.abs(loc);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        System.out.println(Main.move(str));
    }
}
