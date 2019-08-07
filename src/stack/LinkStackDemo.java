package stack;

import java.lang.invoke.MethodHandle;
import java.util.Stack;

public class LinkStackDemo {
    public static void main(String[] args) {
        LinkStack<LinkBean> linkStack = new LinkStack<>(new LinkBean(0));
        linkStack.push(new LinkBean(1));
        linkStack.push(new LinkBean(2));
        linkStack.push(new LinkBean(3));
        linkStack.push(new LinkBean(4));
        linkStack.push(new LinkBean(5));

        System.out.println("初始化后的队列：");
        linkStack.list();


        System.out.println(linkStack.pop().toString());
        System.out.println(linkStack.pop().toString());

        System.out.println("pop后的队列：");
        linkStack.list();

    }
}

//用链表的方式实现栈
class LinkStack<T extends ILinkStack> {
    private T mHead;

    public LinkStack(T mHead) {
        this.mHead = mHead;
    }

    private boolean isEmpty() {
        return mHead.getNext() == null;
    }

    //入栈操作
    public void push(T t) {
        T curre = mHead;
        while (curre.getNext() != null) {
            curre = (T) curre.getNext();
        }
        curre.setNext(t);
    }

    //出栈操作
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }

        T curr = (T) mHead.getNext();
        T pre = null;
        while (curr.getNext() != null) {
            if (((T) curr.getNext()).getNext() == null) {
                pre = curr;
            }
            curr = (T) curr.getNext();
        }
        T value = curr;
        if (pre != null) {
            pre.setNext(null);
        }
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("list : 栈空！");
        } else {
            Stack<T> stack = new Stack<>();
            T curr = (T) mHead.getNext();
            while (curr != null) {
                stack.push(curr);
                curr = (T) curr.getNext();
            }
            while (!stack.isEmpty()) {
                System.out.println(stack.pop().toString());
            }

        }
    }
}


class LinkBean implements ILinkStack<LinkBean> {
    private LinkBean next;
    private int no;

    public LinkBean(int no) {
        this.no = no;
    }

    @Override
    public void setNext(LinkBean linkBean) {
        this.next = linkBean;
    }

    @Override
    public LinkBean getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "LinkBean{" +
                "no=" + no +
                '}';
    }
}