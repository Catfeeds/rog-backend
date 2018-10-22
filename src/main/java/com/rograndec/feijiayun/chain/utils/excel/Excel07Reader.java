 /* ====================================================================
    Licensed to the Apache Software Foundation (ASF) under one or more
    contributor license agreements.  See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    The ASF licenses this file to You under the Apache License, Version 2.0
    (the "License"); you may not use this file except in compliance with
    the License.  You may obtain a copy of the License at
 
        http://www.apache.org/licenses/LICENSE-2.0
 
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 ==================================================================== */

 package com.rograndec.feijiayun.chain.utils.excel;

 import com.rograndec.feijiayun.chain.business.system.opening.excel.OpeningIRowReader;
 import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
 import org.apache.poi.openxml4j.opc.OPCPackage;
 import org.apache.poi.openxml4j.opc.PackageAccess;
 import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
 import org.apache.poi.xssf.eventusermodel.XSSFReader;
 import org.apache.poi.xssf.model.StylesTable;
 import org.xml.sax.ContentHandler;
 import org.xml.sax.InputSource;
 import org.xml.sax.SAXException;
 import org.xml.sax.XMLReader;

 import javax.xml.parsers.ParserConfigurationException;
 import javax.xml.parsers.SAXParser;
 import javax.xml.parsers.SAXParserFactory;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.PrintStream;

 /**
  * A rudimentary XLSX -> CSV processor modeled on the
  * POI sample program XLS2CSVmra by Nick Burch from the
  * package org.apache.poi.hssf.eventusermodel.examples.
  * Unlike the HSSF version, this one completely ignores
  * missing rows.
  * <p/>
  * Data sheets are read using a SAX parser to keep the
  * memory footprint relatively small, so this should be
  * able to read enormous workbooks.  The styles table and
  * the shared-string table must be kept in memory.  The
  * standard POI styles table class is used, but a custom
  * (read-only) class is used for the shared string table
  * because the standard POI SharedStringsTable grows very
  * quickly with the number of unique strings.
  * <p/>
  * Thanks to Eric Smith for a patch that fixes a problem
  * triggered by cells with multiple "t" elements, which is
  * how Excel represents different formats (e.g., one word
  * plain and one word bold).
  *
  * @author Chris Lott
  */
 public class Excel07Reader {
 
     /**
      * The type of the data value is indicated by an attribute on the cell.
      * The value is usually in a "v" element within the cell.
      */
     enum xssfDataType {
         BOOL,
         ERROR,
         FORMULA,
         INLINESTR,
         SSTINDEX,
         NUMBER,
     }
 


     ///////////////////////////////////////
 
     private OPCPackage xlsxPackage;
     private int minColumns;
     private PrintStream output;
 
     // 当前行
      private int curRow = 0;
 
      private int sheetIndex = 0;
 
      private IRowReader rowReader;
 
      public void setRowReader(IRowReader rowReader) {
          this.rowReader = rowReader;
      }


 
     /**
      * Creates a new XLSX -> CSV converter
      *
      * @param pkg        The XLSX package to process
      * @param output     The PrintStream to output the CSV to
      * @param minColumns The minimum number of columns to output, or -1 for no minimum
      * @param rowReader
      */
     public Excel07Reader(OPCPackage pkg, PrintStream output, int minColumns, IRowReader rowReader) {
         this.xlsxPackage = pkg;
         this.output = output;
         this.minColumns = minColumns;
         this.rowReader = rowReader;
     }
 
     /**
      * Parses and shows the content of one sheet
      * using the specified styles and shared-strings tables.
      *
      * @param styles
      * @param strings
      * @param sheetInputStream
      */
     public void processSheet(
             StylesTable styles,
             ReadOnlySharedStringsTable strings,
             InputStream sheetInputStream,
             IRowReader rowReader,
             Integer curRow,
             Integer sheetIndex)
             throws IOException, ParserConfigurationException, SAXException {

         InputSource sheetSource = new InputSource(sheetInputStream);
         SAXParserFactory saxFactory = SAXParserFactory.newInstance();
         SAXParser saxParser = saxFactory.newSAXParser();
         XMLReader sheetParser = saxParser.getXMLReader();
         ContentHandler handler = new XSSFSheetHandler(styles, strings, this.minColumns, this.output,rowReader,curRow,sheetIndex);
         sheetParser.setContentHandler(handler);
         sheetParser.parse(sheetSource);
     }
 
     /**
      * Initiates the processing of the XLS workbook file to CSV.
      *
      * @throws IOException
      * @throws OpenXML4JException
      * @throws ParserConfigurationException
      * @throws SAXException
      */
     public void process() throws Exception {
         InputStream stream = null ;
         try {
             ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
             XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
             StylesTable styles = xssfReader.getStylesTable();
             //sheet
             XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
             while (iter.hasNext()) {
                 if (sheetIndex == 0) {
                     curRow = 0;
                     sheetIndex++;
                     stream = iter.next();
                     processSheet(styles, strings, stream,rowReader,curRow,sheetIndex);
                     stream.close();
                 }
                 break;


             }
         } catch (IllegalArgumentException e) {
             e.printStackTrace();
             throw new IllegalArgumentException(e.getMessage());
         } catch (Exception e) {
             throw e;
         }finally{
             if(null != stream){
                 stream.close();
             }
         }
     }
 
     public static void main(String[] args) throws Exception {
 
//         File xlsxFile = new File("D:\\33221.xlsx");
         File xlsxFile = new File("D:\\excel\\4.xlsx");
         if (!xlsxFile.exists()) {
             System.err.println("Not found or not a file: " + xlsxFile.getPath());
             return;
         }
         int minColumns = 6 ;
         // The package open is instantaneous, as it should be.
         OPCPackage p = OPCPackage.open(xlsxFile.getPath(), PackageAccess.READ);
         IRowReader reader = new OpeningIRowReader();
         Excel07Reader excel07Reader = new Excel07Reader(p, System.out, minColumns ,reader);
         excel07Reader.process();
     }
 
 }