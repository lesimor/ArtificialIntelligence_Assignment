package n_queens_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {
		// 인자로 받을 숫자.
		int board_size;
		
		// 인자로 받을 출력파일 경로.
		String resultFilePath;
		
		// 예외처리 
		try{
			board_size = Integer.parseInt(args[0]);
			resultFilePath = args[1] + "/result"+board_size+".txt";
	    }catch(ArrayIndexOutOfBoundsException e){
	    	board_size = 30;
	    	resultFilePath = "./result" + board_size + ".txt";
	    }
		
		
		// 파일 설정.
		File file = new File(resultFilePath);
		PrintStream printStream = new PrintStream(new FileOutputStream(file));
		
		// standard out과 err을 file로 변경
		System.setOut(printStream);
		System.setErr(printStream);

		// 싱글턴 패턴으로 설계한 State 클래스 인스턴스 생
		State singletonState = State.getInstance();
		
		
		singletonState.setSize(board_size);
		
		int target_col = 0;
		int current_queen_row, optimal_row;
		
		// 보드의 스냅샷 비교를 위한 변수.
		ArrayList<Integer> old_snapshot = new ArrayList<Integer>();
		ArrayList<Integer> new_snapshot = new ArrayList<Integer>();
//		System.out.println(new_snapshot.size());
		
		
		System.out.println(">Hill climbing");
		
		// 시간측정 시작.
		long startTime = System.currentTimeMillis();
		while(true) {
			current_queen_row = singletonState.getQueenRow(target_col);
			optimal_row = singletonState.moveQueenByLocalSearch(target_col);
			
			// 만약 각 열마다 퀸을 이동하는데 움직일 곳이 없으면 local_optimal
			
			// 휴리스틱 총합이 0이면 빠져나온다.
			if(singletonState.getHeuristicIndexSum() == 0) break;
			
			new_snapshot.add(optimal_row);
			
			target_col += 1;
			// 탐색하고자 하는 열이 보드사이즈를 넘어가면 다시 0으로 만들어줌.
			// 스냅샷을 비교.
			if(target_col == board_size){
				// 스냅샷을 비교해서 같으면 local_optimal이므로 다시 랜덤 배치한다.
				if(equalArrayLists(old_snapshot, new_snapshot)){
					// 랜덤 객체 생성 -> 난수 생성을 위함.
					Random rand = new Random();
					
					// 0 ~ size -1 까지의 난수를 생성.
					int rand_num = rand.nextInt(board_size);
					
					singletonState.moveQueenToSecondOptimal(rand_num);
					
				} 
				
				// new_snapshot -> old_snapshot으로 복사.
				copyArrayList(new_snapshot, old_snapshot);
				
				// new_snapshot 초기화.
				new_snapshot.clear();
				
				// target_col을 다시 0으로 내린다.
				target_col = 0;
			}
			
			
		}
		
		// 퀸 위치 출력
		singletonState.printQueenPosition();
		
		// 시간측정 끝.
		long stopTime = System.currentTimeMillis();
		
		// 경과시간.
		long collapsedTime = (stopTime - startTime);
				
		// 경과시간 출력.
		System.out.println("Total Elapsed Time: " + collapsedTime + "ms");
		
//		singletonState.moveQueen(2, 1);
		
//		singletonState.showBoard();
		
//		System.out.println("휴리스틱 인덱스 총합은 " + singletonState.getHeuristicIndexSum());
	}
	
	public static  boolean equalArrayLists(ArrayList<Integer> a, ArrayList<Integer> b){     
	    // Check for sizes and nulls
	    if ((a.size() != b.size()) || (a == null && b!= null) || (a != null && b== null)){
	        return false;
	    }

	    if (a == null && b == null) return true;
	    
	    for (int i = 0 ; i < a.size(); i++){
	    	if (a.get(i) != b.get(i)){
	    		return false;
	    	}
	    }
	    
	    return true;
	}
	
	public static void copyArrayList(ArrayList<Integer> source, ArrayList<Integer> dest){
		dest.clear();	    
	    for (int i = 0 ; i < source.size(); i++){
	    	dest.add(source.get(i));
	    }

	}
}
