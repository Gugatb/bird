package com.dna.Bird.ui.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.dna.Bird.constant.AlleleName;
import com.dna.Bird.entity.Item;

public class AllelesWindow extends JFrame {
	private static final long serialVersionUID = -775669992576108222L;
	
	private JButton button;
	private Map<String, JTextField> fields;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 07/07/2018
	 * @param pTitle o título
	 */
	public AllelesWindow(String pTitle) {
		super(pTitle);
		
		fields = new HashMap<String, JTextField>();
		setForm();
		
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(275, 435);
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Obter o campo.
	 * @author Gugatb
	 * @date 07/07/2018
	 * @param pName o nome
	 * @return field o campo
	 */
	public JTextField getField(String pName) {
		return fields.get(pName);
	}
	
	/**
	 * Obter os campos.
	 * @author Gugatb
	 * @date 07/07/2018
	 * @return fields os campos
	 */
	public Map<String, JTextField> getFields() {
		return fields;
	}
	
	/**
	 * Obter o item
	 * @author Gugatb
	 * @date 09/07/2018
	 * @return item o item
	 */
	public Item getItem() {
		Map<String, String> map = new HashMap<String, String>();
		Item item = new Item();
		
		// Definir os alelos.
		for (AlleleName alleName : AlleleName.values()) {
			String name = alleName.getValue();
			
			map.put(name + "L", getField(name + "L").getText());
			map.put(name + "R", getField(name + "R").getText());
		}
		
		item.setMap(map);
		return item;
	}
	
	/**
	 * Definir o botão.
	 * @author Gugatb
	 * @date 07/07/2018
	 * @param pListener o ouvinte
	 * @param pText o texto
	 */
	public void setButton(ActionListener pListener, String pText) {
		this.button.addActionListener(pListener);
		this.button.setText(pText);
	}
	
	/**
	 * Definir o formulário.
	 * @author Gugatb
	 * @date 07/07/2018
	 */
	private void setForm() {
		// Definir os campos de alelos.
		for (int index = 0; index < AlleleName.values().length; index++) {
			String name = AlleleName.values()[index].getValue();
			int height = (index * 25) + 5;
			
			// Definir o rótulo.
			JLabel label = new JLabel(name + ":");
			label.setBounds(5, height, 50, 20);
			add(label);
			
			// Definir o lado esquerdo.
			JTextField field = new JTextField(50);
			field.setBounds(60, height, 100, 20);
			fields.put(name + "L", field);
			add(field);
			
			// Definir o lado direito.
			field = new JTextField(50);
			field.setBounds(165, height, 100, 20);
			fields.put(name + "R", field);
			add(field);
		}
		
		// Definir o botão cancelar.
		JButton cancel = new JButton("Cancelar");
		cancel.setBounds(60, 380, 100, 20);
		add(cancel);
		
		// Definir o ouvinte.
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent pEvent) {
				dispose();
			}
		});
		
		// Definir o botão.
		button = new JButton("");
		button.setBounds(165, 380, 100, 20);
		add(button);
	}
	
	/**
	 * Definir o item.
	 * @author Gugatb
	 * @date 09/07/2018
	 * @param pItem o item
	 */
	public void setItem(Item pItem) {
		// Definir os campos de alelos.
		for (AlleleName alleName : AlleleName.values()) {
			String name = alleName.getValue();
			
			fields.get(name + "L").setText(pItem.getMap().get(name + "L"));
			fields.get(name + "R").setText(pItem.getMap().get(name + "R"));
		}
	}
}
