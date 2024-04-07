import React from "react"
import Timer from "../Timer"
import {Link} from "react-router-dom"
import "./SideBar.scss"

const SideBar = () => {
    return (
        <nav className={"sideBar"}>
            <header className={"header"}>
            </header>
            <section className={"section"}>
                <ul className={"companyList"}>
                    <Link to={"/company"}>
                        <li className={"companyItem"}>
                            <span className={"company"}>기업 정보 관리</span>
                        </li>
                    </Link>
                </ul>
            </section>
            <footer className={"footer"}>
                <div className={"userInfo"}>
                    <span className={"userName"}>관리자<span className={"userNameNim"}>님</span></span>
                    <i className={"material-icons userMoreIcon"}>keyboard_arrow_right</i>
                </div>
                <Timer classNames={["activeTime"]}/>
            </footer>
        </nav>
    )
}

export default SideBar
