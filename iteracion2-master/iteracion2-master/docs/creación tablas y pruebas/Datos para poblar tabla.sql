INSERT INTO usuarios (ID,nombre,rol,email) VALUES ('admin1', 'albarourive', 'ADMINISTRADOR', 'au@au.com');
INSERT INTO usuarios (ID,nombre,rol,email) VALUES ('av1', 'albarourive2', 'AEROLINEA', 'au2@au.com');
INSERT INTO usuarios (ID,nombre,rol,email) VALUES ('bog1', 'albarourive3', 'AEROPUERTO', 'au2@au.com');

INSERT INTO aeropuertos (iata, nombre, tipo, ciudad, pais, creador) VALUES ('BOG', 'El Dorado', 'INTERNACIONAL', 'Bogota', 'Colombia', 'admin1');
INSERT INTO aeropuertos (iata, nombre, tipo, ciudad, pais, creador) VALUES ('MIA', 'Miami Intl', 'INTERNACIONAL', 'Miami', 'USA', 'admin1');

INSERT INTO aerolineas (iata, nombre, pais, creador) VALUES ('AV', 'Avianca', 'Colombia', 'admin1');

INSERT INTO clientes (tipo_id, id, nombre, remitente, viajero, frecuente, creador) VALUES ('cedula',1, 'alvarinho urivinho', 0, 1, 0, 'admin1');

INSERT INTO aeronaves (modelo, numero_serie, tipo_aeronave, tipo, marca, ano_fabricacion, sillas_economicas, sillas_ejecutivas, creador) 
  VALUES ('B747', 1, 'avion', 'COMERCIAL', 'BOEING', 2005, 100 , 30,'av1');
  
INSERT INTO administra (id_usuario, aerolinea) VALUES ('av1','AV');

INSERT INTO opera (id_usuario, aeropuerto) VALUES ('bog1', 'BOG');

INSERT INTO usa (aerolinea, modelo, numero_serie) VALUES ('AV', 'B747', 1);

INSERT INTO vuelos (iata, origen, destino, aerolinea, tipo, frecuencia, hora_llegada, hora_salida, distancia, duracion, costo_economica, costo_ejecutiva, creador)
  VALUES ('AV1', 'BOG', 'MIA', 'AV', 'COMERCIAL', 'LMXJV', '18:30', '12:00', 1509, '05:30', 300000, 50000, 'av1');
  
INSERT INTO viajes (iata, fecha, modelo_aeronave, numero_serie_aeronave, tipo, sillas_economicas_disponibles, sillas_ejecutivas_disponibles)
  VALUES ('AV1',to_date('10/30/2016','mm/dd/yyyy'),'B747', 1, 'COMERCIAL', 100, 30);
  
INSERT INTO reservas (tipo_id_cliente, id_cliente, iata_vuelo, fecha, tipo, tipo_silla) 
  VALUES ('cedula',1, 'AV1', to_date('10/30/2016','mm/dd/yyyy'), 'COMERCIAL', 'ECONOMICA' );
