package tree;

/**
 * 二叉树学习
 *
 * 使用前序，中序和后序对下面的二叉树进行遍历.
 * 1) 前序遍历: 先输出父节点，再遍历左子树和右子树
 * 2) 中序遍历: 先遍历左子树，再输出父节点，再遍历右子树
 * 3) 后序遍历: 先遍历左子树，再遍历右子树，最后输出父节点
 * 4) 小结: 看输出父节点的顺序，就确定是前序，中序还是后序
 * */
public class BinaryTreeDemo {


    public static void main(String[] args){
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node1 = new HeroNode(2,"吴用");
        HeroNode node2 = new HeroNode(3,"卢俊义");
        HeroNode node3 = new HeroNode(4,"武松");
        HeroNode node4 = new HeroNode(5,"关胜");

        root.setLeft(node1);
        root.setRight(node2);
        node2.setLeft(node4);
        node2.setRight(node3);

        binaryTree.setRoot(root);

        System.out.println("前序遍历：");// 1,2,3,5,4
        binaryTree.preOrder();

        System.out.println("中序遍历"); // 2,1,5,3,4
        binaryTree.infixOrder();

        System.out.println("后序遍历"); //2，5，4，3，1
        binaryTree.postOrder();
    }



    static class BinaryTree{
       private   HeroNode root;



        public void setRoot(HeroNode root) {
            this.root = root;
        }

        public HeroNode getRoot() {
            return root;
        }

        /**
         * 前序遍历
         * */
        public void preOrder(){
            if(root != null){
                root.preOrder();
            }else{
                System.out.println("二叉树为空，无法遍历！");
            }
        }

        /**
         * 中序遍历
         * */
        public void infixOrder(){
            if(root != null){
                root.infixOrder();
            }else{
                System.out.println("二叉树为空，无法遍历！");
            }
        }

        /**
         * 后序遍历
         * */
        public void postOrder(){
            if(root != null){
                root.postOrder();
            }else{
                System.out.println("二叉树为空，无法遍历！");
            }
        }
    }

    /**
     * 先创建节点
     * */
    static class HeroNode{
      private int id;
      private String name;
      private HeroNode left;
      private HeroNode right;

        public HeroNode(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void setLeft(HeroNode left) {
            this.left = left;
        }

        public void setRight(HeroNode right) {
            this.right = right;
        }

        public HeroNode getLeft() {
            return left;
        }

        public HeroNode getRight() {
            return right;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        /**
         * 前序遍历
         *  先输出父节点，然后是左节点，最后是右节点
         * */
        public void preOrder(){
             System.out.println(this);

             if(this.getLeft() != null){
                 this.getLeft().preOrder();
             }

             if(this.getRight() != null){
                 this.getRight().preOrder();
             }
        }

        /**
         * 中序遍历
         *  先输出左子树，然后输出父节点，最后输出右节点
         * */
        public void infixOrder(){
            if(this.getLeft() != null){
               this.getLeft().infixOrder();
            }

            System.out.println(this);

            if(this.getRight() != null){
                this.getRight().infixOrder();
            }
        }

        /**
         * 后序遍历
         *
         *  先输出左子树，再输出右子树，最后输出父节点
         * */
        public void postOrder(){
            if(this.getLeft() != null){
                this.getLeft().postOrder();
            }

            if(this.getRight() != null){
                this.getRight().postOrder();
            }

            System.out.println(this);
        }



    }


}
