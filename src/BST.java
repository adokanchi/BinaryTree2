import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Tony Dokanchi
 * @version: 4/2/24
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        return isInTree(val, root);
    }

    // Returns true if value is in subtree defined with node as root
    public boolean isInTree(int val, BSTNode node) {
        if (node.getVal() == val) {return true;}

        // If val is less than node val, look to left of node
        if (val < node.getVal()) {
            if (node.getLeft() == null) {return false;}
            return isInTree(val, node.getLeft());
        }
        // Otherwise, look to right of node
        if (node.getRight() == null) {return false;}
        return isInTree(val, node.getRight());
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        ArrayList<BSTNode> list = new ArrayList<BSTNode>();
        return addInOrder(root, list);
    }

    public ArrayList<BSTNode> addInOrder(BSTNode node, ArrayList<BSTNode> list) {
        // Left
        if (node.getLeft() != null) {
            addInOrder(node.getLeft(), list);
        }

        // Root
        list.add(node);

        // Right
        if (node.getRight() != null) {
            addInOrder(node.getRight(), list);
        }
        return list;
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder() {
        ArrayList<BSTNode> list = new ArrayList<BSTNode>();
        return addPreOrder(root, list);
    }

    public ArrayList<BSTNode> addPreOrder(BSTNode node, ArrayList<BSTNode> list) {
        // Root
        list.add(node);

        // Left
        if (node.getLeft() != null) {
            addPreOrder(node.getLeft(), list);
        }

        // Right
        if (node.getRight() != null) {
            addPreOrder(node.getRight(), list);
        }
        return list;
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {
        ArrayList<BSTNode> list = new ArrayList<BSTNode>();
        return addPostOrder(root, list);
    }

    public ArrayList<BSTNode> addPostOrder(BSTNode node, ArrayList<BSTNode> list) {
        // Left
        if (node.getLeft() != null) {
            addPostOrder(node.getLeft(), list);
        }

        // Right
        if (node.getRight() != null) {
            addPostOrder(node.getRight(), list);
        }

        // Root
        list.add(node);
        return list;
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        // If this is the first node of the tree, set as root
        if (root == null) {
            root = new BSTNode(val);
            return;
        }
        // If already in tree, do not add
        if (search(val)) {
            return;
        }
        // Add to existing tree
        addToTree(val, root);
    }

    public void addToTree(int val, BSTNode node) {
        // If val < nodeval go to left
        if (val < node.getVal()) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode(val));
                return;
            }
            addToTree(val, node.getLeft());
            return;
        }
        // Otherwise go to right
        if (node.getRight() == null) {
            node.setRight(new BSTNode(val));
            return;
        }
        addToTree(val, node.getRight());
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        // Checks if the inorder is increasing (which it only is for valid BSTs)
        return isIncreasing(getInorder(), 0);
    }

    // Recursively check list to see if values are increasing
    public boolean isIncreasing(ArrayList<BSTNode> list, int index) {
        // Base case
        if (index >= list.size() - 1) {return true;}
        // Recursion
        if (list.get(index).getVal() > list.get(++index).getVal()) {return false;}
        return isIncreasing(list, index);
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println();
        System.out.println("Checking if tree is proper binary tree:");
        System.out.println(tree.isValidBST());
    }
}