/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.bo;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class JCheckCombo extends JComboBox
{
   boolean a=false;
   public String contrast="With Contrast,Without Contrast,";
   public String patientType="";
   public JCheckCombo() {
      init();
   }

   public JCheckCombo(JCheckBox[] items) {
      super(items);
      init();
   }
    
   public JCheckCombo(Vector items) {
      super(items);
      init();
   }
    
   public JCheckCombo(ComboBoxModel aModel) {
      super(aModel);
      init();
   }
    
   private void init() {
      setRenderer(new ComboBoxRenderer());
      addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae) {
            itemSelected();
            
         }
      });
   }

@Override
public void setPopupVisible(boolean v) {
    try{
        if(a)
        super.setPopupVisible(a); //To change body of generated methods, choose Tools | Templates.
    }catch(java.awt.IllegalComponentStateException e)
    {}
}
  
   private void itemSelected() {
      if (getSelectedItem() instanceof JCheckBox) {
         JCheckBox jcb = (JCheckBox)getSelectedItem();
         jcb.setSelected(!jcb.isSelected());
         String jcbText = jcb.getText();
         if(jcb.isSelected())
         {
             if(jcbText.equalsIgnoreCase("With Contrast") || jcbText.equalsIgnoreCase("Without Contrast"))
                 contrast = contrast+jcbText+",";
             else
                 patientType = patientType+jcbText+",";
         }
         else
         {
             if(jcbText.equalsIgnoreCase("With Contrast") || jcbText.equalsIgnoreCase("Without Contrast"))
                contrast = contrast.replace(jcbText+",", "");
             else
                patientType = patientType.replace(jcbText+",", "");
         }
         a=true;
         setPopupVisible(a);
      }
   }
  
   class ComboBoxRenderer implements ListCellRenderer {
      private JLabel label;
       
      public ComboBoxRenderer() {
         setOpaque(true);
      }
       
      public Component getListCellRendererComponent(JList list, Object value, int index,
                                                    boolean isSelected, boolean cellHasFocus) {
         if (value instanceof Component) {
            Component c = (Component)value;
            if (isSelected) {
               c.setBackground(list.getSelectionBackground());
               c.setForeground(list.getSelectionForeground());
            } else {
               c.setBackground(list.getBackground());
               c.setForeground(list.getForeground());
            }
              
            return c;
         } else {
            if (label ==null) {
               label = new JLabel(value.toString());
            }
            else {
               label.setText(value.toString());
            }
                
            return label;
         }
      }
   }
   
   
   
   
}