import React from "react";
import {Link} from "react-router-dom";

const Table = () => {
    return (
        <div>
            <title>Проверка точки</title>
            <div className="container">
                <div id="div_form">
                    <form id="pointForm" method="POST">
                        <div className="selects">
                            <label htmlFor="x">X:</label>
                            <input type="text" id="x" name="x" placeholder="Введите X от -5 до 5" step="any" required/>
                        </div>

                        <div className="selects">
                            <label htmlFor="y">Y:</label>
                            <input type="text" id="y" name="y" placeholder="Введите X от -3 до 3" step="any" required/>
                        </div>

                        <div className="selects">
                            <label htmlFor="r">R:</label>
                            <input type="text" id="r" name="r" placeholder="Введите X от -5 до 5" step="any" required/>
                        </div>

                        <div className="selects">
                            <br/>
                            <button type="submit">Проверить</button>
                            <br/>
                            <button type="submit">Очистить попытки</button>
                            <br/>
                        </div>
                    </form>
                </div>

                <div id="div_canvas">
                    <canvas id="canvas" width="500px" height="500px">

                    </canvas>
                </div>

                <div className="resulting">
                    <p>
                        <Link to="/">На главную</Link>
                    </p>
                </div>

                <div id="div_table" class="resulting">
                </div>

                <script src="./js/draw.js" type="text/javascript"></script>
                <script src="./js/validate.js" type="text/javascript"></script>
                <script src="./js/handleChangeCommandLink.js" type="text/javascript"></script>
                <script src="./js/handleButtons.js" type="text/javascript"></script>
            </div>
        </div>
    );
};

export default Table;