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
     }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while(current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;

        // 为什么下面不通过？
        // if (head == null) {
        //     return null;
        // }

        // ListNode prev = head;
        // ListNode current = head.next;
        // while (current != null)
        // {
        //     ListNode next = current.next;
        //     current.next = prev;
        //     prev = current;
        //     current = next;
        // }
        // return prev;
    }

//    // 递归的反转链表
//    public ListNode reverseLinkRecursive(ListNode head)
//    {
//        ListNode prev = null;
//        ListNode current = head;
//        if(current == null)
//            return prev;
//
//        ListNode next = current.next;
//        current.next = prev;
//        prev = current;
//        current = next;
//        return reverseLinkRecursive(current);
//    }
}