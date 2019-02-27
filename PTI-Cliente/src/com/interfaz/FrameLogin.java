package com.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.servicios.UsuarioBeanRemote;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.UIManager;

import javax.swing.ImageIcon;
import java.awt.Color;

public class FrameLogin {
	
		private JLabel lblUsuario;
		private JLabel lblContraseña;

		private static JButton buttonIngresar;
		private static JButton buttonCancelar;
		private static JTextField textUsuario;
		private static JTextField textContraseña;
		
		public FrameLogin(JFrame frame) {
			try {
				createAndShowGUI();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		public static void main(String[] args) {

			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
						createAndShowGUI();
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}


		public static void createAndShowGUI() throws NamingException {
			
			JFrame frame = new JFrame("Ingreso de Usuario");
			frame.getContentPane().setBackground(Color.WHITE);
			frame.setTitle("Ingreso de Usuario");
			frame.setSize(396, 204);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			textUsuario = new JTextField();
			textUsuario.setBounds(239, 31, 139, 20);
			frame.getContentPane().add(textUsuario);
			textUsuario.setColumns(10);
			
			textContraseña = new JPasswordField();
			textContraseña.setBounds(239, 62, 139, 20);
			frame.getContentPane().add(textContraseña);
			textContraseña.setColumns(10);
			
			JLabel lblUsuario = new JLabel("Usuario :");
			lblUsuario.setBounds(158, 37, 92, 14);
			frame.getContentPane().add(lblUsuario);

			JLabel lblContraseña = new JLabel("Contrase\u00F1a :");
			lblContraseña.setBounds(158, 68, 115, 14);
			frame.getContentPane().add(lblContraseña);

			JButton buttonIngresar = new JButton("Ingresar");
			buttonIngresar.setSize(93, 25);
			buttonIngresar.setLocation(166, 109);
			buttonIngresar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					String usuario = textUsuario.getText().toLowerCase().trim();
					
					String contraseña = textContraseña.getText();
					
					UsuarioBeanRemote usuarioBean = null;
					try {
						usuarioBean = (UsuarioBeanRemote) InitialContext
								.doLookup("PTI-Crianza/UsuarioBean!com.servicios.UsuarioBeanRemote");
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Usuario usuario1 = null;
					try {
						usuario1 = usuarioBean.obtenerUsuarioIgual(usuario);
					} catch (ServiciosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					String u1 = usuario1.getUsuario().toString();
					
					String u2 = usuario1.getContraseña().toString();
					
					String u3 = usuario1.getPerfil().toString();
					
					if(u1.equals(usuario) && u2.equals(contraseña)) {
												
						if(u3.equals("ADMINISTRADOR")) {
						try {
							JOptionPane.showMessageDialog(null, "Bienvenido nuevamente " + u1 + " !", "INGRESO CORRECTO", JOptionPane.INFORMATION_MESSAGE );
							new FramePrincipalAdmin(frame);
							frame.dispose();
						} catch (NamingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						} else {
							
							try {
								JOptionPane.showMessageDialog(null, "Bienvenido nuevamente " + u1 + " !", "INGRESO CORRECTO", JOptionPane.INFORMATION_MESSAGE );
								new FramePrincipal(frame);
								frame.dispose();
							} catch (NamingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
						
					} else {			
						JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos! ", "ADVERTENCIA | SOLICITUD NO COMPLETADA",
							JOptionPane.INFORMATION_MESSAGE);
						
					}
									}
			});
			frame.getContentPane().add(buttonIngresar);

			JButton buttonCancelar = new JButton("Cancelar");
			buttonCancelar.setBounds(272, 109, 92, 25);
			buttonCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				frame.dispose();
					
				}
			});
			frame.getContentPane().add(buttonCancelar);
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(FrameLogin.class.getResource("/com/icn/TiRex-150x150.png")));
			label.setBounds(8, 11, 150, 150);
			frame.getContentPane().add(label);
			
			frame.setVisible(true);

		}
		
		
		public void initalizeFrame(JFrame framePadre) throws NamingException{
			
			JPanel formularioPanel = new JPanel();
			formularioPanel.setBackground(UIManager.getColor("Button.background"));
			formularioPanel.setLocation(0, 0);
			formularioPanel.setSize(410, 201);
			formularioPanel.add(lblContraseña);
			formularioPanel.add(lblUsuario);
								
			formularioPanel.add(buttonIngresar);
			formularioPanel.add(buttonCancelar);
						
			formularioPanel.setLayout(null);
		}
		
}