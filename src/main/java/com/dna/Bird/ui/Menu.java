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

import com.dna.Bird.constant.AlleleName;
import com.dna.Bird.constant.Api;
import com.dna.Bird.entity.Item;
import com.dna.Bird.ui.window.AlleleWindow;
import com.dna.Bird.ui.window.AllelesWindow;
import com.dna.Bird.ui.window.BirdWindow;
import com.dna.Bird.ui.window.MainWindow;
import com.dna.Bird.ui.window.NameWindow;

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
		
		JMenu search = new JMenu("Buscar");
		search.setMnemonic(KeyEvent.VK_B);
		
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
		
		JMenuItem cleanItem = new JMenuItem("Limpar");
		cleanItem.setMnemonic(KeyEvent.VK_L);
		cleanItem.setToolTipText("Limpar a busca");
		cleanItem.addActionListener((ActionEvent event) -> {
			setTableItems(pParent);
		});
		
		JMenuItem editItem = new JMenuItem("Editar");
		editItem.setMnemonic(KeyEvent.VK_E);
		editItem.setToolTipText("Editar o DNA");
		editItem.addActionListener((ActionEvent event) -> {
			edit(pParent);
		});
		
		JMenuItem exitItem = new JMenuItem("Sair");
		exitItem.setMnemonic(KeyEvent.VK_S);
		exitItem.setToolTipText("Sair da aplicação");
		exitItem.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});
		
		JMenuItem removeItem = new JMenuItem("Remover");
		removeItem.setMnemonic(KeyEvent.VK_R);
		removeItem.setToolTipText("Remover o DNA");
		removeItem.addActionListener((ActionEvent event) -> {
			remove(pParent);
		});
		
		JMenuItem searchByAlleleItem = new JMenuItem("Alelo");
		searchByAlleleItem.setMnemonic(KeyEvent.VK_N);
		searchByAlleleItem.setToolTipText("Buscar por alelo");
		searchByAlleleItem.addActionListener((ActionEvent event) -> {
			searchByAllele(pParent);
		});
		
		JMenuItem searchByAllelesItem = new JMenuItem("Alelos");
		searchByAllelesItem.setMnemonic(KeyEvent.VK_N);
		searchByAllelesItem.setToolTipText("Buscar por alelos");
		searchByAllelesItem.addActionListener((ActionEvent event) -> {
			searchByAlleles(pParent);
		});
		
		JMenuItem searchByNameItem = new JMenuItem("Nome");
		searchByNameItem.setMnemonic(KeyEvent.VK_N);
		searchByNameItem.setToolTipText("Buscar por nome");
		searchByNameItem.addActionListener((ActionEvent event) -> {
			searchByName(pParent);
		});
		
		dna.add(addItem);
		dna.add(editItem);
		dna.add(removeItem);
		dna.addSeparator();
		dna.add(exitItem);
		add(dna);
		
		search.add(searchByAlleleItem);
		search.add(searchByAllelesItem);
		search.add(searchByNameItem);
		search.addSeparator();
		search.add(cleanItem);
		add(search);
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
	 * Compara os valores de mapas.
	 * @author Gugatb
	 * @date 09/07/2018
	 * @param pMap1 o mapa
	 * @param pMap2 o mapa
	 * @param pName1 o nome
	 * @param pName2 o nome
	 * @return result o resultado
	 */
	private boolean compareTo(Map<String, String> pMap1, Map<String, String> pMap2, String pName1, String pName2) {
		return pMap1.get(pName1) != null && !pMap1.get(pName1).equals("0") &&
		pMap2.get(pName2) != null && !pMap2.get(pName2).equals("0") &&
		pMap1.get(pName1).compareTo(pMap2.get(pName2)) == 0;
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
	 * Obter os itens.
	 * @author Gugatb
	 * @date 09/07/2018
	 * @return items os itens
	 */
	public List<Item> getItems() {
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
		return items;
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
			
			// Obter as linhas selecionadas.
			int[] rows = pParent.getTable().getSelectedRows();
			
			if (rows.length > 0) {
				Wini writer = new Wini(file);
				
				// Remover as linhas selecionadas.
				for (int row : rows) {
					Object object = pParent.getTable().getModel().getValueAt(row, 0);
					String name = object.toString();
					writer.remove(name);
					writer.store();
				}
				
				// Atualizar a tabela.
				setTableItems(pParent);
			}
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Buscar por alelo.
	 * @author Gugatb
	 * @date 09/07/2018
	 * @param pParent o parente
	 */
	public void searchByAllele(MainWindow pParent) {
		List<Item> match = new ArrayList<Item>();
		List<Item> items = getItems();
		
		if (items.size() > 0) {
			final AlleleWindow window = new AlleleWindow("Buscar por alelo");
			window.setButton(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String allele = window.getField("allele").getText();
					
					// Buscar os itens.
					for (Item item : items) {
						for (String value : item.getMap().values()) {
							if (allele != null && value != null &&
								allele.compareTo(value) == 0) {
								item.setSimilarity(item.getSimilarity() + 1);
							}
						}
						
						if (item.getSimilarity() > 0) {
							match.add(item);
						}
					}
					
					// Definir os itens da tabela.
					pParent.setTableItems(match);
					window.dispose();
				}
			}, "Buscar");
			
			// Exibir a janela.
			window.setVisible(true);
		}
	}
	
	/**
	 * Buscar por alelos.
	 * @author Gugatb
	 * @date 09/07/2018
	 * @param pParent o parente
	 */
	public void searchByAlleles(MainWindow pParent) {
		List<Item> match = new ArrayList<Item>();
		List<Item> items = getItems();
		
		if (items.size() > 0) {
			final AllelesWindow window = new AllelesWindow("Buscar por alelos");
			window.setButton(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Item entity = window.getItem();
					
					// Buscar os itens.
					for (Item item : items) {
						for (AlleleName alleleName : AlleleName.values()) {
							String name = alleleName.getValue();
							Map<String, String> map1 = entity.getMap();
							Map<String, String> map2 = item.getMap();
							
							if (compareTo(map1, map2, name + "L", name + "L")) {
								item.setSimilarity(item.getSimilarity() + 1);
							}
							if (compareTo(map1, map2, name + "L", name + "R")) {
								item.setSimilarity(item.getSimilarity() + 1);
							}
							if (compareTo(map1, map2, name + "R", name + "R")) {
								item.setSimilarity(item.getSimilarity() + 1);
							}
							if (compareTo(map1, map2, name + "R", name + "L")) {
								item.setSimilarity(item.getSimilarity() + 1);
							}
						}
						
						if (item.getSimilarity() > 0) {
							match.add(item);
						}
					}
					
					// Definir os itens da tabela.
					pParent.setTableItems(match);
					window.dispose();
				}
			}, "Buscar");
			
			// Exibir a janela.
			window.setVisible(true);
		}
	}
	
	/**
	 * Buscar por nome.
	 * @author Gugatb
	 * @date 09/07/2018
	 * @param pParent o parente
	 */
	public void searchByName(MainWindow pParent) {
		List<Item> match = new ArrayList<Item>();
		List<Item> items = getItems();
		
		if (items.size() > 0) {
			final NameWindow window = new NameWindow("Buscar por nome");
			window.setButton(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = window.getField("name").getText();
					
					// Buscar os itens.
					for (Item item : items) {
						if (name != null && item.getName().toUpperCase().contains(name.toUpperCase())) {
							match.add(item);
						}
					}
					
					// Definir os itens da tabela.
					pParent.setTableItems(match);
					window.dispose();
				}
			}, "Buscar");
			
			// Exibir a janela.
			window.setVisible(true);
		}
	}
	
	/**
	 * Definir os itens da tabela.
	 * @author Gugatb
	 * @date 07/07/2018
	 * @param pParent o parente
	 */
	public void setTableItems(MainWindow pParent) {
		List<Item> items = getItems();
		pParent.setTableItems(items);
	}
}
