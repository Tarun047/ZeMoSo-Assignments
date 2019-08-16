import React,{Component} from 'react';

const Table = ({list,onDelete,callBack,tag})=>
      <div className="table">
        {list.map(item=>
          React.createElement(tag,
          {
           id:item.id,
           name:item.name,
           rating:item.rating,
           assignments:item.assignments,
           onDelete:onDelete,
           callBack:callBack
          },null
         )
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

export{
    Table,
    Button
}