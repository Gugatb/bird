package com.dna.Bird.ui.window;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.dna.Bird.constant.ColumnName;
import com.dna.Bird.entity.Item;
import com.dna.Bird.ui.Menu;
import com.dna.Bird.ui.StatusBar;
import com.dna.Bird.ui.Table;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -2908442023007904391L;
	
	private Menu menu;
	private StatusBar statusBar;
	
	private Table table;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pTitle o título
	 */
	public MainWindow(String pTitle) {
		super(pTitle);
		setStatusBar();
		
		menu = new Menu(this);
		menu.setTableItems(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(menu);
		this.setSize(1024, 800);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
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
	 * Obter a tabela.
	 * @author Gugatb
	 * @date 07/07/2018
	 * @return table a tabela
	 */
	public Table getTable() {
		return table;
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
		
		// Ordenar os itens.
		Collections.sort(pItems, new Comparator<Item>() {
			public int compare(Item pItem1, Item pItem2) {
				int result = pItem1.getSimilarity().compareTo(pItem2.getSimilarity());
				
				if (result == 0) {
					result = pItem1.getName().compareToIgnoreCase(pItem2.getName());
				}
				else {
					result = -result;
				}
				return result;
			}
		});
		
		// Definir a tabela.
		table = new Table(
			ColumnName.BASIC.getValue(),
			new Table().getRows(pItems)
		);
		
		// Definir o ouvinte de duplo click na tabela.
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent pEvent) {
				if (pEvent.getClickCount() == 2) {
					menu.edit(MainWindow.this);
				}
			}
		});
		
		setTable(table.getScrollPanel());
	}
}
