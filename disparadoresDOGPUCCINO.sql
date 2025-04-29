







-- Disparador para INSERT Cliente
CREATE OR REPLACE TRIGGER fecha_cliente_disparador_insert
BEFORE INSERT ON cliente
FOR EACH ROW
BEGIN
  :NEW.fecha_alta := SYSDATE;
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para UPDATE Cliente
CREATE OR REPLACE TRIGGER fecha_cliente_disparador_update
BEFORE UPDATE ON cliente
FOR EACH ROW
BEGIN
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para INSERT Protectora

CREATE OR REPLACE TRIGGER fecha_protectora_disparador_insert
BEFORE INSERT ON protectora
FOR EACH ROW
BEGIN
  :NEW.fecha_alta := SYSDATE;
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para UPDATE Protectora

CREATE OR REPLACE TRIGGER fecha_protectora_disparador_update
BEFORE UPDATE ON protectora
FOR EACH ROW
BEGIN
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para INSERT Raza

CREATE OR REPLACE TRIGGER fecha_raza_disparador_insert
BEFORE INSERT ON raza
FOR EACH ROW
BEGIN
  :NEW.fecha_alta := SYSDATE;
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para UPDATE Raza

CREATE OR REPLACE TRIGGER fecha_raza_disparador_update
BEFORE UPDATE ON raza
FOR EACH ROW
BEGIN
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para INSERT Patologia

CREATE OR REPLACE TRIGGER fecha_patologia_disparador_insert
BEFORE INSERT ON patologia
FOR EACH ROW
BEGIN
  :NEW.fecha_alta := SYSDATE;
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para UPDATE Patologia

CREATE OR REPLACE TRIGGER fecha_patologia_disparador_update
BEFORE UPDATE ON patologia
FOR EACH ROW
BEGIN
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para INSERT Perro

CREATE OR REPLACE TRIGGER fecha_perro_disparador_insert
BEFORE INSERT ON perro
FOR EACH ROW
BEGIN
  :NEW.fecha_alta := SYSDATE;
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para UPDATE Perro

CREATE OR REPLACE TRIGGER fecha_perro_disparador_update
BEFORE UPDATE ON perro
FOR EACH ROW
BEGIN
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para INSERT Perro-Patologia

CREATE OR REPLACE TRIGGER fecha_perro_patologia_disparador_insert
BEFORE INSERT ON perro_patologia
FOR EACH ROW
BEGIN
  :NEW.fecha_alta := SYSDATE;
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para UPDATE Perro-Patologia

CREATE OR REPLACE TRIGGER fecha_perro_patologia_disparador_update
BEFORE UPDATE ON perro_patologia
FOR EACH ROW
BEGIN
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para INSERT Solicitud-Adopcion

CREATE OR REPLACE TRIGGER fecha_solicitud_disparador_insert
BEFORE INSERT ON solicitud_adopcion
FOR EACH ROW
BEGIN
  :NEW.fecha_alta := SYSDATE;
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para UPDATE Solicitud-Adopcion

CREATE OR REPLACE TRIGGER fecha_solicitud_disparador_update
BEFORE UPDATE ON solicitud_adopcion
FOR EACH ROW
BEGIN
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para INSERT Cita

CREATE OR REPLACE TRIGGER fecha_cita_disparador_insert
BEFORE INSERT ON cita
FOR EACH ROW
BEGIN
  :NEW.fecha_alta := SYSDATE;
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para UPDATE Cita

CREATE OR REPLACE TRIGGER fecha_cita_disparador_update
BEFORE UPDATE ON cita
FOR EACH ROW
BEGIN
  :NEW.fecha_modificacion := SYSDATE;
END;

-- Disparador para INSERT Notificaciones

CREATE OR REPLACE TRIGGER fecha_notificaciones_disparador_insert
BEFORE INSERT ON notificaciones
FOR EACH ROW
BEGIN
  :NEW.fecha_alta := SYSDATE;
  :NEW.fecha_modificacion := SYSDATE;
END;
/

-- Disparador para UPDATE Notificaciones

CREATE OR REPLACE TRIGGER fecha_notificaciones_disparador_update
BEFORE UPDATE ON notificaciones
FOR EACH ROW
BEGIN
  :NEW.fecha_modificacion := SYSDATE;
END;









-- Inserts de PRUEBA:

INSERT INTO protectora (
    cif, nombre, telefono, correo_electronico, redes_sociales,
    localidad, provincia, pais, tipo_via, nombre_via, codigo_postal
) VALUES (
    'B12345678', 'Protectora Animal Sur', '600123456', 'contacto@pasur.org',
    '@protectora_sur', 'Sevilla', 'Sevilla', 'Espa�a', 'Calle', 'Libertad', '41001'
);

INSERT INTO cliente (
    nombre, apellido1, apellido2, fecha_nacimiento,
    telefono, correo_electronico, codigo_postal, localidad,
    provincia, pais, tipo_via, nombre_via
) VALUES (
    'Laura', 'Gomez', 'Ruiz', TO_DATE('1992-06-15', 'YYYY-MM-DD'),
    '698112233', 'laura.gomez@gmail.com', '08001', 'Barcelona',
    'Barcelona', 'Espa�a', 'Avenida', 'Diagonal'
);

INSERT INTO raza (nombre) VALUES ('Labrador Retriever');

INSERT INTO patologia (nombre) VALUES ('Displasia de cadera');

INSERT INTO perro (
    nombre, foto, fecha_nacimiento, raza, sexo, adoptado, protectora_id
) VALUES (
    'Max', 'https://fotoinventada.jpg', TO_DATE('2021-01-10', 'YYYY-MM-DD'),
    1, 'Macho', 'No', 1
);

INSERT INTO perro_patologia (
    perro_id, id_patologia, descripcion_pat
) VALUES (
    1, 1, 'El perro presenta leve cojera al caminar.'
);

INSERT INTO solicitud_adopcion (
    donacion, estado, perro_id, cliente_id
) VALUES (
    50.00, 'pendiente', 1, 1
);

INSERT INTO cita (
    donacion, cliente_id
) VALUES (
    10.00, 1
);

INSERT INTO notificaciones (
    nueva_cita, cancelar_cita, nueva_adopcion, cambio_solicitud,
    protectora_id, cliente_id
) VALUES (
    'S�', 'No', 'S�', 'No', 1, 1
);
