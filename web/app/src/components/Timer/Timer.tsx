import React, { useEffect, useState } from "react"
import "./Timer.scss"
interface Props {
  classNames?: Array<String>;
}

const Timer = ({ classNames }: Props) => {
  const [ time, setTime ] = useState(0)

  useEffect(() => {
    const interval = setInterval(() => {
      setTime(time => time + 1)
    }, 1000)
    return () => clearInterval(interval)
  }, [ time ])

  return (
    <div className={classNames ? classNames.concat("timer").join(' ') : "timer"}>
      <i className={"material-icons timeIcon"}>access_time</i>
      <span className={"time"}>
        {
          (() => {
            let str = '';
            const hours = (time / 60 / 60).toFixed();
            if (hours !== '0') {
              str += `${hours}:`
            }
            const minutes = (time / 60).toFixed().padStart(2, '0')
            const seconds = (time % 60).toFixed().padStart(2, '0')
            str += `${minutes}:${seconds}`
            return str
          })()
        }
      </span>
    </div>
  )
}

export default Timer
