export function createRandomID(type){
    let id = ""
    for (let i = 0; i < 7; i++){
        id = id+Math.floor(Math.random()*10)
    }
    return type+id
}