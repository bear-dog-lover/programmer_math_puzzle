import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Q63 {

	static Set<LocalDate> holidays = new HashSet<>();
	static final int MAX    = 5;
	static final int HEIGHT = 6;
	static final int WIDTH  = 7;

	public static void main (String[] args) throws IOException {
		long s = System.currentTimeMillis();

		BufferedReader br =
				new BufferedReader(new FileReader("C:\\Users\\hangn.DESKTOP-TAM40T8\\Downloads\\sample\\q63.txt"));

		String line = "";
		while ((line = br.readLine()) != null) {
			String[] ary = line.split("/");
			int year  = Integer.parseInt(ary[0]);
			int month = Integer.parseInt(ary[1]);
			int day   = Integer.parseInt(ary[2]);
			holidays.add(LocalDate.of(year, month, day));
		}

		int ans = 0;
		for (int year = 2006; year <= 2015; year++) {
			for (int month = 1; month <= 12; month++)
				ans += solve(year, month);
		}

		br.close();
		System.out.println(ans);
		long e = System.currentTimeMillis();
		System.out.println("time:" + (e-s) + "ms");
	}

	static int solve (int year, int month) {
		boolean[][] is_not_holiday = new boolean[HEIGHT][WIDTH];
		LocalDate start = LocalDate.of(year, month, 1);
		LocalDate lim = start.plusMonths(1);

		int height = 0;
		for (; start.isBefore(lim); start = start.plusDays(1)) {
			int val = start.getDayOfWeek().getValue() % 7;
			is_not_holiday[height][val] = !holidays.contains(start) && (val != 0 && val != 6);
			if (val == WIDTH - 1) height++;
		}

		return find_maximum_square(is_not_holiday);
	}

	static int find_maximum_square (boolean[][] ary) {
		int ret = 0;
		// 開始位置
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				if (!ary[y][x]) continue;
				// 長方形の縦横
				for (int h = 1; h <= MAX; h++) {
					loop_w:
					for (int w = 1; w <= MAX; w++) {
						for (int hi = 0; hi < h; hi++) {
							if (y + hi >= HEIGHT) continue loop_w;
							for (int wi = 0; wi < w; wi++) {
								// はみだしたか、土日祝日
								if (x + wi >= WIDTH || !ary[y + hi][x + wi]) continue loop_w;
							}
						}
						ret = Math.max(ret, h * w);
					}
				}
			}
		}
		return ret;
	}
}
