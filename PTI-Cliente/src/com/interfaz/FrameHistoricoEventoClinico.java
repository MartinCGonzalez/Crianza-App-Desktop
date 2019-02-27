package com.interfaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.EventoClinico;
import com.exceptions.ServiciosException;
import com.servicios.EventoClinicoBeanRemote;

public class FrameHistoricoEventoClinico implements ActionListener {
	
	private JFrame frame;
	
	private JTable tablaEventosClinicos;

	public FrameHistoricoEventoClinico(JFrame framePadre) throws NamingException, ServiciosException {

		this.initalizeFrame(frame);

	}

	public void initalizeFrame(JFrame framePadre) throws NamingException, ServiciosException {

		JFrame frmHistEventoClinico = new JFrame("Historico Evento Clinico");
		frmHistEventoClinico.setTitle("Historico Evento Clinico");
		frmHistEventoClinico.setSize(468, 336);
		frmHistEventoClinico.setResizable(false);
		frmHistEventoClinico.setLocationRelativeTo(null);
		frmHistEventoClinico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHistEventoClinico.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(462, 307);
		formularioPanel.setLayout(null);
		
		EventoClinicoBeanRemote eventoClinicoBean = (EventoClinicoBeanRemote) InitialContext
				.doLookup("PTI-Crianza/EventoClinicoBean!com.servicios.EventoClinicoBeanRemote");


		this.tablaEventosClinicos = this.cargarTablaEventosClinicos(eventoClinicoBean.obtenerTodos());

		JScrollPane pane1 = new JScrollPane(this.tablaEventosClinicos);
		pane1.setSize(434, 247);
		pane1.setLocation(10, 11);
		Dimension dim1 = this.tablaEventosClinicos.getPreferredSize();
		pane1.setPreferredSize(new Dimension(dim1.width,
				this.tablaEventosClinicos.getRowHeight() * this.tablaEventosClinicos.getRowCount() + 1));
		formularioPanel.add(pane1);

		JButton btnAsignarEnfermedad = new JButton("Asignar Enfermedad");
		btnAsignarEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					new FrameAltaEventoClinico(framePadre);
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		});

		btnAsignarEnfermedad.setBounds(287, 267, 157, 23);
		formularioPanel.add(btnAsignarEnfermedad);

		formularioPanel.setLayout(null);
		frmHistEventoClinico.getContentPane().add(formularioPanel);
		frmHistEventoClinico.setVisible(true);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablaEventosClinicos.getModel());
		tablaEventosClinicos.setRowSorter(sorter);
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		
		int columnDiasVida = 2;
		sortKeys.add(new RowSorter.SortKey(columnDiasVida, SortOrder.DESCENDING));
		
		int columnID = 0;
		sortKeys.add(new RowSorter.SortKey(columnID, SortOrder.ASCENDING));
		 
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		
		this.frame = frmHistEventoClinico;
			
	}
	
	private JTable cargarTablaEventosClinicos(List<EventoClinico> list) throws NamingException, ServiciosException {

		List<EventoClinico> eventos;

		EventoClinicoBeanRemote eventoBean = (EventoClinicoBeanRemote) InitialContext
				.doLookup("PTI-Crianza/EventoClinicoBean!com.servicios.EventoClinicoBeanRemote");
		
		eventos = eventoBean.obtenerTodos();
		

		String[] nombreColumnas = { "ID", "Fecha de nacimiento", "Dias de vida", "Fecha Desde", "Fecha Hasta" };

		Object[][] datos = new Object[eventos.size()][5];

		int fila = 0;

		for (EventoClinico ec : eventos) {

			datos[fila][0] = ec.getTernero().getIdTernero();
			datos[fila][1] = ec.getTernero().getFechaNac();
			
			Date fechaNac = ec.getTernero().getFechaNac();
			Date fechaDesde = ec.getFechaDesde();
					
			long diasVida = ((int)((fechaDesde.getTime()/(24*60*60*1000)) -
		    		(int)(fechaNac.getTime()/(24*60*60*1000))));
			
			datos[fila][2] = diasVida;
			datos[fila][3] = ec.getFechaDesde();
			datos[fila][4] = ec.getFechaHasta();
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

				for (int row = 0; row < getRowCount(); row++) {

					Object o = getValueAt(row, columnIndex);

					if (o != null)
						return o.getClass();
				}
				return Object.class;
			}
		};

		JTable table = new JTable(model);
		table.setAutoscrolls(true);
		table.setCellSelectionEnabled(false);
		table.setSize(500, 250);
		table.setRowSelectionAllowed(true);
		
		table.setAutoCreateRowSorter(true);
		
		this.tablaEventosClinicos = table;

		return table;
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}