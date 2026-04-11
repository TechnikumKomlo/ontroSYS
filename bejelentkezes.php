<?php
header('Content-Type: application/json');

$username = $_POST['username'] ?? '';
$passwd   = $_POST['passwd'] ?? '';

if (empty($username) || empty($passwd)) {
    echo json_encode([
        'success' => false,
        'message' => 'A felhasználónév és jelszó megadása kötelező!'
    ]);
    exit;
}

$data = [
    'username' => $username,
    'passwd'   => $passwd
];

$apiUrl = "http://akos0328.webredirect.org/OntroSYS/API/login.php";

$ch = curl_init($apiUrl);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
curl_setopt($ch, CURLOPT_HTTPHEADER, [
    'Content-Type: application/json'
]);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

$response = curl_exec($ch);
curl_close($ch);

echo $response;
?>
