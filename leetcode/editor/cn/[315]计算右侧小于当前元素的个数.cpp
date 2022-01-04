//给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于 
//nums[i] 的元素的数量。 
//
// 
//
// 示例： 
//
// 输入：nums = [5,2,6,1]
//输出：[2,1,1,0] 
//解释：
//5 的右侧有 2 个更小的元素 (2 和 1)
//2 的右侧仅有 1 个更小的元素 (1)
//6 的右侧有 1 个更小的元素 (1)
//1 的右侧有 0 个更小的元素
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 643 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<int> countSmaller(vector<int>& nums) {
        match = vector<int>(nums.size());
        container = vector<int>(nums.size(), 0);
        for (int i = 0; i < nums.size(); i++) {
            match[i] = i;   // 初始化原始索引
        }
        vector<int> aux(nums.size());
        vector<int> auxMatch(nums.size());
        // 排序过程中原始索引对应的数, 其索引不断改变, 需要用match数组备份其原始索引, 和其当前索引
        sort(nums, 0, (int) nums.size() - 1, aux, auxMatch);
        return container;
    }

private:
    vector<int> match;
    vector<int> container;

    void sort(vector<int>& nums, int start, int end, vector<int>& aux, vector<int>& auxMatch) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) >> 1;
        sort(nums, start, mid, aux, auxMatch);
        sort(nums, mid + 1, end, aux, auxMatch);
        for (int i = start; i <= end; i++) {
            aux[i] = nums[i];
            auxMatch[i] = match[i]; // 备份归并前索引
        }
        int left = start, right = mid + 1, idx = start;
        while (idx <= end) {
            if (left > mid) {
                while (idx <= end) {
                    match[idx] = auxMatch[right];   // 被填入索引idx, 其对应索引修改为填入数字的原始索引
                    nums[idx++] = aux[right++];
                }
                break;
            }
            else if (right > end) {
                while (idx <= end) {
                    match[idx] = auxMatch[left];    // 被填入索引idx, 其对应索引修改为填入数字的原始索引
                    // 填入左侧数字时, 已填入的右侧数字数量即本轮右侧小于该数字的元素个数
                    container[auxMatch[left]] += end - mid;
                    nums[idx++] = aux[left++];
                }
                break;
            }
            if (aux[left] <= aux[right]) {
                match[idx] = auxMatch[left];    // 被填入索引idx, 其对应索引修改为填入数字的原始索引
                // 填入左侧数字时, 已填入的右侧数字数量即本轮右侧小于该数字的元素个数
                container[auxMatch[left]] +=  right - mid - 1;
                nums[idx++] = aux[left++];
            }
            else {
                match[idx] = auxMatch[right];   // 被填入索引idx, 其对应索引修改为填入数字的原始索引
                nums[idx++] = aux[right++];
            }
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
