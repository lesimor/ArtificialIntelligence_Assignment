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
			// 숫자와 경로 인자가 잘 들어온다면 해당 인자로 보드 사이즈와 출력파일 경로를 설정.
			board_size = Integer.parseInt(args[0]);
			resultFilePath = args[1] + "/result"+board_size+".txt";
	    }catch(ArrayIndexOutOfBoundsException e){
	    	// 에러가 발생했을 경우 디폴트로 사이즈 30짜리 생성.
	    	board_size = 30;
	    	// 디폴트 설정으로 파일은 같은 디렉토리에 생성되도록.
	    	resultFilePath = "./result" + board_size + ".txt";
	    }
		
		
		// 파일 설정.
		File file = new File(resultFilePath);
		
		// 출력 방향을 바꾸기 위한 프린트 스트립 객체 생성.
		PrintStream printStream = new PrintStream(new FileOutputStream(file));
		
		// standard out과 err을 file로 변경
		System.setOut(printStream);
		System.setErr(printStream);

		// 싱글턴 패턴으로 설계한 State 클래스 인스턴스 생
		State singletonState = State.getInstance();
		
		// 설정한 사이즈만큼의 보드를 생성하고 퀸을 랜덤배치.
		singletonState.setSize(board_size);
		
		
		// 탐색하고자 하는 열 인덱스.
		int target_col = 0;
		
		// 탐색하고자 하는 열에 있는 퀸의 위치와 최적의 위치를 담는 변수.
		int current_queen_row, optimal_row;
		
		// 보드의 스냅샷 비교를 위한 변수.
		// local optimum에 빠졌는지 체크.
		ArrayList<Integer> old_snapshot = new ArrayList<Integer>();
		ArrayList<Integer> new_snapshot = new ArrayList<Integer>();
		
		
		System.out.println(">Hill climbing");
		
		// 시간측정 시작.
		long startTime = System.currentTimeMillis();
		while(true) {
			// 탐색하고자하는 열의 퀸 위치를 가져온다.
			current_queen_row = singletonState.getQueenRow(target_col);
			
			// 탐색하고자하는 열에서 퀸이 이동할 최적의 위치를 찾는다.
			optimal_row = singletonState.moveQueenByLocalSearch(target_col);
			
			// 휴리스틱 총합이 0이면 빠져나온다.
			if(singletonState.getHeuristicIndexSum() == 0) break;
			
			// 스냅샷에 해당 탐색 열의 퀸 위치를 추가한다.
			new_snapshot.add(optimal_row);
			
			// 탐색하고자 하는 열의 인덱스에 1을 더한다.
			target_col += 1;
			
			// 탐색하고자 하는 열이 보드사이즈를 넘어가면 다시 0으로 만들어줌.
			if(target_col == board_size){
				// 스냅샷을 비교.
				// 스냅샷을 비교해서 같으면 local_optimal이므로 다시 랜덤 배치한다.
				if(equalArrayLists(old_snapshot, new_snapshot)){
					// 랜덤 객체 생성 -> 난수 생성을 위함.
					Random rand = new Random();
					
					// 0 ~ size -1 까지의 난수를 생성.
					int rand_num = rand.nextInt(board_size);
					
					// 계속 최선의 선택을 하면 local optimal에서 빠져나올 수 없으므로 차선을 선택한다.
					singletonState.moveQueenToSecondOptimal(rand_num);
					
				} 
				
				// new_snapshot -> old_snapshot으로 백업.
				copyArrayList(new_snapshot, old_snapshot);
				
				// new_snapshot 초기화.
				new_snapshot.clear();
				
				// target_col을 다시 0으로 내린다.
				target_col = 0;
			}
		}
		// 루프문을 빠져나왔다면 해답을 찾았음을 의미.
		
		// 시간측정 끝.
		long stopTime = System.currentTimeMillis();
		
		// 경과시간.
		long collapsedTime = (stopTime - startTime);
				
		// 퀸 위치 출력
		singletonState.printQueenPosition();
				
		// 경과시간 출력.
		System.out.println("Total Elapsed Time: " + collapsedTime + "ms");
		
	}
	
	// ArrayList 타입의 두 인스턴스를 비교
	// 같으면 true, 이외의 경우에는 false를 리턴.
	public static  boolean equalArrayLists(ArrayList<Integer> a, ArrayList<Integer> b){     
	    // Check for sizes and nulls
	    if ((a.size() != b.size()) || (a == null && b!= null) || (a != null && b== null)){
	        return false;
	    }

	    if (a == null && b == null) return true;
	    
	    // 인덱스를 하나씩 옮겨가면서 비교.
	    for (int i = 0 ; i < a.size(); i++){
	    	if (a.get(i) != b.get(i)){
	    		// 하나라도 다르면 false 반환.
	    		return false;
	    	}
	    }
	    
	    // 모두 같은 경우에는 true 반환.
	    return true;
	}
	
	// ArrayList<Integer> 타입 간 값을 복사하는 함수.
	public static void copyArrayList(ArrayList<Integer> source, ArrayList<Integer> dest){
		// 복사 대상의 배열을 초기화.
		dest.clear();	    
	    for (int i = 0 ; i < source.size(); i++){
	    	dest.add(source.get(i));
	    }

	}
}
