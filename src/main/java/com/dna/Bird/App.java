package com.dna.Bird;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import com.dna.Bird.ui.Window;

@SuppressWarnings("unused")
public class App {
	private static Window window;
	
	public static void main(String[] args) {
		try {
			window = new Window("DNA");
			
			File yourFile = new File("data.ini");
			yourFile.createNewFile();
			
			Ini ini = new Ini();
			File file = new File("data.ini");
			ini.load(new FileReader(file));
			
			Set<String> sectionNames = ini.keySet();
			ini.put("Teste", "param", "Teste");
			
			for (String name : sectionNames) {
//				Ini.Section section = ini.get(name);
				Map<String, String> map = ini.get(name);
				
//				ini.put(name, "param", "Teste");
				
				for (Entry<String, String> entry : map.entrySet()) {
					System.err.println(entry.getKey() + " = " + entry.getValue());
				}
			}
		}
		catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

