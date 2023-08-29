ALTER TABLE medicos ADD active TINYINT;
UPDATE medicos SET active = 1;