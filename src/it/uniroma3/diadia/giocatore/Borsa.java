package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	//private List<Attrezzo> ordinata; 
	private int numeroAttrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>();
		this.numeroAttrezzi = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return attrezzi.add(attrezzo);
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for(Attrezzo a : this.attrezzi) {
			if(a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}

	public int getPeso() {
		int pesoTotale = 0;
		for(Attrezzo a : this.attrezzi)
			pesoTotale += a.getPeso();
		return pesoTotale;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		for(Attrezzo a : this.attrezzi) {
			if(a.getNome().equals(nomeAttrezzo))
				return true;
		}
		return false;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while (iteratore.hasNext()) {
			a = iteratore.next();
			if (a.getNome().equals(nomeAttrezzo)) {
				attrezzi.remove(a);    //rimozione dalla lista principale
				return a;
			}
		}
		return null;
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		//this.ordinata = new ArrayList<>(attrezzi);
		attrezzi.sort(new BorsaComparatorPesoNome());
		//ordinata.sort(new BorsaComparatorPesoNome());  
		return attrezzi;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		
		SortedSet<Attrezzo> ss = new TreeSet<Attrezzo>(new BorsaComparatorNome());
		ss.addAll(attrezzi);
		return ss;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> ss2 = new TreeSet<Attrezzo>(new BorsaComparatorPesoNome());
		ss2.addAll(attrezzi);
		return ss2;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		
		Map<Integer,Set<Attrezzo>> map = new HashMap<>();
		for(Attrezzo att : this.attrezzi) {
			if(!map.containsKey(att.getPeso())) {
				map.put(att.getPeso(), new HashSet<>());    
			}
			map.get(att.getPeso()).add(att);
		}
		return map;
	}
	
	public String toString() {              	  //stampa tutte le collezioni, implementare nel
		StringBuilder s = new StringBuilder();    // caso comandoGuarda

		
		if (!attrezzi.isEmpty()) {
			s.append("Contenuto borsa \n["+this.getPeso()+"kg/"+this.getPesoMax()+"kg: ");
			for (int i= 0; i<attrezzi.size(); i++)
				s.append(attrezzi.get(i).getNome()+" ");
			s.append("]\n");
		}
		
		SortedSet<Attrezzo> ss = getSortedSetOrdinatoPerPeso();
		if(!ss.isEmpty()) {
			Iterator<Attrezzo> ia=ss.iterator();
			s.append("{");
			while(ia.hasNext()) {
				Attrezzo att=ia.next();
				s.append(att.getNome() + ",");
			}
			s.append("}\n");
		}
		
		Map<Integer,Set<Attrezzo>> map =  getContenutoRaggruppatoPerPeso();
		if(!map.isEmpty()) {
			for(int i : map.keySet()) {
				s.append("(" + i + ",{");
				for(Attrezzo att : map.get(i)) {
					s.append(att.getNome() + ",");
				}
				s.append("})\n");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	
	//*******CLASSI ASTRATTE*************
	
	class BorsaComparatorPesoNome implements Comparator<Attrezzo> {

		@Override
		public int compare(Attrezzo o1, Attrezzo o2) {
			if(o1.getPeso()-o2.getPeso() == 0) {
				return o1.getNome().compareTo(o2.getNome());
			}
			return o1.getPeso()-o2.getPeso();
		}
	}
	
	class BorsaComparatorNome implements Comparator<Attrezzo> {

		@Override
		public int compare(Attrezzo o1, Attrezzo o2) {
			return o1.getNome().compareTo(o2.getNome());
		}
	}
}