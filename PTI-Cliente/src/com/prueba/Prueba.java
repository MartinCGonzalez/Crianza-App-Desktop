package com.prueba;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import com.entities.Alimento;
import com.entities.Usuario;
import com.entities.Peso;
import com.entities.Ternero;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.interfaz.FramePrincipal;
import com.servicios.AlimentosBeanRemote;
import com.servicios.UsuarioBeanRemote;
import com.servicios.TernerosBeanRemote;
import com.servicios.UsuarioBeanRemote;

public class Prueba {

	public static void main(String[] args) throws NamingException, ServiciosException, ParseException {

		String usuario = "ADMIN";
		
		String contraseña = "nomeacuerdo";
		
		UsuarioBeanRemote usuarioBean = (UsuarioBeanRemote) InitialContext
				.doLookup("PTI-Crianza/UsuarioBean!com.servicios.UsuarioBeanRemote");
		
		Usuario usuario1 = usuarioBean.obtenerUsuarioIgual(usuario);
		
		String u1 = usuario1.getUsuario().toString();
	
		String u2 = usuario1.getContraseña().toString();
		
		System.out.println(u1);
		System.out.println(u2);
		System.out.println(usuario);
		System.out.println(contraseña);

		
//		if(u1.equals(usuario) && u2.equals(contraseña)) {
//			
//			System.out.println("==");
//
//			new FramePrincipal();
//			
//			
//		} else {			
//			JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos! ", "CONFIRMACION | SOLICITUD COMPLETADA",
//				JOptionPane.INFORMATION_MESSAGE);
//			
//		}
		
		
//		String textContraseña = "12345678901234567";
//		
//		if (textContraseña.length() < 8) {
//			JOptionPane.showMessageDialog(null, "La contraseña es muy corta! Minimo 8 caracteres", "ERROR",
//					JOptionPane.WARNING_MESSAGE);
//		} else if (textContraseña.length() > 16) {
//
//			JOptionPane.showMessageDialog(null, "La contraseña es muy larga! Maximo 16 caracteres", "ERROR",
//					JOptionPane.WARNING_MESSAGE);
//
//		}
//		

		
//		 String nombre = "Martin Alberto";
//		 String apellido = "Caceres Gonzalez";
//		//
//		 String[] nombre1 = nombre.trim().split("\\s+");
//		 String[] apellido1 = apellido.trim().split("\\s+");
//		
//		// System.out.println(nombre1[0].toLowerCase());
//		// System.out.println(apellido1[0].toLowerCase());
//		// System.out.println(apellido1[1].toLowerCase().charAt(0));
//		//
//		 String usuario = nombre1[0].toLowerCase() +"." + apellido1[0].toLowerCase();
//		//
//		// System.out.println(usuario);
//		//			
//
//			UsuarioBeanRemote usuarioBean = (UsuarioBeanRemote) InitialContext
//					.doLookup("PTI-Crianza/UsuarioBean!com.servicios.UsuarioBeanRemote");
//
//		Long id = (long) 3;
//		
//		String usuario6 = (String) "asds";
//		
////		Usuario usuario4 = usuarioBean.obtenerUsuario(id);
//		
//		Usuario usuario5 = usuarioBean.obtenerUsuarioIgual(usuario6);
//		
////		System.out.println(usuario4);
//		
//		if (usuario5 == null) {
//			System.out.println("nulo");
//		} else { 
//			System.out.println("existe");
//		}
//				
//		
			
		
//		Usuario u = new Usuario ();
//		u.setNombre("Martin");
//		u.setApellido("Gonzalez");
//		u.setPerfil("Encargado");
//		u.setUsuario("martin.gonzalez");
//		u.setContraseña("123");
//		usuarioBean.alta(u);
		
//		Long idUsuario = (long) 4;
//		
//		Usuario u1 = usuarioBean.obtenerUsuario(idUsuario);
//		
//		Long nextID = usuarioBean.obternerSiguienteUsuario();
//
//
//		System.out.println(u1);
//		System.out.println(nextID);
	}
}
