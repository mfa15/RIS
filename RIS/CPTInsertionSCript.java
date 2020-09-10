/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.cpt;

import app.ris.dam.dam;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Amir Bashir
 */
public class CPTInsertionSCript {
   public static void main(String[] args) {

        String csvFile = "d:\\cpts.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        //﻿CPT_Code	CPT_Desc	Modality	Body_Part	Short_Desc	Active

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                    String cpt_code = country[0];
                    String desc = country[1];
                    String modality = country[2];
                    String body = country[3];
                    String shortDesc = country[4];
                    String active = "Y";
                    String locationId = "1";
                    String contrast = "";
                    //System.out.print(t+"\t");
                    if(desc.toLowerCase().contains("with") && desc.toLowerCase().contains("without")) {
                        contrast = "Y";
                    } else if(desc.toLowerCase().contains("without")) {
                        contrast = "N";
                    } else if(desc.toLowerCase().contains("with")) {
                        contrast = "Y";
                    }
                    if(modality.equalsIgnoreCase("mr")) {
                        desc = "MRI "+desc;
                    } else if(modality.equalsIgnoreCase("ct")) {
                        desc = "CT "+desc;
                    } else if(modality.equalsIgnoreCase("us")) {
                        desc = "Ultrasound "+desc;
                    } else if(modality.equalsIgnoreCase("cr")) {
                        desc = "XRay "+desc;
                    }
                    String query = "insert into radiology.cpt(cpt_code,description,modality,contrast,body_part,cost,price,"
                            + "active,location_id) values ("+cpt_code+",'"+desc+"','"+modality+"','"+contrast+"',"
                            + "'"+body+"',100,100,'Y',1);";
                    query = "update radiology.cpt set "
                            + "description='"+desc.trim()+"',"
                            + "modality='"+modality.trim()+"',"
                            + "contrast='"+contrast+"',"
                            //+ "short_desc='"+shortDesc+"',"
                            + "body_part='"+body.trim()+"' where cpt_code="+cpt_code+";";
                    System.out.println(query);
                   
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
