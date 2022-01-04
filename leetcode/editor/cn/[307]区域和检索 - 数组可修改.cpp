//给你一个数组 nums ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。 
//
// 实现 NumArray 类： 
//
// 
// 
// 
// NumArray(int[] nums) 用整数数组 nums 初始化对象 
// void update(int index, int val) 将 nums[index] 的值更新为 val 
// int sumRange(int left, int right) 返回子数组 nums[left, right] 的总和（即，nums[left] + 
//nums[left + 1], ..., nums[right]） 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["NumArray", "sumRange", "update", "sumRange"]
//[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
//输出：
//[null, 9, null, 8]
//
//解释：
//NumArray numArray = new NumArray([1, 3, 5]);
//numArray.sumRange(0, 2); // 返回 9 ，sum([1,3,5]) = 9
//numArray.update(1, 2);   // nums = [1,2,5]
//numArray.sumRange(0, 2); // 返回 8 ，sum([1,2,5]) = 8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// -100 <= nums[i] <= 100 
// 0 <= index < nums.length 
// -100 <= val <= 100 
// 0 <= left <= right < nums.length 
// 最多调用 3 * 10⁴ 次 update 和 sumRange 方法 
// 
// 
// 
// Related Topics 设计 树状数组 线段树 数组 👍 309 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class NumArray {
public:
    int length;
    vector<int> nums, tree;

    NumArray(vector<int>& nums) {   // 树状数组
        length = (int) nums.size();
        this->nums = nums;
        tree = vector<int> (length + 1);
        for (int i = 0; i < length; i++) {
            int idx = i + 1, val = nums[i];
            while (idx <= length) {
                tree[idx] += val;
                idx += idx & -idx;
            }
        }
    }

    void update(int index, int val) {
        int delta = val - nums[index];
        nums[index++] = val;
        while (index <= length) {
            tree[index] += delta;
            index += index & -index;
        }
    }

    int sumRange(int left, int right) {
        return getSum(right + 1) - getSum(left);
    }

    int getSum(int end) {
        int ans = 0;
        while (end) {
            ans += tree[end];
            end -= end & -end;
        }
        return ans;
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)
