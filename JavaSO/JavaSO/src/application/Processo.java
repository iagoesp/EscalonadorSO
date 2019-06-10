package application;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

class Processo extends FrameProcesso implements Comparable<Processo>{

	private String id;
	private int sobrecarga;
	private int quantum;
	private int tCheg;
	private int tExec;
	private int tDead;
	private int tPrior;
	boolean begin = false;
	private char[] ganttChar;
	private int tamPag;
	
	public Processo() {
	}
	public Processo(String id, int tCheg, int tExec, int tDead, int tPrior, int tP) {
		super();
		this.id = id;
		this.tCheg = tCheg;
		this.tExec = tExec;
		this.tDead = tDead;
		this.tPrior = tPrior;
		this.setTamPag(tP);
		this.exec = new ArrayList<AbstractMap.SimpleEntry<Integer, Integer>>();
	}

	public String getID() {
		return this.id;
	}
	public void setID(String i) {
		this.id  = i;
	}
	public int getQ() {
		return quantum;
	}
	public void setQ(int q) {
		this.quantum = q;
	}
	public int getS() {
		return sobrecarga;
	}	
	public void setS(int s) {
		this.sobrecarga = s;
	}
	public int getCheg() {
		return this.tCheg;
	}
	public void setCheg(int tCheg) {
		this.tCheg = tCheg;
	}
	public int getExec() {
		return this.tExec;
	}
	public void setExec(int tExec) {
		this.tExec = tExec;
	}
	public int getDead() {
		return this.tDead;
	}
	public void setDead(int tDead) {
		this.tDead = tDead;
	}
	public int getPrior() {
		return this.tPrior;
	}
	public void setPrior(int tPrior) {
		this.tPrior = tPrior;
	}


	private static Date date(final int day, final int month, final int year, int hourOfDay, int minute, int second) {		   
       final Calendar calendar = Calendar.getInstance();
       calendar.set(year, month, day, hourOfDay, minute, second);
       final Date result = calendar.getTime();
       return result;
   }
	
	String Gantt() {
		String nome = this.getID() + ":";
		int a = this.getPair().get(this.getPair().size() - 1).getValue() + 1;
		char[] gantt = new char[a];
		boolean dentro = false;
		for(int i = 0; i<gantt.length; i++) {
			if(i >= tCheg && i <= this.getPair().get(0).getKey()) {
				dentro = true;
			}
			else {
				dentro = false;
			}
			if(dentro) {
				gantt[i]='!';
				this.tempoEspera = new AbstractMap.SimpleEntry<>(this.getCheg(), i);
				this.tempoEspera.setValue(i-1);
				if(this.tempoEspera.getValue()<0)
					this.tempoEspera.setValue(0);
			}else {
				gantt[i]='*';
			}
		}
		for(int i = this.tempoEspera.getKey(); i <= this.tempoEspera.getValue();i++) {
			XYChart.Data<Integer, String> d = new XYChart.Data<Integer, String>(i, this.getID(), new ExtraData(1, "status-espera"));
			l.add(d);
		}
		char indicador = '#';
		ArrayList<SimpleEntry<Integer, Integer>> b = this.getPair();
		for(int k = 0; k < b.size(); k++) {
			for(int m = b.get(k).getKey(); m <= b.get(k).getValue(); m++) {
				gantt[m] = indicador;
				if(indicador == '#') {
					XYChart.Data<Integer, String> d = new XYChart.Data<Integer, String>(m, this.getID(), new ExtraData(1, "status-yellow"));
					l.add(d);
				}
				else {
					XYChart.Data<Integer, String> d = new XYChart.Data<Integer, String>(m, this.getID(), new ExtraData(1, "status-red"));
					l.add(d);
				}
			}

			if(indicador == '#') {
				indicador = '-';
			}
			else
				indicador = '#';
		}
		AbstractMap.SimpleEntry<Integer, Character > w = null;
		for(int i = 0; i < gantt.length; i++) {
			if(gantt[i] == '!') {
				 w = new AbstractMap.SimpleEntry<Integer, Character >(i, '!');
			}
			else if(gantt[i] == '#') {
				w = new AbstractMap.SimpleEntry<Integer, Character >(i, '#');
			}
			else if(gantt[i] == '-') {
				w = new AbstractMap.SimpleEntry<Integer, Character >(i, '-');
			}
			else if(gantt[i] == '*') {
				w = new AbstractMap.SimpleEntry<Integer, Character >(i, '*');
			}
			tempoTotal.add(w);
		}
		ganttChar = gantt;
		for(int i = 0; i < gantt.length; i++) {
			nome += gantt[i];
		}
		return nome;
	}
	
	public char[] getGanttChar() {
		return ganttChar;
	}
	
	@Override
	public int compareTo(Processo o) {
		if(this.getCheg() == o.getCheg()) {
			if(this.getExec() < (o.getExec()))
				return -1;
			else if(this.getExec() > (o.getExec()))
				return 1;
			return 0;
		}
		if(this.getCheg() < (o.getCheg()))
			return -1;
		else if(this.getCheg() > (o.getCheg()))
			return 1;
		return 0;
	}
	public int getTamPag() {
		return tamPag;
	}
	public void setTamPag(int tamPag) {
		this.tamPag = tamPag;
	}
}					