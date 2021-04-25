# Cryptography-Tools
This is a repository of all the cryptography tools I'll be making.

<h1>CryptographyAnalyzerTool</h1>
The first project added is the CryptographyAnalysisTool and it comes with a framework to make adding to it as easy as possible (can be done in less than an hour).
The tool is a window that allows the user to play around with, test and build their own algorithms or the one's already included and use them in a GUI environment.
This is done by having a framework located in the package "core".

<h2>How to add a new Protocol</h2>
The framework is fully commented and located in the package "framework". To add a new algorithm, follow the steps
<ol>
<li>Read over the Caesar Cipher Example</li>
<li>Make a new class in the algorithms package that inherrits the type of algorithm you're writing</li>
<li>Write solutions for the algorithm and @Override the method which says which modes you can do and change it so it only includes the ones you wrote</li>
<li>pick or create a Algorithm Display view for the Algorithm and inherrit that and fill in the information </li>
<li> add the display to the main application class next to the others</li>
</ol>

<h2>Why?</h2>
Having a tool like this allows ciphers to be cracked faster, algorithms to be tested/prototyped easier and allows for an easy way show off and use any algorithms you've created.

<h1>Frequency Analyzer tool</h1>
The frequency analyzer tool is a simple histogram tool that shows the number of occurances % of each letter against the average in the given language.
It allows figuring out which cryptography algorithm to use to be easier than it would be without.
