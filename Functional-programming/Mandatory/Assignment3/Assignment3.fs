module Assignment3

    open Types
        
    let rec arithEvalSimple =
       function
       | N n -> n
       | PV(exp1) -> (arithEvalSimple exp1) * (arithEvalSimple exp1)
       | Add(exp1, exp2) -> (arithEvalSimple exp1) + (arithEvalSimple exp2)
       | Sub(exp1, exp2) -> (arithEvalSimple exp1) - (arithEvalSimple exp2)
       | Mul(exp1, exp2) -> (arithEvalSimple exp1) * (arithEvalSimple exp2)
     
    let rec arithEvalState exp st =
       match exp with
       | N n -> n
       | V v -> Map.tryFind v st |> Option.defaultValue 0
       | Add(exp1, exp2) -> arithEvalState exp1 st + arithEvalState exp2 st
       | Sub(exp1, exp2) -> arithEvalState exp1 st + arithEvalState exp2 st
       | Mul(exp1, exp2) -> arithEvalState exp1 st + arithEvalState exp2 st
              
              
    let hello = [] // Insert your version of hello here from the last assignment

    let arithEval _ = failwith "not implemented"

    type cExp =
       | C  of char      (* Character value *)
       | ToUpper of cExp (* Converts lower case to upper case character, non-characters unchanged *)
       | ToLower of cExp (* Converts upper case to lower case character, non characters unchanged *)
       | CV of aExp      (* Character lookup at word index *)

    let charEval _ = failwith "not implemented"

    type bExp =             
       | TT                   (* true *)
       | FF                   (* false *)

       | AEq of aExp * aExp   (* numeric equality *)
       | ALt of aExp * aExp   (* numeric less than *)

       | Not of bExp           (* boolean not *)
       | Conj of bExp * bExp   (* boolean conjunction *)

       | IsDigit of cExp       (* check for digit *)
       | IsLetter of cExp      (* check for letter *)
       | IsVowel of cExp       (* check for vowel *)

    let (~~) b = Not b
    let (.&&.) b1 b2 = Conj (b1, b2)
    let (.||.) b1 b2 = ~~(~~b1 .&&. ~~b2)       (* boolean disjunction *)
       
    let (.=.) a b = AEq (a, b)   
    let (.<.) a b = ALt (a, b)   
    let (.<>.) a b = ~~(a .=. b)
    let (.<=.) a b = a .<. b .||. ~~(a .<>. b)
    let (.>=.) a b = ~~(a .<. b)                (* numeric greater than or equal to *)
    let (.>.) a b = ~~(a .=. b) .&&. (a .>=. b) (* numeric greater than *)

    let boolEval _ = failwith "not implemented"
        
    let isConsonant _ = failwith "not implemented"

    type stmnt =
       | Skip                        (* does nothing *)
       | Ass of string * aExp        (* variable assignment *)
       | Seq of stmnt * stmnt        (* sequential composition *)
       | ITE of bExp * stmnt * stmnt (* if-then-else statement *)    
       | While of bExp * stmnt       (* while statement *)

    let evalStmnt _ = failwith "not implemented"

    let stmntToSquareFun _ = failwith "not implemented"
    
    let singleLetterScore : squareFun = stmntToSquareFun (Ass ("_result_", arithSingleLetterScore))
    let doubleLetterScore : squareFun = stmntToSquareFun (Ass ("_result_", arithDoubleLetterScore))
    let tripleLetterScore : squareFun = stmntToSquareFun (Ass ("_result_", arithTripleLetterScore))

    let doubleWordScore : squareFun = stmntToSquareFun (Ass ("_result_", arithDoubleWordScore))
    let tripleWordScore : squareFun = stmntToSquareFun (Ass ("_result_", arithTripleWordScore))

    
    let oddConsonants = Skip // Replace this with your version of oddConsonants

    type square2 = (int * stmnt) list
    
    let SLS = [(0, Ass ("_result_", arithSingleLetterScore))]
    let DLS = [(0, Ass ("_result_", arithDoubleLetterScore))]
    let TLS = [(0, Ass ("_result_", arithTripleLetterScore))]

    let DWS = [(1, Ass ("_result_", arithDoubleWordScore))] @ SLS
    let TWS = [(1, Ass ("_result_", arithTripleWordScore))] @ SLS

    let calculatePoints2 _ = failwith "not implemented"