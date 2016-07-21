package nl.ShadeBlackwolf.randomnumbergenerator;

import java.util.Random;

public class Coin {
	Random r = new Random();

	public boolean flip() {
		return r.nextBoolean();
	}

	public int flipUntilTails() {
		int heads = 0;
		while(flip()){
			heads++;
		}
		return heads;
	}

	void setRandom(Random random) {
		r = random;
	}

	public int flip(int numberOfCoins) {
		int heads = 0;
		for (int i = 0; i<numberOfCoins; i++){
			if (flip()){heads++;}
		}
		return heads;
	}

}
