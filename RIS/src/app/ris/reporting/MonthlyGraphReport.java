/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.reporting;


import app.ris.bo.Constants;
import app.ris.bo.LocationBO;
import app.ris.dam.dam;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.chart.CategoryChartSerieBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
/**
34
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
35
 */
public class MonthlyGraphReport {
 
    FileOutputStream fs = null;
    StyleBuilder boldStyle         = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		StyleBuilder titleStyle        = stl.style(boldCenteredStyle)
		                                    .setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
		                                    .setFontSize(12);
    File f = new File(System.getProperty("java.io.tmpdir")+"\\temp\\MonthlyGraph"+System.currentTimeMillis() % 1000+".pdf");
    String[] months;
    TextColumnBuilder[] columns =null;
    String dataSourceName[] =null;
    LinkedHashMap<Integer,LinkedHashMap<String,String>> monthWiseLocationData = new LinkedHashMap<>();
    public static LinkedHashMap<String,LocationBO> locationMap = new LinkedHashMap<String, LocationBO>();
    dam d = new dam();
    public MonthlyGraphReport() {
        
        d.getActiveLocations(locationMap);
        columns = new TextColumnBuilder[locationMap.size()+1] ;
        dataSourceName= new String[locationMap.size()+1];
        
        f.getParentFile().mkdirs(); 
        try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(FinalSignedDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

   }
   TextColumnBuilder<String> itemColumn  = col.column("Month", "x", type.stringType());

      public String build(String mon[],LinkedHashMap<Integer,LinkedHashMap<String,String>> mwld) {
       
       months = mon;
       monthWiseLocationData=mwld;
       FontBuilder boldFont = stl.fontArialBold().setFontSize(12);
 
      
      
 
      try {
          try {
              fs = new FileOutputStream(f);
          } catch (FileNotFoundException ex) {
              Logger.getLogger(MonthlyGraphReport.class.getName()).log(Level.SEVERE, null, ex);
          }
         report().setPageFormat(PageType.LETTER, PageOrientation.LANDSCAPE)
               .setTemplate(Templates.reportTemplate)
               .columns(columns() )
               .title(
                       cmp.horizontalList(
                       cmp.image(Constants.logoPath).setFixedDimension(100, 50).setHorizontalImageAlignment(HorizontalImageAlignment.LEFT),
                        cmp.verticalList(
                            cmp.text("Location wise Monthly Graph Report").setStyle(titleStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                        ),
                      cmp.image(Constants.logoGov).setFixedDimension(100, 50).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT)
                               )
               )
               .summary(
                     cht.lineChart()
                           .setTitle("CT Scan Performance Graph")
                           .setTitleFont(boldFont)
                           .setCategory(itemColumn)
                           .series(
                                   series()
                           )
                           .setCategoryAxisFormat(
                                 cht.axisFormat().setLabel("Locations")))
               .pageFooter(Templates.footerComponent)
               .setDataSource(createDataSource())
                    .toPdf(fs);
          try {
              fs.close();
          } catch (IOException ex) {
              Logger.getLogger(MonthlyGraphReport.class.getName()).log(Level.SEVERE, null, ex);
          }
         
      } catch (DRException e) {
         e.printStackTrace();
      }
      return f.getAbsolutePath();
   }
 
   private CategoryChartSerieBuilder[] series()
   {
//       CategoryChartSerieBuilder[] a={cht.serie(quantityColumn),cht.serie(unitPriceColumn),cht.serie(Kasur)};
       CategoryChartSerieBuilder[] a= new CategoryChartSerieBuilder[columns.length-1];
       for(int i=1;i<columns.length;i++)
       {
           a[i-1] = cht.serie(columns[i]);
       }
       
       
       return a;
   }
   
   private TextColumnBuilder[] columns()
   {
       
       int i=0;
       columns[i]= itemColumn;
       dataSourceName[i]="x";
       for(String key:locationMap.keySet())
       {
           LocationBO lbo = locationMap.get(key);
           if(lbo.getActive().equalsIgnoreCase("Y"))
           {
                
                i++;
                TextColumnBuilder c = col.column(lbo.getName(), lbo.getName(), type.integerType());
                columns[i] = c;
                dataSourceName[i]=lbo.getName();
           }
       }
       return columns;
   }
   
   private JRDataSource createDataSource() {
       
      DRDataSource dataSource = new DRDataSource(dataSourceName);
      for (int i = 11; i >=0 ; i--) 
      {
            dataSource.add(valuesOfColumns(i));
           
      }
      return dataSource;
   }
   
   private Object[] valuesOfColumns(int month)
   {
       Object row[]= new Object[locationMap.size()+1];
       int i=0;
       row[i] = months[month];
//       for(int key:monthWiseLocationData.keySet())
       {
            LinkedHashMap<String,String> value = monthWiseLocationData.get(month);
            for(String key1:locationMap.keySet())
            {
                i++;
                String temp = value.get(key1);
                if(temp==null)
                {
                    row[i]=0;
                }
                else
                {
                    row[i] = Integer.valueOf(temp);
                }
            }
       }
       return row;
   }
}
