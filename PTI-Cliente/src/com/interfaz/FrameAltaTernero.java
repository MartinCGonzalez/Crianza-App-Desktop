package com.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.entities.Ternero;
import com.enums.TipoParto;
import com.enums.TipoRaza;
import com.exceptions.ServiciosException;
import com.servicios.TernerosBeanRemote;
import com.validaciones.ValidacionDatos;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FrameAltaTernero implements ActionListener {

	private JFrame frame;

	private JTextField textNroCaravana;
	private JTextField textMadre;
	private JTextField textPadre;
	private JTextField textGuachera;
	private JTextField textPeso;

	private JLabel lblNroCaravana;
	private JLabel lblIdGuachera;
	private JLabel lblIdMadre;
	private JLabel lblIdPadre;
	private JLabel lblFechaNac;
	private JLabel lblPesoNac;
	private JLabel lblRaza;
	private JLabel lblParto;

	private JComboBox<TipoParto> comboTipoParto;
	private JComboBox<TipoRaza> comboTipoRaza;

	private JDatePickerImpl datePicker;

	private JButton buttonIngresar;
	private JButton buttonCancelar;
	private JLabel label;
	private JLabel label_1;

	public FrameAltaTernero(JFrame framePadre) throws NamingException {

		this.lblNroCaravana = new JLabel("Nro Caravana");
		lblNroCaravana.setBounds(10, 27, 115, 14);

		this.lblIdGuachera = new JLabel("Id Guachera");
		lblIdGuachera.setBounds(10, 114, 92, 14);

		this.lblIdMadre = new JLabel("Id Madre");
		lblIdMadre.setBounds(10, 58, 122, 14);

		this.lblIdPadre = new JLabel("Id Padre");
		lblIdPadre.setBounds(10, 86, 132, 14);

		this.lblFechaNac = new JLabel("Fecha Nacimiento");
		lblFechaNac.setBounds(10, 237, 92, 14);

		this.lblPesoNac = new JLabel("Peso Nacimiento");
		lblPesoNac.setBounds(10, 142, 92, 14);

		this.lblRaza = new JLabel("Raza");
		lblRaza.setBounds(10, 185, 132, 14);

		this.lblParto = new JLabel("Parto");
		lblParto.setBounds(10, 210, 132, 14);

		textNroCaravana = new JTextField();
		textNroCaravana.setColumns(10);
		textNroCaravana.setBounds(135, 24, 139, 20);

		textMadre = new JTextField();
		textMadre.setColumns(10);
		textMadre.setBounds(135, 55, 139, 20);

		textPadre = new JTextField();
		textPadre.setColumns(10);
		textPadre.setBounds(135, 83, 139, 20);

		textGuachera = new JTextField();
		textGuachera.setColumns(10);
		textGuachera.setBounds(135, 111, 139, 20);

		textPeso = new JTextField();
		textPeso.setColumns(10);
		textPeso.setBounds(135, 139, 139, 20);

		this.comboTipoParto = new JComboBox<TipoParto>();
		comboTipoParto.setBounds(135, 170, 139, 20);
		comboTipoParto.setModel(new DefaultComboBoxModel<>(TipoParto.values()));

		this.comboTipoRaza = new JComboBox<TipoRaza>();
		comboTipoRaza.setBounds(135, 201, 139, 20);
		comboTipoRaza.setModel(new DefaultComboBoxModel<>(TipoRaza.values()));

		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.setSize(100, 25);
		buttonIngresar.setLocation(21, 294);
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(145, 294, 115, 25);
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);

	}

	public void initalizeFrame(JFrame framePadre) throws NamingException {

		JFrame frmAltaAlimento = new JFrame("Nuevo Ternero");
		frmAltaAlimento.setTitle("Nuevo Ternero");
		frmAltaAlimento.setSize(304, 374);
		frmAltaAlimento.setResizable(false);
		frmAltaAlimento.setLocationRelativeTo(null);
		frmAltaAlimento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAltaAlimento.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(298, 345);

		formularioPanel.add(lblIdMadre);
		formularioPanel.add(lblIdGuachera);
		formularioPanel.add(lblNroCaravana);
		formularioPanel.add(lblIdPadre);
		formularioPanel.add(lblFechaNac);
		formularioPanel.add(lblPesoNac);

		label = new JLabel("Tipo Parto");
		label.setBounds(10, 173, 92, 14);
		formularioPanel.add(label);

		label_1 = new JLabel("Raza");
		label_1.setBounds(10, 204, 92, 14);
		formularioPanel.add(label_1);
		formularioPanel.add(comboTipoRaza);
		formularioPanel.add(comboTipoParto);

		formularioPanel.add(textNroCaravana);
		formularioPanel.add(textMadre);
		formularioPanel.add(textPadre);
		formularioPanel.add(textGuachera);
		formularioPanel.add(textPeso);

		this.datePicker = this.creareDatePicker();
		formularioPanel.add(this.datePicker);
		formularioPanel.add(buttonIngresar);
		formularioPanel.add(buttonCancelar);

		formularioPanel.add(buttonIngresar);
		formularioPanel.add(buttonCancelar);

		formularioPanel.setLayout(null);

		frmAltaAlimento.getContentPane().add(formularioPanel);

		frmAltaAlimento.setVisible(true);

		this.frame = frmAltaAlimento;

	}

	private JDatePickerImpl creareDatePicker() {

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(145, 232, 115, 30);
		return datePicker;
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

		TernerosBeanRemote ternerosBean = (TernerosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/TernerosBean!com.servicios.TernerosBeanRemote");
		
		String nroCaravana1 = textNroCaravana.getText().trim();
		
		String idMadre1 = textMadre.getText().trim();
		
		String idPadre1 = textPadre.getText().trim();
		
		String idGuachera1 = textGuachera.getText().trim();
		
		String pesoNac1 = textPeso.getText().trim();
		
		if(nroCaravana1.isEmpty()&&idMadre1.isEmpty()&&idPadre1.isEmpty()&&idGuachera1.isEmpty()&&pesoNac1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else 	if(nroCaravana1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar la el Nro Caravana!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if(idMadre1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar el ID Madre!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if(idPadre1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar el ID Padre", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if(idGuachera1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar el ID Guachera!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if(pesoNac1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar el Peso Nacimiento!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		}
		
		try {
			Long.parseLong(nroCaravana1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, " El Nro Caravana debe de ser un numero!", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();}
		try {
			Long.parseLong(idMadre1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, " El ID Madre debe de ser un numero!", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();}
		try {
			Long.parseLong(idPadre1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, " El ID Padre debe de ser un numero!", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();}
		try {
			Long.parseLong(idGuachera1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, " El ID Guachera debe de ser un numero!", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();}
		try {
			Double.parseDouble(pesoNac1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, " El Peso debe de ser un numero!", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();}
		
		Long nroCaravana2 = Long.parseLong(nroCaravana1);
		
		Long idMadre = Long.parseLong(idMadre1);

		Long idPadre = Long.parseLong(idPadre1);

		Long idGuachera = Long.parseLong(idGuachera1);
		
		Double pesoNac2 = Double.parseDouble(pesoNac1);
		
		BigDecimal pesoNac3 = BigDecimal.valueOf(pesoNac2);

		Date fechaNac = (Date) this.datePicker.getModel().getValue();
		
		ComboBoxModel<TipoParto> tipo = comboTipoParto.getModel();
		TipoParto tipo1 = (TipoParto) tipo.getSelectedItem();
		String parto = String.valueOf(tipo1);

		ComboBoxModel<TipoRaza> tipo11 = comboTipoRaza.getModel();
		TipoRaza tipo2 = (TipoRaza) tipo11.getSelectedItem();
		String raza = String.valueOf(tipo2);
		


		try {
			if(ValidacionDatos.validarTernero(nroCaravana2,pesoNac3, fechaNac) ) {
			Ternero t = new Ternero(pesoNac3, fechaNac, nroCaravana2, parto, raza, idGuachera, idMadre, idPadre);

			ternerosBean = (TernerosBeanRemote) InitialContext
					.doLookup("PTI-Crianza/TernerosBean!com.servicios.TernerosBeanRemote");
			ternerosBean.alta(t);
			JOptionPane.showMessageDialog(null, "Ternero registrado", "CONFIRMACION | SOLICITUD COMPLETADA",
					JOptionPane.INFORMATION_MESSAGE);
			
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
