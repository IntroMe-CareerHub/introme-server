import React, {MouseEventHandler, useEffect, useMemo, useState} from "react";
import { ClipLoader } from "react-spinners";
import {ColorType} from "../../enums";
import "./Button.scss"

interface Props {
  text?: string
  color: ColorType
  width?: string
  icon?: string
  classNames?: Array<string>
  borderDashed?: boolean
  onClick?: MouseEventHandler<HTMLButtonElement>
  show?: boolean
  enable?: boolean
  iconWithText?: boolean
  isLoading?: boolean
}

const Button = ({ text, color, width, icon, classNames, borderDashed = false, show = true, onClick, enable = true, iconWithText = false, isLoading = false }: Props) => {
  const backgroundColor = `bg-${color}`
  const initialClasses = useMemo(()=> ["button", backgroundColor],[])

  if (borderDashed) {
    initialClasses.push("dashed")
  }
  const [classes, setClasses] = useState<Array<string>>(initialClasses)

  useEffect(() => {
    setClasses(initialClasses)
  }, [color,initialClasses])

  const handleMouseOver = () => {
    if (enable) {
      setClasses(classes => classes.concat(`${backgroundColor}-hover`))
    }
  }

  const handleMouseOut = () => {
    if (enable) {
      setClasses(initialClasses)
    }
  }

  return (
    <>
      <button
        className={classNames ? classes.concat(...classNames, enable ? " enable" : " disable").join(" ") : classes.join(" ").concat(enable ? " enable" : " disable")}
        onClick={onClick}
        style={{ width, display: show ? 'flex' : 'none' }}
        disabled={!enable}
        onMouseOver={handleMouseOver}
        onMouseOut={handleMouseOut}>
        {
          !isLoading && (() => {
            if (iconWithText) {
              return (
                <>
                  <i className={"material-icons"}>{ icon }</i>
                  <label>{ text }</label>
                </>
              )
            } else {
              return icon ? <i className={"material-icons"}>{ icon }</i> : text
            }
          })()
        }
        {
          isLoading && (
            <div className={"loader"}>
              <ClipLoader loading={true} size={10} color={"black"} />
            </div>
          )
        }
      </button>
    </>
  )
}

export default Button
