package com.interfaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowSorter;

import com.entities.Enfermedad;
import com.entities.EventoClinico;
import com.entities.Ternero;
import com.exceptions.EnfermedadException;
import com.exceptions.FormatoFechaNoValido;
import com.exceptions.ServiciosException;
import com.servicios.EnfermedadBeanRemote;
import com.servicios.EventoClinicoBeanRemote;
import com.servicios.TernerosBeanRemote;
import com.validaciones.ValidacionDatos;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FrameAltaEventoClinico implements ActionListener {

	private JFrame frame;

	private JLabel lblNombre;
	private JLabel lblGravedad;
	private JLabel lblObservacion;
	private JTextField textIdTernero;

	private JDatePickerImpl datePickerFechaDesde;
	private JDatePickerImpl datePickerFechaHasta;

	private JButton buttonIngresar;
	private JButton buttonCancelar;
	private JTextField textIdEnfermedad;
	private JTextArea textObservaciones;
	
	private JTable tablaEnfermedad;

	public FrameAltaEventoClinico(JFrame framePadre) throws NamingException {

		this.lblNombre = new JLabel("Fecha Desde :");
		lblNombre.setBounds(6, 237, 92, 14);

		this.lblGravedad = new JLabel("Fecha Hasta :");
		lblGravedad.setBounds(9, 273, 92, 14);

		this.lblObservacion = new JLabel("Observacion:");
		lblObservacion.setBounds(10, 36, 92, 14);

		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.setSize(100, 25);
		buttonIngresar.setLocation(36, 453);
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(36, 489, 100, 25);
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(frame);

	}

	public void initalizeFrame(JFrame framePadre) throws NamingException {

		JFrame frmEventoClinico = new JFrame("Nuevo Evento Clinico");
		frmEventoClinico.setTitle("Nuevo Evento Clinico");
		frmEventoClinico.setSize(334, 600);
		frmEventoClinico.setResizable(false);
		frmEventoClinico.setLocationRelativeTo(null);
		frmEventoClinico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEventoClinico.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setToolTipText("");
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(328, 536);
		
		this.tablaEnfermedad = this.cargarTablaTernero();
		JScrollPane pane1 = new JScrollPane(this.tablaEnfermedad);
		pane1.setSize(315, 131);
		pane1.setLocation(6, 85);
		Dimension dim1 = this.tablaEnfermedad.getPreferredSize();
		pane1.setPreferredSize(
				new Dimension(dim1.width, this.tablaEnfermedad.getRowHeight() * this.tablaEnfermedad.getRowCount() + 1));
		formularioPanel.add(pane1);

		formularioPanel.add(lblNombre);
		formularioPanel.add(lblGravedad);

		this.datePickerFechaDesde = this.creareDatePicker();
		formularioPanel.add(this.datePickerFechaDesde);

		this.datePickerFechaHasta = this.creareDatePickerHasta();
		formularioPanel.add(this.datePickerFechaHasta);

		formularioPanel.add(buttonIngresar);
		formularioPanel.add(buttonCancelar);

		formularioPanel.setLayout(null);

		frmEventoClinico.getContentPane().add(formularioPanel);

		JLabel lblObservaciones = new JLabel("Observaciones :");
		lblObservaciones.setBounds(11, 314, 86, 14);
		formularioPanel.add(lblObservaciones);

		JLabel lblEnfermedad = new JLabel("Seleccionar Enfermedad:");
		lblEnfermedad.setBounds(10, 57, 168, 14);
		formularioPanel.add(lblEnfermedad);

		textIdTernero = new JTextField();
		textIdTernero.setToolTipText("");
		textIdTernero.setBounds(98, 21, 80, 20);
		formularioPanel.add(textIdTernero);
		textIdTernero.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(11, 344, 309, 95);
		formularioPanel.add(scrollPane);

		textObservaciones = new JTextArea();
		scrollPane.setViewportView(textObservaciones);
		textObservaciones.setWrapStyleWord(true);
		textObservaciones.setLineWrap(true);

		JLabel lblTernero = new JLabel("Ternero :");
		lblTernero.setBounds(11, 26, 64, 14);
		formularioPanel.add(lblTernero);

		JButton btnListadoTernero = new JButton("Terneros");
		btnListadoTernero.setToolTipText("Listado de Terneros");
		btnListadoTernero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					new FrameListadoTernero(frame);
				} catch (NamingException | SQLException | ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListadoTernero.setBounds(162, 454, 115, 23);
		formularioPanel.add(btnListadoTernero);

		JButton btnListadoEnfermedad = new JButton("Enfermedad");
		btnListadoEnfermedad.setToolTipText("Listado de Enfermedades");
		btnListadoEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					new FrameListadoEnfermedad(frame);
				} catch (NamingException | ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListadoEnfermedad.setBounds(162, 490, 115, 23);
		formularioPanel.add(btnListadoEnfermedad);

		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablaEnfermedad.getModel());
		tablaEnfermedad.setRowSorter(sorter);
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		
		int idEnfermedad = 0;
		sortKeys.add(new RowSorter.SortKey(idEnfermedad, SortOrder.ASCENDING));
	
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		
		frmEventoClinico.setVisible(true);

		this.frame = frmEventoClinico;

	}
	
	private JTable cargarTablaTernero() throws NamingException {

		List<Enfermedad> enfermedades = null;

		EnfermedadBeanRemote enfermedadesBean = (EnfermedadBeanRemote) InitialContext
				.doLookup("PTI-Crianza/EnfermedadBean!com.servicios.EnfermedadBeanRemote");
		try {
			enfermedades = enfermedadesBean.obtenerTodos();
		} catch (ServiciosException e) {
			e.printStackTrace();
		}

		String[] nombreColumnas = { "ID Enfermedad", "Nombre", "Gravedad"};
		Object[][] datos = new Object[enfermedades.size()][3];

		int fila = 0;

		for (Enfermedad e: enfermedades) {

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

		this.tablaEnfermedad = table;

		return table;
	}



	private JDatePickerImpl creareDatePicker() {

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.getJFormattedTextField().setEditable(true);
		datePicker.setBounds(95, 227, 115, 30);
		return datePicker;
	}

	private JDatePickerImpl creareDatePickerHasta() {

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePickerFechaHasta = new JDatePickerImpl(datePanel);
		datePickerFechaHasta.getJFormattedTextField().setEditable(true);
		datePickerFechaHasta.setBounds(98, 270, 115, 30);
		return datePickerFechaHasta;
	}

	private void accionIngesar()
			throws NamingException, ServiciosException, EnfermedadException, ParseException, FormatoFechaNoValido {

		EnfermedadBeanRemote enfermedadBean = (EnfermedadBeanRemote) InitialContext
				.doLookup("PTI-Crianza/EnfermedadBean!com.servicios.EnfermedadBeanRemote");

		TernerosBeanRemote ternerosBean = (TernerosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/TernerosBean!com.servicios.TernerosBeanRemote");

		@SuppressWarnings("unused")
		String idEnfermedad = textIdEnfermedad.getText().trim();

		String idTernero1 = textIdTernero.getText().trim();

		String observacion = textObservaciones.getText();

		if (/*idEnfermedad.isEmpty() && */idTernero1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Es necesario ingresar todos los datos requeridos!", "ADVERTENCIA",
					JOptionPane.WARNING_MESSAGE);

		} /*else if (idEnfermedad.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar el ID Enfermedad!", "ADVERTENCIA",
					JOptionPane.WARNING_MESSAGE);

		} */else if (idTernero1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar el ID Ternero!", "ADVERTENCIA",
					JOptionPane.WARNING_MESSAGE);

		}
		
		/*
		try {
			Long.parseLong(idEnfermedad);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El ID Enfermedad debe de ser un numero!", "ADVERTENCIA",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		*/
		
		try {
			Long.parseLong(idTernero1);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar la fecha de inicio!", "ADVERTENCIA",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

		//Long idEnfermedad1 = Long.parseLong(idEnfermedad);
		
		int filaSeleccionadaEnfermedad = this.tablaEnfermedad.getSelectedRow();

		if (filaSeleccionadaEnfermedad < 0) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un ternero.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// TERNERO
		Long idEnfermedad1 = (Long) this.tablaEnfermedad.getValueAt(filaSeleccionadaEnfermedad, 0);

		Enfermedad enfermedad = enfermedadBean.obtenerEnfermedad(idEnfermedad1);

		Long idTernero = Long.parseLong(idTernero1);

		Ternero ternero = ternerosBean.obtenerTernero(idTernero);

		Date fechaDesde = (Date) this.datePickerFechaDesde.getModel().getValue();

		if (fechaDesde == null) {
			JOptionPane.showMessageDialog(null, "Debe de seleccionar una fecha!", "ADVERTENCIA",
					JOptionPane.INFORMATION_MESSAGE);
		}

		Date fechaHasta = (Date) this.datePickerFechaHasta.getModel().getValue();

//		if (idTernero != null && idEnfermedad != null && fechaDesde != null) {
//			try {
//				if (ValidacionDatos.validarEventoClinico(idTernero, fechaDesde, idEnfermedad1, observacion)) {
//
//					EventoClinicoBeanRemote eventoClinicoBean;
//					eventoClinicoBean = (EventoClinicoBeanRemote) InitialContext
//							.doLookup("PTI-Crianza/EventoClinicoBean!com.servicios.EventoClinicoBeanRemote");
//
//					EventoClinico ec = new EventoClinico(fechaDesde, enfermedad, ternero);
//
//					eventoClinicoBean.guardarEventoClinico(ec);
//					ternerosBean.guardarDiasVida(ternero);
//
//					JOptionPane.showMessageDialog(null, "Evento registrado", "CONFIRMACION | SOLICITUD COMPLETADA",
//							JOptionPane.INFORMATION_MESSAGE);
//				}
//
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, e.getMessage(), "ADVERTENCIA | SOLICITUD NO COMPLETADA",
//						JOptionPane.INFORMATION_MESSAGE);
//				e.printStackTrace();
//			}
//		}

		if (ternero != null && enfermedad != null && fechaDesde != null && fechaHasta != null) {
			try {
				if (ValidacionDatos.validarEventoClinico2(idTernero, fechaDesde, fechaHasta, idEnfermedad1,
						observacion)) {

					EventoClinicoBeanRemote eventoClinicoBean;
					eventoClinicoBean = (EventoClinicoBeanRemote) InitialContext
							.doLookup("PTI-Crianza/EventoClinicoBean!com.servicios.EventoClinicoBeanRemote");
					EventoClinico ec = new EventoClinico(fechaDesde, enfermedad, ternero, fechaHasta, observacion);
					try {

						if (fechaHasta.before(fechaDesde)) {
							JOptionPane.showMessageDialog(null, "La fecha Desde no puede ser menor a la fecha Hasta ",
									"ERROR", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "ADVERTENCIA | SOLICITUD NO COMPLETADA",
								JOptionPane.INFORMATION_MESSAGE);
						e.printStackTrace();
					}
					eventoClinicoBean.guardarEventoClinico(ec);
					ternerosBean.guardarDiasVida(ternero);
					JOptionPane.showMessageDialog(null, "Evento registrado", "CONFIRMACION | SOLICITUD COMPLETADA",
							JOptionPane.INFORMATION_MESSAGE);

				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ADVERTENCIA | SOLICITUD NO COMPLETADA",
						JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
			}
		}

	}

	private void accionCancelar() {
		this.frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
			try {
				this.accionIngesar();
			} catch (NamingException | ServiciosException | EnfermedadException | ParseException
					| FormatoFechaNoValido e1) {
				e1.printStackTrace();
			}
		}
	}
}
