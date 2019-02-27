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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.entities.Alimento;
import com.entities.ConsumoAlimento;
import com.entities.Ternero;
import com.entities.Unidad;
import com.enums.TipoAlimento;
import com.exceptions.ServiciosException;
import com.servicios.AlimentosBeanRemote;
import com.servicios.ConsumoAlimentosBeanRemote;
import com.servicios.TernerosBeanRemote;
import com.servicios.UnidadBeanRemote;
import com.validaciones.ValidacionDatos;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FrameRegistroConsumoAlimento implements ActionListener {

	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelFecha;
	private JLabel labelCantidad;
	private JLabel labelIdTernero;

	/** Atributos de TexField */
	private JTextField textCantidad;
	private JTable tablaTernero;
	private JTable tablaAlimento;

	/** Date Picker */
	private JDatePickerImpl datePicker;

	/** Botones */
	private JButton buttonIngresar;
	private JButton buttonCancelar;
	private JButton btnRefreshTernero;

	public FrameRegistroConsumoAlimento(JFrame framePadre) throws NamingException {

		this.labelFecha = new JLabel("Fecha:");
		this.labelCantidad = new JLabel("Cantidad:");
		this.labelIdTernero = new JLabel("ID Ternero:");
		labelCantidad.setBounds(20, 205, 100, 20);
		labelFecha.setBounds(20, 236, 100, 20);
		labelIdTernero.setBounds(10, 108, 100, 20);

		this.textCantidad = new JTextField(15);
		textCantidad.setBounds(130, 208, 115, 20);

		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.setSize(89, 23);
		buttonIngresar.setLocation(20, 277);
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(130, 277, 89, 23);
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);

	}

	private void initalizeFrame(JFrame framePadre) throws NamingException {

		JFrame frmRegistroDelConsumo = new JFrame("Nuevo registro");
		frmRegistroDelConsumo.setTitle("Registro del consumo de alimento");
		frmRegistroDelConsumo.setSize(600, 377);
		frmRegistroDelConsumo.setResizable(false);
		frmRegistroDelConsumo.setLocationRelativeTo(null);
		frmRegistroDelConsumo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistroDelConsumo.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(592, 348);

		formularioPanel.add(labelFecha);
		formularioPanel.add(labelCantidad);

		formularioPanel.add(textCantidad);
		this.tablaTernero = this.cargarTablaTernero();
		JScrollPane pane1 = new JScrollPane(this.tablaTernero);
		pane1.setSize(225, 170);
		pane1.setLocation(357, 11);
		Dimension dim1 = this.tablaTernero.getPreferredSize();
		pane1.setPreferredSize(
				new Dimension(dim1.width, this.tablaTernero.getRowHeight() * this.tablaTernero.getRowCount() + 1));
		formularioPanel.add(pane1);

		btnRefreshTernero = new JButton("Refrescar");
		btnRefreshTernero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					tablaTernero = cargarTablaTernero();
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
				tablaTernero.revalidate();
				tablaTernero.repaint();

				pane1.setSize(225, 170);
				pane1.setLocation(357, 11);
				Dimension dim1 = tablaTernero.getPreferredSize();
				pane1.setPreferredSize(
						new Dimension(dim1.width, tablaTernero.getRowHeight() * tablaTernero.getRowCount() + 1));

				formularioPanel.add(pane1);
			}
		});
		btnRefreshTernero.setBounds(367, 192, 100, 20);
		formularioPanel.add(btnRefreshTernero);

		this.tablaAlimento = this.cargarTablaAlimento();

		JScrollPane pane2 = new JScrollPane(this.tablaAlimento);
		pane2.setSize(341, 170);
		pane2.setLocation(10, 11);
		Dimension dim2 = this.tablaAlimento.getPreferredSize();
		pane2.setPreferredSize(
				new Dimension(dim2.width, this.tablaAlimento.getRowHeight() * this.tablaAlimento.getRowCount() + 1));
		formularioPanel.add(pane2);

		formularioPanel.setLayout(null);

		this.datePicker = this.creareDatePicker();
		formularioPanel.add(this.datePicker);
		formularioPanel.add(buttonIngresar);
		formularioPanel.add(buttonCancelar);

		frmRegistroDelConsumo.getContentPane().add(formularioPanel);

		JButton btnCrear = new JButton("Nuevo Ternero");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					new FrameAltaTernero(frame);
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		btnCrear.setBounds(357, 230, 120, 23);
		formularioPanel.add(btnCrear);

		JButton btnHistorial = new JButton("Historial");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					new FrameHistoricoTernero(frame);
				} catch (NamingException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnHistorial.setBounds(487, 230, 89, 23);
		formularioPanel.add(btnHistorial);

		frmRegistroDelConsumo.setVisible(true);

		this.frame = frmRegistroDelConsumo;

	}

	private JDatePickerImpl creareDatePicker() {

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(130, 236, 115, 30);
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

	private JTable cargarTablaAlimento() throws NamingException {

		List<Alimento> alimentos = null;

		AlimentosBeanRemote alimentoBean = (AlimentosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/AlimentosBean!com.servicios.AlimentosBeanRemote");
		try {
			alimentos = alimentoBean.obtenerTodos();
		} catch (ServiciosException e) {
			e.printStackTrace();
		}

		String[] nombreColumnas = { "Nombre", "Cantidad", "Costo Unidad", "Unidad" };

		Object[][] datos = new Object[alimentos.size()][4];

		int fila = 0;

		for (Alimento a : alimentos) {

			datos[fila][0] = a.getNombre();
			datos[fila][1] = a.getCantidad();
			datos[fila][2] = a.getCostoUnidad();
			datos[fila][3] = a.getUnidad().getUnidad();
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

		this.tablaAlimento = table;

		return table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
			try {
				this.accionIngesar();
			} catch (NamingException | ServiciosException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private void accionIngesar() throws NamingException, ServiciosException {

		// Instancias
		ConsumoAlimentosBeanRemote consumoAlimentosBean = (ConsumoAlimentosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/ConsumoAlimentosBean!com.servicios.ConsumoAlimentosBeanRemote");

		TernerosBeanRemote ternerosBean = (TernerosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/TernerosBean!com.servicios.TernerosBeanRemote");

		AlimentosBeanRemote alimentosBean = (AlimentosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/AlimentosBean!com.servicios.AlimentosBeanRemote");

		UnidadBeanRemote unidadBean = (UnidadBeanRemote) InitialContext
				.doLookup("PTI-Crianza/UnidadBean!com.servicios.UnidadBeanRemote");

		int filaSeleccionadaTernero = this.tablaTernero.getSelectedRow();

		int filaSeleccionadaAlimento = this.tablaAlimento.getSelectedRow();

		if (filaSeleccionadaAlimento < 0) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un alimento.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

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

		// DATOS
		Date fecha = (Date) this.datePicker.getModel().getValue();

		String cantidad = textCantidad.getText().trim();

		boolean pase = true;

		if (cantidad.isEmpty() && pase) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar la cantidad!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			pase = false;
		}

		else if (fecha == null && pase) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar la fecha!", "ERROR", JOptionPane.WARNING_MESSAGE);
			pase = false;
		}

		else if (pase) {
			try {

				Double.parseDouble(cantidad);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "La cantidad debe de ser un numero!", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
			}
		}

		Double cantidadAlimento = Double.parseDouble(cantidad);

		BigDecimal cantidadAlime = BigDecimal.valueOf(cantidadAlimento);

		// NOMBRE DEL ALIMENTO
		String nombreAli = (String) this.tablaAlimento.getValueAt(filaSeleccionadaAlimento, 0);

		Alimento a = alimentosBean.obtenerPorNombre(nombreAli);

		TipoAlimento tipo = TipoAlimento.valueOf(nombreAli);

		long idAlimento = (long) a.getIdAlimento();

		long idUnidad = (long) tipo.getIdUnidad();

		Unidad unidad = unidadBean.obtenerUnidad(idUnidad);

		// ALIMENTO
		Alimento alimento = new Alimento();

		try {
			alimento = alimentosBean.obtenerAlimento((long) idAlimento);

		} catch (ServiciosException ex) {
			ex.printStackTrace();
		}

		String nombre = alimento.getNombre();

		BigDecimal stockAlimento = alimento.getCantidad();

		double d = stockAlimento.doubleValue();

		Double cantidadRestante = d - cantidadAlimento;

		if (cantidadAlimento > d) {
			JOptionPane.showMessageDialog(null,
					"Solamente tienes " + stockAlimento + " de " + nombre + " para actualizar!",
					"Advertencia: Consumo de alimento", JOptionPane.INFORMATION_MESSAGE);
		}

		BigDecimal c1 = BigDecimal.valueOf(cantidadRestante);

		// Validacion

		try {
			if (ValidacionDatos.validarConsumoAlimento(cantidadAlime, fecha)) {
				ConsumoAlimento c = new ConsumoAlimento(cantidadAlime, fecha, alimento, unidad, ternero);
				consumoAlimentosBean.alta(c);
				// Actualizo el alimento
				alimento.setCantidad(c1);
				alimentosBean.actualizar(alimento);
				recargarTabla();

				JOptionPane.showMessageDialog(null, "Consumo registrado.", "CONFIRMACION",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo completar el registro", "ADVERTENCIA",
					JOptionPane.INFORMATION_MESSAGE);
			pase = false;
			e.printStackTrace();
		}

		if (cantidadRestante == 0 && pase) {
			JOptionPane.showMessageDialog(null, "Has agotado el stock de " + nombre + "!",
					"Advertencia: Consumo de alimento", JOptionPane.INFORMATION_MESSAGE);
		}

		DefaultTableModel model = (DefaultTableModel) tablaAlimento.getModel();
		if (filaSeleccionadaAlimento < 0) {
			model.setRowCount(filaSeleccionadaAlimento);

			DefaultTableModel model1 = (DefaultTableModel) tablaTernero.getModel();

			if (filaSeleccionadaTernero < 0) {
				model1.setRowCount(filaSeleccionadaTernero);
			}

		}

	}

	private void accionCancelar() {
		this.frame.dispose();

	}

	private void recargarTabla() throws NamingException, ServiciosException {

		int i = tablaAlimento.getSelectedRow();

		DefaultTableModel model = (DefaultTableModel) tablaAlimento.getModel();

		if (i >= 0) {

			int filaSeleccionadaAlimento = this.tablaAlimento.getSelectedRow();

			AlimentosBeanRemote alimentosBean = (AlimentosBeanRemote) InitialContext
					.doLookup("PTI-Crianza/AlimentosBean!com.servicios.AlimentosBeanRemote");

			String idAlimento = (String) this.tablaAlimento.getValueAt(filaSeleccionadaAlimento, 0);
			TipoAlimento tipo = TipoAlimento.valueOf(idAlimento);
			long alimento1 = (long) tipo.getNumero();
			Alimento alimento11 = alimentosBean.obtenerAlimento(alimento1);
			BigDecimal cantidad = alimento11.getCantidad();

			model.setValueAt(cantidad, i, 1);
			model.fireTableDataChanged();

		} else {
			System.out.println("Update Error");
		}
	}
}
