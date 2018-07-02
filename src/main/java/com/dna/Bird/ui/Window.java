package com.dna.Bird.ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.dna.Bird.constant.ColumnName;
import com.dna.Bird.entity.Item;

public class Window extends JFrame {
	private static final long serialVersionUID = -2908442023007904391L;
	
//	private Menu menu;
	
	private StatusBar statusBar;
//	private ToolBar toolBar;
	
	private Table table;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pTitle o título
	 */
	public Window(String pTitle) {
		super(pTitle);
		setStatusBar();
//		setToolBar();
//		
//		menu = new Menu(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setJMenuBar(menu);
		this.setSize(1024, 800);
		this.setVisible(true);
	}
	
	/**
	 * Obter a barra de situação.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @return statusBar a barra de situação
	 */
	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	/**
	 * Remover a tabela.
	 * @author Gugatb
	 * @date 01/07/2018
	 */
	private void removeTable() {
		if (table != null) {
			getContentPane().removeAll();
		}
	}
	
	/**
	 * Definir a barra de situação.
	 * @author Gugatb
	 * @date 01/07/2018
	 */
	private void setStatusBar() {
		statusBar = statusBar != null ? statusBar : new StatusBar();
		getContentPane().add(statusBar, BorderLayout.SOUTH);
	}
	
	/**
	 * Definir a tabela.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pPanel o painel
	 */
	private void setTable(JScrollPane pPanel) {
		getContentPane().add(pPanel, BorderLayout.CENTER);
		setStatusBar();
//		setToolBar();
		revalidate();
		repaint();
	}
	
	/**
	 * Definir os itens da tabela.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pItems os itens
	 */
	public void setTableItems(List<Item> pItems) {
		removeTable();
		
		table = new Table(
			ColumnName.BASIC.getValue(),
			new Table().getRows(pItems)
		);
		
		setTable(table.getScrollPanel());
	}
}
