// JavaScript Document

document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("form2");
    const resultDiv = document.getElementById("result");
    const canvas = document.getElementById("plot");
    const ctx = canvas.getContext("2d");
	// const xCheckboxes = document.querySelectorAll("input[name='x[]']");

	function axesToCanvasCoordinates(xAxes, yAxes, canvas) {

    let originX = canvas.width / 2;
    let originY = canvas.height / 2;

    let canvasX = originX + xAxes;
    let canvasY = originY - yAxes;

    return { x: canvasX, y: canvasY };
}

	function scaleXAxesCoordinate(coordinate) {
    return coordinate * xAxisScale;
}

	function scaleYAxesCoordinate(coordinate) {
    return coordinate * yAxisScale;
}

	function prepare(){
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		ctx.fillStyle = "rgb(220,220,220,0.85)";
    	ctx.fillRect(0, 0, canvas.width, canvas.height);
		ctx.strokeStyle = "rgb(220,20,60)";
		ctx.lineWidth = 5;
		ctx.strokeRect(0,0,canvas.width,canvas.height);
	}

    function drawFigure(r) {
        prepare();

		let originX = canvas.width/2;
		let originY = canvas.height/2;

        // Рисуем фигуру
		//Прямоугольник
        ctx.beginPath();
        ctx.moveTo(originX, originY);
		ctx.lineTo(originX - r * canvas.width / 24, originY);
		ctx.lineTo(originX - r * canvas.width / 24, originY + r * canvas.height / 12);
        ctx.lineTo(originX, originY + r * canvas.height / 12);

		// //Сектор круга
		ctx.moveTo(originX, originY);
		// ctx.lineTo(originY - r * canvas.width / 12, originY);
        ctx.arc(originX, originY, r * canvas.width / 12, Math.PI/2, 0, true);

		//Треугольник
        ctx.moveTo(originX, originY);
        ctx.lineTo(originX, originY - r * canvas.height / 12);
        ctx.lineTo(originX - r * canvas.width / 12, originY);
        ctx.closePath();
        ctx.fillStyle = "rgb(65,105,225,0.75)";
        ctx.fill();

        // Рисуем оси X и Y
        ctx.beginPath();
		ctx.lineWidth = 2;
        ctx.moveTo(0, canvas.height / 2);
        ctx.lineTo(canvas.width, canvas.height / 2);
        ctx.moveTo(canvas.width / 2, 0);
        ctx.lineTo(canvas.width / 2, canvas.height);
        ctx.strokeStyle = "white";
        ctx.stroke();

		// Отметки на осях
		for (let i = -originX; i < originX; i += canvas.width / 24) {
		let scalePos = axesToCanvasCoordinates(i, 0, canvas);
		ctx.beginPath();
		ctx.moveTo(scalePos.x, originY + 10);
		ctx.lineTo(scalePos.x,originY - 10);
		ctx.strokeStyle = "white";
		ctx.stroke();
		}

		for (let j = -originY; j < originY; j += canvas.width / 24) {
		let scalePos = axesToCanvasCoordinates(0, j, canvas);
		ctx.beginPath();
		ctx.moveTo(originX + 10, scalePos.y);
		ctx.lineTo(originX - 10, scalePos.y);
		ctx.strokeStyle = "white"
		ctx.stroke();
		}

		ctx.strokeStyle = "rgb(220,20,60)";
		ctx.lineWidth = 5;
		ctx.strokeRect(0,0,canvas.width,canvas.height);
  }

	// xCheckboxes.forEach(checkbox => {
    //     checkbox.addEventListener("change", function () {
    //         if (this.checked) {
    //             // Если чекбокс выбран, снимаем выбор с других
    //             xCheckboxes.forEach(otherCheckbox => {
    //                 if (otherCheckbox !== this) {
    //                     otherCheckbox.checked = false;
    //                 }
    //             });
    //         }
    //     });
    // });

    function drawPoint(x,y) {
        let originX = canvas.width/2;
        let originY = canvas.height/2;

        ctx.beginPath();
        ctx.arc(originX + x * canvas.height / 12, originY - y * canvas.height / 12, 1, 0, 2 * Math.PI, true);
        ctx.strokeStyle = "rgb(199, 21, 133,0.75)"
        ctx.stroke();
    }


  form.addEventListener("submit", function (event) {
        event.preventDefault();
        // const selectedCheckbox = document.querySelector("input[name='x[]']:checked");
        // const x = selectedCheckbox ? parseFloat(selectedCheckbox.value) : null;
        const x = parseFloat(document.getElementById("x").value);
        const y = parseFloat(document.getElementById("y").value);
        const selectedRadioButton = document.querySelector("input[name='r']:checked");
        const r = selectedRadioButton ? parseFloat(selectedRadioButton.value) : null;
        // const r = parseFloat(Number(document.getElementById("r").value));

        if (x <= -3|| x >= 3) {
            resultDiv.textContent = "Пожалуйста, введите корректное значение для X (максимум, 4 знака после запятой).";
            return;
        }

        if (y < -3|| y > 5) {
            resultDiv.textContent = "Пожалуйста, введите корректное значение для Y (максимум, 4 знака после запятой).";
            return;
        }

	  if (r < 1|| r > 5) {
            resultDiv.textContent = "Пожалуйста, введите корректное значение для R (максимум, 4 знака после запятой).";
            return;
        }

        // Отправка данных на сервер
//        fetch("process2.php", {
//            method: "POST",
//            body: new URLSearchParams({ x, y, r }),
//            headers: {
//                "Content-Type": "application/x-www-form-urlencoded",
//            },
//        })
//        .then(response => response.json())
//        .then(data => {
//            if (data.error) {
//                resultDiv.textContent = data.error;
//            } else {
//                updateResultsTable(data);
//            }
//        })
//        .catch(error => {
//            console.error("Ошибка при отправке данных на сервер: " + error);
//        });

        // Обновляем график
        drawFigure(r);
	    drawPoint(x,y);

	     const urlParams =
                new URLSearchParams({x, y, r});
            window.location.href = "/web2-1.0-SNAPSHOT/controller?" + urlParams.toString();
    });

    function updateResultsTable(results) {
        const table = document.createElement("table");
        table.innerHTML = `
            <tr><th>X</th><th>Y</th><th>R</th><th>Результат</th><th>Время выполнения</th><th>Дата</th></tr>
        `;

        results.forEach(entry => {
            const row = table.insertRow(1);
            row.innerHTML = `
                <td>${entry.x}</td>
                <td>${entry.y}</td>
                <td>${entry.r}</td>
                <td>${entry.result ? "Попал" : "Не попал"}</td>
				<td>${entry.time}</td>
                <td>${entry.timestamp}</td>
            `;
        });

        resultDiv.innerHTML = "";
        resultDiv.insertBefore(table, resultDiv.firstChild);
    }

    // Инициализация графика с начальным значением R
    drawFigure(parseFloat(document.getElementById("r").value));
});

