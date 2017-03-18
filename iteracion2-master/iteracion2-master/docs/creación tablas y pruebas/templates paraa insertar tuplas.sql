INSERT INTO usuarios (ID,nombre,rol,email) VALUES ();

INSERT INTO aeropuertos (iata, nombre, tipo, ciudad, pais, creador) VALUES ();

INSERT INTO aerolineas (iata, nombre, pais, creador) VALUES ();

INSERT INTO clientes (tipo_id, id, nombre, remitente, viajero, frecuente, creador) VALUES ();

INSERT INTO aeronaves (modelo, numero_serie, tipo_aeronave, tipo, marca, ano_fabricacion, capacidad, sillas_economicas, sillas_ejecutivas, creador) 
  VALUES ();
  
INSERT INTO administra (id_usuario, aerolinea) VALUES ();

INSERT INTO opera (id_usuario, aeropuerto) VALUES ();

INSERT INTO usa (aerolinea, modelo, numero_serie) VALUES ();

INSERT INTO vuelos (iata, origen, destino, aerolinea, tipo, frecuencia, hora_llegada, hora_salida, distancia, duracion, costo_densidad, costo_economica, costo_ejecutiva, creador)
  VALUES ();
  
INSERT INTO viajes (iata, fecha, modelo_aeronave, numero_serie_aeronave, tipo, capacidad_disponible, sillas_economicas_disponibles, sillas_ejecutivas_disponibles)
  VALUES ();
  
INSERT INTO reservas (tipo_id_cliente, id_cliente, iata_vuelo, fecha, tipo, tipo_silla, volumen, peso, contenido) VALUES ();

COMMIT;

