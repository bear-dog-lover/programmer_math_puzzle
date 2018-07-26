

public class Q20 {

	public static void main (String[] args) {

		int[] nums = {1,14,14,4,11,7,6,9,8,10,10,5,13,2,3,15};

		int sum = sum(nums);
		int[] dp = new int[sum + 1];

		dp[0] = 1;
		for (int i = 0; i < nums.length; i++) {
		for (int j = sum; j >= nums[i]; j--)
			dp[j] += dp[j - nums[i]];
		}

		int ans = 0, max = 0;
		for (int i = 0; i <= sum; i++) {
			if (max < dp[i]) {
				max = dp[i];
				ans = i;
			}
		}

		System.out.println("ans = " + ans + ", max = " + max);

	}

	public static int sum (int[] nums) {
		int sum = 0;
		for (int n : nums) sum += n;
		return sum;
	}

}
