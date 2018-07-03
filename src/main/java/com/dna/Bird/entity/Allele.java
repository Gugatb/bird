package com.dna.Bird.entity;

public class Allele {
	private int left;
	private String name;
	private int right;
	
	/**
	 * Construtor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 */
	public Allele() {
		this.left = 0;
		this.name = "";
		this.right = 0;
	}
	
	/**
	 * Obter o lado esquerdo.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @return left o lado esquerdo
	 */
	public int getLeft() {
		return left;
	}
	
	/**
	 * Obter o lado esquerdo.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @return left o lado esquerdo
	 */
	public String getLeftText() {
		return left > 0 ? String.valueOf(left) : "";
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
	 * Obter o lado direito.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @return right o lado direito
	 */
	public int getRight() {
		return right;
	}
	
	/**
	 * Obter o lado direito.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @return right o lado direito
	 */
	public String getRightText() {
		return right > 0 ? String.valueOf(right) : "";
	}
	
	/**
	 * Definir o lado esquerdo.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pLeft o lado direito
	 */
	public void setLeft(int pLeft) {
		this.left = pLeft;
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
	
	/**
	 * Definir o lado direito.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pRight o lado direito
	 */
	public void setRight(int pRight) {
		this.right = pRight;
	}
}
