/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.reporting;

import app.ris.bo.Constants;
import app.ris.bo.LocationBO;
import app.ris.bo.OrderReportBO;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.exp;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


public class ImagesReceivedDetail {
    
    SimpleDateFormat simpledatafo = new SimpleDateFormat("dd MMMMMMMMMMM yyyy - HH:mm a");
    String date= simpledatafo.format(new Date());
    
    StyleBuilder bgStyle = stl.style().setRotation(Rotation.NONE).setFontSize(80).setPadding(30).setForegroundColor(Color.LIGHT_GRAY);
    FileOutputStream fs = null;
//    String profilePath = System.getProperty("java.io.tmpdir")+"\\get\\pro.png";
	String value="\n",from,to;
	LocationBO lbo = null;
        LinkedHashMap <String,OrderReportBO> orderReportMap = new  LinkedHashMap<>();
	private void build(File f) {
            
        try {
            fs = new FileOutputStream(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImagesReceivedDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
		StyleBuilder boldStyle         = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
		                                    .setBorder(stl.pen1Point())
		                                    .setBackgroundColor(Color.LIGHT_GRAY);
		StyleBuilder titleStyle        = stl.style(boldCenteredStyle)
		                                    .setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
		                                    .setFontSize(12);
                StyleBuilder fontSize = stl.style().setFontSize(8);
                StyleBuilder fontSize11 = stl.style().setFontSize(11);
                StyleBuilder headingStyle = stl.style().bold().setFontSize(13);
		
                
                TextColumnBuilder<String>     itemColumn      = col.column("Item",       "item",      type.stringType()).setStyle(headingStyle);
		TextColumnBuilder<String>    q1  = col.column("",   "1",  type.stringType()).setStyle(fontSize);
		TextColumnBuilder<String>    q2  = col.column("",   "2",  type.stringType()).setStyle(fontSize);
		TextColumnBuilder<String>    q3 = col.column("",   "3",  type.stringType()).setStyle(fontSize);
		TextColumnBuilder<String>    q4  = col.column("",   "4",  type.stringType()).setStyle(fontSize);
		TextColumnBuilder<String>    q5  = col.column("",   "5",  type.stringType()).setStyle(fontSize);
//		TextColumnBuilder<String>    q6  = col.column("",   "6",  type.stringType()).setStyle(fontSize);
//                TextColumnBuilder<String>    quantityColumn2  = col.column("",   "quantity2",  type.stringType()).setStyle(fontSize);
		ColumnGroupBuilder itemGroup = grp.group(itemColumn);
		itemGroup.setPrintSubtotalsWhenExpression(exp.printWhenGroupHasMoreThanOneRow(itemGroup));
		try {
                    report().setBackgroundStyle(bgStyle)
//                            .addBackground(textField)
                            
                            .setColumnTitleStyle(columnTitleStyle)
                            
                            .setSubtotalStyle(boldStyle)
//                            .highlightDetailEvenRows()
                            
                            .columns(//add columns
                                    /*rowNumberColumn,*/ /*itemColumn,*/ q1,q2,q3,q4,q5/*, unitPriceColumn, priceColumn, pricePercColumn*/)
                            .columnGrid(
                                    /*rowNumberColumn,*/ q1,q2,q3,q4,q5, /*unitPriceColumn,*/ grid.verticalColumnGridList(/*priceColumn, pricePercColumn*/))
                            .groupBy(itemGroup)
                            .setShowColumnTitle(false)
//			  .subtotalsAtSummary(
//			  	sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
//			  .subtotalsAtFirstGroupFooter(
//			  	sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
                            .title(//shows report title
                                    cmp.horizontalList()
                                            .add(
                                                    cmp.image(Constants.logoPath).setFixedDimension(140, 70).setHorizontalImageAlignment(HorizontalImageAlignment.LEFT),
//                                                        cmp.image(Constants.addressPath).setHorizontalImageAlignment(HorizontalImageAlignment.LEFT),
//			  			cmp.text("Organization Name").setStyle(titleStyle).setHorizontalAlignment(HorizontalAlignment.LEFT),
//                                                cmp.text("abc").setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
                                                    cmp.verticalList(
                                                    cmp.text("CT SCAN CENTRE").setStyle(titleStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                                    cmp.text("GOVERNMENT DHQ HOSPITAL, "+lbo.getName()+"").setStyle(headingStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                                    cmp.text( "A landmark initiative of Government of the Punjab in collaboration with").setStyle(fontSize11).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                                    cmp.text("EASTERN MEDICAL TECHNOLOGY SERVICES").setStyle(headingStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                                                    ),
//                                                        cmp.image(profilePath).setFixedDimension(80, 60).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT)
                                                    cmp.image(Constants.logoGov).setFixedDimension(140, 70).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT)
                                            )
                                            .newRow()
                                            .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10))
                            )
                            
                            
                            .pageFooter(cmp.line(),cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
                            .summary(
                                    cmp.line(),
                                    cmp.horizontalList(cmp.text("Terminal: "+Constants.terminalName),
                                           cmp.text("Printed by (Employee ID) : "+Constants.employeeID),
                                           cmp.text("Date\\Time : "+date)),
                                    cmp.line()
                            )
//                                .columnFooter(cmp.image("C:\\Users\\faizanahmed\\Pictures\\Saved Pictures\\1.png").setHorizontalImageAlignment(HorizontalImageAlignment.LEFT))
                            .setDataSource(createDataSource())
                            .toPdf(fs);
            try {
                fs.close();
            } catch (IOException ex) {
                Logger.getLogger(ImagesReceivedDetail.class.getName()).log(Level.SEVERE, null, ex);
            }
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private JRDataSource createDataSource() {
            
            
            //father name
            //cnic
            //contact number
            //gender
            //address + city
            
            // other mrno
            
		DRDataSource dataSource = new DRDataSource("item", "1","2","3","4","5");
//                dataSource.add("Patient Information","Patient ID:\n",patientInformation[0],"Body Part:",patientInformation[4]);
		dataSource.add("Duration", "From:" , from);
                        dataSource.add("Duration","To:",to);
                  


//                dataSource.add("Patient Information", "1aaaaaaaaaaaaaaaaaaaaaaaaaa\t\taaasdasaa");
//		dataSource.add("DVD", 5, new BigDecimal(30));
                dataSource.add("Tech Acknowledge Queue Details", value
                );
                    Iterator<String> iter2 = orderReportMap.keySet().iterator();
                    while(iter2.hasNext())
                    {
                        
                        OrderReportBO var= orderReportMap.get(iter2.next());
                        if(!value.equalsIgnoreCase(var.getModality()))
                        {
                            dataSource.add(var.getModality(),"Patient ID","Order ID","Name","Date and Time","CPT Description\n"); 
                            value = var.getModality();
                        }
                        
                            dataSource.add(var.getModality(),var.getPateitnLocationId()+"-"+var.getPatientId(),var.getId(),
                                    var.getName(),var.getDate() ,var.getCptDesc());
//                            iter.remove();
                        }
                    
//                }
//		dataSource.add("Order Details",patientInformation[5] /*patientInformation[8]+" - "+patientInformation[4]+" "+patientInformation[5]*/);
//		dataSource.add("DVD", 5, new BigDecimal(32));
//		dataSource.add("Book", 3, new BigDecimal(11));
//		dataSource.add("Book", 1, new BigDecimal(15));
//		dataSource.add("History:",patientInformation[6]);
//		dataSource.add("Book", 8, new BigDecimal(9));
		return dataSource;
	}
	
//        public ComponentBuilders makeTitle()
//        {
//            ComponentBuilders a = new ComponentBuilders();
//            
//            a.text("abcx").setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
//            a.text("asdasdadadas").setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
//            return a;
//        }
        
//	public static void main(String[] args) {
//		new orderGenerationReport();
//	}

    public String generateOrder(LinkedHashMap <String,Integer> modalityMap,LinkedHashMap <String,OrderReportBO> map ,String from,String to,String locationId) {
        String filePath="";
        File f = new File(System.getProperty("java.io.tmpdir")+"\\temp\\StudiesReceivedDetail"+System.currentTimeMillis() % 1000+".pdf");
        this.orderReportMap = map;
        lbo = Constants.locationMap.get(locationId);
        this.from = from;
        this.to = to;
        for(String key: modalityMap.keySet())
        {
            int count = modalityMap.get(key);
            value = value +  key + " : "+ count + "\n";
        }
        
        f.getParentFile().mkdirs(); 
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ImagesReceivedDetail.class.getName()).log(Level.SEVERE, null, ex);
            }
        
                build(f);
                filePath = f.getAbsolutePath();
        return filePath;
    }
}
