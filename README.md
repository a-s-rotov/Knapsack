# Knapsack

The solution uses the brute-force approach. The algorithm contains two parts:

* Loading and parsing data
* Finding the best solution

The step of loading data uses regex for parsing. First, we divide the string into two parts: capacity and item. After
that, we use a new rexeg and get weight, cost, and index. Next, we store those data into the Record structure. I have
used the brute-force approach for my solution. First, we have to create a list of all appropriate knapsacks. We have to
use recursion for that, but we will get many dead-end branches if we just combine all our items (repeated knapsacks). I
have used HashSet to avoid this. An identifier stored in a HashSet is used to check whether each new combination is
unique. The identifier is a combination of sorted indexes of all the items within the knapsack. When the new knapsack is
created, we check its identifier. If it is unique, we process this branch further. Otherwise, we consider the branch a
dead-end and do not store it.

      A      B   C
     / \    /
    AB AC  BC
     |
    ABC

The last step is finding the best solution. We have all possible solutions, and we have to go through them and choose
the best one which will be the lightest and the most expensive one.