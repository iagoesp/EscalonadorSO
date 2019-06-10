package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public abstract class MetodosEscalonamento {
	private ArrayList<Processo> listaProcessos;
	private ArrayList<Processo> finalizados;
	private ArrayList<Processo> main;
	private ArrayList<Processo> original = new ArrayList<Processo>();

	private ArrayList<String> linha;
	
	private int quantum;
	private int sobrecarga;
	
	public MetodosEscalonamento(ArrayList<Processo> lista, int q, int s) {
    	String o = "Const" + lista.get(0).getID();
    	System.out.println(o);

		this.original = lista;
		this.setListaProcessos(new ArrayList<Processo>());
		this.setListaProcessos(original);
		this.setQuantum(q);
		this.setSobrecarga(s);
		this.setFinalizados(new ArrayList<Processo>());
		this.setMain(new ArrayList<Processo>());
		this.setLinha(new ArrayList<String>());
	}	
		
	abstract int buscarProcesso();
	
	public abstract void inserir(int t);
	
	public abstract void executar() throws IOException;
	
	public ArrayList<Processo> getMain() {
		return main;
	}
	public void setMain(ArrayList<Processo> main) {
		this.main = main;
	}
	public ArrayList<Processo> getLista() {
		return listaProcessos;
	}
	public void setListaProcessos(ArrayList<Processo> listaProcessos) {
		this.listaProcessos = listaProcessos;
	}
	public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	public int getSobrecarga() {
		return sobrecarga;
	}
	public void setSobrecarga(int sobrecarga) {
		this.sobrecarga = sobrecarga;
	}
	public ArrayList<Processo> getFinalizados() {
		return finalizados;
	}
	public void setFinalizados(ArrayList<Processo> finalizados) {
		this.finalizados = finalizados;
	}
	public ArrayList<String> getLinha() {
		return linha;
	}
	public void setLinha(ArrayList<String> linha) {
		this.linha = linha;
	}
	public ArrayList<Processo> getOriginal() {
		return original;
	}

	public void setOriginal(ArrayList<Processo> original) {
		this.original = original;
	}
	public void ordenarProcessos(String processo) {
	    if(processo.equals("FIFO") || processo.equals("RR")){
	    	Collections.sort(finalizados);
	    }
	    else if(processo.equals("SJF")) {
    		int j;
    	    Processo key;
    	    int i;
    		for (j = 1; j < finalizados.size(); j++){
    		      key = finalizados.get(j);
    		      for (i = j - 1; (i >= 0) && (finalizados.get(i).getExec() > key.getExec()); i--){
    		    	  finalizados.set(i + 1, finalizados.get(i));
    		         
    		      }
    		      finalizados.set(i + 1, key);
			}
	    }
	}
}
