package algorithm.Link;

/**
反转一个单链表。

        示例:

        输入: 1->2->3->4->5->NULL
        输出: 5->4->3->2->1->NULL
        进阶:
        你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

        作者：力扣 (LeetCode)
        链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnnhm6/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 **/
class ReverseSingleLink {

    static class ListNode {
        int val;
        ListNode next;

        public void print() {
            System.out.print(val);
            if (this.next != null) {
                System.out.print("->");
                this.next.print();
            } else {
                System.out.println();
            }
        }
    }

    // 迭代反转单链表
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while(current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode();
        node1.val = 1;
        ListNode node2 = new ListNode();
        node2.val = 2;
        ListNode node3 = new ListNode();
        node3.val = 3;
        ListNode node4 = new ListNode();
        node4.val = 4;
        ListNode node5 = new ListNode();
        node5.val = 5;

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node1.print();
        ListNode newList = reverseLinkRecursive(node1);
        newList.print();
    }



    // 递归的反转链表，其实这个看的不是很懂
    // 参考：https://labuladong.gitbook.io/algo/mu-lu-ye-1/mu-lu-ye/di-gui-fan-zhuan-lian-biao-de-yi-bu-fen
    public static ListNode reverseLinkRecursive(ListNode head)
    {
        if(head == null || head.next == null){
            return head;
        }

        System.out.print("before: ");
        head.print();
        // 遍历到链表尾部
        ListNode newHead = reverseLinkRecursive(head.next);
        System.out.print("after: ");
        newHead.print();

        // 反转
        head.next.next = head;
        head.next = null;

        return newHead;
    }


}