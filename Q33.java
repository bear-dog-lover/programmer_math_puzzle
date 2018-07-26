import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Q33 {

	public static void main (String[] args) throws IOException {

		System.setProperty("file.encoding", "UTF-8");
		BufferedReader br = new BufferedReader(new FileReader("q33.csv"));

		// ヘッダを読み飛ばす
		br.readLine();

		final int SIZE = 100;
		String[] kami = new String[SIZE];
		String[] simo = new String[SIZE];
		for (int i = 0; i < SIZE; i++) {
			String[] line = br.readLine().split(",");
			kami[i] = line[3];
			simo[i] = line[4];
		}
		br.close();

		System.out.println(solve(kami) + solve(simo));
	}

	public static int solve (String[] strs) {
		int ans = 0;
		for (int i = 0; i < strs.length; i++) {
			cnt:
			for (int cnt = 1; ; cnt++) {
				String head = strs[i].substring(0, cnt);
				for (int j = 0; j < strs.length; j++) {
					if (i == j) continue;
					if (strs[j].startsWith(head)) continue cnt;
				}
				ans += cnt;
				break;
			}
		}
		return ans;
	}

}