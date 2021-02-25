package algorithm.Link;

import java.util.HashMap;


/*
 * Desc: 使用双向链表+HashMap得到的LRU算法，最近访问的在队头，老的在队尾，新增数据时入队头，若容量不够了，则删除队尾，总之就是保留最新的
 *
 */
public class LRU {

    static class DoubleLinkNode {
        int key;
        int value;
        DoubleLinkNode prev;
        DoubleLinkNode next;
    }

    private HashMap<Integer, DoubleLinkNode> valueMap = new HashMap<Integer, DoubleLinkNode>();

    private DoubleLinkNode head;

    private DoubleLinkNode tail;

    private int count = 0;

    private int capacity = 0;

    public LRU(int capacity)
    {
        this.capacity = capacity;

        head = new DoubleLinkNode();
        head.prev = null;

        tail = new DoubleLinkNode();
        tail.next = null;

        head.next = tail;
        tail.prev = head;
    }

    // 删除一个节点
    private int removeNode(DoubleLinkNode node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        return 0;
    }

    // 添加一个节点，添加到头部
    private int addNodeToHead(DoubleLinkNode node)
    {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;

        return 0;
    }

    // 将一个节点移动到队头
    private int moveToHead(DoubleLinkNode node)
    {
        removeNode(node);
        addNodeToHead(node);
        return 0;
    }

    // 访问时，将节点移动到队头
    public int get(int key)
    {
        if(valueMap.containsKey(key))
        {
            DoubleLinkNode node = valueMap.get(key);
            moveToHead(node);
            return node.value;
        }
        return -1;
    }

    // 插入时，若已存在，则移动到队头；若不存在，则加入对头，要注意空间是否足够，不足时删除最老的
    public int set(int key, int value)
    {
        if(!valueMap.containsKey(key))
        {
            DoubleLinkNode newNode = new DoubleLinkNode();
            newNode.value = value;
            newNode.key = key;

            if(count >= capacity)
            {
                DoubleLinkNode deleteNode = tail.prev;
                removeNode(deleteNode);
                --count;
            }

            addNodeToHead(newNode);
            valueMap.put(key,newNode);
            ++count;

        }
        else
        {
            DoubleLinkNode node = valueMap.get(key);
            node.value = value;
            moveToHead(node);
        }
        return 0;
    }

    public void printLink()
    {
        System.out.println("------------------");
        DoubleLinkNode begin = head.next;
        while(begin.next != null)
        {
            System.out.println("Link -- key:" + begin.key + ", value:" + begin.value);
            begin = begin.next;
        }
    }

    public static void main(String[] args) {
        LRU lru = new LRU(3);
        lru.set(1,1);
        lru.printLink();
        lru.set(2,2);
        lru.printLink();
        lru.set(3,3);
        lru.printLink();
        lru.set(1,1);
        lru.printLink();
        lru.set(4,4);
        lru.printLink();
        lru.get(1);
        lru.printLink();
    }
}
