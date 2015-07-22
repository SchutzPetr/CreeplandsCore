package cz.Sicka.Core.Utils;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import cz.Sicka.Core.Core;

public class CoreLogger {
	private static FileHandler fileTxt;
	private static SimpleFormatter formatterTxt;

	private static FileHandler fileHTML;
	private static Formatter formatterHTML;
	
	static public void setup() throws IOException {
		// get the global logger to configure it
		Logger logger = Logger.getLogger(Core.class.getName());

	    // suppress the logging output to the console
	    Logger rootLogger = Logger.getLogger("");
	    Handler[] handlers = rootLogger.getHandlers();
	    if (handlers[0] instanceof ConsoleHandler) {
	      rootLogger.removeHandler(handlers[0]);
	    }

	    logger.setLevel(Level.INFO);
	    fileTxt = new FileHandler(Core.getInstance().getDataFolder().getAbsolutePath() + "/Logging.txt", true);
	    fileHTML = new FileHandler(Core.getInstance().getDataFolder().getAbsolutePath() + "/Logging.html", true);

	    // create a TXT formatter
	    formatterTxt = new SimpleFormatter();
	    fileTxt.setFormatter(formatterTxt);
	    logger.addHandler(fileTxt);

	    // create an HTML formatter
	    formatterHTML = new MyHtmlFormatter();
	    fileHTML.setFormatter(formatterHTML);
	    logger.addHandler(fileHTML);
	}
/*
	
	
	File folder = new File(Core.getInstance().getDataFolder(), "debug.yml");
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	Logger logger = Logger.getLogger("CreeplandsCore");  
    FileHandler fh; 
	
	public Debug(){
		try {
			fh = new FileHandler(Core.getInstance().getDataFolder().getAbsolutePath() + "/debug.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter); 
	        logger.setUseParentHandlers(false);
		} catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
	}
	
	public void log(String msg){
		logger.info(msg);
	}
	
	public void log(Level level, String msg){
		logger.log(level, msg);
	}
	
	static private FileHandler fileTxt;
	  static private SimpleFormatter formatterTxt;

	  static private FileHandler fileHTML;
	  static private Formatter formatterHTML;

	  static public void setup() throws IOException {

	    // get the global logger to configure it
	    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	    // suppress the logging output to the console
	    Logger rootLogger = Logger.*getLogger*("");
	    Handler[] handlers = rootLogger.getHandlers();
	    if (handlers[0] instanceof ConsoleHandler) {
	      rootLogger.removeHandler(handlers[0]);
	    }

	    logger.setLevel(Level.INFO);
	    fileTxt = new FileHandler("Logging.txt");
	    fileHTML = new FileHandler("Logging.html");

	    // create a TXT formatter
	    formatterTxt = new SimpleFormatter();
	    fileTxt.setFormatter(formatterTxt);
	    logger.addHandler(fileTxt);

	    // create an HTML formatter
	    formatterHTML = new MyHtmlFormatter();
	    fileHTML.setFormatter(formatterHTML);
	    logger.addHandler(fileHTML);
	  }*/

}
