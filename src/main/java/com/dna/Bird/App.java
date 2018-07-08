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
import com.dna.Bird.ui.window.MainWindow;

@SuppressWarnings("unused")
public class App {
	private static MainWindow window;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pArguments os argumentos
	 */
	public static void main(String[] pArguments) {
		window = new MainWindow("Pássaros");
	}
}

