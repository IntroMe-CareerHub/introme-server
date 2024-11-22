import React, { MouseEventHandler } from "react";
import { ColorType } from "../../enums";
import "./TextButton.scss"
interface Props {
  text: string
  color: ColorType
  width?: string
  onClick?: MouseEventHandler<HTMLButtonElement>
}

const TextButton = ({ text, color, width, onClick }: Props) => {
  return (
    <>
      <button
        className={["textButton", color].join(" ")}
        onClick={onClick}
        style={{ width }}>
        {text}
      </button>
    </>
  )
}

export default TextButton
