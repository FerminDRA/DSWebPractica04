/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.jsf;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author fermin
 */
public class DAOUsuario implements IDAOGeneral<Usuario, Integer>{

    @Override
    public Usuario create(Usuario p) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction t= session.beginTransaction();
            
            session.save(p);
            t.commit();
        }
        return p;
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario update(Integer id, Usuario p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario findById(Integer id) {
        Session session=HibernateUtil.getSession();
        Transaction t=session.beginTransaction();
        //List<Usuario> usrs=null;
        //usrs=session.createQuery("from usuarios where id=" + id).list();
        Usuario usr=session.get(Usuario.class, id);
        
        if(usr!=null){
            t.commit();
            session.close();
            return usr;
        }
        else{
            return null;
        }
    }
    
    public boolean login(Usuario usr) {
        Session session=HibernateUtil.getSession();
        Transaction t=session.beginTransaction();
        List<Usuario> usrs=null;
        Query query = session.createQuery("FROM Usuario WHERE nombre = :nombre AND pass = :pass");
        query.setParameter("nombre", usr.getNombre());
        query.setParameter("pass", usr.getPass());
        usrs = query.list();
        //Usuario user=session.get(Usuario.class, id);
        
        if(usrs.isEmpty()){
            return false;
        }
        else{
            t.commit();
            session.close();
            return true;
        }
    }
    
}





