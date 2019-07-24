package queue;

public interface IQueue<T> {
    //判断队列是否已满
    boolean isFull();
    //判断队列是否为空
    boolean isEmpty();
    //添加数据到队列
    void addQueue(T t);
    //数据出队列
    T getQueue();
    //显示队列的所有数据
    void showQueue();
    //求出当前队列有效的数据的个数
    int size();
    //显示队列的头部数据，不是取出数据
    T headQueue();
}
