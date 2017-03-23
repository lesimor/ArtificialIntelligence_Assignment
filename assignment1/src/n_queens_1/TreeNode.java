package n_queens_1;

import java.util.ArrayList;

public class TreeNode {
	TreeNode parent;
	ArrayList<TreeNode> children;
	public String name;
	public int[][] map;
	public TreeNode(String name, int size){
		this.name = name;
		this.map = new int[size][size];
		System.out.println(this.map[1][1]);
	}
}
