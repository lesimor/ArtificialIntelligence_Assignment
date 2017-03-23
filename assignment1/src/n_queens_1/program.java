package n_queens_1;

import java.util.Scanner;
import java.util.ArrayList;

import n_queens_1.*;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ArrayList <ArrayList<Boolean>> map = new ArrayList<ArrayList<Boolean>>();
//		ArrayList <Boolean> col1 = new ArrayList<Boolean>();
//		col1.add(false);
//		col1.add(true);
//		col1.add(false);
//		col1.add(false);
//		map.add(col1);
//		
//		ArrayList <Boolean> col2 = new ArrayList<Boolean>();
//		col2.add(false);
//		col2.add(false);
//		col2.add(false);
//		col2.add(true);
//		map.add(col2);
//		
//		
//		ArrayList <Boolean> col3 = new ArrayList<Boolean>();
//		col3.add(true);
//		col3.add(false);
//		col3.add(false);
//		col3.add(false);
//		map.add(col3);
//		
//		ArrayList <Boolean> col4 = new ArrayList<Boolean>();
//		col4.add(false);
//		col4.add(false);
//		col4.add(false);
//		col4.add(true);
//		map.add(col4);
//		
//		State s = new State(map, 3, 2);
//		
//		System.out.println(s.goalCheck());
		
		// 트리 expanding
		TreeNode.generateTree(4);
		
	}

}
