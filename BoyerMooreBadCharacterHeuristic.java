import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BoyerMooreBadCharacterHeuristic {
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		String txt = scan.nextLine();		//text
		String pat = scan.nextLine();		//pattern
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		search(txt, pat, map);
		scan.close();
	}
	private static void search(String txt, String pat, Map<Character, Integer> map){
		pre_processor(pat, map);
		//System.out.println(map);
		int num_shifts = 0;
		while(num_shifts <= (txt.length() - pat.length())){
			int j = pat.length() - 1;
			if(txt.charAt(j + num_shifts) == pat.charAt(j)){
				while(j >= 0 && txt.charAt(j + num_shifts) == pat.charAt(j)){
					j--;
				}
				if(j < 0){
					System.out.println("Pattern found at position : " + num_shifts);
					//num_shifts += (num_shifts + pat.length() < txt.length())?(pat.length() - map.get(txt.charAt(pat.length() + num_shifts))) : 1;
					if(pat.length() + num_shifts >= txt.length())
						num_shifts += 1;	
					else if(map.get(txt.charAt(pat.length() + num_shifts)) == null)
						num_shifts += pat.length() + 1;
					else{
						num_shifts += (num_shifts + pat.length() < txt.length())?(pat.length() - map.get(txt.charAt(pat.length() + num_shifts))) : 1;
						//num_shifts += pat.length() - map.get(txt.charAt(pat.length() + num_shifts));
					}
				}else{
					if(map.get(txt.charAt(j + num_shifts)) == null){
						num_shifts += j+1;
					}else{
						num_shifts += Math.max(1, j - map.get(txt.charAt(j + num_shifts)));
					}
				}
			}else{
				if(map.get(txt.charAt(j + num_shifts)) == null){
					num_shifts += pat.length();
				}else{
					num_shifts += j - map.get(txt.charAt(j + num_shifts));
				}
			}
			
			/*ANOTHER TECHNIQUE*/
			
			/*int j = pat.length() - 1;
			while(j >=0 && txt.charAt(j + num_shifts) == pat.charAt(j)){
				j--;
			}
			if(j < 0){
				System.out.println("Pattern occurs at : " + num_shifts);
				num_shifts += (num_shifts + pat.length() < txt.length())?(pat.length() - map.get(txt.charAt(pat.length() + num_shifts))) : 1;
			}else{
				if(map.get(txt.charAt(j + num_shifts)) == null){
					num_shifts += pat.length();
				}else{
					num_shifts += Math.max(1, j - map.get(txt.charAt(j + num_shifts)));
				}
			}
			
			/*ANOTHER TECHNIQUE END*/
			
		}
	}
	private static void pre_processor(String pat, Map<Character, Integer> map){
		for(int i = 0 ; i < pat.length(); i++){
			map.put(pat.charAt(i), i);
		}
	}
}
