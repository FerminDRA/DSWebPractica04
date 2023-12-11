/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.jsf;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fermin
 */
public class DAOPersona implements IDAOGeneral<Persona, Integer>{

    @Override
    public Persona create(Persona p) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction t= session.beginTransaction();
            
            session.save(p);
            t.commit();
        }
        return p;
    }

    @Override
    public boolean delete(Integer id) {
        Session session= HibernateUtil.getSession();
        Transaction t= session.beginTransaction();
        //Venta v=
        boolean res=false;
        Persona persn=findById(id);
        if(persn==null){
            res= false;
        }
        else{
            session.delete(persn);
            res= true;
        }
        t.commit();
        session.close();
        return res;
    }

    @Override
    public Persona update(Integer id, Persona p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Persona> findAll() {
        List<Persona> personas=null;
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        personas = session.createQuery("from Persona",Persona.class).list();
        t.commit();
        return personas;
    }

    @Override
    public Persona findById(Integer id) {
        Session session=HibernateUtil.getSession();
        Transaction t=session.beginTransaction();
        Persona persn=session.get(Persona.class, id);
        
        if(persn!=null){
            t.commit();
            session.close();
            return persn;
        }
        else{
            return null;
        }
    }
    
}
