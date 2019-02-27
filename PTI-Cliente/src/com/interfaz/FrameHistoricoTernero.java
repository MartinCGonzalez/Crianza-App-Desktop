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
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.ConsumoAlimento;
import com.entities.Ternero;
import com.exceptions.ServiciosException;
import com.servicios.ConsumoAlimentosBeanRemote;
import com.servicios.TernerosBeanRemote;

public class FrameHistoricoTernero implements ActionListener {

	private JFrame frame;

	private JLabel labelIdTernero;
	private JLabel labelPeso;

	private JTable tablaTernero;
	private JTable tablaHistorico;

	private JButton buttonCancelar;

	private JButton btnBuscar;

	private TableRowSorter<TableModel> sorter;

	private JLabel labelTipoRegistro;
	private JLabel labelFecha;

	private JTextField textNroCaravana;
	private JTextField textPeso;

	private JButton btnIngresar;
	private JTextField textField;

	/**
	 * @wbp.parser.entryPoint
	 */
	public FrameHistoricoTernero(JFrame framePadre) throws NamingException, SQLException {

		this.labelIdTernero = new JLabel("Terneros:");
		this.labelPeso = new JLabel("Registro de Pesos:");

		labelIdTernero.setBounds(265, 35, 100, 20);
		labelPeso.setBounds(265, 254, 417, 20);

		this.labelTipoRegistro = new JLabel("Tipo de Registro:");
		this.labelFecha = new JLabel("Fecha:");
		labelTipoRegistro.setBounds(10, 44, 100, 20);
		labelFecha.setBounds(10, 12, 100, 20);

		this.textNroCaravana = new JTextField(15);
		this.textPeso = new JTextField(15);

		textNroCaravana.setBounds(120, 111, 115, 20);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(565, 249, 104, 20);
		buttonCancelar.addActionListener(this);
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);

	}

	private void initalizeFrame(JFrame framePadre) throws NamingException, SQLException {

		JFrame frmGananciaPeso = new JFrame("Historial Consumo Alimentos");
		frmGananciaPeso.setTitle("Historial Consumo Alimentos");
		frmGananciaPeso.setSize(707, 348);
		frmGananciaPeso.setResizable(false);
		frmGananciaPeso.setLocationRelativeTo(null);
		frmGananciaPeso.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmGananciaPeso.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(701, 319);

		this.tablaTernero = this.cargarTablaTernero();

		JScrollPane pane1 = new JScrollPane(this.tablaTernero);
		pane1.setSize(247, 172);
		pane1.setLocation(437, 11);
		Dimension dim1 = this.tablaTernero.getPreferredSize();
		pane1.setPreferredSize(
				new Dimension(dim1.width, this.tablaTernero.getRowHeight() * this.tablaTernero.getRowCount() + 1));
		formularioPanel.add(pane1);

		ConsumoAlimentosBeanRemote consumoAlimentosBean = (ConsumoAlimentosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/ConsumoAlimentosBean!com.servicios.ConsumoAlimentosBeanRemote");

		try {
			this.tablaHistorico = this.cargarTablaHistorico(consumoAlimentosBean.obtenerTodos());
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JScrollPane pane2 = new JScrollPane(this.tablaHistorico);
		pane2.setSize(417, 282);
		pane2.setLocation(10, 11);
		Dimension dim2 = this.tablaHistorico.getPreferredSize();
		pane2.setPreferredSize(
				new Dimension(dim2.width, this.tablaHistorico.getRowHeight() * this.tablaHistorico.getRowCount() + 1));
		formularioPanel.add(pane2);

		formularioPanel.setLayout(null);
		formularioPanel.add(buttonCancelar);

		formularioPanel.setLayout(null);
		formularioPanel.add(buttonCancelar);

		frmGananciaPeso.getContentPane().add(formularioPanel);

		JLabel lblNroCaravana = new JLabel("Nro Caravana");
		lblNroCaravana.setBounds(447, 206, 88, 20);
		formularioPanel.add(lblNroCaravana);
		textField = new JTextField();
		textField.setSize(130, 20);
		textField.setLocation(545, 206);
		formularioPanel.add(textField);
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(451, 249, 104, 20);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {

					sorter.setRowFilter(RowFilter.regexFilter("^" + text + "$", 1));

				}
			}
		});
		formularioPanel.add(btnBuscar);

		frmGananciaPeso.getContentPane().add(formularioPanel);

		textPeso = new JTextField();
		textPeso.setBounds(60, 46, 86, 20);
		formularioPanel.add(buttonCancelar);
		
		frmGananciaPeso.setVisible(true);

		this.frame = frmGananciaPeso;

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

		String[] nombreColumnas = { "Terneros - Nro Caravana" };

		Object[][] datos = new Object[terneros.size()][1];

		int fila = 0;

		for (Ternero t : terneros) {

			datos[fila][0] = t.getNroCaravana();

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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel model = (DefaultTableModel) tablaTernero.getModel();


				int filaSeleccionadaTernero = tablaTernero.getSelectedRow();

				textField.setText(model.getValueAt(filaSeleccionadaTernero, 0).toString());
				
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

	private JTable cargarTablaHistorico(List<ConsumoAlimento> consumoAlimento) throws NamingException {

		List<ConsumoAlimento> consumos = consumoAlimento;

		String[] nombreColumnas = { "ID Ternero", "Nro Caravana", "Fecha", "Alimento", "Cantidad", "Unidad" };

		Object[][] datos = new Object[consumos.size()][6];

		int fila = 0;

		for (ConsumoAlimento ca : consumos) {

			datos[fila][0] = ca.getTernero().getIdTernero();
			datos[fila][1] = ca.getTernero().getNroCaravana();
			datos[fila][2] = ca.getFecha().toString();
			datos[fila][3] = ca.getAlimento().getNombre();
			datos[fila][4] = ca.getCantidad();
			datos[fila][5] = ca.getUnidad().getUnidad();
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

		this.tablaHistorico = table;

		return table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else if (e.getSource() == this.btnIngresar) {
			try {
				this.accionIngesar();
			} catch (NamingException | ServiciosException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private void accionIngesar() throws NamingException, ServiciosException {

	}

	private void accionCancelar() {
		this.frame.dispose();
	}

}
