package test;

public class StringCalculator {

	public String getDelimiter(String strNum) {
		String[] split_str = strNum.split("\n");
		
		return split_str[0].replace("//", "");
	}
	
	public String[] split(String strNum) {
		String delimiter = ",|:";
		
		if(strNum.indexOf("//") >= 0 && strNum.indexOf("\n") >= 0) {
			delimiter = getDelimiter(strNum);
			
			strNum = strNum.split("\n")[1];
		}
		
		return strNum.split(delimiter);
	}
	
	private boolean isMinus(String strNum) {
		return Integer.parseInt(strNum) < 0;
	}
	
	private int convertNumber(String strNum) {
		if(isMinus(strNum)) {
			throw new RuntimeException("Minus!!");
		}
		
		return Integer.parseInt(strNum);
	}
	
	public int add(String strNum) {
		int sum = 0;
		
		if(strNum == null || strNum.isEmpty()) {
			return sum;
		}
		
		for(String num : split(strNum)) {
			sum += convertNumber(num);
		}
		
		return sum;
	}

}
