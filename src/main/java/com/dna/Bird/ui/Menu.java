package com.dna.Bird.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JMenuBar {
	private static final long serialVersionUID = -8486603728139625216L;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pParent o parente
	 */
	public Menu(JFrame pParent) {
		JMenu dna = new JMenu("DNA");
		dna.setMnemonic(KeyEvent.VK_D);
		
		JMenuItem addItem = new JMenuItem("Adicionar");
		addItem.setMnemonic(KeyEvent.VK_A);
		addItem.setToolTipText("Adicionar o DNA");
		addItem.addActionListener((ActionEvent event) -> {
			final JDialog dialog = new JDialog(pParent, "Título", true);
			JPanel panel = new JPanel(new GridLayout(5, 4));
			panel.add(new JLabel("Rótulo:"));
			
			JTextField textField = new JTextField(20);
			panel.add(textField);
			
			dialog.setContentPane(panel);
			dialog.pack();
			dialog.setVisible(true);
		});
		
		JMenuItem removeItem = new JMenuItem("Remover");
		removeItem.setMnemonic(KeyEvent.VK_R);
		removeItem.setToolTipText("Remover o DNA");
		removeItem.addActionListener((ActionEvent event) -> {
			
		});
		
		JMenuItem exitItem = new JMenuItem("Sair");
		exitItem.setMnemonic(KeyEvent.VK_S);
		exitItem.setToolTipText("Sair da aplicação");
		exitItem.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});
		
		dna.add(addItem);
		dna.add(removeItem);
		dna.addSeparator();
		dna.add(exitItem);
		add(dna);
	}
}
