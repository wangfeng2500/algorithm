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

    // 反转链表的前N个节点
    public static ListNode reverseN(ListNode head, int n){
        if(head == null){
            return null;
        }

        ListNode prev = null;
        ListNode current = head;
        int i = 0;
        while (current != null && i < n){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            i++;
        }
        head.next = current; // 反转的那段的尾部，最后不能指向null，要指向后段的第一个节点
        return prev;
    }

    // 反转链表的第M到第N区间的节点
    // 注意：m是头节点或者非头节点时，反转后，再次连接时处理方式不一样，所以为了简便期间，设置一个虚拟头节点
    // 参考：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/
    // FIXME::这个题我没做出来，看的答案，虚拟节点这个想法很好
    public static ListNode reverseMN(ListNode head, int m, int n){
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;

        // 先找到要反转区间的起点的前一个节点(注意是从虚拟节点开始走)
        ListNode pre = dummyNode;
        for(int i = 0; i < m-1; i++){
            pre = pre.next;
        }

        // 再找到反转区间的最后一个节点
        ListNode right = pre;
        for(int i = 0; i < n-m+1; i++){
            right = right.next;
        }

        // 反转区间节点
        ListNode reverseHeadNode = pre.next;
        ListNode reverseTailNextNode = right.next;
//        pre.next = null;
        right.next = null;  // 这里一定要切断，不然就把right后面的也一起反转了
        reverseList(reverseHeadNode);

        pre.next = right;
        reverseHeadNode.next = reverseTailNextNode;

        return dummyNode.next;
    }

    // reverseMN极端情况，需要便利两遍链表，这里改用头插法，在遍历的过程中，直接把节点插入到反转区的头部来
    // 参考：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/
    public static ListNode reserveMN2(ListNode head, int m, int n){

        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode( );
        dummyNode.next = head;

        // 找到待反转区间的头部的前一个节点
        ListNode pre = dummyNode;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }

        // 这里的跳转也要注意下，我没写对
        ListNode cur = pre.next;
        for(int i = 0; i < n - m; i++){
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
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
        ListNode node6 = new ListNode();
        node6.val = 6;

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        node1.print();
        ListNode newList = reserveMN2(node1,1,5);
        newList.print();
    }






}