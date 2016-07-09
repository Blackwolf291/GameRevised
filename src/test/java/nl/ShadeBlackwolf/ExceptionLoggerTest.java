package nl.ShadeBlackwolf;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nl.ShadeBlackwolf.exceptions.ExceptionLogger;

import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class ExceptionLoggerTest {

	private ExceptionLogger logger;
	private File logFile;
	@Mock
	private Exception mockException; 
	
	@Before
	public void setup(){
		logger = new ExceptionLogger();
		logFile = new File("error.log");
	}

	@Test
	public void exceptionLogIsCreated() {
		logger.logException(new RuntimeException());
		assertTrue(logFile.exists());
		logFile.delete();
	}
	@Test
	public void whenLogFileExistsNoExceptionIsThrown() throws Exception {
		logFile.createNewFile();
		logger.logException(new RuntimeException());
		assertTrue(logFile.exists());
		logFile.delete();
	}
	
	@Test
	public void logFileContainsException() throws Exception{
		RuntimeException e = new RuntimeException();
		logger.logException(e);
		try (FileReader fr = new FileReader(logFile);BufferedReader reader = new BufferedReader(fr)){
			assertEquals(e.toString(), reader.readLine());
			for (int i = 0; i < e.getStackTrace().length; i++){
				assertEquals("\tat " + e.getStackTrace()[i].toString(), reader.readLine());
			}
		}
	}
	
	@Test
	public void testExceptionsNeverThrown(){
		MockitoAnnotations.initMocks(this);
		doThrow(new RuntimeException()).when(mockException).printStackTrace(any(PrintWriter.class));
		logger.logException(mockException);
	}

}
