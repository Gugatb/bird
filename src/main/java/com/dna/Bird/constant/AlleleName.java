package com.dna.Bird.constant;

public enum AlleleName {
	OA_2("OA_2", "Oa2"),
	OA_7("OA_7", "Oa7"),
	OA_26("OA_26", "Oa26"),
	OA_35("OA_35", "Oa35"),
	UN_5("UN_5", "UN5"),
	UN_7("UN_7", "UN7"),
	UN_10("UN_10", "UN10"),
	UN_13("UN_13", "UN13"),
	UN_14("UN_14", "UN14"),
	UN_15("UN_15", "UN15"),
	UN_19("UN_19", "UN19"),
	UN_21("UN_21", "UN21"),
	UN_30("UN_30", "UN30"),
	UN_34("UN_34", "UN34"),
	UN_38("UN_38", "UN38");
	
	// Valor padrão.
	public final static AlleleName DEFAULT = OA_2;
	
	private String name;
	private String value;
	
	/**
	 * Contrutor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pName o nome
	 * @param pValue o valor
	 */
	AlleleName(String pName, String pValue) {
		this.name = pName;
		this.value = pValue;
	}
	
	/**
	 * Verificar se contém o valor.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pValue o valor
	 * @return true se contém, caso contrário false
	 */
	public boolean contains(String pValue) {
		boolean contains = false;
		
		for (AlleleName item : AlleleName.values()) {
			if (item.getValue().equals(pValue)) {
				contains = true;
			}
		}
		return contains;
	}
	
	/**
	 * Obter a constante.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pName o nome
	 * @return constant a constante
	 */
	public AlleleName getConstant(String pName) {
		AlleleName constant = null;
		
		for (AlleleName item : AlleleName.values()) {
			if (item.getName().equals(pName)) {
				constant = item;
			}
		}
		return constant;
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
	 * Obter o valor.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @return value o valor
	 */
	public String getValue() {
		return value;
	}
}
