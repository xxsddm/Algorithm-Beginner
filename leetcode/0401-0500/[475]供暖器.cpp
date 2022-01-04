//冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。 
//
// 在加热器的加热半径范围内的每个房屋都可以获得供暖。 
//
// 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。 
//
// 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。 
//
// 
//
// 示例 1: 
//
// 
//输入: houses = [1,2,3], heaters = [2]
//输出: 1
//解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
// 
//
// 示例 2: 
//
// 
//输入: houses = [1,2,3,4], heaters = [1,4]
//输出: 1
//解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
// 
//
// 示例 3： 
//
// 
//输入：houses = [1,5], heaters = [2]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= houses.length, heaters.length <= 3 * 10⁴ 
// 1 <= houses[i], heaters[i] <= 10⁹ 
// 
// Related Topics 数组 双指针 二分查找 排序 👍 265 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int findRadius(vector<int>& houses, vector<int>& heaters) { // 二分
        sort(houses.begin(), houses.end());
        sort(heaters.begin(), heaters.end());
        int left = 0, right = max(houses[(int) houses.size() - 1] - heaters[0],
                                  heaters[(int) heaters.size() - 1] - houses[0]);
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (check(mid, houses, heaters)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    bool check(int r, vector<int>& houses, vector<int>& heaters) {
        int i = 0, j = 0, len1 = (int) houses.size(), len2 = (int) heaters.size();
        int start = heaters[0] - r, end = heaters[0] + r;
        while (i < len1) {
            if (houses[i] > end) {
                if (++j == len2) {
                    return false;
                }
                start = heaters[j] - r, end = heaters[j] + r;
            } else if (houses[i] < start) {
                return false;
            } else {
                i++;
            }
        }
        return true;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
