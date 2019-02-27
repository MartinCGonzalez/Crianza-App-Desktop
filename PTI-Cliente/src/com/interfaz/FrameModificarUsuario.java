package com.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.servicios.UsuarioBeanRemote;

public class FrameModificarUsuario implements ActionListener {

	private JFrame frame;
	private JTextField textNueContraseña;
	private JTextField textUsuario;

	private JLabel lblApellido;
	private JLabel lblNueContraseña;
	private JLabel lblCantidad;

	private TableRowSorter<TableModel> sorter;

	private JTable tablaUsuario;

	private JButton buttonCancelar;
	private JTextField textApellidos;
	private JTextField textNombres;
	private JTextField textPerfil;
	private JLabel lblUnidad;
	private JTextField textField;
	private JTextField textIdUsuario;
	private JLabel lblIdentificador;

	public FrameModificarUsuario(JFrame framePadre) throws NamingException, ServiciosException {

		this.lblApellido = new JLabel("Apellidos :");
		lblApellido.setBounds(166, 75, 66, 14);

		this.lblNueContraseña = new JLabel("Nueva Contrase\u00F1a :");
		lblNueContraseña.setBounds(166, 156, 115, 14);

		this.lblCantidad = new JLabel("Usuario :");
		lblCantidad.setBounds(166, 103, 66, 14);

		JButton buttonIngresar = new JButton("Actualizar");
		buttonIngresar.setSize(100, 25);
		buttonIngresar.setLocation(457, 65);
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(457, 120, 100, 25);
		buttonCancelar.addActionListener(this);

		JFrame frmModUsuario = new JFrame("Modificar alimento");
		frmModUsuario.setTitle("Modificar alimento");
		frmModUsuario.setSize(632, 394);
		frmModUsuario.setResizable(false);
		frmModUsuario.setLocationRelativeTo(null);
		frmModUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModUsuario.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(626, 365);

		formularioPanel.add(lblCantidad);
		formularioPanel.add(lblNueContraseña);
		formularioPanel.add(lblApellido);

		tablaUsuario = new JTable();
		cargarTablaUsuarios();
		JScrollPane pane = new JScrollPane(tablaUsuario);
		pane.setBounds(10, 187, 605, 167);
		formularioPanel.add(pane);

		JLabel lblNombre = new JLabel("Nombres :");
		lblNombre.setBounds(165, 42, 80, 14);
		formularioPanel.add(lblNombre);

		lblUnidad = new JLabel("Perfil :");
		lblUnidad.setBounds(166, 131, 46, 14);
		formularioPanel.add(lblUnidad);

		formularioPanel.add(buttonIngresar);
		formularioPanel.add(buttonCancelar);

		formularioPanel.setLayout(null);

		frmModUsuario.getContentPane().add(formularioPanel);

		textNueContraseña = new JTextField();
		textNueContraseña.setBounds(291, 156, 139, 20);

		formularioPanel.add(textNueContraseña);

		textUsuario = new JTextField();
		textUsuario.setEditable(false);
		textUsuario.setBounds(291, 100, 139, 20);
		formularioPanel.add(textUsuario);

		textApellidos = new JTextField();
		textApellidos.setEditable(false);
		textApellidos.setBounds(291, 70, 139, 20);
		formularioPanel.add(textApellidos);
		textApellidos.setColumns(10);

		textNombres = new JTextField();
		textNombres.setEditable(false);
		textNombres.setBounds(291, 40, 139, 20);
		formularioPanel.add(textNombres);
		textNombres.setColumns(10);

		textPerfil = new JTextField();
		textPerfil.setEditable(false);
		textPerfil.setBounds(291, 128, 139, 20);
		formularioPanel.add(textPerfil);
		textPerfil.setColumns(10);

		textIdUsuario = new JTextField();
		textIdUsuario.setEditable(false);
		textIdUsuario.setBounds(291, 9, 139, 20);
		formularioPanel.add(textIdUsuario);
		textIdUsuario.setColumns(10);

		lblIdentificador = new JLabel("Identificador :");
		lblIdentificador.setBounds(166, 12, 100, 14);
		formularioPanel.add(lblIdentificador);

		this.buttonCancelar = buttonCancelar;

		JLabel lblBuscar = new JLabel("Buscar :");
		lblBuscar.setBounds(26, 91, 66, 14);
		formularioPanel.add(lblBuscar);

		textField = new JTextField();
		textField.setBounds(26, 116, 86, 20);
		formularioPanel.add(textField);
		textField.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter(text));

				}

			}
		});
		btnBuscar.setBounds(26, 147, 89, 23);
		formularioPanel.add(btnBuscar);

		frmModUsuario.setVisible(true);

		this.frame = frmModUsuario;

	}

	private JTable cargarTablaUsuarios() throws NamingException, ServiciosException {

		List<Usuario> usuarios = null;

		UsuarioBeanRemote usuarioBean = (UsuarioBeanRemote) InitialContext
				.doLookup("PTI-Crianza/UsuarioBean!com.servicios.UsuarioBeanRemote");

		usuarios = usuarioBean.obtenerTodos();

		String[] nombreColumnas = { "ID", "Nombre", "Apellido", "Perfil", "Usuario", "Contraseña" };

		Object[][] datos = new Object[usuarios.size()][6];

		int fila = 0;

		for (Usuario u : usuarios) {

			datos[fila][0] = u.getIdUsuario();
			datos[fila][1] = u.getNombre();
			datos[fila][2] = u.getApellido();
			datos[fila][3] = u.getPerfil();
			datos[fila][4] = u.getUsuario();
			datos[fila][5] = u.getContraseña();
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

				DefaultTableModel model = (DefaultTableModel) tablaUsuario.getModel();

				int filaSeleccionadaUsuario = tablaUsuario.getSelectedRow();

				textIdUsuario.setText(model.getValueAt(filaSeleccionadaUsuario, 0).toString());
				textIdUsuario.setEditable(false);
				textNombres.setText(model.getValueAt(filaSeleccionadaUsuario, 1).toString());
				textNombres.setEditable(false);
				textApellidos.setText(model.getValueAt(filaSeleccionadaUsuario, 2).toString());
				textApellidos.setEditable(false);
				textPerfil.setText(model.getValueAt(filaSeleccionadaUsuario, 3).toString());
				textPerfil.setEditable(false);
				textUsuario.setText(model.getValueAt(filaSeleccionadaUsuario, 4).toString());
				textUsuario.setEditable(false);

				model.getRowCount();

			}

		});

		table.setAutoscrolls(true);
		table.setCellSelectionEnabled(false);
		table.setSize(500, 250);
		table.setRowSelectionAllowed(true);

		this.tablaUsuario = table;

		sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);

		return table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
			try {
				int boton = JOptionPane.YES_NO_OPTION;
				int resultadoSiNo = JOptionPane.showConfirmDialog(null,
						"Estas seguro de querer modificar la contraseña ?", "ADVERTENCIA", boton);
				if (resultadoSiNo == JOptionPane.YES_OPTION) {
					this.accionIngesar();
				}

			} catch (NamingException | ServiciosException e1) {
				e1.printStackTrace();
			}

		}

	}

	private void accionIngesar() throws NamingException, ServiciosException {

		// ANOTACION
		// Se tiene que volver a seleccionar la fila para actualziar, debe de haber una
		// forma de mantener la fila seleccionada.
		int filaSeleccionadaUsuario = this.tablaUsuario.getSelectedRow();

		if (filaSeleccionadaUsuario < 0) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un Usuario.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		UsuarioBeanRemote usuarioBean = (UsuarioBeanRemote) InitialContext
				.doLookup("PTI-Crianza/UsuarioBean!com.servicios.UsuarioBeanRemote");

		// Usuario
		String idUsuario = textIdUsuario.getText();

		long idUsuario1 = Long.parseLong(idUsuario);

		Usuario u = usuarioBean.obtenerUsuario(idUsuario1);

		String contraseña = textNueContraseña.getText().trim();

		if (contraseña.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste de ingresar la nueva contraseña!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		}

		if (textNueContraseña.getText().length() > 0) {

			if (textNueContraseña.getText().length() < 8) {
				JOptionPane.showMessageDialog(null, "La contraseña es muy corta! Minimo 8 caracteres", "ERROR",
						JOptionPane.WARNING_MESSAGE);
			} else if (textNueContraseña.getText().length() > 16) {
				JOptionPane.showMessageDialog(null, "La contraseña es muy larga! Maximo 16 caracteres", "ERROR",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		try {

			u.setContraseña(contraseña);
			usuarioBean.actualizar(u);
			recargarTabla();

			JOptionPane.showMessageDialog(null, "Usuario actualizado", "CONFIRMACION | SOLICITUD COMPLETADA",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS", "ADVERTENCIA",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

		DefaultTableModel model = (DefaultTableModel) tablaUsuario.getModel();
		if (filaSeleccionadaUsuario < 0) {
			model.setRowCount(filaSeleccionadaUsuario);
		}

	}

	private void accionCancelar() {
		this.frame.dispose();
	}

	private void recargarTabla() {

		// Despues de actualizar recargo las filas de la tablas
		int i = tablaUsuario.getSelectedRow();

		DefaultTableModel model = (DefaultTableModel) tablaUsuario.getModel();

		if (i >= 0) {
			model.setValueAt(textIdUsuario.getText(), i, 0);
			model.setValueAt(textNombres.getText(), i, 1);
			model.setValueAt(textApellidos.getText(), i, 2);
			model.setValueAt(textUsuario.getText(), i, 3);
			model.setValueAt(textPerfil.getText(), i, 4);
			model.setValueAt(textNueContraseña.getText(), i, 5);
			model.fireTableDataChanged();

		} else {
			System.out.println("Update Error");
		}
	}
}
