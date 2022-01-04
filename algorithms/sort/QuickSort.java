import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class QuickSort {
    public static void sort(int[] nums) {
        sort(0, nums.length - 1, nums);
    }

    private static void sort(int start, int end, int[] nums) {  // 快速排序(仅适用于整数, 这里没有随机重排)
        if (start >= end) {
            return;
        }
        // start~left小于分割元素temp, right~end大于分割元素temp
        int temp = nums[start], left = start, right = end + 1, idx = start + 1;
        while (idx < right) {   // 最终start和left+1~right-1全部填入temp
            if (nums[idx] < temp) {
                exchange(idx++, ++left, nums);
            }
            else if (nums[idx] > temp) {
                exchange(idx, --right, nums);
            }
            else {
                idx++;
            }
        }
        exchange(start, left, nums);  // left~right-1全部填入temp
        if (end == start + 1) {
            return;
        }
        sort(start, left - 1, nums);
        sort(right, end, nums);
    }

    private static void exchange(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {    // 测试(LeetCode 面试题 17.14. 测试通过)
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
        QuickSort.sort(test);
        System.out.println(Arrays.toString(test));
    }
}

