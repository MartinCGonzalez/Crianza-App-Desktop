package com.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.exceptions.ServiciosException;

public class FrameAnalisisEventosClinicos implements ActionListener {

	public FrameAnalisisEventosClinicos(JFrame frame) throws NamingException {

		JFrame frmEventoClinico = new JFrame("Evento Clinico - Enfermedad");
		frmEventoClinico.setTitle("Evento Clinico");
		frmEventoClinico.setSize(226, 136);
		frmEventoClinico.setResizable(false);
		frmEventoClinico.setLocationRelativeTo(null);
		frmEventoClinico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEventoClinico.getContentPane().setLayout(null);

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLocation(0, 0);
		formularioPanel.setSize(220, 107);

		JButton btnMostrar = new JButton("Historico Eventos Clinicos");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new FrameHistoricoEventoClinico(frame);
				} catch (NamingException | ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		formularioPanel.setLayout(null);
		btnMostrar.setBounds(10, 29, 194, 23);
		formularioPanel.add(btnMostrar);

		JButton btnAsignarEnfermedad = new JButton("Nuevo Evento Clinico");
		btnAsignarEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					new FrameAltaEventoClinico(frmEventoClinico);
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnAsignarEnfermedad.setBounds(10, 63, 194, 23);
		formularioPanel.add(btnAsignarEnfermedad);

		formularioPanel.setLayout(null);
		frmEventoClinico.getContentPane().add(formularioPanel);

		frmEventoClinico.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
