public class MaxProfit {
	public int solution(int[] A) {
		int maxProfit = 0;
		int minPrice = Integer.MAX_VALUE;
		for (int price : A) {
			minPrice = Math.min(minPrice, price);
			maxProfit = Math.max(maxProfit, price - minPrice);
		}
		return maxProfit;
	}
}
