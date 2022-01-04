//返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。 
//
// 如果没有和至少为 K 的非空子数组，返回 -1 。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：A = [1], K = 1
//输出：1
// 
//
// 示例 2： 
//
// 输入：A = [1,2], K = 4
//输出：-1
// 
//
// 示例 3： 
//
// 输入：A = [2,-1,2], K = 3
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 50000 
// -10 ^ 5 <= A[i] <= 10 ^ 5 
// 1 <= K <= 10 ^ 9 
// 
// Related Topics 队列 数组 二分查找 前缀和 滑动窗口 单调队列 堆（优先队列） 👍 317 👎 0

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int shortestSubarray(int[] nums, int k) {    // k为正,可考虑单调栈
        int ans = nums.length + 1;
        long sum = 0;
        long[] cumsum = new long[nums.length + 1];
        for (int i = 1; i < cumsum.length; i++) {
            sum += nums[i - 1];
            cumsum[i] = sum;
        }
        LinkedList<Integer> container = new LinkedList<>();
        for (int i = 0; i < cumsum.length; i++) {
            long temp = cumsum[i];
            // 当前元素不大于前面元素,则优先选择当前元素作为子序列首端使其更短且和达到k
            while (!container.isEmpty() && temp <= cumsum[container.peekLast()]) {
                container.pollLast();
            }
            // 当前元素可以与双链表首端元素组成满足条件的子数组时
            while (!container.isEmpty() && temp >= cumsum[container.peekFirst()] + k) {
                ans = Math.min(ans, i - container.pollFirst());
            }
            container.add(i);
        }
        return ans == nums.length + 1 ? -1 : ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    int k, ans;
    long[] container, aux;
    int[] idxs, auxIdx;

    // 右侧前缀和与左侧前缀和之差满足条件即可
    // 若左右部分前缀和已排序,则便于计算数量
    // 因此可利用归并
    public int shortestSubarray(int[] nums, int k) {    // 归并(复杂度高,但可以解决k<0的情况)
        int length = nums.length;
        long cumsum = 0;
        this.k = k;
        ans = length + 1;
        container = new long[length + 1];
        aux = new long[length + 1];
        idxs = new int[length + 1];
        auxIdx = new int[length + 1];
        for (int i = 1; i < length + 1; i++) {
            cumsum += nums[i - 1];
            container[i] = cumsum;
        }
        for (int i = 0; i < length + 1; i++) {
            idxs[i] = i - 1;
        }
        sort(0, length);
        return ans == length + 1 ? -1 : ans;
    }

    private void sort(int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + ((end - start) >> 1);
        sort(start, mid);
        sort(mid + 1, end);
        count(start, mid, end);
        if (start == 0 && end == container.length - 1) {
            return;
        }
        merge(start, mid, end);
    }

    private void count(int start, int mid, int end) {
        if (container[end] - container[start] < k) {
            return;
        }
        // 对每个右侧[mid+1,end]元素,选择相应左侧idx范围(选最大的使相减后最小)
        int idx = start, maxIdx = idxs[idx];
        for (int i = mid + 1; i <= end; i++) {
            long temp = container[i];
            if (temp - container[start] < k) {
                continue;
            }
            while (idx <= mid && temp - container[idx] >= k) {
                maxIdx = Math.max(maxIdx, idxs[idx++]);
            }
            ans = Math.min(ans, idxs[i] - maxIdx);
        }
    }

    private void merge(int start, int mid, int end) {
        int left = start, right = mid + 1, idx = start;
        for (int i = start; i <= end; i++) {
            aux[i] = container[i];
            auxIdx[i] = idxs[i];
        }
        while (left <= mid || right <= end) {
            if (left == mid + 1) {
                while (idx <= end) {
                    container[idx] = aux[right];
                    idxs[idx++] = auxIdx[right++];
                }
                return;
            }
            if (right == end + 1) {
                while (idx <= end) {
                    container[idx] = aux[left];
                    idxs[idx++] = auxIdx[left++];
                }
                return;
            }
            if (aux[left] <= aux[right]) {
                container[idx] = aux[left];
                idxs[idx++] = auxIdx[left++];
            }
            else {
                container[idx] = aux[right];
                idxs[idx++] = auxIdx[right++];
            }
        }
    }
}
