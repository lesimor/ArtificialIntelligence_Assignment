package n_queens_1;

import java.util.ArrayList;

public class TreeNode {
	static public TreeNode rootNode;
	TreeNode parent;
	ArrayList<TreeNode> children;
	public State state;
	public TreeNode(State _state){
		this.state = _state;
		this.children = new ArrayList<TreeNode>();
	}
	public TreeNode(State _state, TreeNode _parent){
		this.state = _state;
		this.parent = _parent;
		this.children = new ArrayList<TreeNode>();
	}
	//자식 노드를 expand, 매개변수로 현재 노드의 깊이를 받는다.
	public void expand(){
		// state로부터 y축 길이를 가져온다.
		int y_size = this.state.map.size();
		// 종결조건 -> state의 index_x가 state의 map 사이즈 - 1인 경우
		if(this.state.index_x != this.state.map.size() - 1){
			// y_size만큼 자식을 expand
			for(int i = 0 ; i < y_size ; i++){
				// 현재 인스턴스의 map정보를 토대로 expanding된 map을 만들고 state생성
				ArrayList <ArrayList<Boolean>> followingMap = this.state.copyMap(this.state.map);
				followingMap.get(this.state.index_x + 1).set(i, true);
				State newState = new State(followingMap, this.state.index_x + 1, i);
				// 자식 노드를 생성
				TreeNode child = new TreeNode(newState, this);
				// 재귀호출
				child.expand();
				// 자식 노드를 추가
				this.children.add(child);
				
			}	
		}
	}
	//트리를 initializing하는 클래스 메소드.
	static TreeNode generateTree(int size){
		// 지도 초기화.
		ArrayList <ArrayList<Boolean>> map = new ArrayList <ArrayList<Boolean>>();
		for(int i = 0 ; i < size ; i++){
			map.add(new ArrayList<Boolean>());
			for(int j = 0 ; j < size ; j++){
				map.get(i).add(false);
			}
		}
		// state 세팅
		State state = new State(map, -1, 0);
		// root노드를 하나 생성
		rootNode = new TreeNode(state);
		rootNode.expand();
		return rootNode;
	}
}
