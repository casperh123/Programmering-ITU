module Assignment1

    open System
    
    let sqr x = x * x

    let pow x n = Math.Pow(x, n)
    
    let rec fib =
        function
        | 0 -> 0
        | 1 -> 1
        | n -> fib(n - 1) + fib(n - 2)

    let rec sum n =
        match n with
        | 0 -> 0
        | n -> n + sum(n - 1)

    let dup s:string = s + s

    let rec dupn s n  =
        match n with
        | 0 -> ""
        | _ -> s + dupn s (n - 1)

    let timediff (hh1, mm1) (hh2, mm2) = (hh2 - hh1) * 60 + mm2 - mm1
       
    let minutes (h, m) = timediff(0, 0) (h, m)

    let rec bin (n, k)=
        match n, k with
        | _, 0 -> 0
        | n, k when k = n -> 1
        | _, _ -> bin(n - 1, k - 1) + bin(n - 1, k)

    let curry f a b = f (a, b)
    let uncurry f (a, b) = f a b

    let empty _ = failwith "not implemented"

    let add _ = failwith "not implemented"

    let singleLetterScore _ = failwith "not implemented"
    let doubleLetterScore _ = failwith "not implemented"
    let trippleLetterScore _ = failwith "not implemented"

    let hello _ = failwith "not implemented"