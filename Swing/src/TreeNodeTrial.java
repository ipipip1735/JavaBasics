import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2018/10/21.
 */
public class TreeNodeTrial {
    public static void main(String[] args) {
        TreeNodeTrial treePathTrial = new TreeNodeTrial();
//        treePathTrial.base();
//        treePathTrial.add();
//        treePathTrial.romove();
//        treePathTrial.enumeration();
//        treePathTrial.status();


        //创建数
        DefaultMutableTreeNode root = treePathTrial.create();
//        treePathTrial.depth(root, System.out::println); //打印

        //复制
//        DefaultMutableTreeNode root = treePathTrial.create(); //打印
//        DefaultMutableTreeNode rootNew = treePathTrial.copy(root); //打印
//        treePathTrial.depth(rootNew, System.out::println); //打印

        //获取叶子节点
//        DefaultMutableTreeNode root = treePathTrial.create();
//        for (DefaultMutableTreeNode node : treePathTrial.getLeaves(root)) {
//            System.out.println("node = " + node);
//        }
    }


    private DefaultMutableTreeNode create() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        DefaultMutableTreeNode one = new DefaultMutableTreeNode("one");
        DefaultMutableTreeNode two = new DefaultMutableTreeNode("two");
        DefaultMutableTreeNode three = new DefaultMutableTreeNode("three");
        DefaultMutableTreeNode four = new DefaultMutableTreeNode("four");
        DefaultMutableTreeNode five = new DefaultMutableTreeNode("five");
        DefaultMutableTreeNode six = new DefaultMutableTreeNode("six");
        DefaultMutableTreeNode seven = new DefaultMutableTreeNode("seven");
        DefaultMutableTreeNode eight = new DefaultMutableTreeNode("eight");
        DefaultMutableTreeNode nine = new DefaultMutableTreeNode("nine");
        DefaultMutableTreeNode ten = new DefaultMutableTreeNode("ten");

        /* 拓扑图如下所示：
         * root
         *  |-one
         *      |-four
         *      |-five
         *          |-seven
         *              |-eight
         *                  |-nine
         *                      |-ten
         *  |-two
         *  |-three
         *      |-six
         */

        //L1
        root.add(one);
        root.add(two);
        root.add(three);

        //L2
        one.add(four);
        one.add(five);
        three.add(six);

        //L3
        five.add(seven);

        //L4
        seven.add(eight);

        //L5
        eight.add(nine);

        //L6
        nine.add(ten);


        //遍历树
//        root.preorderEnumeration().asIterator().forEachRemaining(System.out::println); //先根遍历
//        root.postorderEnumeration().asIterator().forEachRemaining(System.out::println); //后根遍历
//        root.breadthFirstEnumeration().asIterator().forEachRemaining(System.out::println); //广度遍历 或 层级优先遍历
//        root.depthFirstEnumeration().asIterator().forEachRemaining(System.out::println); //深度优先遍历

        //获取路径
//        root.getFirstLeaf()
//                .getNextLeaf()
//                .pathFromAncestorEnumeration(root).asIterator()
//                .forEachRemaining(System.out::println);

        //获取路径数组
        TreeNode[] nodes = root.getFirstLeaf().getNextLeaf().getPath();
        Arrays.stream(nodes).forEach(System.out::println);


        return root;
    }

    public DefaultMutableTreeNode copy(DefaultMutableTreeNode src) {

        DefaultMutableTreeNode des = new DefaultMutableTreeNode("new - " + src.getUserObject());

        src.children().asIterator().forEachRemaining(treeNode -> {
            des.add(copy((DefaultMutableTreeNode) treeNode));
        });

        return des;
    }

    /**
     * 深度优先遍历
     *
     * @param root
     * @param consumer
     */
    private void depth(DefaultMutableTreeNode root, Consumer<DefaultMutableTreeNode> consumer) {
        consumer.accept(root);
        root.children().asIterator().forEachRemaining(treeNode ->
                depth((DefaultMutableTreeNode) treeNode, consumer)
        );
    }

    public List<DefaultMutableTreeNode> getLeaves(DefaultMutableTreeNode root) {

        List<DefaultMutableTreeNode> list = new ArrayList<>();

        for (DefaultMutableTreeNode node = root.getFirstLeaf(); node != null; node = node.getNextLeaf()) {
            list.add(node);
        }

        return list;
    }

    private void enumeration() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode oneANode = new DefaultMutableTreeNode("oneANode");
        DefaultMutableTreeNode twoANode = new DefaultMutableTreeNode("twoANode");
        DefaultMutableTreeNode oneBNode = new DefaultMutableTreeNode("oneBNode");
        DefaultMutableTreeNode twoBNode = new DefaultMutableTreeNode("twoBNode");
        DefaultMutableTreeNode oneCNode = new DefaultMutableTreeNode("oneCNode");

        //增加节点
        rootNode.add(oneANode);
        rootNode.add(twoANode);
        oneANode.add(oneBNode);
        twoANode.add(twoBNode);
        twoBNode.add(oneCNode);

        /*拓扑图如下所示：
         *         Root
         *    oneA    twoA
         * oneB         twoB
         *                oneC
         */

        Enumeration e;
        StringBuilder stringBuilder = new StringBuilder(128);

        //先根遍历
        e = rootNode.preorderEnumeration();
        while (e.hasMoreElements()) {
            stringBuilder.append(e.nextElement() + ", ");
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 2));
        stringBuilder.delete(0, stringBuilder.length());


        //后根遍历
        e = rootNode.postorderEnumeration();
        while (e.hasMoreElements()) {
            stringBuilder.append(e.nextElement() + ", ");
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 2));
        stringBuilder.delete(0, stringBuilder.length());


        //宽度优先遍历 或 层级遍历
        e = rootNode.breadthFirstEnumeration();
        while (e.hasMoreElements()) {
            stringBuilder.append(e.nextElement() + ", ");
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 2));
        stringBuilder.delete(0, stringBuilder.length());


        //深度优先遍历
        e = rootNode.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            stringBuilder.append(e.nextElement() + ", ");
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 2));
        stringBuilder.delete(0, stringBuilder.length());


        //返回所给节点到当前节点路径上的所有节点
        e = oneCNode.pathFromAncestorEnumeration(rootNode);
        while (e.hasMoreElements()) {
            stringBuilder.append(e.nextElement() + ", ");
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 2));
        stringBuilder.delete(0, stringBuilder.length());


        //返回所有子节点（不包括后代）
        e = rootNode.children();
        while (e.hasMoreElements()) {
            stringBuilder.append(e.nextElement() + ", ");
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 2));
        stringBuilder.delete(0, stringBuilder.length());


    }

    private void add() {

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode oneANode = new DefaultMutableTreeNode("oneANode");
        DefaultMutableTreeNode twoANode = new DefaultMutableTreeNode("twoANode");
        DefaultMutableTreeNode threeANode = new DefaultMutableTreeNode("ThreeANode");
        DefaultMutableTreeNode oneBNode = new DefaultMutableTreeNode("oneBNode");
        DefaultMutableTreeNode twoBNode = new DefaultMutableTreeNode("twoBNode");
        DefaultMutableTreeNode oneCNode = new DefaultMutableTreeNode("oneCNode");

        //增加节点
        rootNode.add(oneANode);
        rootNode.add(twoANode);
        oneANode.add(oneBNode);
        twoANode.add(twoBNode);
        twoBNode.add(oneCNode);

        /* 拓扑图如下所示：
         *         Root
         *    oneA    twoA
         * oneB         twoB
         *                oneC
         */

        rootNode.insert(threeANode, 1);
        /* 插入后的拓扑图如下所示：
         *            Root
         *          /  |  \
         *     oneA threeA twoA
         *  oneB              twoB
         *                        oneC
         */

        //遍历打印
        StringBuilder stringBuilder = new StringBuilder(128);
        Enumeration e = rootNode.preorderEnumeration();
        while (e.hasMoreElements()) {
            stringBuilder.append(e.nextElement() + ", ");
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 2));
        stringBuilder.delete(0, stringBuilder.length());


    }

    private void romove() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode oneANode = new DefaultMutableTreeNode("oneANode");
        DefaultMutableTreeNode twoANode = new DefaultMutableTreeNode("twoANode");
        DefaultMutableTreeNode oneBNode = new DefaultMutableTreeNode("oneBNode");
        DefaultMutableTreeNode twoBNode = new DefaultMutableTreeNode("twoBNode");
        DefaultMutableTreeNode oneCNode = new DefaultMutableTreeNode("oneCNode");

        //增加节点
        rootNode.add(oneANode);
        rootNode.add(twoANode);
        oneANode.add(oneBNode);
        twoANode.add(twoBNode);
        twoBNode.add(oneCNode);

        /*拓扑图如下所示：
         *         Root
         *    oneA    twoA
         * oneB         twoB
         *                oneC
         */

//        rootNode.remove(twoANode);
        twoBNode.removeFromParent();


        //遍历打印
        StringBuilder stringBuilder = new StringBuilder(128);
        Enumeration e = rootNode.preorderEnumeration();
        while (e.hasMoreElements()) {
            stringBuilder.append(e.nextElement() + ", ");
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 2));
        stringBuilder.delete(0, stringBuilder.length());


    }

    private void status() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode oneANode = new DefaultMutableTreeNode("oneANode");
        DefaultMutableTreeNode twoANode = new DefaultMutableTreeNode("twoANode");
        DefaultMutableTreeNode oneBNode = new DefaultMutableTreeNode("oneBNode");
        DefaultMutableTreeNode oneCNode = new DefaultMutableTreeNode("oneCNode");

        //增加节点
        rootNode.add(oneANode);
        rootNode.add(twoANode);
        twoANode.add(oneBNode);
        oneBNode.add(oneCNode);

        /* 拓扑图如下所示：
         *     Root
         * oneA   twoA
         *          oneB
         *            oneC
         */

        System.out.println("isLeaf is " + oneBNode.isLeaf());
        System.out.println("isNodeAncestor​ is " + oneBNode.isNodeAncestor(rootNode));
        System.out.println("isNodeChild​ is " + oneBNode.isNodeChild(rootNode));
        System.out.println("isNodeDescendant​ is " + oneBNode.isNodeDescendant(rootNode));
        System.out.println("isNodeRelated​ is " + oneBNode.isNodeRelated(rootNode));
        System.out.println("isNodeSibling​ is " + oneBNode.isNodeSibling(rootNode));
        System.out.println("isRoot is " + oneBNode.isRoot());

    }

    private void base() {

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode oneANode = new DefaultMutableTreeNode("oneANode");
        DefaultMutableTreeNode twoANode = new DefaultMutableTreeNode("twoANode");
        DefaultMutableTreeNode oneBNode = new DefaultMutableTreeNode("oneBNode");
        DefaultMutableTreeNode oneCNode = new DefaultMutableTreeNode("oneCNode");

        //增加节点
        rootNode.add(oneANode);
        rootNode.add(twoANode);
        twoANode.add(oneBNode);
        oneBNode.add(oneCNode);

        /* 拓扑图如下所示：
         *     Root
         * oneA   twoA
         *          oneB
         *            oneC
         */


//        gets(rootNode);
//        gets(twoANode);
        gets(oneCNode);


    }

    private void gets(DefaultMutableTreeNode node) {

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode oneANode = new DefaultMutableTreeNode("oneANode");
        DefaultMutableTreeNode twoANode = new DefaultMutableTreeNode("twoANode");
        DefaultMutableTreeNode oneBNode = new DefaultMutableTreeNode("oneBNode");
        DefaultMutableTreeNode oneCNode = new DefaultMutableTreeNode("oneCNode");
        rootNode.add(oneANode);
        rootNode.add(twoANode);
        twoANode.add(oneBNode);
        oneBNode.add(oneCNode);


//        System.out.println("getPathToRoot​ is " + node.getPathToRoot(TreeNode aNode, int depth));
//        System.out.println("getSharedAncestor​ is " + node.getSharedAncestor(DefaultMutableTreeNode aNode));


        System.out.println("getDepth is " + node.getDepth());
        System.out.println("getLevel is " + node.getLevel());
        System.out.println("getAllowsChildren is " + node.getAllowsChildren());
        System.out.println("getRoot is " + node.getRoot());
        System.out.println("getParent is " + node.getParent());


        TreeNode[] nodes = node.getPath();
        StringBuilder stringBuilder = new StringBuilder(128);
        for (TreeNode n : nodes) {
            stringBuilder.append(n.toString() + ", ");
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 2));


        System.out.println("getChildCount is " + node.getChildCount());
//        System.out.println("getChildAt​ is " + node.getChildAt(0));
//        System.out.println("getFirstChild is " + node.getFirstChild());
//        System.out.println("getLastChild is " + node.getLastChild());
//        System.out.println("getIndex​ is " + node.getIndex(TreeNode aChild));
//        System.out.println("getChildAfter​ is " + node.getChildAfter(TreeNode child));
//        System.out.println("getChildBefore​ is " + node.getChildBefore(TreeNode aChild));


        System.out.println("getLeafCount is " + node.getLeafCount());
        System.out.println("getFirstLeaf is " + node.getFirstLeaf());
        System.out.println("getLastLeaf is " + node.getLastLeaf());
        System.out.println("getNextLeaf is " + node.getNextLeaf());
        System.out.println("getPreviousLeaf is " + node.getPreviousLeaf());

        System.out.println("getNextNode is " + node.getNextNode());
        System.out.println("getPreviousNode is " + node.getPreviousNode());

        System.out.println("getSiblingCount is " + node.getSiblingCount());
        System.out.println("getNextSibling is " + node.getNextSibling());
        System.out.println("getPreviousSibling is " + node.getPreviousSibling());

        System.out.println("getUserObject is " + node.getUserObject());
        System.out.println("getUserObjectPath is " + node.getUserObjectPath());
    }

}
