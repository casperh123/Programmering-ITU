# Mandatory Assignment 2

Mandatory Assignment 2 is centered around a scenario, in which a hospital and patients have to exchange, 
store and use patient data to train a Machine Learning Model. This must be done in a way, such that 
the storage, exchange and calculation protocol is GDPR-compliant, secure and respects patient privacy.

## Question 1 - A GDPR compliant protocol

Using and storing data in accordance with GDPR involves adherence to some strict requirements. Since the processing
of personal information is the main point of our federated learning algorithm, it is limited how much the 
gathering and processing of personal information can be limited. Thus, we have to process and store the patient data.
In accordance with Recital 78, the collected personal information must be stored under 
"Appropriate technical and organisational measures". This means that we have to limit who can access the stored information
, and how well protected it is in the event of a security breach. 

For this use case "Appropriate technical and organisational measures" could be:

- Limiting the amount of people and institutions who process the data.
- Anonymising the data.
- Protecting user data by encrypting the stored data.
- Protecting the personal information when it is in transit (Sent over the network).

## The adversarial model

Given that the hospital will implement a pre-agreed, honest data collection protocol among all patients, we are 
operating under a passive static threat model with an honest majority.

- The adversary can observe the protocol and the data exchanged between parties but cannot sabotage the execution.
- The adversary can corrupt one party.
- The adversary must choose which parties to corrupt before the protocol starts, limiting their influence during execution.

This means that: 
- Internal communications between parties must be secured to prevent any single actor from inferring 
original patient data based on observed exchanges.
- All data must be encrypted during transmission to safeguard against interception by outside actors.
- The data used for training the model must remain aggregated, ensuring that the hospital cannot 
reconstruct individual patient data.

## Proposed solution

The proposed protocol will consist of secure network communication, and secure three party computation using replicated secret
sharing, as described but Bernado David in this lecture notes for lecture 6. The communication between parties will be 
secured using TLS 1.2 or 1.3, depending on which machine is running it.

Using TLS will ensure secure communication between parties. Thus, we need not think about further about attacks, that aim
to intercept the communication between parties. To protect the data, that is shared internally in the protocol, the 
replicated string sharing algorithm ensures that no single party piece together the original data, unless two parties are corrupt
(Which they are not).




