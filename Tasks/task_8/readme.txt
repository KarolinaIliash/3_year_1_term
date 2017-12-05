App to managing port with bounded max weight and bounded piers amount.
Ships firstly wait while it can dock, then wait while it can add or remove
some weight or both if it wants so. Every check is doing with
synchronized port method.
Threads which are starting in main function can not finish in order to
their finiteness amount and random values.