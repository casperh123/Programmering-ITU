module ImpParser

    open Eval
    open FParsec.CharParsers
    open Types

    (*

    The interfaces for JParsec and FParsecLight are identical and the implementations should always produce the same output
    for successful parses although running times and error messages will differ. Please report any inconsistencies.

    *)

    open JParsec.TextParser             // Example parser combinator library. Use for CodeJudge.
    // open FParsecLight.TextParser     // Industrial parser-combinator library. Use for Scrabble Project.
    
    let pIntToChar = pstring "intToChar"
    let pPointValue = pstring "pointValue"

    let pCharToInt  = pstring "charToInt"
    let pToUpper    = pstring "toUpper"
    let pToLower    = pstring "toLower"
    let pCharValue  = pstring "charValue"

    let pTrue       = pstring "true"
    let pFalse      = pstring "false"
    let pIsDigit    = pstring "isDigit"
    let pIsLetter   = pstring "isLetter"
    let pIsVowel   = pstring "isVowel"

    let pif       = pstring "if"
    let pthen     = pstring "then"
    let pelse     = pstring "else"
    let pwhile    = pstring "while"
    let pdo       = pstring "do"
    let pdeclare  = pstring "declare"

    let whitespaceChar = satisfy System.Char.IsWhiteSpace
    let pletter = satisfy System.Char.IsLetter
    let palphanumeric  = satisfy System.Char.IsLetterOrDigit

    let spaces         = many whitespaceChar
    let spaces1        = many1 whitespaceChar

    let (.>*>.) p1 p2 = p1 .>> spaces .>>. p2
        
    let (.>*>) p1 p2  = p1 .>> spaces .>> p2
    let (>*>.) p1 p2  = p1 .>>. spaces >>. p2
    
    let parenthesise p = pchar '(' >*>. p .>*> pchar ')'

    let pid =
        let parsePid = (pletter <|> pchar '_') .>>. many (palphanumeric <|> pchar '_')
        let combineChars (firstChar, chars) =
            string firstChar + String.concat "" (List.map string chars)
        parsePid |>> combineChars
    
    let unop op p1 = op >*>. p1
    let binop op p1 p2 = p1 .>*> op >*>. p2

    let TermParse, tref = createParserForwardedToRef<aExp>()
    let ProdParse, pref = createParserForwardedToRef<aExp>()
    let AtomParse, aref = createParserForwardedToRef<aExp>()

    let AddParse = binop (pchar '+') ProdParse TermParse |>> Add <?> "Add"
    let SubParse = binop (pchar '-') ProdParse TermParse |>> Sub <?> "Sub"
    do tref := choice [AddParse; SubParse; ProdParse]

    let MulParse = binop (pchar '*') AtomParse ProdParse |>> Mul <?> "Mul"
    let DivParse = binop (pchar '/') AtomParse ProdParse |>> Div <?> "Sub"
    let ModuloParse = binop (pchar '%') AtomParse ProdParse |>> Mod <?> "Mod"
    do pref := choice [MulParse; DivParse; ModuloParse; AtomParse]
 
    let NParse   = pint32 |>> N <?> "Int"
    let ParParse = parenthesise TermParse
    
    let NegParse = unop (pchar '-') AtomParse |>> (fun x -> Mul(N (-1), x))
    let PointValueParse = unop pPointValue (parenthesise TermParse) |>> PV
    do aref := choice [NParse; NegParse; PointValueParse; ParParse]

    let AexpParse = TermParse 

    let CexpParse = pstring "not implemented"

    let BexpParse = pstring "not implemented"

    let stmntParse = pstring "not implemented"


    let parseSquareProg _ = failwith "not implemented"

    let parseBoardProg _ = failwith "not implemented"

    let mkBoard (bp : boardProg) : board = failwith "not implemented"

