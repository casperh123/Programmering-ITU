module ReExam2023

    open System.Runtime.InteropServices
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
 module ReExam2023 = 
 *)

(* 1: Arithmetic *)
    
    type arith =
    | Num of int
    | Add of arith * arith
    
    let p1 = Num 42
    let p2 = Add(Num 5, Num 3)
    let p3 = Add(Add(Num 5, Num 3), Add(Num 7, Num (-9)))
    
    let p4 = Num 2
    let p5 = Num 8
    
    let p6 = Num 3
(* Question 1.1: Evaluation *)
    let rec eval a =
        match a with
        | Num x -> x 
        | Add (e1, e2) -> eval e1 + eval e2  
    
(* Question 1.2: Negation and subtraction *)
    let rec negate a =
        match a with
        | Num x -> Num -x
        | Add (e1, e2) -> Add (negate e1, negate e2)
        
    let subtract a b = Add (a, negate b)
        
            
(* Question 1.3: Multiplication *)
        
    let rec multiply (a : arith) (b: arith) : arith =
        match (a, b) with
        | Num x, Num y -> Num (x * y)
        | Num x, Add(e1, e2) -> Add(multiply (Num x) e1, multiply (Num x) e2)
        | Add (e1, e2), c
        | c, Add(e1, e2) -> Add (multiply e1 c, multiply e2 c)
    
(* Question 1.4: Exponents *)

    let pow (a : arith) (b : arith) : arith =
        let rec aux a b (acc : arith) =
            match a with
            | _ when eval b = 1 -> acc
            | a -> aux a (subtract b (Num 1)) (multiply a acc) 
        aux a b a
    
(* Question 1.5: Iteration *)

    let rec iterate f acc a =
        match a with
        | _ when eval a = 0 -> acc
        | _ -> iterate f (f acc) (subtract a (Num 1))
        
    let pow2 (a: arith) (b: arith) : arith =
        iterate (multiply a) a (subtract b (Num 1))
(* 2: Code Comprehension *)
 
    let rec foo =
        function
        | 0            -> true
        | x when x > 0 -> bar (x - 1)
        | x            -> bar (x + 1)
        
    and bar =
        function
        | 0            -> false
        | x when x > 0 -> foo (x - 1)
        | x            -> foo (x + 1)
        
    let rec baz =
        function
        | []                 -> [], []
        | x :: xs when foo x ->
            let ys, zs = baz xs
            (x::ys, zs)
        | x :: xs ->
            let ys, zs = baz xs
            (ys, x::zs)
        

(* Question 2.1: Types, names and behaviour *)

    (* 
    Types of Functions
foo: The type of foo is int -> bool.
bar: The type of bar is int -> bool.
baz: The type of baz is int list -> int list * int list.
Behavior of Functions
foo:

Behavior: Determines if a given integer is even.
Explanation: If the input is 0, it returns true. For positive and negative numbers, it decrements or increments the number and toggles to calling bar until the input reaches 0.
bar:

Behavior: Determines if a given integer is odd.
Explanation: If the input is 0, it returns false. For positive and negative numbers, it decrements or increments the number and toggles to calling foo until the input reaches 0.
baz:

Behavior: Splits a list of integers into two lists: one containing even numbers and the other containing odd numbers.
Explanation: It recursively processes the list. If foo x is true (i.e., x is even), it adds x to the first list; otherwise, it adds x to the second list.
Appropriate Names for Functions
foo:

New Name: is_even
Rationale: This name clearly indicates that the function checks if a number is even.
bar:

New Name: is_odd
Rationale: This name clearly indicates that the function checks if a number is odd.
baz:

New Name: partition_even_odd
Rationale: This name clearly indicates that the function partitions a list into even and odd numbers.
        
    *)
        

(* Question 2.2: Code snippets *)

 
    (* 
    The function baz contains the following three code snippets. 

    * A: `baz xs`
    * B: `bar x`
    * C: `(ys, x::zs)`

    Q: In the context of the baz function, i.e. assuming that `x`, `xs`, `ys`, and `zs` all have the correct types,
       what are the types of snippets A, B, and C, expressed using the F# syntax for types, and what are they -- 
       focus on what they do rather than how they do it.
    
    A: <Your answer goes here>
    
    Q: * Explain the use of the `and`-operator that connect the `foo` and the `bar` functions.
       * Argue if the program would work if you replaced `and` with `let rec`.

    A: <Your answer goes here>
    
    Types and Roles of Snippets
Snippet A: baz xs
Type: int list -> int list * int list

Explanation:

Type Details: baz xs takes a list of integers (xs) as input and returns a tuple of two lists of integers ((ys, zs)).
Role: This recursive call processes the remaining elements of the list xs, partitioning them into even and odd lists.
Snippet B: foo x
Type: int -> bool

Explanation:

Type Details: foo x takes an integer (x) as input and returns a boolean.
Role: This determines whether the integer x is even (true if even, false if odd).
Snippet C: (ys, x::zs)
Type: int list * int list

Explanation:

Type Details: This is a tuple containing two lists of integers. The first element (ys) is a list, and the second element (x::zs) is a list with x prepended to zs.
Role: This constructs the result tuple where x is added to the beginning of the list zs, effectively categorizing x as an odd number and keeping ys unchanged.
Detailed Roles
Snippet A: baz xs
What it does: Recursively partitions the remaining elements of the list xs into even and odd lists.
How it fits in: This recursive call is essential for breaking down the list until it's empty, at which point the base case (([], [])) is returned.
Snippet B: foo x
What it does: Checks if the current element x is even.
How it fits in: This check determines which of the two resulting lists (ys or zs) the current element x will be added to.
Snippet C: (ys, x::zs)
What it does: Constructs the tuple for the current recursive step by adding the current element x to the odd list (zs).
How it fits in: When foo x is false (indicating that x is odd), this snippet constructs the new result tuple with x added to the front of the odd list (zs).
Summary
Snippet A (baz xs): This snippet processes the remaining list elements and returns the partitioned result.
Snippet B (foo x): This snippet determines if the current element is even or odd.
Snippet C ((ys, x::zs)): This snippet constructs the result tuple by adding the current element to the appropriate list based on its even/odd status.
    *)

(* Question 2.3: No recursion *) 

    let foo2 x =
        x % 2 = 0
    let bar2 x =
        x % 2 <> 0

(* Question 2.4: Tail Recursion *)

    (*

    Q: The function `baz` is not tail recursive. Demonstrate why.
       To make a compelling argument you should evaluate a function call of the function,
       similarly to what is done in Chapter 1.4 of HR, and reason about that evaluation. 
       You need to make clear what aspects of the evaluation tell you that the function 
       is not tail recursive. Keep in mind that all steps in an evaluation chain must 
       evaluate to the same value ( (5 + 4) * 3 --> 9 * 3 --> 27 , for instance).
       
       You do not have to step through the foo- or the bar-functions. You are allowed to evaluate 
       those function immediately.

    A: <Your answer goes here>
    
    *)
(* Question 2.5: Continuations *)

    
    let bazTail (lst: int list) : int list * int list =
        let rec bazCont lst cont = 
            match lst with
            | [] -> cont [] []
            | x :: xs when foo x ->
                bazCont xs (fun ys zs -> cont (x::ys) zs)
            | x :: xs ->
                bazCont xs (fun ys zs -> cont ys (x::zs))
        bazCont lst (fun yx zs -> (yx, zs))

(* 3: Balanced brackets *)

      
    let explode (str : string) = [for c in str -> c]
    let implode (lst : char list) = lst |> List.toArray |> System.String
    
(* Question 3.1: Balanced brackets *)
    
    let balanced string =
        let chars = explode string
      
        let rec balance chars stack =
            match chars with
            | [] -> stack = []
            | x::xs when x = '(' || x = '{' || x = '[' -> balance xs (x::stack)
            | x::xs when x = ')' || x = '}' || x = ']' ->
                match stack with
                | [] -> false   
                | z::zs when (z = '(' && x = ')') || (z = '{' && x = '}' || z = '[' && x = ']') -> balance xs zs
                | _ -> false
            
        balance chars []

(* Question 3.2: Arbitrary delimiters *)
    
    let balanced2 _ = failwith "not implemented"
    
(* Question 3.3: Matching brackets and palindromes *)    
    
    let balanced3 _ = failwith "not implemented" 
    
    let symmetric _ = failwith "not implemented"
        
(* Question 3.4: Parsing balanced brackets *)    
               
    open JParsec.TextParser
    
        
    let ParseBalanced, bref = createParserForwardedToRef<unit>()
    
    let parseBalancedAux =
        let openBrace = pchar '{' >>. ParseBalanced .>> pchar '}'
        let openParen = pchar '(' >>. ParseBalanced .>> pchar ')'
        let openBracket = pchar '[' >>. ParseBalanced .>> pchar ']'
        
        let balancedString = many (openBrace <|> openParen <|> openBracket) |>> ignore
        
        balancedString
    
    let rec parseBalanced = parseBalancedAux .>> pstring "**END**"
    do bref := parseBalancedAux
    
            
(* Question 3.5: Parallel counting *)

    let countBalanced lst x =
        
        let lists = List.chunkBySize x lst
        
        let tasks =
            lists |> List.map (fun chunk ->
                Task.Run(fun () ->
                    chunk
                    |> List.filter (fun a -> balanced a)
                    |> List.length
                    )
                )
        tasks
        |> Task.WhenAll
        |> fun tasks -> tasks.Result
        |> Array.sum

(* 4: BASIC *)
    
    
    type var = string

    type expr =  
    | Num    of int              // Integer literal
    | Lookup of var              // Variable lookup
    | Plus   of expr * expr      // Addition
    | Minus  of expr * expr      // Subtraction
    
    type stmnt =
    | If of expr * uint32       // Conditional statement (if-then (no else)).
    | Let of var * expr        // Variable update/declaration
    | Goto of uint32           // Goto
    | End                      // Terminate program
      
    type prog = (uint32 * stmnt) list  // Programs are sequences of commands with their own line numbers 

    
    let (.+.) e1 e2 = Plus(e1, e2)  
    let (.-.) e1 e2 = Minus(e1, e2)  
    
    let fibProg xarg =  
        [(10u, Let("x",    Num xarg))                         // x = xarg
         (20u, Let("acc1", Num 1))                            // acc1 = 1
         (30u, Let("acc2", Num 0))                            // acc2 = 0
         
         (40u, If(Lookup "x", 60u))                           // if x > 0 then goto 60 (start loop)
         
         (50u, Goto 110u)                                     // Goto 110 (x = 0, terminate program)
         
         (60u, Let ("x", Lookup "x" .-. Num 1))               // x = x - 1
         (70u, Let ("result", Lookup "acc1"))                 // result = acc1
         (80u, Let ("acc1", Lookup "acc1" .+. Lookup "acc2")) // acc1 = acc1 + acc2
         (90u, Let ("acc2", Lookup "result"))                 // acc2 = result
         (100u, Goto 40u)                                     // Goto 40u (go to top of loop)
         
         (110u, End)                                          // Terminate program
                                                              // the variable result contains the
                                                              // fibonacci number of xarg
         ]

(* Question 4.1: Basic programs *)

    type basicProgram = Map<uint32, stmnt>
    
    let mkBasicProgram (p : prog) : basicProgram =
        Map.ofList p
        
    let getStmnt (l : uint32) (p : basicProgram) =
        Map.find l p
    
    let nextLine (l: uint32) (p: basicProgram) : uint32 =
        Map.findKey (fun k _ -> k > l) p
        
    let firstLine (p: basicProgram) : uint32 =
        p |> Map.minKeyValue |> fst
    
(* Question 4.2: State *)

    type state = {
    lineNumber: uint32
    varEnv: Map<string, int>
    }   
    
    let emptyState (p : basicProgram) : state =
        {
          lineNumber = (firstLine p);
          varEnv = Map.empty
          }
    
    
    let goto (l : uint32) (st : state) : state =
        { st with lineNumber = l }

    let getCurrentStmnt (p : basicProgram) (st : state) =
        getStmnt st.lineNumber p        
        
    let update (v : var) (a : int) (st : state) =
        { st with varEnv = Map.add v a st.varEnv }
    
    let lookup (v : var) (st: state) =
        Map.find v st.varEnv
    
    
(* Question 4.3: Evaluation *)
    
    let rec evalExpr (e: expr) (st:state): int =
        match e with
        | Num x -> x
        | Lookup v -> lookup v st
        | Plus (e1, e2) -> evalExpr e1 st + evalExpr e2 st
        | Minus (e1, e2) -> evalExpr e1 st + evalExpr e2 st
    
    let step (p : basicProgram) (st : state) : state =
        goto (nextLine st.lineNumber p) st
        
    let evalProg (p : basicProgram) : state =
        let rec aux (p : basicProgram) (st : state) : state =
            match getCurrentStmnt p st with
            | If(e, l) when evalExpr e st <> 0 -> aux p <| goto l st
            | If _ -> aux p <| step p st
            | Let(v, e) -> update v (evalExpr e st) st |> step p |> aux p 
            | Goto l -> st |> goto l |> aux p
            | End -> st
            
        let emptyState = (emptyState p) 
        aux p emptyState 
        
        
      
(* Question 4.4: State monad *)
    type StateMonad<'a> = SM of (basicProgram -> state -> 'a * state)  
      
    let ret x = SM (fun _ s -> (x, s))
    
    let bind f (SM a) : StateMonad<'b> =   
        SM (fun p s ->
            let x, s' = a p s
            let (SM g) = f x
            g p s')
          
    let (>>=) x f = bind f x  
    let (>>>=) x y = x >>= (fun _ -> y)  
      
    let evalSM p (SM f) = f p (emptyState p)

    let goto2 (l : uint32) : StateMonad<unit> = SM (fun p st -> ((), goto l st ))
        
    
    let getCurrentStmnt2 = SM (fun p st -> (getCurrentStmnt p st, st))
    
    
    let lookup2 (v : var) : StateMonad<int> =  SM (fun p st -> (lookup v st, st))
    
    let update2 (v : var) (a : int) : StateMonad<unit>= SM (fun p st -> ((), update v a st))
    
    let step2 : StateMonad<unit>= SM (fun p st -> ((), step p st))

(* Question 4.5: State monad evaluation *)

    type StateBuilder() =

        member this.Bind(f, x)    = bind x f
        member this.Return(x)     = ret x
        member this.ReturnFrom(x) = x
        member this.Combine(a, b) = a >>= (fun _ -> b)

    let state = StateBuilder()

    let rec evalExpr2 (e : expr) : StateMonad<int> =
        match e with
        | Num x -> ret x
        | Lookup v -> lookup2 v
        | Plus (e1, e2) ->
            evalExpr2 e1 >>= fun v1 ->
            evalExpr2 e2 >>= fun v2 ->
            ret (v1 + v2)
        | Minus (e1, e2) ->
            evalExpr2 e1 >>= fun v1 ->
            evalExpr2 e2 >>= fun v2 ->
            ret (v1 - v2)
        
    
    let rec evalProg2 : StateMonad<unit> =
        getCurrentStmnt2 >>=
        function
        | If (e, l)  -> evalExpr2 e >>= (fun x -> if x <> 0 then goto2 l else step2) >>>= evalProg2
        | Let (v, e) -> evalExpr2 e >>= update2 v >>>= step2 >>>= evalProg2
        | Goto l -> goto2 l >>>= evalProg2
        | End -> ret ()