open System
open Exam2023

let testQ1 () =
    (* Testsfor Q1.1 *)
    printfn "Testing Question 1"
    
    let p1 = And(TT, FF)  
    let p2 = Or(TT, FF)  
    let p3 = And(Or(TT, And(TT, FF)), TT)  
    let p4 = And(Or(TT, And(TT, FF)), Or(FF, And(TT, FF)))
    
    printfn "%A" (eval p1)
    printfn "%A" (eval p2)
    printfn "%A" (eval p3)
    printfn "%A" (eval p4)
    
    printfn "Question 1.2"
    
    printfn "%A" (negate TT)
    printfn "%A" (negate FF)
    printfn "%A" (negate p1)
    printfn "%A" (negate p2)
    printfn "%A" (negate p3)
    printfn "%A" (negate p4)
    
    printfn "Implication"
    
    printfn "%A" (implies TT FF)
    printfn "%A" (implies FF TT)
    printfn "%A" (implies p3 p4)
    
    printfn "Exists"
    
    let mod2 x = if x % 2 = 0 then TT else FF
    
    printfn "%A" ([0 .. 10] |> exists mod2 |> eval)
    printfn "%A" ([1 .. 2 .. 10] |> forall mod2 |> eval)

    printfn "Bounded existential"
    
    printfn "%A" ([1 .. 10] |> existsOne (fun x -> if x % 2 = 0 then TT else FF) |> eval)
    printfn "%A" ([1 .. 10] |> existsOne (fun x -> if x % 5 = 0 then TT else FF) |> eval)
    printfn "%A" ([1 .. 10] |> existsOne (fun x -> if x % 6 = 0 then TT else FF) |> eval)

let testQ2 () =
    printfn "Testing Question 2"
    // place debug prints for Q2 here
    ()

let testQ3 () =
    printfn "Testing Question 3"
    // place debug prints for Q3 here
    ()
    
    printfn "%A" (collatz 1)
    printfn "%A" (collatz 2)
    printfn "%A" (collatz 3)
    printfn "%A" (collatz 42)
    
    printfn "Even odd collatz"
    
    printfn "%A" (evenOddCollatz 1)
    printfn "%A" (evenOddCollatz 2)
    printfn "%A" (evenOddCollatz 3)
    printfn "%A" (evenOddCollatz 77031)
    
    printfn "Collat max of sequence"
    
    printfn "%A" (maxCollatz 1 10)
    printfn "%A" (maxCollatz 100 1000)
    printfn "%A" (maxCollatz 1000 1000)
    
    printfn "Map with shit"
    
    printfn "%A" (collect 20 30)
    printfn "%A" (collect 100 110)
    
    printfn "Parallel Shit"
    
    printfn "%A" (parallelMaxCollatz 1 1000 1)
    printfn "%A" (parallelMaxCollatz 1 1000 2)
    printfn "%A" (parallelMaxCollatz 1 1000 100)
    printfn "%A" (parallelMaxCollatz 1 1000 500)

let testQ4 () =
    printfn "Testing Question 4"
    // place debug prints for Q4 here
    ()
    
    let assignedMem = emptyMem 4
    let assignetMem1 = assign (emptyMem 5) 2 42
    
    printfn $"%A{evalExpr assignetMem1 (Num 5)}"
    printfn $"%A{lookup (evalProg assignedMem (fibProg 10)) 2}"
    printfn $"%A{[0..3] |> List.map (fun x -> Assign(Num x, Num x)) |> evalProg assignedMem}"
    printfn $"%A{evalProg assignedMem (fibProg 10)}"

    printfn "mannes"
    
    let assignedMem = emptyMem 5
    
    printfn $"%A{lookup2 2 |> evalSM assignedMem |> Option.map fst}"
    printfn $"%A{lookup2 -23 |> evalSM assignedMem |> Option.map fst}"
    printfn $"%A{assign2 2 42 >>>= lookup2 2 |> evalSM assignedMem |> Option.map fst}"
    printfn $"%A{assign2 2 42 >>>= lookup2 4 |> evalSM assignedMem |> Option.map fst}"
    printfn $"%A{assign2 2 42 |> evalSM assignedMem |> Option.map snd}"

[<EntryPoint>]
let main argv =
    testQ1 ()
    0 // return an integer exit code
