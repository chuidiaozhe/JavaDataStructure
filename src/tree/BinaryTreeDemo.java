package tree;

/**
 * 二叉树学习
 * <p>
 * 使用前序，中序和后序对下面的二叉树进行遍历.
 * 1) 前序遍历: 先输出父节点，再遍历左子树和右子树
 * 2) 中序遍历: 先遍历左子树，再输出父节点，再遍历右子树
 * 3) 后序遍历: 先遍历左子树，再遍历右子树，最后输出父节点
 * 4) 小结: 看输出父节点的顺序，就确定是前序，中序还是后序
 */
public class BinaryTreeDemo {


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(2, "吴用");
        HeroNode node2 = new HeroNode(3, "卢俊义");
        HeroNode node3 = new HeroNode(4, "武松");
        HeroNode node4 = new HeroNode(5, "关胜");

        root.setLeft(node1);
        root.setRight(node2);
        node2.setLeft(node4);
        node2.setRight(node3);

        binaryTree.setRoot(root);

//        System.out.println("前序遍历：");// 1,2,3,5,4
//        binaryTree.preOrder();
//
//        System.out.println("中序遍历"); // 2,1,5,3,4
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历"); //2，5，4，3，1
//        binaryTree.postOrder();
//
//
//        int searchNo = 4;
//        //前序查找
//        HeroNode resNode = binaryTree.preOrderSearch(searchNo);
//        System.out.println("前序查找：");
//        if (resNode == null) {
//            System.out.printf("没有找到%d的英雄\n", searchNo);
//        } else {
//            System.out.printf("找到%d  %s\n", resNode.id, resNode.name);
//        }
//
//        resNode = binaryTree.infixOrderSearch(searchNo);
//        System.out.println("中序查找：");
//        if (resNode == null) {
//            System.out.printf("没有找到%d的英雄\n", searchNo);
//        } else {
//            System.out.printf("找到%d  %s\n", resNode.id, resNode.name);
//        }
//
//        resNode = binaryTree.postOrderSearch(searchNo);
//        System.out.println("后序查找：");
//        if (resNode == null) {
//            System.out.printf("没有找到%d的英雄\n", searchNo);
//        } else {
//            System.out.printf("找到%d  %s\n", resNode.id, resNode.name);
//        }

        System.out.println("删除前：");
        binaryTree.preOrder();
        binaryTree.deleteNode(5);
        System.out.println("删除后:");
        binaryTree.preOrder();


    }


    static class BinaryTree {
        private HeroNode root;


        public void setRoot(HeroNode root) {
            this.root = root;
        }

        public HeroNode getRoot() {
            return root;
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            if (root != null) {
                root.preOrder();
            } else {
                System.out.println("二叉树为空，无法遍历！");
            }
        }

        /**
         * 中序遍历
         */
        public void infixOrder() {
            if (root != null) {
                root.infixOrder();
            } else {
                System.out.println("二叉树为空，无法遍历！");
            }
        }

        /**
         * 后序遍历
         */
        public void postOrder() {
            if (root != null) {
                root.postOrder();
            } else {
                System.out.println("二叉树为空，无法遍历！");
            }
        }

        /**
         * 前序遍历查找
         *
         * @param no 查找no
         * @return 如果找到就返回该 Node ,如果没有找到返回 null
         */
        public HeroNode preOrderSearch(int no) {
            if (root == null) {
                return null;
            } else {
                return root.preOrderSearch(no);
            }
        }

        /**
         * 中序遍历查找
         *
         * @param no 查找no
         * @return 如果找到就返回该 Node ,如果没有找到返回 null
         */
        public HeroNode infixOrderSearch(int no) {
            if (root == null) {
                return null;
            } else {
                return root.infixOrderSearch(no);
            }
        }

        /**
         * 后序遍历查找
         *
         * @param no 查找no
         * @return 如果找到就返回该 Node ,如果没有找到返回 null
         */
        public HeroNode postOrderSearch(int no) {
            if (root == null) {
                return null;
            } else {
                return root.postOrderSearch(no);
            }
        }

        public void deleteNode(int no){
            if(root == null){
                System.out.println("空树，不能删除");
            }else{
                //如果只有一个 root 结点, 这里立即判断 root 是不是就是要删除结点
                if(root.id == no){
                    root = null;
                }else{
                    //递归删除
                    root.deleteNode(no);
                }
            }
        }

    }

    /**
     * 先创建节点
     */
    static class HeroNode {
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
         * 先输出父节点，然后是左节点，最后是右节点
         */
        public void preOrder() {
            System.out.println(this);

            if (this.getLeft() != null) {
                this.getLeft().preOrder();
            }

            if (this.getRight() != null) {
                this.getRight().preOrder();
            }
        }

        /**
         * 中序遍历
         * 先输出左子树，然后输出父节点，最后输出右节点
         */
        public void infixOrder() {
            if (this.getLeft() != null) {
                this.getLeft().infixOrder();
            }

            System.out.println(this);

            if (this.getRight() != null) {
                this.getRight().infixOrder();
            }
        }

        /**
         * 后序遍历
         * <p>
         * 先输出左子树，再输出右子树，最后输出父节点
         */
        public void postOrder() {
            if (this.getLeft() != null) {
                this.getLeft().postOrder();
            }

            if (this.getRight() != null) {
                this.getRight().postOrder();
            }

            System.out.println(this);
        }

        /**
         *   前序查找
         */
        public HeroNode preOrderSearch(int no) {
            //比较当前结点是不是
            if (this.id == no) {
                return this;
            }
           //1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
           // 2.如果左递归前序查找，找到结点，则返回
            HeroNode resNode = null;
            if (this.getLeft() != null) {
                resNode = this.getLeft().preOrderSearch(no);
            }
            if (resNode != null) {
                //说明我们左子树找到
                return resNode;
            }
            //1.左递归前序查找，找到结点，则返回，否继续判断，
            // 2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
            if (this.getRight() != null) {
                resNode = this.getRight().preOrderSearch(no);
            }

            return resNode;
        }


        /**
         * 中序查找
         */
        public HeroNode infixOrderSearch(int no) {
            //判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
            HeroNode resNode = null;
            if (this.getLeft() != null) {
                resNode = this.getLeft().infixOrderSearch(no);
            }

            if (resNode != null) {
                return resNode;
            }
            //如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
            if (this.id == no) {
                resNode = this;
            }

            if (resNode != null) {
                return resNode;
            }

            //否则继续进行右递归的中序查找
            if (this.getRight() != null) {
                resNode = this.getRight().infixOrderSearch(no);
            }

            return resNode;
        }

        public HeroNode postOrderSearch(int no) {
            //判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
            HeroNode resNode = null;
            if (this.getLeft() != null) {
                resNode = this.getLeft().postOrderSearch(no);
            }
            if (resNode != null) {
                return resNode;
            }
             //如果左子树没有找到，则向右子树递归进行后序遍历查找
            if (this.getRight() != null) {
                resNode = this.getRight().postOrderSearch(no);
            }
            if (resNode != null) {
                return resNode;
            }
            //如果左右子树都没有找到，就比较当前结点是不是
            if (this.id == no) {
                resNode = this;
            }

            return resNode;
        }
        /**
         * 思路
          1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断 当前这个结点是不是需要删除结点.
          2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将 this.left = null; 并且就返回 (结束递归删除)
          3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将 this.right= null ;并且就返回 (结束递归删除)
          4. 如果第 2 和第 3 步没有删除结点，那么我们就需要向左子树进行递归删除 5. 如果第 4 步也没有删除结点，则应当向右子树进行递归删除.
*/
        public void deleteNode(int no){
            if(this.getLeft() != null && this.getLeft().id == no){
                this.setLeft(null);
                return;
            }

            if(this.getRight() != null && this.getRight().id == no){
                this.setRight(null);
                return;
            }
            if(this.getLeft() != null){
                this.getLeft().deleteNode(no);
            }

            if(this.getRight() != null){
                this.getRight().deleteNode(no);
            }



        }
    }


}
