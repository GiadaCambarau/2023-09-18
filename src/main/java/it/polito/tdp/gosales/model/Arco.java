package it.polito.tdp.gosales.model;

import java.util.Objects;

public class Arco {
	private Volume r1;
	private Volume r2;
	private int peso;
	
	public Arco(Volume r1, Volume r2, int peso) {
		super();
		this.r1 = r1;
		this.r2 = r2;
		this.peso = peso;
	}
	
	public Volume getR1() {
		return r1;
	}
	public void setR1(Volume r1) {
		this.r1 = r1;
	}
	public Volume getR2() {
		return r2;
	}
	public void setR2(Volume r2) {
		this.r2 = r2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(peso, r1, r2);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		return peso == other.peso && Objects.equals(r1, other.r1) && Objects.equals(r2, other.r2);
	}
	
	

}
