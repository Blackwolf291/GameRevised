package nl.ShadeBlackwolf.randomnumbergenerator;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.Answer;

public class RandomDiceTest {
	Dice dice;
	
	@Mock
	Random random;
	
	@Before
	public void setup(){
		dice = new Dice();
	}

	@Test
	public void rollThreeDice(){
		rollDice(3);
	}

	@Test
	public void canRollSingleDice(){
		rollDice(1);
	}

	private void rollDice(int numberOfDice) {
		for (int i = 0; i<10; i++){
			int result = dice.roll(numberOfDice);
			assertTrue(result>=numberOfDice && result <=numberOfDice*20);
		}
	}

	@Test
	public void canKeepHighest(){
		MockitoAnnotations.initMocks(this);
		dice.setRandom(random);
		setMockResponse();
		assertEquals(15, dice.rollAndKeepHighest(6,3));
	}

	private void setMockResponse() {
		when(random.nextInt(anyInt())).thenAnswer(new Answer<Integer>(){
			int returnValue = 0;
			
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				return returnValue++;
			}});
	}
	
	@Test (expected = Exception.class)
	public void keepingMoreDiceThanRolled_Throws(){
		dice.rollAndKeepHighest(3,6);
	}
	
	@Test
	public void canRoll8SidedDice(){
		dice.roll(1, 8);
	}
}
