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
		for (AlleleName alleleName : AlleleName.values()) {
			String name = alleleName.getValue();
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
	 * Obter o mapa.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pMap o mapa
	 */
	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		for (int index = 0; index < AlleleName.values().length; index++) {
			String name = AlleleName.values()[index].getValue();
			
			if (alleles.containsKey(name)) {
				Allele allele = alleles.get(name);
				map.put(name + "L", String.valueOf(allele.getLeft()));
				map.put(name + "R", String.valueOf(allele.getRight()));
			}
			else {
				map.put(name + "L", "0");
				map.put(name + "R", "0");
			}
		}
		return map;
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
		for (AlleleName alleleName : AlleleName.values()) {
			String name = alleleName.getValue();
			
			if (alleles.containsKey(name)) {
				Allele allele = alleles.get(name);
				
				// Definir o lado esquerdo.
				allele.setLeft(
					pMap.get(name + "L") != null && !pMap.get(name + "L").isEmpty() ?
					Integer.valueOf(pMap.get(name + "L")) : 0
				);
				
				// Definir o lado direito.
				allele.setRight(
					pMap.get(name + "R") != null && !pMap.get(name + "R").isEmpty() ?
					Integer.valueOf(pMap.get(name + "R")) : 0
				);
				alleles.put(name, allele);
			}
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
