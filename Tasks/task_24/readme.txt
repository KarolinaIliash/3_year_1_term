Implemented custom cyclic barrier. 
Method await is a synchronized method. It increment waiting parts
and if their's amount is initialParts, notify all threads.
Else current thread waits.