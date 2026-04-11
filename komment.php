<?php
header('Content-Type: application/json');

$apiUrl = "http://akos0328.webredirect.org/OntroSYS/API/comments.php";

$method = $_SERVER['REQUEST_METHOD'];

if ($method === 'POST') {
    $data = $_POST;

    $ch = curl_init($apiUrl);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($ch);
    curl_close($ch);

    echo $response;
    exit;
}

echo json_encode(['success' => false, 'message' => 'Érvénytelen kérés!']);
?>
