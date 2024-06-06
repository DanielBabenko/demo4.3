import {
    FormControl,
    Button
} from "@chakra-ui/react";
import React, { useState } from "react";
import { ViewIcon, ViewOffIcon } from "@chakra-ui/icons";
import {Link} from "react-router-dom";

function Signup() {
    const [showPassword, setShowPassword] = useState(false);

    return (
        <div id="register">
            <h3>Регистрация</h3>
                    {/*<FormControl id="firstName" isRequired>*/}
                        <label>Логин</label>
                        <input type="text"/>
                    {/*</FormControl>*/}

    {/*<FormControl id="password" isRequired>*/}
        <label>Пароль</label>

        <input type={showPassword ? "text" : "password"}/>
                                <FormControl h={"full"}>
                                    <Button
                                        variant={"ghost"}
                                        onClick={() =>
                                            setShowPassword((showPassword) => !showPassword)
                                        }
                                    >
                                        {showPassword ? <ViewIcon/> : <ViewOffIcon/>}
                                    </Button>
                                </FormControl>
                            <button>
                                Подтвердить регистрацию!
                            </button>
        </div>
    );
}

export default Signup;