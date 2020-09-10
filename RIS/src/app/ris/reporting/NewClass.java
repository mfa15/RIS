/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.reporting;

import java.awt.Color;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.exp;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilders;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class NewClass {
	
	public NewClass() {
//		build();
	}
	
	public  void build() {
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
		TextColumnBuilder<String>    quantityColumn  = col.column("CT Scan Report",   "quantity",  type.stringType());
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
			report()//create new report design
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
			  			cmp.image("C:\\Users\\faizanahmed\\Pictures\\Camera Roll\\download.png").setFixedDimension(80, 80).setHorizontalImageAlignment(HorizontalImageAlignment.LEFT),
                                                cmp.image("C:\\Users\\faizanahmed\\Pictures\\Camera Roll\\address.png").setHorizontalImageAlignment(HorizontalImageAlignment.LEFT),
//			  			cmp.text("Organization Name").setStyle(titleStyle).setHorizontalAlignment(HorizontalAlignment.LEFT),
//                                                cmp.text("abc").setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
			  			cmp.text("Patient Procedure Order").setStyle(titleStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT))
			  		.newRow()
			  		.add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10)))
			  .pageFooter(cmp.line(),cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
			  .summary(
			  	cmp.line(),
                                  
                                  
                                  cmp.text("Doctor: Dr. ABC"))
                            .columnFooter(cmp.image("C:\\Users\\faizanahmed\\Pictures\\Saved Pictures\\1.png").setHorizontalImageAlignment(HorizontalImageAlignment.LEFT))
			  .setDataSource(createDataSource())//set datasource
			  .show();//create and show report
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "quantity");
		dataSource.add("Patient Information", "1aaaaaaaaaaaaaaaaaaaaaaaaaa\t\taaaa");
                dataSource.add("Patient Information", "1aaaaaaaaaaaaaaaaaaaaaaaaaa\t\taaasdasaa");
//		dataSource.add("DVD", 5, new BigDecimal(30));
		dataSource.add("Report", "nbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldas\naksjdhkasjdhasd\nasdasd");
//		dataSource.add("DVD", 5, new BigDecimal(32));
//		dataSource.add("Book", 3, new BigDecimal(11));
//		dataSource.add("Book", 1, new BigDecimal(15));
		dataSource.add("Impression","asdasdnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasnbnnaskdhsakdhaskldhaskldasasdasdasd\nasdasdasd\nasdsa");
//		dataSource.add("Book", 8, new BigDecimal(9));
		return dataSource;
	}
	
        public ComponentBuilders makeTitle()
        {
            ComponentBuilders a = new ComponentBuilders();
            
            a.text("abcx").setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
            a.text("asdasdadadas").setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
            return a;
        }
        
	public static void main(String[] args) {
		new NewClass();
	}
}