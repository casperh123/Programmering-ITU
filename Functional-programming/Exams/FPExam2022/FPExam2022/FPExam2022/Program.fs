open System
open Exam2022

let testQ1 () =
    (* Testsfor Q1.1 *)
    printfn "Testing Question 1"
    
    printfn "%A" (countWhite (Square 123uy))
    printfn "%A" (countWhite img)
    
    printfn "ROTATE"

    printfn "%A" (rotateRight (Square 123uy))
    printfn "%A" (rotateRight (Quad(Square 0uy, Square 85uy, Square 170uy, Square 255uy)))
    printfn "%A" (rotateRight img;)
    
    printfn "MAP"
    printfn "%A" (map (fun x -> Square (x + 10uy)) (Square 0uy))
    printfn "%A" (map (fun x -> Square (x + 10uy))
                (Quad (Square 0uy, Square 85uy, Square 170uy, Square 255uy)))
    printfn "%A" (map (fun x -> Quad (Square (x + 10uy),
                Square (x + 20uy),
                Square (x + 30uy),
                Square (x + 40uy)))
                (Square (123uy)))
    
    printfn "BITMAP"

    printfn "%A" (bitmap (Square 120uy))
    printfn "%A" (bitmap (Square 150uy))
    printfn "%A" (bitmap img;)
    
    printfn "FOLDER"
    
    printfn "%A" (fold (fun acc x -> acc + int x) 0 (Square 123uy))
    printfn "%A" (fold (fun acc x -> acc + int x) 0
                    (Quad (Square 0uy, Square 85uy, Square 170uy, Square 255uy)))
    printfn "%A" (fold (fun acc x -> acc + int x) 0 img)
    
    printfn "COUNTWHITE2"
    
    printfn "%A" (countWhite2 (Square 123uy))
    printfn "%A" (countWhite2 img;)
    
    ()

let testQ2 () =
    printfn "Testing Question 2"
    // place debug prints for Q2 here
    ()

let testQ3 () =
    printfn "Testing Question 3"
    
    add (init (fun x y -> x + y) 2 3) (init (fun x y -> x * y) 2 3) |> print
    add (init (fun x y -> x + y) 2 3) (init (fun x y -> x * y) 3 2) |> print
    printfn "%A" (countWhite2 img;)
    printfn "%A" (countWhite2 img;)
    
    
    ()

let testQ4 () =
    printfn "Testing Question 4"
    // place debug prints for Q4 here
    ()

[<EntryPoint>]
let main argv =
    testQ1 ()
    0 // return an integer exit code
