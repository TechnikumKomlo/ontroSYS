<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *'); 
header('Access-Control-Allow-Headers: Content-Type');
header('Access-Control-Allow-Methods: POST');

// --- MySQL connection (local only, safe) ---
$host = 'localhost';
$dbname = 'ontrosysweb';
$user = 'root';
$passwd = 'komloivagyok'; // XAMPP default

try {
    $pdo = new PDO(
        "mysql:host=$host;dbname=$dbname;charset=utf8",
        $user,
        $passwd,
        [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]
    );

    // Read JSON from request body
    $data = json_decode(file_get_contents('php://input'), true);

    if (!$data || empty($data['username']) || empty($data['email']) || empty($data['passwd'])) {
        echo json_encode(['success' => false, 'message' => 'Minden mező kitöltése kötelező!']);
        exit;
    }

    // Hash password
    $hashed_pass = password_hash($data['passwd'], PASSWORD_DEFAULT);

    // Insert user
    $stmt = $pdo->prepare(
        "INSERT INTO users (username, email, passwd, admin) VALUES (?, ?, ?, 0)"
    );

    $stmt->execute([
        $data['username'],
        $data['email'],
        $hashed_pass
    ]);

    echo json_encode(['success' => true]);
    exit;

} catch (PDOException $e) {
    $msg = ($e->getCode() == 23000)
        ? 'A felhasználónév vagy email már foglalt!'
        : 'Szerver hiba történt.';

    echo json_encode(['success' => false, 'message' => $msg]);
    exit;
}
?>
