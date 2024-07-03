package it.polito.tdp.gosales.model;

import java.util.Objects;

public class Volume  implements Comparable <Volume>{
	
	private Retailers  r;
	private int volume;
	public Volume(Retailers r, int volume) {
		super();
		this.r = r;
		this.volume = volume;
	}
	public Retailers getR() {
		return r;
	}
	public void setR(Retailers r) {
		this.r = r;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	@Override
	public int hashCode() {
		return Objects.hash(r, volume);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Volume other = (Volume) obj;
		return Objects.equals(r, other.r) && volume == other.volume;
	}
	@Override
	public int compareTo(Volume o) {
		
		return o.volume- this.volume;
	}
	@Override
	public String toString() {
		return  r + " ---> " + volume ;
	}
	
	
	
	
}
