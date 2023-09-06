package main

import (
	"fmt"
)

func main() {

	var pCards, kCards, hCards, tCards, cards []string

	var inputArray string

	var duplicate bool

	fmt.Scanln(&inputArray)

	for i := 0; i < len(inputArray); i += 3 {

		cardSuit := inputArray[i]
		cardNumber := inputArray[i+1 : i+3]
		cardSuitNumber := inputArray[i : i+3]

		if contains(cards, cardSuitNumber) {
			fmt.Print("GRESKA")
			duplicate = true
		}

		cards = append(cards, cardSuitNumber)

		switch cardSuit {
		case 'P':
			pCards = append(pCards, cardNumber)
		case 'K':
			kCards = append(kCards, cardNumber)
		case 'H':
			hCards = append(hCards, cardNumber)
		case 'T':
			tCards = append(tCards, cardNumber)
		}
	}

	if !duplicate {
		fmt.Print(13-len(pCards), 13-len(kCards), 13-len(hCards), 13-len(tCards))
	}

}

func contains(array []string, search string) (result bool) {

	result = false

	for i := 0; i < len(array); i++ {
		if array[i] == search {
			result = true
			break
		}
	}

	return
}
