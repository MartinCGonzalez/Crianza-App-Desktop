package com.interfaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Peso;
import com.entities.Ternero;
import com.exceptions.ServiciosException;
import com.servicios.PesoBeanRemote;
import com.servicios.TernerosBeanRemote;

public class FrameHistoricoPeso implements ActionListener {

	private JLabel labelIdTernero;
	private JLabel labelPeso;
	private JTable tablaTernero;
	private JTable tablaPeso;

	private JButton btnMostrarRegistro;

	private TableRowSorter<TableModel> sorter;

	private JLabel labelTipoRegistro;
	private JLabel labelFecha;

	private JTextField textIdTernero;
	private JTextField textPeso;

	private JTextField filterText;

	public FrameHistoricoPeso(JFrame framePadre) throws NamingException, SQLException, ServiciosException {

		this.labelIdTernero = new JLabel("Terneros:");
		this.labelPeso = new JLabel("Registro de Pesos:");

		labelIdTernero.setBounds(265, 35, 100, 20);
		labelPeso.setBounds(265, 254, 417, 20);

		this.labelTipoRegistro = new JLabel("Tipo de Registro:");
		this.labelFecha = new JLabel("Fecha:");
		labelTipoRegistro.setBounds(10, 44, 100, 20);
		labelFecha.setBounds(10, 12, 100, 20);

		this.textIdTernero = new JTextField(15);
		this.textPeso = new JTextField(15);

		textIdTernero.setBounds(120, 111, 115, 20);

		this.initalizeFrame(framePadre);

	}

	private void initalizeFrame(JFrame framePadre) throws NamingException, SQLException {

		JFrame frmGananciaPeso = new JFrame("Ganancia de peso");
		frmGananciaPeso.setTitle("Ganancia de Peso por Ternera");
		frmGananciaPeso.setSize(705, 310);
		frmGananciaPeso.setResizable(false);
		frmGananciaPeso.setLocationRelativeTo(null);
		frmGananciaPeso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGananciaPeso.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(694, 281);

		this.tablaTernero = this.cargarTablaTernero();

		JScrollPane pane1 = new JScrollPane(this.tablaTernero);
		pane1.setSize(200, 179);
		pane1.setLocation(10, 11);
		Dimension dim1 = this.tablaTernero.getPreferredSize();
		pane1.setPreferredSize(
				new Dimension(dim1.width, this.tablaTernero.getRowHeight() * this.tablaTernero.getRowCount() + 1));
		formularioPanel.add(pane1);

		PesoBeanRemote pesoBean = (PesoBeanRemote) InitialContext
				.doLookup("PTI-Crianza/PesoBean!com.servicios.PesoBeanRemote");

		try {
			this.tablaPeso = this.cargarTablaPeso(pesoBean.obtenerTodos());
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JScrollPane pane2 = new JScrollPane(this.tablaPeso);
		pane2.setSize(463, 179);
		pane2.setLocation(221, 11);
		Dimension dim2 = this.tablaPeso.getPreferredSize();
		pane2.setPreferredSize(
				new Dimension(dim2.width, this.tablaPeso.getRowHeight() * this.tablaPeso.getRowCount() + 1));
		formularioPanel.add(pane2);

		formularioPanel.setLayout(null);

		formularioPanel.setLayout(null);

		frmGananciaPeso.getContentPane().add(formularioPanel);

		JLabel lblIdTernero = new JLabel("ID Ternero");
		lblIdTernero.setBounds(10, 201, 60, 20);
		formularioPanel.add(lblIdTernero);
		filterText = new JTextField();
		filterText.setSize(130, 20);
		filterText.setLocation(80, 201);
		formularioPanel.add(filterText);
		btnMostrarRegistro = new JButton("Buscar");
		btnMostrarRegistro.setBounds(54, 232, 120, 20);
		btnMostrarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = filterText.getText();
				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {

					sorter.setRowFilter(RowFilter.regexFilter("^" + text + "$", 0));

				}
			}
		});
		formularioPanel.add(btnMostrarRegistro);

		frmGananciaPeso.getContentPane().add(formularioPanel);

		textPeso = new JTextField();
		textPeso.setBounds(60, 46, 86, 20);
		textPeso.setColumns(10);

		frmGananciaPeso.setVisible(true);

	}

	private JTable cargarTablaTernero() throws NamingException {

		List<Ternero> terneros = null;

		TernerosBeanRemote ternerosBean = (TernerosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/TernerosBean!com.servicios.TernerosBeanRemote");
		try {
			terneros = ternerosBean.obtenerTodos();
		} catch (ServiciosException e) {
			e.printStackTrace();
		}

		String[] nombreColumnas = { "ID Ternero", "Peso Nacimiento" };

		Object[][] datos = new Object[terneros.size()][2];

		int fila = 0;

		for (Ternero t : terneros) {

			datos[fila][0] = t.getIdTernero();
			datos[fila][1] = t.getPesoNac();

			fila++;
		}

		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}
		};

		// Cargo los datos de la tabla a los TextField
		JTable table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				DefaultTableModel model = (DefaultTableModel) tablaTernero.getModel();

				int filaSeleccionadaAlimento = tablaTernero.getSelectedRow();

				filterText.setText(model.getValueAt(filaSeleccionadaAlimento, 0).toString());
				filterText.setEditable(false);

				model.getRowCount();

			}

		});

		table.setAutoscrolls(true);
		table.setCellSelectionEnabled(false);
		table.setSize(500, 250);
		table.setRowSelectionAllowed(true);

		this.tablaTernero = table;

		return table;
	}

	private JTable cargarTablaPeso(List<Peso> peso) throws NamingException {

//		PesoBeanRemote pesoBean = (PesoBeanRemote) InitialContext
//				.doLookup("PTI-Crianza/PesoBean!com.servicios.PesoBeanRemote");

		List<Peso> pesos = peso;

		String[] nombreColumnas = { "ID Ternero", "Fecha", "Peso", "Tipo de Registro" };

		Object[][] datos = new Object[pesos.size()][4];

		int fila = 0;

		for (Peso a : pesos) {

			datos[fila][0] = a.getIdTernero();
			datos[fila][1] = a.getFecha();
			datos[fila][2] = a.getPeso();
			datos[fila][3] = a.getTipoRegistro();
			fila++;
		}

		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}

		};

		JTable table = new JTable(model);
		table.setAutoscrolls(true);
		table.setCellSelectionEnabled(false);
		table.setSize(500, 250);
		table.setRowSelectionAllowed(true);

		sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);

		this.tablaPeso = table;

		return table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
