import java.util.*;

public class BalancedParens {
    
    public static boolean isBalanced(String expression) {
        char a[] = expression.toCharArray();
        Stack<Character> s = new Stack<Character>();
        for(int i = 0 ; i < a.length ; i++){
        	if(isopen(a[i])){
        		s.push(a[i]);
        	}else if(s.empty()){
        		return false;
        	}else{
        		if(s.peek().equals(rev(a[i]))){
        			s.pop();
        		}else{
        			return false;
        		}
        	}
        }
        if(s.empty())
        	return true;
        return false;
    }
    private static char rev(char c){
    	if(c == ']')
    		return '[';
    	else if(c == '}')
    		return '{';
    	else if(c == ')')
    		return '(';
    	else 
    		return ' ';
    }
    private static boolean isopen(char c){
    	if(c == '[' || c == '{' || c == '(')
    		return true;
    	else
    		return false;
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
        }
        in.close();
    }
}
