/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.reporting;

import app.ris.bo.Constants;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


public class employeeRegReport {
    
    StyleBuilder bgStyle = stl.style().setRotation(Rotation.NONE).setFontSize(80).setPadding(30).setForegroundColor(Color.LIGHT_GRAY);
    TextFieldBuilder<String> textField = cmp.text("\n\n\n").setStyle(bgStyle);
    
    HashMap<String,String> employee = new HashMap<String, String>();
//	public orderGenerationReport() {
//		build();
//	}
	
	private void build(File f) {
		StyleBuilder boldStyle         = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
		                                    .setBorder(stl.pen1Point())
		                                    .setBackgroundColor(Color.LIGHT_GRAY);
		StyleBuilder titleStyle        = stl.style(boldCenteredStyle)
		                                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
		                                    .setFontSize(15);
		
                
                
		//                                                           title,     field name     data type
		TextColumnBuilder<String>     itemColumn      = col.column("Item",       "item",      type.stringType()).setStyle(boldStyle);
		TextColumnBuilder<String>    quantityColumn  = col.column("Employee Registration Report",   "quantity",  type.stringType());
//		TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "unitprice", type.bigDecimalType());
		//price = unitPrice * quantity
//		TextColumnBuilder<BigDecimal> priceColumn     = unitPriceColumn.multiply(quantityColumn).setTitle("Price");
//		PercentageColumnBuilder       pricePercColumn = col.percentageColumn("Price %", priceColumn);
//		TextColumnBuilder<Integer>    rowNumberColumn = col.reportRowNumberColumn("No.")
//		                                                    //sets the fixed width of a column, width = 2 * character width
//		                                                   .setFixedColumns(2)
//		                                                   .setHorizontalAlignment(HorizontalAlignment.CENTER);
//		Bar3DChartBuilder itemChart = cht.bar3DChart()
//		                                 .setTitle("Sales by item")
//		                                 .setCategory(itemColumn)
//		                                 .addSerie(
//		                                	 cht.serie(unitPriceColumn), cht.serie(priceColumn));
//		Bar3DChartBuilder itemChart2 = cht.bar3DChart()
//		                                 .setTitle("Sales by item")
//		                                 .setCategory(itemColumn)
//		                                 .setUseSeriesAsCategory(true)
//		                                 .addSerie(
//		                                	 cht.serie(unitPriceColumn), cht.serie(priceColumn));
		ColumnGroupBuilder itemGroup = grp.group(itemColumn);
		itemGroup.setPrintSubtotalsWhenExpression(exp.printWhenGroupHasMoreThanOneRow(itemGroup));
		try {
                    try {
                        report().setBackgroundStyle(bgStyle).addBackground(textField)

//create new report design
                                .setColumnTitleStyle(columnTitleStyle)
                                
                                .setSubtotalStyle(boldStyle)
                                .highlightDetailEvenRows()
                                
                                .columns(//add columns
                                        /*rowNumberColumn,*/ /*itemColumn,*/ quantityColumn/*, unitPriceColumn, priceColumn, pricePercColumn*/)
                                .columnGrid(
                                        /*rowNumberColumn,*/ quantityColumn, /*unitPriceColumn,*/ grid.verticalColumnGridList(/*priceColumn, pricePercColumn*/))
                                .groupBy(itemGroup)
//			  .subtotalsAtSummary(
//			  	sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
//			  .subtotalsAtFirstGroupFooter(
//			  	sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
                                .title(//shows report title
                                        cmp.horizontalList()
                                                .add(
                                                        cmp.image(Constants.logoPath).setFixedDimension(243, 104).setHorizontalImageAlignment(HorizontalImageAlignment.LEFT),
//                                                        cmp.image(Constants.addressPath).setHorizontalImageAlignment(HorizontalImageAlignment.LEFT),
//			  			cmp.text("Organization Name").setStyle(titleStyle).setHorizontalAlignment(HorizontalAlignment.LEFT),
//                                                cmp.text("abc").setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
                                                        cmp.text("Employee Registration Report").setStyle(titleStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT))
                                                .newRow()
                                                .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10)))
                                .pageFooter(cmp.line(),cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
                                .summary(
                                        cmp.line(),
                                        
                                        
                                        cmp.text("Counter Location: MAIN CITY"))
//                                .columnFooter(cmp.image("C:\\Users\\faizanahmed\\Pictures\\Saved Pictures\\1.png").setHorizontalImageAlignment(HorizontalImageAlignment.LEFT))
                                .setDataSource(createDataSource())
                                
                                .toPdf(new FileOutputStream(f));
//			  .show();//create and show report
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(employeeRegReport.class.getName()).log(Level.SEVERE, null, ex);
                    }
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "quantity");
                
                
		dataSource.add("Total Count", String.valueOf( employee.size()));
                dataSource.add("Employee Details ","Employee ID\t\t\tName\t\t\t\t\tRegistration Date");
                
                for (Map.Entry<String, String> entry : employee.entrySet()) {
                    String key = entry.getKey();
                    String value[] = employee.get(key).split("#");
                    dataSource.add("",key+"\t\t\t\t"+value[0]+"\t\t\t\t\t"+value[1]);
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

    public String generateOrder(HashMap<String, String> hm) {
        String filePath="";
        employee = hm;
        File f = new File(System.getProperty("user.dir")+"\\temp\\empReg.pdf");
        
        f.getParentFile().mkdirs(); 
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(employeeRegReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        if( f.exists() )
        {
            build(f);
            filePath = System.getProperty("user.dir")+"\\temp\\empReg.pdf";
        }
        return filePath;
    }
}
