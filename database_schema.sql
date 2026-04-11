-- OntroSYS adatbázis séma
-- Létrehozás: phpMyAdmin-ban futtassuk ezt a SQL kódot

CREATE DATABASE IF NOT EXISTS `ontrosysweb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci;

USE `ontrosysweb`;

-- Felhasználók tábla
CREATE TABLE IF NOT EXISTS `users` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Kommentek tábla
CREATE TABLE IF NOT EXISTS `comments` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `post_id` VARCHAR(50) NOT NULL,
    `user_id` INT NOT NULL,
    `username` VARCHAR(50) NOT NULL,
    `comment_text` TEXT NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);

-- Indexek a jobb teljesítményért
CREATE INDEX IF NOT EXISTS `idx_users_username` ON `users`(`username`);
CREATE INDEX IF NOT EXISTS `idx_users_email` ON `users`(`email`);
CREATE INDEX IF NOT EXISTS `idx_comments_post_id` ON `comments`(`post_id`);
CREATE INDEX IF NOT EXISTS `idx_comments_user_id` ON `comments`(`user_id`);
CREATE INDEX IF NOT EXISTS `idx_comments_created_at` ON `comments`(`created_at`);

-- Minta adatok (opcionális)
INSERT INTO `users` (`username`, `email`, `password`) VALUES 
('admin', 'admin@ontrosys.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('teszt', 'teszt@ontrosys.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi');

-- Minta kommentek (opcionális)
INSERT INTO `comments` (`post_id`, `user_id`, `comment_text`) VALUES 
('v2.0.0', 1, 'Ez egy teszt komment az új verzióhoz!'),
('v1.1.1', 2, 'Nagyon jó a hibajavítás!');
