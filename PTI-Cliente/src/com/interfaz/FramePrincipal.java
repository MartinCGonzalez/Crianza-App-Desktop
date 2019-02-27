package com.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import com.exceptions.ServiciosException;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class FramePrincipal {

	public FramePrincipal(JFrame framePadre) throws NamingException {
		
		this.initalizeFrame(framePadre);

	}
	
	public void initalizeFrame(JFrame framePadre) throws NamingException{
		
		JFrame frame = new JFrame("Nuevo alimento");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("Nuevo alimento");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		initializeMenuBar(frame);

		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FramePrincipal.class.getResource("/com/icn/TiRex.png")));
		lblNewLabel.setBounds(142, 11, 320, 320);
		frame.getContentPane().add(lblNewLabel);

		frame.setVisible(true);

	}
	
	private static void initializeMenuBar(JFrame frame) {

		JMenuBar menuBar = new JMenuBar();

		initializeMenuAlimentos(menuBar, frame);
		initializeMenuPeso(menuBar, frame);
		initializeMenuEnfermedades(menuBar, frame);
		initializeMenuTernero(menuBar, frame);
		initializeMenuSalir(menuBar, frame);

		frame.setJMenuBar(menuBar);

	}
	
	
	private static void initializeMenuSalir(JMenuBar menuBar, final JFrame frame) {

		JMenu salir = new JMenu("Salir");

		JMenuItem cerrarSesion = new JMenuItem("Cerrar Sesion");
		cerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				new FrameLogin(frame);
			}
		});
		salir.add(cerrarSesion);
		menuBar.add(salir);
	}
		
	private static void initializeMenuAlimentos(JMenuBar menuBar, final JFrame frame) {

		JMenu alimentos = new JMenu("Alimentos");

		// ALTA ALIMENTO
		JMenuItem nuevoAlimento = new JMenuItem("Nuevo Alimento");
		nuevoAlimento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					new FrameAltaAlimento(frame);
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		});
		alimentos.add(nuevoAlimento);
		menuBar.add(alimentos);
		
		JMenuItem mntmAgregarAlimento = new JMenuItem("Agregar Alimento");
		mntmAgregarAlimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new FrameAgregarAlimento(frame);
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		});
		alimentos.add(mntmAgregarAlimento);
		menuBar.add(alimentos);

		// MODIFICAR ALIMENTO
		JMenuItem modAlimento = new JMenuItem("Modificar Alimento");
		modAlimento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					new FrameModificarAlimento(frame);
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		});
		alimentos.add(modAlimento);
		menuBar.add(alimentos);

		// CONSUMO ALIMENTO
		JMenuItem consumoAlimento = new JMenuItem("Registrar Consumo de alimentos");
		consumoAlimento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					new FrameRegistroConsumoAlimento(frame);
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		});
		alimentos.add(consumoAlimento);
		menuBar.add(alimentos);

		// HISTORICO CONSUMO
		JMenuItem historicoConsumoAlimento = new JMenuItem("Historico de Consumo de alimentos");
		historicoConsumoAlimento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					new FrameHistoricoTernero(frame);
				} catch (NamingException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		alimentos.add(historicoConsumoAlimento);
		menuBar.add(alimentos);
		


	}

	private static void initializeMenuPeso(JMenuBar menuBar, final JFrame frame) {

		JMenu peso = new JMenu("Peso");
		
		JMenuItem nuevoGananciaPeso = new JMenuItem("Registrar Ganancia de Peso");
		nuevoGananciaPeso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					new FrameGananciaPesoTernero(frame);
				} catch (NamingException | SQLException | ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		peso.add(nuevoGananciaPeso);
		menuBar.add(peso);
		
		JMenuItem historicoPeso = new JMenuItem("Historico de Ganancia de Peso");
		historicoPeso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					new FrameHistoricoPeso(frame);
				} catch (NamingException | ServiciosException | SQLException e) {
					e.printStackTrace();
				}
			}
		});

		peso.add(historicoPeso);
		menuBar.add(peso);

	}

	private static void initializeMenuEnfermedades(JMenuBar menuBar, final JFrame frame) {

		JMenu enfermedad = new JMenu("Enfermedad");
		JMenuItem nuevoAnalisis = new JMenuItem("Eventos Clinicos de Enfermedad");

		nuevoAnalisis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					new FrameAnalisisEventosClinicos(frame);
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		enfermedad.add(nuevoAnalisis);
		menuBar.add(enfermedad);

	}
		
	private static void initializeMenuTernero(JMenuBar menuBar, final JFrame frame) {

		JMenu ternero = new JMenu("Ternero");
		JMenuItem nuevoTernero = new JMenuItem("Nuevo Ternero");

		nuevoTernero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					new FrameAltaTernero(frame);
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		ternero.add(nuevoTernero);
		menuBar.add(ternero);
	
		JMenuItem ListadoTerneros = new JMenuItem("Listado Terneros");

		ListadoTerneros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					new FrameListadoTernero(frame);
				} catch (NamingException | SQLException | ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		ternero.add(ListadoTerneros);
		menuBar.add(ternero);
		
}

}
