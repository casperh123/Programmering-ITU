module Assignment5

(* Exercise 5.1 *)

let sum m n = 
    let rec sumA m n acc index =
        match n with
        | _ when n = 0 -> acc + m
        | _ -> sumA m (n - 1) (acc + m + index) (index + 1)
    sumA m n 0 1


(* Exercise 5.2 *)

let length list =
    let rec lengthA list acc =
        match list with  
        | [] -> acc
        | _::xs -> lengthA xs (acc + 1)
    lengthA list 0

(* Exercise 5.3 *)

let foldBack folder list acc = 
    let rec foldBackContinuation lst cont = 
        match lst with
        | [] -> cont
        | x::xs -> foldBackContinuation 


(* Exercise 5.4 *)

let factA _ = failwith "not implemented"

let factC _ = failwith "not implemented"


(* TODO: *)
(* Compare the running time between factA and factC. Which solution is faster and why? 
   <Your answer goes here>
*)

(* Exercise 5.5 *)

let fibW x =
    let mutable res1 = 0
    let mutable res2 = 1
    let mutable i = 1
    while (i <= x) do
        let temp = res1
        res1 <- res2
        res2 <- temp + res2
        i <- i + 1
    res1

let fibA _ = failwith "not implemented"

let fibC _ = failwith "not implemented"


(* TODO: *)
(* Compare the running time of fibW, fibA and fibC. Which solution is faster and why? 
   <Your answer goes here>

*)

(* Exercise 5.6 *)

let rec bigListK c =
    function
    | 0 -> c []
    | n -> bigListK (fun res -> 1 :: c res) (n - 1)

(* TODO *)
(* The call bigListK id 130000 causes a stack overflow. 
   Analyse the problem and describe exactly why this happens. 
   Why is this not an iterative function?
   
   To make a compelling argument (and to prepare for the exam) you must make a step-by-step evalution of a call to
   bigListK. Test correctness of your evaluations by ensuring that they all evaluate to the same value. For example:
   
   (5 + 4) * 3 -->
   9 * 3 -->
   27
   
   If you input any of these lines into FSharp Interactive it will produce the same result. Do the same here and point
   to where you can see that this function is not tail recursive.

   <Your answer goes here>
*)

type aExp =
    | N of int
    | V of string
    | WL
    | PV of aExp
    | Add of aExp * aExp
    | Sub of aExp * aExp
    | Mul of aExp * aExp
    | CharToInt of cExp


and cExp =
   | C  of char  (* Character value *)
   | CV of aExp  (* Character lookup at word index *)
   | ToUpper of cExp
   | ToLower of cExp
   | IntToChar of aExp


let arithEvalSimple _ = failwith "not implemented"

let charEvalSimple _ = failwith "not implemented"

let arithEval _ = failwith "not implemented"
let charEval _ = failwith "not implemented"