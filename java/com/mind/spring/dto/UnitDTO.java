package com.mind.spring.dto;

public class UnitDTO {
	private int unitId;
	private String unitName;

	public UnitDTO() {

	}

	public UnitDTO(int unitId, String unitName) {
		this.unitId = unitId;
		this.unitName = unitName;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}
