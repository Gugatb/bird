package com.dna.Bird.ui;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.dna.Bird.constant.AlleleName;
import com.dna.Bird.constant.Api;
import com.dna.Bird.entity.Item;

public class Table extends JTable {
	private static final long serialVersionUID = 7821040413456877940L;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 */
	public Table() { }
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pColumnNames os nomes das colunas
	 * @param pRowData os dados das linhas
	 */
	public Table(Object[] pColumnNames, String[][] pRowData) {
		super(pRowData, pColumnNames);
		setAlignment();
		setAutoCreateRowSorter(true);
//		setCellSelectionEnabled(true);
//		setRowSelectionAllowed(true);
	}
	
	/**
	 * Obter as linhas.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pItems os itens
	 * @return rows as linhas
	 */
	public String[][] getRows(List<Item> pItems) {
		SimpleDateFormat formater = new SimpleDateFormat(Api.DATE_FORMAT.getValue());
		AlleleName[] names = AlleleName.values();
		String[][] rows = new String[pItems.size()][names.length + 2];
		
		for (int index = 0; index < pItems.size(); index++) {
			rows[index][names.length + 1] = formater.format(pItems.get(index).getDate());
			rows[index][0] = pItems.get(index).getName();
			int index2 = 0;
			
			for (AlleleName name : names) {
				String content = "";
				String leftText = pItems.get(index).getAlleles().get(name.getValue()).getLeftText();
				String rightText = pItems.get(index).getAlleles().get(name.getValue()).getRightText();
				content += leftText;
				content += !leftText.isEmpty() && !rightText.isEmpty() ? " - " : "";
				content += rightText;
				rows[index][index2 + 1] = content;
				index2++;
			}
		}
		return rows;
	}
	
	/**
	 * Obter o painel.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @return panel o painel
	 */
	public JScrollPane getScrollPanel() {
		JScrollPane panel = new JScrollPane(this);
		panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return panel;
	}
	
	@Override
	public boolean isCellEditable(int pRow, int pCol) {
		return false;
	}
	
	/**
	 * Definir o alinhamento.
	 * @author Gugatb
	 * @date 03/07/2018
	 */
	public void setAlignment() {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		for (int index = 0; index < AlleleName.values().length; index++) {
			getColumnModel().getColumn(index + 1).setCellRenderer(centerRenderer);
		}
	}
}
