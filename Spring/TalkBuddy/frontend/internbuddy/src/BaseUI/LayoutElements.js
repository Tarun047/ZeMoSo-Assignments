import React,{Component} from 'react';

const custom

const Table = ({list,onDismiss,onDelete,callBack,tag})=>
      <div className="table">
        {list.map(item=>
          React
        )}
      </div>



const Button = ({onClick,className="",children})=>
      <button
        onClick={onClick}
        className={className}
        type="button"
      >
        {children}
      </button>

export default Table,Button;