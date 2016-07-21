package nl.ShadeBlackwolf.randomnumbergenerator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class RandomCoinsTest {
	Coin coin;
	
	@Mock
	Random random;
	
	
	@Before
	public void setup(){
		coin = new Coin();
	}

	@Test
	public void canFlipCoin(){
		coin.flip();
	}
	
	@Test
	public void canFlipUntilTailsAndCountHeads(){
		MockitoAnnotations.initMocks(this);
		coin.setRandom(random);
		setMockResponse();
		assertEquals(3,coin.flipUntilTails());
	}

	@Test
	public void canFlipManyAndGetNumberOfHeads(){
		MockitoAnnotations.initMocks(this);
		coin.setRandom(random);
		setMockResponse();
		assertEquals(3,coin.flip(5));
	}
	
	private void setMockResponse() {
		when(random.nextBoolean()).thenAnswer(new Answer<Boolean>(){
			int returnValue = 0;
			
			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				returnValue++;
				return returnValue<4;
			}});
	}
}
