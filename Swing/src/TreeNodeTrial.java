import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by Administrator on 2018/10/21.
 */
public class TreeNodeTrial {
    public static void main(String[] args) {
        TreeNodeTrial treePathTrial = new TreeNodeTrial();
        treePathTrial.base();
    }

    private void base() {

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode oneANode = new DefaultMutableTreeNode("oneANode");
        DefaultMutableTreeNode twoANode = new DefaultMutableTreeNode("twoANode");
        rootNode.add(oneANode);
        rootNode.add(twoANode);
        gets(rootNode);



    }

    private void gets(DefaultMutableTreeNode node){
//        System.out.println("getChildAfter​ is " + node.getChildAfter​(TreeNode aChild));
//        System.out.println("getChildAt​ is " + node.getChildAt​(int index));
//        System.out.println("getChildBefore​ is " + node.getChildBefore​(TreeNode aChild));
//        System.out.println("getIndex​ is " + node.getIndex​(TreeNode aChild));
//        System.out.println("getPathToRoot​ is " + node.getPathToRoot​(TreeNode aNode, int depth));
//        System.out.println("getSharedAncestor​ is " + node.getSharedAncestor​(DefaultMutableTreeNode aNode));
        System.out.println("getAllowsChildren is " + node.getAllowsChildren());
        System.out.println("getChildCount is " + node.getChildCount());
        System.out.println("getDepth is " + node.getDepth());
        System.out.println("getFirstChild is " + node.getFirstChild());
        System.out.println("getFirstLeaf is " + node.getFirstLeaf());
        System.out.println("getLastChild is " + node.getLastChild());
        System.out.println("getLastLeaf is " + node.getLastLeaf());
        System.out.println("getLeafCount is " + node.getLeafCount());
        System.out.println("getLevel is " + node.getLevel());
        System.out.println("getNextLeaf is " + node.getNextLeaf());
        System.out.println("getNextNode is " + node.getNextNode());
        System.out.println("getNextSibling is " + node.getNextSibling());
        System.out.println("getParent is " + node.getParent());
        System.out.println("getPath is " + node.getPath());
        System.out.println("getPreviousLeaf is " + node.getPreviousLeaf());
        System.out.println("getPreviousNode is " + node.getPreviousNode());
        System.out.println("getPreviousSibling is " + node.getPreviousSibling());
        System.out.println("getRoot is " + node.getRoot());
        System.out.println("getSiblingCount is " + node.getSiblingCount());
        System.out.println("getUserObject is " + node.getUserObject());
        System.out.println("getUserObjectPath is " + node.getUserObjectPath());
    }
//    private void gets(DefaultMutableTreeNode node){
//        System.out.println("getAllowsChildren is " + node.getAllowsChildren());
//        System.out.println("getChildAfter​ is " + node.getChildAfter​(TreeNode aChild));
//        System.out.println("getChildAt​ is " + node.getChildAt​(int index));
//        System.out.println("getChildBefore​ is " + node.getChildBefore​(TreeNode aChild));
//        System.out.println("getChildCount is " + node.getChildCount());
//        System.out.println("getDepth is " + node.getDepth());
//        System.out.println("getFirstChild is " + node.getFirstChild());
//        System.out.println("getFirstLeaf is " + node.getFirstLeaf());
//        System.out.println("getIndex​ is " + node.getIndex​(TreeNode aChild));
//        System.out.println("getLastChild is " + node.getLastChild());
//        System.out.println("getLastLeaf is " + node.getLastLeaf());
//        System.out.println("getLeafCount is " + node.getLeafCount());
//        System.out.println("getLevel is " + node.getLevel());
//        System.out.println("getNextLeaf is " + node.getNextLeaf());
//        System.out.println("getNextNode is " + node.getNextNode());
//        System.out.println("getNextSibling is " + node.getNextSibling());
//        System.out.println("getParent is " + node.getParent());
//        System.out.println("getPath is " + node.getPath());
//        System.out.println("getPathToRoot​ is " + node.getPathToRoot​(TreeNode aNode, int depth));
//        System.out.println("getPreviousLeaf is " + node.getPreviousLeaf());
//        System.out.println("getPreviousNode is " + node.getPreviousNode());
//        System.out.println("getPreviousSibling is " + node.getPreviousSibling());
//        System.out.println("getRoot is " + node.getRoot());
//        System.out.println("getSharedAncestor​ is " + node.getSharedAncestor​(DefaultMutableTreeNode aNode));
//        System.out.println("getSiblingCount is " + node.getSiblingCount());
//        System.out.println("getUserObject is " + node.getUserObject());
//        System.out.println("getUserObjectPath is " + node.getUserObjectPath());
//    }
}
