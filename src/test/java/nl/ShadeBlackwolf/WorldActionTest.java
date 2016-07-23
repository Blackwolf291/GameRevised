package nl.ShadeBlackwolf;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class WorldActionTest {
	
	@Autowired
	private WorldAction[] actions;

	@Test
	public void allInputMatchersAreUnique() {
		HashMap<String, String> matchers = new HashMap<>();
		for (WorldAction worldAction : actions) {
			for (String matcher : worldAction.getInputMatchers()){
				if (!matchers.containsKey(matcher)){
					matchers.put(matcher, worldAction.getCanonicalName());
				} else {
					fail("duplicate matcher '" + matcher + "' declared by "
							 + worldAction.getCanonicalName() + " and " + matchers.get(matcher));
				}
			}
		}
	}

}
