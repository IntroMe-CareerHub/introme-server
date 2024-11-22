import React, {useState} from "react";
import {useNavigate} from "react-router-dom"
import {ColorType} from "../../enums";
import "./SignIn.scss"
import {PageTemplate, Container, TextButton, TextBox, Button} from "../../components";

const SignIn = () => {
    const [email, setEmail] = useState<String>('')
    const [password, setPassword] = useState<String>('')
    const navigate = useNavigate()

    const handleClickSignIn = () => {
        signIn()
    }

    const signIn = async () => {
        if (email === "admin" && password === "123") {
            navigate('/home')
        } else alert("로그인에 실패하였습니다")
    }

    const handleKeyUpEnterInput = () => {
        signIn()
    }

    return (
        <PageTemplate disableSideBar={true}>
            <Container width={"40%"} height={"550px"} classNames={["signIn"]}>
                <TextBox label={"관리자 계정"} onChange={e => setEmail(e.target.value)}/>
                <TextBox label={"비밀번호"} onChange={e => setPassword(e.target.value)} onKeyUpEnter={handleKeyUpEnterInput}
                         isPassword={true}/>
                <div className={"buttonRow"}>
                    <Button text={"로그인"} width={"60%"} color={ColorType.PRIMARY} onClick={handleClickSignIn}/>
                </div>
                <div className={"buttonRow"}>
                    <TextButton text={"비밀번호 초기화"} width={"60%"} color={ColorType.PRIMARY} onClick={() => {
                    }}/>
                </div>
            </Container>
        </PageTemplate>
    )
}

export default SignIn
