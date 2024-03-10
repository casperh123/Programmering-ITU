module MultiSet

    type MultiSet<'a when 'a : comparison> = Map<'a, uint32> 

    let empty : MultiSet<'a> = Map.empty<'a, uint32>

    let isEmpty (m : MultiSet<'a>) = m.IsEmpty

    let size (m : MultiSet<'a>) = Map.fold (fun acc _ value -> acc + value) 0u m
    
    let contains (x : 'a) (m : MultiSet<'a>) =
       let result = m.TryFind x
       match result with
        | Some _ -> true
        | None -> false

    let numItems (element : 'a) (m : MultiSet<'a>) =
        let result = m.TryFind element
        match result with
        | Some _ -> result.Value
        | None -> 0u

    let add (element : 'a) (amount : uint32) (m : MultiSet<'a>) : MultiSet<'a> =
        let result = m.TryFind element
        match result with
        | Some value -> m.Add (element, (value + amount))
        | None -> m.Add (element, amount)

    let addSingle (element : 'a) (m : MultiSet<'a>) : MultiSet<'a> =
        let result = m.TryFind element
        match result with
        | Some value -> m.Add (element, value + 1u)
        | None -> m.Add (element, 1u)
   
    let remove (element : 'a) (amount : uint32) (m  : MultiSet<'a>) : MultiSet<'a> =
        let result = m.TryFind element
        match result with
        | Some value when value  <= amount -> m.Remove element
        | Some value -> m.Add (element, value - amount)
        | None -> m
            

    let removeSingle (element : 'a) (m : MultiSet<'a>) : MultiSet<'a> =
        let result = m.TryFind element
        match result with
        | Some value when value > 1u -> m.Add (element, value - 1u)
        | _ -> m.Remove element


    let fold (f : 'b -> 'a -> uint32 -> 'b) (x : 'b) (m : MultiSet<'a>) = Map.fold f x m 
    let foldBack (f : 'a -> uint32 -> 'b -> 'b) (m : MultiSet<'a>) (x : 'b) = Map.foldBack f m x 
    
    let ofList (_ : 'a list) : MultiSet<'a> = empty
    let toList (_ : MultiSet<'a>) : 'a list = []


    let map (_ : 'a -> 'b) (_ : MultiSet<'a>) : MultiSet<'b> = empty

    let union (_ : MultiSet<'a>) (_ : MultiSet<'a>) : MultiSet<'a> = empty
    let sum (_ : MultiSet<'a>) (_ : MultiSet<'a>) : MultiSet<'a> = empty
    
    let subtract (_ : MultiSet<'a>) (_ : MultiSet<'a>) : MultiSet<'a> = empty
    
    let intersection (_ : MultiSet<'a>) (_ : MultiSet<'a>) : MultiSet<'a> = empty
       
    