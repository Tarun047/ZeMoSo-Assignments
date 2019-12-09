function makeid(length) {
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
       result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
 }

export default function mock(schema)
{
    console.log(schema)
    if(schema === 'string')
        return makeid(10);
    else if(schema === 'number')
        return Math.floor(Math.random() * 100)
    else if(schema === 'date')
        return new Date(+(new Date()) - Math.floor(Math.random()*10000000000))
    else if('fixed' in schema)
    {
        return schema.fixed[(Math.floor(Math.random() * schema.fixed.length) + 1)-1]
    }
    else if(schema instanceof Array)
        {
            let arr = Array(mock('number'));
            const fixedSchema = schema[0]
            for(let x=0;x<arr.length;x++)
                arr[x]=mock(fixedSchema)
            return arr
        }
    else 
        Object.keys(schema).forEach(key=>schema[key]=mock(schema[key]))
    return schema
}