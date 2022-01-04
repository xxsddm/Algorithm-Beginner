//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -10⁴ <= matrix[i][j], target <= 10⁴ 
// 
// Related Topics 数组 二分查找 矩阵 👍 495 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
func searchMatrix(matrix [][]int, target int) bool {    // 二分
    bottom, top, left, right := 0, len(matrix) - 1, 0, len(matrix[0]) - 1
    // 选择行
    for bottom <= top {
        mid := (bottom + top) >> 1
        if matrix[mid][0] > target {
            top = mid - 1
        } else if matrix[mid][0] < target {
            bottom = mid + 1
        } else {
            return true
        }
    }

    if bottom == 0 {
        return false
    }
    // 选择列
    vector := matrix[bottom - 1]
    for left <= right {
        mid := (left + right) >> 1
        if vector[mid] > target {
            right = mid - 1
        } else if vector[mid] < target {
            left = mid + 1
        } else {
            return true
        }
    }

    return false
}

//leetcode submit region end(Prohibit modification and deletion)


func searchMatrix(matrix [][]int, target int) bool {    // 二分
    // 找不到则返回len(matrix), 即直接查找最后一行
    row := sort.Search(len(matrix), func(i int) bool {return matrix[i][0] > target}) - 1
    if row == - 1 {
        return false
    }
    vector := matrix[row]
    column := sort.SearchInts(vector, target)   // 找不到则返回len(vector)
    return column < len(vector) && vector[column] == target
}
