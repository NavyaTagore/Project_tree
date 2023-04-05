package merkletree;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

public class MerkleTree {
	
	    public static String sha1HashF(String filePath) {
			String hash = null;
			try{
				FileInputStream is = new FileInputStream(filePath);
				hash = DigestUtils.sha1Hex(is);
				is.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return hash;
		}
		
		public static String sha1Hash(String text) {
			String hash = null;
			hash = DigestUtils.sha1Hex(text);
			return hash;
		}
	
	 	public static TreeNode constructTree(List<String> elements) {
	        List<TreeNode> childTreeNodes = new ArrayList<>();
	        
	        elements.forEach(filename -> {
	            childTreeNodes.add(new TreeNode(null, null, MerkleTree.sha1HashF(filename)));
	        });
	       
	        return buildMarkleTree(childTreeNodes);
	    }

	    private static TreeNode buildMarkleTree(List<TreeNode> treeNodes) {
	        ArrayList<TreeNode> nodesList = new ArrayList<>();
	        while (treeNodes.size() != 1) {
	            for(int i = 0;i < treeNodes.size();i+=2) {
	            	   TreeNode leftNode = treeNodes.get(i);
		                TreeNode rightNode = null;
		                if ((i + 1) < treeNodes.size()) {
		                    rightNode = treeNodes.get(i + 1);
		                } else {
		                    rightNode = new TreeNode(null, null, leftNode.getHash());
		                }

		                String parentHash = sha1Hash(leftNode.getHash() + rightNode.getHash());
		                nodesList.add(new TreeNode(leftNode, rightNode, parentHash));
	            }
	            treeNodes = nodesList;
	            nodesList = new ArrayList<>();
	        }
	        return treeNodes.get(0);
	    }

	    private static void printMarkleTree(TreeNode root) {
	        if (root == null) return;
	        if ((root.getLeftNode() == null && root.getRightNode() == null)) {
	            System.out.println(root.getHash());
	        }
	        LinkedList<TreeNode> tree = new LinkedList<>();
	        tree.add(root);
	        tree.add(null);

	        while (!tree.isEmpty()) {
	            TreeNode TreeNode = tree.poll();
	            if (TreeNode != null) {
	                System.out.println(TreeNode.getHash());
	            } else {
	                System.out.println();
	                if (!tree.isEmpty()) {
	                	tree.add(null);
	                }
	            }

	            if (TreeNode != null && TreeNode.getLeftNode() != null) {
	            	tree.add(TreeNode.getLeftNode());
	            }

	            if (TreeNode != null && TreeNode.getRightNode() != null) {
	            	tree.add(TreeNode.getRightNode());
	            }

	        }

    }
	    
	public static void main(String[] args) {

		
			List<String> filesList = List.of("sample1.txt","sample2.txt", "sample3.txt", "sample4.txt");
		
	        TreeNode root = constructTree(filesList);
	        printMarkleTree(root);

	}

}
