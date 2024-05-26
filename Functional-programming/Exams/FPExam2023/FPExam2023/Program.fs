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
    printfn "%A" (collatz 1000)


let testQ4 () =
    printfn "Testing Question 4"
    // place debug prints for Q4 here
    ()

[<EntryPoint>]
let main argv =
    testQ1 ()
    0 // return an integer exit code
