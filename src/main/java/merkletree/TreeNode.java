package merkletree;

public class TreeNode {
	private TreeNode leftNode;
    private TreeNode rightNode;
    private String hash;

    public TreeNode() {
		// TODO Auto-generated constructor stub
	}

	public TreeNode(TreeNode leftNode, TreeNode rightNode, String hash) {
		super();
		this.leftNode = leftNode;
		this.rightNode = rightNode;
		this.hash = hash;
	}

	public TreeNode getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(TreeNode leftNode) {
		this.leftNode = leftNode;
	}

	public TreeNode getRightNode() {
		return rightNode;
	}

	public void setRightNode(TreeNode rightNode) {
		this.rightNode = rightNode;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	
	

}
