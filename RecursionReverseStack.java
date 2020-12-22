package algorithm;

import java.beans.Statement;
import java.util.Stack;

/**
 * Created by IDEA
 * User：fenngwang
 * Date：2020/12/21
 * Time：22:20
 * 【题目】
        一个栈依次压入 1、 2、 3、 4、 5， 那么从栈顶到栈底分别为 5、 4、 3、 2、 1。将这个栈转置
   后，从栈顶到栈底为 1、 2、 3、 4、 5，也就是实现栈中元素的逆序，但是只能用递归函数来实
   现， 不能用其他数据结构。
 */
public class RecursionReverseStack
{

    private Stack<Integer> stack = new Stack<>();


    // 取栈最底部的元素，并删除（递归实现）
    public static int getAndRemoveBottomData(Stack<Integer> stack)
    {
        int top = stack.pop();
        if(!stack.empty())
        {
            int bottom = getAndRemoveBottomData(stack);
            stack.push(top);
            return bottom;
        }
        else
        {
            return top;
        }
    }


    // 然后再递归的清空栈，并插入数据
    public static void reverse(Stack<Integer> stack)
    {
        if (!stack.empty())
        {
            int value = getAndRemoveBottomData(stack);
            reverse(stack);
            stack.push(value);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        reverse(stack);
        System.out.println(stack);
    }
}
