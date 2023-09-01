package controller;

import dto.ClienteDTO;
import dao.ClienteDAO;
import view.ClienteView;
import java.sql.Date;

public class ClienteController {
    private ClienteView view;
    private ClienteDAO dao;

    public ClienteController(ClienteView view, ClienteDAO dao) {
        this.view = view;
        this.dao = dao;

        initController();
    }

    private void initController() {
        view.addGuardarButtonListener(e -> guardarCliente());
        view.addEliminarButtonListener(e -> eliminarCliente());
        view.addActualizarButtonListener(e -> actualizarCliente());
        view.addBuscarButtonListener(e -> buscarCliente());
    }

    private void guardarCliente() {
        try {
            ClienteDTO cliente = getClienteFromView();
            dao.guardarCliente(cliente);
            view.showSuccessMessage("Cliente guardado con éxito!");
        } catch (Exception e) {
            view.showErrorMessage("Error al guardar: " + e.getMessage());
        }
    }

    private void eliminarCliente() {
        try {
            int id = Integer.parseInt(view.getId());
            dao.eliminarCliente(id);
            view.showSuccessMessage("Cliente eliminado con éxito!");
        } catch (Exception e) {
            view.showErrorMessage("Error al eliminar: " + e.getMessage());
        }
    }

    private void actualizarCliente() {
        try {
            ClienteDTO cliente = getClienteFromView();
            dao.actualizarCliente(cliente);
            view.showSuccessMessage("Cliente actualizado con éxito!");
        } catch (Exception e) {
            view.showErrorMessage("Error al actualizar: " + e.getMessage());
        }
    }

    private void buscarCliente() {
        try {
            int id = Integer.parseInt(view.getId());
            ClienteDTO cliente = dao.buscarCliente(id);
            if(cliente != null) {
                view.setNombre(cliente.getNombre());
                view.setApellido(cliente.getApellido());
                view.setDireccion(cliente.getDireccion());
                view.setDni(String.valueOf(cliente.getDni()));
                view.setFecha(String.valueOf(cliente.getFecha()));
            } else {
                view.showErrorMessage("Cliente no encontrado.");
            }
        } catch (Exception e) {
            view.showErrorMessage("Error al buscar: " + e.getMessage());
        }
    }

    private ClienteDTO getClienteFromView() throws Exception {
        String nombre = view.getNombre();
        String apellido = view.getApellido();
        String direccion = view.getDireccion();
        int dni = Integer.parseInt(view.getDni());
        Date fecha = Date.valueOf(view.getFecha());

        return new ClienteDTO(nombre, apellido, direccion, dni, fecha);
    }
}
