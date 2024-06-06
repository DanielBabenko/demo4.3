import yellow from '../images/Yellow.jpeg';
import Clock from "./Clock";
import {Link} from "react-router-dom";
import Signup from "./Signup";

function App() {
  return (
      <div className="App">
          <title>Главная</title>
          <div className="container">
              <div className="resulting" id="results">
                  <h3>Текущее время</h3>
                  <Clock/>
              </div>
              <div className="resulting">
                  <p>
                      <Link to="/table">Основная страница</Link>
                  </p>
              </div>
              <img src={yellow} id="amogus" className="aboba" alt="amogus"/>
          </div>
          <div className="resulting">
              <p>
                  <a href="https://youtu.be/dQw4w9WgXcQ?si=mZp0SWlBVPOzpv_1">Рикролл</a>
              </p>
              <p>
                  <Signup/>
              </p>
          </div>
      </div>
  );
}

export default App;