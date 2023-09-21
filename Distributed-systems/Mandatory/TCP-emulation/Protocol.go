package main

import (
	"fmt"
	"math/rand"
)

type message struct {
	sequence        int
	acknowledgement int
	message         int
	check           int
}

func main() {
	// Creates the channels necessary for communication as well as the implementation specific termination channel,
	// which is simply used for terminating the program when the server has recieved all packets
	clientSend := make(chan message, 10000)
	serverSend := make(chan message, 10000)
	terminate := make(chan bool)

	// Initiates the go routines
	go server(serverSend, clientSend, terminate)
	go client(clientSend, serverSend)

	// Stalls the main method until there is an entry on the termination channel
	<-terminate
}

func server(send, receive chan message, terminate chan bool) {

	// Wait until an initial handshake is received
	handshake := <-receive

	// messageChecksum is the checksum which is compared with the amount of packets received
	messageChecksum := handshake.check
	//The servers sequence
	sequence := rand.Int()
	// The incremented sequence from the client, sent as an acknowledgement
	var acknowledgement = handshake.sequence + 1
	// Checksum is initialised at 0 in order to count the amount of packets.
	var checksum int

	fmt.Println("Server recieved hanshake: ", handshake)

	//return handshake with sequence and correctly incremented acknowledgement
	sentMessage := message{sequence: sequence, acknowledgement: acknowledgement}
	send <- sentMessage

	fmt.Println("Server sent message:", sentMessage)

	// Recieve data
	for {
		select {
		case recievedData := <-receive:
			//Increment server calculated checksum to ensure data completeness
			checksum++
			fmt.Println("Server recieved message:", recievedData)

			// Check if all messages have been received
			if checksum == messageChecksum {
				fmt.Printf("Recieved %v messages\n", messageChecksum)
				terminate <- true
				break
			}
		}
	}

}

func client(send, receive chan message) {

	// Generates the sequence and checksum
	// In this simulation we use checksum as the amount of packets to send
	sequence := rand.Int()
	checksum := rand.Intn(100001)

	// Send initial handshake
	sentHandshake := message{sequence: sequence, check: checksum}
	send <- sentHandshake

	// Recieve returned handshake
	handshake := <-receive
	acknowledgement := handshake.sequence + 1
	sequence = handshake.acknowledgement

	// Send all packets
	for i := 0; i < checksum; i++ {

		sentMessage := message{
			sequence:        sequence,
			acknowledgement: acknowledgement,
			message:         rand.Int(),
			check:           checksum,
		}

		send <- sentMessage

		fmt.Println("Client send:", sentMessage)

		sequence++
	}
}
