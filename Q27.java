

import java.util.HashSet;
import java.util.Set;

public class Q27 {

	static int X = 6;
	static int Y = 4;
	static int[] dx = { 1, 0,-1, 0};
	static int[] dy = { 0,-1, 0, 1};

	public static void main (String[] args) {

		Set<Integer> ini = new HashSet<>();
		System.out.println(solve(Y - 1, 0, Y, 0, 1, ini)
					     + solve(    Y, 1, Y, 0, 0, ini));

	}

	/**
	 * @param dir	向き(0:右/1:上/2:左/3:下)
	 */
	static int solve (int y, int x, int from_y, int from_x, int dir, Set<Integer> path) {
		if (y < 0 || y > Y || x < 0 || x > X)  return 0;
		if (check(path, y, x, from_y, from_x)) return 0;
		//System.out.println("(" + from_y + ", " + from_x + ") -> (" + y + ", " + x + ")");
		if (y == 0 && x == X) return 1;
		int next_dir = (dir + 1) % 4;
		Set<Integer> next_path = next_path(path, y, x, from_y, from_x);
		int s1 = solve(     y + dy[dir],      x + dx[dir], y, x,      dir, next_path);
		int s2 = solve(y + dy[next_dir], x + dx[next_dir], y, x, next_dir, next_path);
		return s1 + s2;
	}

	static Set<Integer> next_path (Set<Integer> set, int ny, int nx, int y, int x) {
		Set<Integer> ret = new HashSet<>();
		for (int s : set) ret.add(s);
		ret.add(ny * 1000 + nx + 100 + y * 10 + x);
		return ret;
	}

	static boolean check (Set<Integer> set, int y, int x, int from_y, int from_x) {
		return set.contains(from_y * 1000 + from_x + 100 + y * 10 + x) ||
				set.contains(y * 1000 + x + 100 + from_y * 10 + from_x);
	}
}
