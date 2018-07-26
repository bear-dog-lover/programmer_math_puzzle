

import static util.util_string.*;

public class Q01 {
	public static void main (String[] args) {

		for (int i = 1; i < 100000; i += 2) {
			if (!is_reverse(i + "")) continue;
			if (!is_reverse(Integer.toBinaryString(i))) continue;
			if (!is_reverse(Integer.toOctalString(i))) continue;
			System.out.println(i);
		}

	}
}
