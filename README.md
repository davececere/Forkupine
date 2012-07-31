Forkupine
=========

A way to model a data flow in Java to be executed in parallel using Java fork join.

A few guiding principles prompting the design
1. Flows should be defined in Java (not xml) and clear enough to serve as the document defining the flow.
2. Flows should be strongly typed and as many problems should be caught at compile time. If not compile-time, favor init errors. No secretly skipping messages or runtime exceptions due to instance-of mismatches.
3. Users of the framework shouldn't have to know about fork join.