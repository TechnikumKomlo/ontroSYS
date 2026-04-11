<?php
header('Content-Type: application/json');

// Read JSON from JS
$data = json_decode(file_get_contents('php://input'), true);

if (!$data || empty($data['username']) || empty($data['email']) || empty($data['passwd'])) {
    echo json_encode(['success' => false, 'message' => 'Minden mező kitöltése kötelező!']);
    exit;
}

// URL of the backend bridge on your XAMPP server
$apiUrl = "http://akos0328.webredirect.org/OntroSYS/API/register.php";

// Forward the JSON to the backend
$ch = curl_init($apiUrl);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
curl_setopt($ch, CURLOPT_HTTPHEADER, [
    'Content-Type: application/json'
]);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

// Get response from backend
$response = curl_exec($ch);
curl_close($ch);

// Return backend response to frontend JS
echo $response;
?>
