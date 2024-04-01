module Types

    open StateMonad
    
    type coord  = int * int 
    type word   = (char * int) list
    
    type squareFun = word -> int -> int -> Result<int, Error>
    type square = Map<int, squareFun>
    
    type boardFun = coord -> Result<square option, Error>
    
    type board = {
        center        : coord
        defaultSquare : square
        squares       : boardFun
    }
    
    type squareProg = Map<int, string>
    type boardProg  = {
            prog       : string;
            squares    : Map<int, squareProg>
            usedSquare : int
            center     : coord
    
            isInfinite : bool   // For pretty-printing purposes only
            ppSquare   : string // For pretty-printing purposes only
        }