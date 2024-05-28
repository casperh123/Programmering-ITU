module Exam2023

    open System.Linq
    open System.Linq.Expressions
    open System.Threading.Tasks

(* If you are importing this into F# interactive then comment out
   the line above and remove the comment for the line bellow.

   Do note that the project will not compile if you do this, but 
   it does allow you to work in interactive mode and you can just remove the '=' 
   to make the project compile again.

   You will also need to load JParsec.fs. Do this by typing
   #load "JParsec.fs" 
   in the interactive environment. You may need the entire path.

   Do not remove the module declaration (even though that does work) because you may inadvertently
   introduce indentation errors in your code that may be hard to find if you want
   to switch back to project mode. 

   Alternative, keep the module declaration as is, but load ExamInteractive.fsx into the interactive environment
   *)
(*
 module Exam2023 = 
 *)

(* 1: Logic *)

    type prop =  
    | TT  
    | FF  
    | And of prop * prop  
    | Or of prop * prop
    
    let p1 = And(TT, FF)  
    let p2 = Or(TT, FF)  
    let p3 = And(Or(TT, And(TT, FF)), TT)  
    let p4 = And(Or(TT, And(TT, FF)), Or(FF, And(TT, FF)))
    
(* Question 1.1: Evaluation *)
    let rec eval prop =
        match prop with
        | TT -> true 
        | FF -> false
        | And(prop1, prop2) -> eval(prop1) && eval(prop2)
        | Or(prop1, prop2) -> eval(prop1) || eval(prop2)
    
(* Question 1.2: Negation and implication *)
    let rec negate (prop : prop)=
        match prop with
        | TT -> FF
        | FF -> TT
        | And(prop, prop1) -> Or(negate prop, negate prop1) 
        | Or(prop, prop1) -> And(negate prop, negate prop1)
    
    let implies p q = Or(negate p, q)
        

(* Question 1.3: Bounded universal quantifiers *)
    let forall f lst =
        let rec aux lst acc =
            match lst with
            | [] -> acc
            | x::xs -> aux xs (And(f x, acc))
        aux lst TT

(* Question 1.4: Bounded existential quantifiers *)

    let exists f lst = List.fold (fun acc a -> Or(f a, acc)) FF lst
    
(* Question 1.5: Bounded unique existential quantifiers *)

    let existsOne f lst =
        let rec aux lst count =
            match lst, count with
            | [], 1 -> TT
            | [], _ -> FF
            | x :: xs, _ ->
                match f x with
                | TT when count = 0 -> aux xs 1
                | TT -> FF
                | FF -> aux xs count
        aux lst 0

    
(* 2: Code Comprehension *)
 
    let rec foo xs ys =  
        match xs, ys with  
        | _       , []                  -> Some xs   
        | x :: xs', y :: ys' when x = y -> foo xs' ys'   
        | _       , _                   -> None  
          
    let rec bar xs ys =
        match foo xs ys with
        | Some zs -> bar zs ys
        | None -> match xs with
                  | [] -> []
                  | x :: xs' -> x :: (bar xs' ys)  

    let baz (a : string) (b : string) =  
        bar [for c in a -> c] [for c in b -> c] |>  
        List.fold (fun acc c -> acc + string c) ""

(* Question 2.1: Types, names and behaviour *)

    (* 
    
    Q: What are the types of functions foo, bar, and baz?

    A:

    The type of foo is 'a list -> 'a list -> 'a list option
    The type of bar is 'a list -> 'a list -> 'a list
    The type of bas is string -> string -> string

    Q: What do the function foo, bar, and baz do.
       Focus on what they do rather than how they do it.
       
      A:
       foo checks wether or not ys is a prefix of xs. If ys is a prefix of xs, then remainder of xs after the prefix ys is returned . 
       If ys is not a prefix of xs, then it returns None.
       
       bar checks wether or not ys is in xs. If ys is in xs, as indicated by the matchcase Some zs,
       then the rest of xs without ys prefixed, zs, is checked for having ys as a prefix recursively, until this is no longer the case.
       If ys is not a prefix to xs, then the first element of the array xs, x, is prepended to the result of the recursive function.
       This will result in an array, bar xs ys, that does not contain any occurrences of ys.  
       
      baz removes any occurences of the string b from string a.
      string a and string b are converted to char lists, and then occurences of b are removed from a.
      The result of this is then converted back to a string. 
    
    
    Q: What would be appropriate names for functions 
       foo could be called removePrefix.
       bar could be stripAllOccurences.
       baz could be called stripSubstring.

    A: <Your answer goes here>
        
        [for c in a -> c]
        has the type char list
        converts the string a to a char list.
        
        [for c in b -> c]
        has the type char list
        converts the string b to a chat list.
        
        List.fold (fun acc c -> acc + string c) ""
        has the type char list -> string
        It converts the char list back to a string.
        
        The |> is the forward pipe operator. It takes the result of the expression to it's left and passes it as an
        argument to the function on its right.
    *)
        

(* Question 2.2: Code snippets *)

 
    (* 
    The function baz contains the following three code snippets. 

    * A: `[for c in a -> c]`
    * B: `[for c in b -> c]`
    * C: `List.fold (fun acc c -> acc + string c) ""`

    Q: In the context of the baz function, i.e. assuming that `a` and `b` are strings, 
       what are the types of snippets A, B, and C and what are they -- 
       focus on what they do rather than how they do it.
    
    A: <Your answer goes here>
    
    Q: Explain the use of the `|>`-operator in the baz function.

    A: <Your answer goes here>

    *)

(* Question 2.3: No recursion *) 

    let foo2 xs ys =
        match List.splitAt (List.length ys) xs with
        | (prefix, remainder) when prefix = ys -> Some remainder
        | _ -> None

(* Question 2.4 *)

    (*

    Q: The function `bar` is not tail recursive. Demonstrate why.
       To make a compelling argument you should evaluate a function call of the function,
       similarly to what is done in Chapter 1.4 of HR, and reason about that evaluation. 
       You need to make clear what aspects of the evaluation tell you that the function 
       is not tail recursive. Keep in mind that all steps in an evaluation chain must 
       evaluate to the same value ( (5 + 4) * 3 --> 9 * 3 --> 27 , for instance).
       
       You do not have to step through the foo-function. You are allowed to evaluate 
       that function immediately.

    A: <Your answer goes here>
        bar [1,2,3,4] [1]
        --> 2 :: bar [3,4] [1]
        --> 2 :: (3 :: bar [4] [1])
        --> 2 :: (3 :: (4 :: bar [] [1]))
        --> 2 :: (3 :: (4 :: []))
        --> 2 :: (3 :: [4])
        --> 2 :: [3, 4]
        --> [2, 3, 4]

        
        
        This function is not tail recursive because after each recursive call to bar, there are still pending operations that need to be evaluated. Specifically, the recursive calls are followed by cons operations (::) that combine the results of the recursive calls with the current element.

In a tail-recursive function, the result of the recursive call is directly returned without any further computation. However, in bar, the recursive call does not immediately compute the final result; instead, it waits until the base case is reached, and then combines the results step-by-step as the call stack unwinds.

The function must maintain the state of each intermediate computation, which prevents the compiler from optimizing the recursion into a loop. This need for retaining intermediate states and performing additional operations after the recursive calls clearly demonstrates that bar is not tail recursive.
    *)
(* Question 2.5 *)
    
    let rec barMannes xs ys =
        match foo xs ys with
        | Some zs -> bar zs ys
        | None -> match xs with
                  | [] -> []
                  | x :: xs' -> x :: (bar xs' ys)  

    let rec barTail xs ys k =
        match foo xs ys with
        | Some zs -> barTail zs ys k
        | None -> match xs with
                  | [] -> []
                  | x :: xs' -> barTail xs' ys (fun result -> k (x :: result))
           

(* 3: Collatz Conjecture *)

(* Question 3.1: Collatz sequences *)

    let collatz x =
        let rec aux x acc =
            match x with
            | x when x < 0 -> failwith $"Non positive number: {x}"
            | _ -> match x with
                    | 1 -> List.rev (1::acc)
                    | x when (x % 2) = 0 -> aux (x/2) (x::acc) 
                    | x when (x % 2) = 1 -> aux (3*x + 1) (x::acc)
        aux x []

(* Question 3.2: Even and odd Collatz sequence elements *)

    let evenOddCollatz x =
        let rec aux collatz even odd =
            match collatz with
            | [] -> (even, odd)
            | x::xs when (x % 2) = 0 -> aux xs (even + 1) odd
            | x::xs when (x % 2) = 1 ->  aux xs even (odd + 1)
        aux (collatz x) 0 0

(* Question 3.3: Maximum length Collatz Sequence *)
  
    let maxCollatz (x:int) (y:int) =
        let rec aux currentX endY currentMax maxLengthX =
            match currentX <= endY with
            | false -> (maxLengthX, currentMax)
            | true ->
                let sequenceLength = List.length <| collatz currentX
                let (newMax, newMaxX) =
                    match sequenceLength > currentMax with
                    | true -> (sequenceLength, currentX)
                    | false -> (currentMax, maxLengthX)
                aux (currentX + 1) endY newMax newMaxX
        aux x y 0 x

(* Question 3.4: Collecting by length *)
    let collect (x:int) (y:int) =
        let rec processNumber currentX map =
            let sequenceLength = List.length (collatz currentX)
            let updatedMap =
                match Map.tryFind sequenceLength map with
                | Some set -> map.Add(sequenceLength, Set.add currentX set)
                | None -> map.Add(sequenceLength, Set.ofList [currentX])
            buildMap (currentX + 1) updatedMap
        and buildMap currentX map =
            match currentX > y with
            | true -> map
            | false -> processNumber currentX map
        buildMap x Map.empty
        
        
(* Question 3.5: Parallel maximum Collatz sequence *)

    let parallelMaxCollatz x y n =
        
        let rangeSize = (y - x + 1) / n
        
        let tasks = [
            for i in 0 .. (n - 1) ->
                let start = x + (i * rangeSize)
                let endInclusive = start + rangeSize - 1
                Task.Run(fun () -> maxCollatz start endInclusive)
        ]
        
        let results = Task.WhenAll(tasks).Result
        
        results |> Array.maxBy snd |> fst
           

(* 4: Memory machines *)

    type expr =  
    | Num    of int              // Integer literal
    | Lookup of expr             // Memory lookup
    | Plus   of expr * expr      // Addition
    | Minus  of expr * expr      // Subtraction
          
    type stmnt =  
    | Assign of expr * expr      // Assign value to memory location
    | While  of expr * prog      // While loop
      
    and prog = stmnt list        // Programs are sequences of statements

    let (.+.) e1 e2 = Plus(e1, e2)  
    let (.-.) e1 e2 = Minus(e1, e2)  
    let (.<-.) e1 e2 = Assign (e1, e2)
    
    // Starting from memory {0, 0, 2, 0}
    let fibProg x =  
        [Num 0 .<-. Num x       // {x, 0, 2, 0}
         Num 1 .<-. Num 1       // {x, 1, 2, 0}
         Num 2 .<-. Num 0       // {x, 1, 0, 0}
         While (Lookup (Num 0), 
                [Num 0 .<-. Lookup (Num 0) .-. Num 1  
                 Num 3 .<-. Lookup (Num 1)  
                 Num 1 .<-. Lookup (Num 1) .+. Lookup (Num 2)  
                 Num 2 .<-. Lookup (Num 3)  
                ]) // after loop {0, fib (x + 1), fib x, fib x}
         ]

(* Question 4.1: Memory blocks *)

    type mem = int array

    let emptyMem (x: int) : mem = Array.create x 0

    let lookup (m: mem) (i: int) : int = m[i]

    let assign (m: mem) (i: int) (v: int) : mem =
        m[i] <- v
        m

(* Question 4.2: Evaluation *)

    let rec evalExpr (m : mem) (e : expr) : int =
        match e with
        | Num x -> x
        | Lookup e' -> m[evalExpr m e']
        | Plus (e1, e2) -> evalExpr m e1 + evalExpr m e2
        | Minus (e1, e2) -> evalExpr m e1 - evalExpr m e2
        
        
    let rec evalStmnt (m : mem) (s: stmnt) : mem =
        match s with
        | Assign (e1, e2) -> assign m (evalExpr m e1) (evalExpr m e2)
        | While (e, p) ->
            match evalExpr m e with
            | 0 -> m
            | _ -> evalProg m (p @ [While(e, p)])
    and evalProg (m : mem) (p : prog) =
        match p with
        | [] -> m
        | x::xs -> evalProg (evalStmnt m x) xs
    
(* Question 4.3: State monad *)
    type StateMonad<'a> = SM of (mem -> ('a * mem) option)  
      
    let ret x = SM (fun s -> Some (x, s))  
    let fail  = SM (fun _ -> None)  
    let bind f (SM a) : StateMonad<'b> =   
        SM (fun s ->   
            match a s with   
            | Some (x, s') ->  let (SM g) = f x               
                               g s'  
            | None -> None)  
          
    let (>>=) x f = bind f x  
    let (>>>=) x y = x >>= (fun _ -> y)  
      
    let evalSM m (SM f) = f m

    let lookup2 i =
        SM (fun mem ->
            match i with
            | i when i >= 0 && i < Array.length mem -> Some (mem[i], mem)
            | _ -> None)
            
    let assign2 (i: int) (v: int) : StateMonad<unit> =
        SM (fun mem ->
            match i with
            | i when i >= 0 && i < Array.length mem ->
                mem[i] <- v
                Some ((), mem)
            | _ -> None)


(* Question 4.4: State monad evaluation *)

    type StateBuilder() =

        member this.Bind(f, x)    = bind x f
        member this.Return(x)     = ret x
        member this.ReturnFrom(x) = x
        member this.Combine(a, b) = a >>= (fun _ -> b)

    let state = StateBuilder()

    let rec evalExpr2 (e : expr) : StateMonad<int> =
        match e with
        | Num x -> ret x
        | Lookup e' -> evalExpr2 e' >>= lookup2
        | Plus (e1, e2) ->
            evalExpr2 e1 >>= fun v1 ->
            evalExpr2 e2 >>= fun v2 ->
            ret (v1 + v2)
        | Minus (e1, e2) ->
            evalExpr2 e1 >>= fun v1 ->
            evalExpr2 e2 >>= fun v2 ->
            ret (v1 - v2)
                           
        
        
    let rec evalStmnt2 (s : stmnt) : StateMonad<unit> =
        match s with
        | Assign (e1, e2) ->
            evalExpr2 e1 >>= fun v1 ->
            evalExpr2 e2 >>= fun v2 ->
            assign2 v1 v2
        | While (e, p) ->
            evalExpr2 e >>= function
            | 0 -> ret ()
            | _ -> evalProg2 (p @ [While(e, p)]) >>= fun _ -> ret ()
    and evalProg2 (p : prog) : StateMonad<unit>=
        match p with
        | [] -> ret ()
        | x::xs -> evalStmnt2 x >>>= evalProg2 xs
    
(* Question 4.5: Parsing *)
    
    open JParsec.TextParser
      
    let ParseExpr, eref = createParserForwardedToRef<expr>()  
    let ParseAtom, aref = createParserForwardedToRef<expr>()
    
    
    let parseNumber = pint32 |>> Num
    let parseLookup = between (pchar '[') (pchar ']') ParseExpr |>> Lookup
  


    
    let parseExpr : Parser<expr> = failwith "not implemented" // Parse addition and minus
          
    let parseAtom = parseNumber <|> parseLookup

//    Uncomment the following two lines once you finish parseExpr and parseAtom             
//    do aref := parseAtom  
//    do eref := parseExpr  
      