/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.reporting;

import app.ris.bo.Constants;
import app.ris.bo.CptWiseOrderEntryBO;
import app.ris.bo.LocationBO;
import app.ris.bo.PatientAttendantInfo;
import app.ris.bo.PatientBO;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import net.sf.dynamicreports.report.builder.component.FillerBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.Markup;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

public class CptWiseOrderGenerationReportEMT {

    public CptWiseOrderGenerationReportEMT(String var) {
        stateOfReceipt = var;
    }
    String attendantProPath = "";
    String attendantThumbPath = "";
    String stateOfReceipt;
    String workOrderByEmp = "";
    String attendantInfo = "";
    StyleBuilder bgStyle = stl.style().setRotation(Rotation.NONE).setFontSize(80).setPadding(30).setForegroundColor(Color.LIGHT_GRAY);
//    TextFieldBuilder<String> textField = cmp.text("\n\n\n").setStyle(bgStyle);
    FileOutputStream fs = null;
    String profilePath = System.getProperty("java.io.tmpdir") + "\\get\\pro.png";
    String orderInfo[] = new String[4];
    PatientAttendantInfo pai = new PatientAttendantInfo();
    PatientBO bo = null;
    LocationBO lbo = null;
    String hist, orderDetail = "", price = "";
    int total = 0, payable = 0;
    String pay = "";
    int width = 10, x = 120, y = 60;
    FillerBuilder filler = null;
//	public orderGenerationReport() {
//		build();
//	}

    private void build(File f) {
        String patientType = orderInfo[3];
        // patientType = "private";
        System.out.println(Constants.getThumbImpression);
        try {
            fs = new FileOutputStream(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CptWiseOrderGenerationReportEMT.class.getName()).log(Level.SEVERE, null, ex);
        }
        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                .setBorder(stl.pen1Point())
                .setBackgroundColor(Color.LIGHT_GRAY);
        StyleBuilder titleStyle = stl.style(boldCenteredStyle)
                .setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
                .setFontSize(14);
        StyleBuilder html = stl.style().setMarkup(Markup.HTML);
        StyleBuilder fontSize11 = stl.style().setFontSize(11);
        StyleBuilder headingStyle = stl.style().bold().setFontSize(13);

//                TextColumnBuilder<String>     itemColumn      = col.column("Item",       "item",      type.stringType()).setStyle(headingStyle);
//		TextColumnBuilder<String>    quantityColumn  = col.column("",   "quantity",  type.stringType()).setStyle(fontSize);
//                TextColumnBuilder<String>    quantityColumn2  = col.column("",   "quantity2",  type.stringType()).setStyle(fontSize);
//		ColumnGroupBuilder itemGroup = grp.group(itemColumn);
//		itemGroup.setPrintSubtotalsWhenExpression(exp.printWhenGroupHasMoreThanOneRow(itemGroup));
        try {
            JasperReportBuilder report = report();
            if (pay.equalsIgnoreCase("0/- PKR")) {
                report = report.addBackground(cmp.image(Constants.free)
                        .setFixedDimension(500, 750));
            }
            report = report.setBackgroundStyle(bgStyle)
                    .setColumnTitleStyle(columnTitleStyle)
                    .setSubtotalStyle(boldStyle)
                    //                            .highlightDetailEvenRows()

                    .columns(//add columns
                    //                                    /*rowNumberColumn,*/ /*itemColumn,*/ quantityColumn,quantityColumn2/*, unitPriceColumn, priceColumn, pricePercColumn*/)
                    //                            .columnGrid(
                    )//                                    /*rowNumberColumn,*/ quantityColumn,quantityColumn2, /*unitPriceColumn,*/ grid.verticalColumnGridList(/*priceColumn, pricePercColumn*/))
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
                                            cmp.text("GOVERNMENT DHQ HOSPITAL, " + lbo.getName() + "").setStyle(headingStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                            cmp.text("A landmark initiative of Government of the Punjab in collaboration with").setStyle(fontSize11).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                                            cmp.text("EASTERN MEDICAL TECHNOLOGY SERVICES").setStyle(headingStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                                    ),
                                    cmp.image(Constants.logoGov).setFixedDimension(140, 70).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT)
                            )
                            .newRow()
                            .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10))
                            .newRow().add(cmp.text(stateOfReceipt).setStyle(headingStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER))
                            .newRow()
                            .newRow()
                            .add(cmp.line())
                            .newRow().add(cmp.text("Patient Information:").setStyle(headingStyle))
                            .newRow()
                            .add(cmp.text(
                                    "Patient ID:\t\t" + bo.getLocation_id() + "-" + bo.getPatientId()
                                    + "\nName:\t\t\t" + bo.getName()
                                    + "\nFather\\Husband Name:\t" + bo.getFatherName()
                                    + "\nCNIC:\t\t\t" + bo.getCnic()
                                    + "\nContact Number:\t\t" + bo.getContactNum()
                                    + "\nCity:\t\t\t" + bo.getCity()
                                    + "\nHeight:\t\t\t" + bo.getHeight()
                                    + "  Feet\nWeight:\t\t\t" + bo.getWeight()
                                    + "  Kgs\nAge:\t\t\t" + bo.getAge()
                                    + "\nPatient Type:\t\t" + orderInfo[3]
                                    + "\nOrder Location:\t\t" + orderInfo[1]
                                    + "\nOrder Date:\t\t" + orderInfo[2]
                            ).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
                                    cmp.horizontalList(
                                            cmp.image(Constants.getThumbImpression).setFixedDimension(120, 60).setHorizontalImageAlignment(HorizontalImageAlignment.LEFT),
                                            cmp.image(Constants.profilePath).setFixedDimension(120, 60).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT)
                                    )
                            )
                            .newRow()
                            .add(filler)
                            .newRow()
                            .add(cmp.text(attendantInfo).setStyle(html).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
                                    cmp.horizontalList(
                                            cmp.image(this.attendantThumbPath).setFixedDimension(120, 60).setHorizontalImageAlignment(HorizontalImageAlignment.LEFT),
                                            cmp.image(this.attendantProPath).setFixedDimension(x, y).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT)
                                    )
                            )
                    )
                    .pageFooter(cmp.line(), cmp.text("Employee ID : " + Constants.employeeID + "\t\tEmployee Name : " + Constants.employeeName + "\t\tDesignation : " + Constants.userRolesMap.get(Constants.employeeRole)))//shows number of page at page footer
                    .pageFooter(cmp.text("Work Order By : " + workOrderByEmp + "\t\tPrinted By : " + Constants.employeeName))//shows number of page at page footer

                    .pageFooter(cmp.line())//shows number of page at page footer

                    .summary(
                            cmp.horizontalList()
                            .newRow().add(cmp.line())
                            .newRow().add(
                                    cmp.text("Order Details").setStyle(headingStyle))
                            .newRow()
                            .add(
                                    cmp.text("CPT Code  -    Acc No.\tCPT Description").setFixedWidth(500).setStyle(boldStyle), cmp.text("Price").setStyle(boldStyle)
                            )
                            .newRow()
                            .add(
                                    cmp.text(orderDetail).setFixedWidth(500), cmp.text(price)
                            )
                            .newRow()
                            .add(
                                    cmp.line()
                            )
                            .newRow().add(
                                    cmp.text("History").setStyle(headingStyle))
                            .newRow()
                            .add(
                                    cmp.text(hist)
                            )
                            .newRow()
                            .add(
                                    cmp.line()
                            )
                            //
                            .newRow().add(
                                    cmp.text("Payment").setStyle(headingStyle))
                            .newRow()
                            .add(
                                    cmp.text("Total Amount ").setFixedWidth(500), cmp.text(total + "/- PKR").setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT)
                            )
                            .newRow().add(cmp.text("Total Payable ").setFixedWidth(500), cmp.text(pay).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT))
                            .newRow()
                            .add(
                                    cmp.text("   ")
                            )
                    );
            if (!patientType.toLowerCase().contains("private")) {
                report.summary(cmp.horizontalList().newRow()
                        .add(
                                cmp.text("Note: CT Scan is being performed under contract # PSHD SO (P-1) 1-8/2016 (A), (B), (C)  with Government of Punjab.").setHorizontalTextAlignment(HorizontalTextAlignment.LEFT)
                        ));
            }
            report.setDataSource(createDataSource())
                    .toPdf(fs);
            try {
                fs.close();
            } catch (IOException ex) {
                Logger.getLogger(CptWiseOrderGenerationReportEMT.class.getName()).log(Level.SEVERE, null, ex);
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
        DRDataSource dataSource = new DRDataSource("item", "quantity", "quantity2");
//                dataSource.add("Patient Information","Patient ID:\n",patientInformation[0],"Body Part:",patientInformation[4]);
        dataSource.add("Patient Information", "Patient ID:\t\t" + bo.getLocation_id() + "-" + bo.getPatientId()
                + "\nPatient Name:\t\t" + bo.getName()
                + "\nFather Name:\t\t" + bo.getFatherName()
                + "\nCNIC:\t\t\t" + bo.getCnic()
                + //                                                        "\nGender:\t\t"+gender+
                "\nOther MRNO:\t\t" + bo.getOtherMrno()
                + "\nContact Number:\t" + bo.getContactNum()
                + //                                                        "\nAddress:\t\t"+bo.getAddress()+" "+bo.getCity(),
                //                                                        "Body Part:\t\t"+patientInformation[4]+
                //                                                        "\nContrast:\t\t"+patientInformation[5]+
                "\nAge:\t\t\t" + bo.getAge() + "  Years"
                + "\nHeight:\t\t\t" + bo.getHeight() + "  Feet"
                + "\nWeight:\t\t" + bo.getWeight() + "  Kgs"
        );
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
        return null;
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
    public String generateOrder(LinkedHashMap<String, CptWiseOrderEntryBO> cptWiseOrderMap, String history, PatientBO pbo, String[] orderInfo, boolean flag, PatientAttendantInfo p) {
        String filePath = "";
        this.orderInfo = orderInfo;
        bo = pbo;
        hist = history;
        lbo = Constants.locationMap.get(Constants.locationID);
        pai = p;

        try {
            if (pai != null) {
                attendantInfo = "<font size=\"3\"><b>Attendant Information:</b></font>\n"
                        + "<br>Name: " + pai.getName() + "\n"
                        + "<br>Relation: " + pai.getRelation() + "\n"
                        + "<br>CNIC No: " + pai.getCnic() + "\n"
                        + "<br>Contact No:" + pai.getContact() + "<br>";
                this.attendantProPath = Constants.attendProfilePath;
                this.attendantThumbPath = Constants.getThumbImpressionAttendant;
                width = 10;
                x = 120;
                y = 60;
                filler = cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(width);
            } else {
                this.attendantProPath = "";
                this.attendantThumbPath = "";
                attendantInfo = "";
                Constants.attendProfilePath = "";
                width = 0;
                x = 0;
                y = 0;
                filler = cmp.filler();
            }
        } catch (Exception e) {
            attendantInfo = "";
            Constants.attendProfilePath = "";
            width = 0;
            x = 0;
            y = 0;
            filler = cmp.filler();
        }

        for (String key : cptWiseOrderMap.keySet()) {
            CptWiseOrderEntryBO temp = cptWiseOrderMap.get(key);
            workOrderByEmp = temp.getWorkOrderEmployeeName();
            int length = 80 - temp.getCptDescription().length();
            if (length > 0) {
                orderDetail = orderDetail + temp.getCptCode() + "\t\t" + temp.getAccessionNum() + "\t" + temp.getCptDescription() + "\n\n";
            } else {
                orderDetail = orderDetail + temp.getCptCode() + "\t\t" + temp.getAccessionNum() + "\t" + temp.getCptDescription().substring(0, 80) + "\n";
            }

            if (temp.getPrice().equalsIgnoreCase("0")) {
                price = price + temp.getCptCost() + "/-\n\n";
            } else {
                price = price + temp.getPrice() + "/-\n\n";
                total = total + Integer.valueOf(temp.getPrice());
            }
        }
        if (flag) {
            pay = total + "/- PKR";
        } else {
            pay = "0/- PKR";
        }
        File f = new File(System.getProperty("java.io.tmpdir") + "\\temp\\order" + System.currentTimeMillis() % 1000 + ".pdf");

        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(CptWiseOrderGenerationReportEMT.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (f.exists()) {
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
