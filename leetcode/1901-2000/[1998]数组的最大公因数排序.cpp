//给你一个整数数组 nums ，你可以在 nums 上执行下述操作 任意次 ： 
//
// 
// 如果 gcd(nums[i], nums[j]) > 1 ，交换 nums[i] 和 nums[j] 的位置。其中 gcd(nums[i], nums[
//j]) 是 nums[i] 和 nums[j] 的最大公因数。 
// 
//
// 如果能使用上述交换方式将 nums 按 非递减顺序 排列，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [7,21,3]
//输出：true
//解释：可以执行下述操作完成对 [7,21,3] 的排序：
//- 交换 7 和 21 因为 gcd(7,21) = 7 。nums = [21,7,3]
//- 交换 21 和 3 因为 gcd(21,3) = 3 。nums = [3,7,21]
// 
//
// 示例 2： 
//
// 输入：nums = [5,2,6,2]
//输出：false
//解释：无法完成排序，因为 5 不能与其他元素交换。
// 
//
// 示例 3： 
//
// 输入：nums = [10,5,9,3,15]
//输出：true
//解释：
//可以执行下述操作完成对 [10,5,9,3,15] 的排序：
//- 交换 10 和 15 因为 gcd(10,15) = 5 。nums = [15,5,9,3,10]
//- 交换 15 和 3 因为 gcd(15,3) = 3 。nums = [3,5,9,15,10]
//- 交换 10 和 15 因为 gcd(10,15) = 5 。nums = [3,5,9,10,15]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// 2 <= nums[i] <= 10⁵ 
// 
// Related Topics 并查集 数组 数学 排序 👍 16 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
private:
    vector<int> root;
    vector<int> size;

    int find(int idx) {
        while (idx != root[idx]) {
            root[idx] = root[root[idx]];
            idx = root[idx];
        }
        return idx;
    }

    void merge(int idx1, int idx2) {
        int set1 = find(idx1), set2 = find(idx2);
        if (set1 == set2) {
            return;
        }
        if (size[set1] < size[set2]) {
            root[set1] = set2;
            size[set2] += size[set1];
        }
        else {
            root[set2] = set1;
            size[set1] += size[set2];
        }
    }

    bool connected(int idx1, int idx2) {
        return find(idx1) == find(idx2);
    }

public:
    bool gcdSort(vector<int>& nums) {
        vector<int> back(nums);
        sort(back.begin(), back.end());
        int maxnum = back[back.size() - 1];
        root = vector<int>(maxnum + 1);
        size = vector(maxnum + 1, 1);
        for (int i = 0; i < maxnum + 1; i++) {
            root[i] = i;
        }
        for (int num: nums) {   // 分解因数(出现因数则连接因数对)
            for (int divisor = 2; divisor <= num / divisor; divisor++) {
                int temp = num;
                // 不能和1连接
                if (temp > divisor && temp % divisor == 0) {
                    temp /= divisor;
                    merge(num, temp);
                    merge(divisor, temp);
                }
            }
        }
        for (int i = 0; i < nums.size(); i++) {
            if (!connected(nums[i], back[i])) {
                return false;
            }
        }
        return true;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
