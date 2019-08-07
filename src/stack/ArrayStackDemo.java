package stack;

/**
 * 用数组模拟栈
 * @see {https://www.bilibili.com/video/av54029771/?p=31}
 * */
public class ArrayStackDemo {

    public static void main(String[] args){
          ArrayStack<String> arrayStack = new ArrayStack<>(8);
          arrayStack.push("第一个");
          arrayStack.push("第二个");
          arrayStack.push("第三个");
          arrayStack.push(null);
          arrayStack.push("第四个");
          arrayStack.push("第五个");

          System.out.println("初始化的值：");
          arrayStack.list();


         System.out.println("pop:" +arrayStack.pop());
         System.out.println("pop:" +arrayStack.pop());

         System.out.println("pop后的值：");
         arrayStack.list();


    }

}

//定义一个ArrayStack表示栈
class ArrayStack<T>{
    public int maxSize;//栈存放的最大值
    public Object[] stack; //用来存放数据的栈
    public int top = -1; // 栈的顶部，初始化为-1

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack  = new Object[maxSize];
    }

    //栈空
    private boolean isEmpty(){
     return  top == -1;
    }

    //栈满
    private boolean isFull(){
        return  top == maxSize -1;
    }

    //入栈-push
    public void push(T value){
        if(isFull()){
            System.out.println("栈已满");
        }else{
            top++;
            stack[top] = value;
        }
    }

    //出栈-pop,将栈顶的数据返回
    public T pop(){
      if(isEmpty()){
          throw  new RuntimeException("栈空，没有数据！");
       }
         Object obj = stack[top];
        top --;
        if(obj == null){
           return  null;
       }else{
           T value = (T) obj;
           return  value;
       }
    }

    //遍历栈
    public void list(){
        if(isEmpty()){
            System.out.println("list ： 栈空，没有数据");
        }else{
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d]=%s\t",i,stack[i] == null? "":stack[i].toString());
            }
            System.out.println();
        }
    }
}