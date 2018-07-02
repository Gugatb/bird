package com.dna.Bird.entity;

import java.util.ArrayList;
import java.util.List;

import com.dna.Bird.constant.AlleleName;

public class Item {
	private List<Allele> alleles;
	private String name;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 */
	public Item() {
		this.alleles = new ArrayList<Allele>();
		this.name = "";
		
		// Instânciar os alelos.
		for (int index = 0; index < AlleleName.values().length; index++) {
			String name = AlleleName.values()[index].getValue();
			Allele allele = new Allele();
			allele.setName(name);
			alleles.add(allele);
		}
	}
	
	/**
	 * Obter os alelos.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @return alleles os alelos
	 */
	public List<Allele> getAlleles() {
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
	public void setAlleles(List<Allele> pAlleles) {
		this.alleles = pAlleles;
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
