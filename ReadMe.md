<h1>LightsOut</h1>

<h2>How to run</h2>

<p>Provide the file to be executed as program argument. Eg: If you want to run file 01 then input "01.txt".
The input files are already in the archive, under io/resources.</p>

<h2>Description</h2>

<p>Problem was resolved using backtracking and optimised by not drilling down in a direction if the solution is not achievable anymore.
There are 2 solutions: one on a bord containing integers and another one on a bord that has been transformed to binary.
The selection between the 2 is being done in the <code>LightsOutRunner</code> class, by setting the <code>PuzzleType</code> 
variable to the desired value.</p>
<p>The algorithm finishes in a reasonable amount of time up until level 5.</p>