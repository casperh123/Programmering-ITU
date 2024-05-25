module Exam2023
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

    let collatz _ = failwith "not implemented"

(* Question 3.2: Even and odd Collatz sequence elements *)

    let evenOddCollatz _ = failwith "not implemented"

(* Question 3.3: Maximum length Collatz Sequence *)
  
    let maxCollatz _ = failwith "not implemented"

(* Question 3.4: Collecting by length *)
    let collect _ = failwith "not implemented"
    
(* Question 3.5: Parallel maximum Collatz sequence *)

    let parallelMaxCollatz _ = failwith "not implemented"

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

    type mem = unit (* replace this entire type with your own *)
    let emptyMem _ = failwith "not implemented"
    let lookup _ = failwith "not implemented"
    let assign _ = failwith "not implemented"

(* Question 4.2: Evaluation *)

    let evalExpr _ = failwith "not implemented"
    let evalStmnt _ = failwith "not implemented"
    let evalProg _ = failwith "not implemented"
    
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

    let lookup2 _ = failwith "not implemented"
    let assign2 _ = failwith "not implemented"

(* Question 4.4: State monad evaluation *)

    type StateBuilder() =

        member this.Bind(f, x)    = bind x f
        member this.Return(x)     = ret x
        member this.ReturnFrom(x) = x
        member this.Combine(a, b) = a >>= (fun _ -> b)

    let state = StateBuilder()

    let evalExpr2 _ = failwith "not implemented"
    let evalStmnt2 _ = failwith "not implemented"
    let evalProg2 _ = failwith "not implemented"
    
(* Question 4.5: Parsing *)
    
    open JParsec.TextParser
      
    let ParseExpr, eref = createParserForwardedToRef<expr>()  
    let ParseAtom, aref = createParserForwardedToRef<expr>()  
      
    let parseExpr _ = failwith "not implemented" // Parse addition and minus
          
    let parseAtom _ = failwith "not implemented" // Parse numbers and lookups

//    Uncomment the following two lines once you finish parseExpr and parseAtom             
//    do aref := parseAtom  
//    do eref := parseExpr  
      