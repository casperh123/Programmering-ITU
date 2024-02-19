module Assignment3

    open Types
        
    let rec arithEvalSimple =
       function
       | N n -> n
       | Add(exp1, exp2) -> (arithEvalSimple exp1) + (arithEvalSimple exp2)
       | Sub(exp1, exp2) -> (arithEvalSimple exp1) - (arithEvalSimple exp2)
       | Mul(exp1, exp2) -> (arithEvalSimple exp1) * (arithEvalSimple exp2)
     
    let rec arithEvalState exp st =
       match exp with
       | N n -> n
       | V v -> Map.tryFind v st |> Option.defaultValue 0
       | Add(exp1, exp2) -> (arithEvalState exp1 st) + (arithEvalState exp2 st)
       | Sub(exp1, exp2) -> (arithEvalState exp1 st) - (arithEvalState exp2 st)
       | Mul(exp1, exp2) -> (arithEvalState exp1 st) * (arithEvalState exp2 st)
              
    let hello : word = [('h', 4); ('e',1); ('l',1); ('l',1); ('o',1)] // Insert your version of hello here from the last assignment

    let rec arithEval (exp:aExp) (w :word) st =
       match exp with
       | N n -> n
       | V v -> Map.tryFind v st |> Option.defaultValue 0
       | WL -> List.length w
       | PV a -> snd (w.Item (arithEval a w st))
       | Add(exp1, exp2) -> arithEval exp1 w st + arithEval exp2 w st
       | Sub(exp1, exp2) -> arithEval exp1 w st - arithEval exp2 w st
       | Mul(exp1, exp2) -> arithEval exp1 w st * arithEval exp2 w st
              

    
    type cExp =
       | C  of char      (* Character value *)
       | ToUpper of cExp (* Converts lower case to upper case character, non-characters unchanged *)
       | ToLower of cExp (* Converts upper case to lower case character, non characters unchanged *)
       | CV of aExp      (* Character lookup at word index *)

    let rec charEval (exp:cExp) (w:word) st =
       match exp with
       | C c -> System.Char.ToUpper c
       | ToUpper exp ->
          match exp with
          | C c -> System.Char.ToUpper c
          | exp -> charEval exp w st
       | ToLower exp ->
          match exp with
          | C c -> System.Char.ToLower c
          | exp -> charEval exp w st
       | CV n ->
          let index = arithEval n w st
          let (char, _) = w.[index]
          charEval (C char) w st

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

    let rec boolEval (exp: bExp) (w:word) (st:Map<string, int>) =
       match exp with
       | TT -> true
       | FF -> false 
       | AEq (exp1, exp2) -> boolEval(exp1 w st) ==  boolEval(exp2 w st)
       | ALt (exp1, exp2) -> boolEval(exp1 w st) > boolEval(exp2 w st)
       | Not bExp ->
        
    let isConsonant _ = failwith "not implemented"

    type stmnt =
       | Skip                        (* does nothing *)
       | Ass of string * aExp        (* variable assignment *)
       | Seq of stmnt * stmnt        (* sequential composition *)
       | ITE of bExp * stmnt * stmnt (* if-then-else statement *)    
       | While of bExp * stmnt       (* while statement *)

    let evalStmnt _ = failwith "not implemented"

    let stmntToSquareFun (_: stmnt) : squareFun = fun _ _ _ -> 0
    
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