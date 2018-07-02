package com.dna.Bird.constant;

public enum ColumnName {
	BASIC("BASIC", new Object[] {
		"Nome",
		AlleleName.OA_2.getValue(),
		AlleleName.OA_7.getValue(),
		AlleleName.OA_26.getValue(),
		AlleleName.OA_35.getValue(),
		AlleleName.UN_5.getValue(),
		AlleleName.UN_7.getValue(),
		AlleleName.UN_10.getValue(),
		AlleleName.UN_13.getValue(),
		AlleleName.UN_14.getValue(),
		AlleleName.UN_15.getValue(),
		AlleleName.UN_19.getValue(),
		AlleleName.UN_21.getValue(),
		AlleleName.UN_30.getValue(),
		AlleleName.UN_34.getValue(),
		AlleleName.UN_38.getValue()
	});
	
	// Valor padrão.
	public final static ColumnName DEFAULT = BASIC;
	
	private String name;
	private Object[] value;
	
	/**
	 * Contrutor da classe.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pName o nome
	 * @param pValue o valor
	 */
	ColumnName(String pName, Object[] pValue) {
		this.name = pName;
		this.value = pValue;
	}
	
	/**
	 * Obter a constante.
	 * @author Gugatb
	 * @date 01/07/2018
	 * @param pName o nome
	 * @return constant a constante
	 */
	public ColumnName getConstant(String pName) {
		ColumnName constant = null;
		
		for (ColumnName item : ColumnName.values()) {
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
	public Object[] getValue() {
		return value;
	}
}
