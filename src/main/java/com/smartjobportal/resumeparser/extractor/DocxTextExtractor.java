package com.smartjobportal.resumeparser.extractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.springframework.stereotype.Component;

import com.smartjobportal.resumeparser.util.ResumeParsingUtil;

@Component
public class DocxTextExtractor {

	public String extractText(File file) throws IOException {

		try (FileInputStream inputStream = new FileInputStream(file);

				XWPFDocument document = new XWPFDocument(inputStream);

				XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {

			String extractedText = extractor.getText();

			return ResumeParsingUtil.cleanText(extractedText);
		}
	}
}