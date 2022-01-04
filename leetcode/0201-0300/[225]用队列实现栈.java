//请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。 
//
// 实现 MyStack 类： 
//
// 
// void push(int x) 将元素 x 压入栈顶。 
// int pop() 移除并返回栈顶元素。 
// int top() 返回栈顶元素。 
// boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。 
// 
//
// 
//
// 注意： 
//
// 
// 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。 
// 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MyStack", "push", "push", "top", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 2, 2, false]
//
//解释：
//MyStack myStack = new MyStack();
//myStack.push(1);
//myStack.push(2);
//myStack.top(); // 返回 2
//myStack.pop(); // 返回 2
//myStack.empty(); // 返回 False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= x <= 9 
// 最多调用100 次 push、pop、top 和 empty 
// 每次调用 pop 和 top 都保证栈不为空 
// 
//
// 
//
// 进阶：你能否实现每种操作的均摊时间复杂度为 O(1) 的栈？换句话说，执行 n 个操作的总时间复杂度 O(n) ，尽管其中某个操作可能需要比其他操作更长的
//时间。你可以使用两个以上的队列。 
// Related Topics 栈 设计 队列 
// 👍 356 👎 0


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class MyStack {
    Queue<Integer> container;   // 单队列
    /** Initialize your data structure here. */
    public MyStack() {
        container = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        container.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        for (int i = 0; i < container.size() - 1; i++){  // 把前面的拿出来重新放进队列即可
            container.add(container.remove());
        }
        return container.remove();
    }

    /** Get the top element. */
    public int top() {
        for (int i = 0; i < container.size() - 1; i++){  // 把前面的拿出来重新放进队列即可
            container.add(container.remove());
        }
        int temp = container.remove();
        container.add(temp);
        return temp;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return container.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)


class MyStack {
    Queue<Integer> container1;    // 双队列
    Queue<Integer> container2;
    /** Initialize your data structure here. */
    public MyStack() {
        container1 = new LinkedList<>();
        container2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if (container2.isEmpty())    container1.add(x);
        else container2.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int target;
        if (container2.isEmpty()){
            while (container1.size() > 1){
                container2.add(container1.remove());
            }
            target = container1.remove();
        }
        else{
            while (container2.size() > 1){
                container1.add(container2.remove());
            }
            target = container2.remove();
        }
        return target;
    }

    /** Get the top element. */
    public int top() {
        int target;
        if (container2.isEmpty()){
            while (container1.size() > 1){
                container2.add(container1.remove());
            }
            target = container1.remove();
            container2.add(target);
        }
        else{
            while (container2.size() > 1){
                container1.add(container2.remove());
            }
            target = container2.remove();
            container1.add(target);
        }
        return target;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return container1.isEmpty() && container2.isEmpty();
    }
}
