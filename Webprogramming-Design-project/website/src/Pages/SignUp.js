import React, { useState } from "react";
import InputField from "../InputField/InputField";
import Layout from "../Layout";
import { Link } from "react-router-dom";

function SignUp() {
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [mail, setMail] = useState("");
    const [password, setPassword] = useState("");
    const [study, setStudy] = useState("");

    async function handleSignUp(event) {
        event.preventDefault(); //prevent reload of site

        let user = {
            name: name,
            surname: surname,
            mail: mail,
            password: password,
            study: study,
        };
    }

    return (
        <Layout>

        <div class="column center">
            <h1>Sign Up</h1>
            <form onLoin={handleSignUp}>
                <InputField name="Name" value={name} setValue={setName}/>
                <InputField name="Surname" value={surname} setValue={setSurname}/>
                <InputField name="Mail" value={mail} setValue={setMail}/>
                <InputField name="Password" value={password} setValue={setPassword}/>
                <InputField name="Line of Study" value={study} setValue={setStudy}/>
                <Link to="/SignUp/create">
                    <button class="button-primary" >Create user</button>
                    </Link>
            </form>
        </div>
    </Layout>
    
    )
}
export default SignUp;