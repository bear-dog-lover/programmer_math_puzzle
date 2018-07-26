import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q48 {

	static final int N = 16;

	static Map<String, String> memo = new HashMap<>();

	static final List<Character> symbol = Arrays.asList(new Character[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'});

	public static void main (String[] args) {
		long s = System.currentTimeMillis();

		String str = "abcdef";
		Set<String> roop = new HashSet<>();
		roop.add(str);
		while (true) {
			String gray = gray(str);
			if (roop.contains(gray)) {
				System.out.println(roop.size());
				break;
			}
			roop.add(gray);
			str = gray;
		}

		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static String gray (String hex_str) {
		if (memo.containsKey(hex_str)) return memo.get(hex_str);

		int len = hex_str.length();
		if (len == 1) return hex_str;

		String ret = gray(hex_str.substring(0, len - 1))
						+ f(hex_str.substring(len - 2, len));

		memo.put(hex_str, ret);
		return ret;
	}

	static char f (String str) {
		char left  = str.charAt(0);
		char right = str.charAt(1);
		int idx = symbol.indexOf(right) - symbol.indexOf(left);
		if (idx < 0) idx += N;
		return symbol.get(idx);
	}

}
