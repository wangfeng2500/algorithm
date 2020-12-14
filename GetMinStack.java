package algorithm;

import java.util.Stack;

/**
 * Created by IDEA
 * User：fenngwang
 * Date：2020/12/14
 * Time：21:14
 * Desc：
 【题目】实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 【要求】1.pop、push、getMin操作的时间复杂度都是O（1）。
        2.设计的栈类型可以使用现成的栈结构。
 */
public class GetMinStack
{
    private Stack<Integer> valueStack = new Stack<>();

    private Stack<Integer> minValueStack = new Stack<>(); // 存放最小值


    public void pushStack(int i)
    {
        valueStack.push(i);
        if(minValueStack.isEmpty())
        {
            minValueStack.push(i);
        }
        else if(i <= minValueStack.peek())
        {
            minValueStack.push(i);
        }
    }

    public void popStack()
    {
        int head = valueStack.pop();
        if(head <= minValueStack.peek())  //出队的时候如果数字比最小值小，则最小值也出去
        {
            minValueStack.pop();
        }
    }

    public int getMin()
    {
        return minValueStack.peek();
    }


    public String toString(){
        return  valueStack.toString();
    }

    public static void main(String[] args) {
        GetMinStack minStack = new GetMinStack();
        minStack.pushStack(10);
        System.out.println(minStack + ",min is " + minStack.getMin());
        minStack.pushStack(9);
        System.out.println(minStack + ",min is " + minStack.getMin());
        minStack.pushStack(9);
        System.out.println(minStack + ",min is " + minStack.getMin());
        minStack.pushStack(1);
        System.out.println(minStack + ",min is " + minStack.getMin());
        minStack.pushStack(8);
        System.out.println(minStack + ",min is " + minStack.getMin());
        minStack.popStack();
        System.out.println(minStack + ",min is " + minStack.getMin());
        minStack.popStack();
        System.out.println(minStack + ",min is " + minStack.getMin());
        minStack.popStack();
        System.out.println(minStack + ",min is " + minStack.getMin());
        minStack.popStack();
        System.out.println(minStack + ",min is " + minStack.getMin());
    }
}
