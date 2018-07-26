

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q04 {
	public static void main (String[] args) {
		int n = 20, m = 3;
		//int n = 100, m = 5;
		//System.out.println(solve(m, n));
		List<Integer> list = new ArrayList<>();
		list.add(n);
		int ans = 0;
		while (list.size() > 0) {
			//disp(list);
			List<Integer> tmp = new ArrayList<>();
			int cnt = 0, li = 0;
			while (li < list.size() && cnt < m) {
				int l = list.get(li++);
				if (l == 1) continue;
				if (l / 2 > 1)     tmp.add(l / 2);
				if (l - l / 2 > 1) tmp.add(l - l / 2);
				cnt++;
			}
			for (; li < list.size(); li++)
				tmp.add(list.get(li));
			sort(tmp, true);
			list = tmp;
			ans++;
		}
		System.out.println(ans);
	}
	public static void disp (List<Integer> list) {
		if (list.size() == 0) return;
		System.out.print("[" + list.get(0));
		for (int i = 1; i < list.size(); i++)
			System.out.print(", " + list.get(i));
		System.out.println("]");
	}
	public static void sort (List<Integer> list, boolean is_desc) {
		Collections.sort(list);
		int size = list.size();
		for (int i = 0; i < size / 2; i++) {
			int tmp = list.get(i);
			list.set(i, list.get(size - i - 1));
			list.set(size - i - 1, tmp);
		}
	}
	// 模範解答
	public static int solve (int m, int n) {
		int ret = 0;
		int current = 1;
		while (current < n) {
			current += current < m ? current : m;
			ret++;
		}
		return ret;
	}
}
