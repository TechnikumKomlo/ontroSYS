<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: Content-Type');
header('Access-Control-Allow-Methods: POST');

$host = 'localhost';
$dbname = 'ontrosysweb';
$user = 'root';
$passwd = 'komloivagyok';

try {
    $pdo = new PDO(
        "mysql:host=$host;dbname=$dbname;charset=utf8",
        $user,
        $passwd,
        [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]
    );

    // Accept both JSON and form-data
    $input = json_decode(file_get_contents('php://input'), true);

    $username = $input['username'] ?? ($_POST['username'] ?? '');
    $password = $input['passwd']   ?? ($_POST['passwd']   ?? '');

    if (empty($username) || empty($password)) {
        echo json_encode(['success' => false, 'message' => 'Hiányzó adatok!']);
        exit;
    }

    $stmt = $pdo->prepare("SELECT id, username, email, passwd FROM users WHERE username = ? OR email = ?");
    $stmt->execute([$username, $username]);
    $user = $stmt->fetch(PDO::FETCH_ASSOC);

    if (!$user) {
        echo json_encode(['success' => false, 'message' => 'A felhasználó nem található!']);
        exit;
    }

    if (!password_verify($password, $user['passwd'])) {
        echo json_encode(['success' => false, 'message' => 'Hibás jelszó!']);
        exit;
    }

    echo json_encode([
        'success' => true,
        'message' => 'Sikeres bejelentkezés!',
        'user' => [
            'id' => $user['id'],
            'username' => $user['username'],
            'email' => $user['email']
        ]
    ]);

} catch (PDOException $e) {
    echo json_encode(['success' => false, 'message' => 'Szerver hiba!']);
}
?>
