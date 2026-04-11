<?php
header('Content-Type: application/json');

// Backend API URL
$apiUrl = "http://akos0328.webredirect.org/OntroSYS/API/profile.php";

// Detect request method
$method = $_SERVER['REQUEST_METHOD'];

if ($method === 'GET') {

    // Forward GET request to backend
    $ch = curl_init($apiUrl);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($ch);
    curl_close($ch);

    echo $response;
    exit;
}

if ($method === 'POST') {

    // Read JSON or form-data
    $input = file_get_contents('php://input');
    $data = json_decode($input, true);

    if (!$data) {
        // Fallback for form-data
        $data = $_POST;
    }

    // Forward POST request to backend
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
    exit;
}

// Invalid request
echo json_encode(['success' => false, 'message' => 'Érvénytelen kérés!']);
?>
