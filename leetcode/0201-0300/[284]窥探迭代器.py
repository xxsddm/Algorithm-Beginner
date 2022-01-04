# 请你设计一个迭代器，除了支持 hasNext 和 next 操作外，还支持 peek 操作。 
# 
#  实现 PeekingIterator 类： 
# 
#  
#  
#  
#  PeekingIterator(int[] nums) 使用指定整数数组 nums 初始化迭代器。 
#  int next() 返回数组中的下一个元素，并将指针移动到下个元素处。 
#  bool hasNext() 如果数组中存在下一个元素，返回 true ；否则，返回 false 。 
#  int peek() 返回数组中的下一个元素，但 不 移动指针。 
#  
# 
#  
# 
#  示例： 
# 
#  
# 输入：
# ["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
# [[[1, 2, 3]], [], [], [], [], []]
# 输出：
# [null, 1, 2, 2, 3, false]
# 
# 解释：
# PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
# peekingIterator.next();    // 返回 1 ，指针移动到下一个元素 [1,2,3]
# peekingIterator.peek();    // 返回 2 ，指针未发生移动 [1,2,3]
# peekingIterator.next();    // 返回 2 ，指针移动到下一个元素 [1,2,3]
# peekingIterator.next();    // 返回 3 ，指针移动到下一个元素 [1,2,3]
# peekingIterator.hasNext(); // 返回 False
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 1000 
#  1 <= nums[i] <= 1000 
#  对 next 和 peek 的调用均有效 
#  next、hasNext 和 peek 最多调用 1000 次 
#  
#  
#  
# 
#  
# 
#  进阶：你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？ 
#  Related Topics 设计 数组 迭代器 👍 97 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Below is the interface for Iterator, which is already defined for you.
#
# class Iterator:
#     def __init__(self, nums):
#         """
#         Initializes an iterator object to the beginning of a list.
#         :type nums: List[int]
#         """
#
#     def hasNext(self):
#         """
#         Returns true if the iteration has more elements.
#         :rtype: bool
#         """
#
#     def next(self):
#         """
#         Returns the next element in the iteration.
#         :rtype: int
#         """

class PeekingIterator:
    def __init__(self, iterator):
        """
        Initialize your data structure here.
        :type iterator: Iterator
        """
        self.iterator = iterator
        # 没看懂为什么不能直接self.next(报错not callable)
        self.numNext = self.iterator.next()

    def peek(self):
        """
        Returns the next element in the iteration without advancing the iterator.
        :rtype: int
        """
        return self.numNext

    def next(self):
        """
        :rtype: int
        """
        temp = self.numNext
        self.numNext = self.iterator.next() if self.iterator.hasNext() else None
        return temp

    def hasNext(self):
        """
        :rtype: bool
        """
        return self.numNext is not None

# Your PeekingIterator object will be instantiated and called as such:
# iter = PeekingIterator(Iterator(nums))
# while iter.hasNext():
#     val = iter.peek()   # Get the next element but not advance the iterator.
#     iter.next()         # Should return the same value as [val].
# leetcode submit region end(Prohibit modification and deletion)
