package com.dna.Bird.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class StatusBar extends JPanel {
	private static final long serialVersionUID = 2163241376047619059L;
	
	private JLabel leftLabel;
	private JLabel middleLabel;
	private JLabel rightLabel;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 */
	public StatusBar() {
		Border border = LineBorder.createGrayLineBorder();
		Border margin = getMargin(5, 20, 5, 20);
		
		leftLabel = getLabel(border, margin, SwingConstants.CENTER);
		middleLabel = getLabel(border, margin, SwingConstants.CENTER);
		rightLabel = getLabel(border, margin, SwingConstants.CENTER);
		
		this.setBorder(new CompoundBorder(border, getMargin(5, 5, 5, 5)));
		this.setLayout(new BorderLayout());
		this.add(leftLabel, BorderLayout.WEST);
		this.add(middleLabel, BorderLayout.CENTER);
		this.add(rightLabel, BorderLayout.EAST);
	}
	
	/**
	 * Obter o rótulo.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pBorder a borda
	 * @param pMargin a margem
	 * @param pAlign o alinhamento
	 * @return
	 */
	private JLabel getLabel(Border pBorder, Border pMargin, int pAlign) {
		JLabel label = new JLabel(" ");
		label.setBorder(new CompoundBorder(pBorder, pMargin));
		label.setHorizontalAlignment(pAlign);
		return label;
	}
	
	/**
	 * Obter a margem
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pTop a cima
	 * @param pLeft a esquerda
	 * @param pBottom a inferior
	 * @param pRight a direita
	 * @return margin a margem
	 */
	private Border getMargin(int pTop, int pLeft, int pBottom, int pRight) {
		return new EmptyBorder(pTop, pLeft, pBottom, pRight);
	}
	
	/**
	 * Definir o texto do rótulo.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pLabel o rótulo
	 * @param pText o texto
	 */
	private void setLabelText(JLabel pLabel, String pText) {
		pLabel.setText(pText != null && !pText.isEmpty() ? pText : " ");
	}
	
	/**
	 * Definir o texto da esquerdo.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pText o texto
	 */
	public void setLeftText(String pText) {
		setLabelText(leftLabel, pText);
	}
	
	/**
	 * Definir o texto do meio.
	 * @author Gugatb
	 * @date 01/07/2018 
	 * @param pText o texto
	 */
	public void setMiddleText(String pText) {
		setLabelText(middleLabel, pText);
	}
	
	/**
	 * Definir o texto da direita.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pText o texto
	 */
	public void setRightText(String pText) {
		setLabelText(rightLabel, pText);
	}
}
