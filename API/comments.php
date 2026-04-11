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
} catch (PDOException $e) {
    echo json_encode(['success' => false, 'message' => 'Adatbázis hiba!']);
    exit;
}

$data = $_POST;

// ADD COMMENT
if ($data['action'] === 'add_comment') {

    $changelog_id = $data['post_id'] ?? '';
    $content = $data['comment_text'] ?? '';
    $user_id = $data['user_id'] ?? null;
    $username = $data['username'] ?? null;

    if (!$changelog_id || !$content) {
        echo json_encode(['success' => false, 'message' => 'A komment szövege nem lehet üres!']);
        exit;
    }

    if (!$user_id || !$username) {
        echo json_encode(['success' => false, 'message' => 'Be kell jelentkezned a kommenteléshez!']);
        exit;
    }

    $stmt = $pdo->prepare("
        INSERT INTO comments (changelog_id, user_id, username, content, created_at)
        VALUES (?, ?, ?, ?, NOW())
    ");
    $stmt->execute([$changelog_id, $user_id, $username, $content]);

    echo json_encode(['success' => true, 'message' => 'Komment hozzáadva!']);
    exit;
}

// GET COMMENTS
if ($data['action'] === 'get_comments') {

    $changelog_id = $data['post_id'] ?? '';

    $stmt = $pdo->prepare("
        SELECT id, user_id, username, content, created_at
        FROM comments
        WHERE changelog_id = ?
        ORDER BY created_at DESC
    ");
    $stmt->execute([$changelog_id]);
    $rows = $stmt->fetchAll(PDO::FETCH_ASSOC);

    $comments = array_map(function($row) {
        return [
            'id' => $row['id'],
            'user_id' => $row['user_id'],
            'author' => $row['username'],
            'text' => $row['content'],
            'timestamp' => $row['created_at']
        ];
    }, $rows);

    echo json_encode(['success' => true, 'comments' => $comments]);
    exit;
}

// DELETE COMMENT
if ($data['action'] === 'delete_comment') {

    $comment_id = $data['comment_id'] ?? null;
    $user_id = $data['user_id'] ?? null;

    if (!$comment_id || !$user_id) {
        echo json_encode(['success' => false, 'message' => 'Hiányzó adatok!']);
        exit;
    }

    // Check ownership
    $stmt = $pdo->prepare("SELECT user_id FROM comments WHERE id = ?");
    $stmt->execute([$comment_id]);
    $comment = $stmt->fetch(PDO::FETCH_ASSOC);

    if (!$comment) {
        echo json_encode(['success' => false, 'message' => 'A komment nem található!']);
        exit;
    }

    if ($comment['user_id'] != $user_id) {
        echo json_encode(['success' => false, 'message' => 'Csak a saját kommentedet törölheted!']);
        exit;
    }

    // Delete
    $stmt = $pdo->prepare("DELETE FROM comments WHERE id = ?");
    $stmt->execute([$comment_id]);

    echo json_encode(['success' => true, 'message' => 'Komment törölve!']);
    exit;
}

echo json_encode(['success' => false, 'message' => 'Érvénytelen kérés!']);
?>
