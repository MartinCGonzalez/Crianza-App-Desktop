package com.interfaz;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Enfermedad;
import com.exceptions.ServiciosException;
import com.servicios.EnfermedadBeanRemote;

public class FrameListadoEnfermedad {

	private JFrame frame;

	private JTable tablaEnfermedad;

	public FrameListadoEnfermedad(JFrame framePadre) throws NamingException, ServiciosException {

		this.initalizeFrame(frame);

	}

	public void initalizeFrame(JFrame framePadre) throws NamingException, ServiciosException {

		JFrame frmEventoClinico = new JFrame("Nuevo Evento Clinico");
		frmEventoClinico.setTitle("Nuevo Evento Clinico");
		frmEventoClinico.setSize(340, 228);
		frmEventoClinico.setResizable(false);
		frmEventoClinico.setLocationRelativeTo(null);
		frmEventoClinico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEventoClinico.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(337, 199);
		this.tablaEnfermedad = this.cargarTablaEnfermedad();

		JScrollPane pane1 = new JScrollPane(this.tablaEnfermedad);
		pane1.setSize(313, 178);
		pane1.setLocation(10, 11);
		Dimension dim1 = this.tablaEnfermedad.getPreferredSize();
		pane1.setPreferredSize(new Dimension(dim1.width,
				this.tablaEnfermedad.getRowHeight() * this.tablaEnfermedad.getRowCount() + 1));
		formularioPanel.add(pane1);

		formularioPanel.setLayout(null);

		frmEventoClinico.getContentPane().add(formularioPanel);

		frmEventoClinico.setVisible(true);

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablaEnfermedad.getModel());
		tablaEnfermedad.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

		sorter.setSortKeys(sortKeys);

		sorter.sort();

		this.frame = frmEventoClinico;

	}

	private JTable cargarTablaEnfermedad() throws NamingException, ServiciosException {

		List<Enfermedad> enfermedades;

		EnfermedadBeanRemote enfermedadBean = (EnfermedadBeanRemote) InitialContext
				.doLookup("PTI-Crianza/EnfermedadBean!com.servicios.EnfermedadBeanRemote");

		enfermedades = enfermedadBean.obtenerTodos();

		String[] nombreColumnas = { "ID Enfermedad", "Tipo", "Gravedad" };

		Object[][] datos = new Object[enfermedades.size()][3];

		int fila = 0;

		for (Enfermedad e : enfermedades) {

			datos[fila][0] = e.getIdEnfermedad();
			datos[fila][1] = e.getNombre();
			datos[fila][2] = e.getGradoGravedad();

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
				if (enfermedades.isEmpty()) {
					return Object.class;
				}
				return getValueAt(0, columnIndex).getClass();
			}

		};

		JTable table = new JTable(model);
		table.setAutoscrolls(true);
		table.setCellSelectionEnabled(false);
		table.setSize(500, 250);
		table.setRowSelectionAllowed(true);

		this.tablaEnfermedad = table;

		return table;
	}

}

