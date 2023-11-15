DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS project;

CREATE TABLE project (
    project_id INT AUTO_INCREMENT NOT NULL,
    project_name VARCHAR(128) NOT NULL,
    estimated_hours DECIMAL(7,2),
    actual_hours DECIMAL(7,2),
    difficulty INT,
    notes TEXT,
    PRIMARY KEY (project_id)
);

CREATE TABLE material (
    material_id INT AUTO_INCREMENT NOT NULL,
    project_id INT NOT NULL,
    material_name VARCHAR(128) NOT NULL,
    num_required INT,
    cost DECIMAL(7,2),
    PRIMARY KEY (material_id),
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE TABLE step (
    step_id INT AUTO_INCREMENT NOT NULL,
    project_id INT NOT NULL,
    step_text TEXT NOT NULL,
    step_order INT NOT NULL,
    PRIMARY KEY (step_id),
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE TABLE category (
    category_id INT AUTO_INCREMENT NOT NULL,
    category_name VARCHAR(128) NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE project_category (
    project_id INT NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE,
    UNIQUE KEY (project_id, category_id)
);

INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) VALUES (1, 'Hang a Door', 4, 3, 3, 'Use the door hangers from Home Depot');
INSERT INTO material (project_id, material_name, num_required, cost) VALUES (1, '2-inch screws', 20, 4.5);
INSERT INTO material (project_id, material_name, num_required, cost) VALUES (1, 'pack of door hangers', 1, 45);
INSERT INTO step (project_id, step_text, step_order) VALUES (1, 'Screw door hangers on the top and bottom of each side of the door frame', 1);
INSERT INTO step (project_id, step_text, step_order) VALUES (1, 'Screw door frame into the hangers', 2);
INSERT INTO category (category_id, category_name) VALUES (1, 'Doors and Windows');
INSERT INTO category (category_id, category_name) VALUES (2, 'Repairs');
INSERT INTO project_category (projedct_id, category_id) VALUES (1, 1);
INSERT INTO project_category (projedct_id, category_id) VALUES (1, 2);