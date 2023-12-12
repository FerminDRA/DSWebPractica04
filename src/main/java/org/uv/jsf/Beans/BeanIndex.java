package org.uv.jsf.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.uv.jsf.DAOPersona;
import org.uv.jsf.Persona;

@Named(value = "beanIndex ")
@SessionScoped
public class BeanIndex implements Serializable {
    private String clave;
    private String nombre;
    private String direccion;
    private String telefono;
    private List<Persona> personas;
    
    private DAOPersona dao=new DAOPersona();
    private Persona prsn;

    public BeanIndex() {
        personas = new ArrayList<>();
        cargarPersonas();
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public List<Persona> getPersonas() {
        return personas;
    }

    public void cargarPersonas() {
        List<Persona> prsns=new ArrayList<>();
        prsns= dao.findAll();

        personas.clear();

        for (Persona prsn1 : prsns) {
            Persona persona = new Persona();
            persona.setClave(prsn1.getClave());
            persona.setNombre(prsn1.getNombre());
            persona.setDireccion(prsn1.getDireccion());
            persona.setTelefono(prsn1.getTelefono());
            personas.add(persona);
        }
    }


    public void crearPersona() {
        prsn= new Persona();
        prsn.setClave(Integer.parseInt(clave));
        prsn.setNombre(nombre);
        prsn.setDireccion(direccion);
        prsn.setTelefono(telefono);
        
        Persona pers=dao.create(prsn);
        if(pers!=null){
            addMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se guardó");
            cargarPersonas();
            limpiarCampos();
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar empleado."));
        }
        try {
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void actualizarPersona(Persona persona) {
//        try {
//            Conexion cx = Conexion.getConexion();
//            String sql = "UPDATE persona SET nombre = ?, direccion = ?, telefeno = ? WHERE clave = ?";
//            PreparedStatement pstmt = cx.con.prepareStatement(sql);
//            pstmt.setString(1, persona.getNombre());
//            pstmt.setString(2, persona.getDireccion());
//            pstmt.setString(3, persona.getTelefono());
//            pstmt.setInt(4, persona.getClave());
//            pstmt.executeUpdate();
//
//            addMessage(FacesMessage.SEVERITY_INFO, "Atención...", "Se actualizó");
//            cargarPersonas();
//            limpiarCampos();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void eliminarPersona(Persona persona) {
        Boolean rs=dao.delete(persona.getClave());
        if(rs){
            addMessage(FacesMessage.SEVERITY_WARN, "Atención...", "Se eliminó");
            cargarPersonas();
            limpiarCampos();
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar empleado."));
        }
    }

    public void limpiarCampos() {
        this.clave = "";
        this.nombre = "";
        this.direccion = "";
        this.telefono = "";
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
}






