package queue;


/**
 *  数组模拟队列
 *
 *  link {https://www.bilibili.com/video/av54029771/?p=13}
 * */
public class QueueArray<T> implements IQueue<T> {
    //表示数组的最大容量
    private int maxSize;
    //front指向队列的开始元素
    private int front;
    //rear指向队列的最后一个元素
    private int rear;
    //该数组用于存放数据，模拟队列
    private Object[] arr;

    public QueueArray(int arrMaxSize){
        maxSize = arrMaxSize;
        front = -1;
        rear = -1;

        arr = new Object[maxSize];
    }

    @Override
    public boolean isFull() {
        return rear == maxSize -1 ;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public void addQueue(T t) {
        if(isFull()){
          System.out.println("队列满，不能加入数据...");
          return;
        }
        rear ++;
        arr[rear] = t;
    }

    @Override
    public T getQueue() {
         if(isEmpty()){
            throw new RuntimeException("队列为空，不能去数据....");
         }
         front ++;
         return (T) arr[front];
     }

    @Override
    public void showQueue() {
        if(isEmpty()){
            System.out.println("队列为空");
         }else{
            for (int i = 0; i < arr.length ; i++) {
                if(arr[i] != null){
                    System.out.println("arr["+i+"] = " + arr[i].toString());
                }
             }
        }
    }

    @Override
    public int size() {
         if(isEmpty()){
             return  0;
         }else{
             return rear - front;
         }
     }

    @Override
    public T headQueue() {
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能去数据....");
        }
        return (T) arr[front+1];
    }
}
