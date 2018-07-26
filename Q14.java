

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q14 {

	static int ans = 0;
	static List<String> list;

	public static void main (String[] args) throws IOException {

		BufferedReader br =
				new BufferedReader(new FileReader("src/14_countries.txt"));

		final int MAX = 32;
		String[] countries = new String[MAX];
		for (int i = 0; i < MAX; i++) {
			String line = br.readLine();
			int idx = line.indexOf('(');
			countries[i] = line.substring(0, idx).toLowerCase();
			System.out.println(countries[i]);
		}
		br.close();

		solve(countries, new ArrayList<String>(), "");
		System.out.println(Arrays.toString(list.toArray()));
		System.out.println(ans);
	}

	public static void solve (String[] words, List<String> chain, String last_pushed) {
		for (int i = 0; i < words.length; i++) {
			if (chain.contains(words[i])) continue;
			if (last_pushed.equals("") ||
					last_pushed.charAt(last_pushed.length() - 1) == words[i].charAt(0)) {
				chain.add(words[i]);
				solve(words, chain, words[i]);
				chain.remove(words[i]);
			}
		}
		if (ans < chain.size()) {
			ans = chain.size();
			list = copy(chain);
		}
	}

	public static List<String> copy (List<String> list) {
		List<String> ret = new ArrayList<>();
		for (String l : list) ret.add(l);
		return ret;
	}

}
