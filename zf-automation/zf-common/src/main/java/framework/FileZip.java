package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileZip {

	static String reportName ="";
	private List <String> fileList;
	private static final String OUTPUT_ZIP_FILE = "Folder.zip";
	private static final String SOURCE_FOLDER = "C:\\Users\\EE210491\\Desktop\\Latestcode\\master\\zf-automation\\zf-web\\AutomationReports28-03-2019 10-26-40-606";

	public FileZip() {
		fileList = new ArrayList < String > ();
	}

//	public static void main(String[] args) {
//		FileZip appZip = new FileZip();
//		appZip.generateFileList(new File(SOURCE_FOLDER));
//		appZip.zipIt(OUTPUT_ZIP_FILE,SOURCE_FOLDER);
//	}

	public void zipIt(String zipFile, String sourceFolder) {
		byte[] buffer = new byte[1024];
		String source = new File(sourceFolder).getName();
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);

			TestLogger.appInfo("Output to Zip : " + zipFile);
			FileInputStream in = null;

			for (String file: this.fileList) {
				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(source + File.separator + file);
				zos.putNextEntry(ze);
				try {
					in = new FileInputStream(sourceFolder + File.separator + file);
					int len;
					while ((len = in .read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
				} finally {
					in.close();
				}
			}

			zos.closeEntry();
			TestLogger.appInfo("Folder successfully compressed");

		} catch (IOException e) {
			TestLogger.appInfo(e.getMessage());
		} finally {
			try {
				zos.close();
			} catch (IOException e) {

				TestLogger.appInfo(e.getMessage());
			}
		}
	}

	public void generateFileList(File node) {
		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.toString()));
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename: subNote) {
				generateFileList(new File(node, filename));
			}
		}
	}

	private String generateZipEntry(String file) {
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}

}