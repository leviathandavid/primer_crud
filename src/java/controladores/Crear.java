/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Usuario;
import facade.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author David
 */
@Named(value = "crear")
@ViewScoped
public class Crear implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private String nombre;
    private int documento;

    public Crear() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String crear() {
        Usuario u = new Usuario();
        u.setId(null);
        u.setNombre(getNombre());
        u.setDocumento(getDocumento());
        usuarioFacadeLocal.create(u);
        return "index?faces-redirect=true";
    }

}
