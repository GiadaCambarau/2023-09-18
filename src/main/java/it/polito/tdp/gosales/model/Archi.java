package it.polito.tdp.gosales.model;

import java.util.Objects;
import java.util.Set;

public class Archi {
	private Retailers r ;
	private Set<Integer> prodotti;
	
	public Archi(Retailers r, Set<Integer> prodotti) {
		super();
		this.r = r;
		this.prodotti = prodotti;
	}

	public Retailers getR() {
		return r;
	}

	public void setR(Retailers r) {
		this.r = r;
	}

	public Set<Integer> getProdotti() {
		return prodotti;
	}

	public void setProdotti(Set<Integer> prodotti) {
		this.prodotti = prodotti;
	}

	@Override
	public int hashCode() {
		return Objects.hash(prodotti, r);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Archi other = (Archi) obj;
		return Objects.equals(prodotti, other.prodotti) && Objects.equals(r, other.r);
	}
	
	

}
