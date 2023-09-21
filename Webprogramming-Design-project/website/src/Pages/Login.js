import Layout from "../Layout";
import React, { useState } from "react";
import InputField from "../InputField/InputField";
import { Link } from "react-router-dom";

function Login() {
    const [mail, setMail] = useState("");
    const [password, setPassword] = useState("");

    async function handleLogin (event) {
        event.preventDefault(); //prevent reload of site
    }

    return (
        <Layout>
            <div class="column center">
                <h1>Login</h1>
                <form onLoin={handleLogin} class="column center">
                    <InputField name="Mail" value={mail} setValue={setMail}/>
                    <InputField name="Password" value={password} setValue={setPassword}/>
                    <Link to="/Login/login">
                    <button class="button-primary fit-parent-width margin-vertical-m">Login</button>
                    </Link>
                    <Link to="/Login/signup">
                    <button class="button-noshow margin-vertical-m" >Don't have an account yet? Sign up</button>
                    </Link>
                </form>
            </div>
        </Layout>
    
    )
}
export default Login;
