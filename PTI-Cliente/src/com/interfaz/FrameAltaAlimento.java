package com.interfaz;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.entities.Alimento;
import com.entities.Unidad;
import com.enums.TipoAlimento;
import com.exceptions.ServiciosException;
import com.servicios.AlimentosBeanRemote;
import com.servicios.UnidadBeanRemote;
import com.validaciones.ValidacionDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class FrameAltaAlimento implements ActionListener {

	private JFrame frame;
	private JTextField textCostoUni;
	private JTextField textCantidad;

	private JLabel lblNombre;
	private JLabel lblCostoPorUnidad;
	private JLabel lblCantidad;

	private JButton buttonIngresar;
	private JButton buttonCancelar;
	private JTextField textNombreAlimento;
	private JButton btnAyuda;

	public FrameAltaAlimento(JFrame framePadre) throws NamingException {

		this.lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 66, 92, 14);

		this.lblCostoPorUnidad = new JLabel("Costo por unidad:");
		lblCostoPorUnidad.setBounds(10, 97, 115, 14);

		this.lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(10, 122, 92, 14);

		textCostoUni = new JTextField();
		textCostoUni.setColumns(10);
		textCostoUni.setBounds(135, 91, 139, 20);

		textCantidad = new JTextField();
		textCantidad.setColumns(10);
		textCantidad.setBounds(135, 116, 139, 20);

		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.setSize(115, 25);
		buttonIngresar.setLocation(20, 165);
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(159, 165, 115, 25);
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);

	}

	public void initalizeFrame(JFrame framePadre) throws NamingException {

		JFrame frmAltaAlimento = new JFrame("Nuevo alimento");
		frmAltaAlimento.setTitle("Nuevo alimento");
		frmAltaAlimento.setSize(296, 230);
		frmAltaAlimento.setResizable(false);
		frmAltaAlimento.setLocationRelativeTo(null);
		frmAltaAlimento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAltaAlimento.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(290, 201);

		formularioPanel.add(lblCantidad);
		formularioPanel.add(lblCostoPorUnidad);
		formularioPanel.add(lblNombre);

		formularioPanel.add(textCostoUni);
		formularioPanel.add(textCantidad);

		formularioPanel.add(buttonIngresar);
		formularioPanel.add(buttonCancelar);

		formularioPanel.setLayout(null);

		frmAltaAlimento.getContentPane().add(formularioPanel);

		textNombreAlimento = new JTextField();
		textNombreAlimento.setBounds(135, 63, 139, 20);
		formularioPanel.add(textNombreAlimento);
		textNombreAlimento.setColumns(10);

		btnAyuda = new JButton("?");
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null,
						"<html>Calostro_Forzado<br>Calostro_Natural<br>Leche<br>Racion<br>Sustituto_Lacteo<br>Iniciador</html>",
						"Lista de Alimentos", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnAyuda.setBounds(227, 11, 47, 23);
		formularioPanel.add(btnAyuda);

		JLabel lblIngresoDeNuevo = new JLabel("Ingreso de Nuevo Alimento");
		lblIngresoDeNuevo.setBounds(10, 20, 171, 14);
		formularioPanel.add(lblIngresoDeNuevo);

		frmAltaAlimento.setVisible(true);

		this.frame = frmAltaAlimento;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
			try {
				this.accionIngesar();
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (ServiciosException e1) {
				e1.printStackTrace();
			}
		}

	}

	private LinkedList<String> comprobarNombre() throws ServiciosException, NamingException {

		// obtengo los nombres de la bd
		AlimentosBeanRemote alimentosBean = (AlimentosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/AlimentosBean!com.servicios.AlimentosBeanRemote");

		LinkedList<String> nombres = new LinkedList<>();

		for (Alimento a : alimentosBean.obtenerTodos()) {
			nombres.add(a.getNombre());
		}

		return nombres;
	}

	private void accionIngesar() throws NamingException, ServiciosException {

		String nombre = textNombreAlimento.getText().toUpperCase().toString();

		boolean pase = true;

		for (String s : comprobarNombre()) {
			if (nombre.equals(s)) {
				JOptionPane.showMessageDialog(null, "El alimento ya esta registrado!", "ERROR",
						JOptionPane.WARNING_MESSAGE);
				pase = false;

			}
		}

		if (textNombreAlimento.getText().isEmpty() && pase == false) {
			JOptionPane.showMessageDialog(null, "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else {
			ValidacionDatos.validarNombre(nombre);
		}

		TipoAlimento tipo = TipoAlimento.valueOf(nombre);

		long idAlimento = (long) tipo.getNumero();

		long idUnidad = (long) tipo.getIdUnidad();

		UnidadBeanRemote unidadBean = (UnidadBeanRemote) InitialContext
				.doLookup("PTI-Crianza/UnidadBean!com.servicios.UnidadBeanRemote");

		Unidad unidad = unidadBean.obtenerUnidad(idUnidad);

		String cantidad = textCantidad.getText().trim();

		String costoUni = textCostoUni.getText().trim();

		if (costoUni.isEmpty() && cantidad.isEmpty()) {
			JOptionPane.showMessageDialog(null, "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if (cantidad.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar la cantidad!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if (costoUni.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar el costo!", "ERROR", JOptionPane.WARNING_MESSAGE);
		}

		Double cantidadAlimento = Double.parseDouble(cantidad);

		Double costoUnidad = Double.parseDouble(costoUni);

		BigDecimal costoUnit = BigDecimal.valueOf(costoUnidad);

		// Suma de lo existente mas lo ingresado
		BigDecimal cantidadAlime = BigDecimal.valueOf(cantidadAlimento);

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

		try {
			if (ValidacionDatos.validar(idAlimento, costoUnit, cantidadAlime)) {
				Alimento a = new Alimento(idAlimento, cantidadAlime, costoUnit, nombre, unidad);
				AlimentosBeanRemote alimentosBean;
				alimentosBean = (AlimentosBeanRemote) InitialContext
						.doLookup("PTI-Crianza/AlimentosBean!com.servicios.AlimentosBeanRemote");
				alimentosBean.alta(a);

				JOptionPane.showMessageDialog(null, "Alimento registrado", "CONFIRMACION | SOLICITUD COMPLETADA",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "ADVERTENCIA", "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS",
//					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

	}

	private void accionCancelar() {
		this.frame.dispose();
	}
}
