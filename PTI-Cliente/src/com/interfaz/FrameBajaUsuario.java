package com.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class FrameBajaUsuario implements ActionListener {

	private JFrame frame;

	private TableRowSorter<TableModel> sorter;

	private JTable tablaUsuario;

	private JButton buttonCancelar;
	private JTextField textField;

	public FrameBajaUsuario(JFrame framePadre) throws NamingException, ServiciosException {

		JButton buttonBorrar = new JButton("Borrar");
		buttonBorrar.setSize(100, 25);
		buttonBorrar.setLocation(16, 191);
		buttonBorrar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(16, 236, 100, 25);
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

		tablaUsuario = new JTable();
		cargarTablaUsuarios();
		JScrollPane pane = new JScrollPane(tablaUsuario);
		pane.setBounds(126, 11, 489, 343);
		formularioPanel.add(pane);

		formularioPanel.add(buttonBorrar);
		formularioPanel.add(buttonCancelar);

		formularioPanel.setLayout(null);

		frmModUsuario.getContentPane().add(formularioPanel);

		this.buttonCancelar = buttonCancelar;

		JLabel lblBuscar = new JLabel("Buscar :");
		lblBuscar.setBounds(16, 29, 66, 14);
		formularioPanel.add(lblBuscar);

		textField = new JTextField();
		textField.setBounds(16, 54, 86, 20);
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
		btnBuscar.setBounds(16, 85, 89, 23);
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

		JTable table = new JTable(model);
		table.setAutoscrolls(true);
		table.setCellSelectionEnabled(false);
		table.setSize(500, 250);
		table.setRowSelectionAllowed(true);
		table.revalidate();

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
						"Estas seguro de querer eliminar este usuario ?", "ADVERTENCIA", boton);
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

		UsuarioBeanRemote usuarioBean = (UsuarioBeanRemote) InitialContext
				.doLookup("PTI-Crianza/UsuarioBean!com.servicios.UsuarioBeanRemote");

		// Usuario
		DefaultTableModel model1 = (DefaultTableModel) tablaUsuario.getModel();

		int filaSeleccionadaUsuario = tablaUsuario.getSelectedRow();

		Long idUsuario = (Long) model1.getValueAt(filaSeleccionadaUsuario, 0);

		try {

			usuarioBean.borrar(idUsuario);

			recargarTabla();

			JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente!",
					"CONFIRMACION | SOLICITUD COMPLETADA", JOptionPane.INFORMATION_MESSAGE);

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
			model.removeRow(i);
		} else {
			System.out.println("Update Error");
		}
	}
}
