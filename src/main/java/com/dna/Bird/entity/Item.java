package com.dna.Bird.entity;

import java.util.HashMap;
import java.util.Map;

import com.dna.Bird.constant.AlleleName;

public class Item {
	private Map<String, Allele> alleles;
	private String name;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 */
	public Item() {
		this.alleles = new HashMap<String, Allele>();
		this.name = "";
		
		// Instânciar os alelos.
		for (int index = 0; index < AlleleName.values().length; index++) {
			String name = AlleleName.values()[index].getValue();
			Allele allele = new Allele();
			allele.setName(name);
			alleles.put(name, allele);
		}
	}
	
	/**
	 * Obter os alelos.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @return alleles os alelos
	 */
	public Map<String, Allele> getAlleles() {
		return alleles;
	}
	
	/**
	 * Obter o nome.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @return name o nome
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Definir os alelos.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pAlleles os alelos
	 */
	public void setAlleles(Map<String, Allele> pAlleles) {
		this.alleles = pAlleles;
	}
	
	/**
	 * Definir o mapa.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pMap o mapa
	 */
	public void setMap(Map<String, String> pMap) {
		for (int index = 0; index < AlleleName.values().length; index++) {
			String name = AlleleName.values()[index].getValue();
			
			
			Allele allele = new Allele();
			allele.setName(name);
			alleles.put(name, allele);
		}
	}
	
	/**
	 * Definir o nome.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pName o nome
	 */
	public void setName(String pName) {
		this.name = pName;
	}
}
