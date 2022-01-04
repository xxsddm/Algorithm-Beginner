import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MergeSort {
    public static void sort(int[] target) {
        sort(target, 0, target.length - 1, new int[target.length]);
    }

    private static void sort(int[] nums, int start, int end, int[] aux) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) >> 1;
        sort(nums, start, mid, aux);
        sort(nums, mid + 1, end, aux);
        for (int i = start; i <= end; i++) {
            aux[i] = nums[i];
        }
        int left = start, right = mid + 1, idx = start;
        while (idx <= end) {
            if (left > mid) {
                while (idx <= end) {
                    nums[idx++] = aux[right++];
                }
                break;
            }
            else if (right > end) {
                while (idx <= end) {
                    nums[idx++] = aux[left++];
                }
                break;
            }
            if (aux[left] <= aux[right]) {
                nums[idx++] = aux[left++];
            }
            else {
                nums[idx++] = aux[right++];
            }
        }
    }

    public static void main(String[] args) {    // 测试(LeetCode 315, 剑指 Offer 51. 测试通过)
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
        MergeSort.sort(test);
        System.out.println(Arrays.toString(test));
    }
}
