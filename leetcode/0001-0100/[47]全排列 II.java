//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
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
// Related Topics 数组 回溯 
// 👍 768 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> container = new ArrayList<>();
    List<Integer> subcontainer;    // 子序列
    boolean[] visited;             // 已填入子序列的点, 相应位置为true
    int length;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        length = nums.length;
        subcontainer = new ArrayList<>(length);
        visited = new boolean[nums.length];
        permuteUnique(0, nums);
        return container;
    }

    private void permuteUnique(int idx, int[] target) {
        // idx: 子序列subcontainer的待填充元素索引(填满length个算一种组合)
        if (idx == length) {
            container.add(new ArrayList<>(subcontainer));
            return;
        }
        for (int i = 0; i < length; i++) {
            // 1. i点已经填入子序列subcontainer, 跳过.
            // 2. i点与i+1点值相同, 出现连续的重复值, 只有先取其中第1个重复值, 重复该步骤(不断取出剩余连续
            //    重复值中的第1个).
            // ps:若抽出其中一个重复值后, 连续重复值不再连续(即取出的不是其中首端重复值), 则中断点(取出点)的
            //    前一个位置(仍为重复值)不可能再加入子序列subcontainer中, idx无法到达末端, 该子序列不会填
            //    入最后结果container中.
            //    即规定了连续重复值的填入顺序为从前往后(排序后重复值自然连续).

            // 这里的是从前往后填入, 若规定从后往前, 则相应部分改为:
            //                (i >0 && target[i] == target[i - 1] && visited[i - 1])
            if (visited[i] || (i < length - 1 && target[i] == target[i + 1] && visited[i + 1])) {
                continue;
            }
            visited[i] = true;
            subcontainer.add(target[i]);
            permuteUnique(idx + 1, target);
            visited[i] = false;    // 进入下轮循环前重置visited
            subcontainer.remove(idx);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> target = new ArrayList<>(nums.length);
        for (int num: nums)    target.add(num);
        return permuteUnique(target);
    }

    private List<List<Integer>> permuteUnique(List<Integer> nums) {
        int length = nums.size();
        HashSet<List<Integer>> back = new HashSet<>();
        List<List<Integer>> container = new ArrayList<>();
        if (length == 1) {
            List<Integer> tempsub = new ArrayList<>();
            tempsub.add(nums.get(0));
            container.add(tempsub);
            return container;
        }
        for (int i = 0; i < length; i++) {
            int last = nums.get(length - 1);
            nums.set(length - 1, nums.get(i));
            nums.set(i, last);
            int temp = nums.remove(length - 1);
            for (List<Integer> subcontainer: permuteUnique(nums)) {
                subcontainer.add(temp);
                if (!back.contains(subcontainer)) {
                    container.add(subcontainer);
                    back.add(subcontainer);
                }
            }
            nums.add(temp);
            nums.set(i, nums.get(length - 1));
            nums.set(length - 1, last);
        }
        return container;
    }
}
