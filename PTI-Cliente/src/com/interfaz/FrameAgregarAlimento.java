package com.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.entities.Alimento;
import com.exceptions.ServiciosException;
import com.servicios.AlimentosBeanRemote;

public class FrameAgregarAlimento implements ActionListener {

	private JFrame frame;
	
	private JLabel lblNombre;
	private JLabel lblCantidad;
	private JLabel lblFaltante;
	
	private JComboBox<String> cbbNombre; 
	
	private JTextField txtCantidad;

	private JButton btnAgregar;
	private JButton btnCancelar;
	private JButton btnNuevo;
	private JButton btnRefresh;

	public FrameAgregarAlimento(JFrame framePadre) throws NamingException {

		this.lblNombre = new JLabel("Nombre:");
		this.lblCantidad = new JLabel("Cantidad:");
		this.lblFaltante = new JLabel("No encuentra el alimento?");
		
		this.cbbNombre = new JComboBox<String>();
		
		this.txtCantidad = new JTextField();
		
		this.btnAgregar = new JButton("Agregar");
		this.btnCancelar = new JButton("Cancelar");
		this.btnNuevo = new JButton("Registrar Nuevo");
		
		lblNombre.setBounds(10, 22, 49, 14);
		lblCantidad.setBounds(10, 93, 63, 14);
		lblFaltante.setBounds(229, 11, 160, 14);

		cbbNombre.setBounds(69, 11, 150, 37);
		
		txtCantidad.setBounds(69, 90, 150, 20);

		btnAgregar.setBounds(10, 118, 85, 20);
		btnCancelar.setBounds(134, 118, 85, 20);
		btnNuevo.setBounds(229, 28, 140, 20);
		
		btnAgregar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnNuevo.addActionListener(this); 
		

		this.initalizeFrame(framePadre);

	}
	
	
	public void initalizeFrame(JFrame framePadre) throws NamingException{
		
		JFrame frmAgregarAlimento = new JFrame("Agregar Alimento");
		frmAgregarAlimento.setTitle("Agregar Alimento");
		frmAgregarAlimento.setSize(400, 178);
		frmAgregarAlimento.setResizable(false);
		frmAgregarAlimento.setLocationRelativeTo(null);
		frmAgregarAlimento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAgregarAlimento.getContentPane().setLayout(null);

		
		JPanel formularioPanel = new JPanel();
		formularioPanel.setBounds(0, 0, 394, 149);

		
		formularioPanel.add(lblNombre);
		formularioPanel.add(lblCantidad);
		formularioPanel.add(lblFaltante);
		
		accionRecargar();
		
		btnRefresh = new JButton("Refrescar");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					accionRecargar();
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
				cbbNombre.revalidate();
				cbbNombre.repaint();
				formularioPanel.add(cbbNombre);
			}
		});
		btnRefresh.setBounds(119, 59, 100, 20);
		formularioPanel.add(btnRefresh);
		
		
		formularioPanel.add(cbbNombre);
		
		
		
		formularioPanel.add(txtCantidad);
				
		formularioPanel.add(btnAgregar);
		formularioPanel.add(btnCancelar);
		formularioPanel.add(btnNuevo);
		
		
		formularioPanel.setLayout(null);
		
		frmAgregarAlimento.getContentPane().add(formularioPanel);
		
		
		frmAgregarAlimento.setVisible(true);

		this.frame = frmAgregarAlimento;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.btnCancelar){
			this.accionCancelar();
		}
		
		else if (e.getSource() == this.cbbNombre){
			try {
				this.accionRecargar();
			} catch (NamingException e1) {
				e1.printStackTrace();
			}
		}
		
		else if (e.getSource() == this.btnNuevo){
			this.accionIngresar();
		}
		
		else if (e.getSource() == this.btnAgregar){
			try {
				this.accionAgregar();
			} catch (NamingException | ServiciosException e1) {
				e1.printStackTrace();
			}
		}
		

	}
	
	
	private void accionIngresar(){
		try {
			new FrameAltaAlimento(frame);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private void accionRecargar() throws NamingException{
		
		AlimentosBeanRemote alimentosBean = (AlimentosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/AlimentosBean!com.servicios.AlimentosBeanRemote");
		
		List<Alimento> alimentos = null;
		
		try {
			alimentos = alimentosBean.obtenerTodos();
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
		
		LinkedList<String> items = new LinkedList<>();

		for(int i=0; i<cbbNombre.getItemCount(); i++){
			items.add(cbbNombre.getItemAt(i));
		}
		
		for(Alimento a: alimentos){
			if (!items.contains(a.getNombre())){
				cbbNombre.addItem(a.getNombre());
	        }
		}
		
	} 
	
	
	private void accionAgregar() throws NamingException, ServiciosException {
		
		AlimentosBeanRemote alimentosBean = (AlimentosBeanRemote) InitialContext
				.doLookup("PTI-Crianza/AlimentosBean!com.servicios.AlimentosBeanRemote");
		
		
		
		//cast mal hecho, tengo que buscar alimento por nombre
		//Alimento a = null; //tengo que sacar el alimento
		String nombre = (String) cbbNombre.getSelectedItem();
		Alimento a = alimentosBean.obtenerPorNombre(nombre);
		
		 //tengo el nombre del enum
		
		boolean pase = true;
		
		if(txtCantidad.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Olvidaste ingresar la cantidad!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			pase = false;
		}
		
		if(pase){
			try {
				Double.parseDouble(txtCantidad.getText());

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "La cantidad debe de ser un numero!", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
			}
		}
		
		
		if(Double.parseDouble(txtCantidad.getText())<=0){
			JOptionPane.showMessageDialog(null, "El numero debe ser mayor a 0!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			pase=false;
		}
		
		
		
		
		
		
		BigDecimal cantidad = BigDecimal.valueOf(Double.parseDouble(txtCantidad.getText()));
		
		if(pase){
			try {
				a.setCantidad(a.getCantidad().add(cantidad));
				
				alimentosBean.actualizar(a);
				
				JOptionPane.showMessageDialog(null, "Alimento agregado!", "CORRECTO",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ADVERTENCIA","ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS",JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
			}
		}
		
	}

	private void accionCancelar() {
		this.frame.dispose();
	}
}
