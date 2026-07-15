-- Script de creación de la base de datos para ProyectoFormas
-- Ejecutar en MySQL Workbench, DBeaver, o en la línea de comandos de MySQL.

CREATE DATABASE IF NOT EXISTS proyecto_formas
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE proyecto_formas;

-- Tabla de figuras planas. Un solo tipo de tabla con discriminador "tipo";
-- las columnas que no aplican al tipo específico quedan en NULL.
CREATE TABLE IF NOT EXISTS figuras (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    tipo            VARCHAR(30) NOT NULL,
    lado1           DOUBLE NULL,
    lado2           DOUBLE NULL,
    lado3           DOUBLE NULL,
    radio           DOUBLE NULL,
    base            DOUBLE NULL,
    altura          DOUBLE NULL,
    diagonal_mayor  DOUBLE NULL,
    diagonal_menor  DOUBLE NULL
);

-- Tabla de cuerpos sólidos. figura_id referencia la cara/base asociada
-- (columna NULL únicamente para ESFERA, que no tiene figura asociada).
CREATE TABLE IF NOT EXISTS cuerpos (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    tipo        VARCHAR(30) NOT NULL,
    figura_id   INT NULL,
    altura      DOUBLE NULL,
    radio       DOUBLE NULL,
    CONSTRAINT fk_cuerpo_figura
        FOREIGN KEY (figura_id) REFERENCES figuras(id)
        ON DELETE CASCADE
);
