//给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。 
//
//
// 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。 
//
//
// 
// 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。 
// 
//
// 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。 
//
// 
//
// 示例 1： 
//
// 
//输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
//输出：["JFK","MUC","LHR","SFO","SJC"]
// 
//
// 示例 2： 
//
// 
//输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL",
//"SFO"]]
//输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
//解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= tickets.length <= 300 
// tickets[i].length == 2 
// fromi.length == 3 
// toi.length == 3 
// fromi 和 toi 由大写英文字母组成 
// fromi != toi 
// 
// Related Topics 深度优先搜索 图 欧拉回路 👍 439 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    HashMap<String, PriorityQueue<String>> container = new HashMap<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> ans = new LinkedList<>();
        for (List<String> ticket : tickets) {
            if (!container.containsKey(ticket.get(0))) {
                container.put(ticket.get(0), new PriorityQueue<>());
            }
            container.get(ticket.get(0)).add(ticket.get(1));
        }
        dfs("JFK", ans);
        return ans;
    }

    void dfs(String node, LinkedList<String> ans) {
        PriorityQueue<String> pq = container.get(node);
        if (pq == null) {
            ans.addFirst(node);
            return;
        }
        while (!pq.isEmpty()) {
            dfs(pq.poll(), ans);
        }
        ans.addFirst(node);
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    List<String> ans;
    List<List<String>> tickets;
    LinkedList<String> sublist = new LinkedList<>();
    HashMap<String, List<String>> fromTo = new HashMap<>();     // 起点 -> 终点
    HashMap<String, Integer> ticketCount = new HashMap<>();     // 起点终点 -> 机票剩余数量(可能有重复票)

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            if (!fromTo.containsKey(ticket.get(0))) {
                fromTo.put(ticket.get(0), new ArrayList<>());
            }
            fromTo.get(ticket.get(0)).add(ticket.get(1));
            String temp = ticket.get(0) + ticket.get(1);
            ticketCount.put(temp, ticketCount.getOrDefault(temp, 0) + 1);
        }
        for (List<String> list: fromTo.values()) {
            Collections.sort(list);
        }
        this.tickets = tickets;
        sublist.add("JFK");
        backtrack(0, "JFK");
        return ans;
    }

    private void backtrack(int numused, String loc) {
        if (numused == tickets.size()) {
            ans = new ArrayList<>(sublist);
            return;
        }
        if (!fromTo.containsKey(loc)) {
            return;
        }
        for (String to: fromTo.get(loc)) {
            if (ans != null) {
                return;
            }
            String ticket = loc + to;
            int count = ticketCount.get(ticket);
            if (count == 0) {
                continue;
            }
            sublist.add(to);
            ticketCount.put(ticket, count - 1);
            backtrack(numused + 1, to);
            sublist.removeLast();
            ticketCount.put(ticket, count);
        }
    }
}
