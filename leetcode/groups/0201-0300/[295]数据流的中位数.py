# 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。 
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
#  示例： 
# 
#  addNum(1)
# addNum(2)
# findMedian() -> 1.5
# addNum(3) 
# findMedian() -> 2 
# 
#  进阶: 
# 
#  
#  如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
#  如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
#  
#  Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 463 👎 0


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
