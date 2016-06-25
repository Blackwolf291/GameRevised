package nl.ShadeBlackwolf;

import java.io.File;
import java.io.PrintWriter;

import org.springframework.stereotype.Component;

@Component
public class ExceptionLogger {
	
	private File logFile = new File("error.log");
	
	void logException(Exception e) {
		try {
			logFile.createNewFile();
			try(PrintWriter out = new PrintWriter(logFile)){
				e.printStackTrace(out);
			}
		} catch (Exception e1) {
			//caught because customers may never see this in game.
		}
	}
}
