import React, {ChangeEventHandler, KeyboardEvent, useEffect, useMemo, useRef, useState} from "react";
import "./TextBox.scss"
interface Props {
  label?: string
  width?: string
  value?: any
  enable?: boolean
  description?: string
  onChange?: ChangeEventHandler<HTMLInputElement>
  onKeyUpEnter?: Function
  isPassword?: boolean | false
}

const TextBox = ({ label, width, onChange, description, onKeyUpEnter, value = '', enable = true, isPassword }: Props) => {
  const domId = Math.random().toString()
  const initialClasses = useMemo( () => ["inputText"] ,[] )

  const [ classes, setClasses ] = useState<Array<string>>(initialClasses)

  useEffect(() => {
    setClasses(() => initialClasses.concat(enable ? "enable" : "disable"))
  }, [ enable, initialClasses ])

  const handleInputFocus = () => {
    setClasses(classes => classes.concat("focus"))
  }

  const handleInputBlur = () => {
    setClasses(initialClasses)
  }

  const handleKeyUp = (e: KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter' && onKeyUpEnter) {
      onKeyUpEnter()
    }
  }

  const input = useRef<HTMLInputElement>(null)

  useEffect(() => {
    // @ts-ignore
    input.current.value = value
  }, [ value ])

  return (
    <>
      <div className={"inputWrapper"} style={{ width: width || '100%' }}>
        {
          label ? <label className={"inputLabel"} htmlFor={domId}>{label}</label> : <></>
        }
        <div className={classes.join(" ")}>
          <input
            ref={input}
            type={isPassword ? "password" : "text"}
            onChange={onChange}
            onKeyUp={handleKeyUp}
            id={domId}
            // value={value}
            disabled={!enable}
            onFocus={handleInputFocus}
            onBlur={handleInputBlur}
          />
        </div>
        {
          description && (
            <div className={"description"}>
              <span className={"square"}>✔ </span>
              { description }
            </div>
          )
        }
      </div>
    </>
  )
}

export default TextBox
