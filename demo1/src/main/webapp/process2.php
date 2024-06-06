<?php
error_reporting(-1);
header('Content-Type: text/html; charset=utf-8');
session_start();

if ($_SERVER["REQUEST_METHOD"] === "POST") {


    $x = $_POST["x"];
    $y = $_POST["y"];
    $r = $_POST["r"];
    $flag = 0;
	
	$start_exec = microtime(1);

    preg_match('/\-?\d+\.?\d{0,}/', $x, $matches);
        if (count($matches) == 1 && $matches[0] == $x) {
            $x = floatval($x);
        } else {
            echo json_encode(array("error" => "X должен быть float."));
        }

    preg_match('/\-?\d+\.?\d{0,}/', $y, $matches);
    if (count($matches) == 1 && $matches[0] == $y) {
        $y = floatval($y);
    } else {
        echo json_encode(array("error" => "Y должен быть float."));
    }

    preg_match('/\-?\d+\.?\d{0,}/', $r, $matches);
    if (count($matches) == 1 && $matches[0] == $r) {
        $r = floatval($r);
    } else {
        echo json_encode(array("error" => "R должен быть float."));
    }

    $x = round($x,4,PHP_ROUND_HALF_UP);
    $y = round($y,4,PHP_ROUND_HALF_UP);
    $r = round($r,4,PHP_ROUND_HALF_UP);
    // Валидация данных
    if (is_numeric($x) && is_numeric($y) && is_numeric($r) && $x>-3 && $x<3 && $y>=-3 && $y<=5 && $r >= 1 && $r <= 5) {
        // Выполнение проверки точки
        $result = ($x >= -$r/2 && $x <= 0 && $y <= 0 && $y >= -$r) ||
                  ($x >= 0 && $y <= 0 && $x**2 + $y**2 <= ($r**2)) ||
                  ($x <= 0 && $y >= 0 && $y - $x <= $r);

        // Сохранение результатов в сессии
        if (!isset($_SESSION["results"])) {
            $_SESSION["results"] = array();
        }
		$execution_time = (microtime(1) - $start_exec) * 1000;
        array_push($_SESSION["results"], array("x" => $x, "y" => $y, "r" => $r, "result" => $result, "time" => round($execution_time,5,PHP_ROUND_HALF_UP),"timestamp" => date("Y-m-d H:i:s")));

        // Возвращение результатов в формате JSON
        header('Content-Type: application/json');
        echo json_encode($_SESSION["results"]);
    } else {
        echo json_encode(array("error" => "Пожалуйста, введите корректные числовые значения (максимум, 4 знака после запятой)."));
    }
} else {
    echo json_encode(array("error" => "Неправильный запрос."));
}
?>
