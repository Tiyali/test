package com.andy;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.andy.tools.FileUtils;




/**
 * dip计算
 * 
 * @author DwGG
 */
public class YYdip extends JFrame {
	
	final String PREBUILD_PATH = "/com/andy/prebuild/";
	final int DESKTOP_WIDTH = 480;
	final int DESKTOP_HEIGHT = 360;
	
//	private TextField px;
//	private JComboBox dpi;
//	private TextField dp;
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new YYdip().createAndShowGUI();
			}
		});
	}
	
	public YYdip(){
		super("dip计算 v0.1");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		ImageIcon icon=new ImageIcon(FileUtils.getResource(PREBUILD_PATH + "app.png"));
		setIconImage(icon.getImage());
	}
	
	private void createAndShowGUI() {


        //Display the window.
//		setApiInfo(null);
		this.setPreferredSize(new Dimension(DESKTOP_WIDTH, DESKTOP_HEIGHT));
	    JPanel xPanel = new JPanel();
	    xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.X_AXIS));
	    
	    addComponentsToPane(getRootPane().getContentPane());
        pack();
        setLocationRelativeTo(null);  
        setVisible(true);
	}
	
	private void addComponentsToPane(Container contentPane) {
		
		Box container = Box.createVerticalBox();
		contentPane.add(container);

	    JPanel mPanel = new JPanel();
	    mPanel.setBorder(BorderFactory.createEtchedBorder());
	    mPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    mPanel.add(createCaclPanel(240));
	    mPanel.add(createCaclPanel(320));
	    mPanel.add(createCaclPanel(480));
	    container.add(mPanel);
	    
	    
	    JPanel xPanel = new JPanel();
	    xPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    init1(xPanel);
	    container.add(xPanel);

	    
	}
	
	private JPanel createCaclPanel(int dpi) {
	    JPanel dpiPanel = new JPanel();
	    dpiPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    init2(dpiPanel, dpi);
	    return dpiPanel;
	}
	
	private void init1(Container container) {
		final TextField px = new TextField(10);
		Integer[] petStrings = { 120, 160, 240, 320, 480, 640, 400 };
		final JComboBox dpi = new JComboBox(petStrings);
		final TextField dp = new TextField(10);
		
		container.add(new JLabel("px-->dp ："));
		container.add(new JLabel("("));
		container.add(px);
		container.add(new JLabel("/"));
		container.add(dpi);
		container.add(new JLabel(") * 160 = "));
		container.add(dp);
		
		px.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				String result = calc(px.getText(), Integer.valueOf((Integer) dpi.getSelectedItem()));
				dp.setText(result);
			}
		});
		
		dpi.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String result = calc(px.getText(), Integer.valueOf((Integer) dpi.getSelectedItem()));
				dp.setText(result);
			}
		});
	}
	
	private void init2(Container container, final int dpi) {
		final TextField px = new TextField(10);
		final TextField dp = new TextField(10);
		
		container.add(new JLabel("px-->dp ："));
		container.add(new JLabel("("));
		container.add(px);
		container.add(new JLabel("/ " + dpi + " ) * 160 = "));
		container.add(dp);
		
		px.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				String result = calc(px.getText(), dpi);
				dp.setText(result);
			}
		});
	}
	
	private String calc(String pxStr, int dpiInt) {
		boolean ok = false;
		float pxInt = 0;
		
		if (pxStr != null && !pxStr.isEmpty()) {
			try {
				pxInt = Float.valueOf(pxStr);
				ok = true;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			return "";
		}
		
		if (ok) {
			return String.valueOf(doCalc(pxInt, dpiInt)) ;
		} else {
			return "error";
		}
	}
	
	//dp = (px/dpi)*160
	private float doCalc(float px, int dpi) {
		return (px/dpi)*160;
	}
	
}
