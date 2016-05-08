CarJump Compression API
=======================

A CarJump Compression application built in Scala/Spray which deals with large sequences of elements with runs of duplicate values at some high frequency, such that compression is viable and desired for some reason (persistence, memory management, etc)

Getting Started
---------------

1. Clone the repository
2. In the base directory, start sbt
3. Compile the application by typing 'compile'
4. Run the application by typing 'run' or run the tests by typing 'test'
5. Create assembly jar by typing 'assembly'
6. type 'eclipse' to create eclipse related project files
7. Use the api...Examples:

   a) http://localhost:8080/api/add  (Post)
   	request body contains one or more elements (newline delimited)
  	returns empty response
   b) http://localhost:8080/api/0 or http://localhost:8080/api/1 or http://localhost:8080/api/2 and so on
   	returns element at specified 0-based index
   c) http://localhost:8080/api/list
   	returns run-length encoded list of all elements (newline delimited)
   
   

