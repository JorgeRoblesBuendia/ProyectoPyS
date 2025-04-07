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
    private static JTextField nombreText, precioCompraText, precioVentaText, stockText, stockMinimoText, codigoBarrasText, buscarText;
    private static JTable tabla;
    private static JComboBox<String> categoriaComboBox, proveedorComboBox;
    private static JComboBox<String> diaComboBox, mesComboBox, añoComboBox;

    public static void main(String[] args) {
        // Inicializar ComboBox antes de usar
        categoriaComboBox = new JComboBox<>();
        proveedorComboBox = new JComboBox<>();
        diaComboBox = new JComboBox<>();
        mesComboBox = new JComboBox<>();
        añoComboBox = new JComboBox<>();

        // Crear ventana
        JFrame frame = new JFrame("Gestión de Productos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 102, 102)); // Fondo color #FF6666
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
        
        // Validar que precio de venta sea mayor al precio de compra
        precioVentaText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    double precioCompra = Double.parseDouble(precioCompraText.getText());
                    double precioVenta = Double.parseDouble(precioVentaText.getText());

                    if (precioVenta <= precioCompra) {
                        JOptionPane.showMessageDialog(null, "El precio de venta debe ser mayor al precio de compra.");
                        precioVentaText.setText("");
                        precioVentaText.requestFocus();
                    }
                } catch (NumberFormatException ex) {
                    // Ignorar si no hay valores válidos aún
                }
            }
        });

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

        // Llenar días, meses y años
        for (int i = 1; i <= 31; i++) {
            diaComboBox.addItem(i < 10 ? "0" + i : String.valueOf(i));
        }
        for (int i = 1; i <= 12; i++) {
            mesComboBox.addItem(i < 10 ? "0" + i : String.valueOf(i));
        }
        // Llenar años desde el actual hasta dentro de 10 años
        for (int i = 2023; i <= 2033; i++) {
            añoComboBox.addItem(String.valueOf(i));
        }

        // Establecer las posiciones en la interfaz
        diaComboBox.setBounds(150, 370, 50, 25);
        mesComboBox.setBounds(210, 370, 50, 25);
        añoComboBox.setBounds(270, 370, 80, 25);

        panel.add(diaComboBox);
        panel.add(mesComboBox);
        panel.add(añoComboBox);

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
        String[] columnas = {"ID", "Nombre", "Precio Compra", "Precio Venta", "Stock", "Stock Minimo", "Categoría", "Proveedor", "Código Barras", "Fecha Vencimiento"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(20, 420, 740, 130);
        panel.add(scrollPane);
        
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila != -1 && fila < tabla.getRowCount()) {
                    // Obtener el valor de la columna
                    Object categoriaObj = tabla.getValueAt(fila, 6);
                    Object proveedorObj = tabla.getValueAt(fila, 7);

                    // Asegurarse de que no sean nulos y estén en el formato esperado
                    if (categoriaObj != null && categoriaObj instanceof String) {
                        String categoriaString = (String) categoriaObj;
                        if (categoriaString.contains(" - ")) {
                            int idCategoria = Integer.parseInt(categoriaString.split(" - ")[1]);
                            for (int i = 1; i < categoriaComboBox.getItemCount(); i++) {
                                String item = categoriaComboBox.getItemAt(i);
                                if (item.endsWith(" - " + idCategoria)) {
                                    categoriaComboBox.setSelectedIndex(i);
                                    break;
                                }
                            }
                        }
                    }

                    if (proveedorObj != null && proveedorObj instanceof String) {
                        String proveedorString = (String) proveedorObj;
                        if (proveedorString.contains(" - ")) {
                            int idProveedor = Integer.parseInt(proveedorString.split(" - ")[1]);
                            for (int i = 1; i < proveedorComboBox.getItemCount(); i++) {
                                String item = proveedorComboBox.getItemAt(i);
                                if (item.endsWith(" - " + idProveedor)) {
                                    proveedorComboBox.setSelectedIndex(i);
                                    break;
                                }
                            }
                        }
                    }

                    // Rellenar los otros campos
                    nombreText.setText(tabla.getValueAt(fila, 1).toString());
                    precioCompraText.setText(tabla.getValueAt(fila, 2).toString());
                    precioVentaText.setText(tabla.getValueAt(fila, 3).toString());
                    stockText.setText(tabla.getValueAt(fila, 4).toString());
                    stockMinimoText.setText(tabla.getValueAt(fila, 5).toString());
                    codigoBarrasText.setText(tabla.getValueAt(fila, 8).toString());
                    
                    // Actualizar los ComboBox de fecha
                    String fechaVencimiento = tabla.getValueAt(fila, 9).toString();
                    if (fechaVencimiento != null && !fechaVencimiento.isEmpty()) {
                        String[] partesFecha = fechaVencimiento.split("-");
                        if (partesFecha.length == 3) {
                            añoComboBox.setSelectedItem(partesFecha[0]);
                            mesComboBox.setSelectedItem(partesFecha[1]);
                            diaComboBox.setSelectedItem(partesFecha[2]);
                        }
                    }
                }
            }
        });

        // Acciones de los botones
        agregarButton.addActionListener(e -> agregarProducto());
        actualizarButton.addActionListener(e -> actualizarProducto());
        eliminarButton.addActionListener(e -> eliminarProducto());
        buscarButton.addActionListener(e -> buscarProducto());
        menuButton.addActionListener(e -> mostrarMenuPrincipal());
    }

    private static void cargarDatosDesdeBD() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = """
                SELECT p.idProducto, p.nombre, p.precioCompra, p.precioVenta, p.stock, p.stockMinimo,
                       c.nombre AS nombreCategoria, pr.nombre AS nombreProveedor,
                       p.codigoBarras, p.fechaVencimiento
                FROM Productos p
                JOIN Categorias c ON p.idCategoria = c.idCategoria
                JOIN Proveedores pr ON p.idProveedor = pr.idProveedor
            """;

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
                        rs.getString("nombreCategoria"),
                        rs.getString("nombreProveedor"),
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
    
    // Mostrar menú principal
    private static void mostrarMenuPrincipal() {
        JOptionPane.showMessageDialog(null, "Regresando al menú principal...");
        
        // Cerrar la ventana actual
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(tabla);
        frame.dispose();
        
        // Llamada para abrir el menú principal
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

        // Obtener la fecha de vencimiento desde los comboBoxes
        String dia = (String) diaComboBox.getSelectedItem();
        String mes = (String) mesComboBox.getSelectedItem();
        String año = (String) añoComboBox.getSelectedItem();
        String fechaVencimiento = año + "-" + mes + "-" + dia; // Formato 'YYYY-MM-DD'

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
            stmt.setString(9, fechaVencimiento);
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

            // Obtener la fecha de vencimiento desde los comboBoxes
            String dia = (String) diaComboBox.getSelectedItem();
            String mes = (String) mesComboBox.getSelectedItem();
            String año = (String) añoComboBox.getSelectedItem();
            String fechaVencimiento = año + "-" + mes + "-" + dia;

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
                stmt.setString(9, fechaVencimiento);
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