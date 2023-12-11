package org.uv.jsf.Beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.uv.jsf.DAOUsuario;
import org.uv.jsf.Usuario;

@Named(value = "registroBean")
@SessionScoped
public class RegistroBean implements Serializable {
    private String nombre;
    private String contrasena;
    
    private DAOUsuario dao=new DAOUsuario();
    private Usuario usr;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String registrar() {
        usr = new Usuario();
            
        usr.setNombre(nombre);
        usr.setPass(contrasena);

        Usuario user=dao.create(usr);
        if(user!=null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Usuario registrado correctamente."));
            return "login.xhtml";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar el usuario."));
            return null;
        }
    }

    public String regresar() {
        return "login.xhtml";
    }
}
