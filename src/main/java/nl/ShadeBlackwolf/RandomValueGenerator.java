package nl.ShadeBlackwolf;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomValueGenerator {
	private static RandomValueGenerator generator = new RandomValueGenerator();
	private Random randomGenerator = new Random();

	public int rollDice(int dice, int diesize){
		int result = 0;
		for (int i = dice; i > 0;i--){
			result = result + randomGenerator.nextInt(diesize) +1;
		}
		return result;
	}
	public int rollDice(int diesize){
		return randomGenerator.nextInt(diesize)+1;
	}
	public boolean flipCoin () {
		boolean result = randomGenerator.nextBoolean();
		return result;
	}
	public static RandomValueGenerator getGenerator() {
		return generator;
	}
}
