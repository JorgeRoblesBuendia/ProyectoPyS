import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class GestionProductos {

    // Conexión a la BD
    private static final String URL = "jdbc:mysql://belbr9kwb1stmqbvm6si-mysql.services.clever-cloud.com:3306/belbr9kwb1stmqbvm6si?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "uakgprfg2wghdbl8";
    private static final String PASSWORD = "GbNuYm3g8kkcG1jRi14n";

    // Modelo de tabla
    private static DefaultTableModel modeloTabla;

    // Campos para entrada de datos
    private static JTextField nombreText, precioCompraText, precioVentaText, stockText, stockMinimoText, codigoBarrasText, fechaVencimientoText, buscarText;
    private static JTable tabla;
    private static JComboBox<String> categoriaComboBox, proveedorComboBox;

    public static void main(String[] args) {
        // Inicializar ComboBox antes de usar
        categoriaComboBox = new JComboBox<>();
        proveedorComboBox = new JComboBox<>();

        // Crear ventana
        JFrame frame = new JFrame("Gestión de Productos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        // Colocar componentes
        colocarComponentes(panel);

        // Cargar datos desde la BD
        cargarDatosDesdeBD();
        cargarCategorias();
        cargarProveedores();

        frame.setVisible(true);
    }

    private static void colocarComponentes(JPanel panel) {
        JLabel tituloLabel = new JLabel("Gestión de Productos");
        tituloLabel.setBounds(300, 10, 300, 25);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(tituloLabel);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(20, 50, 100, 25);
        panel.add(nombreLabel);

        nombreText = new JTextField(20);
        nombreText.setBounds(120, 50, 160, 25);
        panel.add(nombreText);

        JLabel precioCompraLabel = new JLabel("Precio Compra:");
        precioCompraLabel.setBounds(20, 90, 100, 25);
        panel.add(precioCompraLabel);

        precioCompraText = new JTextField(20);
        precioCompraText.setBounds(120, 90, 160, 25);
        panel.add(precioCompraText);

        JLabel precioVentaLabel = new JLabel("Precio Venta:");
        precioVentaLabel.setBounds(20, 130, 100, 25);
        panel.add(precioVentaLabel);

        precioVentaText = new JTextField(20);
        precioVentaText.setBounds(120, 130, 160, 25);
        panel.add(precioVentaText);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(20, 170, 100, 25);
        panel.add(stockLabel);

        stockText = new JTextField(20);
        stockText.setBounds(120, 170, 160, 25);
        panel.add(stockText);

        JLabel stockMinimoLabel = new JLabel("Stock Mínimo:");
        stockMinimoLabel.setBounds(20, 210, 100, 25);
        panel.add(stockMinimoLabel);

        stockMinimoText = new JTextField(20);
        stockMinimoText.setBounds(120, 210, 160, 25);
        panel.add(stockMinimoText);

        JLabel categoriaLabel = new JLabel("Categoría:");
        categoriaLabel.setBounds(20, 250, 100, 25);
        panel.add(categoriaLabel);

        categoriaComboBox.setBounds(120, 250, 160, 25);
        panel.add(categoriaComboBox);

        JLabel proveedorLabel = new JLabel("Proveedor:");
        proveedorLabel.setBounds(20, 290, 100, 25);
        panel.add(proveedorLabel);

        proveedorComboBox.setBounds(120, 290, 160, 25);
        panel.add(proveedorComboBox);

        JLabel codigoBarrasLabel = new JLabel("Código Barras:");
        codigoBarrasLabel.setBounds(20, 330, 100, 25);
        panel.add(codigoBarrasLabel);

        codigoBarrasText = new JTextField(20);
        codigoBarrasText.setBounds(120, 330, 160, 25);
        panel.add(codigoBarrasText);

        // Generar código de barras automáticamente al hacer clic
        codigoBarrasText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                generarCodigoBarras();
            }
        });

        JLabel fechaVencimientoLabel = new JLabel("Fecha Vencimiento:");
        fechaVencimientoLabel.setBounds(20, 370, 120, 25);
        panel.add(fechaVencimientoLabel);

        fechaVencimientoText = new JTextField(20);
        fechaVencimientoText.setBounds(150, 370, 130, 25);
        panel.add(fechaVencimientoText);

        // Botones
        JButton agregarButton = new JButton("Agregar");
        agregarButton.setBounds(320, 50, 120, 25);
        panel.add(agregarButton);

        JButton actualizarButton = new JButton("Actualizar");
        actualizarButton.setBounds(320, 90, 120, 25);
        panel.add(actualizarButton);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(320, 130, 120, 25);
        panel.add(eliminarButton);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(320, 170, 120, 25);
        panel.add(buscarButton);
        
        JButton menuButton = new JButton("Regresar al Menú");
        menuButton.setBounds(320, 260, 160, 25);
        panel.add(menuButton);

        JLabel buscarLabel = new JLabel("Buscar:");
        buscarLabel.setBounds(320, 210, 60, 25);
        panel.add(buscarLabel);

        buscarText = new JTextField(20);
        buscarText.setBounds(380, 210, 160, 25);
        panel.add(buscarText);

        // Tabla
        String[] columnas = {"ID", "Nombre", "Precio Compra", "Precio Venta", "Stock", "Stock Minimo", "ID Categoria", "ID Proveedor", "Código Barras", "Fecha Vencimiento"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(20, 420, 740, 130);
        panel.add(scrollPane);

        // Acciones de los botones
        agregarButton.addActionListener(e -> agregarProducto());
        actualizarButton.addActionListener(e -> actualizarProducto());
        eliminarButton.addActionListener(e -> eliminarProducto());
        buscarButton.addActionListener(e -> buscarProducto());
        menuButton.addActionListener(e -> mostrarMenuPrincipal());
    }

    // Cargar datos desde la BD
    private static void cargarDatosDesdeBD() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM Productos";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            modeloTabla.setRowCount(0);
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getDouble("precioCompra"),
                        rs.getDouble("precioVenta"),
                        rs.getInt("stock"),
                        rs.getInt("stockMinimo"),
                        rs.getInt("idCategoria"),
                        rs.getInt("idProveedor"),
                        rs.getString("codigoBarras"),
                        rs.getDate("fechaVencimiento")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar productos: " + e.getMessage());
        }
    }

    // Cargar categorías en el ComboBox
    private static void cargarCategorias() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT idCategoria, nombre FROM Categorias";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            categoriaComboBox.removeAllItems();
            categoriaComboBox.addItem("-- Selecciona una categoría --");

            while (rs.next()) {
                categoriaComboBox.addItem(rs.getString("nombre") + " - " + rs.getInt("idCategoria"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar categorías: " + e.getMessage());
        }
    }

    // Cargar proveedores en el ComboBox
    private static void cargarProveedores() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT idProveedor, nombre FROM Proveedores";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            proveedorComboBox.removeAllItems();
            proveedorComboBox.addItem("-- Selecciona un proveedor --");

            while (rs.next()) {
                proveedorComboBox.addItem(rs.getString("nombre") + " - " + rs.getInt("idProveedor"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar proveedores: " + e.getMessage());
        }
    }

    // Obtener el ID seleccionado desde el ComboBox
    private static int obtenerIdSeleccionado(JComboBox<String> comboBox) {
        String seleccion = (String) comboBox.getSelectedItem();
        if (seleccion != null && !seleccion.equals("-- Selecciona una categoría --") && !seleccion.equals("-- Selecciona un proveedor --")) {
            String[] partes = seleccion.split(" - ");
            if (partes.length == 2) {
                return Integer.parseInt(partes[1]);
            }
        }
        return -1;
    }

    // Generar código de barras automáticamente
    private static void generarCodigoBarras() {
        Random rand = new Random();
        int codigo = 100000 + rand.nextInt(900000);
        codigoBarrasText.setText(String.valueOf(codigo));
    }
    
    // Mostrar menú principal (invocar método del menú principal)
    private static void mostrarMenuPrincipal() {
        JOptionPane.showMessageDialog(null, "Regresando al menú principal...");
        // Llamada a la interfaz del menú principal
        VentanaMenu.mostrarMenu();
    }

    // Agregar producto
    private static void agregarProducto() {
        int idCategoriaSeleccionada = obtenerIdSeleccionado(categoriaComboBox);
        int idProveedorSeleccionado = obtenerIdSeleccionado(proveedorComboBox);

        if (idCategoriaSeleccionada == -1 || idProveedorSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Por favor selecciona una categoría y un proveedor válidos.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO Productos (nombre, precioCompra, precioVenta, stock, stockMinimo, idCategoria, idProveedor, codigoBarras, fechaVencimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreText.getText());
            stmt.setDouble(2, Double.parseDouble(precioCompraText.getText()));
            stmt.setDouble(3, Double.parseDouble(precioVentaText.getText()));
            stmt.setInt(4, Integer.parseInt(stockText.getText()));
            stmt.setInt(5, Integer.parseInt(stockMinimoText.getText()));
            stmt.setInt(6, idCategoriaSeleccionada);
            stmt.setInt(7, idProveedorSeleccionado);
            stmt.setString(8, codigoBarrasText.getText());
            stmt.setString(9, fechaVencimientoText.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto agregado correctamente.");
            modeloTabla.setRowCount(0);
            cargarDatosDesdeBD();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar producto: " + e.getMessage());
        }
    }

    // Actualizar producto
    private static void actualizarProducto() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idProducto = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            int idCategoriaSeleccionada = obtenerIdSeleccionado(categoriaComboBox);
            int idProveedorSeleccionado = obtenerIdSeleccionado(proveedorComboBox);

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "UPDATE Productos SET nombre=?, precioCompra=?, precioVenta=?, stock=?, stockMinimo=?, idCategoria=?, idProveedor=?, codigoBarras=?, fechaVencimiento=? WHERE idProducto=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nombreText.getText());
                stmt.setDouble(2, Double.parseDouble(precioCompraText.getText()));
                stmt.setDouble(3, Double.parseDouble(precioVentaText.getText()));
                stmt.setInt(4, Integer.parseInt(stockText.getText()));
                stmt.setInt(5, Integer.parseInt(stockMinimoText.getText()));
                stmt.setInt(6, idCategoriaSeleccionada);
                stmt.setInt(7, idProveedorSeleccionado);
                stmt.setString(8, codigoBarrasText.getText());
                stmt.setString(9, fechaVencimientoText.getText());
                stmt.setInt(10, idProducto);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
                modeloTabla.setRowCount(0);
                cargarDatosDesdeBD();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar producto: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un producto para actualizar.");
        }
    }

    // Eliminar producto
    private static void eliminarProducto() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idProducto = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "DELETE FROM Productos WHERE idProducto=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idProducto);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                modeloTabla.setRowCount(0);
                cargarDatosDesdeBD();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un producto para eliminar.");
        }
    }

    // Buscar producto
    private static void buscarProducto() {
        String nombreBuscar = buscarText.getText().trim();
        if (nombreBuscar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Introduce un nombre para buscar.");
            return;
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM Productos WHERE nombre LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nombreBuscar + "%");
            ResultSet rs = stmt.executeQuery();

            modeloTabla.setRowCount(0);
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getDouble("precioCompra"),
                        rs.getDouble("precioVenta"),
                        rs.getInt("stock"),
                        rs.getInt("stockMinimo"),
                        rs.getInt("idCategoria"),
                        rs.getInt("idProveedor"),
                        rs.getString("codigoBarras"),
                        rs.getDate("fechaVencimiento")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar producto: " + e.getMessage());
        }
    }
}
/*
// Método para mostrar el menú principal
public static void mostrarMenu() {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new VentanaMenu().setVisible(true);
        }
    });
}
*/