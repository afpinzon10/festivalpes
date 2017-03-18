/*USUARIOS*/

INSERT INTO usuarios (ID,nombre,rol,email) VALUES ('admin1', 'albarourive', 'ADMINISTRADOR', 'au@au.com');
INSERT INTO usuarios (ID,nombre,rol,email) VALUES ('admin1', 'albarourive', 'AEROLINEA', 'au@au.com');

INSERT INTO usuarios (ID,nombre,rol,email) VALUES ('av1', 'albarourive2', 'AEROLINEA', 'au2@au.com');
INSERT INTO usuarios (ID,nombre,rol,email) VALUES ('bog1', 'albarourive3', 'AEROPUERTO', 'au2@au.com');

  /*CK*/
INSERT INTO usuarios (ID,nombre,rol,email) VALUES ('bog2', 'albarourive3', 'fallo', 'au2@au.com');



/*AERPOPUERTOS*/

INSERT INTO aeropuertos (iata, nombre, tipo, ciudad, pais, creador) VALUES ('BOG', 'El Dorado', 'INTERNACIONAL', 'Bogota', 'Colombia', 'admin1');
INSERT INTO aeropuertos (iata, nombre, tipo, ciudad, pais, creador) VALUES ('BOG', 'El Dorado 2', 'INTERNACIONAL', 'Bogota', 'Colombia', 'admin1');

INSERT INTO aeropuertos (iata, nombre, tipo, ciudad, pais, creador) VALUES ('MIA', 'Miami Intl', 'INTERNACIONAL', 'Miami', 'USA', 'admin1');

/*FK*/
INSERT INTO aeropuertos (iata, nombre, tipo, ciudad, pais, creador) VALUES ('LIN', 'Linate', 'INTERNACIONAL', 'Milan', 'Italia', 'admin2');




/*AEROLINEAS*/
INSERT INTO aerolineas (iata, nombre, pais, creador) VALUES ('AV', 'Avianca', 'Colombia', 'admin1');

INSERT INTO aerolineas (iata, nombre, pais, creador) VALUES ('AV', 'Delta', 'USA', 'admin1');

/*FK*/
INSERT INTO aerolineas (iata, nombre, pais, creador) VALUES ('DL', 'Delta', 'USA', 'admin2');




/*CLIENTES*/
INSERT INTO clientes (tipo_id, id, nombre, remitente, viajero, frecuente, creador) VALUES ('cedula',1, 'alvarinho urivinho', 0, 1, 0, 'admin1');

INSERT INTO clientes (tipo_id, id, nombre, remitente, viajero, frecuente, creador) VALUES ('cedula',1, 'carlitos tevez', 0, 1, 0, 'admin1');

  /*FK*/
INSERT INTO clientes (tipo_id, id, nombre, remitente, viajero, frecuente, creador) VALUES ('cedula',2, 'carlitos tevez', 0, 1, 0, 'admin2');

  /*CK*/
INSERT INTO clientes (tipo_id, id, nombre, remitente, viajero, frecuente, creador) VALUES ('cedula',3, 'carlitos tevez', 2, 1, 0, 'admin2');
 




/*AERONAVES*/

INSERT INTO aeronaves (modelo, numero_serie, tipo_aeronave, tipo, marca, ano_fabricacion, sillas_economicas, sillas_ejecutivas, creador) 
  VALUES ('B747', 1, 'avion', 'COMERCIAL', 'BOEING', 2005, 100 , 30,'av1');

INSERT INTO aeronaves (modelo, numero_serie, tipo_aeronave, tipo, marca, ano_fabricacion, sillas_economicas, sillas_ejecutivas, creador) 
  VALUES ('B747', 1, 'avion', 'COMERCIAL', 'BOEING', 2005, 100 , 30,'av1'); 
 
  /*FK*/
 
INSERT INTO aeronaves (modelo, numero_serie, tipo_aeronave, tipo, marca, ano_fabricacion, sillas_economicas, sillas_ejecutivas, creador) 
  VALUES ('B747', 2, 'avion', 'COMERCIAL', 'BOEING', 2005, 100 , 30,'av2'); 
 
  
/*ADMINISTRA*/  
INSERT INTO administra (id_usuario, aerolinea) VALUES ('av1','AV');

INSERT INTO administra (id_usuario, aerolinea) VALUES ('av1','AV');

  /*FK*/
INSERT INTO administra (id_usuario, aerolinea) VALUES ('av1','SS');



/* OPERA */
INSERT INTO opera (id_usuario, aeropuerto) VALUES ('bog1', 'BOG');

INSERT INTO opera (id_usuario, aeropuerto) VALUES ('bog1', 'BOG');

  /*FK*/
INSERT INTO opera (id_usuario, aeropuerto) VALUES ('bog1', 'NYC');




/*USA*/
INSERT INTO usa (aerolinea, modelo, numero_serie) VALUES ('AV', 'B747', 1);

INSERT INTO usa (aerolinea, modelo, numero_serie) VALUES ('AV', 'B747', 1);

  /*FK*/
INSERT INTO usa (aerolinea, modelo, numero_serie) VALUES ('AV', 'B747', 100);



/* VUELOS */
INSERT INTO vuelos (iata, origen, destino, aerolinea, tipo, frecuencia, hora_llegada, hora_salida, distancia, duracion, costo_economica, costo_ejecutiva, creador)
  VALUES ('AV1', 'BOG', 'MIA', 'AV', 'COMERCIAL', 'LMXJV', '18:30', '12:00', 1509, '05:30', 300000, 50000, 'av1');

INSERT INTO vuelos (iata, origen, destino, aerolinea, tipo, frecuencia, hora_llegada, hora_salida, distancia, duracion, costo_economica, costo_ejecutiva, creador)
  VALUES ('AV1', 'BOG', 'MIA', 'AV', 'COMERCIAL', 'L', '18:30', '12:00', 1509, '05:30', 700000, 80000, 'av1'); 

  /*FK*/
INSERT INTO vuelos (iata, origen, destino, aerolinea, tipo, frecuencia, hora_llegada, hora_salida, distancia, duracion, costo_economica, costo_ejecutiva, creador)
  VALUES ('AV1', 'BOG', 'MIA', 'SS', 'COMERCIAL', 'L', '18:30', '12:00', 1509, '05:30', 700000, 80000, 'av10');

  /*CK*/
INSERT INTO vuelos (iata, origen, destino, aerolinea, tipo, frecuencia, hora_llegada, hora_salida, distancia, duracion, costo_economica, costo_ejecutiva, creador)
  VALUES ('AV2', 'BOG', 'MIA', 'AV', 'Error', 'L', '18:30', '12:00', 1509, '05:30', 700000, 80000, 'av1');
  


/*VIAJES*/
INSERT INTO viajes (iata, fecha, modelo_aeronave, numero_serie_aeronave, tipo, sillas_economicas_disponibles, sillas_ejecutivas_disponibles)
  VALUES ('AV1',to_date('10/30/2016','mm/dd/yyyy'),'B747', 1, 'COMERCIAL', 100, 30);
  
  /*PK*/
INSERT INTO viajes (iata, fecha, modelo_aeronave, numero_serie_aeronave, tipo, sillas_economicas_disponibles, sillas_ejecutivas_disponibles)
  VALUES ('AV1',to_date('10/30/2016','mm/dd/yyyy'),'B747', 1, 'CARGA', 100, 30);
  
  /*FK*/
    
INSERT INTO viajes (iata, fecha, modelo_aeronave, numero_serie_aeronave, tipo, sillas_economicas_disponibles, sillas_ejecutivas_disponibles)
  VALUES ('AV1',to_date('10/30/2016','mm/dd/yyyy'),'B747', 1, 'COMERCIAL', 100, 30);
  
  
  
INSERT INTO reservas (tipo_id_cliente, id_cliente, iata_vuelo, fecha, tipo, tipo_silla) 
  VALUES ('cedula',1, 'AV1', to_date('10/30/2016','mm/dd/yyyy'), 'COMERCIAL', 'ECONOMICA' );
