import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q37 {

	public static void main (String[] args) {
		Set<Integer> loops = new HashSet<>();
		char[] c = {'0', '7', '8', '9'};

		main:
		for (int i = 111111; i <= 666666; i++) {
			for (int ci = 0; ci < 4; ci++)
				if ((i + "").indexOf(c[ci]) != -1) continue main;

			if (loops.contains(i)) continue;

			List<Integer> list = new ArrayList<>();
			int n = i, end = -1;
			list.add(n);
			while (true) {
				n = reverse(n);
				if (list.contains(n)) {
					end = n;
					break;
				} else {
					list.add(n);
				}
			}

			for (int j = list.indexOf(end); j < list.size(); j++)
				loops.add(list.get(j));
		}

		System.out.println(46656 - loops.size());
	}

	static int reverse (int n) {
		int cnt =  n / 100000, head = cnt;
		for (int i = 0; i < cnt; i++) {
			head = n / 100000;
			n = n % 100000 * 10 + (7 - head);
		}
		return n;
	}
}
