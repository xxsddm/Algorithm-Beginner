import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class BucketSort {
    public static void sort(int[] nums) {
        sort(nums, 10);
    }

    public static void sort(int[] nums, int step) { // 桶排序(可视为计数排序的推广)(仅适用于整数)
        if (nums.length == 0) {
            return;
        }
        // idx为nums中当前待填入排序元素的索引
        int minNum = nums[0], maxNum = nums[0], idx = 0;
        for (int num: nums) {
            minNum = Math.min(minNum, num);
            maxNum = Math.max(maxNum, num);
        }
        // 归一化, 根据相对位置选择不同的桶
        int numBucket = (maxNum - minNum) / step + 1;
        Stack<Integer>[] buckets =(Stack<Integer>[]) new Stack[numBucket];
        for (int num: nums) {
            int loc = (num - minNum) / step;
            if (buckets[loc] == null) {
                buckets[loc] = new Stack<>();
            }
            buckets[loc].add(num);
        }
        for (Stack<Integer> bucket: buckets) {
            if (bucket == null) {
                continue;
            }
            int[] temp = new int[bucket.size()];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = bucket.pop();
            }
            // 桶内单独排序, 这里使用插入排序
            // InsertSort.sort(temp);
            for (int i = 1; i < temp.length; i++) {
                // 插入排序将较小值向前交换
                while (i > 0 && temp[i] < temp[i - 1]) {
                    int back = temp[i];
                    temp[i] = temp[i - 1];
                    temp[i - 1] = back;
                    i--;
                }
            }
            for (int num: temp) {
                nums[idx++] = num;
            }
        }
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
        BucketSort.sort(test, 20);
        System.out.println(Arrays.toString(test));
    }
}
