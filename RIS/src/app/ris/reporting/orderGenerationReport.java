/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.reporting;

import app.ris.bo.Constants;
import app.ris.bo.LocationBO;
import app.ris.bo.PatientBO;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


public class orderGenerationReport {
    
    StyleBuilder bgStyle = stl.style().setRotation(Rotation.NONE).setFontSize(80).setPadding(30).setForegroundColor(Color.LIGHT_GRAY);
//    TextFieldBuilder<String> textField = cmp.text("\n\n\n").setStyle(bgStyle);
    FileOutputStream fs = null;
    String profilePath = System.getProperty("java.io.tmpdir")+"\\get\\pro.png";
	String patientInformation[] = new String[9];
        PatientBO bo = null;
        LocationBO lbo = null;
//	public orderGenerationReport() {
//		build();
//	}
	
	private void build(File f) {
            
            System.out.println(profilePath);
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
		                                    .setFontSize(15);
                StyleBuilder fontSize = stl.style().setFontSize(12);
                StyleBuilder headingStyle = stl.style().bold().setFontSize(13);
		
                
                TextColumnBuilder<String>     itemColumn      = col.column("Item",       "item",      type.stringType()).setStyle(headingStyle);
		TextColumnBuilder<String>    quantityColumn  = col.column("",   "quantity",  type.stringType()).setStyle(fontSize);
                TextColumnBuilder<String>    quantityColumn2  = col.column("",   "quantity2",  type.stringType()).setStyle(fontSize);
		ColumnGroupBuilder itemGroup = grp.group(itemColumn);
		itemGroup.setPrintSubtotalsWhenExpression(exp.printWhenGroupHasMoreThanOneRow(itemGroup));
		try {
                    report().setBackgroundStyle(bgStyle)
//                            .addBackground(textField)
                            
                            .setColumnTitleStyle(columnTitleStyle)
                            
                            .setSubtotalStyle(boldStyle)
//                            .highlightDetailEvenRows()
                            
                            .columns(//add columns
                                    /*rowNumberColumn,*/ /*itemColumn,*/ quantityColumn,quantityColumn2/*, unitPriceColumn, priceColumn, pricePercColumn*/)
                            .columnGrid(
                                    /*rowNumberColumn,*/ quantityColumn,quantityColumn2, /*unitPriceColumn,*/ grid.verticalColumnGridList(/*priceColumn, pricePercColumn*/))
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
                                                    cmp.text("Department of Diagnostic Imaging and Radiology\nMayo Surgical Tower").setStyle(titleStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
//                                                        cmp.image(profilePath).setFixedDimension(80, 60).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT)
                                                    cmp.image(Constants.logoGov).setFixedDimension(140, 70).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT)
                                            )
                                            .newRow()
                                            .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10))
                            )
                            
                            
                            .pageFooter(cmp.line(),cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
                            .summary(
                                    cmp.line()
//                                    
//                                    
//                                    cmp.text("Location: "+lbo.getName()).setStyle(fontSize)
                            )
//                                .columnFooter(cmp.image("C:\\Users\\faizanahmed\\Pictures\\Saved Pictures\\1.png").setHorizontalImageAlignment(HorizontalImageAlignment.LEFT))
                            .setDataSource(createDataSource())
                            .toPdf(fs);
            try {
                fs.close();
            } catch (IOException ex) {
                Logger.getLogger(orderGenerationReport.class.getName()).log(Level.SEVERE, null, ex);
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
            String gender = bo.getGender().equalsIgnoreCase("M")?"Male":"Female";
		DRDataSource dataSource = new DRDataSource("item", "quantity","quantity2");
//                dataSource.add("Patient Information","Patient ID:\n",patientInformation[0],"Body Part:",patientInformation[4]);
		dataSource.add("Patient Information", "Patient ID:\t\t"+patientInformation[0]+
                                                        "\nPatient Name:\t\t"+patientInformation[1]+
                                                        "\nFather Name:\t\t"+bo.getFatherName()+
                                                        "\nCNIC:\t\t\t"+bo.getCnic()+
                                                        
                                                        "\nGender:\t\t"+gender+
                                                        "\nOther MRNO:\t\t"+bo.getOtherMrno()+
                                                        "\nAge:\t\t\t"+bo.getAge()+"  Years"+
                                                        "\nContact Number:\t"+bo.getContactNum(),
//                                                        "\nAddress:\t\t"+bo.getAddress()+" "+bo.getCity(),
//                                                        "Body Part:\t\t"+patientInformation[4]+
//                                                        "\nContrast:\t\t"+patientInformation[5]+                                                    
                                                        "Height:\t\t\t"+bo.getHeight()+"  Feet"+
                                                        "\nWeight:\t\t"+bo.getWeight()+"  Kgs"+                                                        
                                                        "\nOrder Location:\t"+patientInformation[3]+
                                                        "\nOrder Date:\t\t"+patientInformation[4]+
                                                        
                                                        "\n\nAccession Number:\t"+patientInformation[2]
                );
//                dataSource.add("Patient Information", "1aaaaaaaaaaaaaaaaaaaaaaaaaa\t\taaasdasaa");
//		dataSource.add("DVD", 5, new BigDecimal(30));
		dataSource.add("Order Details",patientInformation[5] /*patientInformation[8]+" - "+patientInformation[4]+" "+patientInformation[5]*/);
//		dataSource.add("DVD", 5, new BigDecimal(32));
//		dataSource.add("Book", 3, new BigDecimal(11));
//		dataSource.add("Book", 1, new BigDecimal(15));
		dataSource.add("History:",patientInformation[6]);
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

    public String generateOrder(String[] pi,boolean duplicate,PatientBO pbo) {
        String filePath="";
        patientInformation = pi;
        bo = pbo;
        lbo = Constants.locationMap.get(Constants.locationID);
        File f = new File(System.getProperty("java.io.tmpdir")+"\\temp\\order"+System.currentTimeMillis() % 1000+".pdf");
        
        f.getParentFile().mkdirs(); 
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(orderGenerationReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        if( f.exists() )
        {
//            if(duplicate)
            {
//                textField = cmp.text("\n\n\nDUPLICATE").setStyle(bgStyle);
//                build(f);
//                filePath = f.getAbsolutePath();
            }
//            else
            {
//                textField = cmp.text("\n\n\nORIGINAL").setStyle(bgStyle);
                build(f);
                filePath = f.getAbsolutePath();
            }
        }
        
        return filePath;
    }
}
