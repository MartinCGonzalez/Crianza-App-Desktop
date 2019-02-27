package com.interfaz;

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
import com.entities.Unidad;
import com.enums.TipoUnidadAlimento;
import com.exceptions.ServiciosException;
import com.servicios.AlimentosBeanRemote;
import com.servicios.UnidadBeanRemote;
import com.validaciones.ValidacionDatos;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

public class FrameModificarAlimento implements ActionListener {

	private JFrame frame;
	private JTextField textCosto;
	private JTextField textCantidad;

	private JLabel lblNombre;
	private JLabel lblCostoPorUnidad;
	private JLabel lblCantidad;

	private JTable tablaAlimento;

	private JButton buttonCancelar;
	private JTextField textNombre;
	private JTextField textId;
	private JTextField textUnidad;
	private JLabel lblUnidad;

	public FrameModificarAlimento(JFrame framePadre) throws NamingException {

		this.lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(11, 183, 66, 14);

		this.lblCostoPorUnidad = new JLabel("Costo por unidad:");
		lblCostoPorUnidad.setBounds(11, 211, 92, 14);

		this.lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(11, 245, 66, 14);

		JButton buttonIngresar = new JButton("Actualizar");
		buttonIngresar.setSize(100, 25);
		buttonIngresar.setLocation(332, 179);
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(332, 234, 100, 25);
		buttonCancelar.addActionListener(this);

		JFrame frmModAlimento = new JFrame("Modificar alimento");
		frmModAlimento.setTitle("Modificar alimento");
		frmModAlimento.setSize(484, 348);
		frmModAlimento.setResizable(false);
		frmModAlimento.setLocationRelativeTo(null);
		frmModAlimento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModAlimento.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(478, 319);

		formularioPanel.add(lblCantidad);
		formularioPanel.add(lblCostoPorUnidad);
		formularioPanel.add(lblNombre);

		tablaAlimento = new JTable();
		cargarTablaAlimento();
		JScrollPane pane1 = new JScrollPane(tablaAlimento);
		pane1.setSize(444, 126);
		pane1.setLocation(10, 11);

		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setBounds(10, 150, 80, 14);
		formularioPanel.add(lblIdentificador);

		lblUnidad = new JLabel("Unidad:");
		lblUnidad.setBounds(11, 273, 46, 14);
		formularioPanel.add(lblUnidad);
		formularioPanel.add(pane1);

		formularioPanel.add(buttonIngresar);
		formularioPanel.add(buttonCancelar);

		formularioPanel.setLayout(null);

		frmModAlimento.getContentPane().add(formularioPanel);

		textCosto = new JTextField();
		textCosto.setBounds(136, 211, 139, 20);

		formularioPanel.add(textCosto);

		textCantidad = new JTextField();
		textCantidad.setBounds(136, 242, 139, 20);
		formularioPanel.add(textCantidad);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setBounds(136, 178, 139, 20);
		formularioPanel.add(textNombre);
		textNombre.setColumns(10);

		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(136, 148, 139, 20);
		formularioPanel.add(textId);
		textId.setColumns(10);

		textUnidad = new JTextField();
		textUnidad.setEditable(false);
		textUnidad.setBounds(136, 270, 139, 20);
		formularioPanel.add(textUnidad);
		textUnidad.setColumns(10);

		frmModAlimento.setVisible(true);

		this.frame = frmModAlimento;

		this.buttonCancelar = buttonCancelar;

	}

	private void cargarTablaAlimento() throws NamingException {

		List<Alimento> alimentos = null;

		AlimentosBeanRemote alimentoBean = (AlimentosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/AlimentosBean!com.servicios.AlimentosBeanRemote");
		try {
			alimentos = alimentoBean.obtenerTodos();
		} catch (ServiciosException e) {
			e.printStackTrace();
		}

		String[] nombreColumnas = { "ID", "Nombre", "Costo Unidad", "Cantidad", "Unidad" };

		Object[][] datos = new Object[alimentos.size()][5];

		int fila = 0;

		for (Alimento a : alimentos) {

			datos[fila][0] = a.getIdAlimento();
			datos[fila][1] = a.getNombre();
			datos[fila][2] = a.getCostoUnidad();
			datos[fila][3] = a.getCantidad();
			datos[fila][4] = a.getUnidad().getUnidad();
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

				DefaultTableModel model = (DefaultTableModel) tablaAlimento.getModel();

				int filaSeleccionadaAlimento = tablaAlimento.getSelectedRow();

				textId.setText(model.getValueAt(filaSeleccionadaAlimento, 0).toString());
				textId.setEditable(false);
				textNombre.setText(model.getValueAt(filaSeleccionadaAlimento, 1).toString());
				textNombre.setEditable(false);
				textCosto.setText(model.getValueAt(filaSeleccionadaAlimento, 2).toString());
				textCantidad.setText(model.getValueAt(filaSeleccionadaAlimento, 3).toString());
				textUnidad.setText(model.getValueAt(filaSeleccionadaAlimento, 4).toString());
				textUnidad.setEditable(false);

				model.getRowCount();

			}

		});

		table.setAutoscrolls(true);
		table.setCellSelectionEnabled(false);
		table.setSize(500, 250);
		table.setRowSelectionAllowed(true);

		this.tablaAlimento = table;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
			try {
				this.accionIngesar();
			} catch (NamingException | ServiciosException e1) {
				e1.printStackTrace();
			}

		}

	}

	private void accionIngesar() throws NamingException, ServiciosException {

		// ANOTACION
		// Se tiene que volver a seleccionar la fila para actualziar, debe de haber una
		// forma de mantener la fila seleccionada.

		int filaSeleccionadaAlimento = this.tablaAlimento.getSelectedRow();

		if (filaSeleccionadaAlimento < 0) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un alimento.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		AlimentosBeanRemote alimentoBean = (AlimentosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/AlimentosBean!com.servicios.AlimentosBeanRemote");

		UnidadBeanRemote unidadBean = (UnidadBeanRemote) InitialContext
				.doLookup("PTI-Crianza/UnidadBean!com.servicios.UnidadBeanRemote");

		// Alimento

		String ida = textId.getText();

		long idAlimento = Long.parseLong(ida);

		Alimento a = alimentoBean.obtenerAlimento(idAlimento);

		String nombre = textNombre.getText().trim();

		String cantidad = textCantidad.getText().trim();

		String costoUni = textCosto.getText().trim();

		if (costoUni.isEmpty() && cantidad.isEmpty()) {
			JOptionPane.showMessageDialog(null, "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if (cantidad.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar la cantidad!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if (costoUni.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar el costo!", "ERROR", JOptionPane.WARNING_MESSAGE);
		}

		try {
			Double.parseDouble(cantidad);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "La cantidad debe de ser un numero!", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

		try {
			Double.parseDouble(costoUni);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El costo debe de ser un numero!", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

		Double cantidadAlimento = Double.parseDouble(cantidad);

		Double costoAlimento = Double.parseDouble(costoUni);

		String nombreUni = textUnidad.getText().toUpperCase().toString();

		TipoUnidadAlimento tipo = TipoUnidadAlimento.valueOf(nombreUni);

		long idUnidad = (long) tipo.getId();

		BigDecimal costoUnit = BigDecimal.valueOf(costoAlimento);

		BigDecimal cantidadAlime = BigDecimal.valueOf(cantidadAlimento);

		Unidad unidadAli = unidadBean.obtenerUnidad(idUnidad);

		// SETEO LOS NUEVOS ATRIBUTOS A MI ALIMENTO
		try {
			if (ValidacionDatos.validarActualizacion(costoUnit, cantidadAlime)) {

				a.setNombre(nombre);
				a.setCostoUnidad(costoUnit);
				a.setCantidad(cantidadAlime);
				a.setUnidad(unidadAli);
				alimentoBean.actualizar(a);
				recargarTabla();

				JOptionPane.showMessageDialog(null, "Alimento actualizado", "CONFIRMACION | SOLICITUD COMPLETADA",
						JOptionPane.INFORMATION_MESSAGE);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ADVERTENCIA", "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

		DefaultTableModel model = (DefaultTableModel) tablaAlimento.getModel();
		if (filaSeleccionadaAlimento < 0) {
			model.setRowCount(filaSeleccionadaAlimento);
		}

	}

	private void accionCancelar() {
		this.frame.dispose();
	}

	private void recargarTabla() {

		// Despues de actualizar recargo las filas de la tablas

		int i = tablaAlimento.getSelectedRow();

		DefaultTableModel model = (DefaultTableModel) tablaAlimento.getModel();

		if (i >= 0) {
			model.setValueAt(textId.getText(), i, 0);
			model.setValueAt(textNombre.getText(), i, 1);
			model.setValueAt(textCosto.getText(), i, 2);
			model.setValueAt(textCantidad.getText(), i, 3);
			model.setValueAt(textUnidad.getText(), i, 4);
			model.fireTableDataChanged();

		} else {
			System.out.println("Update Error");
		}
	}

}
