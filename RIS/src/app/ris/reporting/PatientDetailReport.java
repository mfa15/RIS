/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.reporting;

import app.ris.bo.Constants;
import app.ris.bo.PatientDetailReportBo;
import app.ris.bo.PatientSummaryReportBo;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.exp;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


public class PatientDetailReport {
    
    StyleBuilder bgStyle = stl.style().setRotation(Rotation.NONE).setFontSize(80).setPadding(30).setForegroundColor(Color.LIGHT_GRAY);
//    TextFieldBuilder<String> textField = cmp.text("\n\n\n").setStyle(bgStyle);
    FileOutputStream fs = null;
    LinkedHashMap<String,PatientDetailReportBo> patientDetailMap = new LinkedHashMap<String, PatientDetailReportBo>();
//        LocationBO lbo = null;
        String from, to,location;
        LinkedHashMap<String,PatientSummaryReportBo> patientSummaryMap = new LinkedHashMap<String, PatientSummaryReportBo>();
//	public orderGenerationReport() {
//		build();
//	}
	
	private void build(File f) {
            
        try {
            fs = new FileOutputStream(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PatientDetailReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConditionalStyleBuilder condition1 = stl.conditionalStyle(new CustomExpression()).bold();
        ConditionalStyleBuilder condition2 = stl.conditionalStyle(new CustomExpression2()).setBackgroundColor(new Color(234, 234, 234));
		StyleBuilder boldStyle         = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
		                                    .setBorder(stl.pen1Point())
		                                    .setBackgroundColor(Color.LIGHT_GRAY);
		StyleBuilder titleStyle        = stl.style(boldCenteredStyle)
		                                    .setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
		                                    .setFontSize(14);
                StyleBuilder fontSize = stl.style().setFontSize(12);
                StyleBuilder fontSize11 = stl.style().setFontSize(11);
                StyleBuilder headingStyle = stl.style().setFontSize(13);
		
                
                TextColumnBuilder<String>     itemColumn      = col.column("Itemz",       "item",      type.stringType()).setStyle(headingStyle);
		TextColumnBuilder<String>    quantityColumn  = col.column("",   "quantity",  type.stringType()).setStyle(fontSize);
                TextColumnBuilder<String>    quantityColumn2  = col.column("",   "quantity2",  type.stringType()).setStyle(fontSize);
                TextColumnBuilder<String>    quantityColumn3  = col.column("",   "quantity3",  type.stringType()).setStyle(fontSize);
                TextColumnBuilder<String>    quantityColumn4  = col.column("",   "quantity4",  type.stringType()).setStyle(fontSize);
                TextColumnBuilder<String>    quantityColumn5  = col.column("",   "quantity5",  type.stringType()).setStyle(fontSize);
                
                
//		ColumnGroupBuilder itemGroup = grp.group(itemColumn);
//		itemGroup.setPrintSubtotalsWhenExpression(exp.printWhenGroupHasMoreThanOneRow(itemGroup));
		try {
                    report().setBackgroundStyle(bgStyle)
//                            .addBackground(textField)
                            
                            .setColumnTitleStyle(columnTitleStyle)
                            
                            .setSubtotalStyle(boldStyle)
//                            .highlightDetailEvenRows()
                            
                            .detailRowHighlighters(condition1,condition2)
                            
                            
                            
                            .columns(//add columns
//                                    /*rowNumberColumn,*/ 
                                    itemColumn,  quantityColumn,quantityColumn2,quantityColumn3,quantityColumn4,quantityColumn5/*, unitPriceColumn, priceColumn, pricePercColumn*/)
                            .columnGrid(
                                    /*rowNumberColumn,*/itemColumn, quantityColumn,quantityColumn2,quantityColumn3,quantityColumn4,quantityColumn5, /*unitPriceColumn,*/ grid.verticalColumnGridList(/*priceColumn, pricePercColumn*/))
//                            .groupBy(itemGroup)
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
                                                    cmp.text("GOVERNMENT DHQ HOSPITAL, "+location+"").setStyle(headingStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                                    cmp.text( "A landmark initiative of Government of the Punjab in collaboration with").setStyle(fontSize11).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                                    cmp.text("EASTERN MEDICAL TECHNOLOGY SERVICES").setStyle(headingStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                                                    ),
                                                    
                                                        
                                                    cmp.image(Constants.logoGov).setFixedDimension(140, 70).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT)
                                            )
                                            .newRow()
                                            .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10))
                                    
                                    
                                    .newRow()
                                    .add(cmp.text("From : "+from))
                                    .newRow()
                                    .add(cmp.text("To : "+to))
                                    .newRow()
                                    .add(cmp.text("Location : "+location))
                                    .newRow()
                                            .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(8))
                            )
                            
                            
                            .pageFooter(cmp.line(),cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
                                    
//                                    cmp.line(),
//                                    cmp.text("Order Details").setStyle(headingStyle),
//                                    cmp.text("CPT Code  -    Acc No.\tCPT Description"),
//                                    cmp.text(orderDetail),
//                                    cmp.line(),                                  
//                                    cmp.text("History").setStyle(headingStyle),
//                                    cmp.text(hist)
////                                    
//                                    cmp.text("Location: "+lbo.getName()).setStyle(fontSize)
                            
//                                .columnFooter(cmp.image("C:\\Users\\faizanahmed\\Pictures\\Saved Pictures\\1.png").setHorizontalImageAlignment(HorizontalImageAlignment.LEFT))
                            .setDataSource(createDataSource())
                            .toPdf(fs);
            try {
                fs.close();
            } catch (IOException ex) {
                Logger.getLogger(PatientDetailReport.class.getName()).log(Level.SEVERE, null, ex);
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
//            String gender = bo.getGender().equalsIgnoreCase("M")?"Male":"Female";
		DRDataSource dataSource = new DRDataSource("item", "quantity","quantity2","quantity3","quantity4","quantity5");
                dataSource.add("Patient Type","With Contrast","Without Contrast","Total Count");
//here

                for(String key:this.patientSummaryMap.keySet())
                {
                    PatientSummaryReportBo value = patientSummaryMap.get(key);
                    String total = String.valueOf(Integer.valueOf(value.getWithContrast())+Integer.valueOf(value.getWithoutContrast()));
                    System.out.println(total);
                    dataSource.add(value.getPatientType(),value.getWithContrast(),value.getWithoutContrast(),total);
                }
                dataSource.add("","","");
                String type="",print="";
                for(String key:this.patientDetailMap.keySet())
                {
                    PatientDetailReportBo value =  this.patientDetailMap.get(key);
                    if(!type.equalsIgnoreCase(value.getPatien_type()))
                    {
                        type = value.getPatien_type();
                        print = type;
                    }
                    else
                    {
                        print="";
                    }
                    if(!print.equalsIgnoreCase(""))
                    {
                        dataSource.add(print);
                        dataSource.add();
                        dataSource.add("Patient ID","Patient Name","CNIC","Order Desc","Order Date","Contrast");
                    }
                    dataSource.add(value.getPatientId(),
                            value.getName(),value.getCnic(),value.getDescription(),value.getTrans_date().substring(0, value.getTrans_date().length()-5),value.getContrast());
                }
                
//                dataSource.add("Patient Information","Patient ID:\n",patientInformation[0],"Body Part:",patientInformation[4]);
//                dataSource.add("Patient Information", "1aaaaaaaaaaaaaaaaaaaaaaaaaa\t\taaasdasaa");
//		dataSource.add("DVD", 5, new BigDecimal(30));
//		dataSource.add( /*patientInformation[8]+" - "+patientInformation[4]+" "+patientInformation[5]*/);
//                dataSource.add("Order Details","CPT Code - Accession Number - Cpt Description ");
//                dataSource.add("",orderDetail /*patientInformation[8]+" - "+patientInformation[4]+" "+patientInformation[5]*/);
//                
////		dataSource.add("DVD", 5, new BigDecimal(32));
////		dataSource.add("Book", 3, new BigDecimal(11));
////		dataSource.add("Book", 1, new BigDecimal(15));
//		dataSource.add("History:",hist);
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

    public String generateOrder(String location,String from,String to,LinkedHashMap<String,PatientSummaryReportBo> patientSummaryMap,LinkedHashMap<String,PatientDetailReportBo> patientDetailMap) {
        String filePath="";
        this.from=from;
        this.to = to;
        this.location =location;
        this.patientDetailMap = patientDetailMap;
        this.patientSummaryMap = patientSummaryMap;
        File f = new File(System.getProperty("java.io.tmpdir")+"\\temp\\PatientDetail"+System.currentTimeMillis() % 1000+".pdf");
        
        f.getParentFile().mkdirs(); 
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(PatientDetailReport.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    
    
    private class CustomExpression extends AbstractSimpleExpression<Boolean> {
        private static final long serialVersionUID = 1L;
        @Override
        public Boolean evaluate(ReportParameters reportParameters) {
            String modality = reportParameters.getValue("item");

            if(modality!=null){
            if (modality.contains("-")) {

                return false;

            }  else {

                return true;

            }
            }
            return true;
        }
        
        
    }
    private class CustomExpression2 extends AbstractSimpleExpression<Boolean> {
        private static final long serialVersionUID = 1L;
        @Override
        public Boolean evaluate(ReportParameters reportParameters) {
            String modality = reportParameters.getValue("item");

            if(modality!=null){
            if (modality.contains("-") && reportParameters.getReportRowNumber()%2==0) {

                return true;

            }  else {

                return false;

            }
            }
            return false;
        }

 
        }

}
