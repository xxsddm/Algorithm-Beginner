# 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数
# 值排序之后中间两个数的平均值。 
# 
#  例如， 
# 
#  [2,3,4] 的中位数是 3 
# 
#  [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
# 
#  设计一个支持以下两种操作的数据结构： 
# 
#  
#  void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
#  double findMedian() - 返回目前所有元素的中位数。 
#  
# 
#  示例 1： 
# 
#  输入：
# ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
# [[],[1],[2],[],[3],[]]
# 输出：[null,null,null,1.50000,null,2.00000]
#  
# 
#  示例 2： 
# 
#  输入：
# ["MedianFinder","addNum","findMedian","addNum","findMedian"]
# [[],[2],[],[3],[]]
# 输出：[null,null,2.00000,null,2.50000] 
# 
#  
# 
#  限制： 
# 
#  
#  最多会对 addNum、findMedian 进行 50000 次调用。 
#  
# 
#  注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-
# stream/ 
#  Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 172 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder:
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.size = 0
        self.minHeap = []   # 小根堆
        self.maxHeap = []   # 大根堆

    def addNum(self, num: int) -> None:
        if not self.minHeap or num > self.minHeap[0]:
            heapq.heappush(self.minHeap, num)
        else:
            heapq.heappush(self.maxHeap, - num)
        if len(self.minHeap) > len(self.maxHeap):
            heapq.heappush(self.maxHeap, - heapq.heappop(self.minHeap))
        if len(self.minHeap) < len(self.maxHeap):
            heapq.heappush(self.minHeap, - heapq.heappop(self.maxHeap))
        self.size += 1

    def findMedian(self) -> float:
        if self.size & 1 == 0:
            return (self.minHeap[0] - self.maxHeap[0]) * 0.5
        return float(self.minHeap[0])

# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
# leetcode submit region end(Prohibit modification and deletion)
