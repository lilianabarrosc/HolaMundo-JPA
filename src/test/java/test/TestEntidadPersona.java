package test;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import beans.dominio.Persona;

public class TestEntidadPersona {
	static EntityManager em = null;
	static EntityTransaction tx = null; //para que se guarden los datos en la base de dats
	static EntityManagerFactory emf = null;
	Logger log = Logger.getLogger("TestEntidadPersona");
	
	// se ejecuta cuando se esta construyendo la clase
	@BeforeClass
	public static void init() throws Exception {
		emf = Persistence.createEntityManagerFactory("PersonaPU"); //mismo nombre del archivo xml persistence
	}
	
	@Before
	public void setup() {
		try {
			em = emf.createEntityManager();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testPersonaEntity() {
		System.out.println("Iniciando test Persona Entity con JPA");
		assertTrue(em != null);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//No se debe especificar el ID, ya que se genera en automático
		Persona persona1 = new Persona("Angelica", "Lara", "Gomez", "alara@mail.com3", "1314145519");
		log.debug("Objeto a persistir:" + persona1); //muestra por consola
		//se guarda en la base de datos
		em.persist(persona1);
		assertTrue(persona1.getIdPersona() != null);
		tx.commit();
		
		log.debug("Objeto persistido:" + persona1);
		System.out.println("Fin test Persona Entity con JPA");
	}
	

}
