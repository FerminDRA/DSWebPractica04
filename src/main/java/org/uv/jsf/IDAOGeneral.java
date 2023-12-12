package org.uv.jsf;

import java.util.List;

/**
 *
 * @author fermin
 */
public interface IDAOGeneral<T, I>{
    public T create(T p);
    public boolean delete(I id);
    public T update(I id, T p);
    
    public List<T> findAll();
    public T findById(I id);
}








