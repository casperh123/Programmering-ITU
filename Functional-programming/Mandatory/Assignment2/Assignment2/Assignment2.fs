﻿module Assignment2

    open System

    let rec downto1 n = 
        if n <= 0 then []
        else n :: downto1(n - 1)
    
    
    let rec downto2 n =
        match n with
        | 0 -> []
        | n -> n :: downto2 (n - 1)

    (*let removeOddIdx xs =
        let rec loop list tail index =
            match tail with
            | [] -> List.rev list
            | x::tail when index % 2 = 0 -> loop (x::list) tail (index + 1)
            | _::tail -> loop list tail (index + 1)
        loop [] xs 0*)
        
    let rec removeOddIdx xs =
        match xs with 
        | x1::x2::tail -> x1 :: (removeOddIdx tail)
        | x::tail -> x :: (removeOddIdx tail)
        | _ -> [] 

    let rec combinePair xs =
        match xs with
        | x1::x2::tail -> (x1, x2) :: combinePair tail
        | _ -> []


    type complex = Complex of float * float
    
    let mkComplex a b = Complex(a,b)
    
    let complexToPair (Complex(a, b)) = (a,b)

    let squareRoot n = n * n
    let (|+|) (Complex(a, b)) (Complex(c, d)) = Complex(a + c, b + d)
    let (|*|) (Complex(a, b)) (Complex(c, d)) = Complex(a*c - b*d, b*c + a*d)
    let (|-|) (Complex(a, b)) (Complex(c, d))= Complex((a + (-c), b + (-d)))
    let (|/|) (Complex(a, b)) (Complex(c, d)) = (a * (c/(c**2 + d**2)), b * ((-d) / (c**2 + d**2)))

    let explode1 (s:string) = Seq.toList s

    let rec explode2 (s:string) =
        match s.Length with
        | 0 -> []
        | _ -> s.[0]::explode2 (s.Remove(0, 1))
        
    let implode (cs : char list) = List.foldBack (fun char accumulator -> System.String(char.ToString()) + accumulator) cs ""
    
    let implodeRev (cs : char list) = List.fold(fun accumulator char -> System.String(char.ToString()) + accumulator) "" cs

    let toUpper _ = failwith "not implemented"

    let ack _ = failwith "not implemented"



    let downto3 _ = failwith "not implemented"

    let fac _ = failwith "not implemented"
    let range _ = failwith "not implemented"

    type word = (char * int) list


    let hello = [] // Fill in your answer here

    type squareFun = (char * int) list -> int -> int -> int

    let singleLetterScore _ = failwith "not implemented"
    let doubleLetterScore _ = failwith "not implemented"
    let tripleLetterScore _ = failwith "not implemented"

    let doubleWordScore _ = failwith "not implemented"
    let tripleWordScore _ = failwith "not implemented"

    type square = (int * squareFun) list


    let oddConsonants _ = failwith "not implemented"
    
    

    let SLS : square = [(0, singleLetterScore)]
    let DLS : square = [(0, doubleLetterScore)]
    let TLS : square = [(0, tripleLetterScore)]

    let DWS : square = SLS @ [(1, doubleWordScore)]
    let TWS : square = SLS @ [(1, tripleWordScore)]

    let calculatePoints _ = failwith "not implemented"