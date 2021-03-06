The challenge asked us to reverse an array. It gave us the option to either reverse the array in place or create a new reversed array. We came up with solutions to both.

## Challenge

We're being asking to write a method that reverses an array, without using any of the built in methods available that reverse an array.

## Approach & Efficiency

The first approach we took created a new reversed array. The second approach involved swapping elements in place.

The main differences is that the first approach allocates a new array in memory, and thus doubles the amount of memory used. The second approach swaps elements in place by assigning them to a temporary variable. This doesn't require allocating any extra memory.

Regarding time efficiency, both approach take linear time in the length of the array.

## Solution

[Our whiteboard in progress](../assets/array-reverse.png)

## Collaborators

This solution was whiteboarded together with my peers [Pablito14](https://github.com/Pablito14), [F0rgiv](https://github.com/F0rgiv), [GoldenDog190](https://github.com/GoldenDog190).
