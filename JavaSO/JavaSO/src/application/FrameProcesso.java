package application;

import java.util.AbstractMap;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class FrameProcesso extends JPanel implements CaretListener {
	JLabel lblTempoDeChegada;
	JLabel lblTempoDeExecuo;
	JLabel lblDeadline;
	JLabel lblPrioridade;
	JTextField tChegTF;
	JTextField tExecTF;
	JTextField tDeadTF;
	JTextField tPriorTF;
	
	JButton okProcBT;
	boolean ready;
	
	protected ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> exec;
	protected ArrayList<XYChart.Data<Integer, String>> l = new ArrayList<XYChart.Data<Integer, String>>();
	AbstractMap.SimpleEntry<Integer, Integer> tempoEspera;
	ArrayList<AbstractMap.SimpleEntry<Integer, Character >> tempoTotal = new ArrayList<AbstractMap.SimpleEntry<Integer, Character >>();
	
	public ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> getPair(){
		return this.exec;
	}
	
	public void addCaret() {
		tPriorTF.addCaretListener(this);
		tDeadTF.addCaretListener(this);
		tChegTF.addCaretListener(this);
		tExecTF.addCaretListener(this);
	}
	@Override
	public void caretUpdate(CaretEvent arg0) {
		// TODO Auto-generated method stub
		int c;
		try{
			c = Integer.parseInt(tChegTF.getText());
		}
		catch(NumberFormatException f){
			c = -1;
		}
		
		int e;
		try{
			e = Integer.parseInt(tExecTF.getText());
		}
		catch(NumberFormatException f){
			e = -1;
		}
		
		int d;
		try{
			d = Integer.parseInt(tDeadTF.getText());
		}
		catch(NumberFormatException f){
			d = -1;
		}
		
		int p;
		try{
			p = Integer.parseInt(tPriorTF.getText()); 
		}
		catch(NumberFormatException f){
			p = -1;
		}
		 

		if(c >= 0 && e > 0 && d > 0 && p >= 0)
			okProcBT.setEnabled(true);
		else
			okProcBT.setEnabled(false);
	}
	public ArrayList<Data<Integer, String>> getData() {
		return l;
	}
	public void inserirExecucao(int z, int i, int f) {
		AbstractMap.SimpleEntry<Integer, Integer> a = new AbstractMap.SimpleEntry<>(i, f);
		exec.add(0,a);
	}
	public void inserirExecucao(int i, int f) {
		AbstractMap.SimpleEntry<Integer, Integer> a = new AbstractMap.SimpleEntry<>(i, f);
		exec.add(a);
	}
	public boolean getReady() {
		return this.ready;
	}
	public void setReady(boolean r) {
		this.ready = r;
	}
	
	public static class ExtraData {

        public long length;
        public String styleClass;


        public ExtraData(long lengthMs, String styleClass) {
            super();
            this.length = lengthMs;
            this.styleClass = styleClass;
        }
        public long getLength() {
            return length;
        }
        public void setLength(long length) {
            this.length = length;
        }
        public String getStyleClass() {
            return styleClass;
        }
        public void setStyleClass(String styleClass) {
            this.styleClass = styleClass;
        }
    }
	

}
