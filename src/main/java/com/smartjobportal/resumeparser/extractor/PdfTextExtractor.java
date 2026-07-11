package com.smartjobportal.resumeparser.extractor;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import com.smartjobportal.resumeparser.util.ResumeParsingUtil;

@Component
public class PdfTextExtractor {

	public String extractText(File file) throws IOException {

		try (PDDocument document = Loader.loadPDF(file)) {

			PDFTextStripper pdfTextStripper = new PDFTextStripper();

			String extractedText = pdfTextStripper.getText(document);

			System.out.println("========== RAW PDF TEXT ==========");
			System.out.println(extractedText);
			System.out.println("==================================");

			return ResumeParsingUtil.cleanText(extractedText);
		}
	}
}