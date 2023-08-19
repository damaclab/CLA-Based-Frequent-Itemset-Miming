# CellFPM
An Irregular Irregular Cellular Learning Automata based Novel Pattern Mining Approach.

Authors: Moumita Ghosh, Sourav Mondal, Harshita Moondra, Anirban Roy, Kartick Chandra Mondal

## Description
CellFPM is a frequent pattern mining algorithm based on Irregular Cellular Learning Automata. We have successfully adapted the idea of Irregular Cellular Learning Automata and proposed CellFPM to find frequent itemsets from a dataset. The algorithm introduces the notion of frequency and distance between any two items and updates the state of a cell (item) based on previous states of the cell. Here CellFPM has been used for mining frequent itemsets in a set of transactions in a large dataset.


## Citation Details
If you use the source code in your work please reference this work by citing the following paper:

<To be added after publication>

## Execution Environment
The experiments have been executed on a system with the following specifications:
- Processor: Intel i7-10870H @2.21GHZ, 4 core CPU
- Memory: 16GB 2666MHz DDR4
- Operating System: Windows 10, 64-bit

## Comparison Algorithms
CellFPM algorithm has been compared against the following state-of-the-art algorithms: 
- EClat
- FPGrowth*
- LCM
- DFIN
- PrePost
- NEclatClosed
- NegFIN.

We used the publicly available implementation of these algorithms from SPMF(https://www.philippefournier-viger.com/spmf/), a publicly available data mining library, written in Java.
The implementation for CellFPM is in Java.

## Datasets
The datasets that we have used to test CellFPM have also been added to the repository. The datasets have been converted(preprocessed) to a space-separated text file which contains a single transaction on each line. This is the required format for CellFPM to run as expected.

## Setup
In order to successfully run the code, users need to have a JRE(Java Runtime Environment) installed. This can be done by installing a software program called Java SE Development Kit (or JDK for short, and SE means Standard Edition). The JDK contains the JRE. Post installation, the JAVA_HOME variable needs to be added to the systems env variables under classpath section.

## Compiling and Execution
The CellFPM code has been written in a modular fashion with each class in a different file. Every class is designed by keeping the concept of Single Responsibility Principle (SRP) is mind and hence each class deals with a single responsibility. The repository contains both the CellFPM code and datasets. The datasets are present in a directory named ```datasets``` from where they can be reffered. CellFPM can be used for mining frequent patterns in a transaction dataset by executing the following commands.

For executing the class file, users nee to know the following terms:

**Item Count:** The number of unique items in a dataset

**Transaction Count:** The number of transactions in a dataset

**Threshold:** The minimum number of occurence of a particular pattern for it to be included in the final output

### Compiling
If you wish to make any change to the CellFPM code, please re-compile the code to generate the updated class files.

Command:
```javac Project.java```

### Execution
Command:
```java -cp . Project <dataset-path> <item-count> <transaction-count> <threshold>```

Example:
```java -cp . Project datasets/chessPreprocessed.txt" 78 3157 0.2```

