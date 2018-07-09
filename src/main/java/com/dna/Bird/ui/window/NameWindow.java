package com.dna.Bird.ui.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NameWindow extends JFrame {
	private static final long serialVersionUID = -775669992576108222L;
	
	private JButton button;
	private Map<String, JTextField> fields;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 09/07/2018
	 * @param pTitle o título
	 */
	public NameWindow(String pTitle) {
		super(pTitle);
		
		fields = new HashMap<String, JTextField>();
		setForm();
		
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(275, 85);
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Obter o campo.
	 * @author Gugatb
	 * @date 09/07/2018
	 * @param pName o nome
	 * @return field o campo
	 */
	public JTextField getField(String pName) {
		return fields.get(pName);
	}
	
	/**
	 * Obter os campos.
	 * @author Gugatb
	 * @date 09/07/2018
	 * @return fields os campos
	 */
	public Map<String, JTextField> getFields() {
		return fields;
	}
	
	/**
	 * Definir o botão.
	 * @author Gugatb
	 * @date 09/07/2018
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
	 * @date 09/07/2018
	 */
	private void setForm() {
		JLabel label = new JLabel("Nome:");
		label.setBounds(5, 5, 50, 20);
		add(label);
		
		// Definir o campo de nome.
		JTextField field = new JTextField(200);
		field.setBounds(60, 5, 205, 20);
		fields.put("name", field);
		add(field);
		
		// Definir o botão cancelar.
		JButton cancel = new JButton("Cancelar");
		cancel.setBounds(60, 30, 100, 20);
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
		button.setBounds(165, 30, 100, 20);
		add(button);
	}
}
