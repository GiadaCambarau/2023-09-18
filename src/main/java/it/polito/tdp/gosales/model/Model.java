package it.polito.tdp.gosales.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.gosales.dao.GOsalesDAO;

public class Model {
	private GOsalesDAO dao;
	private List<String> nazioni;
	private Graph<Retailers, DefaultWeightedEdge> grafo;
	private List<Retailers>best;
	private int max;
	private List<Volume> volumi;
	
	
	public Model() {
		this.dao = new GOsalesDAO();
		this.nazioni = dao.getNazioni();
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}
	
	
	
	public List<String> getNazioni(){
		return this.nazioni;
	}
	
	public void creaGrafo(String nazione, int anno) {
		
		List<Retailers> vertici = dao.getVertici(nazione);
		
		Graphs.addAllVertices(this.grafo, vertici);
		
		List<Archi> archi  = dao.getProdottiVenduti(anno, nazione);
		for (Archi a1 : archi ) {
			for (Archi a2: archi) {
				if (!a1.equals(a2)) {
					Set<Integer> prodottia1 = a1.getProdotti();
					Set<Integer> prodottia2 = a2.getProdotti();
					Set<Integer> comuni = new HashSet<>(prodottia1);
					comuni.retainAll(prodottia2);
					if (comuni.size()>0) {
						Graphs.addEdgeWithVertices(this.grafo, a1.getR(), a2.getR(), comuni.size());
					}
				}
			}
		}
		System.out.println("#Vertici: "+ this.grafo.vertexSet().size());
		System.out.println("#Archi: "+ this.grafo.edgeSet().size());
		
	}
	
	public int getV() {
		return this.grafo.vertexSet().size();
	}
	public int getA() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Volume> calcolaVolume() {
		this.volumi = new ArrayList<>();
		for (Retailers r : this.grafo.vertexSet()) {
			int peso =0;
			Set<DefaultWeightedEdge> incoming = this.grafo.incomingEdgesOf(r);
			for (DefaultWeightedEdge e : incoming) {
				peso+= this.grafo.getEdgeWeight(e);
				
			}
			Volume v = new Volume(r,peso);
			v.setVolume(peso);
			if (peso>0) {
				volumi.add(v);
			}
		}
		Collections.sort(volumi);
		return volumi;
		
	}
	//il percorso deve essere chiuso
	//somma pesi archi massima
	//i vertici intermedi non si posssono ripetere
/*	public List<Arco> calcolaPercorso(int n) {
		this.best = new ArrayList<>();
		this.max=0;
		List<Volume> v = new ArrayList<>(this.volumi);
		//prendo solo gli archi connessi
		for (Volume v1: v) {
			List<Volume> parziale = new ArrayList<>();
			parziale.add(v1);
			doRicorsione(parziale, n , new ArrayList<Arco>());
		}
		return this.best;
	}



	private void doRicorsione(List<Volume> parziale, int n, ArrayList<Arco> parzialeArchi) {
		Volume primo = parziale.get(0);
		
		if (parziale.size() == n) {
			Volume ultimo = parziale.get(parziale.size()-1);
			if (primo.equals(ultimo)) {
				if (calcolaPeso(parzialeArchi)>max) {
					this.best = new ArrayList<>(parzialeArchi);
					max = calcolaPeso(parzialeArchi);
				}
			}
		}
		//caso normale 
		List<Retailers> vicini = Graphs.neighborListOf(this.grafo, primo.getR());
		
		vicini.removeAll(parziale);
		for (Volume r: vicini) {
			parziale.add(r);
			parzialeArchi.add(arcoDaVertici(primo, r));
			doRicorsione(parziale, n, parzialeArchi);
			parziale.remove(parziale.size()-1);
			parzialeArchi.remove(parzialeArchi.size()-1);
		}
		
	}



	private Arco arcoDaVertici(Volume R1, Volume R2) {
		DefaultWeightedEdge arco = this.grafo.getEdge(R1.getR(), R2.getR());
		if (arco==null) {
			return null;
		}else {
			return new Arco(R1, R2, (int)this.grafo.getEdgeWeight(arco));
		}
	}



	private int calcolaPeso(List<Arco> parziale) {
		int peso = 0;
		for (Arco a : parziale) {
			peso+= a.getPeso();
		}
		return peso;
	}*/
	
	public List<Retailers> trovaPercorso(int N){
		this.best = new ArrayList<>();
		this.max =0;
		List<Retailers> parziale = new ArrayList<>();
		for (Retailers r: this.grafo.vertexSet()) {
			parziale.add(r);
			ricorsione(parziale, N);
			parziale.remove(parziale.size()-1);
		}
		return this.best;
		
	}



	private void ricorsione(List<Retailers> parziale, int n) {
		Retailers corrente = parziale.get(parziale.size()-1);
		List<Retailers> vicini = Graphs.neighborListOf(this.grafo, corrente);
		Retailers primo = parziale.get(0);
		//uscita 
		if (corrente.equals(primo)) {
			if (calcolaPeso(parziale)>= max) {
				this.best = new ArrayList<>(parziale);
				this.max = calcolaPeso(parziale);
			}
		}
		//normale
		for (Retailers r: vicini) {
			if (parziale.size() == n) {
				if (r.equals(primo)) {
					parziale.add(r);
					break;
				}
			}else {
				if (!parziale.contains(r)) {
					parziale.add(r);
					ricorsione(parziale, n);
					parziale.remove(parziale.size()-1);
				}
			}
		}
		
	}



	private int calcolaPeso(List<Retailers> parziale) {
		// TODO Auto-generated method stub
		return 0;
	}
}
