package nl.ShadeBlackwolf.randomnumbergenerator;

import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Dice {
	
	private Random r = new Random();
	
	void setRandom(Random random){
		r = random;
	}

	public int roll(int numberOfDice) {
		return roll(numberOfDice, 20);
	}
	
	public int rollAndKeepHighest(int numberOfDiceRolled, int numberOfDiceKept) {
		validateDiceCounts(numberOfDiceRolled, numberOfDiceKept);
		int[] rolls = new int[numberOfDiceRolled];
		for (int i = 0; i < numberOfDiceRolled; i++){
			rolls[i] = r.nextInt(6)+1;
		}
		Arrays.sort(rolls);
		int result = 0;
		for (int i = 0, j = numberOfDiceRolled-1; i < numberOfDiceKept; i++, j--){
			result += rolls[j];
		}
		return result;
	}

	private void validateDiceCounts(int numberOfDiceRolled, int numberOfDiceKept) {
		if(numberOfDiceKept>numberOfDiceRolled){
			throw new MoreDiceKeptThanRolled();
		}
	}
	
	private class MoreDiceKeptThanRolled extends RuntimeException{}

	public int roll(int numberOfDice, int sides) {
		int sum = 0;
		for (int i = 0; i<numberOfDice; i++){
			sum += r.nextInt(sides)+1;
		}
		return sum;
	}
}
