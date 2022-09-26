package konec.superchinese.services.logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LogService {
	private static final char DEFAULT_SEPARATOR = '\t';
	private static final String NEW_LINE_SEPARATOR = "\n";

	@Value("${superchinese.folder.logs}")
	private String rootPath;

	public void writeLogApi(String fileName, String apiName, String request, String response) {
		Date dt = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String DateString = sd.format(dt);
		String logFileName = "Log_" + fileName + "_" + DateString + ".txt";
		FileWriter writer = null;

		try {
			File f = new File(rootPath + "/" + logFileName);
			writer = new FileWriter(rootPath + "/" + logFileName, true);
			f.createNewFile();
			// //Write the CSV file header
			writer.append("TimeRequest");
			writer.append(DEFAULT_SEPARATOR);
			SimpleDateFormat sdfr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String dtStr = sdfr.format(new Date());
			writer.append(dtStr);
			writer.append(DEFAULT_SEPARATOR);
			writer.append("INFO");
			writer.append(DEFAULT_SEPARATOR);
			writer.append(apiName);
			writer.append(DEFAULT_SEPARATOR);
			writer.append("request: ");
			writer.append(request);
			writer.append(DEFAULT_SEPARATOR);
			writer.append("response: ");
			writer.append(response);
			writer.append(NEW_LINE_SEPARATOR);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}
}
