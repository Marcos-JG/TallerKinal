-- Datos de prueba: 5 filas por tabla
-- Limpia tablas para evitar duplicados y asegura orden de inserción
INSERT IGNORE INTO administradores( nombre, apellido, correo, contrasena, telefono) VALUES
('Admin','Uno','admin1@taller.com','pass1','555-0001'),
('Admin','Dos','admin2@taller.com','pass2','555-0002'),
('Admin','Tres','admin3@taller.com','pass3','555-0003'),
('Admin','Cuatro','admin4@taller.com','pass4','555-0004'),
('Admin','Cinco','admin5@taller.com','pass5','555-0005');

-- Clientes
INSERT IGNORE INTO clientes( nombre, apellido, correo, contrasena) VALUES
('Cliente','Uno','cliente1@taller.com','cpass1'),
('Cliente','Dos','cliente2@taller.com','cpass2'),
('Cliente','Tres','cliente3@taller.com','cpass3'),
('Cliente','Cuatro','cliente4@taller.com','cpass4'),
('Cliente','Cinco','cliente5@taller.com','cpass5');

-- Mecanicos
INSERT IGNORE INTO mecanicos( nombre, apellido, telefono) VALUES
('Mec','Uno','600-0001'),
('Mec','Dos','600-0002'),
('Mec','Tres','600-0003'),
('Mec','Cuatro','600-0004'),
('Mec','Cinco','600-0005');

-- Proveedores
INSERT IGNORE INTO proveedores( nombre_empresa, contacto, telefono, correo) VALUES
('Proveedor A','Contacto A','700-0001','provA@proveedor.com'),
('Proveedor B','Contacto B','700-0002','provB@proveedor.com'),
('Proveedor C','Contacto C','700-0003','provC@proveedor.com'),
('Proveedor D','Contacto D','700-0004','provD@proveedor.com'),
('Proveedor E','Contacto E','700-0005','provE@proveedor.com');

-- Categorias de Producto
INSERT IGNORE INTO categorias_producto(nombre) VALUES
('Aceites'),
('Filtros'),
('Frenos'),
('Iluminacion'),
('Neumaticos');

-- Marcas Producto
INSERT IGNORE INTO marcas_producto( nombre) VALUES
('MarcaA'),
('MarcaB'),
('MarcaC'),
('MarcaD'),
('MarcaE');

-- Productos Inventario
INSERT IGNORE INTO productos_inventario( id_proveedor, nombre, descripcion, id_categoria, especificacion, precio_unitario, stock_actual, stock_minimo, id_marca, fecha_entrada) VALUES
(1,'Aceite 5W-30','Aceite sintético 1L',1,'API SP',25.50,100,10,1,'2025-01-02'),
(2,'Filtro de aceite','Filtro para motor',2,'STD',8.75,50,5,2,'2025-02-10'),
(3,'Pastillas de freno','Juego delantero',3,'CERAMIC',45.00,30,3,3,'2025-03-05'),
(4,'Bombilla H4','Bombilla halógena',4,'12V',12.00,20,2,4,'2025-01-20'),
(5,'Neumático 205/55','Neumático radial',5,'RIM17',85.00,15,2,5,'2025-02-28');

-- Vehículos
INSERT IGNORE INTO vehiculos( id_cliente, placas, color, modelo, marca, año) VALUES
(1,'ABC-123','Rojo','Model X','MarcaA',2018),
(2,'DEF-456','Azul','Model Y','MarcaB',2020),
(3,'GHI-789','Blanco','Model Z','MarcaC',2015),
(4,'JKL-012','Negro','Model A','MarcaD',2022),
(5,'MNO-345','Gris','Model B','MarcaE',2010);

-- Citas
INSERT IGNORE INTO citas( fecha_cita, id_empleado, id_cliente, tipo_cita, id_vehiculo, estado_cita, comentario) VALUES
('2025-06-10',1,1,'mantenimiento',1,'pendiente','Revisión general'),
('2025-06-11',2,2,'reparación',2,'en proceso','Fallo en transmisión'),
('2025-06-12',3,3,'diagnóstico',3,'finalizada','Sensor cambiado'),
('2025-06-13',4,4,'mantenimiento',4,'cancelada','Cliente canceló'),
('2025-06-14',5,5,'reparación',5,'pendiente','Alineación y balanceo');

-- Trabajos Realizados
INSERT IGNORE INTO trabajos_realizados( id_cita, descripcion, mano_obra, total_trabajo) VALUES
(1,'Cambio de aceite',30.00,55.50),
(2,'Reparación caja',150.00,500.00),
(3,'Diagnóstico eléctrico',40.00,70.00),
(4,'Revisión general',25.00,25.00),
(5,'Alineación',20.00,120.00);

-- Detalle Uso Producto
INSERT IGNORE INTO detalle_uso_producto( id_trabajo_realizado, id_producto_inventario, cantidad_usada, precio_unitario, subtotal) VALUES
(1,1,1,25.50,25.50),
(2,3,2,45.00,90.00),
(3,2,1,8.75,8.75),
(4,4,1,12.00,12.00),
(5,5,4,85.00,340.00);

-- Reportes
INSERT IGNORE INTO reportes( id_cita, descripcion_general, total) VALUES
(1,'Reporte cita 1',55.50),
(2,'Reporte cita 2',590.00),
(3,'Reporte cita 3',78.75),
(4,'Reporte cita 4',37.00),
(5,'Reporte cita 5',460.00);
