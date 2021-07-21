package algorithm.Link;

/**
   反转单链表题目集合
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

    /*
     *  题目1，单链表反转，easy难度
     *  解法：迭代反转
     */
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

    /*
     * 题目1，单链表反转，easy难度
     * 解法：递归
     * 备注：这个看的不是很懂，参考：https://labuladong.gitbook.io/algo/mu-lu-ye-1/mu-lu-ye/di-gui-fan-zhuan-lian-biao-de-yi-bu-fen
     * FIXME::没想出来
     */
    public static ListNode reverseLinkRecursive(ListNode head)
    {
        if(head.next == null){
            return head;
        }

        System.out.print("before: ");
        head.print();

        // 遍历到链表尾部
        ListNode newHead = reverseLinkRecursive(head.next);
        System.out.print("after: ");
        newHead.print();

        // 把反转后的尾节点指向当前节点
        head.next.next = head;
        head.next = null;

        return newHead;
    }


    /*
     *  题目2，反转链表的前N个节点，easy难度
     *  解法：迭代反转
     */
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

    /*
     *  题目3，反转链表的第M到第N个节点区间，middle难度
     *  解法：迭代反转
     *  参考：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/
     *  FIXME:: 这个题我没做出来，看的答案，虚拟节点这个想法很好，因为m是头节点或者非头节点时，反转后再连接的处理方式是不一样的，非常复杂，增加一个虚拟
                节点，极大的简化了问题
     */
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

    /*
     *  题目3，反转链表的第M到第N个节点区间，middle难度
     *  解法：另一种迭代解法
     *  参考：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/
     *  FIXME: reverseMN极端情况，需要遍历两遍链表，这里改用头插法，在遍历的过程中，直接把节点插入到反转区的头部来
     */
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

    /*
     * 题目4：k个一组反转链表，比如1->2->3->4->5, k = 2，则为2->1->4->3->5
     *                    比如1->2->3->4->5, k = 3, 则为3->2->1->4->5，最后剩余的不足K的节点不反转，hard难度的题目
     * 参考：https://mp.weixin.qq.com/s/A-dQ9spsP_Iu1Y4iCRP9nA
     * FIXME:: 这个我也没做出来
     */
    public static ListNode reverseKGroup(ListNode head, int k){
        // 使用递归进行，反转一段，然后这一段的尾节点连接到下一段的头节点

        ListNode a = head;
        ListNode b = head;
        for(int i = 0; i < k; ++i){
            if(b == null){
                return head; // 不足k个的，不需要反转，base case
            }
            b = b.next;
        }
        ListNode newHead = reverseList(a,b);
        a.next = reverseKGroup(b,k);
        return newHead;
    }

    /** 反转区间 [a, b) 的元素，注意是左闭右开
     *  注意：这里只管反转这一段
     */
    private static ListNode reverseList(ListNode a, ListNode b)
    {
        ListNode pre, cur, nxt;
        pre = null; cur = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
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
//        ListNode newList = reverseKGroup(node1,2);
        ListNode newList = reverseLinkRecursive(node1);
        newList.print();
    }

}