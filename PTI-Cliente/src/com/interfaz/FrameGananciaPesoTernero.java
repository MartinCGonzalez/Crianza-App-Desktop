package com.interfaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.entities.Peso;
import com.entities.Ternero;
import com.enums.TipoRegistroPeso;
import com.exceptions.GananciaPesoNegativaNulaException;
import com.exceptions.RegistroPesoVacioException;
import com.exceptions.ServiciosException;
import com.servicios.PesoBeanRemote;
import com.servicios.TernerosBeanRemote;
import com.validaciones.ValidacionDatos;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FrameGananciaPesoTernero implements ActionListener {

	private JFrame frame;

	private JLabel labelIdTernero;
	private JLabel labelPeso;
	private JDatePickerImpl datePicker;

	private JTable tablaTernero;

	private JButton buttonCancelar;

	private JLabel labelTipoRegistro;
	private JLabel labelFecha;

	private JTextField textIdTernero;
	private JComboBox<TipoRegistroPeso> comboBox;
	private JTextField textPeso;

	private JButton btnIngresar;
	private JTextField textField_1;

	public FrameGananciaPesoTernero(JFrame framePadre) throws NamingException, SQLException, ServiciosException {

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

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(22, 280, 89, 23);
		btnIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(146, 297, 89, 24);
		buttonCancelar.addActionListener(this);

		this.btnIngresar = btnIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);

	}

	private void initalizeFrame(JFrame framePadre) throws NamingException, SQLException {

		JFrame frmGananciaPeso = new JFrame("Historico de Ganancia de peso");
		frmGananciaPeso.setTitle("Ganancia de Peso por Ternera");
		frmGananciaPeso.setSize(352, 361);
		frmGananciaPeso.setResizable(false);
		frmGananciaPeso.setLocationRelativeTo(null);
		frmGananciaPeso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGananciaPeso.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(346, 332);

		formularioPanel.setLayout(null);
		formularioPanel.add(buttonCancelar);

		formularioPanel.setLayout(null);

		this.tablaTernero = this.cargarTablaTernero();
		JScrollPane pane1 = new JScrollPane(this.tablaTernero);
		pane1.setSize(326, 117);
		pane1.setLocation(10, 11);
		Dimension dim1 = this.tablaTernero.getPreferredSize();
		pane1.setPreferredSize(
				new Dimension(dim1.width, this.tablaTernero.getRowHeight() * this.tablaTernero.getRowCount() + 1));
		formularioPanel.add(pane1);

		this.datePicker = this.creareDatePicker();
		formularioPanel.add(this.datePicker);
		formularioPanel.add(buttonCancelar);

		frmGananciaPeso.getContentPane().add(formularioPanel);

		frmGananciaPeso.getContentPane().add(formularioPanel);

		textPeso = new JTextField();
		textPeso.setBounds(60, 46, 86, 20);

		textField_1 = new JTextField();
		textField_1.setBounds(120, 232, 115, 20);
		formularioPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblTipoDeRegistro = new JLabel("Tipo de Registro");
		lblTipoDeRegistro.setBounds(10, 170, 100, 14);
		formularioPanel.add(lblTipoDeRegistro);

		comboBox = new JComboBox<TipoRegistroPeso>();
		comboBox.setBounds(120, 162, 115, 20);
		comboBox.setModel(new DefaultComboBoxModel<>(TipoRegistroPeso.values()));
		formularioPanel.add(comboBox);

		JLabel lblFechaDeRegistro = new JLabel("Fecha de Registro");
		lblFechaDeRegistro.setBounds(10, 201, 100, 14);
		formularioPanel.add(lblFechaDeRegistro);

		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(10, 232, 46, 14);
		formularioPanel.add(lblPeso);

		formularioPanel.add(btnIngresar);
		formularioPanel.add(buttonCancelar);

		JButton btnHistorial = new JButton("Historial");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new FrameHistoricoPeso(frame);
				} catch (NamingException | ServiciosException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnHistorial.setBounds(146, 263, 89, 23);
		formularioPanel.add(btnHistorial);
		textPeso.setColumns(10);

		frmGananciaPeso.setVisible(true);

		this.frame = frmGananciaPeso;

	}

	private JDatePickerImpl creareDatePicker() {

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

		datePicker.setBounds(120, 192, 115, 30);
		return datePicker;
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

		String[] nombreColumnas = { "ID Ternero", "Nro Caravana", "Guachera" };
		Object[][] datos = new Object[terneros.size()][3];

		int fila = 0;

		for (Ternero t : terneros) {

			datos[fila][0] = t.getIdTernero();
			datos[fila][1] = t.getNroCaravana();
			datos[fila][2] = t.getGuachera();

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

		this.tablaTernero = table;

		return table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else if (e.getSource() == this.btnIngresar) {
			try {
				this.accionIngesar();
			} catch (NamingException | ServiciosException | RegistroPesoVacioException
					| GananciaPesoNegativaNulaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private void accionIngesar()
			throws NamingException, ServiciosException, RegistroPesoVacioException, GananciaPesoNegativaNulaException {

		TernerosBeanRemote ternerosBean = (TernerosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/TernerosBean!com.servicios.TernerosBeanRemote");

		PesoBeanRemote pesoBean = (PesoBeanRemote) InitialContext
				.doLookup("PTI-Crianza/PesoBean!com.servicios.PesoBeanRemote");

		int filaSeleccionadaTernero = this.tablaTernero.getSelectedRow();

		if (filaSeleccionadaTernero < 0) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un ternero.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// TERNERO
		Long idTernero = (Long) this.tablaTernero.getValueAt(filaSeleccionadaTernero, 0);
		Ternero ternero = new Ternero();

		try {
			ternero = ternerosBean.obtenerTernero(idTernero);

		} catch (ServiciosException ex) {
			ex.printStackTrace();
		}

		TipoRegistroPeso tipoRegistroPeso = (TipoRegistroPeso) comboBox.getSelectedItem();

		String tipoRegistro = String.valueOf(tipoRegistroPeso);

		Date fecha = (Date) this.datePicker.getModel().getValue();
		
		boolean pase = true;
		if (fecha == null && pase) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar la fecha!", "ERROR", JOptionPane.WARNING_MESSAGE);
			pase = false;
		}

		else if (pase) {
			try {

				Double.parseDouble(textField_1.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "La cantidad debe de ser un numero!", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		double peso = Double.parseDouble(textField_1.getText());

		BigDecimal peso2 = BigDecimal.valueOf(peso);

		double difPeso = 0;
		
		
		try {

			if (ValidacionDatos.validarPeso(peso2, fecha)) {
				Peso p = new Peso(fecha, peso2, tipoRegistro, ternero, idTernero);
				pesoBean.alta(p);
				
				
				if (ternero.getListaRegPeso().size() < 1) {

					double pesoNac = ternero.getPesoNac().doubleValue();

					double difPeso1 = peso - pesoNac;
					


					JOptionPane.showMessageDialog(null, "La ganancia de peso es de " + difPeso1 + " .",
							"CONFIRMACION | SOLICITUD COMPLETADA", JOptionPane.INFORMATION_MESSAGE);

				} else {
					
					ternero.getListaRegPeso().add(p);
					difPeso = ternerosBean.informeGananciaPeso(ternero);
					

					
					JOptionPane.showMessageDialog(null, "La ganancia de peso es de " + difPeso + "registrado",
							"CONFIRMACION | SOLICITUD COMPLETADA", JOptionPane.INFORMATION_MESSAGE);
				}
				
				JOptionPane.showMessageDialog(null, "Peso Registrado", "CONFIRMACION | SOLICITUD COMPLETADA",
						JOptionPane.INFORMATION_MESSAGE);

				if(difPeso <= 0 ) {
					JOptionPane.showMessageDialog(null, "El ternero no muestra ganancia de peso frente a registro anterior, se recomienda dar una revision inmediata", "ERROR",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ADVERTENCIA | SOLICITUD NO COMPLETADA",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

	}

	private void accionCancelar() {
		this.frame.dispose();
	}

}
