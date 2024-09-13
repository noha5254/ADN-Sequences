# ADN-Sequences
## Overview

### Project: Generation and Detection of DNA Sequences

This project focuses on the generation and detection of specific DNA sequences that repeat within a nucleotide chain. The system is designed to generate DNA sequences based on the following parameters:

* Number of Sequences: The total number of sequences to be generated.
* Maximum Sequence Length: The maximum allowed length for each generated sequence.
* Base Generation Probability: The probability assigned to the occurrence of each type of base (adenine, thymine, cytosine, and guanine) in the generated sequences.
* Pattern Size: The length of the patterns to be identified and detected within the DNA chain.
The project's objective is to provide an effective tool for simulating and analyzing DNA sequences, facilitating the identification of specific patterns and repetitions for various biological and genetic applications.

### Getting Started
To use this project, you'll need to provide input for the number of sequences, the minimum and maximum lengths of sequences, the probabilities for each nucleotide base, and the length of the patterns you wish to detect. The tool will generate the sequences, detect patterns, and output the count of each pattern found.

### Example Usage
1. Enter the number of sequences to generate.
2. Specify the minimum and maximum length for each sequence.
3. Provide the probabilities for adenine (A), cytosine (C), guanine (G), and thymine (T).
4. Input the pattern length to search for.

### Example

``=== Sequence Setup ===
Enter the number of sequences to generate: 100
Enter the minimum length of these sequences: 10
Now enter the maximum length: 20

Enter the probabilities for each base (should sum to 1 or less):
A:    0,25
C:    0,5
G:    0,30
T:    1

Warning! Probabilities do not sum to 1. Normalizing.
Enter the pattern length to find: 3``

### Installation
``git clone https://github.com/yourusername/DNA-Sequences.git
cd DNA-Sequences
javac Seqs.java``
