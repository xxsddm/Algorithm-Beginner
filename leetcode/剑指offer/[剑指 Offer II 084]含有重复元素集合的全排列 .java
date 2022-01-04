//给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
//
// 
//
// 注意：本题与主站 47 题相同： https://leetcode-cn.com/problems/permutations-ii/ 
// Related Topics 数组 回溯 
// 👍 1 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> container;
    List<Integer> target;
    boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        visited = new boolean[nums.length];
        container = new ArrayList<>();
        target = new ArrayList<>(nums.length);
        for (int num: nums) {
            target.add(num);
        }
        Collections.sort(target);
        permuteUnique(new ArrayList<>(nums.length));
        return container;
    }

    private void permuteUnique(List<Integer> temp) {
        int idx = temp.size();
        if (idx == visited.length) {
            container.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] || i + 1 < visited.length && target.get(i) == target.get(i + 1) && visited[i + 1]) {
                continue;
            }
            visited[i] = true;
            temp.add(target.get(i));
            permuteUnique(temp);
            visited[i] = false;
            temp.remove(idx);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
