package dim.ris.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dim.ris.model.Project;

public class ProjectController {

	public static boolean addProject(Project p) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			
			et.begin();
			em.persist(p);
			em.flush();
			et.commit();
			
			return true;
		}catch (Exception e) {
			if(et != null)
				et.rollback();
			return false;
		}finally {
			if(em != null)
				em.close();
		}
	}
	
	public static void main(String[] args) {//testiranje
		Project p = new Project();
		p.setTitle("Bojana's task");
		p.setDescription("New project...");
		p.setUser(UserController.getUser(5));
		
		if(addProject(p))
			System.out.println("Uspesno dodat projekat");
		else
			System.out.println("Nesto je poslo po zlu....");
	}
}
