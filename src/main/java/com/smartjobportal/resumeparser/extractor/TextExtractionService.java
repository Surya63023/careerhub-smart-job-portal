package com.smartjobportal.resumeparser.extractor;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartjobportal.resumeparser.util.FileTypeUtil;

@Service
public class TextExtractionService {

	@Autowired
	private PdfTextExtractor pdfTextExtractor;

	@Autowired
	private DocxTextExtractor docxTextExtractor;

	public String extractText(File file) throws IOException {

		String fileName = file.getName();

		if (FileTypeUtil.isPdf(fileName)) {
			return pdfTextExtractor.extractText(file);
		}

		if (FileTypeUtil.isDocx(fileName)) {
			return docxTextExtractor.extractText(file);
		}

		throw new IllegalArgumentException("Unsupported file type: " + fileName);
	}
}