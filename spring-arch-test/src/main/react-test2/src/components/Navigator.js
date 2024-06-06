import {Route, Routes} from "react-router-dom";
import Table from "./Table";
import App from "./App";
import Signup from "./Signup";

const Navigator = () => {
    return (
        <Routes>
            <Route exact path="/" element={<App/>} />
            <Route path="/table" element={<Table/>} />
            <Route path="/signup" element={<Signup/>} />
        </Routes>
    );
}

export default Navigator;