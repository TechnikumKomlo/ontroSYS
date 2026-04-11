<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: Content-Type');
header('Access-Control-Allow-Methods: POST, GET');

// Database connection
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
} catch (PDOException $e) {
    echo json_encode(['success' => false, 'message' => 'Adatbázis hiba!']);
    exit;
}

$data = json_decode(file_get_contents('php://input'), true);

// GET PROFILE
if (isset($data['action']) && $data['action'] === 'get_profile') {

    if (!isset($data['id'])) {
        echo json_encode(['success' => false, 'message' => 'Hiányzó felhasználó ID!']);
        exit;
    }

    $user_id = $data['id'];

    $stmt = $pdo->prepare("SELECT id, username, email, bio, avatar, created_at, updated_at FROM users WHERE id = ?");
    $stmt->execute([$user_id]);
    $user = $stmt->fetch(PDO::FETCH_ASSOC);

    if (!$user) {
        echo json_encode(['success' => false, 'message' => 'A felhasználó nem található!']);
        exit;
    }

    echo json_encode([
        'success' => true,
        'user' => $user
    ]);
    exit;
}

// UPDATE PROFILE
if (isset($data['action']) && $data['action'] === 'update_profile') {

    if (!isset($data['id'])) {
        echo json_encode(['success' => false, 'message' => 'Hiányzó felhasználó ID!']);
        exit;
    }

    $user_id = $data['id'];
    $username = $data['username'] ?? '';
    $email    = $data['email'] ?? '';
    $bio      = $data['bio'] ?? '';
    $avatar   = $data['avatar'] ?? '';

    if (empty($username) || empty($email)) {
        echo json_encode(['success' => false, 'message' => 'A felhasználónév és email megadása kötelező!']);
        exit;
    }

    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo json_encode(['success' => false, 'message' => 'Érvénytelen email formátum!']);
        exit;
    }

    // Check duplicates
    $check = $pdo->prepare("SELECT id FROM users WHERE (username = ? OR email = ?) AND id != ?");
    $check->execute([$username, $email, $user_id]);

    if ($check->rowCount() > 0) {
        echo json_encode(['success' => false, 'message' => 'A felhasználónév vagy email már foglalt!']);
        exit;
    }

    // Update
    $update = $pdo->prepare("UPDATE users SET username = ?, email = ?, bio = ?, avatar = ?, updated_at = NOW() WHERE id = ?");
    $success = $update->execute([$username, $email, $bio, $avatar, $user_id]);

    if ($success) {
        echo json_encode([
            'success' => true,
            'message' => 'Profil sikeresen frissítve!',
            'user' => [
                'id' => $user_id,
                'username' => $username,
                'email' => $email,
                'bio' => $bio,
                'avatar' => $avatar,
                'updated_at' => date('Y-m-d H:i:s')
            ]
        ]);
    } else {
        echo json_encode(['success' => false, 'message' => 'Hiba történt a frissítés során!']);
    }

    exit;
}

echo json_encode(['success' => false, 'message' => 'Érvénytelen kérés!']);
?>
