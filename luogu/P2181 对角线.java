// https://www.luogu.com.cn/problem/P2181

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    // 4个顶点确定2条相交对角线, 即确定1个交点, 排列组合
    public String calculate(int n) {
        BigInteger temp = BigInteger.valueOf(n);    // long依然溢出(支持int64则不会溢出, 可惜)
        BigInteger ans = BigInteger.valueOf(1);
        for (int i = 0; i <= 3; i++) {
            ans = ans.multiply(temp.subtract(BigInteger.valueOf(i)));
        }
        return ans.divide(BigInteger.valueOf(24)).toString();
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(new Main().calculate(input.nextInt()));
    }
}
