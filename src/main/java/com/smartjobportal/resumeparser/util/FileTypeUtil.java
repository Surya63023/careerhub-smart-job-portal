package com.smartjobportal.resumeparser.util;

public final class FileTypeUtil {

	private FileTypeUtil() {
	}

	public static boolean isPdf(String fileName) {

		return fileName != null && fileName.toLowerCase().endsWith(".pdf");
	}

	public static boolean isDocx(String fileName) {

		return fileName != null && fileName.toLowerCase().endsWith(".docx");
	}

	public static boolean isSupportedFileType(String fileName) {

		return isPdf(fileName) || isDocx(fileName);
	}
}