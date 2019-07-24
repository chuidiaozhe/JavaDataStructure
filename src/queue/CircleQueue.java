package queue;

import java.awt.*;

/**
 * @link {https://www.bilibili.com/video/av54029771/?p=16}
 * */

public class CircleQueue<T> implements IQueue<T> {
    //表示数组的最大容量
    private int maxSize ;
    //front就是指向队列的第一个元素，
    private int front;
    //rear指向队列的最后一个元素的一个位置，因为希望空出一个空间做为约定
    private int rear;
    //该数组用于存放数据，模拟队列
    private Object[] arr;

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new Object[maxSize];
    }

    @Override
    public boolean isFull() {
        return (rear+1)%maxSize == front;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public void addQueue(T t) {
      //判断队列是否满
       if(isFull()){
           System.out.println("队列满，不能加入数据～～");
           return;
       }

       //直接将数据加入
        arr[rear] = t;
       rear = (rear+1)%maxSize;

    }

    @Override
    public T getQueue() {
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front始指向队列的第一个元素
        //1.先将front对应的值保存到一个临时变量
        //2.front后移
        //3.将临时保存的变量返回
        T value = (T) arr[front];
        front = (front+1)%maxSize;
        return value;
    }

    @Override
    public void showQueue() {
        if(isEmpty()){
            System.out.println("队列空的，没有数据~~~");
            return;
        }
        for (int i = front; i < front + size();i++){
            System.out.printf("arr[%d]=%s\n",i%maxSize,arr[i].toString());
        }

    }

    @Override
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    @Override
    public T headQueue() {
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据~~");
        }
        return (T) arr[front];
    }
}
