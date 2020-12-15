package algorithm;

import java.util.Stack;

/**
 * Created by IDEA
 * User：fenngwang
 * Date：2020/12/15
 * Time：11:26
 * Desc:
 【题目】
  编写一个类，用两个栈实现队列，支持队列的基本操作（add、poll、peek）。
 */
public class TwoStackQueue {
    private Stack<Integer> pushStack =  new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    public void add(Integer value)
    {
        pushStack.push(value);

        if(popStack.empty()) {
            while (!pushStack.empty()){
                popStack.push(pushStack.pop());
            }
        }
    }

    public Integer poll()
    {
        if(pushStack.empty() && popStack.empty()) {
            return null;
        }

        if(popStack.empty()) {
            while (!pushStack.empty()){
                popStack.push(pushStack.pop());
            }
        }

        return popStack.pop();
    }

    public Integer peek() {
        if(pushStack.empty() && popStack.empty()) {
            return null;
        }

        if(popStack.empty()) {
            while (!pushStack.empty()){
                popStack.push(pushStack.pop());
            }
        }

        return popStack.peek();
    }

    public static void main(String[] args) {
        TwoStackQueue stackQueue = new TwoStackQueue();
        stackQueue.add(1);
        System.out.println(stackQueue.poll());

        stackQueue.add(2);
        stackQueue.add(3);
        System.out.println(stackQueue.poll());
    }
}
