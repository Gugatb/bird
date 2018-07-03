package com.dna.Bird;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.dna.Bird.entity.Item;
import com.dna.Bird.ui.Window;

@SuppressWarnings("unused")
public class App {
	private static Window window;
	
	public static void main(String[] args) {
		try {
			List<Item> items = new ArrayList<Item>();
			File f = new File("data.ini");
			f.createNewFile();
//			f.delete();
			
			Ini iniR = new Ini();
			iniR.load(new FileReader("data.ini"));
			
			Wini iniW = new Wini(new File("data.ini"));
			
			Item item = new Item();
//			for (Entry<String, String> entry : item.getMap().entrySet()) {
//				iniW.put("Teste", entry.getKey(), entry.getValue());
//			}
//			iniW.store();
			
			Set<String> sectionNames = iniR.keySet();
//			ini.put("Teste2", "param", "Teste");
//			ini.put("Secao", "Oa2L", "6");
			
			for (String name : sectionNames) {
//				Ini.Section section = ini.get(name);
				Map<String, String> map = iniR.get(name);
				
//				ini.put(name, "param", "Teste");
				
				item = new Item();
				item.setName(name);
				item.setMap(map);
				items.add(item);
				
				for (Entry<String, String> entry : map.entrySet()) {
					System.err.println(entry.getKey() + " = " + entry.getValue());
				}
			}
			
			window = new Window("Pássaros");
			window.setTableItems(items);
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
}

