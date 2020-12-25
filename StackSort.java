package algorithm;


import java.util.Stack;

/**
 * Created by IDEA
 * User：fenngwang
 * Date：2020/12/14
 * Time：16:54
 * Desc：
  【题目】
   一个栈中元素的类型为整型，现在想将该栈从顶到底按从大到小的顺序排序，只许申请一个栈。除此之外，可以申请新的变量，但不能申请额外的数据结构。如何完成排序？
 */
public class StackSort {

    /*
     * 方法1：类似选择排序，利用辅助tempStack，从stack中挑出最小的元素，然后再压回stack，然后从stack的下一个元素开始继续重复
     */
    public static void Sort(Stack<Integer> stack)
    {
        Stack<Integer> tempStack = new Stack<>();
        int length = stack.size();
        for(int i = length; i >= 0; --i) // 每次遍历后，stack栈底都是最小的
        {
            int j = 0;
            while (j++ < i)
            {
                int value = stack.pop();
                if(tempStack.empty())
                {
                    tempStack.push(value);
                }
                else if(tempStack.peek() < value)
                {
                    int tempValue = tempStack.pop();
                    tempStack.push(value);
                    tempStack.push(tempValue);
                }
                else
                {
                    tempStack.push(value);
                }
            }

            while (!tempStack.empty())
            {
                stack.push(tempStack.pop());
            }
        }
    }

    public static void Sort2(Stack<Integer> stack)
    {
        Stack<Integer> tempStack = new Stack<>();
        while (!stack.empty())
        {
            int value = stack.pop();
            if(tempStack.empty() || tempStack.peek() <= value)
            {
                tempStack.push(value);
            }
            else
            {
                while (!tempStack.empty())
                {
                    if(tempStack.peek() >= value)
                    {
                        stack.push(tempStack.pop());
                    }
                    else
                    {
                        break;
                    }
                }
                tempStack.push(value);
            }
        }

        while (!tempStack.empty())
        {
            stack.push(tempStack.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(6);
        stack.push(1);
        stack.push(5);
        stack.push(3);
        stack.push(2);
        stack.push(7);
        stack.push(2);


        System.out.println(stack);

//      Sort(stack);
//      System.out.println(stack);

        Sort2(stack);
        System.out.println(stack);


    }
}
