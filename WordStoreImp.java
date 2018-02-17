class WordStoreImp implements WordStore
{
    Node rootNode;
    public WordStoreImp(int n)
    {
        rootNode = null;
    }

    public void add(String word)
    {
        rootNode = addNode(word, rootNode);
    }

    public int count(String word)
    {
        return countNode(word, rootNode);
    }

    public void remove(String word)
    {
        removeNode(word, rootNode);
    }

    private Node addNode(String word, Node rootNode)
    {
        if(rootNode == null)
        {
            rootNode = new Node(word);
        }
        else if(word.equals(rootNode.data))
        {
            rootNode.countWord++;
        }
        else if(word.compareTo(rootNode.data) < 0)
        {
            rootNode.left = addNode(word, rootNode.left);
        }
        else
        {
            rootNode.right = addNode(word, rootNode.right);
        }
        return rootNode;
    }

    private int countNode(String word, Node rootNode)
    {
        if(rootNode == null)
        {
            return 0;
        }
        else if(word.equals(rootNode.data))
        {
            return rootNode.countWord;
        }
        else if(word.compareTo(rootNode.data) <= 0)
        {
            return countNode(word, rootNode.left);
        }
        else
        {
            return countNode(word, rootNode.right);
        }
    }

    private Node removeNode(String word, Node rootNode)
    {
        if(rootNode == null)
        {
            return null;
        }
        if(word.equals(rootNode.data) && rootNode.countWord > 1)
        {
            rootNode.countWord--;
        }
        else if(word.equals(rootNode.data) && rootNode.countWord == 1)
        {
            if(rootNode.left != null && rootNode.right != null)//Node has 2 children
            {
                rootNode.data = min(rootNode.right).data;//minimum element used to replace the root node
                removeNode(rootNode.data, rootNode.right);//minimum node removed
            }
            else if(rootNode.left != null && rootNode.right == null)//Node has 1 child on the left
            {
                rootNode.data = rootNode.left.data;
                rootNode.countWord = rootNode.left.countWord;
                rootNode.left = null;
            }
            else if(rootNode.right != null && rootNode.left == null)//Node has one child on the right
            {
                rootNode.data = rootNode.right.data;
                rootNode.countWord = rootNode.right.countWord;
                rootNode.right = null;
            }
            else//Node has 0 children
            {
                rootNode = null;
            }
        }
        else if(word.compareTo(rootNode.data) < 0)
        {
            rootNode.left = removeNode(word, rootNode.left);
        }
        else
        {
            rootNode.right = removeNode(word, rootNode.right);
        }
        return rootNode;
    }

    private Node min(Node node)//finds the smallest elements node in a part of the tree and returns it. Only needs to search the left side of the given node as smallest elements are on the left.
    {
        if(node.left == null)
        {
            return node;
        }
        else
        {
            return min(node.left);
        }
    }

    private Node getNode()
    {
        return rootNode;
    }

    public static void print(Node node)
    {
        if(node != null)
        {
            print(node.left);
            System.out.print(node.data + " ");
            print(node.right);
        }
    }

    private class Node
    {
        public String data;
        public Node left;
        public Node right;
        public int countWord;
        public Node(String word)
        {
            this.data = word;
            left = null;
            right = null;
            countWord = 1;
        }
    }
}
