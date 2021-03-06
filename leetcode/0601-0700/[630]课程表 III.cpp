//这里有 n 门不同的在线课程，他们按从 1 到 n 编号。每一门课程有一定的持续上课时间（课程时间）t 以及关闭时间第 d 天。一门课要持续学习 t 天直到
//第 d 天时要完成，你将会从第 1 天开始。 
//
// 给出 n 个在线课程用 (t, d) 对表示。你的任务是找出最多可以修几门课。 
//
// 
//
// 示例： 
//
// 
//输入: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
//输出: 3
//解释: 
//这里一共有 4 门课程, 但是你最多可以修 3 门:
//首先, 修第一门课时, 它要耗费 100 天，你会在第 100 天完成, 在第 101 天准备下门课。
//第二, 修第三门课时, 它会耗费 1000 天，所以你将在第 1100 天的时候完成它, 以及在第 1101 天开始准备下门课程。
//第三, 修第二门课时, 它会耗时 200 天，所以你将会在第 1300 天时完成它。
//第四门课现在不能修，因为你将会在第 3300 天完成它，这已经超出了关闭日期。 
//
// 
//
// 提示: 
//
// 
// 整数 1 <= d, t, n <= 10,000 。 
// 你不能同时修两门课程。 
// 
//
// 
// Related Topics 贪心 数组 堆（优先队列） 👍 162 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int scheduleCourse(vector<vector<int>>& courses) {
        sort(courses.begin(), courses.end(), [](const vector<int> &o1, vector<int> &o2) {
            return o1[1] < o2[1];
        });
        int total = 0;
        priority_queue<int> container;
        for (const auto &course: courses) {
            int cost = course[0];
            if (total + cost <= course[1]) {
                total += cost;
                container.push(cost);
            }
            else if (!container.empty() && container.top() > cost) {
                total -= container.top();
                total += cost;
                container.pop();
                container.push(cost);
            }
        }
        return (int) container.size();
    }
};

//leetcode submit region end(Prohibit modification and deletion)
