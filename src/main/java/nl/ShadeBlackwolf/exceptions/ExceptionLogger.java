package nl.ShadeBlackwolf.exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Component;

@Component
public class ExceptionLogger {
	
	private File logFile = new File("error.log");
	
	public void logException(Exception e) {
		try {
			ensureLogFile();
			writeExceptionToFile(e);
		} catch (Exception e1) {
			//caught because customers may never see this in game.
		}
	}

	private void writeExceptionToFile(Exception e) throws FileNotFoundException {
		try(PrintWriter out = new PrintWriter(logFile)){
			e.printStackTrace(out);
		}
	}

	private void ensureLogFile() throws IOException {
		if (!logFile.exists()){
			logFile.createNewFile();
		}
	}
}
