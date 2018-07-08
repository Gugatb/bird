package com.dna.Bird.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.dna.Bird.constant.Api;
import com.dna.Bird.entity.Item;
import com.dna.Bird.ui.window.BirdWindow;
import com.dna.Bird.ui.window.MainWindow;

public class Menu extends JMenuBar {
	private static final long serialVersionUID = -8486603728139625216L;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pParent o parente
	 */
	public Menu(MainWindow pParent) {
		JMenu dna = new JMenu("DNA");
		dna.setMnemonic(KeyEvent.VK_D);
		
		JMenuItem addItem = new JMenuItem("Adicionar");
		addItem.setMnemonic(KeyEvent.VK_A);
		addItem.setToolTipText("Adicionar o DNA");
		addItem.addActionListener((ActionEvent event) -> {
			final BirdWindow window = new BirdWindow("Adicionar");
			window.setButton(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					add(pParent, window);
				}
			}, "Salvar");
			
			window.setItem(new Item());
			window.setVisible(true);
		});
		
		JMenuItem editItem = new JMenuItem("Editar");
		editItem.setMnemonic(KeyEvent.VK_E);
		editItem.setToolTipText("Editar o DNA");
		editItem.addActionListener((ActionEvent event) -> {
			edit(pParent);
		});
		
		JMenuItem removeItem = new JMenuItem("Remover");
		removeItem.setMnemonic(KeyEvent.VK_R);
		removeItem.setToolTipText("Remover o DNA");
		removeItem.addActionListener((ActionEvent event) -> {
			remove(pParent);
		});
		
		JMenuItem exitItem = new JMenuItem("Sair");
		exitItem.setMnemonic(KeyEvent.VK_S);
		exitItem.setToolTipText("Sair da aplicação");
		exitItem.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});
		
		dna.add(addItem);
		dna.add(editItem);
		dna.add(removeItem);
		dna.addSeparator();
		dna.add(exitItem);
		add(dna);
	}
	
	/**
	 * Adicionar o DNA.
	 * @author Gugatb
	 * @date 07/07/2018
	 * @param pParent o parente
	 * @param pWindow a janela
	 */
	public void add(MainWindow pParent, BirdWindow pWindow) {
		SimpleDateFormat formater = new SimpleDateFormat(Api.DATE_FORMAT.getValue());
		
		try {
			// Criar o arquivo, se não existir.
			File file = new File(Api.DATA.getValue());
			file.createNewFile();
			
			// Adicionar o DNA.
			Wini writer = new Wini(file);
			Item item = pWindow.getItem();
			for (Entry<String, String> entry : item.getMap().entrySet()) {
				writer.put(item.getName(), entry.getKey(), entry.getValue());
			}
			
			// Armazenar o DNA.
			writer.put(item.getName(), "Date", formater.format(item.getDate()));
			writer.store();
			
			// Atualizar a tabela.
			setTableItems(pParent);
			pWindow.dispose();
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Editar o DNA.
	 * @author Gugatb
	 * @date 08/07/2018
	 * @param pParent o parente
	 */
	public void edit(MainWindow pParent) {
		try {
			// Obter a linha selecionada.
			int row = pParent.getTable().getSelectedRow();
			
			if (row >= 0) {
				Object object = pParent.getTable().getModel().getValueAt(row, 0);
				String name = object.toString();
				
				// Definir o leitor.
				Ini reader = new Ini();
				reader.load(new FileReader(Api.DATA.getValue()));
				
				// Ler a seção.
				Item item = new Item();
				item.setName(name);
				item.setMap(reader.get(name));
				
				final BirdWindow window = new BirdWindow("Editar");
				window.getField("name").setEditable(false);
				window.setButton(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						edit(pParent, window);
					}
				}, "Salvar");
				
				window.setItem(item);
				window.setVisible(true);
			}
		}
		catch (InvalidFileFormatException exception) {
			exception.printStackTrace();
		}
		catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Editar o DNA.
	 * @author Gugatb
	 * @date 07/07/2018
	 * @param pParent o parente
	 * @param pWindow a janela
	 */
	public void edit(MainWindow pParent, BirdWindow pWindow) {
		try {
			// Criar o arquivo, se não existir.
			File file = new File(Api.DATA.getValue());
			file.createNewFile();
			
			// Adicionar o DNA.
			Wini writer = new Wini(file);
			Item item = pWindow.getItem();
			for (Entry<String, String> entry : item.getMap().entrySet()) {
				writer.put(item.getName(), entry.getKey(), entry.getValue());
			}
			writer.store();
			
			// Atualizar a tabela.
			setTableItems(pParent);
			pWindow.dispose();
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Remover o DNA.
	 * @author Gugatb
	 * @date 08/07/2018
	 * @param pParent o parente
	 */
	public void remove(MainWindow pParent) {
		try {
			// Criar o arquivo, se não existir.
			File file = new File(Api.DATA.getValue());
			file.createNewFile();
			
			// Obter a linha selecionada.
			int row = pParent.getTable().getSelectedRow();
			
			if (row >= 0) {
				Object object = pParent.getTable().getModel().getValueAt(row, 0);
				String name = object.toString();
				
				// Remover o DNA.
				Wini writer = new Wini(file);
				writer.remove(name);
				writer.store();
				
				// Atualizar a tabela.
				setTableItems(pParent);
			}
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Definir os itens da tabela.
	 * @author Gugatb
	 * @date 07/07/2018
	 * @param pParent o parente
	 */
	public void setTableItems(MainWindow pParent) {
		SimpleDateFormat formater = new SimpleDateFormat(Api.DATE_FORMAT.getValue());
		List<Item> items = new ArrayList<Item>();
		
		try {
			// Definir o leitor.
			Ini reader = new Ini();
			reader.load(new FileReader(Api.DATA.getValue()));
			
			// Ler as seções.
			Set<String> sectionNames = reader.keySet();
			for (String name : sectionNames) {
				Map<String, String> map = reader.get(name);
				Item item = new Item();
				item.setDate(formater.parse(map.get("Date")));
				item.setName(name);
				item.setMap(map);
				items.add(item);
			}
			pParent.setTableItems(items);
		}
		catch (InvalidFileFormatException exception) {
			exception.printStackTrace();
		}
		catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
		catch (ParseException exception) {
			exception.printStackTrace();
		}
	}
}
