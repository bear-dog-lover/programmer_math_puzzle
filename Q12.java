

public class Q12 {

	public static void main (String[] args) {

		//System.out.println(check("1357902468"));
		boolean hit1 = false, hit2 = false;

		for (int i = 2; !hit1 || !hit2; i++) {
			double sqrt = Math.sqrt(i);
			String[] spl = (sqrt + "").split("\\.");
			// 整数部を含む場合
			if (!hit1 && (spl[0]+spl[1]).length()>=10 && check((spl[0]+spl[1]).substring(0,10))) {
				System.out.println("patter1\t" + i + ":" + sqrt);
				hit1 = true;
			}
			// 整数部を含まない場合
			if (!hit2 && spl[1].length()>=10 && check(spl[1].substring(0,10))) {
				System.out.println("pattern2\t"+i + ":" + sqrt);
				hit2 = true;
			}
		}

	}



	public static boolean check (String str) {
		if (str.length() < 10) return false;
		for (int i = 0; i < 10; i++)
			if (str.indexOf(""+i) == -1) {
				return false;
			}
		return true;
	}

}
