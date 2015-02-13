import java.util.ArrayList;
import java.util.List;

public class CountSemiprimes {
	public int[] solution(int N, int[] P, int[] Q) {
		List<Integer> primes = buildPrimes(N);
		boolean[] semiprimes = buildSemiprimes(N, primes);
		int[] semiprimeNums = buildSemiprimeNums(semiprimes);

		int[] result = new int[P.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = computeSemiprimeNum(semiprimeNums, P[i], Q[i]);
		}
		return result;
	}

	List<Integer> buildPrimes(int limit) {
		List<Integer> primes = new ArrayList<Integer>();
		boolean[] composites = new boolean[limit + 1];
		for (int i = 2; i < composites.length; i++) {
			if (!composites[i]) {
				primes.add(i);
				for (long j = (long) i * i; j < composites.length; j += i) {
					composites[(int) j] = true;
				}
			}
		}
		return primes;
	}

	boolean[] buildSemiprimes(int limit, List<Integer> primes) {
		boolean[] semiprimes = new boolean[limit + 1];
		for (int i = 0; i < primes.size(); i++) {
			int prime1 = primes.get(i);
			if (prime1 * prime1 >= semiprimes.length) {
				break;
			}
			for (int j = i; j < primes.size(); j++) {
				int prime2 = primes.get(j);
				int semiprime = prime1 * prime2;
				if (semiprime >= semiprimes.length) {
					break;
				}
				semiprimes[semiprime] = true;
			}
		}
		return semiprimes;
	}

	int[] buildSemiprimeNums(boolean[] semiprimes) {
		int[] semiprimeNums = new int[semiprimes.length];
		int semiprimeNum = 0;
		for (int i = 0; i < semiprimeNums.length; i++) {
			if (semiprimes[i]) {
				semiprimeNum++;
			}
			semiprimeNums[i] = semiprimeNum;
		}
		return semiprimeNums;
	}

	int computeSemiprimeNum(int[] semiprimeNums, int begin, int end) {
		return semiprimeNums[end] - (begin == 0 ? 0 : semiprimeNums[begin - 1]);
	}
}
