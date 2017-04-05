package n_queens_2;

import java.util.ArrayList;
import java.util.Random;

// State를 판단하는 건 체스판 전체를 토대로 이루어지기 때문에 인스턴스는 하나만 있어도 된다.
// 싱글턴 패턴으로 설계.
public class State {
	// State 판단을 담당할 정적 인스턴스 생성.
	private static State instance = new State();
	
	// 해당 인스턴스의 chessBoard
	private ArrayList <ArrayList<Boolean>> board = new ArrayList <ArrayList<Boolean>>();
	
	// 해당 인스턴스의 휴리스틱을 판단할 인덱스.
	// 지수가 높을 수록 안좋다.
	private int heuristic_index = 0;
	
	// State객체 생성자 함수
	// 구조상 프로그램 전체에서 한번밖에 호출 안된다.
	// 즉 인스턴스도 하나밖에 없다.
	private State(){
		System.out.println("call State constructor");
	}
	
	public static State getInstance(){
		return instance;
	}
	
	// 정해진 가로세로 크기만큼 체스판을 초기화
	// 각 열마다 퀸 랜덤배치.
	public void setSize(int size){
		// 체스판 초기화.
		this.board.clear();
		
		// 루프문에서 생성할 난수를 임시로 담아 둘 공간
		int rand_num;
		
		// 생성한 returnMap변수에 매개변수로 들어온 _map값을 복사해준다.
		for(int i = 0 ; i < size; i++){
			// 랜덤 객체 생성 -> 난수 생성을 위함.
			Random rand = new Random();
			
			// 0 ~ size -1 까지의 난수를 생성.
			rand_num = rand.nextInt(size);
			
			board.add(new ArrayList<Boolean>());
			for(int j = 0 ; j < size ; j++){
				// 퀸 랜덤 배치.
				if (j == rand_num){
					// 생성한 난수에 해당하는 위치의 열에 퀸을 배치.
					board.get(i).add(true);
				} else {
					// 이외의 경우에는 빈칸.
					board.get(i).add(false);
				}
			}
		}
		
	}
	
	public void showBoard(){
		for(int i = 0 ; i < this.board.get(0).size() ; i++){
			for(int j = 0 ; j < this.board.size() ; j++){
				if(this.board.get(j).get(i) == true){
						System.out.print("퀸 ");
				} else {
					System.out.print("ㅁ ");
				}
			}
				System.out.println("");
		}
	}

	public void print(){
		System.out.println("This is print method in State instance");
		System.out.println("instance hash code -> " + instance.hashCode());
		System.out.println("Heuristic index is " + instance.heuristic_index);
	}
}
