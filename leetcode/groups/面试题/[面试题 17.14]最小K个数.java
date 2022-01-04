//设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。 
//
// 示例： 
//
// 输入： arr = [1,3,5,7,2,4,6,8], k = 4
//输出： [1,2,3,4]
// 
//
// 提示： 
//
// 
// 0 <= len(arr) <= 100000 
// 0 <= k <= min(100000, len(arr)) 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 76 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int k;
    int[] nums;

    public int[] smallestK(int[] arr, int k) {  // 快速排序(这里没有随机重排, O(n))
        if (k == 0) {
            return new int[k];
        }
        if (k == arr.length) {
            return arr;
        }
        int[] ans = new int[k];
        this.k = k;
        nums = arr;
        sort(0, arr.length - 1);
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    private void sort(int start, int end) { // 分割元素取左端值(取右端值见go)
        if (start >= end) {
            return;
        }
        // start元素作为分割元素temp, start+1~left小于分割元素temp, right~end大于分割元素temp
        int temp = nums[start], left = start, right = end + 1, idx = start + 1;
        while (idx < right) {   // 最终start和left+1~right-1全部填入temp
            if (nums[idx] < temp) {
                exchange(idx++, ++left);
            }
            else if (nums[idx] > temp) {
                exchange(idx, --right);
            }
            else {
                idx++;
            }
        }
        exchange(start, left);  // left~right-1全部填入temp
        if (end == start + 1) {
            return;
        }
        if (right < k) {
            sort(right, end);
        }
        else if (left > k) {
            sort(start, left);
        }
    }

    private void exchange(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int[] smallestK(int[] arr, int k) {  // 优先队列(很慢, O(nlnk))
        if (k == 0) {
            return new int[k];
        }
        PriorityQueue<Integer> container = new PriorityQueue<>(Comparator.comparing(o -> - o));
        int[] ans = new int[k];
        for (int num: arr) {
            if (container.size() < k) {
                container.add(num);
            }
            else {
                if (num < container.peek()) {
                    container.poll();
                    container.add(num);
                }
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = container.poll();
        }
        return ans;
    }
}

class QuickSort {
    public static void sort(int[] nums) {   // 测试快速排序QuickSort
        sort(0, nums.length - 1, nums);
    }

    private static void sort(int start, int end, int[] nums) {  // 快速排序(这里没有随机重排)
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
}

class Solution {
    public int[] smallestK(int[] arr, int k) {
        QuickSort.sort(arr);
        return Arrays.copyOfRange(arr, 0, k + 1);
    }
}
