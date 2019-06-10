package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;

public class Main implements CaretListener{

	/*Equipe: Iago Pinto,
	 * 			Jurgen Fink,
	 * 			Jo�o Carneiro
	 */
	
	private JFrame frame;
	private JTextField quantProcTF;
	private JTextField sobrecargaTF;
	private JTextField quantumTF;
	private int quantProc;
	private int sobrecarga;
	private int quantum;
	private Processo jpanels[];
	private String tipo = "FIFO";
	
	private JPanel panel_1;
	private JPanel panel_2;
	private JRadioButton fifoRB;
	private JRadioButton sjfRB;
	private JRadioButton roundRRB;
	private JRadioButton edfRB;
	private JButton criarLB;
	private JButton okBT;
	private boolean procB = false, sobreB = false, quantB = false, algB = false, jpan = false;
	private JLabel lblTamanhoDaPgina;
	private JTextField tamPaginaTF;
	private boolean tamPagB;
	ArrayList<Processo> lista = new ArrayList<Processo>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(10, 10, 850, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 850, 850, 10, 0};
		gridBagLayout.rowHeights = new int[]{20, 40, -1, 50, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 10.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 150, 30, 80, 30, 80, 30, 0, 30, 87, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblQuantProcessos = new JLabel("Quantidade de Processos");
		GridBagConstraints gbc_lblQuantProcessos = new GridBagConstraints();
		gbc_lblQuantProcessos.fill = GridBagConstraints.BOTH;
		gbc_lblQuantProcessos.insets = new Insets(0, 0, 0, 5);
		gbc_lblQuantProcessos.gridx = 1;
		gbc_lblQuantProcessos.gridy = 0;
		panel.add(lblQuantProcessos, gbc_lblQuantProcessos);
		
		quantProcTF = new JTextField();
		quantProcTF.setHorizontalAlignment(SwingConstants.CENTER);
		quantProcTF.setText("1");
		GridBagConstraints gbc_quantProcTF = new GridBagConstraints();
		gbc_quantProcTF.fill = GridBagConstraints.BOTH;
		gbc_quantProcTF.insets = new Insets(0, 0, 0, 5);
		gbc_quantProcTF.gridx = 2;
		gbc_quantProcTF.gridy = 0;
		panel.add(quantProcTF, gbc_quantProcTF);
		quantProcTF.setColumns(10);
		
		quantProcTF.addCaretListener(this);
		
		okBT = new JButton("Definir");
		okBT.setEnabled(false);
		okBT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!okBT.isEnabled())
					return;
				String aux = quantProcTF.getText();
				int tam = aux.length();
				quantProc = 0;
				sobrecarga = Integer.parseInt(sobrecargaTF.getText());
				quantum = Integer.parseInt(quantumTF.getText());
				panel_1.removeAll();
				for(int i = 0; i < tam; i++)
					quantProc += (int) ((aux.charAt(tam-i-1)-48)*Math.pow(10,i));
				if(quantProc>0) {
					jpanels = new Processo[quantProc];
					//tipo = defineTipo();
					GridBagConstraints gbc_panel[] = new GridBagConstraints[quantProc];
					panel_1.setPreferredSize(new Dimension(899, 100*quantProc));
					panel_1.setSize(new Dimension(400, 100*quantProc));
					for(int i = 0; i <quantProc; i++) {
						jpanels[i] = new Processo();
						jpanels[i].setQ(quantum);
						jpanels[i].setS(sobrecarga);
						jpanels[i].setBorder(new TitledBorder(null, "Processo " + (i + 1), TitledBorder.LEADING, TitledBorder.TOP, null, null));
						gbc_panel[i] = new GridBagConstraints();
						gbc_panel[i].insets = new Insets(0, 0, 0, 5);
						gbc_panel[i].fill = GridBagConstraints.HORIZONTAL;
						gbc_panel[i].gridx = 1;
						gbc_panel[i].gridy = 3 + i;
						
						jpanels[i].lblTempoDeChegada = new JLabel("Tempo de Chegada");
						jpanels[i].add(jpanels[i].lblTempoDeChegada);
						
						jpanels[i].tChegTF = new JTextField();
						jpanels[i].add(jpanels[i].tChegTF);
						jpanels[i].tChegTF.setColumns(10);
						
						jpanels[i].lblTempoDeExecuo = new JLabel("Tempo de Execu\u00E7\u00E3o");
						jpanels[i].add(jpanels[i].lblTempoDeExecuo);
						
						jpanels[i].tExecTF = new JTextField();
						jpanels[i].add(jpanels[i].tExecTF);
						jpanels[i].tExecTF.setColumns(10);
						
						jpanels[i].lblDeadline = new JLabel("Deadline");
						jpanels[i].add(jpanels[i].lblDeadline);
						
						jpanels[i].tDeadTF = new JTextField();
						jpanels[i].add(jpanels[i].tDeadTF);
						jpanels[i].tDeadTF.setColumns(10);
						
						jpanels[i].lblPrioridade = new JLabel("Prioridade");
						jpanels[i].add(jpanels[i].lblPrioridade);
						
						jpanels[i].tPriorTF = new JTextField();
						jpanels[i].add(jpanels[i].tPriorTF);
						jpanels[i].tPriorTF.setColumns(10);
						
						jpanels[i].tChegTF.setText("");
						jpanels[i].tExecTF.setText("");
						jpanels[i].tDeadTF.setText("");
						jpanels[i].tPriorTF.setText("");
						jpanels[i].addCaret();
						
						jpanels[i].okProcBT = new JButton("Definir");
						jpanels[i].okProcBT.setEnabled(false);
						
						final int j = i;
						
						jpanels[i].okProcBT.addMouseListener(new MouseAdapter() {
							boolean clicked = false;
							@Override
							public void mouseClicked(MouseEvent arg0) {
								if(jpanels[j].okProcBT.isEnabled()==true) {
									clicked = true;
									jpanels[j].tChegTF.setEditable(false);
									jpanels[j].tDeadTF.setEditable(false);
									jpanels[j].tExecTF.setEditable(false);
									jpanels[j].tPriorTF.setEditable(false);
									jpanels[j].setPrior(Integer.parseInt(jpanels[j].tPriorTF.getText()));
									jpanels[j].setCheg(Integer.parseInt(jpanels[j].tChegTF.getText()));
									jpanels[j].setDead(Integer.parseInt(jpanels[j].tDeadTF.getText()));
									jpanels[j].setExec(Integer.parseInt(jpanels[j].tExecTF.getText()));
									int s = j + 1;
									jpanels[j].setID(Integer.toString((s)));
								}
							}
							public void mouseExited(MouseEvent arg0) {
								if(clicked) {
								    jpanels[j].setReady(true);
							    	boolean ok = true;
								    for(int k = 0; k < jpanels.length; k++) {
								    	if(jpanels[k].getReady()==false) {
								    		ok = false;
								    		break;
								    	}
								    }
								    if(ok){
							    		criarLB.setEnabled(true);
							    	}	    		
								    try { Thread.sleep (500);
								    	panel_1.remove(jpanels[j]);
								    }catch (InterruptedException ex) {}
							        frame.revalidate();
									frame.repaint();
								}
								clicked = false;
								jpan = false;
							}
						});
						jpanels[i].add(jpanels[i].okProcBT);
						
						panel_1.add(jpanels[i], gbc_panel[i]);
						panel_1.revalidate();
						panel_1.repaint();
						frame.revalidate();
						frame.repaint();
						
					}
				}						
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				caretUpdate(null);
			}
		});
		
		JLabel lblSobrecarga = new JLabel("Sobrecarga");
		lblSobrecarga.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSobrecarga = new GridBagConstraints();
		gbc_lblSobrecarga.fill = GridBagConstraints.BOTH;
		gbc_lblSobrecarga.insets = new Insets(0, 0, 0, 5);
		gbc_lblSobrecarga.gridx = 3;
		gbc_lblSobrecarga.gridy = 0;
		panel.add(lblSobrecarga, gbc_lblSobrecarga);
		
		sobrecargaTF = new JTextField();
		sobrecargaTF.setHorizontalAlignment(SwingConstants.CENTER);
		sobrecargaTF.setText("1");
		GridBagConstraints gbc_sobrecargaTF = new GridBagConstraints();
		gbc_sobrecargaTF.fill = GridBagConstraints.BOTH;
		gbc_sobrecargaTF.insets = new Insets(0, 0, 0, 5);
		gbc_sobrecargaTF.gridx = 4;
		gbc_sobrecargaTF.gridy = 0;
		panel.add(sobrecargaTF, gbc_sobrecargaTF);
		sobrecargaTF.setColumns(10);
		sobrecargaTF.addCaretListener(this);
		
		JLabel lblQuantum = new JLabel("Quantum");
		lblQuantum.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblQuantum = new GridBagConstraints();
		gbc_lblQuantum.fill = GridBagConstraints.BOTH;
		gbc_lblQuantum.insets = new Insets(0, 0, 0, 5);
		gbc_lblQuantum.gridx = 5;
		gbc_lblQuantum.gridy = 0;
		panel.add(lblQuantum, gbc_lblQuantum);
		
		quantumTF = new JTextField();
		quantumTF.setHorizontalAlignment(SwingConstants.CENTER);
		quantumTF.setText("2");
		GridBagConstraints gbc_quantumTF = new GridBagConstraints();
		gbc_quantumTF.fill = GridBagConstraints.BOTH;
		gbc_quantumTF.insets = new Insets(0, 0, 0, 5);
		gbc_quantumTF.gridx = 6;
		gbc_quantumTF.gridy = 0;
		panel.add(quantumTF, gbc_quantumTF);
		quantumTF.setColumns(10);
		quantumTF.addCaretListener(this);	
		
		lblTamanhoDaPgina = new JLabel("Tamanho da P\u00E1gina");
		GridBagConstraints gbc_lblTamanhoDaPgina = new GridBagConstraints();
		gbc_lblTamanhoDaPgina.anchor = GridBagConstraints.EAST;
		gbc_lblTamanhoDaPgina.insets = new Insets(0, 0, 0, 5);
		gbc_lblTamanhoDaPgina.gridx = 7;
		gbc_lblTamanhoDaPgina.gridy = 0;
		panel.add(lblTamanhoDaPgina, gbc_lblTamanhoDaPgina);
		
		tamPaginaTF = new JTextField();
		tamPaginaTF.setText("5");
		tamPaginaTF.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_tamPaginaTF = new GridBagConstraints();
		gbc_tamPaginaTF.insets = new Insets(0, 0, 0, 5);
		gbc_tamPaginaTF.fill = GridBagConstraints.BOTH;
		gbc_tamPaginaTF.gridx = 8;
		gbc_tamPaginaTF.gridy = 0;
		panel.add(tamPaginaTF, gbc_tamPaginaTF);
		tamPaginaTF.setColumns(10);
		tamPaginaTF.addCaretListener(this);
		
		GridBagConstraints gbc_okBT = new GridBagConstraints();
		gbc_okBT.fill = GridBagConstraints.HORIZONTAL;
		gbc_okBT.gridx = 9;
		gbc_okBT.gridy = 0;
		panel.add(okBT, gbc_okBT);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
				
		panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		
		panel_2 = new JPanel();
		panel_2.setVisible(false);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 3;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setToolTipText("");
		
		fifoRB = new JRadioButton("FIFO");
		fifoRB.setSelected(true);
		panel_2.add(fifoRB);
		
		sjfRB = new JRadioButton("SJF");
		panel_2.add(sjfRB);
		
		roundRRB = new JRadioButton("Round Robin");
		panel_2.add(roundRRB);
		
		edfRB = new JRadioButton("EDF");		
		panel_2.add(edfRB);
		
		criarLB = new JButton("Criar");
		panel_2.add(criarLB);
		criarLB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(criarLB.isEnabled()==true) {
					
					for(int i = 0; i < jpanels.length; i++) {
						int c = jpanels[i].getCheg();
						int e = jpanels[i].getExec();
						int d = jpanels[i].getDead();
						int p = jpanels[i].getPrior();
						String id = jpanels[i].getID();
						int tamPag = Integer.parseInt(tamPaginaTF.getText());
						Processo p1 = new Processo(id, c, e, d, p, tamPag);
						lista.add(p1);
					}
					
					if(tipo == "FIFO") {
						FIFO f = new FIFO(lista, quantum, sobrecarga);
						try {
							newDisco(lista);
							f.executar();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(tipo == "SJF") {
						SJF s = new SJF(lista, quantum, sobrecarga);
						try {
							newDisco(lista);
							s.executar();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(tipo == "RRobin") {
						RRobin r = new RRobin(lista, quantum, sobrecarga);
						try {
							newDisco(lista);
							r.executar();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(tipo == "EDF") {
						EDF e = new EDF(lista, quantum, sobrecarga);
						try {
							newDisco(lista);
							e.executar();
						} catch (IOException h) {
							// TODO Auto-generated catch block
							h.printStackTrace();
						}
					}
					
				}
			}
		});
		criarLB.setEnabled(false);
		panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{fifoRB, sjfRB, roundRRB, edfRB}));
		
		fifoRB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tipo = "FIFO";
				changeState(sjfRB, roundRRB, edfRB, fifoRB);
			}
		});
		sjfRB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tipo = "SJF";
				changeState(fifoRB, roundRRB, edfRB, sjfRB);
			}
		});
		roundRRB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tipo = "RRobin";
				changeState(fifoRB, sjfRB, edfRB, roundRRB);
			}
		});
		edfRB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tipo = "EDF";
				changeState(fifoRB, sjfRB, roundRRB, edfRB);
			}
		});
	
	}

	@Override
	public void caretUpdate(CaretEvent arg0) {
		if(procB && sobreB && quantB && tamPagB) {
			okBT.setEnabled(true);
			panel_2.setVisible(true);
			if(fifoRB.isSelected()==false && sjfRB.isSelected()==false && roundRRB.isSelected()==false && edfRB.isSelected()==false)
				return;
			criarLB.setEnabled(false);
		}
		else {
			okBT.setEnabled(false);
			panel_2.setVisible(false);
		}
		procB = false; sobreB = false; quantB = false; tamPagB = false; algB = false;
		if(quantProcTF.getText().isEmpty() || sobrecargaTF.getText().isEmpty() || quantumTF.getText().isEmpty() || tamPaginaTF.getText().isEmpty())
			return;
		int proc = Integer.parseInt(quantProcTF.getText());
		int quant = Integer.parseInt(quantumTF.getText());
		int tamPag = Integer.parseInt(tamPaginaTF.getText());
		if(proc == 0 || quant == 0 || tamPag == 0)
			return; 
		okBT.setEnabled(true);
		panel_2.setVisible(true);
		if(fifoRB.isSelected()==false && sjfRB.isSelected()==false && roundRRB.isSelected()==false && edfRB.isSelected()==false)
			return;
		if(lista.size() != quant)
			return;
		criarLB.setEnabled(true);
	}
	public void changeState(JRadioButton a, JRadioButton b, JRadioButton c, JRadioButton d) {
		a.setSelected(false);
		c.setSelected(false);
		b.setSelected(false);
		d.setSelected(true);
		algB = true;
		caretUpdate(null);
		criarLB.setEnabled(true);
		
	}
	
	void newDisco(ArrayList<Processo> lista) {
		boolean a = true;
		if(a) {
			Object[] o = new Object[lista.size()];
			for(int i = 0; i < lista.size(); i++) {
				o[i] = "Processo " + lista.get(i).getID();
			}
			Disco disco = new Disco(o, Integer.parseInt(tamPaginaTF.getText()));
			disco.main(null);
		}
	}
	
}
