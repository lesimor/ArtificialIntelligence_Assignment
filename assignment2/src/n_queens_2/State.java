package n_queens_2;

import java.util.ArrayList;
import java.util.Collections;
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
	private State(){}
	
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
	
	// 현재 보드의 상태를 출력.
	// 디버깅용으로 사용.
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
	
	// 각 열의 퀸 위치에 따라 해당 퀸의 휴리스틱 인덱스를 계산.
	// 휴리스틱 인덱스 -> 충돌하는 다른 열의 퀸 갯수.
	public int getHeuristicIndexPerColumn(int index_x, int index_y){
		// 휴리스틱 값 0으로 초기화.
		heuristic_index = 0;
		
		// 첫번째 col부터 차례로 검사
		for(int i = 0 ; i < this.board.size() ; i++){
			// 검사하고자하는 열이 매개변수로 들어온 검사 대상 퀸의 열과 같으면 넘긴다.
			if(i == index_x) continue;
			
			// 검사할 위치 -> 인덱스 차이의 절대값
			int offset = Math.abs(index_x - i);
			
			// 검사하고자 하는 상위 위치
			int check_upper_index_y = index_y - offset;
			
			// 범위를 넘어가는지 검사 & 해당 위치에 퀸이 있는지 검사.
			if((check_upper_index_y >= 0 && check_upper_index_y < board.get(i).size()) && board.get(i).get(check_upper_index_y) == true){
				// 더이상 볼 필요 없으므로 다음 열로 넘어간다.
				// 한 열당 퀸은 하나이기 때문.
				this.heuristic_index += 1;
				continue;
			}
			// 검사하고자 하는 중위 위치
			int check_middle_index_y = index_y;
			if(board.get(i).get(check_middle_index_y) == true){
				heuristic_index += 1;
				continue;
			}
			
			// 검사하고자 하는 하위 위치
			int check_below_index_y = index_y + offset;
			if((check_below_index_y >= 0 && check_below_index_y < board.get(i).size()) && board.get(i).get(check_below_index_y) == true){
				heuristic_index += 1;
				continue;
			}
		}
		
		// 충돌하는 퀸의 총 갯수를 반환.
		return heuristic_index;
	}
	
	// 인자로 들어온 행값과 열값으로 퀸을 이동시킨 후 보드 위에 잇는 모든 퀸의 휴리스틱 인덱스를 다 합친 총합. 
	// 이게 0이면 gaol에 도달한 것.
	public int getHeuristicIndexSum(){
		// 탐색하고자 하는 위치의 행과 열값.
		int queen_col, queen_row;
		// 전체 휴리스틱 값을 담을 변
		int heuristic_sum = 0;
		for(queen_col = 0; queen_col < board.size(); queen_col++){
			queen_row = board.get(queen_col).indexOf(true);
			// 해당 위치의 퀸의 휴리스틱 값을 더한다.
			heuristic_sum += getHeuristicIndexPerColumn(queen_col, queen_row);
		}
		return heuristic_sum;
	}
	
	public void moveQueen(int col, int row){
		// 해당 열의 현재 퀸 위치.
		int current_queen_row = board.get(col).indexOf(true);
		
		// 현재 열
		ArrayList <Boolean> current_col = board.get(col);
		
		// 현재 퀸 위치를 false로 공백처리하고.
		current_col.set(current_queen_row, false);
		
		// 인자로 들어온 row 위치에 퀸을 이동
		current_col.set(row, true);
	}
	
	// 매개변수로 들어온 열(col)의 퀸을 매개변수로 들어온 행(row)의 위치로 이동.
	// 휴리스틱 총합이 가장 낮아지는 row로 이동.
	public int moveQueenByLocalSearch(int col){
		// 각 row별 휴리스틱 총합값.
		ArrayList <Integer> heuristic_per_row = new ArrayList<Integer>();
		
		for(int i = 0 ; i < board.size(); i++){
			moveQueen(col, i);
			heuristic_per_row.add(getHeuristicIndexSum());
		}
		
		// 휴리스틱 값이 가장 최소가 되는 인덱스 탐색.
		int minHeuristicRow = heuristic_per_row.indexOf(Collections.min(heuristic_per_row));
		
		// 해당 위치로 퀸을 이동.
		moveQueen(col, minHeuristicRow);
		
		// 이동한 row를 리턴
		// local optimal에 빠졌는지 체크하기 위한 값으로 사용.
		return minHeuristicRow;
	}
	
	// 인자로 들어온 열값에 위치한 퀸의 row값을 리턴
	public int getQueenRow(int col){
		return board.get(col).indexOf(true);
	}
	
	// 특정 열에서 최선의 값이 아닌 차선의 값으로 퀸을 이동
	public void moveQueenToSecondOptimal(int col){
		int minHeuristicRow = 0;
		
		// 각 row별 휴리스틱 총합값.
		ArrayList <Integer> heuristic_per_row = new ArrayList<Integer>();
		
		for(int i = 0 ; i < board.size(); i++){
			// 해당 위치로 퀸을 이동시켜보고.
			moveQueen(col, i);
			
			// 각 위치에 이동시킬때마다의 휴리스틱 총합을 리스트에 추가.
			heuristic_per_row.add(getHeuristicIndexSum());
		}
		
		// 휴리스틱 값이 가장 최소가 되는 인덱스 탐색.
		minHeuristicRow = heuristic_per_row.indexOf(Collections.min(heuristic_per_row));
		
		// 최소값을 무한대로 
		heuristic_per_row.set(minHeuristicRow, Integer.MAX_VALUE);
		
		// 다시 최소 인덱스 탐색
		// 차선이 선택된다.
		minHeuristicRow = heuristic_per_row.indexOf(Collections.min(heuristic_per_row));
		
		// 해당 위치로 퀸을 이동.
		moveQueen(col, minHeuristicRow);
	}
	
	// 각 열의 퀸 위치 인덱스를 출력하는 함수.
	public void printQueenPosition(){
		int col_size = board.size();
		for(int i = 0 ; i < col_size ; i++){
			System.out.print(board.get(i).indexOf(true) + " ");
		}
		// 개행.
		System.out.println("");
	}
}
