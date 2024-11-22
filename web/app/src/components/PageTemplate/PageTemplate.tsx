import React from "react"
import SideBar from "../Sidebar";
import "./PageTemplate.scss"

interface Props {
    disableSideBar?: boolean
    children?: React.JSX.Element | Array<React.JSX.Element>
}

const PageTemplate = ({disableSideBar = false, children}: Props) => {
    return (
        <>
            {
                disableSideBar ? <></> : <SideBar/>
            }
            <section className={"pageTemplate jumbotron"}>
                {children}
            </section>
        </>
    )
}

export default PageTemplate
