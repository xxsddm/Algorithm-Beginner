import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class PriorityQueue<Key> implements Iterable<Key> {
    private int size;
    private Key[] container;
    private final Comparator<Key> comparator;

    public PriorityQueue() {
        this(16, null);
    }

    public PriorityQueue(int n) {
        this(n, null);
    }

    public PriorityQueue(Comparator<Key> compare) {
        this(16, compare);
    }

    public PriorityQueue(int n, Comparator<Key> compare) {
        size = 0;
        container = (Key[]) new Object[n];
        comparator = compare;
    }

    public void add(Key key) {  // 增
        if (size == container.length) {
            resize(container.length * 2);
        }
        container[size] = key;
        up(size++);
    }

    public Key poll() { // 删(同时查)
        if (isEmpty()) {
            return null;
        }
        Key ans = container[0];
        exchange(0, size - 1);
        container[size-- - 1] = null;
        down(0);
        if (size * 2 < container.length) {
            resize((int) (size * 1.2));
        }
        return ans;
    }

    public void delete(Key key) {   // 删(直接删除)
        int idx = - 1;
        for (int i = 0; i < size; i++) {
            if (compare(container[i], key) == 0) {
                idx = i;
                break;
            }
        }
        if (idx == - 1) {
            return;
        }
        exchange(idx, size - 1);
        container[size-- - 1] = null;
        up(idx);
        down(idx);
        if (size * 2 < container.length) {
            resize((int) (size * 1.2));
        }
    }

    public void change(Key from, Key to) {  // 改
        for (int i = 0; i < size; i++) {
            if (compare(container[i], from) == 0) {
                container[i] = to;
                up(i);
                down(i);
                return;
            }
        }
    }

    public void idxChange(int idx, Key key, boolean move) {  // 改(根据索引)
        assert idx >= 0 && idx < size;
        container[idx] = key;
        if (move) {
            up(idx);
            down(idx);
        }
    }

    public Key peek() { // 查
        if (size == 0) {
            return null;
        }
        return container[0];
    }

    public boolean containsKey(Key key) {   // 查(返回是否存在)
        for (Key item: container) {
            if (compare(item, key) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void up(int idx) {  // 小于父节点, 上移
        // 取相同值时退出循环
        while (idx > 0 && compare(container[idx], container[((idx + 1) >> 1) - 1]) < 0) {
            exchange(idx, ((idx + 1) >> 1) - 1);
            idx = ((idx + 1) >> 1) - 1;
        }
    }

    private void down(int idx) {    // 大于子节点, 下移
        while (((idx + 1) << 1) - 1 < size) {
            if (compare(container[((idx + 1) << 1) - 1], container[idx]) < 0) {
                if (((idx + 1) << 1) < size && compare(container[(idx + 1) << 1], container[((idx+ 1) << 1) - 1]) < 0) {
                    exchange(idx, (idx + 1) << 1);
                    idx = (idx + 1) << 1;
                }
                else {
                    exchange(idx, ((idx + 1) << 1) - 1);
                    idx = ((idx + 1) << 1) - 1;
                }
            }
            else if ((idx + 1) << 1 < size && compare(container[(idx + 1) << 1], container[idx]) < 0) {
                exchange(idx, (idx + 1) << 1);
                idx = (idx + 1) << 1;
            }
            else {
                break;
            }
        }
    }

    private void exchange(int i, int j) {
        Key temp = container[i];
        container[i] = container[j];
        container[j] = temp;
    }

    private void resize(int newcapacity) {  // 重置container容量
        assert newcapacity >= size;
        Key[] temp = (Key[]) new Object[newcapacity];
        for (int i = 0; i < size; i++) {
            temp[i] = container[i];
        }
        container = temp;
    }

    private int compare(Key key1, Key key2) {
        if (comparator != null) {
            return comparator.compare(key1, key2);
        }
        return ((Comparable<Key>) key1).compareTo(key2);
    }

    @Override
    public Iterator<Key> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Key> {
        private final PriorityQueue<Key> temp;

        private MyIterator() {
            temp = new PriorityQueue<>(size, comparator);
            for (int i = 0; i < size; i++) {
                temp.idxChange(i, container[i], false);
            }
        }

        public boolean hasNext() {
            return !temp.isEmpty();
        }

        public Key next() {
            if (temp.isEmpty()) {
                return null;
            }
            return temp.poll();
        }

        public void remove() {}
    }

    public static void main(String[] args) {    // 测试(LeetCode 218, 407题测试通过)
        Random generator = new Random();
        Scanner input = new Scanner(System.in);
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 120; i++) {
            line.append('=');
        }
        System.out.println("输入数组长度");
        int length = input.nextInt();
        System.out.println("输入数组下界");
        int low = input.nextInt();
        System.out.println("输入数组上界");
        int high = input.nextInt();
        if (length < 0 || low > high) {
            throw new IllegalArgumentException("参数有误");
        }
        PriorityQueue<Integer> test = new PriorityQueue<>();
        for (int i = 0; i < length; i++) {
            test.add(generator.nextInt(high - low + 1) + low);
        }
        System.out.println(line);
        while (!test.isEmpty()) {
            System.out.println(test.poll());
        }
    }
}
