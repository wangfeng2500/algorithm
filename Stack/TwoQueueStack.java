package algorithm.Stack;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IDEA
 * User：fenngwang
 * Date：2020/12/16
 * Time：21:28
 *【题目】
     编写一个类，用两个队列实现栈，支持栈的基本操作（push、pop）。
  【方案】
     将插入的数据直接排好序，后插入的数据在列队的头部，先插入的数据在列队尾部
     参考：https://www.jianshu.com/p/c33ce2389abe
 */
public class TwoQueueStack
{
    private Queue<Integer> oneQueue = new LinkedBlockingQueue<>();
    private Queue<Integer> TwoQueue = new LinkedBlockingQueue<>();

    // 数据插入空队列，然后将非空队列的数据移动到之前的空队列，这样就是倒序了
    public void push(Integer value)
    {
        if(oneQueue.isEmpty())
        {
            oneQueue.add(value);
            while (!TwoQueue.isEmpty())
            {
                int oldValue = TwoQueue.poll();
                oneQueue.add(oldValue);
            }
        }
        else
        {
            TwoQueue.add(value);
            while (!oneQueue.isEmpty())
            {
               int oldValue = oneQueue.poll();
                TwoQueue.add(oldValue);
            }
        }
    }

    public Integer pop()
    {
        if(!oneQueue.isEmpty())
        {
            return oneQueue.poll();
        }
        else
        {
            return TwoQueue.poll();
        }
    }

    public static void main(String[] args) {
        TwoQueueStack queueStack = new TwoQueueStack();
        queueStack.push(1);
        queueStack.push(2);
        System.out.println(queueStack.pop());
        queueStack.push(3);
        System.out.println(queueStack.pop());
        System.out.println(queueStack.pop());
    }
}
