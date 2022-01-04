//这个问题和“最多能完成排序的块”相似，但给定数组中的元素可以重复，输入数组最大长度为2000，其中的元素最大为10**8。 
//
// arr是一个可能包含重复元素的整数数组，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
// 
//
// 我们最多能将数组分成多少块？ 
//
// 示例 1: 
//
// 
//输入: arr = [5,4,3,2,1]
//输出: 1
//解释:
//将数组分成2块或者更多块，都无法得到所需的结果。
//例如，分成 [5, 4], [3, 2, 1] 的结果是 [4, 5, 1, 2, 3]，这不是有序的数组。 
// 
//
// 示例 2: 
//
// 
//输入: arr = [2,1,3,4,4]
//输出: 4
//解释:
//我们可以把它分成两块，例如 [2, 1], [3, 4, 4]。
//然而，分成 [2, 1], [3], [4], [4] 可以得到最多的块数。 
// 
//
// 注意: 
//
// 
// arr的长度在[1, 2000]之间。 
// arr[i]的大小在[0, 10**8]之间。 
// 
// Related Topics 栈 贪心 数组 排序 单调栈 👍 104 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {   // 单调栈(记录每个块的最大元素,单调非减)
        int length = (int) arr.size();
        stack<int> container;    // 保存当前所有块的上界
        container.push(arr[0]);
        for (int i = 1; i < length; i++) {
            int temp = arr[i];
            if (temp >= container.top()) {
                container.push(temp);
                continue;
            }
            // 当前值小于栈顶元素,则不断合并栈内的块直到temp无需合并入栈顶块
            int upper = container.top();    // 记录合并后块内最大元素
            container.pop();
            while (!container.empty() && temp < container.top()) {
                container.pop();
            }
            container.push(upper);
        }
        return (int) container.size();
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {   // 单调栈(区间,直观但较慢)
        int length = (int) arr.size();
        // 末端为元素最大的块,其最小元素不小于前方块的最大值
        stack<pair<int, int>> container;    // 保存当前所有块的下界和上界(索引first和second)
        container.push(pair {arr[0], arr[0]});
        for (int i = 1; i < length; i++) {
            int temp = arr[i];
            // 1.当前值不小于末端块的最大值,则单独成块
            if (temp >= container.top().second) {
                container.push(pair {temp, temp});
                continue;
            }
            // 2.当前值不小于末端块最小值,且小于末端块最大值,必须包含在末端块中,不作操作
            // 3.当前值小于末端块最小值,必须不断合并前方的块,直到栈空或大于等于末端块最小值
            if (temp < container.top().first) {
                int upper = container.top().second;     // 保存合并后末端块最大值
                while (!container.empty() && temp < container.top().first) {
                    container.pop();
                }
                // 若非空且当前值小于末端块最大值(情况2,隐含不小于末端块最小值)
                if (!container.empty() && container.top().second > temp) {
                    container.top().second = upper;
                } else {
                    // 栈空或情况1
                    container.push(pair {temp, upper});
                }
            }
        }
        return (int) container.size();
    }
};
