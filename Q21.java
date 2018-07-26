

public class Q21 {

	public static void main (String[] args) {

		int cnt_zero = 0;
		boolean[] bef = {true, true};
		// i段目を作る
		main:
		for (int i = 3; ; i++) {
			// i段目
			boolean[] now = new boolean[i];
			// 右端と左端は1
			now[0] = now[i - 1] = true;
			// つくる
			for (int j = 1; j < i - 1; j++) {
				now[j] = bef[j - 1]^bef[j];
				if (now[j]) continue;
				if (++cnt_zero == 2014) {
					System.out.println(i);
					break main;
				}
			}
			bef = now;
		}

	}

}
