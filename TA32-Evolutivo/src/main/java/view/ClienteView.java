package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClienteView {
	private JFrame frame;
	private JTextField id, nombre, apellido, direccion, dni, fecha;
    private JLabel labelid, labelnombre, labelapellido, labeldireccion, labeldni, labelfecha;
    private JButton btnGuardar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnBuscar;
    
    
    @SuppressWarnings("deprecation")
	public ClienteView() {
    	frame = new JFrame("Cliente");
    	id = new JTextField();
    	nombre = new JTextField();
    	apellido = new JTextField();
    	direccion = new JTextField();
    	dni = new JTextField();
    	fecha = new JTextField();
    	
    	id.setBounds(100, 25, 280, 30);
    	nombre.setBounds(100, 55, 280, 30);
    	apellido.setBounds(100, 85, 280, 30);
    	direccion.setBounds(100, 115, 280, 30);
    	dni.setBounds(100, 145, 280, 30);
    	fecha.setBounds(100, 175, 280, 30);
    	
    	frame.add(id);
        frame.add(nombre);
        frame.add(apellido);
        frame.add(direccion);
        frame.add(dni);
        frame.add(fecha);

    	
    	labelid = new JLabel();
    	labelnombre = new JLabel();
    	labelapellido = new JLabel();
    	labeldireccion = new JLabel();
    	labeldni = new JLabel();
    	labelfecha = new JLabel();
        
    	labelid.setBounds(30, 25, 280, 30);
    	labelnombre.setBounds(30, 55, 280, 30);
    	labelapellido.setBounds(30, 85, 280, 30);
    	labeldireccion.setBounds(30, 115, 280, 30);
    	labeldni.setBounds(30, 145, 280, 30);
    	labelfecha.setBounds(30, 175, 280, 30);

    	labelid.setText("ID: ");
        labelnombre.setText("Nombre: ");
        labelapellido.setText("Apellido: ");
        labeldireccion.setText("Direccion: ");
        labeldni.setText("DNI: ");
        labelfecha.setText("Fecha: ");
        
        frame.add(labelid);
        frame.add(labelnombre);
        frame.add(labelapellido);
        frame.add(labeldireccion);
        frame.add(labeldni);
        frame.add(labelfecha);
        
        btnGuardar = new JButton();
        btnGuardar.setText("Guardar");
        btnGuardar.setBounds(30, 225, 280, 30);
        frame.add(btnGuardar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(325, 225, 100, 30);
        frame.add(btnEliminar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(325, 265, 100, 30);
        frame.add(btnActualizar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(325, 305, 100, 30);
        frame.add(btnBuscar);
        
        frame.setLayout(null);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    // Métodos para añadir ActionListeners
    public void addGuardarButtonListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void addEliminarButtonListener(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }

    public void addActualizarButtonListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
    }

    public void addBuscarButtonListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }

    // Métodos para mostrar mensajes al usuario
    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Métodos getters y setters para campos de texto
    public String getId() {
        return id.getText().trim();
    }

    public String getNombre() {
        return nombre.getText().trim();
    }

    public String getApellido() {
        return apellido.getText().trim();
    }

    public String getDireccion() {
        return direccion.getText().trim();
    }

    public String getDni() {
        return dni.getText().trim();
    }

    public String getFecha() {
        return fecha.getText().trim();
    }

    public void setNombre(String nombre) {
        this.nombre.setText(nombre);
    }

    public void setApellido(String apellido) {
        this.apellido.setText(apellido);
    }

    public void setDireccion(String direccion) {
        this.direccion.setText(direccion);
    }

    public void setDni(String dni) {
        this.dni.setText(dni);
    }

    public void setFecha(String fecha) {
        this.fecha.setText(fecha);
    }

    // Validaciones básicas para datos del cliente
    public boolean isValidData() {
        if (getNombre().isEmpty() || getApellido().isEmpty() || getDireccion().isEmpty() || getDni().isEmpty() || getFecha().isEmpty()) {
            showErrorMessage("Todos los campos son obligatorios.");
            return false;
        }

        try {
            Integer.parseInt(getDni());
        } catch (NumberFormatException e) {
            showErrorMessage("El DNI debe ser un número válido.");
            return false;
        }

        try {
            java.sql.Date.valueOf(getFecha());
        } catch (IllegalArgumentException e) {
            showErrorMessage("La fecha debe tener el formato yyyy-MM-dd.");
            return false;
        }

        return true;
    }
    }
    
