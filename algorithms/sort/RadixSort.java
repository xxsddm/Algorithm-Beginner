import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RadixSort {    // LSD
    public static void sort(int[] nums) {   // 基数排序(仅支持整数)
        int nega = 0, idx1 = 0, idx2 = 0;
        for (int num: nums) {
            if (num < 0) {
                nega++;
            }
        }
        int[] nums1 = new int[nega], nums2 = new int[nums.length - nega];
        for (int num: nums) {
            if (num < 0) {
                nums1[idx1++] = - num;
            }
            else {
                nums2[idx2++] = num;
            }
        }
        // 负数转换为正数单独排序, 填入时颠倒负数顺序
        positiveSort(nums1);
        positiveSort(nums2);
        for (int i = nega - 1; i >= 0; i--) {
            nums[i] = - nums1[nega - 1 - i];
        }
        for (int i = nega; i < nums.length; i++) {
            nums[i] = nums2[i - nega];
        }
    }

    private static void positiveSort(int[] nums) {  // 仅支持非负整数
        if (nums.length <= 1) {
            return;
        }
        // 若不出现低位(小于minNum)不同而高位相同的情况, 则可以使用. 否则删除minNum
        int divisor = 1, maxNum = 0, minNum = Integer.MAX_VALUE;
        int[] cache1 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            maxNum = Math.max(maxNum, nums[i]);
            minNum = Math.min(minNum, nums[i]);
            cache1[i] = nums[i];
        }
        while (minNum > divisor) {
            divisor *= 10;
        }
        if (divisor >= 10) {
            divisor /= 10;
        }
        while (maxNum >= divisor) {
            int[] counter = new int[11];
            int[] cache2 = new int[nums.length];
            for (int num: nums) {
                // 计算索引
                counter[(num / divisor) % 10 + 1]++;
            }
            for (int i = 1; i < 9; i++) {
                counter[i + 1] += counter[i];
            }
            for (int num: cache1) {
                cache2[counter[(num / divisor) % 10]++] = num;
            }
            cache1 = cache2;
            divisor *= 10;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = cache1[i];
        }
    }

    public static void main(String[] args) {    // 测试(LeetCode 164题测试通过)
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
        RadixSort.sort(test);
        System.out.println(Arrays.toString(test));
    }
}
