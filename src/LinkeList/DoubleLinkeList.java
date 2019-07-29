package LinkeList;


public class DoubleLinkeList {
    private DoubleHeroNode mHeader;

    public DoubleLinkeList() {
        mHeader = new DoubleHeroNode(0,"","");
    }

    /**
     * 添加数据
     * */
    public void add(DoubleHeroNode node){
        DoubleHeroNode temp = mHeader;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }
        temp.setNext(node);
        node.setPre(temp);
    }

    /**
     * 按顺序添加数据
     * */
    public void addById(DoubleHeroNode node){
        DoubleHeroNode currNode = mHeader;
        boolean flag = true;
        while (currNode != null && currNode.getNext() != null){
            if(currNode.getId() == node.getId()){
                flag = false;
                break;
            }
            if(currNode.getId() < node.getId() && currNode.getNext().getId() > node.getId()){
                break;
            }
            currNode = currNode.getNext();
        }
        if(flag ){
            node.setNext(currNode.getNext());
            if(currNode.getNext() != null){
                currNode.getNext().setPre(node);
            }
            node.setPre(currNode);
            currNode.setNext(node);
        }else{
            update(node);
        }
    }

    /**
     * 根据id来删除数据
     * */
    public void del(int id){
       if (mHeader.getNext() == null){
           System.out.println("链表为空，无数据···");
       }else{
           DoubleHeroNode temp = mHeader.getNext();
           boolean flag  = false;
           while (temp != null){
               if(temp.getId() == id){
                   //查询到id相等的数据
                   flag = true;
                   break;
               }
               temp = temp.getNext();
           }

           if(flag){
               temp.getPre().setNext(temp.getNext());
               if(temp.getNext() != null){
                   temp.getNext().setPre(temp.getPre());
               }
           }else{
               System.out.println("删除失败，数据不存在");
            }
        }
    }

    /**
     * 根据id来更新数据
     * */
    public void update(DoubleHeroNode node){
        boolean flag  = false;
        DoubleHeroNode currentNode = mHeader.getNext();
        while(currentNode != null){
            if(currentNode.getId() == node.getId()){
                flag = true;
                break;
            }
           currentNode = currentNode.getNext();
        }
        if(flag){
            currentNode.setName(node.getName());
            currentNode.setNickName(node.getNickName());
            currentNode.setId(node.getId());
        }else{
            add(node);
        }
    }


    /**
     * 遍历输出所有数据
     * */
    public void list(){
       if(mHeader.getNext() == null){
           System.out.println("链表为空，无数据输出···");
       } else{
           DoubleHeroNode temp = mHeader.getNext();
           while (temp != null){
               System.out.println(temp.toString());
               temp = temp.getNext();
           }
       }
    }
}
