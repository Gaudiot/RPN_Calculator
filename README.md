# RPN Calculator

RPN or Reverse Polish Notation is a stacked based way to make calculations.

## How it works
You keep pushing the input into your stack until the top element of the stack is an operator (+, -, *, /). Then you will take the topmost 2 elements from the stack, make the appropriate operation with them and return the result to the top of the stack.

Some examples
- **`a b +`** is the same as **`a + b`**
- **`a b * c d *`** is the same as **`(a * b) + (c * d)`**
- **`a b c + *`** is the same as **`a * (b + c)`**
- **`a b * c d * - e f * /`** is the same as **`((a*b) - (c*d)) / (e*f)`**