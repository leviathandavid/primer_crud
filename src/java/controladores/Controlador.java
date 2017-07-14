/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Usuario;
import facade.AbstractFacade;
import facade.UsuarioFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controlador")
@ConversationScoped
public class Controlador implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    private Conversation conversacion;
    private Usuario usuarioSeleccionado;
    private Usuario usuario;
    private List<Usuario> listaUsuarios;
    private String nombre;
    private int documento;

    public Controlador() {

    }

    @PostConstruct
    public void init() {
        listaUsuarios = usuarioFacadeLocal.findAll();

    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
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

    public void eliminar(Usuario u) {
        usuarioFacadeLocal.remove(u);

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void iniciarConversacion() {
        if (conversacion.isTransient()) {
            conversacion.begin();

        }

    }

    private void terminarConversacion() {
        if (!conversacion.isTransient()) {
            conversacion.end();

        }

    }

    public String cancelar() {
        terminarConversacion();
        return "index?faces-redirect=true";

    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public String prepararEditar(Usuario u) {
        iniciarConversacion();
        usuarioSeleccionado = u;
        System.out.println(usuarioSeleccionado.getNombre());
        return "editar?faces-redirect=true";

    }

    public String actualizar() {
        usuarioFacadeLocal.edit(usuarioSeleccionado);
        return cancelar();
    }

}

