/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.reporting;

import app.ris.bo.Constants;
import app.ris.bo.LocationBO;
import app.ris.bo.PatientBO;
import app.ris.bo.PatientReportBO;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.Markup;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


public class patientReport {
    
    StyleBuilder bgStyle = stl.style().setRotation(Rotation.NONE).setFontSize(80).setPadding(30).setForegroundColor(Color.LIGHT_GRAY);
    StyleBuilder headingStyle = stl.style().bold() .setRotation(Rotation.NONE).setFontSize(15).setForegroundColor(Color.BLACK);
    TextFieldBuilder<String> textField = cmp.text("\n\n\n").setStyle(bgStyle);
    PatientBO pbo=null;
    PatientReportBO prbo=null;
    LocationBO lbo = null;
    SimpleDateFormat simpledatafo = new SimpleDateFormat("dd MMMMMMMMMMM yyyy - HH:mm a");
    String patientInformation[] = new String[10];
    FileOutputStream fs = null;
    

	private void build(File f) {
        
        
        
        String date= simpledatafo.format(new Date());
        
        
            
        try {
            fs = new FileOutputStream(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(orderGenerationReport.class.getName()).log(Level.SEVERE, null, ex);
        }
		StyleBuilder boldStyle         = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
		                                    .setBorder(stl.pen1Point())
		                                    .setBackgroundColor(Color.LIGHT_GRAY);
		StyleBuilder titleStyle        = stl.style(boldCenteredStyle)
		                                    .setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
		                                    .setFontSize(12);
                StyleBuilder font = stl.style().setFontSize(12).setMarkup(Markup.NONE);
		StyleBuilder fontSize = stl.style().setFontSize(12).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT);
                StyleBuilder fontSize2 = stl.style().setFontSize(12).setHorizontalImageAlignment(HorizontalImageAlignment.LEFT);
                StyleBuilder headingStyle = stl.style().bold().setFontSize(13);
                StyleBuilder fontSize11 = stl.style().setFontSize(11);
                
		//                                                           title,     field name     data type
		TextColumnBuilder<String>     itemColumn      = col.column("Item",       "item",      type.stringType()).setStyle(headingStyle);
		TextColumnBuilder<String>    quantityColumn  = col.column("Patient Scan Report",   "quantity",  type.stringType()).setStyle(font);

		ColumnGroupBuilder itemGroup = grp.group(itemColumn);
		itemGroup.setPrintSubtotalsWhenExpression(exp.printWhenGroupHasMoreThanOneRow(itemGroup));
                String gender = pbo.getGender().equalsIgnoreCase("M")?"Male":"Female";
		try {
                    report().setBackgroundStyle(bgStyle).addBackground(textField)
                            
//create new report design
                            .setColumnTitleStyle(columnTitleStyle)
                            
                            .setSubtotalStyle(boldStyle)
                            
                            .columns(//add columns
                                    /*rowNumberColumn,*/ /*itemColumn,*/ quantityColumn/*, unitPriceColumn, priceColumn, pricePercColumn*/)
                            .columnGrid(
                                    /*rowNumberColumn,*/ quantityColumn, /*unitPriceColumn,*/ grid.verticalColumnGridList(/*priceColumn, pricePercColumn*/))
                            .groupBy(itemGroup)
                            .setShowColumnTitle(false)
//			  .subtotalsAtSummary(
//			  	sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
//			  .subtotalsAtFirstGroupFooter(
//			  	sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
                            .pageHeader(//shows report title
                                    cmp.horizontalList()
                                            .add(
//                                                    cmp.image(Constants.logoPath).setFixedDimension(140, 70).setHorizontalImageAlignment(HorizontalImageAlignment.LEFT),
////                                                        cmp.image("C:\\Users\\faizanahmed\\Pictures\\Camera Roll\\address.png").setHorizontalImageAlignment(HorizontalImageAlignment.LEFT),
////			  			cmp.text("Organization Name").setStyle(titleStyle).setHorizontalAlignment(HorizontalAlignment.LEFT),
////                                                cmp.text("abc").setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
//                                                    cmp.text("Department of Diagnostic Imaging and Radiology\nMayo Surgical Tower").setStyle(titleStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
//                                                    cmp.image(Constants.logoGov).setFixedDimension(140, 70).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT)
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
                                                    
                                                        
                                                    cmp.image(Constants.logoGov).setFixedDimension(140, 70).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT)
                                            )
                                            .newRow()
                                            .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(0))
                            
                                            .newRow()
                                            .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10))
                                            
                                            .newRow()
                                            .add(cmp.text("Patient Information:\n").setStyle(headingStyle))
                                            
                                            
                                            .newRow()
                                            .add(cmp.text(
                                               "Patient ID:\t\t"+pbo.getLocation_id()+"-"+pbo.getPatientId()
                                            +"\nName:\t\t\t"+pbo.getName()
                                            +"\nFather Name:\t\t"+pbo.getFatherName()
                                            +"\nCNIC:\t\t\t"+pbo.getCnic()
                                            +"\nContact Number:\t"+pbo.getContactNum()
                                            ).setStyle(fontSize2))
                                            
                                            .add(cmp.text(
                                            "Gender:\t\t"+gender
                                            
                                            +"\nCity:\t\t\t"+pbo.getCity()
                                            +"\nHeight:\t\t\t"+pbo.getHeight()
                                            +"  Feet\nWeight:\t\t"+pbo.getWeight()
                                            +"  Kgs\nAge:\t\t\t"+pbo.getAge()
                                            ).setStyle(fontSize))
                                            
                                            .add(cmp.image(Constants.profilePath).setFixedDimension(70, 30).setHorizontalImageAlignment(HorizontalImageAlignment.LEFT))
                                            
                                            .newRow()
                                            .add(cmp.text("Address:\t\t"+pbo.getAddress()).setStyle(fontSize2))
                                                    .newRow().add(cmp.text("Printing Date:\t\t"+date).setStyle(fontSize2))
                                            
                                            .newRow().add(cmp.text(""))
                            
                            .newRow().add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10))
                                    
                            .newRow().add(cmp.text(patientInformation[9]+" - "+patientInformation[8]).setStyle(titleStyle).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT))
                                  .newRow().add(cmp.text(""))  
                                    
                            )
                            .pageFooter(
                                    
                                    cmp.line(),cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
                            .summary(
                                    
                                    cmp.line(),
                                    cmp.text("Electronically Signed and Verfied by "+prbo.getTitle1()+", "+ prbo.getName()+" ( "+prbo.getDegrees()+" )"))
//                                .columnFooter(cmp.image("C:\\Users\\faizanahmed\\Pictures\\Saved Pictures\\1.png").setHorizontalImageAlignment(HorizontalImageAlignment.LEFT))
                            .setDataSource(createDataSource())
                            
                            .toPdf(fs);
            try {
                fs.close();
            } catch (IOException ex) {
                Logger.getLogger(patientReport.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private JRDataSource createDataSource() {
        
            DRDataSource dataSource = new DRDataSource("item", "quantity");
            
            
            String startDateString = prbo.getReportDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
            Date startDate=null;
            try {
                startDate = df.parse(startDateString);
//                String newDateString = df.format(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            
            dataSource.add("History: ", prbo.getHistory()+"\n");
            dataSource.add("Result ("+simpledatafo.format(startDate) +"):  ",prbo.getResult()+"\n");
            dataSource.add("Impression ("+simpledatafo.format(startDate)+"): ",prbo.getImpression()+"\n");
            
            if(prbo.getAddendum().size()>0)
            {
                for(int i=0;i<prbo.getAddendum().size();i++)
                {
                    String tempAdd[] = prbo.getAddendum().get(String.valueOf(i)).split("####");
                    
                    String adate = tempAdd[1] + " " + tempAdd[0];
                    startDate=null;
                    try {
                        startDate = df.parse(adate);
//                        String newDateString = df.format(startDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    dataSource.add("Addendum ("+(i+1)+") dated "+simpledatafo.format(startDate),tempAdd[0]);
                }
            }
            

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

//    public String generateOrder(HashMap<String, String> hm) {
//        String filePath="";
//        employee = hm;
//        File f = new File(System.getProperty("user.dir")+"\\temp\\patientReport.pdf");
//        
//        f.getParentFile().mkdirs(); 
//            try {
//                f.createNewFile();
//            } catch (IOException ex) {
//                Logger.getLogger(patientReport.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        if( f.exists() )
//        {
//            build(f);
//            filePath = System.getProperty("user.dir")+"\\temp\\patientReport.pdf";
//        }
//        return filePath;
//    }

    public String generateOrder(String[] pi,PatientBO bo, PatientReportBO prbo) {
        String filePath="";
        pbo = bo;
        this.prbo = prbo;
        lbo = Constants.locationMap.get(Constants.locationID);
        patientInformation = pi;
        File f = new File(System.getProperty("java.io.tmpdir")+"\\temp\\patientReport"+System.currentTimeMillis() % 1000+".pdf");
        
        f.getParentFile().mkdirs(); 
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(patientReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        if( f.exists() )
        {
            build(f);
            filePath = f.getAbsolutePath();
        }
        return filePath;
    }
}
