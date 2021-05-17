package algorithm.Link;
import algorithm.Link.ReverseSingleLink.ListNode;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 * 1---->2----->3---->4---->5
 * ｜
 * ｜
 * head
 */
public class RemoveLinkNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        int i = 0;
        while (i < n)
        {
            second = second.next;
            i++;
        }

        // 如果已经到头了，说明倒数第N就是头节点了，不考虑n小于链表长度
        if(second == null)
        {
            return head.next;
        }

        // 找到倒数第N个节点的前一个
        while (second.next != null) {
            second = second.next;
            first = first.next;   //这里最终first不是倒数第n个节点，他是倒数第n+1个节点
        }

        first.next = first.next.next;
        return head;
    }
}
