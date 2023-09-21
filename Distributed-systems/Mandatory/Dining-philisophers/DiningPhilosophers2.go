package main

import (
	"fmt"
	"time"
)

func main() {

	// runs the program for x seconds
	var x time.Duration = 5

	ch1 := make(chan bool)
	ch2 := make(chan bool)
	ch3 := make(chan bool)
	ch4 := make(chan bool)
	ch5 := make(chan bool)

	toFork1 := make(chan bool)
	toFork2 := make(chan bool)
	toFork3 := make(chan bool)
	toFork4 := make(chan bool)
	toFork5 := make(chan bool)

	go fork2(ch1, toFork1)
	go fork2(ch2, toFork2)
	go fork2(ch3, toFork3)
	go fork2(ch4, toFork4)
	go fork2(ch5, toFork5)

	go eater2(1, ch5, ch1, toFork5, toFork1)
	go eater2(2, ch1, ch2, toFork1, toFork2)
	go eater2(3, ch2, ch3, toFork2, toFork3)
	go eater2(4, ch3, ch4, toFork3, toFork4)
	go eater2(5, ch4, ch5, toFork4, toFork5)

	time.Sleep(x * 1000 * time.Millisecond)
}

func fork2(ch, toFork chan bool) {
	// Announces initial availability
	ch <- true

	// This infinite loop was literally only added to increase use of channels...
	// Otherwise, the fork function could just be used as an initializer for the ch channels
	// and the eaters could announce the fork availability on the channels themselves.
	// In reality all 'toFork' channels are unnecessary.
	for {
		// Awaits feedback to announce availability
		<-toFork
		ch <- true
	}
}

func eater2(number int, rightCH, leftCH, toForkRight, toForkLeft chan bool) {

	var y int = 0

	for {

		// Avoids deadlock by alternating which fork each philosopher prioritizes
		// This works by ensuring that every philosopher doesn't have one fork each and awaits the availability of the other fork
		// This would result in a circular deadlock.
		if number%2 == 0 {
			<-rightCH
			<-leftCH
		} else {
			<-leftCH
			<-rightCH
		}

		y++

		fmt.Printf("Philosopher no: %v, State: Eating, Count: %v\n", number, y)

		// Tells the forks to announce themselves as available
		if number%2 == 0 {
			toForkLeft <- true
			toForkRight <- true
		} else {
			toForkRight <- true
			toForkLeft <- true
		}

		fmt.Printf("Philosopher no: %v, State: Thinking\n", number)
	}

}
