package com.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.entities.Usuario;
import com.enums.TipoUsuario;
import com.exceptions.ServiciosException;
import com.servicios.UsuarioBeanRemote;
import javax.swing.JComboBox;

public class FrameAltaUsuario implements ActionListener {

	private JFrame frame;
	private JTextField textApellidos;
	private JTextField textUsuario;

	private JLabel lblNombre;
	private JLabel lblCostoPorUnidad;
	private JLabel lblCantidad;

	private JComboBox<TipoUsuario> comboBoxPerfil;

	private JButton buttonIngresar;
	private JButton buttonCancelar;
	private JTextField textNombres;
	private JTextField textContraseña;

	public FrameAltaUsuario(JFrame framePadre) throws NamingException {

		this.lblNombre = new JLabel("Nombres :");
		lblNombre.setBounds(20, 45, 92, 14);

		this.lblCostoPorUnidad = new JLabel("Apellidos :");
		lblCostoPorUnidad.setBounds(20, 76, 115, 14);

		this.lblCantidad = new JLabel("Perfil :");
		lblCantidad.setBounds(20, 107, 92, 14);

		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(135, 73, 171, 20);

		textUsuario = new JTextField();
		textUsuario.setEditable(false);
		textUsuario.setColumns(10);
		textUsuario.setBounds(135, 135, 139, 20);

		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.setSize(115, 25);
		buttonIngresar.setLocation(34, 219);
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(173, 219, 115, 25);
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initalizeFrame(JFrame framePadre) throws NamingException {

		JFrame frmAltaAlimento = new JFrame("Nuevo alimento");
		frmAltaAlimento.setTitle("Nuevo alimento");
		frmAltaAlimento.setSize(340, 310);
		frmAltaAlimento.setResizable(false);
		frmAltaAlimento.setLocationRelativeTo(null);
		frmAltaAlimento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAltaAlimento.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(334, 281);

		formularioPanel.add(lblCantidad);
		formularioPanel.add(lblCostoPorUnidad);
		formularioPanel.add(lblNombre);

		formularioPanel.add(textApellidos);
		formularioPanel.add(textUsuario);

		formularioPanel.add(buttonIngresar);
		formularioPanel.add(buttonCancelar);

		formularioPanel.setLayout(null);

		frmAltaAlimento.getContentPane().add(formularioPanel);

		textNombres = new JTextField();
		textNombres.setBounds(135, 42, 171, 20);
		formularioPanel.add(textNombres);
		textNombres.setColumns(10);

		JLabel lblIngresoDeNuevo = new JLabel("Ingreso de Nuevo Usuario");
		lblIngresoDeNuevo.setBounds(10, 11, 171, 14);
		formularioPanel.add(lblIngresoDeNuevo);

		comboBoxPerfil = new JComboBox();
		comboBoxPerfil.setBounds(135, 104, 171, 20);
		comboBoxPerfil.setModel(new DefaultComboBoxModel<>(TipoUsuario.values()));
		formularioPanel.add(comboBoxPerfil);

		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(20, 141, 46, 14);
		formularioPanel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a :");
		lblContrasea.setBounds(20, 172, 92, 14);
		formularioPanel.add(lblContrasea);

		textContraseña = new JTextField();
		textContraseña.setColumns(10);
		textContraseña.setBounds(135, 166, 139, 20);
		formularioPanel.add(textContraseña);

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
			} catch (NamingException | ServiciosException e1) {
				e1.printStackTrace();
			}
		}

	}

	private void accionIngesar() throws NamingException, ServiciosException {

		UsuarioBeanRemote usuarioBean = (UsuarioBeanRemote) InitialContext
				.doLookup("PTI-Crianza/UsuarioBean!com.servicios.UsuarioBeanRemote");

		String nombre = textNombres.getText().toUpperCase().trim();

		String apellido = textApellidos.getText().toUpperCase().trim();

		String contraseña = textContraseña.getText().trim();

		if (textNombres.getText().isEmpty() && textApellidos.getText().isEmpty()
				&& textContraseña.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if (textNombres.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar los nombres!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if (textApellidos.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar los apellidos!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if (textContraseña.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar la contraseña!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		}

		if (textNombres.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "El largo del nombre exede los 50 caracteres!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		} else if (textApellidos.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "El largo de los apellidos exede los 50 caracteres!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		}

		if (textContraseña.getText().length() > 0) {

			if (textContraseña.getText().length() < 8) {
				JOptionPane.showMessageDialog(null, "La contraseña es muy corta! Minimo 8 caracteres", "ERROR",
						JOptionPane.WARNING_MESSAGE);
			} else if (textContraseña.getText().length() > 16) {
				JOptionPane.showMessageDialog(null, "La contraseña es muy larga! Maximo 16 caracteres", "ERROR",
						JOptionPane.WARNING_MESSAGE);
			}

		}

		TipoUsuario tipoUsuario = (TipoUsuario) comboBoxPerfil.getSelectedItem();

		String perfil = String.valueOf(tipoUsuario);

		String[] nombre1 = nombre.trim().split("\\s+");
		String[] apellido1 = apellido.trim().split("\\s+");

		String usuario = nombre1[0].toLowerCase() + "." + apellido1[0].toLowerCase();
		String usuario2 = nombre1[0].toLowerCase() + "." + apellido1[0].toLowerCase() + "."
				+ apellido1[1].toLowerCase().charAt(0);

		Usuario usuario1 = usuarioBean.obtenerUsuarioIgual(usuario);

		try {

			if (usuario1 == null) {

				Usuario u = new Usuario(apellido, contraseña, nombre, perfil, usuario);
				usuarioBean = (UsuarioBeanRemote) InitialContext
						.doLookup("PTI-Crianza/UsuarioBean!com.servicios.UsuarioBeanRemote");

				usuarioBean.alta(u);
				textUsuario.setText(usuario);

				JOptionPane.showMessageDialog(null, "Usuario registrado " + usuario,
						"CONFIRMACION | SOLICITUD COMPLETADA", JOptionPane.INFORMATION_MESSAGE);

			} else if (usuarioBean.obtenerUsuarioIgual(usuario2) == null) {

				Usuario u = new Usuario(apellido, contraseña, nombre, perfil, usuario2);
				usuarioBean = (UsuarioBeanRemote) InitialContext
						.doLookup("PTI-Crianza/UsuarioBean!com.servicios.UsuarioBeanRemote");
				usuarioBean.alta(u);
				textUsuario.setText(usuario2);

				JOptionPane.showMessageDialog(null, "Usuario registrado " + usuario2,
						"CONFIRMACION | SOLICITUD COMPLETADA", JOptionPane.INFORMATION_MESSAGE);

			} else {

				JOptionPane.showMessageDialog(null, "Usuario ya existente, por favor revise sus datos",
						"ADVERTENCIA | SOLICITUD NO COMPLETADA", JOptionPane.INFORMATION_MESSAGE);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ADVERTENCIA", "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

	}

	private void accionCancelar() {
		this.frame.dispose();
	}
}
