//设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。 
//
// 示例： 
//
// 输入： arr = [1,3,5,7,2,4,6,8], k = 4
//输出： [1,2,3,4]
// 
//
// 提示： 
//
// 
// 0 <= len(arr) <= 100000 
// 0 <= k <= min(100000, len(arr)) 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 109 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
func smallestK(arr []int, k int) []int {    // 快速排序
    if k == 0 {
        return []int{}
    }
    if k == len(arr) {
        return arr
    }
    sort(0, len(arr) - 1, k, &arr)
    return arr[: k]
}

func sort(start, end, k int, nums *[]int) { // 分割元素取右端值(取左端值见java)
    if start >= end {
        return
    }
    arr := *nums
    temp, left, right, idx := arr[end], start - 1, end, start
    for idx < right {
        if arr[idx] > temp {
            right--
            exchange(idx, right, nums)
        } else if arr[idx] < temp {
            left++
            exchange(idx, left, nums)
            idx++
        } else {
            idx++
        }
    }
    exchange(right, end, nums)
    right++
    if end == start + 1 {
        return
    }
    if right <= k - 1 {
        sort(right, end, k, nums)
    } else if left > k - 1 {
        sort(start, left, k, nums)
    }
}

func exchange(i, j int, nums *[]int) {
    arr := *nums
    temp := arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}

//leetcode submit region end(Prohibit modification and deletion)
