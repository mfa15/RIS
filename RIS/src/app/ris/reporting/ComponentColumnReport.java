/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.reporting;

/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2015 Ricardo Mariaca
 * http://www.dynamicreports.org
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.InputStream;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class ComponentColumnReport {

	public ComponentColumnReport() {
		build();
	}

	private void build() {
		try {
			ImageBuilder image = cmp.image(new ImageExpression()).setFixedDimension(20, 20);
//			HorizontalListBuilder itemComponent = cmp.horizontalList(
//				image,
//				cmp.verticalList(
//					cmp.text(new ItemExpression())));
        
			report()
			  .setTemplate(Templates.reportTemplate)
			  .fields(
			  	field("image", String.class)
                                //  ,field("barcode", String.class)
                          )
			  .columns(
			  	col.componentColumn("Image", image),
			  	col.column("Item", "item", type.stringType())
			  //	col.componentColumn("Item",	itemComponent)
                          )
			  .title(Templates.createTitleComponent("ComponentColumn"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class ImageExpression extends AbstractSimpleExpression<InputStream> {
		private static final long serialVersionUID = 1L;

		@Override
		public InputStream evaluate(ReportParameters reportParameters) {
			return Templates.class.getResourceAsStream("images/" + reportParameters.getValue("image") + ".png");
		}
	}



	public class ItemExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			return reportParameters.getValue("item");
		}
	}

	public class BarcodeExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			return reportParameters.getValue("barcode");
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "image", "barcode");
		dataSource.add("PDA", "pda", "100264832717658");
		dataSource.add("Camera", "camera", "100364875790352");
		dataSource.add("Camera", "camera", "100764935316351");
		dataSource.add("USB", "usb", "100864565780343");
		dataSource.add("PDA", "pda", "100264865712551");
		dataSource.add("USB", "usb123", "100268834723431");
		return dataSource;
	}

	public static void main(String[] args) {
		new ComponentColumnReport();
	}
}
