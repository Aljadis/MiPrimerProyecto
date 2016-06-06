/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Maite
 */

 public class Trabajoem {
  final static private EntityManager em =Persistence.createEntityManagerFactory("HolaSpringPU").createEntityManager();

  //Este metodo se ejecuta cuando se carga la clase
 /* static {
   EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoEstudioPU");
   em = emf.createEntityManager();
  }*/
  public static EntityManager GetEntityManager(){
      return em;
  }

 
}
