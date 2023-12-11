package org.uv.jsf.Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.io.Serializable;
import java.sql.SQLException;
import org.uv.jsf.DAOUsuario;
import org.uv.jsf.Usuario;

@ManagedBean
@SessionScoped
public class loginBean implements Serializable {
    private String username;
    private String contrasena;
    
    private DAOUsuario dao=new DAOUsuario();
    private Usuario usr;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String iniciarSesion() throws SQLException {
        usr = new Usuario();
            
        usr.setNombre(username);
        usr.setPass(contrasena);
        Boolean rs=dao.login(usr);
        if (rs) {
                return "index.xhtml";
        } else {
            // Las credenciales son incorrectas, muestra un mensaje de error.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrectos."));
            return null; 
        }
    }

    public String irARegistro() {
        // Lógica para redireccionar al usuario a la página de registro.
        return "registro.xhtml"; // Debes retornar la página a la que deseas redireccionar.
    }
}
