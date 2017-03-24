package n_queens_1;

import java.util.ArrayList;

//트리의 노드를 구성하는 트리노드 객체.
public class TreeNode {
	// 루트노드를 정적변수로 선언한다.
	// generateTree를 이용하여 트리를 만들기 위함.
	static public TreeNode rootNode;
	
	// 부모노드를 가리키는 변수.
	TreeNode parent;
	
	// 자식노드를 가리키는 변수. 여러개가 달리므로 ArrayList로 선언해준다.
	ArrayList<TreeNode> children;
	
	// 상태값을 담을 변수.
	public State state;
	
	// 생성자 함수. state만 들어온 경우 parent는 null값.
	public TreeNode(State _state){
		this.state = _state;
		this.children = new ArrayList<TreeNode>();
	}
	
	// 생성자 함수. 부모 객체가 함께 들어온 경우 parent를 연결.
	public TreeNode(State _state, TreeNode _parent){
		this.state = _state;
		this.parent = _parent;	// parent와 연결.
		this.children = new ArrayList<TreeNode>();
	}
	
	//자식 노드를 expand, 매개변수로 현재 노드의 깊이를 받는다.
	//재귀적으로 호출하여 맨 아래 레벨까지 트리를 생성한다.
	public void expand(){
		// state로부터 y축 길이를 가져온다.
		int y_size = this.state.map.size();
		
		// expand하지 않는 조건 -> state의 index_x가 state의 map 사이즈 - 1인 경우
		// 즉 leaf 노드인 경우.
		if(this.state.index_x != this.state.map.size() - 1){
			// y_size만큼 자식을 expand
			for(int i = 0 ; i < y_size ; i++){
				// 현재 인스턴스의 map정보를 토대로 expanding된 map을 만들고 그것을 토대로 state생성
				ArrayList <ArrayList<Boolean>> followingMap = this.state.copyMap(this.state.map);
				
				// 다음 단계의 map정보를 생성.
				followingMap.get(this.state.index_x + 1).set(i, true);
				
				// 다음단계의 map정보를 이용해 state생성.
				State newState = new State(followingMap, this.state.index_x + 1, i);
				
				// 자식 노드를 생성
				// 부모를 함께 인자로 넣어준다.
				TreeNode child = new TreeNode(newState, this);
				
				// 재귀호출
				child.expand();
				
				// 자식 노드를 추가
				this.children.add(child);
				
			}	
		}
	}
	
	// 해당 노드가 leaf 노드인지 판단.
	// goal체크할 때 사용. goal체크는 무조건 leaf노드에서 이루어진다.(적어도 n-queens에서는)
	public boolean isLeafNode(){
		// 방금 놓인 퀸의 위치 인덱스가 사이즈-1과 같을때 leaf노드.
		if(this.state.map.size() - 1 == this.state.index_x){
			return true;
		} else {
			return false;
		}
	}
	
//	public void showStatus(){
//		System.out.println("------노드 초기화--------");
//		System.out.println("Depth(index_x):" + this.state.index_x);
//		for(int i = 0 ; i < this.state.map.get(0).size() ; i++){
//			for(int j = 0 ; j < this.state.map.size() ; j++){
//				if(this.state.map.get(j).get(i) == true){
//					System.out.print("퀸 ");
//				} else {
//					System.out.print("ㅁ ");
//				}
//			}
//			System.out.println("");
//		}
//		System.out.println("위치: " + this.state.index_x + this.state.index_y);
//	}
	
	// 해당 노드의 퀸 위치를 print로 출력하는 함수.
	// goal에 도달했을 시 호출한다.
	public void showQueenPosition(){
		int size_x = this.state.map.size();	// 해당 노드 map의 가로길이.
		int size_y = this.state.map.get(0).size(); 	// 해당 노드 map의 세로길이.
		for(int i = 0 ; i < size_x ; i++){
			for(int j = 0 ; j < size_y ; j++){
				if(this.state.map.get(i).get(j)){
					System.out.print(j+" ");	// 각 col별로 탐색하여 true 값인 것의 index를 출력.
				}
			}
		}
	}
	
	//트리를 생성하는 클래스 메소드.
	static TreeNode generateTree(int size){
		// 지도 초기화.
		ArrayList <ArrayList<Boolean>> map = new ArrayList <ArrayList<Boolean>>();
		
		// 인자로 받은 size만큼의 비어있는 맵을 하나 생성.
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
		
		// 해당 노드를 시작으로 트리 생성.
		// 재귀적으로 호출하여 expand한다.
		rootNode.expand();
		
		return rootNode;
	}
}
