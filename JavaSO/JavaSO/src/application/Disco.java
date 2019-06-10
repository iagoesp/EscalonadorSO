package application;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;

public class Disco extends JFrame {

	private JPanel contentPane;
	static Object[] o;
	private static int qtdPages;
	private JScrollPane barraRolagem;
	private Container painelFundo;
	private JTable tabela;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Disco frame = new Disco(o, qtdPages);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Disco(Object[] o, int qP) {
		this.qtdPages = qP;
		this.o = new Object[o.length];
		this.o = o;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(850, 10, 232, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{200, 0};
		gbl_contentPane.rowHeights = new int[]{0, 200, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		adicionar();
	}

	
	void adicionar() {
		tabela = new JTable();

		DefaultTableModel dtm = new DefaultTableModel(0, 0);

		String header[] = new String[] { "Processos", "Páginas" };

		dtm.setColumnIdentifiers(header);

		tabela.setModel(dtm);

		for (int count = 0; count < o.length; count++) {
				for(int i = count*qtdPages; i < (count+1)*qtdPages; i++) {
					dtm.addRow(new Object[] { o[count], i});
				}
				if(count != o.length - 1)
					dtm.addRow(new Object[] { , });
		 }
		
		lblNewLabel = new JLabel("Disco");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		barraRolagem = new JScrollPane(tabela);
		GridBagConstraints gbc_barraRolagem = new GridBagConstraints();
		gbc_barraRolagem.gridx = 0;
		gbc_barraRolagem.gridy = 1;
		gbc_barraRolagem.fill = GridBagConstraints.BOTH;
		getContentPane().add(barraRolagem, gbc_barraRolagem);
		barraRolagem.setVisible(true);

	}

}
