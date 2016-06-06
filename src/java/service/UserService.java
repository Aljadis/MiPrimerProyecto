/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Departamento;
import model.User;
import util.Trabajoem;

/**
 *
 * @author Angel A. Díaz Piña
 */
public class UserService {
    
    private ArrayList <User> listaUsers = new ArrayList<User>();
    EntityManager em = Trabajoem.GetEntityManager();
    
    private java.util.List<Departamento> departamentoList;
    private javax.persistence.Query departamentoQuery;

    public ArrayList<User> getListaUsers() {
        return listaUsers;
    }

    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public Query getDepartamentoQuery() {
        return departamentoQuery;
    }
    
    
    
    public String adicionarUser(User user){
        
        listaUsers.add(user);
        /**********  Prueba de inserción en la base de datos   *******/
        
        Departamento departamentoAux = new Departamento();
        departamentoAux.setNombre(user.getEducation());
        
        em.getTransaction().begin();
        em.merge(departamentoAux);
        em.getTransaction().commit();

        departamentoQuery = Trabajoem.GetEntityManager().createQuery("SELECT d FROM Departamento d");
        departamentoList = departamentoQuery.getResultList();
        
        
        /************************************************************/
        
        return "Usuario adicionado correctamente " + listaUsers.size() ;
    }
    
    public String eliminarDepartamento(Departamento departamento){
        
        
        em.getTransaction().begin();
        em.remove(departamento);
        em.getTransaction().commit();
        departamentoQuery = Trabajoem.GetEntityManager().createQuery("SELECT d FROM Departamento d");
        departamentoList = departamentoQuery.getResultList();
        
        return "Dpto eliminado correctamente.";
    }
    
    
}
