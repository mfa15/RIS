/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.printing;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//package org.apache.pdfbox.examples.printing;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.Sides;
import static javax.swing.text.StyleConstants.Orientation;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

/**
 * Examples of various different ways to print PDFs using PDFBox.
 */
public final class Printing {

    private Printing() {
    }

    public static void main(String args[]) throws PrinterException, IOException {
        args = new String[1];
        args[0] = "d:\\1.pdf";
        if (args.length != 1) {
            System.err.println("usage: java " + Printing.class.getName() + " <input>");
            System.exit(1);
        }

        String filename = args[0];
        try (PDDocument document = PDDocument.load(new File(filename))) {
          //  customPrint(document);
            // choose your printing method:
            print(document);
//            printWithAttributes(document);
//            printWithDialog(document);
            //printWithDialogAndAttributes(document);
//            printWithPaper(document);
        }
    }

    /**
     * Prints the document at its actual size. This is the recommended way to
     * print.
     */
    private static void print(PDDocument document) throws IOException, PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        PDFPrintable doc=new PDFPrintable(document,Scaling.SCALE_TO_FIT);
                
//        job.setPageable(new PDFPageable(document));
//        job.print();
        PrintRequestAttributeSet attr_set = new HashPrintRequestAttributeSet();
        attr_set.add(MediaSizeName.NA_LETTER);
        attr_set.add(Sides.ONE_SIDED);
        job.setPrintable(doc);
        Copies c = new Copies(1);
        attr_set.add(c);
        job.print(attr_set);
    }

    /**
     * Prints using custom PrintRequestAttribute values.
     */
    private static void printWithAttributes(PDDocument document)
            throws IOException, PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));

        PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
        attr.add(new PageRanges(1, 1)); // pages 1 to 1
        PrintRequestAttributeSet attr_set = new HashPrintRequestAttributeSet();
        attr_set.add(MediaSizeName.ISO_A4);
        attr_set.add(Sides.ONE_SIDED);
        job.print(attr_set);
    }

    /**
     * Prints with a print preview dialog.
     */
    private static void printWithDialog(PDDocument document) throws IOException, PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));

        if (job.printDialog()) {
            job.print();
        }
    }

    /**
     * Prints with a print preview dialog and custom PrintRequestAttribute
     * values.
     */
    private static void printWithDialogAndAttributes(PDDocument document)
            throws IOException, PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));

        PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
        attr.add(new PageRanges(1, 1)); // pages 1 to 1

        PDViewerPreferences vp = document.getDocumentCatalog().getViewerPreferences();
        if (vp != null && vp.getDuplex() != null) {
            String dp = vp.getDuplex();
            if (PDViewerPreferences.DUPLEX.DuplexFlipLongEdge.toString().equals(dp)) {
                attr.add(Sides.TWO_SIDED_LONG_EDGE);
            } else if (PDViewerPreferences.DUPLEX.DuplexFlipShortEdge.toString().equals(dp)) {
                attr.add(Sides.TWO_SIDED_SHORT_EDGE);
            } else if (PDViewerPreferences.DUPLEX.Simplex.toString().equals(dp)) {
                attr.add(Sides.ONE_SIDED);
            }
        }

        if (job.printDialog(attr)) {
            job.print(attr);
        }
    }

    /**
     * Prints using a custom page size and custom margins.
     */
    private static void printWithPaper(PDDocument document)
            throws IOException, PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));

        // define custom paper
        Paper paper = new Paper();
        paper.setSize(306, 396); // 1/72 inch
        paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight()); // no margins

        //PrinterJob job = PrinterJob.getPrinterJob();
//        paper = new Paper();
//        paper.setSize(72d * 8.267d, 72d * 11.692d);
//        paper.setImageableArea(0, 0, 72d * 8.267d, 72d * 11.692d);

        PrintRequestAttributeSet attr_set = new HashPrintRequestAttributeSet();
        attr_set.add(MediaSizeName.ISO_A4);
        attr_set.add(Sides.ONE_SIDED);
        
        // custom page format
        PageFormat pageFormat = new PageFormat();
        pageFormat.setPaper(paper);
        

        // override the page format
        Book book = new Book();
        // append all pages
        book.append(new PDFPrintable(document), pageFormat, document.getNumberOfPages());
        job.setPageable(book);

        job.print();
    }
}
