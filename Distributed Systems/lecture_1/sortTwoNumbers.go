package main

import (
	"fmt"
	"sort"
)

func main() {

	numberArray := [2]int{}

	fmt.Scan(&numberArray[0], &numberArray[1])

	sort.Ints(numberArray[:])

	fmt.Print(numberArray[0], " ", numberArray[1])

}
