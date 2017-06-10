package assignment5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {
		// 인자로 받을 숫자.
		int map_size;
		
		// 인자로 받을 출력파일 경로.
		String resultFilePath;
		// 예외처리 
		try{
			map_size = Integer.parseInt(args[0]);
			// 두번째 인자로 받은 출력파일 절대경로
			resultFilePath = args[1];
	    }catch(Exception e){
	    	map_size = 9;
	    	resultFilePath = ".";
	    }
		
		// 파일 경로 설정.
		File file = new File(resultFilePath+"/result"+map_size+".txt");
		PrintStream printStream = new PrintStream(new FileOutputStream(file));
		
		// standard out과 err을 file로 변경
		System.setOut(printStream);
		System.setErr(printStream);
				
		// population size: 체스판 크기의 3제곱.
		int population_size = (int) Math.pow(map_size, 3);
		
		// Selection된 객체들은 그대로 복제
		
		ArrayList<Integer> seed_list = new ArrayList<Integer>();
		for(int i = 0; i < map_size ; i++){
			seed_list.add(i);
		}
		
		// 유전자를 담을 리스트.
		List<Gene> g_list = new ArrayList<Gene>();
		List<Gene> uniq_check_g_list = new ArrayList<Gene>();
		
		// 중복체크 리스트
		ArrayList<ArrayList<Integer> > uniq_check = new ArrayList<ArrayList<Integer> >(); 
		
		// population 만큼 랜덤 유전자 배열 생성.
		for(int i = 0 ; i < population_size ; i++){
			// 랜덤 셔플을 위한 시드.
			long seed = System.nanoTime();
			// 셔플.
			Collections.shuffle(seed_list, new Random(seed));
			
			Gene new_g = new Gene(seed_list); 
			g_list.add(new_g);
		}
		
		// 시간측정 시작.
		long startTime = System.currentTimeMillis();
		
		// 루프 시작.
		while(true){
			// g_list 중복제거
			uniq_check.clear();
			uniq_check_g_list.clear();
			uniq_check_g_list.addAll(g_list);
			g_list.clear();
			
			for(Gene g:uniq_check_g_list){
				if(!uniq_check.contains(g.location_list)){
					uniq_check.add(g.location_list);
					g_list.add(new Gene(g.location_list));
				}
			}
			
			// 정렬.
			// 휴리스틱 값에 의해 정렬.
			Collections.sort(g_list);
			
			// 1등짜리를 뽑아서 검증.
			State s = State.getInstance();
			s.setQueenLocation(g_list.get(0).location_list);
			
			// Termination condition: 퀸들의 충돌이 발생하지 않는 경우.
			if(s.getHeuristicIndexSum() == 0){
				// 시간측정 끝.
				long stopTime = System.currentTimeMillis();
				
				System.out.println(">Genetic Algorithm");
				
				// 경과시간 출력.
				System.out.println("Total Elapsed Time: " + (stopTime - startTime) + "ms");
				
//				System.out.println("탐색 종료.");
				
				s.printQueenPosition();
				
				break;
			} 
			
			// Selection: 상위 10%
			g_list = g_list.subList(0, (int) (g_list.size() * 0.1));
			List<Gene> g_list_copy = new ArrayList<Gene>();
			g_list_copy.addAll(g_list);
			
			Gene father, mother;
			int partition_index = map_size / 2;
			// 부모 한쌍 끼리 왼쪽 오른쪽 나눠가며 섞는다.
			for(int i = 0 ; i < g_list_copy.size() ; i++){
				for(int j = i + 1 ; j < g_list_copy.size(); j++){
					father = g_list_copy.get(i);
					mother = g_list_copy.get(j);
					
					
					// 부모가 서로 같은 경우는 pass
					if(father.location_list.equals(mother.location_list)){
						break;
					} 
										
					ArrayList<Integer> child1_queen_locations = new ArrayList<Integer>();
					// 아빠 왼쪽, 엄마 오른쪽.
					child1_queen_locations.addAll(father.location_list.subList(0, partition_index));
					child1_queen_locations.addAll(mother.location_list.subList(partition_index, map_size));
					Gene child1 = new Gene(child1_queen_locations);
					
					// 자녀의 행 번호가 중복되는 게 없을 경우에만 추가. 
					if(child1.checkRowDuplication()){
						g_list.add(child1);
					}
						
					
					ArrayList<Integer> child2_queen_locations = new ArrayList<Integer>();
					// 아빠 오른쪽, 엄마 왼쪽.
					child2_queen_locations.addAll(mother.location_list.subList(0, partition_index));
					child2_queen_locations.addAll(father.location_list.subList(partition_index, map_size));
					Gene child2 = new Gene(child2_queen_locations); 
					if(child2.checkRowDuplication()){
						g_list.add(child2);
					}
						
				}
			}			
			// 남는 부분은 랜덤으로 채운다.
			while(g_list.size() < population_size){
				// 랜덤 셔플을 위한 시드.
				long seed = System.nanoTime();
				// 셔플.
				Collections.shuffle(seed_list, new Random(seed));
				Gene new_g = new Gene(seed_list); 
				g_list.add(new_g);
			}
			
			// population 크기만큼 자른다.
			g_list = g_list.subList(0, population_size);

		}
	}

}
