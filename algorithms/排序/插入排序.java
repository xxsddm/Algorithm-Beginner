import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class InsertSort {
    public static void sort(int[] nums) {   // 插入排序(仅适用于整数)(常用于较小数组, 可用于快速排序的较小子序列)
        for (int i = 1; i < nums.length; i++) {
            // 插入排序将较小值向前交换
            while (i > 0 && nums[i] < nums[i - 1]) {
                exchange(i, i - 1, nums);
                i--;
            }
        }
    }

    private static void exchange(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Random generator = new Random();
        Scanner input = new Scanner(System.in);
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 120; i++) {
            line.append('=');
        }
        System.out.println("输入数组长度");
        int length = input.nextInt();
        System.out.println("输入数组下界");
        int low = input.nextInt();
        System.out.println("输入数组上界");
        int high = input.nextInt();
        if (length < 0 || low > high) {
            throw new IllegalArgumentException("参数有误");
        }
        int[] test = new int[length];
        for (int i = 0; i < length; i++) {
            test[i] = generator.nextInt(high - low + 1) + low;
        }
        System.out.println(line);
        InsertSort.sort(test);
        System.out.println(Arrays.toString(test));
    }
}
