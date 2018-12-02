package DataStructures.Tree;

/**
 * This program is an example of binary tree implementation
 */
public class BinaryTree {


    TreeNode root;


    public void insert(int data)
    {
        if(root==null)
            root=new TreeNode(data);
        else
        {
            TreeNode temp=root;
            while(temp.left!=null || temp.right!=null)
            {
                if(temp.data<data) temp=temp.right;

                else if (temp.data>data) temp=temp.left;
                else if (temp.data==data)
                {
                    temp.count++;
                    return;
                }
            }
            if (data>temp.data){ temp.right=new TreeNode(data); return;}
            if (data<temp.data){ temp.left=new TreeNode(data); return;}
        }
    }



    public void inorderTraversal(TreeNode temp)
    {
        if(temp!=null)
        {
            inorderTraversal(temp.left);
            System.out.println(temp.data);
            inorderTraversal(temp.right);
        }
    }



    public void preOrderTraversal(TreeNode temp)
    {
        if(temp!=null)
        {
            preOrderTraversal(temp.left);
            preOrderTraversal(temp.right);
            System.out.println(temp.data);
        }
    }



    public void postOrderTraversal(TreeNode temp)
    {
        if(temp!=null)
        {
            System.out.println(temp.data);
            postOrderTraversal(temp.left);
            postOrderTraversal(temp.right);
        }
    }



    public static void main(String args[])
    {
        BinaryTree ob=new BinaryTree();
        ob.insert(10);
        ob.insert(11);
        ob.insert(12);
        ob.insert(12);
        ob.insert(13);
        ob.inorderTraversal(ob.root);
        System.out.println();
        ob.preOrderTraversal(ob.root);
        System.out.println();
        ob.postOrderTraversal(ob.root);

    }
}
