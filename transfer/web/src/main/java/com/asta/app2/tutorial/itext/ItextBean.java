/*in_action/chapter01/HelloWorld.Java*/
package com.asta.app2.tutorial.itext;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
 
import com.asta.app2.Constants;
import com.asta.app2.webapp.action.BasePage;
import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.GreekList;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.RomanList;
import com.lowagie.text.Section;
import com.lowagie.text.SimpleCell;
import com.lowagie.text.SimpleTable;
import com.lowagie.text.Table;
import com.lowagie.text.ZapfDingbatsList;
import com.lowagie.text.ZapfDingbatsNumberList;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.MultiColumnText;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * This example was written by Bruno Lowagie.
 * It is part of the book 'iText in Action' by Manning Publications.
 * ISBN: 1932394796
 * http://www.1t3xt.com/docs/book.php
 * http://www.manning.com/lowagie/
 * 
 * and it has some change by [saeid3 at gmail dot com]
 */

public class ItextBean extends BasePage{

	/**
	 * Generates a PDF file with the text 'Hello World'
	 * @param args no arguments needed here 
	 */
	public String hello() {
		
		System.out.println("Chapter1: example HelloWorld");
		//step 1: creation of a document-object
		Document document = new Document();
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			//step4: we add paragraph o the document
			document.add(new Paragraph("Hello World"));
			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	
	/**
	 * Generates a PDF file with the font peace
	 * @param args no arguments needed here 
	 * @throws FactoryConfigurationError 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public String fontPeace() throws ParserConfigurationException, SAXException, FactoryConfigurationError {
		
		System.out.println("Chapter8: example font peace");
		//step 1: creation of a document-object
		Document document = new Document(PageSize.A4);
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			//step4: we add paragraph o the document
			new SayPeace(document, new InputSource(new FileInputStream("src/main/webapp/tutorial/itext/say_peace.xml")));
			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	
	/**
	 * Generates a PDF file with the font ttf
	 * @param args no arguments needed here 
	 */
	public String fontTtf() {
		
		System.out.println("Chapter8: example font ttf");
		//step 1: creation of a document-object
		Document document = new Document();
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			//step4: we add paragraph o the document
			BaseFont bf;
			Font font;
			bf = BaseFont.createFont("/com/asta/app2/fonts/nazlib.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			font = new Font(bf, 12);
			System.out.println(bf.getClass().getName());
			document.add(new Paragraph(	"This is font nazlib black italic (embedded)ضصثقفغعهخحجچگکمنتالبیسشظطزرذدپو./", font));
			bf = BaseFont.createFont("/com/asta/app2/fonts/nazlib.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			font = new Font(bf, 12);
			document.add(new Paragraph("This is font nazlib black italic (not embedded)ضصثقفغعهخحجچگکمنتالبیسشظطزرذدپو", font));
			document.add(new Paragraph("PostScript name:" + bf.getPostscriptFontName()));
			document.add(new Paragraph("Available code pages:"));
			String[] encoding = bf.getCodePagesSupported();
			for (int i = 0; i < encoding.length; i++) {
				document.add(new Paragraph("encoding[" + i + "] = " + encoding[i]));
			}
			document.newPage();
			document.add(new Paragraph("Full font names:"));
			String[][] name = bf.getFullFontName();
			for (int i = 0; i < name.length; i++) {
				document.add(new Paragraph(name[i][3] + " (" + name[i][0]
				                                                       + "; " + name[i][1] + "; " + name[i][2] + ")"));
			}
			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	
	
	/**
	 * Generates a PDF file with the font Type1 AFM
	 * @param args no arguments needed here 
	 */
	public String fontType1AFM() {
		
		System.out.println("Chapter8: example font type1 AFM");
		//step 1: creation of a document-object
		Document document = new Document();
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			//step4: we add paragraph o the document
			BaseFont bf1 = BaseFont.createFont("/com/lowagie/text/pdf/fonts/Times-Roman.afm", "",BaseFont.NOT_EMBEDDED);
			Font font1 = new Font(bf1, 12);
			document.add(new Paragraph("0123456789\nabcdefghijklmnopqrstuvwxyz\nABCDEFGHIJKLMNOPQRSTUVWXZ",font1));
			
			BaseFont bf2 = BaseFont.createFont("/com/asta/app2/tutorial/itext/putr8a.afm", "",BaseFont.NOT_EMBEDDED);
			Font font2 = new Font(bf2, 12);
			document.add(new Paragraph("0123456789\nabcdefghijklmnopqrstuvwxyz\nABCDEFGHIJKLMNOPQRSTUVWXZ",font2));
			
			BaseFont bf3 = BaseFont.createFont("/com/asta/app2/tutorial/itext/cmr10.afm", "",BaseFont.NOT_EMBEDDED);
			Font font3 = new Font(bf3, 12);
			document.add(new Paragraph("0123456789\nabcdefghijklmnopqrstuvwxyz\nABCDEFGHIJKLMNOPQRSTUVWXZ",font3));
			
			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	
	
	/**
	 * Generates a PDF file with the Font Metrics
	 * @param args no arguments needed here 
	 */
	public String fontMetrics() {
		
		System.out.println("Chapter8: example fontMetrics");
		//step 1: creation of a document-object
		Document document = new Document();
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			//step4: we add paragraph o the document
			Font font = new Font(Font.HELVETICA, 12);
			BaseFont bf = font.getCalculatedBaseFont(false);
			
			String numbers = "0123456789";
			String letters = "abcdefghijklmnopqrstuvwxyz";
			document.add(new Paragraph(numbers, font));
			document.add(new Paragraph("width: " + bf.getWidth(numbers) + " ("
					+ bf.getWidthPoint(numbers, 12) + "pt)", font));
			document.add(new Paragraph("ascent: "
					+ bf.getAscent(numbers)
					+ "; descent: "
					+ bf.getDescent(numbers)
					+ "; height: "
					+ (bf.getAscentPoint(numbers, 12)
							- bf.getDescentPoint(numbers, 12) + "pt"), font));
			document.add(new Paragraph(letters, font));
			document.add(new Paragraph("width: " + bf.getWidth(letters) + " ("
					+ bf.getWidthPoint(letters, 12) + "pt)", font));
			document.add(new Paragraph("ascent: "
					+ bf.getAscent(letters)
					+ "; descent: "
					+ bf.getDescent(letters)
					+ "; height: "
					+ (bf.getAscentPoint(letters, 12) - bf.getDescentPoint(
							letters, 12)) + "pt", font));
			
			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	
	/**
	 * Generates a PDF file with the text standardFont
	 * @param args no arguments needed here 
	 */
	public String standardFont() {
		
		System.out.println("Chapter8: example standardFont");
		//step 1: creation of a document-object
		Document document = new Document();
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			//step4: we add paragraph o the document
			// the 14 standard fonts in PDF: do not use this Font constructor!
			// this is for demonstration purposes only, use FontFactory!
			Font[] fonts = new Font[14];
			fonts[0] = new Font(Font.COURIER, Font.DEFAULTSIZE, Font.NORMAL);
			fonts[1] = new Font(Font.COURIER, Font.DEFAULTSIZE, Font.ITALIC);
			fonts[2] = new Font(Font.COURIER, Font.DEFAULTSIZE, Font.BOLD);
			fonts[3] = new Font(Font.COURIER, Font.DEFAULTSIZE, Font.BOLD | Font.ITALIC);
			fonts[4] = new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL);
			fonts[5] = new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.ITALIC);
			fonts[6] = new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
			fonts[7] = new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.BOLDITALIC);
			fonts[8] = new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.NORMAL);
			fonts[9] = new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.ITALIC);
			fonts[10] = new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD);
			fonts[11] = new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLDITALIC);
			fonts[12] = new Font(Font.SYMBOL, Font.DEFAULTSIZE);
			fonts[13] = new Font(Font.ZAPFDINGBATS, Font.DEFAULTSIZE, Font.UNDEFINED, new Color(0xFF, 0x00, 0x00));
			// add the content
			for (int i = 0; i < 14; i++) {
				document.add(new Paragraph("quick brown fox jumps over the lazy dog", fonts[i]));
			}
			
			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	
	/**
	 * Generates a PDF file with the custom column
	 * @param args no arguments needed here 
	 */
	public String customColumn() {
		
		System.out.println("Chapter7: example Custom Column");
		//step 1: creation of a document-object
		Document document = new Document();
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");

			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			//step4: we add paragraph o the document
			MultiColumnText mct = new MultiColumnText();
            mct.addSimpleColumn(100, 280);
            mct.addSimpleColumn(300, 480);
            // Write some iText poems
            for (int i = 0; i < 30; i++) {
            	mct.addElement(new Paragraph(String.valueOf(i + 1)));
                mct.addElement(newParagraph(randomWord(noun), Element.ALIGN_CENTER, Font.BOLDITALIC));
                for (int j = 0; j < 4; j++) {
                    mct.addElement(newParagraph(poemLine(), Element.ALIGN_LEFT, Font.NORMAL));
                }
                mct.addElement(newParagraph(randomWord(adverb), Element.ALIGN_LEFT, Font.NORMAL));
                mct.addElement(newParagraph("\n\n", Element.ALIGN_LEFT, Font.NORMAL));
            }
            document.add(mct);
            document.close();


			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	 
	/**
	 * Generates a PDF file with the multi column
	 * @param args no arguments needed here 
	 */
	public String column() {
		
		System.out.println("Chapter7: example Multi Column");
		//step 1: creation of a document-object
		Document document = new Document();
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			//step4: we add paragraph o the document
			MultiColumnText mct = new MultiColumnText();
			mct.setColumnsRightToLeft(true);
			mct.addRegularColumns(document.left(), document.right(), 10f, 3);
			// Write some iText poems
			for (int i = 0; i < 30; i++) {
				mct.addElement(new Paragraph(String.valueOf(i + 1)));
				mct.addElement(newParagraph(randomWord(noun), Element.ALIGN_CENTER, Font.BOLDITALIC));
				for (int j = 0; j < 4; j++) {
					mct.addElement(newParagraph(poemLine(), Element.ALIGN_LEFT, Font.NORMAL));
				}
				mct.addElement(newParagraph(randomWord(adverb), Element.ALIGN_LEFT, Font.NORMAL));
				mct.addElement(newParagraph("\n\n", Element.ALIGN_LEFT, Font.NORMAL));
			}
			document.add(mct);
			document.close();
			
			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	
	/** Generates a new Paragraph. */
    private static Element newParagraph(String text, int alignment, int type) {
        Font font = FontFactory.getFont(BaseFont.HELVETICA, 10, type);
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(alignment);
        p.setLeading(font.getSize() * 1.2f);
        return p;
     }
 
     static Random rand = new Random();
     static String[] verb = {"flows", "draws", "renders", "throws exception", "runs",
         "crashes", "downloads", "usurps", "vexes", "whispers", "boils",
         "capitulates", "crashes", "craves", "looks", "defies", "defers",
         "defines", "envelops", "entombs", "falls", "fails", "halts",
         "appears", "nags", "overflows", "burns", "dies", "writes",
         "flushes"};
     static String[] noun = {"ColumnText", "paragraph", "phrase", "chunk", "PdfContentByte",
         "PdfPTable", "iText", "color", "vertical alignment", "horizontal alignment", "PdfWriter",
         "ListItem", "PdfStamper", "PDF", "HTML", "XML", "column", "font",
         "table", "FDF", "field", "NullPointerException", "CJK font"};
     static String[] adjective = {"foul", "broken", "gray", "slow", "beautiful",
        "throbbing", "sharp", "stout", "soundless", "neat",
        "swift", "uniform", "upright", "vibrant", "dingy",
        "vestigal", "messy", "sloppy", "baleful", "boastful",
        "dark", "capricious", "concrete", "deliberate", "sharp",
         "drunken", "undisciplined", "perfect", "bloated"};
     static String[] adverb = {"randomly", "quickly", "triumphantly", "suggestively",
        "slowly", "angrily", "uncomfortably", "finally", "unexpectedly",
        "hysterically", "thinly", "dryly", "blazingly",
        "terribly", "bleakly", "irritably", "dazzlingly", "expectantly",
        "impersonally", "abruptly", "awfully", "caressingly", "completely",
        "undesirably", "drolly", "hypocritically", "blankly",
        "dimly"};
 
     /** Returns a random word from an array. */
     private static String randomWord(String[] type)
     {
        return type[rand.nextInt(type.length)];
     }
 
     /**
      * Generates a random poem line.
      * @return a poem that is generated with some keywords.
      */
     public static String poemLine()
     {
        StringBuffer results = new StringBuffer(150);
        results.append(randomWord(adjective));
        results.append(" ");
        results.append(randomWord(noun));
        results.append(" ");
        results.append(randomWord(verb));
        results.append(" ");
        results.append(randomWord(adverb));
        results.append(", ");
        return results.toString();
  }


	/**
	 * Generates a PDF file with the PdfpTable
	 * @param args no arguments needed here 
	 */
	public String pdfptable() {
		
		System.out.println("Chapter6: example PdfpTable");
		//step 1: creation of a document-object
		Document document = new Document();
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			
			
			//step4: we add paragraph o the document
			PdfPTable table = new PdfPTable(4);
			PdfPTable nested1 = new PdfPTable(2);
			nested1.addCell("1.1");
			nested1.addCell("1.2");
			PdfPTable nested2 = new PdfPTable(1);
			nested2.addCell("20.1");
			nested2.addCell("20.2");
			for (int k = 0; k < 24; ++k) {
				if (k == 1) {
					table.addCell(nested1);
				} else if (k == 20) {
					table.addCell(new PdfPCell(nested2));
				} else {
					table.addCell("cell " + k);
				}
			}
			document.add(table);


			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	
	/**
	 * Generates a PDF file with the table
	 * @param args no arguments needed here 
	 */
	public String table() {
		
		System.out.println("Chapter6: example table");
		//step 1: creation of a document-object
		Document document = new Document();
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			
			//step4: we add paragraph o the document
			Table table = new Table(3);
			table.setBorderWidth(1);
			table.setBorderColor(new Color(0, 0, 255));
			table.setPadding(5);
			table.setSpacing(5);
			Cell cell = new Cell("header");
			cell.setHeader(true);
			cell.setColspan(3);
			table.addCell(cell);
			cell = new Cell("example cell with colspan 1 and rowspan 2");
			cell.setRowspan(2);
			cell.setBorderColor(new Color(255, 0, 0));
			table.addCell(cell);
			table.addCell("1.1");
			table.addCell("2.1");
			table.addCell("1.2");
			table.addCell("2.2");
			table.addCell("cell test1");
			cell = new Cell("big cell");
			cell.setRowspan(2);
			cell.setColspan(2);
			cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			table.addCell(cell);
			table.addCell("cell test2");
			document.add(table);

			
			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	
	/**
	 * Generates a PDF file with the simple table
	 * @param args no arguments needed here 
	 * @throws SAXException 
	 * @throws FactoryConfigurationError 
	 * @throws ParserConfigurationException 
	 */
	public String simpleTable() throws ParserConfigurationException, FactoryConfigurationError, SAXException {
		
		System.out.println("Chapter6: example simpleTable");
		//step 1: creation of a document-object
		Document document = new Document(PageSize.A4.rotate());
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			HtmlWriter.getInstance(document, getResponse().getOutputStream());
			document.open();
			Paragraph p = new Paragraph("Academic Year 2006-2007\n\n");
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			document.add(new FoobarStudyProgram("src/main/webapp/tutorial/itext/studyprogram.xml").getTable());
			p = new Paragraph("Sem.: 1 = first semester, 2 = second semester, Y = annual course");
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			p = new Paragraph(
					"P-T = courses can be taken on a part-time basis, 1 = first part, 2 = second part");
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			document.close();

			
			
			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	/**
	 * Creates a new FoobarHtmlHandler.
	 * 
	 * @param html
	 * @throws DocumentException
	 * @throws FactoryConfigurationError
	 * @throws ParserConfigurationException
	 * @throws FileNotFoundException
	 * @throws SAXException
	 * @throws IOException
	 */
	


	

	/**
	 * Generates a PDF file with the text 'Quick brown fox jumps over the lazy dog'
	 * 
	 * @param args no arguments needed here
	 */
	public String foxDog() {
		
		System.out.println("Chapter4: example FoxDog!");
		//step 1: creation of a document-object
		Document document = new Document();
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			
			
			//step4: we add paragraph o the document
			Image img = Image.getInstance("src/main/webapp/tutorial/itext/foxdog.jpg");
			img.setAbsolutePosition(50, 600);
			document.add(img);
			Phrase p = new Phrase("Quick brown fox jumps over the lazy dog. ");
			for (int i = 0; i < 80; i++) {
				document.add(p);
			}
			img.setAbsolutePosition(50, 300);
			document.add(img);



			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}

	/**
	 * Generates a PDF file with bookmarks.
	 * @param filename the filename of the PDF file.
	 */

	public String helloBurst() {
		
		System.out.println("Chapter1: example HelloBurst");
		//step 1: creation of a document-object
		Document document = new Document();
		try{
			getResponse().setContentType("application/pdf");
			getResponse().addHeader("Content-Disposition", "attachment; filename=preview.pdf");
			
			//step2: we create writer
			PdfWriter.getInstance(document, getResponse().getOutputStream());
			//step3: we open the document
			document.open();
			//step4: we add paragraph o the document
			Paragraph hello = new Paragraph("(English:) hello, " +
					"(Esperanto:) he, alo, saluton, (Latin:) heu, ave, " +
					"(French:) all\u00f4, (Italian:) ciao, (German:) hallo, he, heda, holla, " +
					"(Portuguese:) al\u00f4, ol\u00e1, hei, psiu, bom d\u00eda, (Dutch:) hallo, dag, " +
					"(Spanish:) ola, eh, (Catalan:) au, bah, eh, ep, " +
					"(Swedish:) hej, hejsan(Danish:) hallo, dav, davs, goddag, hej, " +
					"(Norwegian:) hei; morn, (Papiamento:) halo; hallo; k\u00ed tal, " +
					"(Faeroese:) hall\u00f3, hoyr, (Turkish:) alo, merhaba, (Albanian:) tungjatjeta");
			Chapter universe = new Chapter("To the Universe:", 1);
			Section section;
			section = universe.addSection("to the World:");
			section.add(hello);
			section = universe.addSection("to the Sun:");
			section.add(hello);
			section = universe.addSection("to the Moon:");
			section.add(hello);
			section = universe.addSection("to the Stars:");
			section.add(hello);
			document.add(universe);
			Chapter people = new Chapter("To the People:", 2);
			section = people.addSection("to mothers and fathers:");
			section.add(hello);
			section = people.addSection("to brothers and sisters:");
			section.add(hello);
			section = people.addSection("to wives and husbands:");
			section.add(hello);
			section = people.addSection("to sons and daughters:");
			section.add(hello);
			section = people.addSection("to complete strangers:");
			section.add(hello);
			document.add(people);
			document.setPageSize(PageSize.A4.rotate());
			Chapter animals = new Chapter("To the Animals:", 3);
			section = animals.addSection("to cats and dogs:");
			section.add(hello);
			section = animals.addSection("to birds and bees:");
			section.add(hello);
			section = animals.addSection("to farm animals and wild animals:");
			section.add(hello);
			section = animals.addSection("to bugs and beatles:");
			section.add(hello);
			section = animals.addSection("to fish and shellfish:");
			section.add(hello);
			document.add(animals);

			
			getFacesContext().getApplication().getStateManager().saveView(getFacesContext());
			getFacesContext().responseComplete();
		}catch(DocumentException de){
			System.err.println(de.getMessage());
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		//step5: we close the document
		document.close();
		
		return Constants.NO_WHERE;
	}
	
	
	
}
